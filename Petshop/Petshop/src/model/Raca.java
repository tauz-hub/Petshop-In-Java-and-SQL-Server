package model;

public class Raca {
	private int idRaca;
	private String nomeRaca;
	
	//idEspecie: CHAVE ESTRANGEIRA
	private Especie especie;

	public Raca() {
		super();
		especie = new Especie();
	}
	
	public int getIdRaca() {
		return idRaca;
	}
	public void setIdRaca(int idRaca) {
		this.idRaca = idRaca;
	}
	public String getNomeRaca() {
		return nomeRaca;
	}
	public void setNomeRaca(String nomeRaca) {
		this.nomeRaca = nomeRaca;
	}
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return nomeRaca;
	}
	
	@Override
	public int hashCode() {
		return idRaca;
	}
	
	
}
