/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class Etudiant extends Personne {

    public Classe classe;
    public Qcm qcm;

    public Etudiant(String nom, String prenom, int id) {
        super(nom, prenom, id);
        classe = new Classe();
    }

    public Etudiant(Classe classe, String nom, String prenom, int id) {
        super(nom, prenom, id);
        this.classe = classe;
    }

    public ArrayList<Float> voirNotes() {
        ArrayList<Float> notes = new ArrayList();
        Connexion co = new Connexion("QCM.sqlite");
        co.connect();
        ResultSet res = co.query("SELECT note FROM Note WHERE id_etu = " + this.id + ";");

        try {
            while (res.next()) {
                notes.add(res.getFloat("note"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }

    public void setQcm(Qcm qcm) {
        this.qcm = qcm;
    }

    public Qcm getQcm() {
        return qcm;
    }

    public void afficheQcm() {
        // aff tout les qcm de l'eleve
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String toString() {
        return id + " - " + prenom + " " + nom + ", Etudiant";
    }
    
    public void initialisation_bd(Etudiant etu) {
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
        /*ResultSet resultSet = connexion.query("SELECT nom, prenom, id "
                + "FROM Personne WHERE id='1'");
        try {
            etu = new Etudiant(resultSet.getString("nom"),
                    resultSet.getString("prenom"), resultSet.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        ResultSet resultSet = connexion.query("SELECT intitule"
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
                liste_q.add(new Qcm(resultSet.getString("titre"), new Professeur(resultSet.getInt("id_prof")), liste_quest, resultSet.getInt("id")));
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
}
