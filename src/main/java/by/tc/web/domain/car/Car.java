package by.tc.web.domain.car;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Car implements Serializable {
    private static final long serialVersionUID = 2415287254651393896L;
    private char[] number;
    private String model;

    public Car() {}

    public Car(char[] number, String model) {
        this.number = number;
        this.model = model;
    }

    public char[] getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Car car = (Car) obj;
        return Arrays.equals(number, car.number) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(model);
        result = 31 * result + Arrays.hashCode(number);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + Arrays.toString(number) +
                ", model='" + model + '\'' +
                '}';
    }
}
