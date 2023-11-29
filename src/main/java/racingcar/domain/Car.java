package racingcar.domain;

import static racingcar.exception.ErrorMessage.NAME_LENGTH_ERROR;

import racingcar.exception.InvalidInputException;
import racingcar.util.NumberGenerator;

public class Car implements Comparable<Car> {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_CONDITION = 4;
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

    public int getPosition() {
        return position;
    }

    private boolean canMove(int value) {
        return value >= MOVE_CONDITION;
    }

    private void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidInputException(NAME_LENGTH_ERROR.message);
        }
    }
}
