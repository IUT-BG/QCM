/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controleur.EffectuerQcm;
import Controleur.VerifReponse;
import Model.Classe;
import Model.Connexion;
import Model.Etudiant;
import Model.Note;
import Model.Qcm;
import Model.Question;
import Model.Reponse;
import Test.TestQcm;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author toshiba
 */
public class PanelEtudiant extends JPanel {

    //maj QCM eleve
    Frame parentFrame;
    JPanel affiche_qcm;
    JPanel liste_qcm;
    JList liste_qcm_etu;
    Etudiant etu;
    TestQcm test;
    ArrayList<Question> liste_question;
    ArrayList<JRadioButton> liste_radio;
    JScrollPane jsp;
    FenetrePrincipal fen;

    public PanelEtudiant(FenetrePrincipal fp) {

        initialisation();
        affQcm();
        ajoutListe();
        

    }

    public void initialisation() {

        Window parentWindow = SwingUtilities.windowForComponent(this);
        parentFrame = null;
        if (parentWindow instanceof Frame) {
            parentFrame = (Frame) parentWindow;
        }

        /* ATTENTION
         APRES LES TEST : ne pas oublier de changer cette ligne et celle de setQcm test qcm
         */
        test = new TestQcm();
        Classe t_classe = new Classe();
        t_classe.setNum("2nd2");

        affiche_qcm = new JPanel();

        etu = new Etudiant(t_classe, "Magand", "Louis", 1);
        etu.setQcm(test.getQcm());

        liste_question = new ArrayList();
        liste_radio = new ArrayList();

        //GridBag
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //intialisation boutton
        JButton bt = new JButton("Notes");
        c.anchor = GridBagConstraints.WEST;
        this.add(bt, c);

        JTextArea txt = new JTextArea(etu.getNom() + " " + etu.getPrenom() + " "
                + etu.getClasse().getNum());

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        this.add(txt, c);

        //Placement du panel ListeQcmEtu
        liste_qcm = new JPanel();

        TitledBorder bord = BorderFactory.createTitledBorder("Liste");

        liste_qcm_etu = new JList(new DefaultListModel());

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;

        this.add(liste_qcm, c);

        //Placement du scroll dans le panneau ListeQcmEtu
        liste_qcm.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        jsp = new JScrollPane(liste_qcm_etu);
        c2.fill = GridBagConstraints.BOTH;
        c2.gridy = 0;
        c2.gridx = 0;
        c2.weighty = 10;

        liste_qcm.add(jsp, c2);
        liste_qcm.setBorder(bord);

        //Placement du panel avec Qcm actuel
        c.gridy = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.weightx = 2;
        c.weighty = 2;
        c.fill = GridBagConstraints.BOTH;

        bord = BorderFactory.createTitledBorder("Qcm");

        jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBorder(bord);

        jsp.setViewportView(affiche_qcm);
        jsp.setColumnHeader(null);
        this.add(jsp, c);

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 0;
        //ActionListeners
        JButton bt_Effectuer = new JButton("Effectuer");
        bt_Effectuer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EffectuerQcm x = new EffectuerQcm((Qcm) liste_qcm_etu.getSelectedValue(), etu);
                if (etu.getQcm() == (Qcm) liste_qcm_etu.getSelectedValue()) {
                    affQcm();
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Lol. Fini ton qcm coquin.");
                }
            }

        });
        bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== bt){
                    VisuNotes notes = new VisuNotes();
                    
                }
            }
        });
        this.add(bt_Effectuer, c);

    }

    public void ajoutListe() {
        ((DefaultListModel) liste_qcm_etu.getModel()).removeAllElements();

        /*Fonction utile aprés quand on aura d'autre QCM a ajouter dans la liste
        
         for (Qcm qc : etu.getClasse().getListe_qcm()){
         ((DefaultListModel)liste_qcm_etu.getModel()).addElement(qc.getTitre());
        
            
         }*/
        ((DefaultListModel) liste_qcm_etu.getModel()).addElement("caca");
        ((DefaultListModel) liste_qcm_etu.getModel()).addElement(etu.getQcm().getTitre());
    }

    public void affQcm() {
        int i = 0;
        int re = 0;

        affiche_qcm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        for (Question q : etu.getQcm().getQ()) {
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth = 10;

            JLabel label_q = new JLabel("<HTML><u>" + q.getIntitule() + "</u></HTML>");
            affiche_qcm.add(label_q, c);

            liste_question.add(q);

            c.gridy++;

            for (Reponse r : q.getReponse()) {
                if (c.gridx == 2) {
                    c.gridx = 0;
                    c.gridy++;
                }

                c.ipady = 20;
                c.ipadx = 20;
                c.anchor = GridBagConstraints.WEST;
                c.gridwidth = 1;

                JLabel label_r = new JLabel(r.getIntitule());
                affiche_qcm.add(label_r, c);

                c.anchor = GridBagConstraints.WEST;

                c.gridx++;

                JRadioButton bt_r = new JRadioButton();
                affiche_qcm.add(bt_r, c);

                c.gridx++;
                c.ipadx = 0;
                liste_radio.add(bt_r);
            }
            c.gridy = c.gridy + 1;
            c.gridx = 0;
            i++;
            re++;
        }

        /* for (Question q : etu.getQcm().getQ()) {
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
         affiche_qcm.add(bt_r, c);

         c.gridx++;
         c.ipadx = 0;
         }
         liste_question.add(q);
         c.gridy = c.gridy + 1;
         c.gridx = 0;
         i++;
         }
        
         /*for (Question q : etu.getQcm().getQ()) {
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
         }*/
        System.out.println(i + " et " + re);
        c.gridy = (i + re) * 2;
        c.gridx = 0;

        JButton bt_valid = new JButton("Valider");
        bt_valid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int r = 0;

                VerifReponse v = new VerifReponse(liste_question, liste_radio, etu.getQcm());
                float final_note = v.note();
                System.out.println(final_note);

                if (v.getNonValide()) {
                    JOptionPane.showMessageDialog(parentFrame, "Veuillez completer le qcm en entier.");
                    return;
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Qcm validé.");
                    Note n = new Note(etu.getId(), final_note);
                    etu.getQcm().ajouterNote(n);
                    addNote(n);
                    etu.setQcm(null);//faire en sorte qu'il ne puisse pas reselectionner ce qcm

                    rafraichissement();
                }
            }

        });
        affiche_qcm.add(bt_valid, c);
    }

    public void rafraichissement() {
        if (etu.getQcm() == null) {
            affiche_qcm.removeAll();
            affiche_qcm.repaint();
            return;
        }
        affQcm();
    }

    public void selectQcm() {

    }

    public void addNote(Note n) {
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();

        ArrayList<String> liste = new ArrayList<String>();

        int resultSet = connexion.insert("INSERT INTO note (note, id_etu, id_qcm) VALUES "
                + "(" + n.getNote() + "," + etu.getId() + "," + etu.getQcm().getId() + ");");
        System.out.println("Nb de ligne affecté : "+resultSet);
    }
    public class VisuNotes extends JDialog{
                private ArrayList<JLabel> liste;
                
                public VisuNotes(){
                    super(fen,"notes de l'étudiant n°"+etu.getId(),true);
                    liste=new ArrayList<>();
                   
                    init();
                    this.pack();
                    this.setVisible(true);
                    
                  
                    
                }
                public void init(){
                    this.setLayout(new GridBagLayout());
                    this.setLocationRelativeTo(fen);
                    
                   
                    
                    for(int i = 0; i< etu.voirNotes().size(); i++){
                        liste.add(new JLabel());
                        liste.get(i).setText("Note n°"+(i+1)+": "+Float.toString(etu.voirNotes().get(i)));

                }
                    placement_notes();
                }
                
                /*public void remplir_liste( ArrayList<Float> lNotes){
                   JLabel lab =new JLabel();
                    for(int i = 0; i<lNotes.size(); i++){
                        liste.get(i).setText("Note n°"+(i+1)+": "+Float.toString(lNotes.get(i)));
                    }
                    
                    
                }*/
                
                public void placement_notes(){
                    GridBagConstraints cont = new GridBagConstraints();
                    for(int i=0; i<liste.size();i++){
                          cont.gridx=0;
                          cont.gridy = 1+i;
                          this.add(liste.get(i),cont);
                    }
                }
               
            }
}
