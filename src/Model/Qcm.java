/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author toshiba
 */
public class Qcm {
    private int id;
    private String titre;
    private int id_prof;
    private ArrayList<Note> note;
    private ArrayList<Question> q;

    public Qcm(String titre, int id_prof, ArrayList<Question> q) {
        this.titre = titre;
        this.id_prof = id_prof;
        this.q = q;
        this.note = new ArrayList(); 
    }
    
    public void modifierQcm(ArrayList<Question> nouvelle){
        if(note == null)
            this.q = nouvelle;
        else
            System.err.println("Modification ipossible : un élève à déjà rempli ce Qcm");
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getId_prof() {
        return id_prof;
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
    
    
} 
