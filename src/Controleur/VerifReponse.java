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
public class VerifReponse {

    ArrayList<Reponse> liste_reponse;
    ArrayList<JRadioButton> liste_radio;
    Qcm qcm;

    public VerifReponse(ArrayList<Reponse> liste_reponse,
            ArrayList<JRadioButton> liste_radio, Qcm qcm) {
        this.liste_reponse = liste_reponse;
        this.liste_radio = liste_radio;
        this.qcm = qcm;
    }

    public float verification() {
        int question = 0;
        int reponse = 0;
        float note = 0;
        for (Reponse r : liste_reponse) {
            if (r.isValide() && liste_radio.get(reponse).isSelected()) {
                note++;
            } else if (!r.isValide() && liste_radio.get(reponse).isSelected()) {
                note--;
            }
            reponse++;
        }
        return note;
    }

    public float note() {
        int r = 0;
        float score = verification();
        for (Reponse repon : liste_reponse) {
            if (repon.isValide()) {
                r++;
            }
        }
        if(score/r*20 < 0)
            return 0;
        return score/r*20;
    }
}
