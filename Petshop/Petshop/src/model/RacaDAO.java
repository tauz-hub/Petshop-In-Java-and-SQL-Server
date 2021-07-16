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

public class RacaDAO {
	//*** Data Access Object ***\\
	
	Connection conexao = new ConnectionFactory().getConnection(); //conexao: conecta a Aplicação com o BD
	private PreparedStatement st = null; //st: Aplicação -> BD
	private ResultSet rs = null; //rs: BD -> Aplicação
	Raca raca; //representa a tabela do BD
	
	public RacaDAO() {
		
	}
	public int inserir(Raca raca) {
		int resultado = 0;	
		String sql = "INSERT Raca (nomeRaca, idEspecie) VALUES (?, ?)";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, raca.getNomeRaca());
			st.setInt(2, raca.getEspecie().getIdEspecie());
			
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public int atualizar(Raca raca) {
		int resultado = 0;	
		String sql = "UPDATE Raca " + 
					 "SET nomeRaca = ?, idEspecie = ? " + 
					 "WHERE idRaca = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setString(1, raca.getNomeRaca());
			st.setInt(2, raca.getEspecie().getIdEspecie());
			st.setInt(3, raca.getIdRaca());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int inativar(Raca raca) {
		int resultado = 0;	
		String sql = "UPDATE Raca " + 
					 "SET statusRaca = 'INATIVO'" + 
					 "WHERE idRaca = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, raca.getIdRaca());
			resultado = st.executeUpdate();
			
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Raca buscaPorId(int id) {
		String sql = "SELECT * FROM Raca where idRaca = ? AND statusRaca = 'ATIVO'";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();		
			if (rs.next()) {
				raca = new Raca();
				raca.setIdRaca(rs.getInt("idRaca"));
				raca.setNomeRaca(rs.getString("nomeRaca"));
				raca.getEspecie().setIdEspecie(rs.getInt("idEspecie"));
			} else {
				return null;
			}
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return raca;
	}
	
	public Vector<Raca> listarCombo(int idEspecie){
		String sql = "SELECT * FROM Raca WHERE idEspecie = ? AND "
					+ "statusRaca = 'ATIVO' ORDER BY nomeRaca";
		Vector<Raca> racas = new Vector<Raca>();
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, idEspecie);
			rs = st.executeQuery();	
			while (rs.next()) {
				raca = new Raca();
				raca.setIdRaca(rs.getInt("idRaca"));
				raca.setNomeRaca(rs.getString("nomeRaca"));
				racas.add(raca);
			} 
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racas;
	}
	
	
	public List<Raca> listar(){
		String sql = "SELECT Raca.idRaca, Raca.nomeRaca, " + 
					"Especie.idEspecie, Especie.nomeEspecie " + 
					"FROM Especie INNER JOIN Raca " + 
					"ON Especie.idEspecie = Raca.idEspecie " + 
					"WHERE Raca.statusRaca = 'ATIVO'";
		List<Raca> racas = new ArrayList<Raca>();
	
		try {
			st = conexao.prepareStatement(sql);
			rs = st.executeQuery();	
			while (rs.next()) {
				raca = new Raca();
				raca.setIdRaca(rs.getInt("idRaca"));
				raca.setNomeRaca(rs.getString("nomeRaca"));
				raca.getEspecie().setIdEspecie(rs.getInt("idEspecie"));
				raca.getEspecie().setNomeEspecie(rs.getString("nomeEspecie"));
				racas.add(raca);
			} 
			conexao.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racas;
	}
	
	public int excluir(Raca raca) {
		int resultado = 0;	
		String sql = "DELETE FROM Raca WHERE idRaca = ?";
		try {
			st = conexao.prepareStatement(sql);
			st.setInt(1, raca.getIdRaca());
			resultado = st.executeUpdate();
			conexao.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
