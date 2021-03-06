package br.com.estatistica.visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.estatistica.bean.EstadoFormatter;
import br.com.estatistica.dao.CidadeDAO;
import br.com.estatistica.dao.EstadoDAO;
import br.com.estatistica.modelos.Cidade;
import br.com.estatistica.modelos.Estado;
import br.com.estatistica.modelos.table.ObjectComboBoxModel;
import br.com.estatistica.util.ConnectionFactory;
import br.com.estatistica.util.Mensagem;

public class FrmCadastroCidade extends GenericFormCadastro {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JButton btnPesquisar;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblUf;
	private JLabel lblDescrio;
	private JTextArea txtDescricao;
	private JScrollPane scrollPane;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private ObjectComboBoxModel<Estado> comboBoxModel;
	private EstadoDAO eDao;
	private JComboBox<Object> comboBox;
	private JLabel lblCodIbge;
	private JTextField txtCodIbge;
	private CidadeDAO cDao;

	public FrmCadastroCidade(Connection connection) {
		super("Cadastro de Cidades", connection);
		this.initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		this.setSize(570, 399);
		this.setResizable(false);
		this.panel = new JPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(null);

		this.lblCdigo = new JLabel("Código");
		this.lblCdigo.setBounds(10, 11, 58, 14);
		this.panel.add(this.lblCdigo);

		this.txtCodigo = new JTextField();
		this.txtCodigo.setEditable(false);
		this.txtCodigo.setBounds(10, 25, 46, 20);
		this.panel.add(this.txtCodigo);
		this.txtCodigo.setColumns(10);

		this.btnPesquisar = new JButton("");
		this.btnPesquisar.addActionListener(e -> FrmCadastroCidade.this.btnPesquisarActionPerformed(e));
		this.btnPesquisar.setBounds(57, 25, 25, 20);
		this.panel.add(this.btnPesquisar);

		this.lblNome = new JLabel("Nome");
		this.lblNome.setBounds(92, 11, 70, 14);
		this.panel.add(this.lblNome);

		this.txtNome = new JTextField();
		this.txtNome.setBounds(92, 25, 135, 20);
		this.panel.add(this.txtNome);
		this.txtNome.setColumns(10);

		this.lblUf = new JLabel("UF");
		this.lblUf.setBounds(326, 11, 46, 14);
		this.panel.add(this.lblUf);

		this.lblDescrio = new JLabel("Descrição");
		this.lblDescrio.setBounds(10, 56, 94, 14);
		this.panel.add(this.lblDescrio);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 71, 362, 242);
		this.panel.add(this.scrollPane);

		this.txtDescricao = new JTextArea();
		this.txtDescricao.setLocation(173, 0);
		this.scrollPane.setViewportView(this.txtDescricao);

		this.btnNovo = new JButton("Novo");
		this.btnNovo.addActionListener(e -> FrmCadastroCidade.this.btnNovoActionPerformed(e));
		this.btnNovo.setBounds(10, 322, 81, 31);
		this.panel.add(this.btnNovo);

		this.btnSalvar = new JButton("Salvar");
		this.btnSalvar.addActionListener(e -> FrmCadastroCidade.this.btnSalvarPerformed(e));
		this.btnSalvar.setBounds(92, 322, 80, 31);
		this.panel.add(this.btnSalvar);

		this.btnExcluir = new JButton("Excluir");
		this.btnExcluir.addActionListener(e -> FrmCadastroCidade.this.btnExcluirActionPerformed(e));
		this.btnExcluir.setBounds(171, 322, 80, 31);
		this.panel.add(this.btnExcluir);

		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(e -> FrmCadastroCidade.this.btnCancelarActionPerformed(e));
		this.btnCancelar.setBounds(281, 322, 94, 31);
		this.panel.add(this.btnCancelar);

		this.comboBoxModel = new ObjectComboBoxModel<Estado>();
		this.comboBoxModel.setFormatter(new EstadoFormatter());
		this.preencheComboBox();
		this.comboBox = new JComboBox<Object>(this.comboBoxModel);
		this.comboBox.setBounds(325, 25, 47, 20);
		this.panel.add(this.comboBox);

		this.lblCodIbge = new JLabel("Cod. IBGE");
		this.lblCodIbge.setBounds(230, 11, 70, 14);
		this.panel.add(this.lblCodIbge);

		this.txtCodIbge = new JTextField();
		this.txtCodIbge.setBounds(230, 25, 86, 20);
		this.panel.add(this.txtCodIbge);
		this.txtCodIbge.setColumns(10);

	}

	private void preencheComboBox() {
		try {
			this.eDao = new EstadoDAO(this.getConnection());
			List<Estado> all = this.eDao.getAll();

			for (Estado estado : all) {
				this.comboBoxModel.add(estado);
			}

		} catch (SQLException e) {
			Mensagem.erro(this, e);
		}
	}

	protected void btnNovoActionPerformed(ActionEvent e) {
		super.limpaCampos(this.getContentPane());
		this.comboBoxModel.setSelectedItem(null);
		this.txtCodigo.requestFocus();
	}

	protected void btnSalvarPerformed(ActionEvent e) {
		try {
			Cidade cidade = new Cidade();
			cidade.setId(this.txtCodigo.getText().isEmpty() ? null : Integer.parseInt(this.txtCodigo.getText()));
			cidade.setNome(this.txtNome.getText());
			cidade.setDescricao(this.txtDescricao.getText());
			cidade.setCodIbge(this.txtCodIbge.getText());
			System.out.println(this.comboBoxModel.getSelectedItem());
			cidade.setEstado(this.comboBoxModel.getSelectedObject());

			this.cDao = new CidadeDAO(this.getConnection());
			this.txtCodigo.setText(String.valueOf(this.cDao.save(cidade)));
		} catch (SQLException ex) {
			Mensagem.erro(this, ex);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			FrmCadastroCidade e = new FrmCadastroCidade(new ConnectionFactory().getConnection());
			e.setVisible(true);
		});
	}

	protected void btnExcluirActionPerformed(ActionEvent e) {
		try {
			if (Mensagem.confirma(this, "Deseja realmente excluir esse registro?") == JOptionPane.YES_OPTION) {
				if (!this.txtCodigo.getText().isEmpty()) {
					Cidade b = new Cidade();
					b.setId(Integer.parseInt(this.txtCodigo.getText()));

					this.cDao = new CidadeDAO(this.getConnection());
					this.cDao.delete(b);
					super.limpaCampos(this.getContentPane());
				} else {
					Mensagem.erro(this, "É necessário que seja informado o código do registro antes de realizar a exclusão.");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	protected void btnPesquisarActionPerformed(ActionEvent e) {
		try {
			FrmConsultaCidade consulta = new FrmConsultaCidade(this, this.getConnection());

			if (consulta.execute()) {
				if (consulta.getSelecionadas().size() == 1) {
					this.abreRegistro(consulta.getSelecionadas().get(0));
				} else {
					Mensagem.aviso(this,
							"Você selecionou vários registros!\nPor gentileza, repita a operação e selecione apenas um registro.");
				}
			}
		} catch (SQLException ex) {
			Mensagem.erro(this, ex);
		}

	}

	private void abreRegistro(Cidade cidade) {
		this.txtCodigo.setText(String.valueOf(cidade.getId()));
		this.txtNome.setText(cidade.getNome());
		this.txtCodIbge.setText(cidade.getCodIbge());
		this.txtDescricao.setText(cidade.getDescricao());
		this.comboBox.setSelectedItem(cidade.getEstado());

	}

	protected void btnCancelarActionPerformed(ActionEvent e) {
		this.dispose();
	}
}
