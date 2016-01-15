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
    
    private ArrayList<JButton> note;
    private ArrayList<JButton> creer;
    private ArrayList<JButton> modifier;
    private ArrayList<JButton> supprimer;
    
    private ArrayList<JLabel> titre;
    
    
    public PanelProfesseur(){
        
        //Contrainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        
        note = new ArrayList<JButton>();
        creer = new ArrayList<JButton>();
        modifier = new ArrayList<JButton>();
        supprimer = new ArrayList<JButton>();
        titre = new ArrayList<JLabel>();
        
        Professeur p =new Professeur();
        
        ArrayList<String> liste = p.voirQcm();
        
        for(int i=0;i<liste.size(); i++){
            titre.add(new JLabel(liste.get(i)));
            note.add(new JButton("Notes"));
            creer.add(new JButton("Creer"));
            modifier.add(new JButton("Modifier"));
            supprimer.add(new JButton("Supprimer"));
            
            global.gridy = i;
            global.gridx = 0;
            this.add(titre.get(i), global);
            
            global.gridx++;
            this.add(note.get(i), global);
            
            global.gridx++;
            this.add(creer.get(i), global);
            
            global.gridx++;
            this.add(modifier.get(i), global);
            
            global.gridx++;
            this.add(supprimer.get(i), global);
        }
        
        
    }
    
} 
