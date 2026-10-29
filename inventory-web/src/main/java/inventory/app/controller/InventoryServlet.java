package inventory.app.controller;

import java.io.IOException;
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
 * Issue #34 (FR-01): 在庫一覧を取得しJSPにフォワード
 */
@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InventoryDao dao = new PostgresInventoryDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 在庫一覧を取得
		List<Inventory> inventories = dao.findAll();

		// リクエストスコープに在庫一覧を格納
		request.setAttribute("inventories", inventories);

		// views配下のJSPにフォワード
		request.getRequestDispatcher("/WEB-INF/views/inventory-list.jsp")
				.forward(request, response);

	}
}
