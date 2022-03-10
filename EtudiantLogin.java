/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import java.sql.*;

/**
 *
 * @author Hart
 */
public class EtudiantLogin {
    String login,mot_de_passe;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public int authentification(Connection con) throws SQLException{
        Statement stmt=con.createStatement();
        String sql="SELECGT * FROM EtudiantLogin where login='"+this.getLogin()+"' and mdp='"+this.getMot_de_passe() +"'";
        ResultSet RS=stmt.executeQuery(sql);
        int res=0;
        while (RS.next()){
            res=RS.getInt("idEtudiant");
      
        }
        RS.close();
        stmt.close();
        
        return res;//0 rah tsisy, idEtudiant sinon
    }
    
    
}
