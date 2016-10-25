package br.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.entidade.EUsuario;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 252);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(184, 92, 46, 14);
		panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(184, 128, 46, 14);
		panel.add(lblSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(240, 89, 125, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(240, 125, 125, 20);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(184, 172, 89, 23);
		panel.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			/*try{
				   EUsuario usuario = new EUsuario();

		            if (usuario.ValidarLogin(txtUsuario.getText(), txtSenha.getText())) {
		               // Principal1 principal1 = new Principal1(txtMail.getText());
		                //principal1.setVisible(true);
		                this.dispose();

		            }

		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(rootPane, e.getMessage());
		        }*/
		     
			
		});
		btnSalvar.setBounds(294, 172, 89, 23);
		panel.add(btnSalvar);
	}
}
