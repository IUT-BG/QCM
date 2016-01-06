/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author toshiba
 */
public class Professeur extends Personne{
    private ArrayList<Classe> liste_classe;
    
    public void voirResultats(Qcm qcm){
        
    }
    
    public void voirQcm(){
        Connexion connexion = new Connexion("Z:\\Java2a\\TP1\\statistics.sqlite");
        connexion.connect();
        
        ResultSet resultSet = connexion.query("SELECT * FROM WORLDCUP;");
    }
}  
