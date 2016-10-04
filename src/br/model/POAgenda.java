package br.model;

import br.model.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class POAgenda {

	public POAgenda() {

	}

	public void salvar(EAgenda contato) throws SQLException {
		
		// Cria a string com o sql para ser executado
		String sql = "insert into contatos ( nome, telefone) values (?, ?)";

		// Cria o objeto de conexão com o banco
		Connection conn =  ConexaoBD.getConexao();

		// Cria o objeto para executar os comandos "contra" o banco
		PreparedStatement prepStat = (PreparedStatement) conn.prepareStatement(sql);

		// Seta os valores recebidos como parametro para a string SQL
		prepStat.setString(1, contato.getNome());
		prepStat.setString(2, contato.getTelefone());

		// Executa o SQL no banco de dados
		prepStat.execute();
		
		conn.close();

	}

	
	public void alterar(EAgenda contato) throws SQLException {

		// Cria o objeto para a conexao
		Connection conn = ConexaoBD.getConexao();

		String sql = "UPDATE contato " + "SET nome = ?, " + "telefone = ?, " + " WHERE id = ?";

		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setString(1, contato.getNome());
		prd.setString(3, contato.getTelefone());

		prd.execute();
		conn.close();
	}

	
	public List<EAgenda> listar(String nome) throws SQLException {
		List<EAgenda> lista = new ArrayList<>(); // <<<<<<<<<<<<<<<<<<<<<<

		Connection conn = ConexaoBD.getConexao();

		String sql = "SELECT * FROM contatos WHERE 1=1 ";

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
			agenda.setId(rs.getInt("id"));
			agenda.setNome(rs.getString("nome"));
			agenda.setTelefone(rs.getString("telefone"));
			lista.add(agenda);
		}
		rs.close();
		conn.close();

		return lista;
	}

	
	public void excluir(int contato) throws SQLException {

		// Cria o objeto para a conexao
		java.sql.Connection conn = ConexaoBD.getConexao();

		String sql = "DELETE FROM contato WHERE id = ?";

		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setLong(1, contato);

		prd.execute();
		conn.close();
	}


	public EAgenda consultar(int contato) throws SQLException {

		Connection conn = ConexaoBD.getConexao();
		String sql = "SELECT id, nome, telefone, " 
				+ "FROM contato WHERE id = ?";
		PreparedStatement prd = conn.prepareStatement(sql);
		prd.setInt(1, contato);

		ResultSet rs = prd.executeQuery();
		EAgenda retorno = new EAgenda();
		if (rs.next()) {
			retorno.setId(rs.getInt("id"));
			retorno.setNome(rs.getString("nome"));
			retorno.setTelefone(rs.getString("telefone"));

		}
		rs.close();
		conn.close();

		return retorno;
	}

}
