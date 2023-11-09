package christmas.view;

public class InputView {
    OutputView outputView = new OutputView();

    public String readDate() {
        outputView.printReadDateMessage();
        return InputUtil.readInt();
    }

    public String readOrder() {
        outputView.printReadOrderMessage();
        return InputUtil.readString();
    }
}
