/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class Professeur extends Personne{
    private ArrayList<Classe> liste_classe;

    public Professeur(String nom, String prenom, int id) {
        super(nom,prenom,id);
    }
    public ArrayList<Note> voirResultats(Qcm qcm){
        return qcm.getNote();
    }
    
    public ArrayList<String> voirQcm(){
        
            Connexion connexion = new Connexion("Z:\\Documents\\NetBeansProjects\\QCM\\QCM.sqlite");
            connexion.connect();
            
            ArrayList<String> liste = new ArrayList<String>();
            
            ResultSet resultSet = connexion.query("SELECT titre FROM Qcm WHERE "+Integer.toString(id)+"=id_prof;");
        try {
            while (resultSet.next()) {
                liste.add(resultSet.getString("titre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
        
    }
    
    public void afficheQcm(){
        ArrayList<String> liste = voirQcm();
        
        for(int i=0; i<liste.size(); i++)
            System.out.println(liste.get(i));
    }
    public String toString()
    {
        return id+" - "+prenom+" "+nom+", Prof";
    }
}  
