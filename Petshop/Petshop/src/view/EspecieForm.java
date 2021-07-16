package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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

public class EspecieForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6363606638282743346L;
	private JPanel contentPane;
	private JTextField txtIdEspecie;
	private JLabel lblIdEspecie;
	private JTextField txtNomeEspecie;
	private JButton btnGravar;
	private JButton btnAtualizar;
	private JButton btnBuscar;
	private JButton btnListar;
	EspecieControl espControl = new EspecieControl();
	private JTable tblEspecies;
	private JButton btnNovo;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspecieForm frame = new EspecieForm();
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
	public EspecieForm() {
		setResizable(false);
		setTitle("Esp\u00E9cie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 350);
		setModal(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIdEspecie = new JTextField();
		txtIdEspecie.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEspecie.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtIdEspecie.setBounds(145, 11, 125, 26);
		contentPane.add(txtIdEspecie);
		txtIdEspecie.setColumns(10);
		
		lblIdEspecie = new JLabel("ID Esp\u00E9cie:");
		lblIdEspecie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdEspecie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdEspecie.setBounds(10, 12, 130, 24);
		contentPane.add(lblIdEspecie);
		
		JLabel lblNomeEspcie = new JLabel("Nome Esp\u00E9cie:");
		lblNomeEspcie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeEspcie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeEspcie.setBounds(10, 45, 130, 24);
		contentPane.add(lblNomeEspcie);
		
		txtNomeEspecie = new JTextField();
		txtNomeEspecie.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNomeEspecie.setColumns(10);
		txtNomeEspecie.setBounds(145, 44, 250, 26);
		contentPane.add(txtNomeEspecie);
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtNomeEspecie.getText().trim().isEmpty()) {
					espControl.novaEspecie(txtNomeEspecie.getText());
					
					espControl.tabelaEspecies(tblEspecies);	
					txtNomeEspecie.setText("");
				}
			}
		});
		btnGravar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGravar.setBounds(399, 42, 120, 30);
		contentPane.add(btnGravar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!txtNomeEspecie.getText().trim().isEmpty() &&
						!txtIdEspecie.getText().trim().isEmpty()) {
					
					espControl.alteraEspecie(
						txtNomeEspecie.getText(), Integer.parseInt(txtIdEspecie.getText()));
					
					espControl.tabelaEspecies(tblEspecies);
					
					txtIdEspecie.setText("");
					txtNomeEspecie.setText("");
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtualizar.setBounds(399, 79, 120, 30);
		contentPane.add(btnAtualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtIdEspecie.getText().trim().isEmpty()) {
					espControl.buscarEspecieId(Integer.parseInt(txtIdEspecie.getText()));
					if (espControl.getEspecie() != null) {
						txtNomeEspecie.setText(espControl.getEspecie().getNomeEspecie());
					} else {
						JOptionPane.showMessageDialog(null, "Registro não encontrado.");
						txtNomeEspecie.setText("");
					}
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
				espControl.tabelaEspecies(tblEspecies);
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnListar.setBounds(399, 113, 120, 30);
		contentPane.add(btnListar);
		
		tblEspecies = new JTable();
		tblEspecies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdEspecie.setText(tblEspecies.getValueAt(tblEspecies.getSelectedRow(), 0).toString());
				txtNomeEspecie.setText(tblEspecies.getValueAt(tblEspecies.getSelectedRow(), 1).toString());
			}
		});
		tblEspecies.setBounds(1, 1, 1, 1);
		contentPane.add(tblEspecies);
		
		JScrollPane scrollPane = new JScrollPane(tblEspecies);
		scrollPane.setBounds(10, 79, 386, 230);
		contentPane.add(scrollPane);
		
		espControl.tabelaEspecies(tblEspecies);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdEspecie.setText("");
				txtIdEspecie.setEditable(false);
				txtNomeEspecie.setText("");
				
				txtNomeEspecie.requestFocus();
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNovo.setBounds(399, 9, 120, 30);
		contentPane.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNomeEspecie.getText().trim().isEmpty() &&
						!txtIdEspecie.getText().trim().isEmpty()) {
					
					espControl.inativaEspecie(Integer.parseInt(txtIdEspecie.getText()));
					//espControl.excluiEspecie(Integer.parseInt(txtIdEspecie.getText()));
					txtIdEspecie.setText("");
					txtNomeEspecie.setText("");
					espControl.tabelaEspecies(tblEspecies);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExcluir.setBounds(399, 147, 120, 30);
		contentPane.add(btnExcluir);
	}
}
