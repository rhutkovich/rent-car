package com.epam.rentcar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.rentcar.entity.Account;

public class AccountDAO extends AbstractDAO<Account> {
    private static final String SELECT_ALL = "SELECT * FROM Accounts";
    private static final String SELECT_BY_ID = "SELECT * FROM Accounts WHERE ID_account = ";
    private static final String DELETE_BY_ID = "DELETE FROM Accounts WHERE ID_account = ";
    private static final String INSERT_ENTITY = "INSERT INTO Accounts "
    	+ "(Login,Password) VALUES ";
    private static final String UPDATE_ENTITY = "UPDATE Accounts SET ";
    
    public AccountDAO(Connection connection) {
	super(connection);
    }

    @Override
    public List<Account> findAll() throws DAOException {
	List<Account> accounts = new ArrayList<Account>();
	Statement st = null;
	ResultSet givenAccounts = null;
	
	try {
	    st = connection.createStatement();
	    givenAccounts = st.executeQuery(SELECT_ALL);
	    while (givenAccounts.next()) {
		Account account = new Account();
		account.setId(givenAccounts.getInt(1));
		account.setLogin(givenAccounts.getString(2));
		account.setPassword(givenAccounts.getString(3));
		
		accounts.add(account);
	    }
	} catch (SQLException e) {
	    throw new DAOException("It's problem to find all accounts.",e);
	} finally {
	    close(st,givenAccounts);
	}
	
	return accounts;
    }

    @Override
    public Account findEntityById(int id) throws DAOException {
	Account account = null;
	Statement st = null;
	ResultSet givenAccounts = null;
	
	try {
	    st = connection.createStatement();
	    givenAccounts = st.executeQuery(SELECT_BY_ID+id);
	    if (givenAccounts.next()) {
		account = new Account();
		account.setId(givenAccounts.getInt(1));
		account.setLogin(givenAccounts.getString(2));
		account.setPassword(givenAccounts.getString(3));
	    }
	} catch (SQLException e) {
	    throw new DAOException("It's problem to find account.",e);
	} finally {
	    close(st,givenAccounts);
	}
	
	return account;
    }

    @Override
    public boolean delete(int id) throws DAOException {
	Statement query = null;
	boolean result = false;
	
	try {
	    query = connection.createStatement();
	    result = (query.executeUpdate(DELETE_BY_ID+id) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Unable delete account by ID",e);
	} finally {
	   close(query); 
	}
	
	return result;
    }

    @Override
    public boolean delete(Account entity) throws DAOException {
	Account account = findEntityById(entity.getId());
	return (account.equals(entity))? delete(entity.getId()) : false;
    }

    @Override
    public boolean create(Account entity) throws DAOException {
	Statement statement = null;
	boolean result = false;
	
	try {
	    
	    statement = connection.createStatement();
	    result = (statement.executeUpdate(makeCreateQuery(entity)) > 0)? true : false;
	    
	} catch (SQLException e) {
	    throw new DAOException("Cant create account", e);
	} finally {
	    close(statement);
	}
	
	return result;
    }

    private String makeCreateQuery(Account entity) {
	StringBuilder query = new StringBuilder(INSERT_ENTITY);
	query.append("(\"").append(entity.getLogin()).append("\",").
	append("\"").append(entity.getPassword()).append("\")");
	return query.toString();
    }
    @Override
    public Account update(Account entity) throws DAOException {
	Statement statement = null;
	Account updatedAccount = null;
	
	try {
	    statement = connection.createStatement();
	    if (statement.executeUpdate(makeUpdateQuery(entity)) > 0) {
		updatedAccount = findEntityById(entity.getId());
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't update account",e);
	} finally {
	    close(statement);
	}
	
	return updatedAccount;
    }
    
    private String makeUpdateQuery(Account entity) {
	StringBuilder query = new StringBuilder(UPDATE_ENTITY);
	query.append("Login=\"").append(entity.getLogin()).append("\",").
	append("Password=\"").append(entity.getPassword()).
	append("\" WHERE ID_account=").append(entity.getId());
	
	return query.toString();
    }

}
