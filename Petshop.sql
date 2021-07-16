CREATE DATABASE Petshop_2CM_2DM
GO
USE Petshop_2CM_2DM
GO

CREATE TABLE Especie(
	idEspecie   int IDENTITY PRIMARY KEY,
	nomeEspecie varchar(100) NOT NULL,
	statusEspecie varchar(10) DEFAULT 'ATIVO'
)
GO

CREATE TABLE Raca(
	idRaca    int IDENTITY PRIMARY KEY,
	nomeRaca  varchar(100) NOT NULL,
	idEspecie int FOREIGN KEY
					REFERENCES Especie,
	statusRaca varchar(10) DEFAULT 'ATIVO'
)
GO

CREATE TABLE Animal(
	idAnimal   int IDENTITY PRIMARY KEY,
	nomeAnimal varchar(100) NOT NULL,
	sexo	   varchar(20) NOT NULL,
	idEspecie  int FOREIGN KEY
					REFERENCES Especie,
	idRaca int FOREIGN KEY
					REFERENCES Raca,
	statusAnimal varchar(10) DEFAULT 'ATIVO'
)
GO

INSERT Especie (nomeEspecie) VALUES ('Cachorro')

UPDATE Especie
SET nomeEspecie = 'Cachorro'
WHERE idEspecie = 1

SELECT * FROM Raca WHERE statusEspecie = 'ATIVO' ORDER BY nomeEspecie

DELETE FROM Especie WHERE idEspecie = 5

INSERT Raca (nomeRaca, idEspecie) VALUES ('Pitbull', 10)

SELECT Raca.idRaca, Raca.nomeRaca, Especie.idEspecie, Especie.nomeEspecie
FROM Raca INNER JOIN Especie on Raca.idEspecie = Especie.idEspecie
WHERE Raca.statusRaca = 'ATIVO'

SELECT a.idAnimal, a.nomeAnimal, a.sexo, 
e.idEspecie, e.nomeEspecie, r.idRaca, r.nomeRaca 
FROM Animal a INNER JOIN Especie e on a.idEspecie = e.idEspecie  
INNER JOIN Raca r on a.idRaca = r.idRaca  
WHERE a.statusAnimal = 'ATIVO'