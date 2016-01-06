/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Qcm;
import Model.Question;
import Model.Reponse;
import java.util.ArrayList;

/**
 *
 * @author p1406664
 */
public class TestQcm {
    
    Qcm qcm;
    
    public TestQcm(){
        
        ArrayList<Reponse> r = new ArrayList();
        r.add(new Reponse("George",false));
        r.add(new Reponse("Louis",true));
        
        ArrayList<Question> q = new ArrayList();
        q.add(new Question("Quel est ton prenom ?",r));
        
        r = new ArrayList();
        r.add(new Reponse("Magand",true));
        r.add(new Reponse("Ta maman", false));
        r.add(new Reponse("Jean Jacque",false));
        
        q.add(new Question("Quel est ton nom?",r));
        
        r = new ArrayList();
        r.add(new Reponse("Chien",false));
        r.add(new Reponse("Ta chatte(True)", true));
        r.add(new Reponse("Notre tortue",false));
        r.add(new Reponse("Le ciel(True)",true));
        
        q.add(new Question("Quel est ton(tes) animal(-aux) preferé(s)?",r));
        
        qcm = new Qcm("Histoire",0,q);
    }
    
    public Qcm getQcm(){
        return qcm;
    }
    
}
