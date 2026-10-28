package inventory.app.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** PostgreSQLへの接続。パスワードはGitに保存せず、環境変数から取得する。 */
public final class DbConnection {

    private DbConnection() {
    }

    public static Connection open() throws SQLException {
        String url = System.getenv("INVENTORY_DB_URL");
        String user = System.getenv("INVENTORY_DB_USER");
        String password = System.getenv("INVENTORY_DB_PASSWORD");

        if (url == null || url.isBlank()
                || user == null || user.isBlank()
                || password == null) {
            throw new SQLException("INVENTORY_DB_URL / INVENTORY_DB_USER / "
                    + "INVENTORY_DB_PASSWORD を設定してください。");
        }
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "PostgreSQL JDBCドライバーが見つかりません", e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
