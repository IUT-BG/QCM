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
    private ArrayList<Float> note;
    private ArrayList<Question> q;

    public Qcm(String titre, int id_prof, ArrayList<Question> q) {
        this.titre = titre;
        this.id_prof = id_prof;
        this.q = q;
        this.note = new ArrayList();
    }
    
    
} 
