/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
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
    
    private JTextField infos;
    
    
    public PanelProfesseur(){
        
        //Contrainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        
        
    }
    
} 
