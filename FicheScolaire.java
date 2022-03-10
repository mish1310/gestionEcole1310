/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Hart
 */
public class FicheScolaire {
    Etudiant etu;
    String anneeScolaire,mois,classe,idFicheScolaire;

    public String getIdFicheScolaire() {
        return idFicheScolaire;
    }

    public void setIdFicheScolaire(String idFicheScolaire) {
        this.idFicheScolaire = idFicheScolaire;
    }
    int numero;

    public Etudiant getEtu() {
        return etu;
    }

    public void setEtu(Etudiant etu) {
        this.etu = etu;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void assigner(Connection con) throws SQLException, ClassNotFoundException{
        String sql="INSERT INTO FicheScolaire values (nextval(),'"+this.getMois()+"','"+this.getAnneeScolaire()+"','"+this.getEtu().getIdEtudiant() +"','"+this.getClasse() +"','"+this.getNumero() +"')";
        Statement stmt=con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
    public int estAssigner(Connection con, String matricule,String anneeScolaire) throws SQLException{
        String sql="SELECT * FROM FicheScolaire join Etudiant on Etudiant.idEtudiant=FicheScolaire.idEtudiant where matricule='"+matricule+"' and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        int count=0;
        int res=0;
        while(RS.next()){
            res=RS.getInt("idFicheScolaire");
            count++;
        }
        return res;
    }
    public List<FicheScolaire> rechercherEtu(Connection con,String classe, int agemin,int agemax,String sexe,String anneeScolaire) throws SQLException{
        String sql="SELECT * FROM FicheScolaire join Etudianrt on Etudiant.idEtudiant=FicheScolaire.idEtudiant where idClasse='"+classe+"' and sexe='"+sexe+"'" ;
        sql+=" and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        ArrayList<FicheScolaire> res=new ArrayList<FicheScolaire>();
        while(RS.next()){
            FicheScolaire temp=new FicheScolaire();
            temp.setAnneeScolaire(RS.getString("anneeScolaire"));
            temp.setMois(RS.getString("mois"));
            temp.setClasse(RS.getString("idClasse"));
            temp.setNumero(RS.getInt("numero"));
            temp.setEtu(new Etudiant());
            temp.getEtu().setDateNaissance(RS.getString("dateNaissance"));
            temp.getEtu().setAdresse(RS.getString("adresse"));
            temp.getEtu().setInscription(RS.getString("inscription"));
            temp.getEtu().setMatricule(RS.getString("matricule"));
            temp.getEtu().setNom(RS.getString("nom"));
            temp.getEtu().setPrenom(RS.getString("prenom"));
            temp.getEtu().setSexe(RS.getString("sexe"));
            int age=temp.getEtu().calculAge();
            if(agemin<age && agemax>age){
                res.add(temp);
            }
        }
        stmt.close();
        RS.close();
        return res;
    }
    public List<FicheScolaire> rechercherAssigner(Connection con,String matricule,String anneescolaire) throws SQLException{
        String sql="SELECT * FROM FicheScolaire join Etudianrt on Etudiant.idEtudiant=FicheScolaire.idEtudiant where matricule='"+matricule+"' and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        ArrayList<FicheScolaire> res=new ArrayList<FicheScolaire>();
        while(RS.next()){
            FicheScolaire temp=new FicheScolaire();
            temp.setAnneeScolaire(RS.getString("anneeScolaire"));
            temp.setMois(RS.getString("mois"));
            temp.setClasse(RS.getString("idClasse"));
            temp.setNumero(RS.getInt("numero"));
            temp.setEtu(new Etudiant());
            temp.getEtu().setDateNaissance(RS.getString("dateNaissance"));
            temp.getEtu().setAdresse(RS.getString("adresse"));
            temp.getEtu().setInscription(RS.getString("inscription"));
            temp.getEtu().setMatricule(RS.getString("matricule"));
            temp.getEtu().setNom(RS.getString("nom"));
            temp.getEtu().setPrenom(RS.getString("prenom"));
            temp.getEtu().setSexe(RS.getString("sexe"));
            res.add(temp);
        }
        
        return null;
    
    }
}
