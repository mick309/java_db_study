package text.section_04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTable_1 {
	public static void main(String[] args) {

		Connection con = null;
		Statement statement = null;

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/java_db",
					"root",
					"Can185537@");
			System.out.println("データベース接続成功");

			statement = con.createStatement();
			String sql = """
									CREATE TABLE users (

					id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,

					name VARCHAR(60) NOT NULL,

					age INT(11)
											);
							""";

			int rowCnt = statement.executeUpdate(sql);
			System.out.println("テーブルを作成:rowCunt=" + rowCnt);
		} catch (

		SQLException e) {
			System.out.println("エラー派生:" + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
