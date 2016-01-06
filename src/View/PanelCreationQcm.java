/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
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
public class PanelCreationQcm extends JPanel{
    
    private JTextField titre;
    private ArrayList<JTextField> question;
    private JTextField reponse_intitule;
    private ArrayList<JRadioButton> reponse_valide;
    
    private JButton ajout_question;
    private JButton ajout_reponse;
    
    private JButton valider;
    
    public PanelCreationQcm(){
        //initialisation
        init();
        //affichage 1er
        affichage();
        
        /*
        Logic:
            On créer une nouvelle question au clique sur le boutton "+"
            On vide les reponses et les radios buttons
            On ajoute l'ancienne question à la liste des question
        */
        
        
    }
    
    public void init(){
        //init des ArrayList---------
        question = new ArrayList();
        reponse_valide = new ArrayList();
        //init des autres éléments
            //JTextField
        titre = new JTextField();
        titre.setPreferredSize(new Dimension(200, 20));
        reponse_intitule = new JTextField();
            //JButton
        ajout_question = new JButton("+");
        ajout_reponse = new JButton("+");
        //---------------------------
    }
    
    public void affichage(){
        
        //Contrainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel("Titre : "));
        this.add(titre);
        
        for(int i=0; i<question.size(); i++){
            
        }
        
    }
}
