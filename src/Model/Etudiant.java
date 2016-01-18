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
public class Etudiant extends Personne{
    public Classe classe;
    public Qcm qcm;
    
    public Etudiant(String nom, String prenom, int id){
        super(nom,prenom,id);
        classe = new Classe();
    }
    
    public Etudiant(Classe classe, String nom, String prenom, int id){
        super(nom, prenom, id);
        this.classe = classe;
    }
    
    /* A METTRE DANS PanelEtudiant
    
    
    private void validerRep(Qcm qcm){
        recup réponse de élève avec listener et compare aux bonnes
        rép du Qcm envoyé en param pour enregistrer la note
    }*/
    
    private ArrayList<Float> voirNotes(){
        ArrayList<Float> notes = new ArrayList();
        Connexion co = new Connexion("Z:\\Documents\\NetBeansProjects\\QCM\\QCM.sqlite");
        ResultSet res = co.query("SELECT id FROM Personne WHERE nom ="+ this.nom + ";");
        ResultSet res1 = co.query("SELECT note FROM Note WHERE id_etu ="+ res+";");
        try {
            while(res1.next()){
                notes.add(res1.getFloat("note"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }
    
    public void setQcm(Qcm qcm){
        this.qcm = qcm;
    }
    
    public Qcm getQcm(){
        return qcm;
    }
    
    public void afficheQcm(){
        // aff tout les qcm de l'eleve
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public String toString()
    {
        return id+" - "+prenom+" "+nom+", Etudiant";
    }    
}  
