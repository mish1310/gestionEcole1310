/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.*;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Hart
 */
public class Etudiant {
    String nom,prenom,dateNaissance,sexe,matricule,adresse,inscription,idEtudiant;
    public String getIdEtudiant()throws SQLException,ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:8089/rh", "postgres", "root");
        String sql="SELECT * FROM Etudiant where matricule='"+this.getMatricule()+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        RS.next();
        String res=RS.getString(0);
        return res;
    }
    public void setIdEtudiant(String idEtudiant){
        this.idEtudiant=idEtudiant;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }
    public void inserer(Connection con) throws SQLException{
        String sql="INSERT INTO Etudiant values(nextval('seq'),'"+this.getNom()+"','"+this.getPrenom()+"','"+this.getDateNaissance() +"','"+this.getSexe()+"','"+this.getAdresse() +"','"+this.getMatricule()+"','"+this.getInscription()+"')";
        System.out.println(sql);
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }
    public void modifier(Connection con,String adresse)throws SQLException{
        String sql="UPDATE Etudiant set adresse='"+adresse+"' where matricule='"+this.getMatricule() +"'";
        System.out.println(sql);
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
        
    }
    public void supprimer(Connection con,String matricule)throws SQLException{
        String sql="DELETE FROM Etudiant where matricule='"+matricule+"'";
        System.out.println(sql);
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }
    public int calculAge(){
        Date date=new Date(this.getDateNaissance() );
        Date now=Date.from(Instant.now());
        long valDate=date.getTime();
        long valnow=now.getTime();
        long res=valnow-valDate;
        long annee=1000*60*60*24*365;
        long taona=res/annee;
        int res3=(int) taona;
        return res3;
    }
}
