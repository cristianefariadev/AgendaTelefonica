package br.controller;

import java.sql.SQLException;
import java.util.List;

import br.model.EAgenda;


public class ControllerAgenda {
	
	EAgenda objetoEAgenda = new EAgenda();


	
	public void salvar(EAgenda contato) throws SQLException {
		
	}

	
	public void alterar(EAgenda contato) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public List<EAgenda> listar(String nome) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public void excluir(int contato) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public EAgenda consultar(int contato) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
/*	Aluno alunoBO = new AlunoBO();

    @Override
    public boolean salvar(Aluno aluno) {
        return alunoBO.salvar(aluno);
    }

    @Override
    public List<Entidade> listar() {
       return alunoBO.listar();
    }

    @Override
    public Aluno consultar(Aluno aluno) {
        return alunoBO.consultar(aluno);
    }

    @Override
    public List<Aluno> pesquisar(Aluno aluno) {
        return alunoBO.pesquisar(aluno);
    }

    @Override
    public boolean excluir(Entidade entidade) {
        return alunoBO.excluir(entidade);
    }*/
}
	

