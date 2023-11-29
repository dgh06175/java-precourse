package racingcar.domain;

import static racingcar.exception.ErrorMessage.NAME_LENGTH_ERROR;

import racingcar.exception.InvalidInputException;
import racingcar.util.NumberGenerator;

public class Car implements Comparable<Car> {
    private static final int MAX_NAME_LENGTH = 5;
    private final NumberGenerator generator;
    private final String name;
    private int position;

    public Car(String name, NumberGenerator generator) {
        validateName(name);
        this.name = name;
        this.position = 0;
        this.generator = generator;
    }

    public void tryMove() {
        if (canMove(generator.generate())) {
            position += 1;
        }
    }

    @Override
    public int compareTo(Car otherCar) {
        return Integer.compare(this.position, otherCar.position);
    }

    public String getName() {
        return name;
    }

    private boolean canMove(int value) {
        return value >= 4;
    }

    private void validateName(String name) {
        if (name.length() > 5) {
            throw new InvalidInputException(NAME_LENGTH_ERROR.message);
        }
    }
}
