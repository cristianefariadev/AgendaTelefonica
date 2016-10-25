package br.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {

	private static Connection conexao = null;

	public static Connection getConexao() {
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

	public static void fecharConexao(Connection conexao, PreparedStatement prd) {
		fecharConexao(conexao, prd, null);
	}

	public static void fecharConexao(Connection conexao) {
		fecharConexao(conexao, null, null);
	}

	public static void fecharConexao(Connection conexao, PreparedStatement prd, ResultSet rs) {
		try {
			conexao.close();
		} catch (Exception e) {
		}
		try {
			prd.close();
		} catch (Exception e) {
		}
		try {
			rs.close();
		} catch (Exception e) {
		}
	}

}
