package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PrincipalForm extends JFrame {

	private JPanel contentPane;
	private JButton btnEspecie;
	private JButton btnRaca;
	private JButton btnAnimal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalForm frame = new PrincipalForm();
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
	public PrincipalForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(148, 476, 498, 76);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnEspecie = new JButton("Esp\u00E9cie");
		btnEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspecieForm especieForm = new EspecieForm();
				especieForm.setVisible(true);
			}
		});
		btnEspecie.setBounds(12, 13, 150, 50);
		panel.add(btnEspecie);
		btnEspecie.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnRaca = new JButton("Ra\u00E7a");
		btnRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RacaForm racaForm = new RacaForm();
				racaForm.setVisible(true);
			}
		});
		btnRaca.setBounds(174, 13, 150, 50);
		panel.add(btnRaca);
		btnRaca.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnAnimal = new JButton("Animal");
		btnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimalForm animalForm = new AnimalForm();
				animalForm.setVisible(true);
			}
		});
		btnAnimal.setBounds(336, 13, 150, 50);
		panel.add(btnAnimal);
		btnAnimal.setFont(new Font("Tahoma", Font.BOLD, 18));
	}
}
