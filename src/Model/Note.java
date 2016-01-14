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
    
    int id_etu;
    int note;

    public Note() {
    }

    public Note(int id_etu, int note) {
        this.id_etu = id_etu;
        this.note = note;
    }
    

    public int getId_etu() {
        return id_etu;
    }

    public int getNote() {
        return note;
    }

    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
     
}
