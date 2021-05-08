package com.hutkovich.rentcar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hutkovich.rentcar.entity.Client;

public class ClientDAO extends AbstractDAO<Client> {
    private static final String SELECT_ALL = "SELECT * FROM Clients";
    private static final String SELECT_BY_ID = "SELECT * FROM Clients WHERE ID_account=";
    private static final String DELETE_BY_ID = "DELETE FROM Clients WHERE ID_account = ";
    private static final String INSERT_ENTITY = "INSERT INTO Clients "
	    +"(ID_account,Name,Passport_num,Passport_date,Passport_depart) VALUES ";
    private static final String UPDATE_ENTITY = "UPDATE Clients SET ";
    
    public ClientDAO(Connection connection) {
	super(connection);
    }

    @Override
    public List<Client> findAll() throws DAOException {
	List<Client> clients = new ArrayList<Client>();
	Statement statement = null;
	ResultSet resultSet = null;
	
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(SELECT_ALL);
	    
	    while (resultSet.next()) {
		Client client = new Client();
		client.setId(resultSet.getInt(1));
		client.setName(resultSet.getString(2));
		client.setPassportNumber(resultSet.getString(3));
		client.setPassportDate(resultSet.getDate(4));
		client.setPassportDepart(resultSet.getString(5));
		
		clients.add(client);
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't find all clients", e);
	} finally {
	    close(statement,resultSet);
	}
	
	return clients;
    }

    @Override
    public Client findEntityById(int id) throws DAOException {
	Client client = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(SELECT_BY_ID+id);
	    
	    if (resultSet.next()) {
		client = new Client();
		client.setId(resultSet.getInt(1));
		client.setName(resultSet.getString(2));
		client.setPassportNumber(resultSet.getString(3));
		client.setPassportDate(resultSet.getDate(4));
		client.setPassportDepart(resultSet.getString(5));
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't find all clients", e);
	} finally {
	    close(statement,resultSet);
	}
	
	return client;
    }

    @Override
    public boolean delete(int id) throws DAOException {
	Statement statement = null;
	boolean result = false;
	
	try {
	    statement = connection.createStatement();
	    result = (statement.executeUpdate(DELETE_BY_ID+id) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Can't delete client.", e);
	} finally {
	    close(statement);
	}
	
	return result;
    }

    @Override
    public boolean delete(Client entity) throws DAOException {
	Client client = findEntityById(entity.getId());
	return (client.equals(entity))? delete(entity.getId()) : false;
    }

    @Override
    public boolean create(Client entity) throws DAOException {
	Statement statement = null;
	boolean result = false;
	
	try {
	    statement = connection.createStatement();
	    result = (statement.executeUpdate(makeCreateQuery(entity)) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Can't create client", e);
	} finally {
	    close(statement);
	}
	
	return result;
    }
    
    private String makeCreateQuery(Client entity) {
	StringBuilder query = new StringBuilder(INSERT_ENTITY);
	query.append("(").append(entity.getId()).append(",").
	append("\"").append(entity.getName()).append("\",").
	append("\"").append(entity.getPassportNumber()).append("\",").
	append("\"").append(entity.getPassportDate()).append("\",").
	append("\"").append(entity.getPassportDepart()).append("\")");
	
	return query.toString();
    }

    @Override
    public Client update(Client entity) throws DAOException {
	Statement statement = null;
	Client updatedClient = null;
	
	try {
	    statement = connection.createStatement();
	    if (statement.executeUpdate(makeUpdateQuery(entity)) > 0) {
		updatedClient = findEntityById(entity.getId());
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't create client", e);
	} finally {
	    close(statement);
	}
	
	return updatedClient;
    }
    
    private String makeUpdateQuery(Client entity) {
	StringBuilder query = new StringBuilder(UPDATE_ENTITY);
	query.append("Name=\"").append(entity.getName()).append("\",").
	append("Passport_num=\"").append(entity.getPassportNumber()).append("\",").
	append("Passport_date=\"").append(entity.getPassportDate()).append("\",").
	append("Passport_depart=\"").append(entity.getPassportDepart()).
	append("\" WHERE ID_account=").append(entity.getId());
	
	return query.toString();
    }

}
