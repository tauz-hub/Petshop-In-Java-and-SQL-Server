package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Especie;
import model.EspecieDAO;
import model.Raca;
import model.RacaDAO;

public class RacaControl {

	List<Raca> listadeRacas;
	private Raca raca = new Raca();
	public Raca getRaca() {
		return raca;
	}
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	
	private Especie especie;
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	public void novaRaca(String nomeRaca, int idEspecie) {
		int result = 0;
		Raca raca = new Raca();
		raca.setNomeRaca(nomeRaca);
		raca.getEspecie().setIdEspecie(idEspecie);
		
		result = new RacaDAO().inserir(raca);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Raça resgistrada com sucesso !!!");
		}
	}
	
	public void alteraRaca(String nomeRaca, int idEspecie, int idRaca) {
		int result = 0;
		Raca raca = new Raca();
		raca.setNomeRaca(nomeRaca);
		raca.getEspecie().setIdEspecie(idEspecie);
		raca.setIdRaca(idRaca);
		
		result = new RacaDAO().atualizar(raca);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Raça atualizada com sucesso !!!");
		}
	}
	
	public void inativaRaca(int idRaca) {
		int result = 0;
		Raca raca = new Raca();
		raca.setIdRaca(idRaca);
		result = new RacaDAO().inativar(raca);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Raça excluída com sucesso !!!");
		}
	}
	
	public void buscarRacaId(int id) {
		raca = new RacaDAO().buscaPorId(id);
		if (raca != null) {
			especie = new EspecieDAO().buscaPorId(raca.getEspecie().getIdEspecie());
		}
	}
	
	public static void carregaComboRacas(JComboBox<Raca> combo, int idEspecie) {
		try {
			Vector<Raca> racas = new RacaDAO().listarCombo(idEspecie);
			combo.setModel(new DefaultComboBoxModel<Raca>(racas));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tabelaRacas(JTable tabela) {
		listadeRacas = new RacaDAO().listar();
		List<String[]> listaRegistros = new ArrayList<String[]>();
		String[] nomesColunas = {"ID Raça", "Raça", "ID Espécie", "Espécie"};
		
		for (int i = 0; i < listadeRacas.size(); i++) {
			listaRegistros.add(new String[] {
					String.valueOf(listadeRacas.get(i).getIdRaca()),
					listadeRacas.get(i).getNomeRaca(),
					String.valueOf(listadeRacas.get(i).getEspecie().getIdEspecie()),
					listadeRacas.get(i).getEspecie().getNomeEspecie()
			});	
		}
		
		DefaultTableModel model = new DefaultTableModel(
				listaRegistros.toArray(new String[listaRegistros.size()][]), nomesColunas);
		
		tabela.setModel(model);

		tabela.getColumnModel().getColumn(0).setMaxWidth(85);
		tabela.getColumnModel().getColumn(1).setMaxWidth(175);
		
		tabela.getColumnModel().getColumn(2).setMinWidth(0);
		tabela.getColumnModel().getColumn(2).setMaxWidth(0);
		
		tabela.getColumnModel().getColumn(3).setMaxWidth(126);
		
	}

	public void excluiRaca(int idRaca) {
		int result = 0;
		Raca raca = new Raca();
		raca.setIdRaca(idRaca);
		result = new RacaDAO().excluir(raca);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Raça excluída com sucesso !!!");
		}
	}
}
