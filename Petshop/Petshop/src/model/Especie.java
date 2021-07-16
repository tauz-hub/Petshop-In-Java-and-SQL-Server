package model;

public class Especie {
	
	private int idEspecie;
	private String nomeEspecie;
	
	public int getIdEspecie() {
		return idEspecie;
	}
	public void setIdEspecie(int idEspecie) {
		this.idEspecie = idEspecie;
	}
	public String getNomeEspecie() {
		return nomeEspecie;
	}
	public void setNomeEspecie(String nomeEspecie) {
		this.nomeEspecie = nomeEspecie;
	}
	
	@Override
	public String toString() {
		return nomeEspecie;
	}
	
	@Override
	public int hashCode() {
		return idEspecie;
	}

}
