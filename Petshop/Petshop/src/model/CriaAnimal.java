package model;

public class CriaAnimal {

	public static void main(String[] args) {
		Especie especie = new Especie();
		especie.setIdEspecie(1);
		especie.setNomeEspecie("Cachorro");
		
		System.out.println( 
				"--> *** Esp�cie *** <--\n" +
				"ID: " + especie.getIdEspecie() +"\n"+
				"Esp�cie: " + especie.getNomeEspecie());
		
		Raca raca = new Raca();
		raca.setIdRaca(10);
		raca.setNomeRaca("Poodle");
		raca.setEspecie(especie);
		
		System.out.println( 
				"\n\n--> *** Ra�a *** <--\n" +
				"ID: " + raca.getIdRaca() +"\n"+
				"Ra�a: " + raca.getNomeRaca() +"\n"+
				"Esp�cie: " + raca.getEspecie().getNomeEspecie());
		
		Animal animal = new Animal();
		animal.setIdAnimal(100);
		animal.setNomeAnimal("Tot�");
		animal.setSexo("Macho");
		animal.setEspecie(especie);
		animal.setRaca(raca);
		
		System.out.println( 
				"\n\n--> *** Animal *** <--\n" +
				"ID: " + animal.getIdAnimal() +"\n"+
				"Animal: " + animal.getNomeAnimal() +"\n"+
				"Sexo: " + animal.getSexo() +"\n"+
				"Esp�cie: " + animal.getEspecie().getNomeEspecie()+"\n"+
				"Ra�a: " + animal.getRaca().getNomeRaca());

	}

}
