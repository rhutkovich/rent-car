package com.hutkovich.rentcar.logic;

import com.hutkovich.rentcar.dao.AbstractDAO;
import com.hutkovich.rentcar.dao.CarDAO;
import com.hutkovich.rentcar.dao.DAOException;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPool;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPoolException;
import com.hutkovich.rentcar.entity.Car;
import com.hutkovich.rentcar.logic.exception.RentLogicException;

import java.sql.Connection;

public class RentLogic {

    public static Car getRentedCar(int id) throws RentLogicException {
        Connection connection = null;
        Car rentedCar = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            AbstractDAO<Car> carDAO = new CarDAO(connection);
            rentedCar = carDAO.findEntityById(id);

        } catch (ConnectionPoolException e) {
            throw new RentLogicException("Can't take connection from connection pool", e);
        } catch (DAOException e) {
            throw new RentLogicException("Troubles with searching car by ID", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }

        return rentedCar;
    }
}
