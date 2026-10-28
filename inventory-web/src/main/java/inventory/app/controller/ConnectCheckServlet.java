package inventory.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import inventory.app.model.db.DbConnection;

/**
 * Servlet implementation class ConnectCheckServlet
 */
@WebServlet("/con_check")
public class ConnectCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try(Connection con = DbConnection.open()){
			out.print("接続成功！");
		} catch (SQLException e) {
			out.print("接続失敗。" + e.getMessage());
			e.printStackTrace();
		}
	}
}
