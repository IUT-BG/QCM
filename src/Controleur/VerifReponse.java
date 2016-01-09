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

    ArrayList<Question> liste_question;
    ArrayList<JRadioButton> liste_radio;
    ArrayList<Reponse> liste_reponse;
    boolean question_manquante;
    Qcm qcm;

    public VerifReponse(ArrayList<Question> liste_question,
            ArrayList<JRadioButton> liste_radio, Qcm qcm) {
        this.liste_question = liste_question;
        this.liste_radio = liste_radio;
        this.qcm = qcm;
        this.liste_reponse = new ArrayList();
        question_manquante = false;
    }

    public float verification() {
        int question = 0;
        int reponse = 0;
        float note = 0;
        int case_vide = 0;
        for (Question q : liste_question) {
            for (Reponse r : liste_question.get(question).getReponse()) {
                if (r.isValide() && liste_radio.get(reponse).isSelected()) {
                    note++;
                } else if (!r.isValide() && liste_radio.get(reponse).isSelected()) {
                    note--;
                }
                else if(!liste_radio.get(reponse).isSelected()){
                    case_vide++;
                }
                liste_reponse.add(r);
                reponse++;
            }
            if(case_vide == liste_question.get(question).getReponse().size()){
                question_manquante = true;
            }
            case_vide = 0;
            question++;
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
        if (score / r * 20 < 0) {
            return 0;
        }
        return score / r * 20;
    }
    
    public boolean getNonValide(){
        return question_manquante;
    }
}
