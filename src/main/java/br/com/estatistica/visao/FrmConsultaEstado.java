package br.com.estatistica.visao;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.com.estatistica.dao.EstadoDAO;
import br.com.estatistica.modelos.Estado;
import br.com.estatistica.modelos.table.TableModelEstado;
import br.com.estatistica.util.ConnectionFactory;
import br.com.estatistica.util.Mensagem;

public class FrmConsultaEstado extends GenericDialogConsulta {
	
	private static final long serialVersionUID = 1L;
	private TableModelEstado tableModel;
	
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JLabel lblNome;
	private JTextField txtUf;
	private JLabel lblUf;
	private JButton btnPesquisar;
	private EstadoDAO eDao;
	
	private List<Estado> resultados;
	private JTable tbResultados;
	private JScrollPane scrollPane;
	
	public FrmConsultaEstado(Frame owner, Connection connection) throws SQLException {
		super(owner, "Consulta de Estados", connection);
		this.initComponents();
	}
	
	private void initComponents() throws SQLException {
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.panel = new JPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(null);
		
		this.lblNewLabel = new JLabel("Código");
		this.lblNewLabel.setBounds(10, 11, 46, 14);
		this.panel.add(this.lblNewLabel);
		
		this.txtCodigo = new JTextField();
		this.txtCodigo.setBounds(10, 25, 46, 20);
		this.panel.add(this.txtCodigo);
		this.txtCodigo.setColumns(10);
		
		this.txtNome = new JTextField();
		this.txtNome.setColumns(10);
		this.txtNome.setBounds(66, 25, 285, 20);
		this.panel.add(this.txtNome);
		
		this.lblNome = new JLabel("Nome");
		this.lblNome.setBounds(66, 11, 46, 14);
		this.panel.add(this.lblNome);
		
		this.txtUf = new JTextField();
		this.txtUf.setColumns(10);
		this.txtUf.setBounds(361, 25, 46, 20);
		this.panel.add(this.txtUf);
		
		this.lblUf = new JLabel("UF");
		this.lblUf.setBounds(361, 11, 46, 14);
		this.panel.add(this.lblUf);
		
		this.btnPesquisar = new JButton("Ir");
		this.btnPesquisar.addActionListener(e -> this.btnPesquisarActionPerformed(e));
		this.btnPesquisar.setBounds(414, 24, 43, 23);
		this.panel.add(this.btnPesquisar);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 61, 447, 333);
		this.panel.add(this.scrollPane);
		
		this.tbResultados = this.getTbResultados();
		this.scrollPane.setViewportView(this.tbResultados);
		
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	private JTable getTbResultados() throws SQLException {
		JTable table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(new TableModelEstado(this.inicializaTabela()));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		return table;
	}
	
	public static void main(String[] args) {
		try {
			FrmConsultaEstado dialog = new FrmConsultaEstado(null, new ConnectionFactory().getConnection());
			dialog.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Estado> inicializaTabela() throws SQLException {
		this.eDao = new EstadoDAO(this.getConnection());
		return this.eDao.getAll();
	}
	
	@Override
	protected void btnOkActionPerformed(ActionEvent e) {
		
	}
	
	protected void btnPesquisarActionPerformed(ActionEvent arg0) {
		try {
			this.eDao = new EstadoDAO(this.getConnection());
			Estado estado = new Estado();
			estado.setId((this.txtCodigo.getText().isEmpty()) ? 0 : Integer.parseInt(this.txtCodigo.getText()));
			estado.setNome(this.txtNome.getText());
			estado.setUf(this.txtUf.getText());
			List<Estado> resultados = this.eDao.get(estado);
			if (!resultados.isEmpty()) {
				FrmConsultaEstado.this.tableModel.addAll(resultados);
			} else {
				Mensagem.informa(this, "A busca não obteve resultados.");
			}
		} catch (NumberFormatException | SQLException ex) {
			Mensagem.erro(this, ex);
		}
	}
}
