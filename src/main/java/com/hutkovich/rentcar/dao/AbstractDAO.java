package com.hutkovich.rentcar.dao;

import com.hutkovich.rentcar.entity.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract boolean delete(T entity) throws DAOException;

    public abstract boolean create(T entity) throws DAOException;

    public abstract T update(T entity) throws DAOException;

    public void close(Statement st, ResultSet res) throws DAOException {
        try {
            if ((st != null) && (res != null)) {
                st.close();
                res.close();
            } else {
                //TODO
                throw new DAOException("Statement or Result set is null", new Exception());
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to close the statement or result set!", e);
        }
    }

    public void close(Statement st) throws DAOException {
        try {
            if (st != null) {
                st.close();
            } else {
                //TODO
                throw new DAOException("Statement is null", new Exception());
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to close the statement!", e);
        }
    }
}
