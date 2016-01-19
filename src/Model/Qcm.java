/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author toshiba
 */
public class Qcm {

    private int id;
    private String titre;
    private Professeur prof;
    private ArrayList<Note> note;
    private ArrayList<Question> q;
    private String access;

    public Qcm(String titre, Professeur prof, ArrayList<Question> question) {
        this.titre = titre;
        this.prof = prof;
        this.q = question;
    }

    public Qcm(String titre, Professeur prof, ArrayList<Question> q, String classe) {
        this.titre = titre;
        this.prof = prof;
        this.q = q;
        this.note = new ArrayList();
        this.access = classe;
    }

    public Qcm(String titre, Professeur prof, ArrayList<Question> q, int id) {
        this.titre = titre;
        this.prof = prof;
        this.q = q;
        this.note = new ArrayList();
        this.id = id;
    }

    public void modifierQcm(ArrayList<Question> nouvelle) {
        if (note == null) {
            this.q = nouvelle;
        } else {
            System.err.println("Modification ipossible : un élève à déjà rempli ce Qcm");
        }
    }
    
    public void supprimerQcm(){
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();
        
        connexion.insert("DELETE FROM Note WHERE id_qcm = "+ this.id);
        
        for (int i = 0; i < q.size(); i++) {
            for (int j = 0; j < q.get(i).getReponse().size(); j++) {
                connexion.insert("DELETE FROM Note WHERE id_question = "+ q.get(i).getId());
            }
            connexion.insert("DELETE FROM Question WHERE id_qcm = "+ this.id);
        }
        
        connexion.insert("DELETE FROM Qcm WHERE id = "+ this.id);
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Professeur getProf() {
        return prof;
    }

    public ArrayList<Note> getNote() {
        return this.note;
    }

    public ArrayList<Question> getQ() {
        return q;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void ajouterNote(Note note) {
        this.note.add(note);
    }

    public void setQ(ArrayList<Question> q) {
        this.q = q;
    }

    public void publish() {
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();

        connexion.insert("INSERT INTO Qcm (titre, id_prof, access) VALUES(\"" + this.titre + "\", " + prof.getId() + ", \"" + this.access + "\" )");

        ResultSet new_id = connexion.query("SELECT COUNT(*) FROM Qcm");
        try {
            this.id = new_id.getInt("COUNT(*)");
        } catch (SQLException ex) {
        }

        int id_q = 0;
        for (int i = 0; i < this.q.size(); i++) {
            connexion.insert("INSERT INTO Question (intitule, id_qcm) VALUES(\"" + this.q.get(i).getIntitule() + "\", " + this.id + " )");

            ResultSet new_q = connexion.query("SELECT COUNT(*) FROM Question");
            try {
                id_q = new_q.getInt("COUNT(*)");
            } catch (SQLException ex) {
            }

            for (int j = 0; j < this.q.get(i).getReponse().size(); j++) {
                connexion.insert("INSERT INTO Reponse (intitule, id_question, valide) VALUES(\"" + this.q.get(i).getReponse().get(j).getIntitule() + "\", " + id_q + ", \"" + this.q.get(i).getReponse().get(j).isValide() + "\" )");
            }
        }
        connexion.close();
    } 

}
