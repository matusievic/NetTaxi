package by.tc.web.domain.user.impl;

import by.tc.web.domain.user.User;

import java.util.Objects;

public class TaxiDriver extends User {
    private String phone;
    private String carModel;
    private String carNumber;

    public TaxiDriver() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        if (!super.equals(obj)) { return false; }

        TaxiDriver taxiDriver = (TaxiDriver) obj;

        if (Objects.equals(phone, taxiDriver.phone)) { return false; }
        if (Objects.equals(carModel, taxiDriver.carModel)) { return false; }
        if (Objects.equals(carNumber, taxiDriver.carNumber)) { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        return result;
    }
}
