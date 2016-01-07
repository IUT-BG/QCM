/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author toshiba
 */

public class Question {
    private int id;
    private String intitule;
    private ArrayList<Reponse> reponse;

    public Question() {
        this.intitule = null;
        this.reponse = new ArrayList();
    }
    
    
    public Question(String intitule, ArrayList<Reponse> reponse) {
        this.intitule = intitule;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public ArrayList<Reponse> getReponse() {
        return reponse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setReponse(Reponse reponse) {
        this.reponse.add(reponse);
    }
    
} 
