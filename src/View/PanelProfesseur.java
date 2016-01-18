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
import javax.swing.event.ListSelectionEvent;
/**
 *
 * @author toshiba
 */
public class PanelProfesseur extends JPanel{
    
    private JButton note;
    private JButton creer;
    private JButton modifier;
    private JButton supprimer;
    private Professeur pers;
    
    public JLabel l_test;
    
    private JTextField infos;
    
    
    public PanelProfesseur(Professeur pers){
        this.pers=pers;
        l_test=new JLabel(pers.toString());
        
        //Cont  rainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        this.add(l_test);
        this.setVisible(true);
        
        
    }
    
} 
