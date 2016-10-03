package br.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {

	private static Connection conexao = null;

	static Connection getConexao() {
		if (conexao != null) {
			return conexao;
		} else {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost/agenda";
				String user = "root";
				String password = "";

				Class.forName(driver);
				conexao = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conexao;
		}
	}

	public static void fecharConexao(Connection con, PreparedStatement ps) {
		fecharConexao(con, ps, null);
	}

	public static void fecharConexao(Connection con) {
		fecharConexao(con, null, null);
	}

	public static void fecharConexao(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			con.close();
		} catch (Exception e) {
		}
		try {
			ps.close();
		} catch (Exception e) {
		}
		try {
			rs.close();
		} catch (Exception e) {
		}
	}

}
