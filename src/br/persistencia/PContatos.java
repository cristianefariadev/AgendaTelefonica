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

	public boolean salvar(EContatos contato) {
		conexao = ConexaoBD.getConexao();
		String sql;
		
		if (contato.getId() == 0L){
			sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";
		} else {
			
			sql = "update contatos set nome =?,  telefone =?  where id = " + contato.getId();
		}
		System.out.println("SQL == " + sql);
		try {
			prd = conexao.prepareStatement(sql);

			prd.setString(1, contato.getNome());
			prd.setString(2, contato.getTelefone());
			prd.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
/*	public void salvar(EContatos contato) {
		
		

		try {
			conexao = ConexaoBD.getConexao();
			String sql = "INSERT INTO contatos ( nome, telefone) VALUES (?, ?)";

			prd = conexao.prepareStatement(sql);

			prd.setString(1, contato.getNome());
			prd.setString(2, contato.getTelefone());

			prd.execute();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + erro.getMessage());
		}

	}

	public void alterar(EContatos eContato) throws SQLException {

		conexao = ConexaoBD.getConexao();
		try {

			String sql = "update contatos set nome =?,  telefone =?,  where id =? ";

			prd = conexao.prepareStatement(sql);

			prd.setString(1, eContato.getNome());
			prd.setString(2, eContato.getTelefone());
			prd.setLong(3, eContato.getId());

			prd.execute();
			
			
		} catch (SQLException erro) {
			erro.printStackTrace();
			erro.getMessage();
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + erro.getMessage());
		}

	}*/

	public List<EContatos> listar() throws SQLException {
		List<EContatos> lista = new ArrayList<EContatos>();
		try{
		
		conexao = ConexaoBD.getConexao();

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
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
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

		if (rs.next()) {
			EContatos contato = new EContatos();
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setTelefone(rs.getString("telefone"));
			return contato;
		}
		return null;
	}

}
