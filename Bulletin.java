/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author Hart
 */
public class Bulletin {
    Etudiant etu;
    Examen exam;
    String idBulletin;

    public String getIdBulletin() {
        return idBulletin;
    }

    public void setIdBulletin(String idBulletin) {
        this.idBulletin = idBulletin;
    }
    int note;

    public Etudiant getEtu() {
        return etu;
    }

    public void setEtu(Etudiant etu) {
        this.etu = etu;
    }

    public Examen getExam() {
        return exam;
    }

    public void setExam(Examen exam) {
        this.exam = exam;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    public void insererDemande(Connection con) throws SQLException, ClassNotFoundException{
        String sql="INSERT INTO Bulletin values (nextval('bulletin_seq'),'"+exam+"','"+this.getEtu().getIdEtudiant() +"','"+this.getNote()+"')";
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
        
    }
    public void accepterDemande(Connection con) throws SQLException, ClassNotFoundException{
        /*String sql="INSERT INTO Bulletin values (nextval('bulletin_seq'),'"+exam+"','"+this.getEtu().getIdEtudiant() +"','"+this.getNote()+"')";
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();*/
        
    }
    public String getMention(int note,int coeff){
        String res="";
        if(note<10)
            res="echec";
        else if(note<12){
            res="passable";
        }
        else if(note<14){
            res="assez bien";
        }
        else if(note<16){
            res="bien";
        }
        else {
            res="tres bien";
        }
        return res;
    }
    public double moyenne(Connection con, String periode,String anneeScolaire) throws SQLException{
        String sql="SELECT * FROM Bulletin join Examen on Examen.idExamen=Bulletin.idExamen where periode='"+periode+"' and anneeScolaire='"+anneeScolaire+"'";
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        double res=0;
        int count=0;
        while(RS.next()){
            res+=RS.getInt("note");
            count++;
        }
        
        
        return res/(double)count;
    }
    
    public void modifier(Connection con, int note, int coeff) throws SQLException, ClassNotFoundException{
        String sql="UPDATE Bulletin set note='"+note+"' where idExamen='"+this.getExam().getIdExamen() +"' and idEtudiant='"+this.getEtu().getIdEtudiant()+"'";
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
        String sql2="SELECT idClasse FROM Examen where idExamen='"+this.getIdBulletin() +"'";
        stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql2);
        RS.next();
        String idCla=RS.getString("idClasse");
        RS.close();
        stmt.close();
        stmt=con.createStatement();
        String sql3="SELECT idMatiere from classe where idClasse='"+idCla+"'";
        ResultSet RS2=stmt.executeQuery(sql3);
        RS2.next();
        String idMat=RS.getString("idMatiere");
        RS2.close();
        stmt.close();
        stmt=con.createStatement();
        String sqlUpdate="update matiere set coefficient='"+coeff+"' where idMatiere='"+idMat+"'";
        stmt.executeUpdate(sqlUpdate);
    }
}
