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
public class Note {

    int id_etu;
    float note;

    public Note() {
    }

    public Note( float note,  int id_etu) {
        this.id_etu = id_etu;
        this.note = note;
    }

    public int getId_etu() {
        return id_etu;
    }

    public float getNote() {
        return note;
    }

    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void addNote(Etudiant etu) {
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();
        ArrayList<String> liste = new ArrayList<String>();

        int resultSet = connexion.insert("INSERT INTO note (note, id_etu, id_qcm) VALUES "
                + "(" + getNote() + "," + etu.getId() + "," + etu.getQcm().getId() + ");");
        System.out.println("Nb de ligne affecté : " + resultSet);
    }

}
