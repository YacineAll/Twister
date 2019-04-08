package com.twister.servlets.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.twister.services.Comment;

/**
 * Servlet implementation class FriendsComments
 */
@WebServlet("/FriendsComments")
public class FriendsComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String key = request.getParameter("key");
		JSONObject result = Comment.friendsComments(key);
		response.setContentType("text/JSON");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	
}
