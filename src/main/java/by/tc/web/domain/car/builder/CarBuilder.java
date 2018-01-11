package by.tc.web.domain.car.builder;

import by.tc.web.domain.car.Car;

public class CarBuilder {
    private String model;
    private char[] number;

    public CarBuilder() {}

    public CarBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder number(char[] number) {
        this.number = number;
        return this;
    }

    public Car build() {
        return new Car(number, model);
    }
}
