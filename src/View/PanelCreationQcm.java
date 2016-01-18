/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Connexion;
import Model.Professeur;
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
import java.util.regex.Pattern;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String;

/**
 *
 * @author toshiba
 */
public class PanelCreationQcm extends JPanel{
    private Professeur prof;
    
    private JPanel pan;
    private JFrame frame;
    
    private JTextField titre;
    private ArrayList<Question> question;
    private ArrayList<JTextField> question_intitule;
    private ArrayList<JTextField> reponse_intitule;
    private ArrayList<JPanel> question_panel;
    private int action;
    
    private Reponse lock;
    JTextField rep;
    JRadioButton juste;
    
    private JButton ajout_question;
    private ArrayList<JButton> ajout_reponse;
    private ArrayList<JRadioButton> ajout_radio;
    private int reponse_cumule;
    private int indice;
    
    private JComboBox classes;
    
    private JButton valider;
    
    public PanelCreationQcm(JFrame frame, Professeur prof){
        this.frame = frame;
        this.prof = prof;
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
                
                
                JTextField t = new JTextField();
                t.setPreferredSize(new Dimension(200, 20));
                question_intitule.add(t);
                
                JButton aj = new JButton("+ Réponse");
                ajout_reponse.add(aj);
                
                JPanel Q = new JPanel();
                Q.setBorder(BorderFactory.createTitledBorder("Réponses question " + (question_panel.size()+1)));
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
        reponse_cumule = 0;
        action = 0;
        //init des autres éléments
            //JTextField
        JPanel te = new JPanel();
        titre = new JTextField();
        titre.setPreferredSize(new Dimension(200, 20));
            //JButton
        ajout_question = new JButton("+ Question");
        valider = new JButton("Valider");
            //JComboBox
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();
        ResultSet result_classes = connexion.query("SELECT classe FROM Personne WHERE id = "+ prof.getId());
        
        String[] s = null;
        try{
            s = (result_classes.getString("classe")).split("|");
        } catch (SQLException ex) {}
        
        classes = new JComboBox(s);
        
        //---------------------------
        te.add(new JLabel("Titre : "), c);
        c.gridx = 1;
        te.add(titre, c);
        c.gridx = 0;
        this.add(te);
        
        valider.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(!Pattern.matches("^$", titre.getText())){
                        boolean valide = true;
                        boolean une_reponse_juste = false;
                        int compteur_reponse = 0;
                        System.out.println(question.size());
                        for(int i = 0; i<question.size(); i++){
                            System.out.println("ok");
                            if(!Pattern.matches("^$", question_intitule.get(i).getText())){
                                
                                for(int j=0; j<question.get(i).getReponse().size(); j++){
                                    if(Pattern.matches("^$", question_intitule.get(i).getText())){
                                        question.get(i).getReponse().remove(compteur_reponse);
                                        reponse_intitule.remove(compteur_reponse);
                                    }
                                    else{
                                        if(ajout_radio.get(compteur_reponse).isSelected() == true)
                                            une_reponse_juste = true;
                                    }
                                    
                                    compteur_reponse ++;  
                                }
                                
                                if(question.get(i).getReponse().size() < 2)
                                    valide = false;
                                
                                if(!une_reponse_juste)
                                    valide = false;
                                
                                une_reponse_juste = false;  
                            }
                            else{
                                for(int k=0; k<question.get(i).getReponse().size(); k++){
                                    question.get(i).getReponse().remove(k);
                                    reponse_intitule.remove(compteur_reponse + k);
                                }
                                question.remove(i);
                                question_intitule.remove(i);
                                System.out.println(question.size());
                                i -= 1;
                                if(question.size() == 0)
                                    valide = false;
                            }
                        }
                        
                        
                        compteur_reponse = 0;
                        for(int i = 0; i<question.size(); i++){
                                question.get(i).setIntitule(question_intitule.get(i).getText());
                                
                                for(int j=0; j<question.get(i).getReponse().size(); j++){
                                    //On ajoute la question
                                    question.get(i).getReponse().get(j).setIntitule(reponse_intitule.get(compteur_reponse).getText());
                                    question.get(i).getReponse().get(j).setValide(ajout_radio.get(compteur_reponse).isSelected());

                                    compteur_reponse ++; 
                                }
                        }
                        
                        if(valide){
                            Qcm qcm = new Qcm(titre.getText(), prof, question, classes.getItemAt(classes.getSelectedIndex()).toString());
                            qcm.publish();
                        }
                        else
                            JOptionPane.showMessageDialog(frame,"Pour que le QCM soit validé il doit respécter différentes règle :\n"
                                                                + "- Chaque question doit avoir au moins 2 réponse\n"
                                                                + "- Chaque question doit avoir au moins 1 réponse juste\n"
                                                                + "- Le QCM doit avoir au moins une question\n",
                                    "Inane warning",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(frame,"Vous devez rentrer un titre au QCM !","Inane warning",JOptionPane.WARNING_MESSAGE);
                }
            });
        
    }
    
    public void affichage(GridBagConstraints c){
        
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        
        for(int i=0; i<question.size(); i++){
            JPanel panelQ = new JPanel();
            
            //titre
            
            panelQ.add(new JLabel("Question" + (i+1) + " : "));
            panelQ.add(question_intitule.get(i));
            
            //reponsses
            
            question_panel.get(i).setLayout(new GridBagLayout());
            GridBagConstraints cR = new GridBagConstraints();
            cR.gridy = cR.gridx = 0;
            
            for(int j=0; j<question.get(i).getReponse().size(); j++){
               
                cR.gridy = j;
                question_panel.get(i).add(reponse_intitule.get(reponse_cumule), cR);
                
                cR.gridx = 1;
                question_panel.get(i).add(ajout_radio.get(reponse_cumule), cR);
                cR.gridx = 0;
                
                reponse_cumule ++;
            }
            panelQ.add(question_panel.get(i));
            panelQ.add(ajout_reponse.get(i));
            
            //panelQ.repaint();
            
            
            pan.add(panelQ);
        }
        reponse_cumule = 0;
        
        
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
        
        this.validate();
        this.setVisible(true);
       
        
        //------------Action Liseners----------//////////
        for(indice=action; indice<ajout_reponse.size(); indice++){
            action ++ ;
            int var = indice;
            (ajout_reponse.get(indice)).addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    lock = new Reponse();
                    rep = new JTextField();
                    juste = new JRadioButton("Juste");
                    
                    pan.removeAll();
                    rep.setPreferredSize(new Dimension(200, 20));
                    reponse_intitule.add(rep);
                    
                    ajout_radio.add(juste);
                    //On rentre une reponse pour l'aspet graphique
                    
                    question.get(var).setReponse(lock);
                    
                    affichage(c);
                }
            });
        }
        /////////-------------------------//////////////////

    }
}
