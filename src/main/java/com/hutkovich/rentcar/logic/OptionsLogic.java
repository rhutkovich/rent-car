package com.hutkovich.rentcar.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hutkovich.rentcar.entity.Car;

public class OptionsLogic {
    private static final String PARAM_NAME_MANUFACTURER = "manufacturer";
    private static final String PARAM_NAME_MODEL = "model";
    private static final String PARAM_NAME_COLOR = "color";
    private static final String PARAM_NAME_DATE_FROM = "date-from";
    private static final String PARAM_NAME_DATE_TO = "date-to";
    private static final String PARAM_NAME_COST_FROM = "cost-from";
    private static final String PARAM_NAME_COST_TO = "cost-to";
    private static final String PARAM_NAME_ANY = "any";
    
    public static List<Car> getSuitableCarsList(HttpServletRequest request, List<Car> freeCars) {
	List<Car> cars = new ArrayList<Car>();
	OptionsTransfer options = getOptions(request, freeCars);
	
	for (Car car : freeCars) {
	    if (isSuitableOption(car.getManufacturer(),options.getSelectedManufacturer())
		    && isSuitableOption(car.getModel(),options.getSelectedModel())
		    && isSuitableOption(car.getColor(),options.getSelectedColor())
		    && isBelongsInterval(options.getSelectedDateFrom(), options.getSelectedDateTo(), Integer.toString((car.getIssueDate().getYear()+1900)))
		    && isBelongsInterval(options.getSelectedCostFrom(), options.getSelectedCostTo(), Float.toString(car.getCost()))
		    ) {
		cars.add(car);
	    }
	}
	    
	return cars;
    }
    
    // Если одна из опций равна any, то всегда true,
    // т.к. пользователю не важно значение этой опции
    private static boolean isSuitableOption(String value1, String value2) {
	return (PARAM_NAME_ANY.equals(value2))? true : value1.equals(value2);
    }
    
    private static boolean isBelongsInterval(String from, String to, String value) {
	float realFrom = (PARAM_NAME_ANY.equals(from))? 0 : Float.parseFloat(from);
	float realTo = (PARAM_NAME_ANY.equals(to))? 99999 : Float.parseFloat(to);
	float realValue = Float.parseFloat(value);
	
	return (realValue <= realTo && realValue >= realFrom)? true : false;
    }
    
    //OptionsTransfer - объект, хранящий в себе различные опции, доступные пользователю.
    public static OptionsTransfer getOptions(HttpServletRequest request,List<Car> freeCars) {
	OptionsTransfer optionsTransfer = new OptionsTransfer(freeCars);
	
	optionsTransfer.setSelectedManufacturer(request.getParameter(PARAM_NAME_MANUFACTURER));
	optionsTransfer.setSelectedModel(request.getParameter(PARAM_NAME_MODEL));
	optionsTransfer.setSelectedColor(request.getParameter(PARAM_NAME_COLOR));
	optionsTransfer.setSelectedDateFrom(request.getParameter(PARAM_NAME_DATE_FROM));
	optionsTransfer.setSelectedDateTo(request.getParameter(PARAM_NAME_DATE_TO));
	optionsTransfer.setSelectedCostFrom(request.getParameter(PARAM_NAME_COST_FROM));
	optionsTransfer.setSelectedCostTo(request.getParameter(PARAM_NAME_COST_TO));
	
	return optionsTransfer;
    }
}
