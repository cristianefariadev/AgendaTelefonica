package br.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
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

import br.entidade.EContatos;
import br.negocio.BContatos;
import br.persistencia.PContatos;

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
	BContatos bContato = new BContatos();
	EContatos eContato = new EContatos();
	PContatos pContato = new PContatos();
	List<EContatos> lista;

	DefaultTableModel model = null;
	JDesktopPane desktop;
	private JTextField txtTelefone;

	private void pegaAlunoTela() {
		int linha = table.getSelectedRow();
		// System.out.println(model.getValueAt(linha, 0).toString());
		eContato.setId(((EContatos) lista.get(linha)).getId());
		eContato.setNome(model.getValueAt(linha, 0).toString());
		eContato.setTelefone(model.getValueAt(linha, 1).toString());

	}

	void limpar() {
		eContato = new EContatos();
	}

	public void limparTela() {
		txtNome.setText("");
		txtTelefone.setText("");

	}

	public void listar() {
		try {
			model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);

			lista = pContato.listar();

			for (int i = 0; i < lista.size(); i++) {
				Object[] rowData = new Object[3];
				EContatos c = (EContatos) lista.get(i);
				rowData[0] = c.getId();
				rowData[1] = c.getNome();
				rowData[2] = c.getTelefone();
				if (rowData != null) {
					model.addRow(rowData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public TelaPrincipal() {
		setTitle("Agenda Telefonica da Cris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 487);
		contentPaneConteiner = new JPanel();
		contentPaneConteiner.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneConteiner);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBotoes.setBounds(22, 0, 418, 51);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(
				new TitledBorder(null, "Pesquisar Contato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearch.setBounds(22, 168, 418, 270);

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
		txtNome.setEnabled(false);
		txtNome.setBounds(101, 27, 187, 20);
		panelInsert.add(txtNome);
		txtNome.setColumns(10);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.setEnabled(false);
		btnInserir.setBounds(320, 52, 75, 20);
		panelInsert.add(btnInserir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setEnabled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtTelefone.setText("");
			}
		});
		btnLimpar.setBounds(320, 27, 75, 20);
		panelInsert.add(btnLimpar);

		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setBounds(101, 55, 187, 20);
		panelInsert.add(txtTelefone);
		txtTelefone.setColumns(10);

		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// pega o o texto da tela
				eContato.setNome(txtNome.getText());
				eContato.setTelefone(txtTelefone.getText());

				if (bContato.validar(txtNome, txtTelefone) == true) {

					// objeto dao chama metodo salvar
					pContato.salvar(eContato);
					JOptionPane.showMessageDialog(null, "Usuario " + txtNome.getText() + " inserido com sucesso! ");
					limparTela();
					listar();
				}
			}
		});

		contentPaneConteiner.add(panelSearch);
		panelSearch.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 62, 394, 159);
		panelSearch.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome:", "Telefone:" }));
		scrollPane.setViewportView(table);

		JLabel lblConsultar = new JLabel("C\u00F3digo:");
		lblConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConsultar.setBounds(10, 33, 60, 14);
		panelSearch.add(lblConsultar);

		txtConsultar = new JTextField();
		txtConsultar.setEnabled(false);
		txtConsultar.setBounds(65, 31, 128, 20);
		panelSearch.add(txtConsultar);
		txtConsultar.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setEnabled(false);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					model = (DefaultTableModel) table.getModel();
					model.setNumRows(0);

					eContato = pContato.consultar(txtConsultar.getText());

					Object[] rowData = new Object[3];
					rowData[0] = eContato.getId();
					rowData[1] = eContato.getNome();
					rowData[2] = eContato.getTelefone();
					if (rowData != null) {
						model.addRow(rowData);
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro ao pesquisar");
				}
			}

		});
		btnConsultar.setBounds(203, 28, 95, 23);
		panelSearch.add(btnConsultar);

		JButton btnListarTudo = new JButton("Listar");
		btnListarTudo.setEnabled(false);
		btnListarTudo.setBounds(322, 28, 84, 23);
		panelSearch.add(btnListarTudo);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnImprimir.setBounds(317, 236, 89, 23);
		panelSearch.add(btnImprimir);
		btnListarTudo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				listar();
			}

		});
		contentPaneConteiner.add(panelBotoes);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setEnabled(true);
				txtTelefone.setEnabled(true);
				btnInserir.setEnabled(true);
				btnLimpar.setEnabled(true);
			}
		});
		btnNovo.setBounds(10, 17, 66, 23);
		panelBotoes.add(btnNovo);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					int resposta = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do contato?",
							"Agenda da Cris", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {

						pContato.excluir(Long.parseLong("" + table.getValueAt(table.getSelectedRow(), 0)));
						listar();

						JOptionPane.showMessageDialog(null, "Operação executada com sucesso!");
					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR");
				}

			}

		});
		btnExcluir.setBounds(261, 17, 76, 23);
		panelBotoes.add(btnExcluir);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(347, 17, 61, 23);
		panelBotoes.add(btnSair);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtConsultar.setEnabled(true);
				btnConsultar.setEnabled(true);
				btnListarTudo.setEnabled(true);

			}
		});
		btnPesquisar.setBounds(81, 17, 89, 23);
		panelBotoes.add(btnPesquisar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});
		btnEditar.setBounds(176, 17, 75, 23);
		panelBotoes.add(btnEditar);
	}
}
