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
import Model.Professeur;
import Model.Qcm;
import Model.Question;
import Model.Reponse;
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
    ArrayList<Question> liste_question;
    ArrayList<JRadioButton> liste_radio;
    JScrollPane jsp;
    Connexion co;

    public PanelEtudiant() {
        initialisation_bd();
        initialisation();
        affQcm();
        ajoutListe();

    }

    public void initialisation_bd() {
        //peut etre mettre dans etudiant et mettre load classe dans classe
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();

        Connexion connexion_quest = new Connexion("QCM.sqlite");
        connexion_quest.connect();

        Connexion connexion_rep = new Connexion("QCM.sqlite");
        connexion_rep.connect();
        //faire l'identification etu
        /* etu, etu.setQcm,
         test = qcm actuel
         classe
         */
        ResultSet resultSet = connexion.query("SELECT nom, prenom, id "
                + "FROM Personne WHERE id='1'");
        try {
            etu = new Etudiant(resultSet.getString("nom"),
                    resultSet.getString("prenom"), resultSet.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultSet = connexion.query("SELECT intitule"
                + " FROM Classe "
                + "INNER JOIN Personne "
                + "WHERE Personne.id = " + etu.getId()
                + " AND Classe.intitule = Personne.classe");
        try {
            Classe classe = new Classe(resultSet.getString("intitule"));
            ResultSet resultSet_qcm = connexion.query("SELECT Qcm.titre, Qcm.id_prof, Qcm.id"
                    + " FROM Qcm INNER JOIN Classe"
                    + " WHERE Classe.intitule = '" + classe.getNom()
                    + "' AND Classe.intitule = Qcm.access ORDER BY Qcm.id ASC ");
            System.out.println(resultSet_qcm);

            ArrayList<Reponse> liste_rep = new ArrayList();
            ArrayList<Qcm> liste_q = new ArrayList();
            ArrayList<Question> liste_quest = new ArrayList();

            while (resultSet_qcm.next()) {
                System.out.println("On fait le QCM avec un id : " + resultSet.getString("id"));
                ResultSet resultSet_question = connexion_quest.query("SELECT q.intitule, q.id FROM Question q WHERE q.id_qcm =" + resultSet.getString("id") + " ORDER BY q.id ASC");
                liste_quest = new ArrayList();
                while (resultSet_question.next()) {
                    ResultSet resultSet_reponse = connexion_rep.query("SELECT r.intitule, r.valide FROM Reponse r WHERE id_question =" + resultSet_question.getString("id") + " ORDER BY r.id ASC");
                    liste_rep = new ArrayList();
                    while (resultSet_reponse.next()) {
                        liste_rep.add(new Reponse(resultSet_reponse.getString("intitule"), resultSet_reponse.getBoolean("valide")));
                        System.out.println("ajout HELLO : " + resultSet_reponse.getString("intitule"));
                        System.out.println(" valide ? : " + resultSet_reponse.getBoolean("valide"));
                        //remplir a liste de rep
                    }
                    liste_quest.add(new Question(resultSet_question.getString("intitule"), liste_rep));
                    System.out.println("ajout question : " + resultSet_question.getString("intitule"));
                    //remplir la liste de questions
                }
                //remplir la liste de qcm
                liste_q.add(new Qcm(resultSet.getString("titre"), resultSet.getInt("id_prof"), liste_quest, resultSet.getInt("id")));
                ResultSet resultSet_note = connexion_quest.query("SELECT n.note, n.id_etu, n.id_qcm FROM Note n WHERE n.id_qcm ="
                        + resultSet.getString("id") + " AND n.id_etu =" + etu.getId() + " ORDER BY n.id ASC");
                while (resultSet_note.next()) {
                    for (Qcm q : liste_q) {
                        if (q.getId() == resultSet_note.getInt("id_qcm")) {
                            q.ajouterNote(new Note(resultSet_note.getInt("note"), resultSet_note.getInt("id_etu")));
                            System.out.println("Note add pour le qcm : " + q.getId());
                        }
                    }
                }
            }
            classe.setListe_qcm(liste_q);
            etu.setClasse(classe);

            //etu.setQcm(liste_q.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialisation() {

        Window parentWindow = SwingUtilities.windowForComponent(this);
        parentFrame = null;
        if (parentWindow instanceof Frame) {
            parentFrame = (Frame) parentWindow;
        }

        etu.setQcm(null);
        affiche_qcm = new JPanel();
        liste_question = new ArrayList();
        liste_radio = new ArrayList();

        //GridBag
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //intialisation boutton
        JButton bt_note = new JButton("Notes");
        c.anchor = GridBagConstraints.WEST;
        this.add(bt_note, c);

        JTextArea txt = new JTextArea(etu.getNom() + " " + etu.getPrenom() + " "
                + etu.getClasse().getNom());

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

        JButton bt_Effectuer = new JButton("Effectuer");
        bt_Effectuer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Qcm qcm_test = null;
                int i = 0;
                for (Qcm q : etu.getClasse().getListe_qcm()) {
                    System.out.println("SI : " + q.getTitre() + " est egale avec : " + etu.getClasse().getListe_qcm().get(i).getTitre());
                    i++;
                    if (q.getTitre().equals(liste_qcm_etu.getSelectedValue())) {
                        qcm_test = q;
                    }

                }
                EffectuerQcm x = new EffectuerQcm(qcm_test, etu);
                x.test();
                //faut tester si test() de effectuer qcm renvoie qql chose; sinon...
                if (etu.getQcm() == qcm_test) {
                    rafraichissement();
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Qcm déjà noté");
                }
            }

        });
        this.add(bt_Effectuer, c);

        this.setVisible(true);

        bt_note.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bt_note) {
                    VisuNotes notes = new VisuNotes();

                }
            }
        });

    }

    public void ajoutListe() {
        ((DefaultListModel) liste_qcm_etu.getModel()).removeAllElements();

        for (Qcm qc : etu.getClasse().getListe_qcm()) {
                ((DefaultListModel) liste_qcm_etu.getModel()).addElement(qc.getTitre());
            
        }
    }

    public void affQcm() {
        if (etu.getQcm() == null) {
            return;
        }
        affiche_qcm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        for (Question q : etu.getQcm().getQ()) {
            System.out.println(q.getIntitule());
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
        }
        c.gridy++;
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
                    JOptionPane.showMessageDialog(parentFrame, "Qcm validé. Titire & id du QCM : " + etu.getQcm().getTitre() + " " + etu.getQcm().getId());
                    Note n = new Note(final_note, etu.getId());
                    etu.getQcm().ajouterNote(n);
                    n.addNote(etu);
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
        affiche_qcm.repaint();
        affiche_qcm.validate();
    }

    public void selectQcm() {

    }

    public class VisuNotes extends JDialog {

        private ArrayList<JLabel> liste;

        public VisuNotes() {
            super(parentFrame, "notes de l'étudiant n°" + etu.getId(), true);
            liste = new ArrayList<>();

            init();
            this.pack();
            this.setVisible(true);

        }

        public void init() {
            this.setLayout(new GridBagLayout());
            this.setLocationRelativeTo(parentFrame);

            for (int i = 0; i < etu.voirNotes().size(); i++) {
                liste.add(new JLabel());
                liste.get(i).setText("Note n°" + (i + 1) + ": " + Float.toString(etu.voirNotes().get(i)));

            }
            placement_notes();
        }

        /*public void remplir_liste( ArrayList<Float> lNotes){
         JLabel lab =new JLabel();
         for(int i = 0; i<lNotes.size(); i++){
         liste.get(i).setText("Note n°"+(i+1)+": "+Float.toString(lNotes.get(i)));
         }
                    
                    
         }*/
        public void placement_notes() {
            GridBagConstraints cont = new GridBagConstraints();
            for (int i = 0; i < liste.size(); i++) {
                cont.gridx = 0;
                cont.gridy = 1 + i;
                this.add(liste.get(i), cont);
            }
        }

    }

}
