/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Qcm;
import Model.Question;
import Model.Reponse;
import java.util.ArrayList;
import javax.swing.JRadioButton;

/**
 *
 * @author Louis
 */
public class ValidationQcm {
    
    ArrayList<JRadioButton> liste;
    Qcm qcm;
    
    public ValidationQcm(ArrayList<JRadioButton> liste, Qcm qcm){
        this.liste = liste;
        this.qcm = qcm;
        verification();
    }
    
    public int verification(){
        int question = 0;
        int reponse = 0;
        for(JRadioButton bt : liste){
            if( bt.isSelected() && qcm.getQ().get(question).getReponse().get(reponse).isValide() )
                bon++;
            question++;
            reponse = 0;
        }
        return 0;
    }
}
