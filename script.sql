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
CREATE TABLE IF NOT EXISTS Ecole.EtudiantLogin(
    idEtudiantLogin int NOT NULL,
    login VARCHAR(30) NOT NULL,
    mdp VARCHAR(30) NOT NULL,
    idEtudiant int NOT NULL,
    CONSTRAINT pk_EtudiantLogin_ PRIMARY KEY (idEtudiantLogin),
    CONSTRAINT fk_EtudiantLogin_idEtudiant1 FOREIGN KEY (idEtudiant) REFERENCES Ecole.Etudiant(idEtudiant)
);

CREATE TABLE Ecole.Paiement(
    idPaiement int NOT NULL,
    idEtudiant int NOT NULL,
    montant integer CHECK(montant>0),
    progression integer,
    CONSTRAINT fk_Paiement_idEtudiant1 FOREIGN KEY (idEtudiant) REFERENCES Ecole.Etudiant(idEtudiant)
);


CREATE TABLE Ecole.Matiere(
    idMatiere int NOT NULL,
    nomMatiere varchar(40) NOT NULL ,
    Coefficient int not null  CHECK(coefficient>0),
      CONSTRAINT pk_matiere_idmatiere PRIMARY KEY (idMatiere)
);


CREATE TABLE Ecole.Classe(
    idClasse int NOT NULL ,
    idMatiere int NOT NULL,
    nomClasse varchar(20) NOT NULL,
    CONSTRAINT PK_CLASSE_idclasse PRIMARY KEY(idClasse),
    CONSTRAINT FK_Classe_idMatiere  FOREIGN KEY(idMatiere) REFERENCES Matiere(idMatiere)
);


CREATE TABLE Ecole.Examen(
    idExamen int NOT NULL ,
    periode int NOT NULL,
    idClasse   int  NOT NULL  ,
    anneeScolaire int NOT NULL,
    CONSTRAINT PK_Examen_idExamen PRIMARY KEY (idExamen),
    CONSTRAINT FK_Examen_idClasse FOREIGN KEY (idClassesClasse) REFERENCES Classes(idClasse)
);

CREATE TABLE Ecole.Bulletin(
    idBulletin INTEGER NOT NULL,
    idExamen INTEGER NOT NULL,
    idEtudiant INT NOT NULL,
    note int check (note>=0),
    CONSTRAINT PK_Bulletin_idBulletin PRIMARY KEY (idBulletin),
    CONSTRAINT FK_Bulletin_idExamen FOREIGN KEY (idExamen) REFERENCES Ecole.Examen (idExamen),
    CONSTRAINT FK_Bulletin_idEtudiant FOREIGN KEY (idEtudiant) REFERENCES Ecole.Etudiant(idEtudiant)
);

CREATE TABLE Ecole.prof(
    idProf INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(30),
    mdp VARCHAR(30)
);

CREATE TABLE Ecole.admin(
    idAdmin INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(30),
    mdp VARCHAR(30)
);

CREATE TABLE Ecole.fichescolaire(
    idFicheScolaire INTEGER NOT NULL PRIMARY KEY,
    mois integer NOT NULL CHECK (mois<=12),
    anneeScolaire integer NOT NULL CHECK (anneeScolaire>0),
    idEtudiant int NOT NULL,
    idClasse int NOT NULL,
    numero int NOT NULL,
    CONSTRAINT FK_FicheScolaire_idEtudiant FOREIGN KEY(idEtudiant) REFERENCES Ecole.Etudiant(idEtudiant),
    CONSTRAINT FK_FicheScolaire_idClasse FOREIGN KEY(idClasse) REFERENCES Ecole.Classe(idClasse)
);
