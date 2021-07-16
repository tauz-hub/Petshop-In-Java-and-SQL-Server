package model;

public class CriaAnimal {

	public static void main(String[] args) {
		Especie especie = new Especie();
		especie.setIdEspecie(1);
		especie.setNomeEspecie("Cachorro");
		
		System.out.println( 
				"--> *** Espécie *** <--\n" +
				"ID: " + especie.getIdEspecie() +"\n"+
				"Espécie: " + especie.getNomeEspecie());
		
		Raca raca = new Raca();
		raca.setIdRaca(10);
		raca.setNomeRaca("Poodle");
		raca.setEspecie(especie);
		
		System.out.println( 
				"\n\n--> *** Raça *** <--\n" +
				"ID: " + raca.getIdRaca() +"\n"+
				"Raça: " + raca.getNomeRaca() +"\n"+
				"Espécie: " + raca.getEspecie().getNomeEspecie());
		
		Animal animal = new Animal();
		animal.setIdAnimal(100);
		animal.setNomeAnimal("Totó");
		animal.setSexo("Macho");
		animal.setEspecie(especie);
		animal.setRaca(raca);
		
		System.out.println( 
				"\n\n--> *** Animal *** <--\n" +
				"ID: " + animal.getIdAnimal() +"\n"+
				"Animal: " + animal.getNomeAnimal() +"\n"+
				"Sexo: " + animal.getSexo() +"\n"+
				"Espécie: " + animal.getEspecie().getNomeEspecie()+"\n"+
				"Raça: " + animal.getRaca().getNomeRaca());

	}

}
