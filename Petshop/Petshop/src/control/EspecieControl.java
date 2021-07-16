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

public class EspecieControl {

	List<Especie> listadeEspecies;
	private Especie especie = new Especie();
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public void novaEspecie(String nomeEspecie) {
		int result = 0;
		Especie especie = new Especie();
		especie.setNomeEspecie(nomeEspecie);
		result = new EspecieDAO().inserir(especie);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Espécie resgistrada com sucesso !!!");
		}
	}
	
	public void alteraEspecie(String nomeEspecie, int idEspecie) {
		int result = 0;
		Especie especie = new Especie();
		especie.setNomeEspecie(nomeEspecie);
		especie.setIdEspecie(idEspecie);
		result = new EspecieDAO().atualizar(especie);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Espécie atualizada com sucesso !!!");
		}
	}
	
	public void inativaEspecie(int idEspecie) {
		int result = 0;
		Especie especie = new Especie();
		especie.setIdEspecie(idEspecie);
		result = new EspecieDAO().inativar(especie);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Espécie excluída com sucesso !!!");
		}
	}
	
	public void buscarEspecieId(int id) {
		especie = new EspecieDAO().buscaPorId(id);
	}
	
	public static void carregaComboEspecies(JComboBox<Especie> combo) {
		try {
			Vector<Especie> especies = new EspecieDAO().listarCombo();
			combo.setModel(new DefaultComboBoxModel<Especie>(especies));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tabelaEspecies(JTable tabela) {
		listadeEspecies = new EspecieDAO().listar();
		List<String[]> listaRegistros = new ArrayList<String[]>();
		String[] nomesColunas = {"ID Espécie", "Nome da Espécie"};
		
		for (int i = 0; i < listadeEspecies.size(); i++) {
			listaRegistros.add(new String[] {
					String.valueOf(listadeEspecies.get(i).getIdEspecie()),
					listadeEspecies.get(i).getNomeEspecie()
			});	
		}
		
		DefaultTableModel model = new DefaultTableModel(
				listaRegistros.toArray(new String[listaRegistros.size()][]), nomesColunas);
		
		tabela.setModel(model);
		
		//tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(130);
		tabela.getColumnModel().getColumn(1).setMaxWidth(256);
		
	}

	public void excluiEspecie(int idEspecie) {
		int result = 0;
		Especie especie = new Especie();
		especie.setIdEspecie(idEspecie);
		result = new EspecieDAO().excluir(especie);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Espécie excluída com sucesso !!!");
		}
	}
}
