package com.epam.rentcar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.rentcar.entity.Car;

public class CarDAO extends AbstractDAO<Car> {
    private static final String SELECT_ALL = "SELECT * FROM Cars";
    private static final String SELECT_BY_ID = "SELECT * FROM Cars WHERE ID_car=";
    private static final String DELETE_BY_ID = "DELETE FROM Cars WHERE ID_car = ";
    private static final String INSERT_ENTITY = "INSERT INTO Cars "
	    +"(Class,Manufacturer,Model,Color,Carcase,Issue_date,Transmission,Engine_vol,Fuel_flow,Engine_type,Cost) VALUES ";
    private static final String UPDATE_ENTITY = "UPDATE Cars SET ";
    
    public CarDAO(Connection connection) {
	super(connection);
    }

    @Override
    public List<Car> findAll() throws DAOException {
	Statement st = null;
	ResultSet cars = null;
	List<Car> givenCars = new ArrayList<Car>();
	
	try {
	    st = connection.createStatement();
	    cars = st.executeQuery(SELECT_ALL);
	    while (cars.next()) {
		Car car = new Car();
		car.setId(cars.getInt(1));
		car.setCarClass(cars.getString(2));
		car.setManufacturer(cars.getString(3));
		car.setModel(cars.getString(4));
		car.setColor(cars.getString(5));
		car.setCarcase(cars.getString(6));
		car.setIssueDate(cars.getDate(7));
		car.setTransmission(cars.getString(8));
		car.setEngineVol(cars.getInt(9));
		car.setFuelFlow(cars.getInt(10));
		car.setEngineType(cars.getString(11));
		car.setCost(cars.getFloat(12));
		
		givenCars.add(car);
	    }
	} catch (SQLException e) {
	    throw new DAOException("Cannot find all cars in Cars table",e);
	} finally {
	    close(st,cars);
	}
	return givenCars;
    }

    @Override
    public Car findEntityById(int id) throws DAOException {
	Car car = null;
	Statement st = null;
	ResultSet givenCar = null;
	
	try {
	    st = connection.createStatement();
	    givenCar = st.executeQuery(SELECT_BY_ID+id);
	    if (givenCar.next()) {
		car = new Car();
		car.setId(givenCar.getInt(1));
		car.setCarClass(givenCar.getString(2));
		car.setManufacturer(givenCar.getString(3));
		car.setModel(givenCar.getString(4));
		car.setColor(givenCar.getString(5));
		car.setCarcase(givenCar.getString(6));
		car.setIssueDate(givenCar.getDate(7));
		car.setTransmission(givenCar.getString(8));
		car.setEngineVol(givenCar.getInt(9));
		car.setFuelFlow(givenCar.getInt(10));	
		car.setEngineType(givenCar.getString(11));
		car.setCost(givenCar.getFloat(12));
	    }
	} catch (SQLException e) {
	    throw new DAOException("Cannot find car by id.",e);
	} finally {
	    close(st,givenCar);
	}
	return car;
    }

    @Override
    public boolean delete(int id) throws DAOException {
	Statement query = null;
	boolean result = false;
	
	try {
	    query = connection.createStatement();
	    result = (query.executeUpdate(DELETE_BY_ID+id) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Unable delete car by ID",e);
	} finally {
	   close(query); 
	}
	
	return result;
    }

    @Override
    public boolean delete(Car entity) throws DAOException {
	Car carFromDB = findEntityById(entity.getId());
	return (carFromDB.equals(entity))? delete(entity.getId()) : false;
    }

    @Override
    public boolean create(Car entity) throws DAOException {
	Statement st = null;
	boolean result = false;
	
	try {
	    st = connection.createStatement();
	    result = (st.executeUpdate(makeCreateQuery(entity)) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Unable to create add new car to DB.",e);
	} finally {
	    close(st);
	}
	
	return result;
    }
    
    private String makeCreateQuery(Car entity) {
	StringBuilder query = new StringBuilder(INSERT_ENTITY);
	query.append("(\"").append(entity.getCarClass()).append("\",").
		append("\"").append(entity.getManufacturer()).append("\",").
		append("\"").append(entity.getModel()).append("\",").
		append("\"").append(entity.getColor()).append("\",").
		append("\"").append(entity.getCarcase()).append("\",").
		append("\"").append(entity.getIssueDate()).append("\",").
		append("\"").append(entity.getTransmission()).append("\",").
		append(entity.getEngineVol()).append(",").
		append(entity.getFuelFlow()).append(",").
		append("\"").append(entity.getEngineType()).append("\",").
		append("\"").append(entity.getCost()).append("\",").append(")");
	
	return query.toString();
    }

    @Override
    public Car update(Car entity) throws DAOException {
	Statement st = null;
	Car updatedCar = null;
	
	try {
	    st = connection.createStatement();
	    if (st.executeUpdate(makeUpdateQuery(entity)) > 0) {
		updatedCar = findEntityById(entity.getId());
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't update Cars table",e);
	} finally {
	    close(st);
	}
	
	return updatedCar;
    }
   
    private String makeUpdateQuery(Car entity) {
	StringBuilder query = new StringBuilder(UPDATE_ENTITY);
	query.append("Class=\"").append(entity.getCarClass()).append("\",").
		append("Manufacturer=\"").append(entity.getManufacturer()).append("\",").
		append("Model=\"").append(entity.getModel()).append("\",").
		append("Color=\"").append(entity.getColor()).append("\",").
		append("Carcase=\"").append(entity.getCarcase()).append("\",").
		append("Issue_date=\"").append(entity.getIssueDate()).append("\",").
		append("Transmission=\"").append(entity.getTransmission()).append("\",").
		append("Engine_vol=").append(entity.getEngineVol()).append(",").
		append("Fuel_flow=").append(entity.getFuelFlow()).
		append("Engine_type=\"").append(entity.getEngineType()).append("\",").
		append("Cost=\"").append(entity.getCost()).append("\"").
		append(" WHERE ID_car=").append(entity.getId());
	
	return query.toString();
    }

}
