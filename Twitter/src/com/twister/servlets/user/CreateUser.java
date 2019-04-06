package com.twister.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twister.tools.JSONResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String birth_date = request.getParameter("birth_date");

		JSONResponse resp = (JSONResponse) com.twister.services.User.createUser(nom, prenom, login, password, sex,birth_date);

		response.setContentType("text/JSON");
		PrintWriter out = response.getWriter();
		out.print(resp);

//		if (resp.isAccepted()) {
//			this.getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
//		}else {
//			request.setAttribute("erreur", resp);
//			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
