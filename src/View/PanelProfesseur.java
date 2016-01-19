/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Professeur;
import Model.Qcm;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import javafx.scene.layout.Border;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author toshiba
 */
public class PanelProfesseur extends JPanel{
    
    private JFrame frame;
    
    private JPanel actif;
    private ArrayList<JLabel> nom_qcm;
    private ArrayList<JButton> note;
    private JButton creer;
    private JButton gerer;
    private ArrayList<JButton> modifier;
    private ArrayList<JButton> supprimer;
    
    private Professeur pers;

    public JLabel l_test;

    private JTextField infos;

    
    
    public PanelProfesseur(Professeur pers, JFrame frame){
        this.frame = frame;
        this.pers=pers;
        
        l_test = new JLabel("  " + pers.getNom() + "   " + pers.getPrenom() + "  ");

        //Cont  rainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        global.gridx = global.gridy = 0;
        
        init();
        
        this.add(creer, global);
        global.gridx = 1;
        this.add(gerer, global);
        global.gridx++;
        this.add(l_test, global);
        global.gridx = 0;
        
        global.gridy = 1;
        global.gridwidth = 3;
        this.add(actif, global);
        
        
        
        //this.add(l_test);
        this.setVisible(true);
    }
    
    public void init(){
        //Allocation de la mémoire
            nom_qcm = new ArrayList();
            note = new ArrayList();
            modifier = new ArrayList();
            supprimer = new ArrayList();
            
            actif = new JPanel();
            actif.setVisible(true);
            actif.setPreferredSize(new Dimension(1200, 500));
            
            creer = new JButton("Nouveau QCM");
            gerer = new JButton("Gérer les QCM");
            
            creer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    actif.removeAll();
                    actif.add(new PanelCreationQcm(frame, pers));
                    actif.validate();
                }
            });
    }
    
    public void update(){
        actif.removeAll();
        
        actif.validate();
    }

}
