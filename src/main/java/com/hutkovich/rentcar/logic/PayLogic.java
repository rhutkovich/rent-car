package com.hutkovich.rentcar.logic;

import java.sql.Connection;
import java.sql.Date;

import com.hutkovich.rentcar.dao.*;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPool;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPoolException;
import com.hutkovich.rentcar.entity.Account;
import com.hutkovich.rentcar.entity.Car;
import com.hutkovich.rentcar.entity.Order;
import com.hutkovich.rentcar.logic.exception.PayLogicException;

public class PayLogic {

    public static boolean rentCar(int userId, int carId, int term) throws PayLogicException {
	boolean result = false;
	Connection connection = null;
	
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Order> orderDAO = new OrderDAO(connection);
	    AbstractDAO<Account> accountDAO = new AccountDAO(connection);
	    AbstractDAO<Car> carDAO = new CarDAO(connection);
	    
	    if ((accountDAO.findEntityById(userId) != null)
		    && (carDAO.findEntityById(carId) != null)) {
		Order newOrder = new Order();
		newOrder.setIdAccount(userId);
		newOrder.setIdCar(carId);
		newOrder.setOrderDate(new Date(System.currentTimeMillis()));
		// 24*60*60*1000 = 86400000
		newOrder.setTerm(new Date(System.currentTimeMillis()+term*86400000L));
		newOrder.setCrashed(false);
		newOrder.setFree(false);
		
		result = orderDAO.create(newOrder);
	    } else {
		throw new PayLogicException("Null id's");
	    }
	    
	} catch (ConnectionPoolException e) {
	    throw new PayLogicException("Troubles with conenction pool",e);
	} catch (DAOException e) {
	    throw new PayLogicException("Troubles in the DAO",e);
	} finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	
	return result;
    }
    
}
