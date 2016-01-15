/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author toshiba
 */
public class Note {
    int id_etudiant;
    int id_qcm;
    int note;

    public Note(int id_etudiant, int id_qcm, int note) {
        this.id_etudiant = id_etudiant;
        this.id_qcm = id_qcm;
        this.note = note;
    }
    
    
}
