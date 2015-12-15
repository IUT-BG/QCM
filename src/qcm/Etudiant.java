/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

/**
 *
 * @author toshiba
 */
public class Etudiant extends Personne{
    private Classe classe;
    
    public Etudiant(Classe c) {
        classe=c;
        
    }

    public Classe getClasse() {
        return classe;
    }
    
    public void validerRep(Qcm qcm){
        
    }
}  
