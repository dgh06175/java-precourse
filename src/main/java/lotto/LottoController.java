package lotto;

import static lotto.domain.util.LottoParser.*;

import java.util.*;
import lotto.domain.*;
import lotto.domain.numbergenerator.NumberGenerator;
import lotto.exception.InputException;
import lotto.exception.LottoException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator generator;
    private LottoGameService service;

    public LottoController(InputView inputView, OutputView outputView, NumberGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
        this.service = new LottoGameService();
    }

    public void run() {
        // 로또 발행
        int money = receiveValidMoney();
        List <Lotto> lottos = service.generateLottos(money, generator);
        // 발행 로또 출력
        outputView.printLottoNumbers(parseLottoToInteger(lottos));
        // 로또 당첨 판단 객체 생성
        List<Integer> validWinningNumbers = receiveValidWinningNumbers();
        int validBonusNumber = receiveValidBonusNumber();
        // 당첨 등수 맵 생성
        Map<LottoRank, Integer> rankCount = service.getResultRankCount(lottos, validWinningNumbers, validBonusNumber);
        // 결과 출력
        showResult(rankCount);
    }

    private int receiveValidMoney() {
        while(true) {
            outputView.printInputMoneyMessage();
            try {
                String money = inputView.inputMoney();
                return parseMoney(money);
            } catch (InputException | LottoException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<Integer> receiveValidWinningNumbers() {
        while(true) {
            outputView.printInputWinningNumbersMessage();
            try {
                String winningNumbers = inputView.inputWinningNumber();
                return parseWinningNumbers(winningNumbers);
            } catch (InputException | LottoException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int receiveValidBonusNumber() {
        while(true) {
            outputView.printInputBonusNumberMessage();
            try {
                String bonusNumber = inputView.inputBonusNumber();
                return parseBonusNumber(bonusNumber);
            } catch (InputException | LottoException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void showResult(Map<LottoRank, Integer> rankCount) {
        outputView.printRanksCount(rankCount);
        outputView.printRateOfProfits(rankCount);
    }
}
