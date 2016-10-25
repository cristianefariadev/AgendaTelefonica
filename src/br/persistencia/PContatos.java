package br.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.entidade.EContatos;
import br.util.ConexaoBD;

public class PContatos {

	private Connection conexao = null;
	private PreparedStatement prd = null;
	private ResultSet rs = null;

	public PContatos() {
		conexao = (Connection) ConexaoBD.getConexao();
	}

	public void salvar(EContatos contato) {

		conexao = ConexaoBD.getConexao();

		try {
			String sql = "INSERT INTO contatos ( nome, telefone) VALUES (?, ?)";

			prd = conexao.prepareStatement(sql);

			prd.setString(1, contato.getNome());
			prd.setString(2, contato.getTelefone());

			prd.execute();
			// conexao.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + erro.getMessage());
		}

	}

	public void alterar(EContatos eContato) throws SQLException {

		conexao = ConexaoBD.getConexao();

		String sql = "update contatos set nome =?,  telefone =?,  where id =? ";

		prd = conexao.prepareStatement(sql);

		prd.setString(1, eContato.getNome());
		prd.setString(2, eContato.getTelefone());

		//return prd.execute();

	}

	public List<EContatos> listar() throws SQLException {

		conexao = ConexaoBD.getConexao();
		
		List<EContatos> lista = new ArrayList<EContatos>();
		
		String sql = "select * from contatos";
		
		prd = conexao.prepareStatement(sql);
		
		rs = prd.executeQuery();

		while (rs.next()) {
			EContatos eContato = new EContatos();
			
			eContato.setId(rs.getLong("id"));
			eContato.setNome(rs.getString("nome"));
			eContato.setTelefone(rs.getString("telefone"));

			lista.add(eContato);
		}
		return lista;
	}

	public void excluir(long id) {

		conexao = ConexaoBD.getConexao();

		try {
			String sql = "DELETE FROM contatos WHERE id = ?";

			prd = conexao.prepareStatement(sql);
			prd.setLong(1, (id));
			prd.execute();
			// conexao.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + e.getMessage());
		}

	}

	public EContatos consultar(String id) throws SQLException {

		conexao = ConexaoBD.getConexao();

		String sql = "SELECT * FROM CONTATOS WHERE id = ?";

		prd = conexao.prepareStatement(sql);

		prd.setString(1, id);

		rs = prd.executeQuery();

		while (rs.next()) {
			EContatos contato = new EContatos();
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setTelefone(rs.getString("telefone"));
			return contato;
		}
		return null;
	}

}
