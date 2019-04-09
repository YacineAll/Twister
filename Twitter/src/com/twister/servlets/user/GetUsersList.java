package com.twister.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twister.services.Search;

/**
 * Servlet implementation class GetUsersList
 */
@WebServlet("/GetUsersList")
public class GetUsersList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4244136333826093063L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/JSON");
		PrintWriter out = response.getWriter();
		out.print(Search.getUsers());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

