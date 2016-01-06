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
import javax.swing.JPanel;

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
        test = new TestQcm();
        
        etu = new Etudiant();
        etu.SetQcm(test.getQcm());
        
        JButton but = new JButton("test");
        this.add(but);
    }

        
}
