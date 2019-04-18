package com.twister.servlets.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class RemoveLike
 */
@WebServlet("/RemoveLike")
public class RemoveLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("idUser");
		String idComment = request.getParameter("idComment");

		
		JSONObject res = com.twister.services.Comment.removeLike(id, idComment);
		response.setContentType("text/JSON");
		response.getWriter().println(res);

	}

}

