package com.twister.servlets.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class CommentsAuthor
 */
@WebServlet("/CommentsAuthor")
public class CommentsAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id_author = request.getParameter("idAuthor");
	
		JSONObject res = com.twister.services.Search.userComments(id_author);
		response.setContentType("text/JSON");
		response.getWriter().print(res);

	}

}
