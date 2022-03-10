

CREATE TABLE Ecolage ( 
	idEcolage            int  NOT NULL  ,
	montant              int  NOT NULL  ,
	progression          int  NOT NULL  ,
	idEtudiant           int  NOT NULL  ,
	idClasse             int  NOT NULL  ,
	CONSTRAINT Pk_Ecolage_idEcolage PRIMARY KEY ( idEcolage ),
	CONSTRAINT Unq_Ecolage_idEtudiant UNIQUE ( idEtudiant ),
	CONSTRAINT Unq_Ecolage_idClasse UNIQUE ( idClasse )
 );

CREATE TABLE Etudiant ( 
	idEtudiant           int  NOT NULL  ,
	nom                  varchar(60)  NOT NULL  ,
	login                varchar(20)  NOT NULL  ,
	mdp                  varchar(50)  NOT NULL  ,
	CONSTRAINT Pk_Etudiant_idEtudiant PRIMARY KEY ( idEtudiant )
 );

CREATE TABLE IF NOT EXISTS EtudiantLogin(
    idEtudiantLogin int NOT NULL,
    login VARCHAR(30) NOT NULL,
    mdp VARCHAR(30) NOT NULL,
    idEtudiant int NOT NULL,
    CONSTRAINT pk_EtudiantLogin_ PRIMARY KEY (idEtudiantLogin),
    CONSTRAINT fk_EtudiantLogin_idEtudiant1 FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant)
);




CREATE TABLE Matiere(
    idMatiere int NOT NULL,
    nomMatiere varchar(40) NOT NULL ,
    Coefficient int not null  CHECK(coefficient>0),
      CONSTRAINT pk_matiere_idmatiere PRIMARY KEY (idMatiere)
);


CREATE TABLE Classe(
    idClasse int NOT NULL ,
    idMatiere int NOT NULL,
    nomClasse varchar(20) NOT NULL,
    CONSTRAINT PK_CLASSE_idclasse PRIMARY KEY(idClasse),
    CONSTRAINT FK_Classe_idMatiere  FOREIGN KEY(idMatiere) REFERENCES Matiere(idMatiere)
);
CREATE TABLE fichescolaire(
    idFicheScolaire INTEGER NOT NULL PRIMARY KEY,
    mois integer NOT NULL CHECK (mois<=12),
    anneeScolaire integer NOT NULL CHECK (anneeScolaire>0),
    idEtudiant int NOT NULL,
    idClasse int NOT NULL,
    numero int NOT NULL,
    CONSTRAINT FK_FicheScolaire_idEtudiant FOREIGN KEY(idEtudiant) REFERENCES Etudiant(idEtudiant),
    CONSTRAINT FK_FicheScolaire_idClasse FOREIGN KEY(idClasse) REFERENCES Classe(idClasse)
);

CREATE TABLE Paiement(
    idPaiement int NOT NULL,
    idFicheScolaire int NOT NULL,
    montant integer CHECK(montant>0),
    progression integer,
    CONSTRAINT fk_Paiement_idFicheScolaire FOREIGN KEY (idFicheScolaire) REFERENCES ficheScolaire(idFichescolaire)
);

CREATE TABLE Examen(
    idExamen int NOT NULL ,
    periode int NOT NULL,
    idClasse   int  NOT NULL  ,
    anneeScolaire int NOT NULL,
    CONSTRAINT PK_Examen_idExamen PRIMARY KEY (idExamen),
    CONSTRAINT FK_Examen_idClasse FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);

CREATE TABLE Bulletin(
    idBulletin INTEGER NOT NULL,
    idExamen INTEGER NOT NULL,
    idEtudiant INT NOT NULL,
    note int check (note>=0),
    CONSTRAINT PK_Bulletin_idBulletin PRIMARY KEY (idBulletin),
    CONSTRAINT FK_Bulletin_idExamen FOREIGN KEY (idExamen) REFERENCES Examen (idExamen),
    CONSTRAINT FK_Bulletin_idEtudiant FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant)
);

CREATE TABLE prof(
    idProf INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(30),
    mdp VARCHAR(30)
);

CREATE TABLE admin(
    idAdmin INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(30),
    mdp VARCHAR(30)
);



CREATE SEQUENCE classe_seq
    INCREMENT 1
    START 1
    MINVALUE 1;


CREATE SEQUENCE etudiant_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE examen_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE bulletin_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE etudiantlogin_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE paiement_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE matiere_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE ecolage_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE admin_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE prof_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

CREATE SEQUENCE public.fichierscolaire_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

INSERT INTO matiere(
	idmatiere, nommatiere, coefficient)
	VALUES (nextval('matiere_seq'),'Philo',6);

INSERT INTO classe values(nextval('classe_seq'),1,'SANDRATRA');

INSERT INTO admin(
	idadmin, login, mdp)
	VALUES (nextval('admin_seq'), 'admin@gmail.com','admin123!');


INSERT INTO prof(
	idprof, login, mdp)
	VALUES (nextval('prof_seq'), 'profadmin', 'prof123!');

INSERT INTO etudiant(
	idetudiant, nom, login, mdp)
	VALUES (nextval('etudiant_seq'), 'Hart', 'hart113@gmail.com', 'hart123!');

INSERT INTO etudiantlogin(
	idetudiantlogin, login, mdp, idetudiant)
	VALUES (nextval('etudiantlogin_seq'), 'Hart@gmail.com', 'hart123!',1);




INSERT INTO ecolage(
	idecolage, montant, progression, idetudiant, idclasse)
	VALUES (nextval('ecolage_Seq'), 30000, 200, 1,2);

INSERT INTO examen(
	idexamen, periode, idclasse, anneescolaire)
	VALUES (nextval('examen_seq'), 3,1,2022);

INSERT INTO bulletin(
	idbulletin, idExamen, idetudiant, note)
	VALUES (nextval('bulletin_seq'), 1, 1, 12);

INSERT INTO fichescolaire(
	idfichescolaire, mois, anneescolaire, idetudiant, idclasse, numero)
	VALUES (nextval('fichierscolaire_seq'),3,2022,1, 1, 1);
   
INSERT INTO Paiement(
	idpaiement, idfichescolaire, montant, progression)
	VALUES (nextval('paiement_seq'), 1, 3000, 200);

INSERT INTO prof(
	idprof, login, mdp)
	VALUES (nextval('prof_seq'),'Lehibe@gmail.com','prof123!');

