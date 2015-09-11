package br.com.estatistica.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.estatistica.dao.UsuarioDAO;
import br.com.estatistica.modelos.Usuario;
import br.com.estatistica.util.ConnectionFactory;

public class FrmLoginUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLoginUsuario frame = new FrmLoginUsuario();
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
	public FrmLoginUsuario() {
		setTitle("Autenticação de usuário");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("֍");
		label.setBounds(101, 11, 46, 54);
		label.setForeground(new Color(220, 220, 220));
		label.setFont(new Font("Arial", Font.PLAIN, 46));
		panel.add(label);

		JLabel label_1 = new JLabel("Delfos");
		label_1.setBounds(152, 39, 56, 26);
		label_1.setForeground(new Color(220, 220, 220));
		label_1.setFont(new Font("Calibri Light", Font.BOLD, 20));
		panel.add(label_1);

		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setForeground(new Color(220, 220, 220));
		lblUsurio.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblUsurio.setBounds(22, 117, 79, 26);
		panel.add(lblUsurio);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		txtUsuario.setBounds(111, 121, 180, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(220, 220, 220));
		lblSenha.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblSenha.setBounds(34, 160, 67, 26);
		panel.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		txtSenha.setBounds(111, 164, 180, 20);
		panel.add(txtSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(btnEntrarActionPerformed());
		btnEntrar.setFont(new Font("Calibri Light", Font.BOLD, 18));
		btnEntrar.setBounds(22, 221, 89, 39);
		panel.add(btnEntrar);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Calibri Light", Font.BOLD, 18));
		btnSair.setBounds(202, 221, 89, 39);
		panel.add(btnSair);
	}

	protected ActionListener btnEntrarActionPerformed() {
		return new ActionListener() {
			private UsuarioDAO uDao;

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (validaCampos()) {
					Connection connection = new ConnectionFactory().getConnection();
					uDao = new UsuarioDAO(connection);
					try {
						if (uDao.autentica(txtUsuario.getText(), txtSenha.getText())) {
							// chamaMenuPrincipal(uDao);
							JOptionPane.showMessageDialog(getParent(), "Validado!");
						} else {
							JOptionPane.showMessageDialog(getParent(), "Usuário ou senha incorretos.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(getParent(), "Algo aconteceu.\nDetalhes: " + e.getMessage());
					}
				}

			}

			protected void chamaMenuPrincipal(UsuarioDAO uDao) throws SQLException {
				Usuario user = uDao.get(txtUsuario.getText());
				ConnectionFactory.setUsuarioConectado(user);
				FrmMenuPrincipal menuPrincipal = new FrmMenuPrincipal();
				menuPrincipal.configPermissoes(user);
				menuPrincipal.setVisible(true);
			}
		};
	}

	@SuppressWarnings("deprecation")
	protected boolean validaCampos() {
		// TODO Auto-generated method stub
		return (!txtUsuario.getText().isEmpty() && txtSenha.getText().isEmpty());
	}
}