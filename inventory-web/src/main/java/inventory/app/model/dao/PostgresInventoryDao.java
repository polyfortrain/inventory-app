package inventory.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inventory.app.model.db.DbConnection;
import inventory.app.model.dto.Inventory;

/** JDBCを利用した在庫データアクセス。 */
public class PostgresInventoryDao implements InventoryDao {

    private static final String FIND_ALL_SQL =
            "SELECT id, name, quantity FROM inventory ORDER BY id";

    @Override
    public List<Inventory> findAll(){
        List<Inventory> result = new ArrayList<>();

        try (Connection conn = DbConnection.open();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rows = stmt.executeQuery()) {
            while (rows.next()) {
                result.add(new Inventory(
                        rows.getInt("id"),
                        rows.getString("name"),
                        rows.getInt("quantity")));
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
    }
}
