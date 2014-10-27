package com.epam.rentcar.logic;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.epam.rentcar.entity.Car;

public class OptionsTransfer {
    //----------Available options---------------    
    private Set<String> manufacturers;
    private Set<String> models;
    private Set<String> colors;
    private Set<String> dates;
    private Set<Float> costs;
    //-----------Selected-----------------------
    private String selectedManufacturer;
    private String selectedModel;
    private String selectedColor;
    private String selectedDateFrom;
    private String selectedDateTo;
    private String selectedCostFrom;
    private String selectedCostTo;
    //------------------------------------------
    
    public OptionsTransfer(List<Car> freeCars) {
	manufacturers = new TreeSet<String>();
	models = new TreeSet<String>();
	colors = new TreeSet<String>();
	dates = new TreeSet<String>();
	costs = new TreeSet<Float>();
	
	for(Car car : freeCars) {
	    manufacturers.add(car.getManufacturer());
	    models.add(car.getModel());
	    colors.add(car.getColor());
	    dates.add(Integer.toString(car.getIssueDate().getYear()+1900));
	    costs.add(car.getCost());
	}	
    }
    
    public String getSelectedManufacturer() {
        return selectedManufacturer;
    }

    public void setSelectedManufacturer(String selectedManufacturer) {
        this.selectedManufacturer = selectedManufacturer;
    }

    public String getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(String selectedModel) {
        this.selectedModel = selectedModel;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getSelectedDateFrom() {
        return selectedDateFrom;
    }

    public void setSelectedDateFrom(String selectedDateFrom) {
        this.selectedDateFrom = selectedDateFrom;
    }

    public String getSelectedDateTo() {
        return selectedDateTo;
    }

    public void setSelectedDateTo(String selectedDateTo) {
        this.selectedDateTo = selectedDateTo;
    }

    public String getSelectedCostFrom() {
        return selectedCostFrom;
    }

    public void setSelectedCostFrom(String selectedCostFrom) {
        this.selectedCostFrom = selectedCostFrom;
    }

    public String getSelectedCostTo() {
        return selectedCostTo;
    }

    public void setSelectedCostTo(String selectedCostTo) {
        this.selectedCostTo = selectedCostTo;
    }

    public Set<String> getManufacturers() {
	return manufacturers;
    }
    
    public Set<String> getModels() {
	return models;
    }
    
    public Set<String> getColors() {
	return colors;
    }
    
    public Set<String> getDates() {
	return dates;
    }
    
    public Set<Float> getCosts() {
	return costs;
    }
}
