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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author toshiba
 */
public class PanelEtudiant extends JPanel {

    //maj QCM eleve
    JPanel affiche_qcm;
    Qcm qcm;
    Etudiant etu;
    TestQcm test;
    ArrayList<JRadioButton> liste_radio;
    JScrollPane jsp;

    public PanelEtudiant() {
        
        initialisation();
        affQcm();
        
        
        
    }

    public void initialisation(){
        //first panel with the actual QCM
        TitledBorder bord = BorderFactory.createTitledBorder("Qcm");

        jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBorder(bord);
        jsp.setPreferredSize(new Dimension(600, 400));

        test = new TestQcm();
        
        affiche_qcm = new JPanel();

        etu = new Etudiant();
        etu.setQcm(test.getQcm());

        liste_radio = new ArrayList();
        
        jsp.setViewportView(affiche_qcm);
        this.add(jsp);
        
        //second panel with list of QCM
    }
    
    public void affQcm() {
        int i = 0;

        affiche_qcm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 6;
        c.gridx = 0;
        c.gridy = 0;

        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                //c.ipady= 20;
                c.ipadx = 20;
                c.anchor = GridBagConstraints.WEST;
                c.gridwidth = 1;

                JLabel label_r = new JLabel(r.getIntitule());
                affiche_qcm.add(label_r, c);

                c.anchor = GridBagConstraints.WEST;
                c.gridx++;

                JRadioButton bt_r = new JRadioButton();
                liste_radio.add(bt_r);
                affiche_qcm.add(bt_r, c);

                c.gridx++;
                c.ipadx = 0;
            }
            c.gridy = c.gridy + 1;
            c.gridx = 0;
            i++;
        }
        c.gridy = i * 2;
        c.gridx = 0;

        JButton bt_valid = new JButton("Valider");
        affiche_qcm.add(bt_valid, c);
    }
}
