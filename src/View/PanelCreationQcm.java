/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Connexion;
import Model.Qcm;
import Model.Question;
import Model.Reponse;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author toshiba
 */
public class PanelCreationQcm extends JPanel{
    
    private JPanel pan;
    
    private JTextField titre;
    private ArrayList<Question> question;
    private ArrayList<JTextField> question_intitule;
    private ArrayList<JTextField> reponse_intitule;
    private ArrayList<JPanel> question_panel;
    private int action;
    
    private JButton ajout_question;
    private ArrayList<JButton> ajout_reponse;
    private ArrayList<JRadioButton> ajout_radio;
    private int indice;
    
    private JButton valider;
    
    public PanelCreationQcm(){
        //Contrainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        
        //initialisation
        init(c);
        
        
        ajout_question.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                pan.removeAll();
                Question q = new Question();
                question.add(q);
                JButton aj = new JButton("+ Réponse");
                ajout_reponse.add(aj);
                
                JPanel Q = new JPanel();
                question_panel.add(Q);
                    
                affichage(c);
            }
            
        });
        //affichage 1er
        affichage(c);
        
    }
    
    public void init(GridBagConstraints c){
        this.pan = new JPanel();
        //init des ArrayList---------
        question = new ArrayList();
        question_intitule = new ArrayList();
        reponse_intitule = new ArrayList();
        ajout_reponse = new ArrayList();
        ajout_radio = new ArrayList();
        question_panel = new ArrayList();
        action = 0;
        //init des autres éléments
            //JTextField
        JPanel te = new JPanel();
        titre = new JTextField();
        titre.setPreferredSize(new Dimension(200, 20));
            //JButton
        ajout_question = new JButton("+ Question");
        valider = new JButton("Valider");
        //---------------------------
        te.add(new JLabel("Titre : "), c);
        c.gridx = 1;
        te.add(titre, c);
        c.gridx = 0;
        this.add(te);
        
    }
    
    public void affichage(GridBagConstraints c){
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        //GridBagConstraints cQ = new GridBagConstraints();
        
        question_intitule.clear();
        
        for(int i=0; i<question.size(); i++){
            JPanel panelQ = new JPanel();
            
            //titre
            JTextField t = new JTextField();
            t.setPreferredSize(new Dimension(200, 20));
            question_intitule.add(t);
            
            panelQ.add(new JLabel("Question" + (i+1) + " : "));
            panelQ.add(t);
            
            //reponsses
            question_panel.get(i).removeAll();
            
            question_panel.get(i).setLayout(new GridBagLayout());
            GridBagConstraints cR = new GridBagConstraints();
            cR.gridy = cR.gridx = 0;
                    
            for(int j=0; j<question.get(i).getReponse().size(); j++){
                
                question_panel.get(i).add(reponse_intitule.get(j), cR);
                
                cR.gridx = 1;
                question_panel.get(i).add(ajout_radio.get(j), cR);
                cR.gridx = 0;
                
                question_panel.get(i).validate();
                
                cR.gridy++;
            }
            
            panelQ.add(question_panel.get(i));
            panelQ.add(ajout_reponse.get(i));
            
            panelQ.revalidate();
            panelQ.repaint();
            
            
            pan.add(panelQ);
        }
        
        c.gridy ++ ;
        this.add(pan, c);
        //bouttons
        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        this.add(ajout_question, c);
        
        c.gridwidth = 1;
        c.gridy++;
        this.add(valider, c);
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
        
        
        //------------Action Liseners----------//////////
        for(indice=action; indice<ajout_reponse.size(); indice++){
            action ++ ;
            int var = indice;
            (ajout_reponse.get(indice)).addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    pan.removeAll();
                    JTextField rep = new JTextField();
                    rep.setPreferredSize(new Dimension(200, 20));
                    reponse_intitule.add(rep);
                    
                    ajout_radio.add(new JRadioButton("Juste"));
                    
                    //On rentre une reponse pour l'aspet graphique
                    Reponse lock = new Reponse();
                    question.get(var).setReponse(lock);
                    
                    affichage(c);
                }
            });
        }
        
        valider.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(titre.getText() != ""){
                        
                        int compteur_reponse = 0;
                        for(int i = 0; i<question.size(); i++){
                            for(int j=0; j<question.get(i).getReponse().size(); j++){
                                //On ajoute la question
                                question.get(i).getReponse().get(j).setIntitule(reponse_intitule.get(compteur_reponse).getText());
                                question.get(i).getReponse().get(j).setValide(ajout_radio.get(compteur_reponse).isSelected());
                                
                                compteur_reponse ++; 
                            }
                        }
                        
                        Qcm qcm = new Qcm(titre.getText(), 0, question);
                        qcm.publish();
                    }
                    else
                        System.err.println("Vous devez rentrer un titre  u QCM");
                }
            });
        /////////-------------------------//////////////////
        
    }
}
