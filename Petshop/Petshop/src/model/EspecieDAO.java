package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class EspecieDAO {
	//*** Data Access Object ***\\
	
	Connection conexao = new ConnectionFactory().getConnection(); //conexao: conecta a Aplicação com o BD
	private PreparedStatement st = null; //st: Aplicação -> BD
	private ResultSet rs = null; //rs: BD -> Aplicação
	Especie especie; //representa a tabela do BD
	
	public EspecieDAO() {
		
	}
	public int inserir(Especie especie) {
		int resultado = 0;	
		String sql = "INSERT Especie (nomeEspecie) VALUES (?)";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, especie.getNomeEspecie());
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public int atualizar(Especie especie) {
		int resultado = 0;	
		String sql = "UPDATE Especie " + 
					 "SET nomeEspecie = ? " + 
					 "WHERE idEspecie = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, especie.getNomeEspecie());
			st.setInt(2, especie.getIdEspecie());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int inativar(Especie especie) {
		int resultado = 0;	
		String sql = "UPDATE Especie " + 
					 "SET statusEspecie = 'INATIVO'" + 
					 "WHERE idEspecie = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, especie.getIdEspecie());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Especie buscaPorId(int id) {
		String sql = "SELECT * FROM Especie where idEspecie = ? AND statusEspecie = 'ATIVO'";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();		
			if (rs.next()) {
				especie = new Especie();
				especie.setIdEspecie(rs.getInt("idEspecie"));
				especie.setNomeEspecie(rs.getString("nomeEspecie"));
			} else {
				return null;
			}
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especie;
	}
	
	public List<Especie> listar(){
		String sql = "SELECT * FROM Especie WHERE statusEspecie = 'ATIVO'";
		List<Especie> especies = new ArrayList<Especie>();
	
		try {
			st = conexao.prepareStatement(sql);
			rs = st.executeQuery();	
			while (rs.next()) {
				especie = new Especie();
				especie.setIdEspecie(rs.getInt("idEspecie"));
				especie.setNomeEspecie(rs.getString("nomeEspecie"));
				especies.add(especie);
			} 
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especies;
	}
	
	public Vector<Especie> listarCombo(){
		String sql = "SELECT * FROM Especie WHERE statusEspecie = 'ATIVO' ORDER BY nomeEspecie";
		Vector<Especie> especies = new Vector<Especie>();
		try {
			st = conexao.prepareStatement(sql);
			rs = st.executeQuery();	
			while (rs.next()) {
				especie = new Especie();
				especie.setIdEspecie(rs.getInt("idEspecie"));
				especie.setNomeEspecie(rs.getString("nomeEspecie"));
				especies.add(especie);
			} 
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especies;
	}
	
	public int excluir(Especie especie) {
		int resultado = 0;	
		String sql = "DELETE FROM Especie WHERE idEspecie = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, especie.getIdEspecie());
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
