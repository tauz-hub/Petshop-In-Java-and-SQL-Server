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
import control.AnimalControl;
import model.Especie;
import model.Raca;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Insets;

public class AnimalForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6363606638282743346L;
	private JPanel contentPane;
	private JTextField txtIdAnimal;
	private JLabel lblIdAnimal;

	private JButton btnGravar;
	private JButton btnAtualizar;
	private JButton btnBuscar;
	private JButton btnListar;
	AnimalControl animalControl = new AnimalControl();
	private JTable tblAnimals;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JComboBox<Especie> cboEspecies;
	private JComboBox<Raca> cboRacas;
	private final ButtonGroup btnGrSexo = new ButtonGroup();
	private JRadioButton rdbtnMacho;
	private JRadioButton rdbtnFemea;
	private JRadioButton rdbtnIndefinido;
	private JButton btnNovaEspecie;
	private JButton btnNovaRaca;
	private JTextField txtNomeAnimal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalForm frame = new AnimalForm();
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
	public AnimalForm() {
		setResizable(false);
		setTitle("Animal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(655, 425);
		setLocationRelativeTo(null);
		setModal(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIdAnimal = new JTextField();
		txtIdAnimal.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdAnimal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtIdAnimal.setBounds(145, 11, 155, 26);
		contentPane.add(txtIdAnimal);
		txtIdAnimal.setColumns(10);
		
		lblIdAnimal = new JLabel("ID Animal:");
		lblIdAnimal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdAnimal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdAnimal.setBounds(10, 12, 130, 24);
		contentPane.add(lblIdAnimal);
		
		JLabel lblNomeAnimal = new JLabel("Nome Animal:");
		lblNomeAnimal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeAnimal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeAnimal.setBounds(5, 45, 135, 24);
		contentPane.add(lblNomeAnimal);
		
		txtNomeAnimal = new JTextField();
		txtNomeAnimal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNomeAnimal.setColumns(10);
		txtNomeAnimal.setBounds(145, 44, 280, 26);
		contentPane.add(txtNomeAnimal);
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sexo = "";
				if (rdbtnMacho.isSelected()) {
					sexo = "Macho";
				} else if (rdbtnFemea.isSelected()) {
					sexo = "Fêmea";
				} else if (rdbtnIndefinido.isSelected()) {
					sexo = "Indefinido";
				}
				if (!txtNomeAnimal.getText().trim().isEmpty()) {
					
					animalControl.novaAnimal(
							txtNomeAnimal.getText(), sexo,
							cboEspecies.getSelectedItem().hashCode(),
							cboRacas.getSelectedItem().hashCode());
					
					animalControl.tabelaAnimais(tblAnimals);

					limpar();
				}
			}
		});
		btnGravar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGravar.setBounds(538, 9, 105, 30);
		contentPane.add(btnGravar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setMargin(new Insets(2, 2, 2, 2));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sexo = "";
				if (rdbtnMacho.isSelected()) {
					sexo = "Macho";
				} else if (rdbtnFemea.isSelected()) {
					sexo = "Fêmea";
				} else if (rdbtnIndefinido.isSelected()) {
					sexo = "Indefinido";
				}
				if (!txtNomeAnimal.getText().trim().isEmpty() &&
						!txtIdAnimal.getText().trim().isEmpty()) {
					
					animalControl.alteraAnimal(
						txtNomeAnimal.getText(), sexo,
						cboEspecies.getSelectedItem().hashCode(), 
						cboRacas.getSelectedItem().hashCode(),
						Integer.parseInt(txtIdAnimal.getText()));
					
					animalControl.tabelaAnimais(tblAnimals);
					
					limpar();
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtualizar.setBounds(538, 42, 105, 30);
		contentPane.add(btnAtualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animalControl.buscarAnimalId(Integer.parseInt(txtIdAnimal.getText()));
				if (animalControl.getAnimal() != null) {
					txtNomeAnimal.setText(animalControl.getAnimal().getNomeAnimal());
					cboEspecies.setEditable(true);
					cboEspecies.setSelectedItem(animalControl.getEspecie());
					cboEspecies.setEditable(false);
					
					cboRacas.setEditable(true);
					cboRacas.setSelectedItem(animalControl.getRaca());
					cboRacas.setEditable(false);
					
					if (animalControl.getAnimal().getSexo().equals("Macho")) {
						rdbtnMacho.setSelected(true);
					} else if (animalControl.getAnimal().getSexo().equals("Fêmea")) {
						rdbtnFemea.setSelected(true);
					} else if (animalControl.getAnimal().getSexo().equals("Indefinido")) {
						rdbtnIndefinido.setSelected(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Registro não encontrado.");
					txtNomeAnimal.setText("");
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscar.setBounds(304, 9, 120, 30);
		contentPane.add(btnBuscar);
		
		btnListar =  new JButton("Listar");
		btnListar.setEnabled(false);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animalControl.tabelaAnimais(tblAnimals);
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnListar.setBounds(430, 42, 105, 30);
		contentPane.add(btnListar);
		
		tblAnimals = new JTable();
		tblAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdAnimal.setText(tblAnimals.getValueAt(tblAnimals.getSelectedRow(), 0).toString());
				btnBuscar.doClick();
			}
		});
		tblAnimals.setBounds(1, 1, 1, 1);
		contentPane.add(tblAnimals);
		
		JScrollPane scrollPane = new JScrollPane(tblAnimals);
		scrollPane.setBounds(9, 183, 630, 196);
		contentPane.add(scrollPane);
		
		animalControl.tabelaAnimais(tblAnimals);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNovo.setBounds(430, 9, 105, 30);
		contentPane.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNomeAnimal.getText().trim().isEmpty() &&
						!txtIdAnimal.getText().trim().isEmpty()) {
					
					animalControl.inativaAnimal(Integer.parseInt(txtIdAnimal.getText()));
					//animalControl.excluiAnimal(Integer.parseInt(txtIdAnimal.getText()));
					limpar();
					animalControl.tabelaAnimais(tblAnimals);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExcluir.setBounds(538, 75, 105, 30);
		contentPane.add(btnExcluir);
		
		JLabel lblEspcie = new JLabel("Esp\u00E9cie:");
		lblEspcie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspcie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEspcie.setBounds(10, 112, 130, 24);
		contentPane.add(lblEspcie);
		
		cboEspecies = new JComboBox<Especie>();
		cboEspecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cboEspecies.getSelectedIndex() != -1) {
					RacaControl.carregaComboRacas(cboRacas, cboEspecies.getSelectedItem().hashCode());
					cboRacas.setSelectedIndex(-1);
				}
			}
		});
		cboEspecies.setFont(new Font("Tahoma", Font.BOLD, 16));
		cboEspecies.setBounds(145, 112, 280, 26);
		contentPane.add(cboEspecies);
		
		EspecieControl.carregaComboEspecies(cboEspecies);
		cboEspecies.setSelectedIndex(-1);
		
		JLabel lblRaca = new JLabel("Ra\u00E7a:");
		lblRaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRaca.setBounds(10, 146, 130, 24);
		contentPane.add(lblRaca);
		
		cboRacas = new JComboBox<Raca>();
		cboRacas.setFont(new Font("Tahoma", Font.BOLD, 16));
		cboRacas.setBounds(145, 146, 280, 26);
		contentPane.add(cboRacas);
				
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSexo.setBounds(5, 78, 135, 24);
		contentPane.add(lblSexo);
		
		rdbtnMacho = new JRadioButton("Macho");
		btnGrSexo.add(rdbtnMacho);
		rdbtnMacho.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnMacho.setBounds(145, 78, 81, 24);
		contentPane.add(rdbtnMacho);
		
		rdbtnFemea = new JRadioButton("F\u00EAmea");
		btnGrSexo.add(rdbtnFemea);
		rdbtnFemea.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFemea.setBounds(230, 78, 83, 24);
		contentPane.add(rdbtnFemea);
		
		rdbtnIndefinido = new JRadioButton("Indefinido");
		btnGrSexo.add(rdbtnIndefinido);
		rdbtnIndefinido.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnIndefinido.setBounds(317, 78, 111, 24);
		contentPane.add(rdbtnIndefinido);
		
		btnNovaEspecie = new JButton("Nova Esp\u00E9cie");
		btnNovaEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspecieForm especieForm = new EspecieForm();
				especieForm.setVisible(true);
			}
		});
		btnNovaEspecie.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNovaEspecie.setBounds(430, 109, 213, 30);
		contentPane.add(btnNovaEspecie);
		
		btnNovaRaca = new JButton("Nova Ra\u00E7a");
		btnNovaRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RacaForm racaForm = new RacaForm();
				racaForm.setVisible(true);
			}
		});
		btnNovaRaca.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNovaRaca.setBounds(430, 144, 213, 30);
		contentPane.add(btnNovaRaca);
		
	}
	
	public void limpar() {
		txtIdAnimal.setText("");
		txtNomeAnimal.setText("");
		cboEspecies.setSelectedIndex(-1);
		cboRacas.setSelectedIndex(-1);
		txtNomeAnimal.requestFocus();
		btnGrSexo.clearSelection();
	}

	
}
