package br.model;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceAgenda {

	// create
	public void salvar(EAgenda parmAgenda) throws SQLException;

	// update
	public void alterar(EAgenda parmAgenda) throws SQLException;

	// read all
	public List<EAgenda> listar(String nome) throws SQLException;

	// delete
	public void excluir(int parametro) throws SQLException;

	// select
	public EAgenda consultar(int parmAgenda) throws SQLException;

}
