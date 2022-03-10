/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Hart
 */
public class FicheEcolage {
    String classe,mois,anneeScolaire;
    int montant;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    public int insertionEcolage(Connection con) throws SQLException{
       // String sql1="SELECT * FROM Classe where nomCLasse='"+this.getClasse() +"'";
        Statement stmt=con.createStatement();
        String sql="INSERT INTO Ecolage (idEcolage,idClasse,montant,mois,anneeScolaire) VALUES (nextval(''),'"+this.getClasse()+"','"+this.getMontant() +"','"+this.getMois() +"','"+this.getAnneeScolaire() +"')";
        //TODO: Fill sequence
        int res=stmt.executeUpdate(sql);
        stmt.close();
        return res;
    }
    public static FicheEcolage getEcolage(Connection con,String mois,String anneeScolaire) throws SQLException { 
        String sql="SELECT * FROM Ecolage where mois='"+mois+"' and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        Vector res=new Vector();
        while(RS.next()){
            FicheEcolage temp=new FicheEcolage();
            temp.setClasse(RS.getString("idClasse") );
            temp.setAnneeScolaire(RS.getString("anneeScolaire"));
            temp.setMontant(RS.getInt("montant") );
            temp.setMois(RS.getString("mois"));
            res.add(temp);
        }
        FicheEcolage valiny=(FicheEcolage)res.elementAt(res.size()-1);
        
        return valiny;
    }
}
