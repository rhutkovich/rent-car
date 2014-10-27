package com.epam.rentcar.logic;

import java.sql.Connection;

import com.epam.rentcar.dao.AbstractDAO;
import com.epam.rentcar.dao.CarDAO;
import com.epam.rentcar.dao.DAOException;
import com.epam.rentcar.db.connectionpool.ConnectionPool;
import com.epam.rentcar.db.connectionpool.ConnectionPoolException;
import com.epam.rentcar.entity.Car;
import com.epam.rentcar.logic.exception.RentLogicException;

public class RentLogic {
    
    public static Car getRentedCar(int id) throws RentLogicException {
	Connection connection = null;
	Car rentedCar = null;
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Car> carDAO = new CarDAO(connection);
	    rentedCar = carDAO.findEntityById(id);
	    
	} catch (ConnectionPoolException e) {
	    throw new RentLogicException("Can't take connection from connection pool",e);
	} catch (DAOException e) {
	    throw new RentLogicException("Troubles with searching car by ID",e);
	} finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	
	return rentedCar;
    }
}
