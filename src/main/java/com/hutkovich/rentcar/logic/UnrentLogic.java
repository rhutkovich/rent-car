package com.hutkovich.rentcar.logic;

import com.hutkovich.rentcar.dao.AbstractDAO;
import com.hutkovich.rentcar.dao.DAOException;
import com.hutkovich.rentcar.dao.OrderDAO;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPool;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPoolException;
import com.hutkovich.rentcar.entity.Order;
import com.hutkovich.rentcar.logic.exception.UnrentLogicException;

import java.sql.Connection;

public class UnrentLogic {
    public static boolean unrentCar(int carId) throws UnrentLogicException {
        boolean result = false;
        Connection connection = null;

        if (carId <= 0) {
            throw new UnrentLogicException("Negative or zero ID value");
        }

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            AbstractDAO<Order> orderDAO = new OrderDAO(connection);
            for (Order order : orderDAO.findAll()) {
                if (order.getIdCar() == carId) {
                    order.setFree(true);
                    orderDAO.update(order);
                }
            }
        } catch (ConnectionPoolException e) {
            throw new UnrentLogicException("Trouble with connection pool.", e);
        } catch (DAOException e) {
            throw new UnrentLogicException("Trouble with DAO", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }

        return result;
    }
}
