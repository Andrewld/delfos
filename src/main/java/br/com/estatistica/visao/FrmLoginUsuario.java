package br.com.estatistica.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.estatistica.dao.UsuarioDAO;
import br.com.estatistica.modelos.Usuario;
import br.com.estatistica.util.ConnectionFactory;
import br.com.estatistica.util.GuiUtils;
import br.com.estatistica.util.Mensagem;

public class FrmLoginUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	private static final UsuarioDAO uDao;
	private static final Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrmLoginUsuario frame = new FrmLoginUsuario();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	static {
		connection = new ConnectionFactory().getConnection();
		uDao = new UsuarioDAO(connection);
	}

	/**
	 * Create the frame.
	 */
	public FrmLoginUsuario() {
		this.initComponents();

	}

	protected void initComponents() {
		this.setTitle("Autenticação de usuário");

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		this.contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FrmLoginUsuario.class.getResource("/br/com/estatistica/util/icons/logo/Logo-vers-1(16-09)min3.png")));
		label_1.setBounds(145, 31, 193, 101);
		label_1.setForeground(new Color(220, 220, 220));
		label_1.setFont(new Font("Calibri Light", Font.BOLD, 30));
		panel.add(label_1);

		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setForeground(new Color(220, 220, 220));
		lblUsurio.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblUsurio.setBounds(204, 143, 118, 26);
		panel.add(lblUsurio);

		this.txtUsuario = new JTextField();
		this.txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				FrmLoginUsuario.this.txtUsuarioKeyPressed(e);
			}
		});
		this.txtUsuario.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		this.txtUsuario.setBounds(101, 168, 282, 28);
		panel.add(this.txtUsuario);
		this.txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(220, 220, 220));
		lblSenha.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblSenha.setBounds(215, 207, 97, 20);
		panel.add(lblSenha);

		this.txtSenha = new JPasswordField();
		this.txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				FrmLoginUsuario.this.txtSenhaKeyPressed(e);
			}
		});
		this.txtSenha.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		this.txtSenha.setBounds(101, 228, 282, 28);
		panel.add(this.txtSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(this.btnEntrarActionPerformed());
		btnEntrar.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnEntrar.setBounds(265, 284, 118, 31);
		panel.add(btnEntrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this.btnCancelarActionPerformed());
		btnCancelar.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnCancelar.setBounds(101, 284, 118, 31);
		panel.add(btnCancelar);

		this.setLocation(GuiUtils.centralizaTela(this));

	}

	protected ActionListener btnCancelarActionPerformed() {
		return e -> Mensagem.confirmaSaidaDoPrograma();
	}

	protected ActionListener btnEntrarActionPerformed() {
		return arg0 -> FrmLoginUsuario.this.autenticaUsuario();
	}

	protected boolean validaCampos() {
		return (!this.txtUsuario.getText().isEmpty() && !new String(this.txtSenha.getPassword()).isEmpty());
	}

	protected void autenticaUsuario() {
		if (this.validaCampos()) {
			try {
				String senha = new String(this.txtSenha.getPassword());

				if (uDao.autentica(this.txtUsuario.getText(), senha)) {
					this.chamaMenuPrincipal(uDao.getUser(new Usuario(this.txtUsuario.getText(), senha)));
				} else {
					Mensagem.erro(this, "Usuário ou senha incorretos.");
				}
			} catch (SQLException e) {
				Mensagem.erro(this, e);
			}
		} else {
			Mensagem.erro(this, "Preencha os campos obrigatórios antes de continuar.");
		}
	}

	protected void chamaMenuPrincipal(Usuario usuario) throws SQLException {
		ConnectionFactory.setUsuarioConectado(usuario);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		FrmMenuPrincipal menuPrincipal = new FrmMenuPrincipal(new ConnectionFactory().getConnection());
		menuPrincipal.setVisible(true);
		this.dispose();
	}

	protected void txtUsuarioKeyPressed(KeyEvent e) {
		// muda o foco do textField
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.txtSenha.requestFocus();
		}
	}

	protected void txtSenhaKeyPressed(KeyEvent e) {
		// executa método do botão entrar
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.autenticaUsuario();
		}
	}
}
