/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Etudiant;
import Model.Qcm;
import Model.Question;
import Model.Reponse;
import Test.TestQcm;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author toshiba
 */
public class PanelEtudiant extends JPanel {
    //maj QCM eleve
    Qcm qcm;
    Etudiant etu;
    TestQcm test;

    public PanelEtudiant() {
        int i =0;
        test = new TestQcm();
        
        etu = new Etudiant();
        etu.setQcm(test.getQcm());
        
        for(Question q : etu.getQcm().getQ()){
            JLabel label_q = new JLabel(q.getIntitule());
            this.add(label_q);
            for(Reponse r : q.getReponse()){
                JLabel label_r = new JLabel(r.getIntitule());
                this.add(label_r);
                JRadioButton bt_r = new JRadioButton();
                this.add(bt_r);
            }
            i++;
        }
    }

        
}
