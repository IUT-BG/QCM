/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author toshiba
 */
public class Reponse {
    private String intitule;
    private boolean valide;

    public Reponse(String intitule, boolean valide) {
        this.intitule = intitule;
        this.valide = valide;
    } 

    public String getIntitule() {
        return intitule;
    }

    public boolean isValide() {
        return valide;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
    
    
}
