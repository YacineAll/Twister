package com.twister.servlets.friend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twister.services.Friend;

/**
 * Servlet implementation class ListerFriends
 */
@WebServlet("/ListerFriends")
public class ListerFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String key = request.getParameter("key");

		response.setContentType("text/JSON");
		PrintWriter out = response.getWriter();
		out.println(Friend.listeFriend(key));

	}

}
