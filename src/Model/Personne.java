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
public class Personne {

    String nom;
    String prenom;
    int id;

    public Personne() {
        nom = null;
        prenom = null;
        id = -1;
    }

    public Personne(int id) {
        this.id = id;
    }

    public Personne(String nom, String prenom, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public void Connexion() {
        return;
    }

    public void Deconnexion() {
        return;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
