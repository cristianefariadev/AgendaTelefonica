package br.model;

import br.model.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PersistenciaAgenda implements InterfaceAgenda {

	public PersistenciaAgenda() {

	}

	@Override
	public void salvar(EAgenda parmAgenda) throws SQLException {
		// Cria a string com o sql para ser executado
		String sql = "INSERT INTO contato ( nome, endereco) VALUES (?, ?)";

		// Cria o objeto de conexão com o banco
		Connection conn =  ConexaoBD.getConexao();

		// Cria o objeto para executar os comandos "contra" o banco
		PreparedStatement prepStat = (PreparedStatement) conn.prepareStatement(sql);

		// Seta os valores recebidos como parametro para a string SQL
		prepStat.setString(1, parmAgenda.getNome());
		prepStat.setString(2, parmAgenda.getTelefone());

		// Executa o SQL no banco de dados
		prepStat.execute();

		// Cria o sql para recuperar o codigo gerado
		String sql2 = "SELECT currval('associado_codigo_seq') as codigo";

		// Crio o statement a partir da conexao
		Statement st =  conn.createStatement();

		// Crio o resultset a partir do sql
		ResultSet rs = st.executeQuery(sql2);

		if (rs.next()) {
			parmAgenda.setId(rs.getLong("Id"));
		}

		rs.close();
		conn.close();

	}

	@Override
	public void alterar(EAgenda parmAgenda) throws SQLException {

		// Cria o objeto para a conexao
		Connection conn = ConexaoBD.getConexao();

		String sql = "UPDATE agenda " + "SET nome = ?, " + "telefone = ?, " + " WHERE id = ?";

		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setString(1, parmAgenda.getNome());
		prd.setString(3, parmAgenda.getTelefone());

		prd.execute();
		conn.close();
	}

	@Override
	public List<EAgenda> listar(String nome) throws SQLException {
		List<EAgenda> lista = new ArrayList<>(); // <<<<<<<<<<<<<<<<<<<<<<

		Connection conn = ConexaoBD.getConexao();

		String sql = "SELECT * " + "FROM agenda " + "WHERE 1=1 ";

		if (nome != null) {
			if (!nome.isEmpty()) {
				sql += " and nome like ? ";
			}
		}

		sql += "ORDER BY nome";

		PreparedStatement prd = conn.prepareStatement(sql);

		if (nome != null) {
			if (!nome.isEmpty()) {
				prd.setString(1, "%" + nome + "%");
			}
		}

		ResultSet rs = prd.executeQuery();

		while (rs.next()) {
			EAgenda agenda = new EAgenda();
			agenda.setId(rs.getLong("id"));
			agenda.setNome(rs.getString("nome"));
			agenda.setTelefone(rs.getString("telefone"));
			lista.add(agenda);
		}
		rs.close();
		conn.close();

		return lista;
	}

	@Override
	public void excluir(int parmAgenda) throws SQLException {

		// Cria o objeto para a conexao
		java.sql.Connection conn = ConexaoBD.getConexao();

		String sql = "DELETE FROM agenda WHERE id = ?";

		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setLong(1, parmAgenda);

		prd.execute();
		conn.close();
	}

	@Override
	public EAgenda consultar(int parmAgenda) throws SQLException {

		Connection conn = ConexaoBD.getConexao();
		String sql = "SELECT id, nome, telefone, " 
				+ "FROM agenda WHERE id = ?";
		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setInt(1, parmAgenda);

		ResultSet rs = prd.executeQuery();
		EAgenda retorno = new EAgenda();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome"));
			retorno.setTelefone(rs.getString("telefone"));

		}
		rs.close();
		conn.close();

		return retorno;
	}

}
