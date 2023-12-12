package menu.util;

import java.util.List;

public class FakeGenerator implements Generatable {
    private final int fakeNumber;
    private final int fakeElementIndex;

    public FakeGenerator(int fakeNumber, int fakeElementIndex) {
        this.fakeNumber = fakeNumber;
        this.fakeElementIndex = fakeElementIndex;
    }

    @Override
    public int getRandomNumber() {
        return fakeNumber;
    }

    @Override
    public String getRandomElementOf(List<String> values) {
        return values.get(fakeElementIndex);
    }
}
