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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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
    JPanel liste_qcm;
    JList liste_qcm_etu;
    Etudiant etu;
    TestQcm test;
    ArrayList<JRadioButton> liste_radio;
    JScrollPane jsp;

    public PanelEtudiant() {
        
        initialisation();
        affQcm();
        ajoutListe();
        
        
    }

    public void initialisation(){
        //GridBag
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //second panel with list of QCM
        liste_qcm = new JPanel();
        //Placement scroll dans le panel dans le panel
        
        TitledBorder bord = BorderFactory.createTitledBorder("Liste Qcm");
        
        liste_qcm_etu = new JList(new DefaultListModel());
        
        jsp = new JScrollPane(liste_qcm_etu);
        
        c.fill = GridBagConstraints.BOTH;
        //c.ipady = 120;
        c.gridy = 0;
        c.gridx = 0;
        c.weighty = 10;
        c.weightx = 100;
        
        liste_qcm.setBorder(bord);
        liste_qcm.add(jsp,c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 0;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        
        this.add(liste_qcm, c);
        
        //first panel with the actual QCM
        c.gridheight=1;
        c.gridx=1;
        c.weightx = 2;
        c.weighty = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        
         bord = BorderFactory.createTitledBorder("Qcm");

        jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBorder(bord);

        
        /* ATTENTION
        APRES LES TEST : ne pas oublier de changer cette ligne et celle de setQcm test qcm
        */
        test = new TestQcm();
        
        affiche_qcm = new JPanel();

        etu = new Etudiant();
        etu.setQcm(test.getQcm());

        liste_radio = new ArrayList();
        
        jsp.setViewportView(affiche_qcm);
        this.add(jsp, c);
    }
    
    public void ajoutListe(){
        ((DefaultListModel)liste_qcm_etu.getModel()).removeAllElements();
        
        /*for (Qcm qc : etu.getClasse().getListe_qcm()){
            ((DefaultListModel)liste_qcm_etu.getModel()).addElement(qc.getTitre());
        
            
        }*/
        ((DefaultListModel)liste_qcm_etu.getModel()).addElement("caca");
        ((DefaultListModel)liste_qcm_etu.getModel()).addElement(etu.getQcm().getTitre());
    }
    
    public void affQcm() {
        int i = 0;

        affiche_qcm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
        
        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
        
        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
        
        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
        
        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
        
        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            c.gridy++;

            for (Reponse r : q.getReponse()) {

                c.ipady= 20;
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
