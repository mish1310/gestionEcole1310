import java.sql.*;
import java.util.Vector;
/**
 * Etudiant
 */
public class Etudiant {
    Integer idEtudiant;
    String nom,login,mdp;
    public Integer getIdEtudiant() {
        return idEtudiant;
    }
    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public /*void*/ int authentification(Connection con) throws SQLException{
        int res=0;
        String sql="SELECT * FROM Etudiant where login="+this.login+" and mdp="+this.mdp;
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        // Vector<Etudiant> res2=new Vector<Etudiant>();
        rs.next();
        String mdp=rs.getString(4);

        if (mdp.equals(this.getMdp())) {
            res= 1;
        }
        else { res=0; }
        rs.close();
        stmt.close();

        return res;
        // return idEtudiant;
    }
}