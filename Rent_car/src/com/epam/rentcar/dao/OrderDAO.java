package com.epam.rentcar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.rentcar.entity.*;

public class OrderDAO extends AbstractDAO<Order> {
    private static final String SELECT_ALL = "SELECT * FROM Orders";
    private static final String SELECT_BY_ID = "SELECT * FROM Orders WHERE ID_order = ";
    private static final String DELETE_BY_ID = "DELETE FROM Orders WHERE ID_order = ";
    private static final String INSERT_ENTITY = "INSERT INTO Orders "
    	+ "(ID_car,ID_account,Term,Order_date,isFree,isCrashed) VALUES ";
    private static final String UPDATE_ENTITY = "UPDATE Orders SET ";
    
    public OrderDAO(Connection connection) {
	super(connection);
    }

    @Override
    public List<Order> findAll() throws DAOException {
	List<Order> orders = new ArrayList<Order>();
	Statement st = null;
	ResultSet givenOrders = null;
	
	try {
	    st = connection.createStatement();
	    givenOrders = st.executeQuery(SELECT_ALL);
	    while (givenOrders.next()) {
		Order order = new Order();
		order.setId(givenOrders.getInt(1));
		order.setIdCar(givenOrders.getInt(2));
		order.setIdAccount(givenOrders.getInt(3));
		order.setTerm(givenOrders.getDate(4));
		order.setOrderDate(givenOrders.getDate(5));
		order.setFree(givenOrders.getBoolean(6));
		order.setCrashed(givenOrders.getBoolean(7));
		
		orders.add(order);
	    }
	} catch (SQLException e) {
	    throw new DAOException("It's problem to find all Orders.",e);
	} finally {
	    close(st,givenOrders);
	}
	
	return orders;
    }

    @Override
    public Order findEntityById(int id) throws DAOException {
	Order order = null;
	Statement query = null;
	ResultSet givenOrder = null;
	
	try {
	    query = connection.createStatement();
	    givenOrder = query.executeQuery(SELECT_BY_ID+id);
	    if (givenOrder.next()) {
		order = new Order();
		order.setId(givenOrder.getInt(1));
		order.setIdCar(givenOrder.getInt(2));
		order.setIdAccount(givenOrder.getInt(3));
		order.setTerm(givenOrder.getDate(4));
		order.setOrderDate(givenOrder.getDate(5));
		order.setFree(givenOrder.getBoolean(6));
		order.setCrashed(givenOrder.getBoolean(7));
	    }
	} catch (SQLException e) {
	    throw new DAOException("It's problem to find Order by ID.",e);
	} finally {
	    close(query,givenOrder);
	}
	
	return order;
    }

    @Override
    public boolean delete(int id) throws DAOException {
	Statement query = null;
	boolean result = false;
	
	try {
	    query = connection.createStatement();
	    result = (query.executeUpdate(DELETE_BY_ID+id) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Unable delete order by ID",e);
	} finally {
	   close(query); 
	}
	
	return result;
    }

    @Override
    public boolean delete(Order entity) throws DAOException {
	Order orderFromDB = findEntityById(entity.getId());
	return (orderFromDB.equals(entity))? delete(entity.getId()) : false;
    }

    @Override
    public boolean create(Order entity) throws DAOException {
	Statement st = null;
	boolean result = false;
	
	try {
	    st = connection.createStatement();
	    result = (st.executeUpdate(makeCreateQuery(entity)) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Unable to create add new order to DB.",e);
	} finally {
	    close(st);
	}
	
	return result;
    }
    
    private String makeCreateQuery(Order entity) {
	StringBuilder queryBuilder = new StringBuilder(INSERT_ENTITY);
	queryBuilder.append("(").
		append(entity.getIdCar()).append(",").
	    	append(entity.getIdAccount()).append(",").
	    	append("\"").append(entity.getTerm()).append("\",").
	    	append("\"").append(entity.getOrderDate()).append("\",").
	    	append(entity.isFree()).append(",").
	    	append(entity.isCrashed()).append(")");
	
	return queryBuilder.toString();    
    }

    @Override
    public Order update(Order entity) throws DAOException {
	Statement st = null;
	Order updatedOrder = null;
	
	try {
	    st = connection.createStatement();
	    if (st.executeUpdate(makeUpdateQuery(entity)) > 0) {
		updatedOrder = findEntityById(entity.getId());
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't update Orders table",e);
	} finally {
	    close(st);
	}
	
	return updatedOrder;
    }
    
    private String makeUpdateQuery(Order entity) {
	StringBuilder statementBuilder = new StringBuilder(UPDATE_ENTITY);
	statementBuilder.append("ID_car=").append(entity.getIdCar()).append(",").
		append("ID_account=").append(entity.getIdAccount()).append(",").
	    	append("Term=\"").append(entity.getTerm()).append("\",").
	    	append("Order_date=\"").append(entity.getOrderDate()).append("\",").
	    	append("isFree=").append(entity.isFree()).append(",").
	    	append("isCrashed=").append(entity.isCrashed()).
	    	append(" WHERE ID_order=").append(entity.getId());
	
	return statementBuilder.toString();
    }
    
}
