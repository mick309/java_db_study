package text.section_05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInsert_1 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement statement = null;

		String[][] userList = {
				{ "侍一郎", "28" },
				{ "侍花子", "24" },
				{ "侍二郎", "26" },
				{ "侍寺子", "37" },
				{ "侍三郎", "21" },
		};

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/java_db",
					"root",
					"Can185537@");

			System.out.println("データベース接続成功");

			String sql = "INSERT INTO users (name, age) VALUES (?, ?);";
			statement = con.prepareStatement(sql);

			int rowCnt = 0;
			for (int i = 0; i < userList.length; i++) {

				statement.setString(1, userList[i][0]);
				statement.setString(2, userList[i][1]);
				
				System.out.println("レコード追加:" + statement.toString() );
				rowCnt = statement.executeUpdate();
				System.out.println( rowCnt + "件のレコードが追加されました");
			}
		} catch (SQLException e) {
			System.out.println("エラー発生:" + e.getMessage());
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
