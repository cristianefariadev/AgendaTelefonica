package br.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;



import br.model.EAgenda;
import br.model.POAgenda;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneConteiner;
	private JTextField txtNome;
	private JTable table;
	private JTextField txtConsultar;
	
	   
	    
	  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("Agenda Telefonica da Cris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 449);
		contentPaneConteiner = new JPanel();
		contentPaneConteiner.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneConteiner);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBotoes.setBounds(22, 0, 418, 51);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(
				new TitledBorder(null, "Pesquisar Contato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearch.setBounds(22, 168, 418, 221);

		JPanel panelInsert = new JPanel();
		panelInsert.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inserir Contato",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInsert.setBounds(22, 62, 418, 95);
		panelBotoes.setLayout(null);
		contentPaneConteiner.setLayout(null);
		contentPaneConteiner.add(panelInsert);
		panelInsert.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(43, 30, 48, 14);
		panelInsert.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(30, 55, 61, 14);
		panelInsert.add(lblTelefone);

		txtNome = new JTextField();
		txtNome.setBounds(101, 27, 187, 20);
		panelInsert.add(txtNome);
		txtNome.setColumns(10);

		try {
			JFormattedTextField frmtdtxtfldTelefone = new JFormattedTextField(new MaskFormatter("(###) #####-####"));
			frmtdtxtfldTelefone.setBounds(101, 52, 187, 20);
			panelInsert.add(frmtdtxtfldTelefone);

			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(320, 52, 73, 20);
			panelInsert.add(btnSalvar);

			JButton btnLimpar = new JButton("Limpar");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtNome.setText("");
					frmtdtxtfldTelefone.setText("");
				}
			});
			btnLimpar.setBounds(320, 27, 75, 20);
			panelInsert.add(btnLimpar);

			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//instancia da entidade
					EAgenda contato = new EAgenda();
					
					//pega o o texto da tela 
					contato.setNome(txtNome.getText());
					contato.setTelefone(frmtdtxtfldTelefone.getText());
					
					//validando campos se esta vazio
			        if ((txtNome.getText().isEmpty()) || (frmtdtxtfldTelefone.getText().isEmpty())) {
			            JOptionPane.showMessageDialog(null, "Os campos nao podem retornar vazios");

			        }

			        else {
			        	//instancia - objeto da persistencia
			           POAgenda dao = new POAgenda();

			            try {
			            	
			            	//objeto dao chama metodo salvar
							dao.salvar(contato);
							JOptionPane.showMessageDialog(null, "Usuario "+txtNome.getText()+" inserido com sucesso! ");
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "deu merda");
							e1.printStackTrace();
						}
			        }
			        txtNome.setText("");
			        frmtdtxtfldTelefone.setText("");
					
				}
			});

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Essa porcaria deu problema");
		}

		contentPaneConteiner.add(panelSearch);
		panelSearch.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 394, 159);
		panelSearch.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome:", "Telefone:" }));
		scrollPane.setViewportView(table);

		JLabel lblConsultar = new JLabel("Consultar:");
		lblConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConsultar.setBounds(10, 33, 60, 14);
		panelSearch.add(lblConsultar);

		txtConsultar = new JTextField();
		txtConsultar.setBounds(65, 31, 128, 20);
		panelSearch.add(txtConsultar);
		txtConsultar.setColumns(10);

		JButton btnListarTudo = new JButton("Listar");
		btnListarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnListarTudo.setBounds(308, 30, 97, 23);
		panelSearch.add(btnListarTudo);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EAgenda cadastro = new EAgenda();

		       
		    }
		});
		btnPesquisar.setBounds(203, 30, 95, 23);
		panelSearch.add(btnPesquisar);
		contentPaneConteiner.add(panelBotoes);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(83, 17, 76, 23);
		panelBotoes.add(btnEditar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 17, 66, 23);
		panelBotoes.add(btnNovo);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {

			            int resposta = JOptionPane.showConfirmDialog(null,"Confirma a exclusão do contato?","Agenda da Cris"
			            		,JOptionPane.YES_NO_OPTION);
			            if (resposta == JOptionPane.YES_OPTION) {

			                int codigo = Integer.parseInt(txtNome.getText());
			                new POAgenda().excluir(codigo);
			                JOptionPane.showMessageDialog(null, "Operação executada com sucesso!");
			            }

			        } catch (Exception erro) {
			            JOptionPane.showMessageDialog(null, erro.getMessage());
			        }
			}
			
		});
		btnExcluir.setBounds(169, 17, 76, 23);
		panelBotoes.add(btnExcluir);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(255, 17, 82, 23);
		panelBotoes.add(btnImprimir);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(347, 17, 61, 23);
		panelBotoes.add(btnSair);
	}

}
