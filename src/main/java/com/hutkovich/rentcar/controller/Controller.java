package com.hutkovich.rentcar.controller;

import com.hutkovich.rentcar.command.ActionCommand;
import com.hutkovich.rentcar.command.factory.ActionFactory;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPool;
import com.hutkovich.rentcar.db.connectionpool.ConnectionPoolException;
import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;
import com.hutkovich.rentcar.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConnectionPool pool = null;

    public Controller() {
        super();
    }


    public void init(ServletConfig config) throws ServletException {
        //TODO
        super.init(config);
        pool = ConnectionPool.getInstance();
        try {
            pool.initPoolData();
        } catch (ConnectionPoolException e) {
            //TODO make a normal exception
            throw new ServletException(e);
        }
    }

    public void destroy() {
        //TODO
        super.destroy();
        pool.dispose();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        try {
            page = command.execute(request);
        } catch (BusinessLogicException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(page);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
