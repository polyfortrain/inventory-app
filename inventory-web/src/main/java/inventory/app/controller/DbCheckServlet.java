package inventory.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import inventory.app.model.dao.InventoryDao;
import inventory.app.model.dao.PostgresInventoryDao;
import inventory.app.model.dto.Inventory;

/**
 * Issue#33チェック用
 */
@WebServlet("/DbCheckServlet")
public class DbCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InventoryDao dao = new PostgresInventoryDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		List<Inventory> inventories = dao.findAll();
		
		for(Inventory inventory: inventories) {
			out.print(inventory.getId() + ": "
					+ inventory.getName() + "("
					+ inventory.getQuantity() + ")\n");
		}
		
	}
}
