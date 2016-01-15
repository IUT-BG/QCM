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
public class Classe {

    public String nom;
    protected ArrayList<Integer> liste_id_etu;
    protected ArrayList<Qcm> liste_qcm;

    public Classe() {
        liste_id_etu = new ArrayList();
        liste_qcm = new ArrayList();
        nom = "";
    }

    public Classe(String nom) {
        this.nom = nom;
    }

    public Classe(ArrayList<Qcm> liste_qcm, String nom) {
        this.liste_qcm = liste_qcm;
        this.nom = nom;
    }

    public Classe(ArrayList<Integer> liste_id_etu, ArrayList<Qcm> liste_qcm, String nom) {
        this.liste_id_etu = liste_id_etu;
        this.liste_qcm = liste_qcm;
        this.nom = nom;
    }

    public void setListe_id_etu(ArrayList<Integer> liste_id_etu) {
        this.liste_id_etu = liste_id_etu;
    }

    public void setListe_qcm(ArrayList<Qcm> liste_qcm) {
        this.liste_qcm = liste_qcm;
    }

    public void setNom(String num) {
        this.nom = num;
    }

    public ArrayList<Integer> getListe_id_etu() {
        return liste_id_etu;
    }

    public ArrayList<Qcm> getListe_qcm() {
        return liste_qcm;
    }

    public String getNom() {
        return nom;
    }

}
