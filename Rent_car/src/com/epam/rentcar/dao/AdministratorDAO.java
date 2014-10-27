package com.epam.rentcar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.rentcar.entity.Administrator;

public class AdministratorDAO extends AbstractDAO<Administrator> {
    private static final String SELECT_ALL = "SELECT * FROM Administrators";
    private static final String SELECT_BY_ID = "SELECT * FROM Administrators WHERE ID_account=";
    private static final String DELETE_BY_ID = "DELETE FROM Administrators WHERE ID_account= ";
    private static final String INSERT_ENTITY = "INSERT INTO Administrators (ID_account,Name) VALUES ";
    private static final String UPDATE_ENTITY = "UPDATE Administrators SET ";
    
    public AdministratorDAO(Connection connection) {
	super(connection);
    }

    @Override
    public List<Administrator> findAll() throws DAOException {
	List<Administrator> admins = new ArrayList<Administrator>();
	Statement statement = null;
	ResultSet resultSet = null;
	
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(SELECT_ALL);
	    
	    while(resultSet.next()) {
		Administrator administrator = new Administrator();
		administrator.setId(resultSet.getInt(1));
		administrator.setName(resultSet.getString(2));
		
		admins.add(administrator);
	    }
	} catch (SQLException e) {
	    throw new DAOException("Cannot select all administrators from DB",e);
	} finally {
	    close(statement,resultSet);
	}
	
	return admins;
    }

    @Override
    public Administrator findEntityById(int id) throws DAOException {
	Administrator administrator = null;
	Statement st = null;
	ResultSet rs = null;
	
	try {
	    st = connection.createStatement();
	    rs = st.executeQuery(SELECT_BY_ID+id);
	    
	    if(rs.next()) {
		administrator = new Administrator();
		administrator.setId(rs.getInt(1));
		administrator.setName(rs.getString(2));
	    }
	} catch (SQLException e) {
	    throw new DAOException("Cannot select administrator by ID.",e);
	} finally {
	    close(st,rs);
	}
	
	return administrator;
    }

    @Override
    public boolean delete(int id) throws DAOException {
	Statement st = null;
	boolean result = false;
	
	try {
	    st = connection.createStatement();
	    result = (st.executeUpdate(DELETE_BY_ID+id) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Can't delete administrator by ID",e);
	} finally {
	    close(st);
	}
	
	return result;
    }

    @Override
    public boolean delete(Administrator entity) throws DAOException {
	Administrator admin = findEntityById(entity.getId());
	return (admin.equals(entity))? delete(entity.getId()) : false;
    }

    @Override
    public boolean create(Administrator entity) throws DAOException {
	Statement st = null;
	boolean result = false;
	
	try {
	    st = connection.createStatement();
	    result = (st.executeUpdate(makeCreateQuery(entity)) > 0)? true : false;
	} catch (SQLException e) {
	    throw new DAOException("Can't create administrator.",e);
	} finally {
	    close(st);
	}
	return result;
    }
    
    private String makeCreateQuery(Administrator entity) {
	StringBuilder query = new StringBuilder(INSERT_ENTITY);
	query.append("(").append(entity.getId()).append(",\"").
	append(entity.getName()).append("\")");
	
	return query.toString();
    }

    @Override
    public Administrator update(Administrator entity) throws DAOException {
	Administrator admin = null;
	Statement st = null;
	
	try {
	    st = connection.createStatement();
	    
	    if (st.executeUpdate(makeUpdateQuery(entity)) > 0) {
		admin = findEntityById(entity.getId());
	    }
	} catch (SQLException e) {
	    throw new DAOException("Can't update administrator.",e);
	} finally {
	    close(st);
	}
	
	return admin;
    }
    
    private String makeUpdateQuery(Administrator entity) {
	StringBuilder query = new StringBuilder(UPDATE_ENTITY);
	query.append("Name=\"").append(entity.getName()).append("\"").
	append(" WHERE ID_account=").append(entity.getId());
	
	return query.toString();
    }
}
