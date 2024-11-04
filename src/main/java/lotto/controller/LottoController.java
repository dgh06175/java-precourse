package lotto.controller;

import static lotto.exception.LottoErrorMessage.BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBER;
import static lotto.exception.LottoErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoPrice;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoController {
    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;

    public LottoController(LottoInputView lottoInputView, LottoOutputView lottoOutputView) {
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
    }

    public void run() {
        // 로또 구입 금액 에 알맞은 개수만큼 로또 발행
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> lottos = buyLottos(lottoMachine);

        // 발행한 로또 수량 및 번호 출력
        printLottoNumbers(lottos);

        // 당첨 번호 입력 받기
        Lotto winningLotto = inputWinningNumbers();

        // 보너스 번호 입력 받기
        int bonusNumber = inputBonusNumber(winningLotto);

        // 구매한 로또와 당첨 번호 비교
        HashMap<LottoPrice, Integer> prices = lottoMachine.calculateLottoPrice(lottos, winningLotto, bonusNumber);

        // 당첨 내역 출력
        lottoOutputView.printLottoWinPrice(prices);

        // 수익률 출력
        lottoOutputView.printProfitRate(prices, lottos.size());
    }

    private List<Lotto> buyLottos(LottoMachine lottoMachine) {
        return executeWithRetry(() -> {
            lottoOutputView.printInputLottoMoneyMessage();
            int money = lottoInputView.readInt();
            return lottoMachine.issueLottos(money);
        });
    }

    private <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printLottoNumbers(List<Lotto> lottos) {
        List<List<Integer>> lottosNumbers = lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
        lottoOutputView.printLottosNumber(lottosNumbers);
    }

    private Lotto inputWinningNumbers() {
        return executeWithRetry(() -> {
            lottoOutputView.printWinningNumbersMessage();
            String input = lottoInputView.readString();
            return parseWinningNumbers(input);
        });
    }

    private Lotto parseWinningNumbers(String input) {
        List<Integer> winningNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .toList();
        return new Lotto(winningNumbers);
    }

    private int inputBonusNumber(Lotto lottoNumbers) {
        return executeWithRetry(() -> {
            System.out.println("\n보너스 번호를 입력해 주세요.");
            int bonusNumber = Integer.parseInt(Console.readLine());
            validateBonusNumber(bonusNumber, lottoNumbers);
            return bonusNumber;
        });
    }

    private void validateBonusNumber(int bonusNumber, Lotto lottoNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE.message);
        }
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBER.message);
        }
    }
}
