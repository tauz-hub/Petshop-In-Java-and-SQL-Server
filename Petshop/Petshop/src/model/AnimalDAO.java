package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class AnimalDAO {
	//*** Data Access Object ***\\
	
	Connection conexao = new ConnectionFactory().getConnection(); //conexao: conecta a Aplicação com o BD
	private PreparedStatement st = null; //st: Aplicação -> BD
	private ResultSet rs = null; //rs: BD -> Aplicação
	Animal animal; //representa a tabela do BD
	
	public AnimalDAO() {
		
	}
	public int inserir(Animal animal) {
		int resultado = 0;	
		String sql = "INSERT Animal (nomeAnimal, sexo, idEspecie, idRaca) VALUES (?, ?, ?, ?)";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, animal.getNomeAnimal());
			st.setString(2, animal.getSexo());
			st.setInt(3, animal.getEspecie().getIdEspecie());
			st.setInt(4, animal.getRaca().getIdRaca());
			
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public int atualizar(Animal animal) {
		int resultado = 0;	
		String sql = "UPDATE Animal " + 
					 "SET nomeAnimal = ?, sexo = ?, idEspecie = ?, idRaca = ?  " + 
					 "WHERE idAnimal = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, animal.getNomeAnimal());
			st.setString(2, animal.getSexo());
			st.setInt(3, animal.getEspecie().getIdEspecie());
			st.setInt(4, animal.getRaca().getIdRaca());
			st.setInt(5, animal.getIdAnimal());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int inativar(Animal animal) {
		int resultado = 0;	
		String sql = "UPDATE Animal " + 
					 "SET statusAnimal = 'INATIVO'" + 
					 "WHERE idAnimal = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, animal.getIdAnimal());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Animal buscaPorId(int id) {
		String sql = "SELECT * FROM Animal where idAnimal = ? AND statusAnimal = 'ATIVO'";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();		
			if (rs.next()) {
				animal = new Animal();
				animal.setIdAnimal(rs.getInt("idAnimal"));
				animal.setNomeAnimal(rs.getString("nomeAnimal"));
				animal.setSexo(rs.getString("sexo"));
				animal.getEspecie().setIdEspecie(rs.getInt("idEspecie"));
				animal.getRaca().setIdRaca(rs.getInt("idRaca"));
			} else {
				return null;
			}
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animal;
	}
	
	public List<Animal> listar(){
		String sql = "SELECT a.idAnimal, a.nomeAnimal, a.sexo, " + 
					"e.idEspecie, e.nomeEspecie, r.idRaca, r.nomeRaca " + 
					"FROM Animal a INNER JOIN Especie e " + 
					"ON a.idEspecie = e.idEspecie " + 
					"INNER JOIN Raca r ON a.idRaca = r.idRaca " + 
					"WHERE a.statusAnimal = 'ATIVO'";
		List<Animal> animals = new ArrayList<Animal>();
	
		try {
			st = conexao.prepareStatement(sql);
			rs = st.executeQuery();	
			while (rs.next()) {
				animal = new Animal();
				animal.setIdAnimal(rs.getInt("idAnimal"));
				animal.setNomeAnimal(rs.getString("nomeAnimal"));
				animal.setSexo(rs.getString("sexo"));
				animal.getEspecie().setIdEspecie(rs.getInt("idEspecie"));
				animal.getEspecie().setNomeEspecie(rs.getString("nomeEspecie"));
				animal.getRaca().setIdRaca(rs.getInt("idRaca"));
				animal.getRaca().setNomeRaca(rs.getString("nomeRaca"));
				animals.add(animal);
			} 
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animals;
	}
	
	public int excluir(Animal animal) {
		int resultado = 0;	
		String sql = "DELETE FROM Animal WHERE idAnimal = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, animal.getIdAnimal());
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
