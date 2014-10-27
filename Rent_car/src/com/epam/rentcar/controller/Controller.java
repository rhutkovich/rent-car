package com.epam.rentcar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rentcar.command.ActionCommand;
import com.epam.rentcar.command.factory.ActionFactory;
import com.epam.rentcar.db.connectionpool.ConnectionPool;
import com.epam.rentcar.db.connectionpool.ConnectionPoolException;
import com.epam.rentcar.logic.exception.BusinessLogicException;
import com.epam.rentcar.resource.ConfigurationManager;
import com.epam.rentcar.resource.MessageManager;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionPool pool = null;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
	    super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	    //TODO
	    super.destroy();
	    pool.dispose();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	    String page = null;
	    // ���������������������� �������������� ���� JSP
	    ActionFactory client = new ActionFactory();
	    ActionCommand command = client.defineCommand(request);
	    /*
	     * ���������� ���������������������������� ������������ execute() �� ���������������� ��������������������
	     * ������������-���������������������� �������������������� ��������������
	     */
	    try {
		page = command.execute(request);
	    } catch (BusinessLogicException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    System.out.println(page);
	    // ���������� �������������������� ���������������� ������������
	    if (page != null) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		// ���������� ���������������� ������������ ���� ������������
		dispatcher.forward(request, response);
	    } else {
		// ������������������ ���������������� �� �������������������� ���� ������������
		page = ConfigurationManager.getProperty("path.page.index");
		request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
		response.sendRedirect(request.getContextPath()+page);
	    }
	}
}
