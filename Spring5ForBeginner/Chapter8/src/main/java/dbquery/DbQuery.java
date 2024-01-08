package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.tomcat.jdbc.pool.DataSource;

public class DbQuery {
    private DataSource dataSource;

    public DbQuery(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); // 풀에서 구함
            // DataSource에서 커넥션을 구하는데 이때 풀에서 커넥션을 가져온다 (이 시점에서 커넥션 conn은 활성상태)
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null)
                try {
                    // 커넥션 종료 시 실제 커넥션을 끊지 않고 풀에 반환
                    // 풀에 반환된 커넥션은 다시 유휴 상태가 됨
                    conn.close(); // 풀에 반환
                } catch (SQLException e) {
                }
        }
    }
}
