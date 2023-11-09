package christmas.view;

public class InputView {
    OutputView outputView = new OutputView();

    public int readDate() {
        outputView.printReadDateMessage();
        return InputUtil.readInt();
    }

    public String readMenu() {
        outputView.printReadMenuMessage();
        return InputUtil.readString();
    }
}
