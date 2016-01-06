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
public class Etudiant extends Personne{
    public Classe classe;
    public Qcm qcm;
    
    public Etudiant(){
        super();
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
    
    private void voirNotes(){
        //aff note eleve tiré de la bd
        
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
    
}  
