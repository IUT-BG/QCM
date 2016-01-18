/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Etudiant;
import Model.Note;
import Model.Qcm;

/**
 *
 * @author Louis
 */
public class EffectuerQcm {

    Qcm qcm;
    Etudiant etu;

    public EffectuerQcm(Qcm qcm, Etudiant etu) {
        if (qcm == null) {
            return;
        }
        this.qcm = qcm;
        this.etu = etu;
    }

    public void test() {
        if (etu.getQcm() != null) {
            System.out.println("Veuillez terminer votre Qcm avant d'en commencer un nouveau.");
            return;
        } else {
            try {
                for (Qcm q : etu.getClasse().getListe_qcm()) {
                    if (q == qcm) {
                        for (Note n : qcm.getNote()) {
                            if (n.getId_etu() == etu.getId()) {
                                if (n.getNote() >= 0) {
                                    //qcm deja effectué
                                    System.out.println("Qcm deja effectué");
                                    return;
                                } else {
                                    System.out.println("etu.setQcm(qcm) normalement viser par l'utilisateur");
                                    etu.setQcm(qcm);
                                    return;
                                }
                            }
                        }
                        if (qcm.getNote().isEmpty()) {
                            System.out.println("etu.setQcm(qcm) normalement viser par l'utilisateur");
                            etu.setQcm(qcm);
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
