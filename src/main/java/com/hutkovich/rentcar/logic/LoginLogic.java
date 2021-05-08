package com.hutkovich.rentcar.logic;

import com.hutkovich.rentcar.dao.*;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPool;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPoolException;
import com.hutkovich.rentcar.entity.*;
import com.hutkovich.rentcar.logic.exception.LoginLogicException;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class LoginLogic {
    private static final String ALGORITHM = "SHA-256";
    private static final String ENCODING = "UTF-8";
    
    public static Account getAccount(String login, String password) throws LoginLogicException {
	Connection connection = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
	    digest.update(password.getBytes(ENCODING));
	    String encryptedPassword = DatatypeConverter.printHexBinary(digest.digest());
	    System.out.println(encryptedPassword);
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Account> accountDAO = new AccountDAO(connection);
	    for (Account acc : accountDAO.findAll()) {
		if (acc.getLogin().equals(login)
			&& acc.getPassword().equals(encryptedPassword)) {
		    return acc;
		}
	    }
	} catch (NoSuchAlgorithmException e) {
	    throw new LoginLogicException("Trouble with encrypting algorithm",e);
	} catch (UnsupportedEncodingException e) {
	    throw new LoginLogicException("Trouble with password encoding",e);
	} catch (ConnectionPoolException e) {
	    throw new LoginLogicException("Trouble with connection pool",e);
	} catch (DAOException e) {
	    throw new LoginLogicException("Trouble with DAO",e);
	}
	finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	
	return null;
    }
    
    public static Administrator getAdmin(Account account) throws LoginLogicException {
	Connection connection = null;
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Administrator> adminDAO = new AdministratorDAO(connection);
	    
	    for (Administrator admin : adminDAO.findAll()) {
		if (account.getId() == admin.getId()) {
		    return admin;
		}
	    }
	} catch (ConnectionPoolException e) {
	    throw new LoginLogicException("Trouble with connection pool",e);
	} catch (DAOException e) {
	    throw new LoginLogicException("Trouble with administrators DAO",e);
	} 
	finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	return null;
    }
    
    public static Client getClient(Account account) throws LoginLogicException {
	Connection connection = null;
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Client> clientDAO = new ClientDAO(connection);
	    
	    for (Client client : clientDAO.findAll()) {
		if (account.getId() == client.getId()) {
		    return client;
		}
	    }
	} catch (ConnectionPoolException e) {
	    throw new LoginLogicException("Trouble with connection pool",e);
	} catch (DAOException e) {
	    throw new LoginLogicException("Trouble with clients DAO",e);
	} 
	finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	return null;
    }
    
    public static List<Car> getFreeCars() throws LoginLogicException {
	List<Car> freeCars = new ArrayList<Car>();
	Connection connection = null;
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    // ������������������ �������� ������������������ ���������� �� ��������������
	    AbstractDAO<Car> carDao  = new CarDAO(connection);
	    AbstractDAO<Order> orderDao = new OrderDAO(connection);
	    freeCars = carDao.findAll();
	    List<Order> orders = orderDao.findAll();
	    
	    /* �������� �������� ���������� �� �������������� �� ���� ������ ������ �� ����������������
	     * ������ ������������ ��������������, ���� ���������� ������������ ���������������������� 
	     * ���� ������������ ������������������ 
	     */
	    List<Car> nonFree = new ArrayList<Car>();
	    for (Order order : orders) {
		for (Car car : freeCars) {
		    if ((order.getIdCar() == car.getId()) 
			    && ((!order.isFree()) || order.isCrashed())) {
			nonFree.add(car);
		    }
		}
	    }
	    freeCars.removeAll(nonFree);
	    
	} catch (ConnectionPoolException e) {
	    throw new LoginLogicException("Can't take connection from connection pool.",e);
	} catch (DAOException e) {
	    throw new LoginLogicException("Can't find all cars/orders.",e);
	} finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	
	return freeCars;
    }

    public static List<Car> getRentedCars() {
	List<Car> rentedCars = new ArrayList<Car>();
	Connection connection = null;
	try {
	    connection = ConnectionPool.getInstance().takeConnection();
	    AbstractDAO<Car> carsDAO = new CarDAO(connection);
	    AbstractDAO<Order> orderDAO = new OrderDAO(connection);
	    for (Car car : carsDAO.findAll()) {
		for (Order order : orderDAO.findAll()) {
		    if (!order.isFree() && order.getIdCar() == car.getId()) {
			rentedCars.add(car);
		    }
		}
	    }
	} catch (ConnectionPoolException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (DAOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    ConnectionPool.getInstance().closeConnection(connection);
	}
	return rentedCars;
    }
}
