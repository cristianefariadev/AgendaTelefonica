package br.model;


	import java.sql.Connection;// biblioteca de conexao
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Dao{

	    private Connection conectar = null;

	    public Dao() {
	        conectar = ConexaoBD.getConexao();
	    }

	   
	    public void salvar (EAgenda objeto) throws SQLException{

	        String comando = "insert into contatos(nome, telefone)values(?, ?)";
	        PreparedStatement ps = conectar.prepareStatement(comando);
	      
	        ps.setString(1, objeto.getNome());
	        ps.setString(2, objeto.getTelefone());
	        ps.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Contato inserido com sucesso!");

	    }

	    public ArrayList<EAgenda> listar(String nome) throws SQLException {
	        ArrayList<EAgenda> dados = new ArrayList();
	        String comando = "select * from contatos";
	        PreparedStatement ps = conectar.prepareStatement(comando);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	EAgenda contato = new EAgenda();
	        	contato.setNome(rs.getString("nome"));;
	        	contato.setTelefone(rs.getString("telefone"));
	            dados.add(contato);
	        }
	        return dados;
	    }

	   
	    public void excluir(EAgenda objeto) throws SQLException {
	        String comando = "delete from contatos where id =? ";
	        PreparedStatement ps = conectar.prepareStatement(comando);
	        ps.setString(1, (""+ objeto));
	        ps.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Contato foi excluído com sucesso!");
	    }

   
	    public EAgenda pesquisar(int objeto) throws SQLException {
	    	
	    	Connection conn = ConexaoBD.getConexao();
			String sql = "SELECT id, nome, telefone, " 
					+ "FROM contato WHERE id = ?";
			PreparedStatement prd = conn.prepareStatement(sql);
			prd.setInt(1, objeto);

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

	  
	    public void alterar(EAgenda objeto) throws SQLException {
	        String comando = "update Contatos set nome =?, endereco =?, telefone =?, email =? where cpf =? ";
	        PreparedStatement ps = conectar.prepareStatement(comando);

	        ps.setString(1, objeto.getNome());
	        ps.setString(2, objeto.getTelefone());
	        ps.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Contato foi editado com sucesso!");
	    }


}
