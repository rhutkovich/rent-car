/*
 * 16.09.2014 Roman Hutkovich
 */
package com.hutkovich.rentcar.entity;

import java.sql.Date;

public class Car extends Entity {
    private String carClass;
    private String manufacturer;
    private String model;
    private String color;
    private String carcase;
    private Date issueDate;
    private String transmission;
    private int engineVol;
    private int fuelFlow;
    private String engineType;
    private float cost;

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarcase() {
        return carcase;
    }

    public void setCarcase(String carcase) {
        this.carcase = carcase;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getEngineVol() {
        return engineVol;
    }

    public void setEngineVol(int engineVol) {
        this.engineVol = engineVol;
    }

    public int getFuelFlow() {
        return fuelFlow;
    }

    public void setFuelFlow(int fuelFlow) {
        this.fuelFlow = fuelFlow;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((carClass == null) ? 0 : carClass.hashCode());
        result = prime * result + ((carcase == null) ? 0 : carcase.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + Float.floatToIntBits(cost);
        result = prime * result
                + ((engineType == null) ? 0 : engineType.hashCode());
        result = prime * result + engineVol;
        result = prime * result + fuelFlow;
        result = prime * result
                + ((issueDate == null) ? 0 : issueDate.hashCode());
        result = prime * result
                + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result
                + ((transmission == null) ? 0 : transmission.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (carClass == null) {
            if (other.carClass != null)
                return false;
        } else if (!carClass.equals(other.carClass))
            return false;
        if (carcase == null) {
            if (other.carcase != null)
                return false;
        } else if (!carcase.equals(other.carcase))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
            return false;
        if (engineType == null) {
            if (other.engineType != null)
                return false;
        } else if (!engineType.equals(other.engineType))
            return false;
        if (engineVol != other.engineVol)
            return false;
        if (fuelFlow != other.fuelFlow)
            return false;
        if (issueDate == null) {
            if (other.issueDate != null)
                return false;
        } else if (!issueDate.equals(other.issueDate))
            return false;
        if (manufacturer == null) {
            if (other.manufacturer != null)
                return false;
        } else if (!manufacturer.equals(other.manufacturer))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (transmission == null) {
            if (other.transmission != null)
                return false;
        } else if (!transmission.equals(other.transmission))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Car [carClass=" + carClass + ", manufacturer=" + manufacturer
                + ", model=" + model + ", color=" + color + ", carcase="
                + carcase + ", issueDate=" + issueDate + ", transmission="
                + transmission + ", engineVol=" + engineVol + ", fuelFlow="
                + fuelFlow + ", engineType=" + engineType + ", cost=" + cost
                + "]";
    }
}
