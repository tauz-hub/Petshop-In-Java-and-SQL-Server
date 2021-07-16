package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.EspecieControl;
import control.RacaControl;
import model.Especie;

public class RacaForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6363606638282743346L;
	private JPanel contentPane;
	private JTextField txtIdRaca;
	private JLabel lblIdRaca;
	private JTextField txtNomeRaca;
	private JButton btnGravar;
	private JButton btnAtualizar;
	private JButton btnBuscar;
	private JButton btnListar;
	RacaControl racaControl = new RacaControl();
	private JTable tblRacas;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JComboBox<Especie> cboEspecies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RacaForm frame = new RacaForm();
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
	public RacaForm() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Raça");
		setBounds(100, 100, 535, 350);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIdRaca = new JTextField();
		txtIdRaca.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtIdRaca.setBounds(145, 11, 125, 26);
		contentPane.add(txtIdRaca);
		txtIdRaca.setColumns(10);
		
		lblIdRaca = new JLabel("ID Raça:");
		lblIdRaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdRaca.setBounds(10, 12, 130, 24);
		contentPane.add(lblIdRaca);
		
		JLabel lblNomeRaca = new JLabel("Nome da Ra\u00E7a:");
		lblNomeRaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeRaca.setBounds(10, 45, 130, 24);
		contentPane.add(lblNomeRaca);
		
		txtNomeRaca = new JTextField();
		txtNomeRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNomeRaca.setColumns(10);
		txtNomeRaca.setBounds(145, 44, 250, 26);
		contentPane.add(txtNomeRaca);
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtNomeRaca.getText().trim().isEmpty()) {
					
					racaControl.novaRaca(
							txtNomeRaca.getText(),
							cboEspecies.getSelectedItem().hashCode());
					
					racaControl.tabelaRacas(tblRacas);
					
					txtNomeRaca.setText("");
					cboEspecies.setSelectedIndex(-1);
				}
			}
		});
		btnGravar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGravar.setBounds(399, 42, 120, 30);
		contentPane.add(btnGravar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!txtNomeRaca.getText().trim().isEmpty() &&
						!txtIdRaca.getText().trim().isEmpty()) {
					
					racaControl.alteraRaca(
						txtNomeRaca.getText(), 
						cboEspecies.getSelectedItem().hashCode(), 
						Integer.parseInt(txtIdRaca.getText()));
					
					racaControl.tabelaRacas(tblRacas);
					
					txtIdRaca.setText("");
					txtNomeRaca.setText("");
					cboEspecies.setSelectedIndex(-1);
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtualizar.setBounds(399, 76, 120, 30);
		contentPane.add(btnAtualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				racaControl.buscarRacaId(Integer.parseInt(txtIdRaca.getText()));
				if (racaControl.getRaca() != null) {
					txtNomeRaca.setText(racaControl.getRaca().getNomeRaca());
					cboEspecies.setEditable(true);
					cboEspecies.setSelectedItem(racaControl.getEspecie());
					cboEspecies.setEditable(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "Registro não encontrado.");
					txtNomeRaca.setText("");
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscar.setBounds(274, 9, 120, 30);
		contentPane.add(btnBuscar);
		
		btnListar =  new JButton("Listar");
		btnListar.setEnabled(false);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				racaControl.tabelaRacas(tblRacas);
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnListar.setBounds(399, 113, 120, 30);
		contentPane.add(btnListar);
		
		tblRacas = new JTable();
		tblRacas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdRaca.setText(tblRacas.getValueAt(tblRacas.getSelectedRow(), 0).toString());
				btnBuscar.doClick();
			}
		});
		tblRacas.setBounds(1, 1, 1, 1);
		contentPane.add(tblRacas);
		
		JScrollPane scrollPane = new JScrollPane(tblRacas);
		scrollPane.setBounds(10, 113, 386, 196);
		contentPane.add(scrollPane);
		
		racaControl.tabelaRacas(tblRacas);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdRaca.setText("");
				txtNomeRaca.setText("");
				cboEspecies.setSelectedIndex(-1);
				txtNomeRaca.requestFocus();
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNovo.setBounds(399, 9, 120, 30);
		contentPane.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNomeRaca.getText().trim().isEmpty() &&
						!txtIdRaca.getText().trim().isEmpty()) {
					
					racaControl.inativaRaca(Integer.parseInt(txtIdRaca.getText()));
					//racaControl.excluiRaca(Integer.parseInt(txtIdRaca.getText()));
					txtIdRaca.setText("");
					txtNomeRaca.setText("");
					racaControl.tabelaRacas(tblRacas);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExcluir.setBounds(399, 147, 120, 30);
		contentPane.add(btnExcluir);
		
		JLabel lblEspcie = new JLabel("Esp\u00E9cie:");
		lblEspcie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspcie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEspcie.setBounds(10, 79, 130, 24);
		contentPane.add(lblEspcie);
		
		cboEspecies = new JComboBox<Especie>();
		cboEspecies.setFont(new Font("Tahoma", Font.BOLD, 16));
		cboEspecies.setBounds(145, 78, 250, 26);
		contentPane.add(cboEspecies);
		
		EspecieControl.carregaComboEspecies(cboEspecies);
		cboEspecies.setSelectedIndex(-1);
		
	}
}
