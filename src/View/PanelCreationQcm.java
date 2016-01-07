/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Question;
import Model.Reponse;
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
    
    private JPanel pan;
    
    private JTextField titre;
    private ArrayList<Question> question;
    private ArrayList<JTextField> question_intitule;
    private ArrayList<JTextField> reponse_intitule;
    
    private JButton ajout_question;
    private ArrayList<JButton> ajout_reponse;
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
        //affichage 1er
        affichage(c);
        
        ajout_question.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                pan.removeAll();
                Question q = new Question();
                question.add(q);
                JButton aj = new JButton("+");
                ajout_reponse.add(aj);
                affichage(c);
            }
            
        });
        
    }
    
    public void init(GridBagConstraints c){
        this.pan = new JPanel();
        //init des ArrayList---------
        question = new ArrayList();
        question_intitule = new ArrayList();
        reponse_intitule = new ArrayList();
        ajout_reponse = new ArrayList();
        //init des autres éléments
            //JTextField
        titre = new JTextField();
        titre.setPreferredSize(new Dimension(200, 20));
            //JButton
        ajout_question = new JButton("+");
        valider = new JButton("Valider");
        //---------------------------
        this.add(new JLabel("Titre : "), c);
        c.gridx = 1;
        this.add(titre, c);
        c.gridx = 0;
    }
    
    public void affichage(GridBagConstraints c){
        pan.setLayout(new GridBagLayout());
        GridBagConstraints cQ = new GridBagConstraints();
        cQ.gridx = 0;
        cQ.gridy = 0;
        
        System.out.println(ajout_reponse.size());
        System.out.println(question.size());
        
        for(int i=0; i<question.size(); i++){
            //titre
            JTextField t = new JTextField();
            t.setPreferredSize(new Dimension(200, 20));
            question_intitule.add(t);
            cQ.gridy++;
            pan.add(new JLabel("Question" + i + " : "), cQ);
            cQ.gridx = 1;
            pan.add(t, cQ);
            cQ.gridx = 0;
            
            //reponsess
            for(int j=0; j<question.get(i).getReponse().size(); j++){
                cQ.gridy++;
                pan.add(reponse_intitule.get(j), cQ);
                
                JRadioButton radio = new JRadioButton("juste");
                cQ.gridx = 1;
                pan.add(radio, cQ);
                cQ.gridx = 0;
            }
            cQ.gridy++;
            pan.add(ajout_reponse.get(i), cQ);
            
            c.gridy++;
            
            this.add(pan, c);
        }
        
        //bouttons
        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        this.add(ajout_question, c);
        
        c.gridwidth = 1;
        c.gridy++;
        this.add(valider, c);
        
        this.setVisible(true);
        this.validate();
        
        //------------Action Liseners----------//////////
        for(indice=0; indice<ajout_reponse.size(); indice++){
            
            (ajout_reponse.get(indice)).addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    pan.removeAll();
                    JTextField rep = new JTextField();
                    rep.setPreferredSize(new Dimension(200, 20));
                    reponse_intitule.add(rep);
                    
                    //On rentre une reponse pour l'aspet graphique
                    Reponse lock = new Reponse();
                    question.get(indice-1).setReponse(lock);
                    
                    affichage(c);
                }
            });
        }
        /////////-------------------------//////////////////
    }
}
