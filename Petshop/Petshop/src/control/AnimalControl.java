package control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Especie;
import model.EspecieDAO;
import model.Raca;
import model.RacaDAO;
import model.Animal;
import model.AnimalDAO;

public class AnimalControl {

	List<Animal> listadeAnimals;
	private Animal animal = new Animal();
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	private Especie especie;
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	
	private Raca raca;
	public Raca getRaca() {
		return raca;
	}
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	
	public void novaAnimal(String nomeAnimal, String sexo, int idEspecie, int idRaca) {
		int result = 0;
		Animal animal = new Animal();
		animal.setNomeAnimal(nomeAnimal);
		animal.setSexo(sexo);
		animal.getEspecie().setIdEspecie(idEspecie);
		animal.getRaca().setIdRaca(idRaca);
		
		result = new AnimalDAO().inserir(animal);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Animal resgistrado com sucesso !!!");
		}
	}
	
	public void alteraAnimal(
			String nomeAnimal, String sexo, int idEspecie, int idRaca, int idAnimal) {
		int result = 0;
		Animal animal = new Animal();
		animal.setNomeAnimal(nomeAnimal);
		animal.setSexo(sexo);
		animal.getEspecie().setIdEspecie(idEspecie);
		animal.getRaca().setIdRaca(idRaca);
		animal.setIdAnimal(idAnimal);
		
		result = new AnimalDAO().atualizar(animal);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Animal atualizado com sucesso !!!");
		}
	}
	
	public void inativaAnimal(int idAnimal) {
		int result = 0;
		Animal animal = new Animal();
		animal.setIdAnimal(idAnimal);
		result = new AnimalDAO().inativar(animal);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Animal excluído com sucesso !!!");
		}
	}
	
	public void buscarAnimalId(int id) {
		animal = new AnimalDAO().buscaPorId(id);
		if (animal != null) {
			especie = new EspecieDAO().buscaPorId(animal.getEspecie().getIdEspecie());
			raca = new RacaDAO().buscaPorId(animal.getRaca().getIdRaca());
		}
	}
	
	public void tabelaAnimais(JTable tabela) {
		listadeAnimals = new AnimalDAO().listar();
		List<String[]> listaRegistros = new ArrayList<String[]>();
		String[] nomesColunas = {
				"ID Animal", "Animal", "Sexo", 
				"ID Espécie", "Espécie", "ID Raça", "Raça"};
		
		for (int i = 0; i < listadeAnimals.size(); i++) {
			listaRegistros.add(new String[] {
					String.valueOf(listadeAnimals.get(i).getIdAnimal()),
					listadeAnimals.get(i).getNomeAnimal(),
					listadeAnimals.get(i).getSexo(),
					String.valueOf(listadeAnimals.get(i).getEspecie().getIdEspecie()),
					listadeAnimals.get(i).getEspecie().getNomeEspecie(),
					String.valueOf(listadeAnimals.get(i).getRaca().getIdRaca()),
					listadeAnimals.get(i).getRaca().getNomeRaca()
			});	
		}
		
		DefaultTableModel model = new DefaultTableModel(
				listaRegistros.toArray(new String[listaRegistros.size()][]), nomesColunas);
		
		tabela.setModel(model);

		tabela.getColumnModel().getColumn(0).setMaxWidth(85);
		tabela.getColumnModel().getColumn(1).setMaxWidth(200);
		tabela.getColumnModel().getColumn(2).setMaxWidth(85);
		
		tabela.getColumnModel().getColumn(3).setMinWidth(0);
		tabela.getColumnModel().getColumn(3).setMaxWidth(0);
		
		tabela.getColumnModel().getColumn(4).setMaxWidth(130);
		
		tabela.getColumnModel().getColumn(5).setMinWidth(0);
		tabela.getColumnModel().getColumn(5).setMaxWidth(0);
		
		tabela.getColumnModel().getColumn(6).setMaxWidth(130);	
	}

	public void excluiAnimal(int idAnimal) {
		int result = 0;
		Animal animal = new Animal();
		animal.setIdAnimal(idAnimal);
		result = new AnimalDAO().excluir(animal);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "Animal excluído com sucesso !!!");
		}
	}
}
