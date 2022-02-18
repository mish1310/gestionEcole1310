CREATE SCHEMA Ecole;

CREATE TABLE Ecole.Ecolage ( 
	idEcolage            int  NOT NULL  ,
	montant              int  NOT NULL  ,
	progression          int  NOT NULL  ,
	idEtudiant           int  NOT NULL  ,
	idClasse             int  NOT NULL  ,
	CONSTRAINT Pk_Ecolage_idEcolage PRIMARY KEY ( idEcolage ),
	CONSTRAINT Unq_Ecolage_idEtudiant UNIQUE ( idEtudiant ),
	CONSTRAINT Unq_Ecolage_idClasse UNIQUE ( idClasse )
 );

CREATE TABLE Ecole.Etudiant ( 
	idEtudiant           int  NOT NULL  ,
	nom                  varchar(60)  NOT NULL  ,
	login                varchar(20)  NOT NULL  ,
	mdp                  varchar(50)  NOT NULL  ,
	CONSTRAINT Pk_Etudiant_idEtudiant PRIMARY KEY ( idEtudiant )
 );

CREATE TABLE Ecole.Classe ( 
	idClasse             int  NOT NULL  ,
	designation          varchar(4)  NOT NULL  ,
	CONSTRAINT Pk_Classe_idClasse PRIMARY KEY ( idClasse )
 );

ALTER TABLE Ecole.Classe ADD CONSTRAINT Fk_Classe_Ecolage FOREIGN KEY ( idClasse ) REFERENCES Ecole.Ecolage( idClasse ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE Ecole.Etudiant ADD CONSTRAINT Fk_Etudiant_Ecolage FOREIGN KEY ( idEtudiant ) REFERENCES Ecole.Ecolage( idEtudiant ) ON DELETE NO ACTION ON UPDATE NO ACTION;
