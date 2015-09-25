package br.com.estatistica.visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;

import br.com.estatistica.dao.PesquisaDAO;
import br.com.estatistica.modelos.Pesquisa;
import br.com.estatistica.util.ConnectionFactory;
import br.com.estatistica.util.Mensagem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class FrmCadastroPesquisa extends GenericFormCadastro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private PesquisaDAO pesquisaDAO;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCadastroPesquisa frame = new FrmCadastroPesquisa(new ConnectionFactory().getConnection());
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
	public FrmCadastroPesquisa(Connection connection) {
		super("Cadastro de Pesquisa", connection);
		initComponents();
		setSize(600, 361);
	}
	private void initComponents() {
		setResizable(false);
		
				JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
				JLabel lblId = new JLabel("ID");
		lblId.setBounds(11, 11, 17, 14);
		panel.add(lblId);
		
				textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 27, 34, 20);
		panel.add(textField);
		textField.setColumns(10);
		
				JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(50, 11, 46, 14);
		panel.add(lblNome);
		
				textField_1 = new JTextField();
		textField_1.setBounds(50, 27, 354, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
				JLabel lblNewLabel = new JLabel("Limite de Especialistas");
		lblNewLabel.setBounds(11, 212, 187, 14);
		panel.add(lblNewLabel);
		
				textField_2 = new JTextField();
		textField_2.setBounds(11, 237, 46, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
				JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvarActionPerformed(e);
			}
		});
		
				btnSalvar.setBounds(11, 274, 89, 23);
		panel.add(btnSalvar);
		
				JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(110, 274, 89, 23);
		panel.add(btnCancelar);
		
				JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(11, 58, 64, 14);
		panel.add(lblDescrio);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(11, 83, 393, 118);
		panel.add(this.scrollPane);
		
		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	}

	protected void btnSalvarActionPerformed(ActionEvent e) {
		Integer valor = 0;
		try {
			valor = Integer.parseInt(textField_2.getText());
			System.out.println(valor);
			pesquisaDAO = new PesquisaDAO(super.getConnection());
			Pesquisa p1 = new Pesquisa(textField_1.getText(),textArea.getText(), valor);
			
			int valorCodigo = pesquisaDAO.save(p1); 
			
			textField.setText(String.valueOf(valorCodigo));

		} catch (NumberFormatException e1) {
			Mensagem.erro(this, e1);
		} catch (SQLException e1) {
			Mensagem.erro(this, e1);
		}

		Toolkit.getDefaultToolkit().beep();
	}
}
