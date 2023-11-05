package lotto;

import lotto.domain.numbergenerator.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoController lottoController = new LottoController(inputView, outputView, new RandomNumberGenerator());
        lottoController.run();
    }
}
