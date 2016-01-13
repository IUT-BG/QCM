/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Etudiant;
import Model.Qcm;

/**
 *
 * @author Louis
 */
public class EffectuerQcm {
    
    Qcm qcm;
    Etudiant etu;
    
    public EffectuerQcm(Qcm qcm, Etudiant etu){
        this.qcm = qcm;
        this.etu = etu;
        test();
    }
    
    public void test(){
        if( etu.getQcm() != null )
            System.out.println("Veuillez terminer votre Qcm.");
        else if( qcm.getNote().get(1).)
    }
}
