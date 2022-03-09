/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hart
 */
public class Paiement {
    FicheScolaire FS;
    int montant, progression;
    public void insertionPaiement(Connection con) throws SQLException, ClassNotFoundException{
        String sql="INSERT INTO Paiement values (nextval('paiement_seq'),'"+this.FS.getEtu().getIdEtudiant()+"')";
        Statement stmt=con.createStatement();
        stmt.executeQuery(sql);
        stmt.close();
    }

    public FicheScolaire getFS() {
        return FS;
    }

    public void setFS(FicheScolaire FS) {
        this.FS = FS;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }
    public int getProgression(Connection con,String mois,String anneeScolaire) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM Paiement join FicheScolaire on Paiement.idFicheScolaire=FicheScolaire.idFicheScolaire where idEtudiant='"+this.getFS().getEtu().getIdEtudiant()+"' and mois='"+mois+" and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        int res=0;
        while (RS.next()){
            res=RS.getInt("progression");
        }
        
        return res;
    }
    public List<Paiement> getPaiement(Connection con, String classe,String mois,String anneeScolaire) throws SQLException{
        ArrayList<Paiement> res=new ArrayList<Paiement>();
        String sql="SELECT * FROM Paiement join FicheScolaire on Paiement.idFicheScolaire=FicheScolaire.idFicheScolaire";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        while(RS.next()){
            Paiement temp=new Paiement();
            temp.setMontant(RS.getInt("montant"));
            temp.setProgression(RS.getInt("progression"));
            temp.setFS(new FicheScolaire());
            temp.getFS().setAnneeScolaire(RS.getString("anneeScolaire"));
            temp.getFS().setClasse(RS.getString("idClasse"));
            temp.getFS().setEtu(new Etudiant());
            temp.getFS().getEtu().setIdEtudiant(RS.getString("idEtudiant"));
            temp.getFS().setMois(RS.getString("mois"));
            temp.getFS().setNumero(RS.getInt("numero"));
            temp.getFS().setIdFicheScolaire(RS.getString(2) );
            res.add(temp);
           
        }
        
        
        return res;
    }
}
