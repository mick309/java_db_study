package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Scores_Chapter10 {
	public static void main(String[] args) {
		Connection con = null;
		Statement statement = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/challenge_java", // ここに正確なデータベース名を反映
					"root",
					"Can185537@");

			System.out.println("データベース接続成功");

			// SQLクエリを準備
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = '95',score_english = '80' WHERE id = 5;";

			try {

				Scanner scanner = new Scanner(System.in);

				// SQLクエリを実行（DBMSに送信）
				System.out.println("レコード更新:" + statement.toString());
				int rowCnt = statement.executeUpdate(sql);
				System.out.println(rowCnt + "件のレコードが更新されました");

				// SQLクエリを準備（並べ替え）
				System.out.println("数学・英語の点数が高い順に並べ替えます");
				String sqlSelect = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
				ResultSet result = statement.executeQuery(sqlSelect);

				// 結果の取得と表示
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					int scoreMath = result.getInt("score_math");
					int scoreEnglish = result.getInt("score_english");
					System.out.println(result.getRow() + "件目：id=" + id
							+ "／name=" + name + "／math=" + scoreMath + "／english=" + scoreEnglish);
				}
			} catch (SQLException e) {
				System.out.println("エラー発生：" + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("データベース接続エラー：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
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