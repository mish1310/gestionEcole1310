import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Ecolage{
    Integer idEcolage,montant,progression,idEtudiant,idClasse;

    public Integer getIdEcolage() {
        return idEcolage;
    }

    public void setIdEcolage(Integer idEcolage) {
        this.idEcolage = idEcolage;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Integer getProgression() {
        return progression;
    }

    public void setProgression(Integer progression) {
        this.progression = progression;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }
    public void insertEcolage(Connection con) throws SQLException{
        String sql="INSERT INTO Ecolage values ('','"+this.montant.toString()+"','"+this.progression+"','"+this.idEtudiant+"','"+this.idClasse+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }
    public static List<Ecolage> getAllEcolage(Connection con) throws SQLException{
        String sql="SELECT * FROM Ecolage";
        Statement stmt = con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        List<Ecolage> result = new ArrayList<Ecolage>();
        while(RS.next()){
            Ecolage temp=new Ecolage();
            temp.setIdEcolage(RS.getInt(1));
            temp.setMontant(RS.getInt(2));
            temp.setProgression(RS.getInt(3));
            temp.setIdEtudiant(RS.getInt(4));
            temp.setIdClasse(RS.getInt(5));
            result.add(temp);
        }
        return result;
    }
    public double verifProgressionEcolage(Connection con) throws SQLException{
        String sql="SELECT progression FROM Ecolage WHERE idEcolage="+this.getIdEcolage();
        Statement stmt=con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        RS.next();
        double res=RS.getDouble(1);

        return res;
    }
    public Ecolage[] getTriEcolage(Connection con) throws SQLException{
        String sql="SELECT * FROM Ecolage where progression='"+this.getProgression()+"'";
        Statement stmt = con.createStatement();
        ResultSet RS=stmt.executeQuery(sql);
        List<Ecolage> result = new ArrayList<Ecolage>();
        while(RS.next()){
            Ecolage temp=new Ecolage();
            temp.setIdEcolage(RS.getInt(1));
            temp.setMontant(RS.getInt(2));
            temp.setProgression(RS.getInt(3));
            temp.setIdEtudiant(RS.getInt(4));
            temp.setIdClasse(RS.getInt(5));
            result.add(temp);
        }
        Object[] res=result.toArray();
        Ecolage[] lisRs=new Ecolage[res.length];
        for (int i = 0; i < lisRs.length; i++) {
            lisRs[i]=(Ecolage)res[i];
        }
        return lisRs;
    }
}