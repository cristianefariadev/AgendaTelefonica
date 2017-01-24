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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneConteiner;
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
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;

	public void limparTela() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		eContato = new EContatos();//vai lá vou ver aqui ja ajudou muito obrigada
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
		setResizable(false);

		setTitle("Agenda Telefonica da Cris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 501);
		contentPaneConteiner = new JPanel();
		contentPaneConteiner.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneConteiner);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Contatos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(10, 180, 415, 277);

		JPanel panelInsert = new JPanel();
		panelInsert.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adicionar Contato",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInsert.setBounds(10, 11, 415, 158);
		contentPaneConteiner.setLayout(null);
		contentPaneConteiner.add(panelInsert);
		panelInsert.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(21, 67, 167, 14);
		panelInsert.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(227, 67, 61, 14);
		panelInsert.add(lblTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/tick.png")));
		btnSalvar.setBounds(303, 115, 96, 25);
		panelInsert.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/cross.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtTelefone.setText("");
			}
		});
		btnLimpar.setBounds(186, 115, 102, 25);
		panelInsert.add(btnLimpar);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(21, 22, 55, 14);
		panelInsert.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(21, 36, 86, 20);
		panelInsert.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(21, 83, 167, 20);
		panelInsert.add(txtNome);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(227, 83, 172, 20);
		panelInsert.add(txtTelefone);
		txtTelefone.setColumns(10);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eContato.setId(Long.parseLong(txtCodigo.getText()));
				eContato.setNome(txtNome.getText());
				eContato.setTelefone(txtTelefone.getText());

				if (bContato.validar(txtNome, txtTelefone) == true) {

					try {
						new PContatos().salvar(eContato);
						limparTela();
						listar();

					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "Botão salvar não funcionou");
						erro.printStackTrace();
					}
				}
			}
		});

		contentPaneConteiner.add(panelSearch);
		panelSearch.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtTelefone.setText(table.getValueAt(table.getSelectedRow(), 2).toString());

			}
		});
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 53, 395, 178);
		panelSearch.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código:", "Nome:", "Telefone:" }));
		scrollPane.setViewportView(table);

		txtConsultar = new JTextField();
		txtConsultar.setBounds(69, 22, 133, 20);
		panelSearch.add(txtConsultar);
		txtConsultar.setColumns(10);

		JButton btnConsultar = new JButton("");
		btnConsultar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/zoom.png")));
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
		btnConsultar.setBounds(211, 22, 32, 20);
		panelSearch.add(btnConsultar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/pencil.png")));
		btnEditar.setBounds(203, 242, 93, 23);
		panelSearch.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/delete.png")));
		btnExcluir.setBounds(306, 242, 99, 23);
		panelSearch.add(btnExcluir);

		JLabel lblConsultar = new JLabel("Consultar:");
		lblConsultar.setBounds(10, 25, 63, 14);
		panelSearch.add(lblConsultar);

		JButton btnListarTudo = new JButton("Lista de Clientes");
		btnListarTudo.setBounds(253, 21, 152, 23);
		panelSearch.add(btnListarTudo);
		btnListarTudo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/page_white_text.png")));
		
				JButton btnImprimir = new JButton("Imprimir");
				btnImprimir.setBounds(76, 242, 116, 23);
				panelSearch.add(btnImprimir);
				btnImprimir.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/image/icons/printer.png")));
		btnListarTudo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				listar();
			}

		});
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
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtTelefone.setText(table.getValueAt(table.getSelectedRow(), 2).toString());

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro ao selecionar");
					erro.printStackTrace();
				}

			}
		});

	}
}
