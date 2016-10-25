package br.negocio;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.persistencia.PContatos;

public class BContatos {

	PContatos pContato;

	public BContatos() {
		pContato = new PContatos();
	}

	public boolean validar(JTextField txtNome, JTextField txtTelefone) {

		if ((txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null, "Os campos nao podem retornar vazios, tente novamente");
			return false;
		}
		return true;

	}
}
