/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Connexion;
import Model.Etudiant;
import Model.Personne;
import Model.Professeur;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author toshiba
 */
public class FenetrePrincipal extends JFrame {

    JPanel pano;
    Personne pers;

    public class PanelConnexion extends JPanel {

        private JLabel l_user, l_password, l_warning;
        private JTextField t_user;
        private JPasswordField p_password;
        private JButton b_login;

        public PanelConnexion(Personne pers) {
            this.setBackground(new Color(0x34495e));
            l_warning = new JLabel("");
            l_warning.setForeground(Color.red);
            l_user = new JLabel("Identifiant :");
            l_user.setForeground(Color.white);
            l_user.setFont(new Font("Century", 0, 20));
            l_password = new JLabel("Mot de passe :");
            l_password.setForeground(Color.white);
            l_password.setFont(new Font("Century", 0, 20));
            t_user = new JTextField(8);
            t_user.setDragEnabled(true);
            p_password = new JPasswordField(8);
            b_login = new JButton("Valider");
            b_login.addActionListener((ActionEvent e) -> {
                if (verifChamp()) {
                    try {
                        System.out.println(verifConnexion(Integer.parseInt(t_user.getText()), p_password.getText()));
                    } catch (SQLException ex) {
                        Logger.getLogger(PanelConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                }
            });

            init();
        }

        public void init() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.ipadx = 20;
            c.ipady = 20;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            this.add(l_warning, c);
            c.gridy = 1;
            c.gridwidth = 1;
            this.add(l_user, c);
            c.gridx = 1;
            this.add(t_user, c);
            c.gridx = 0;
            c.gridy = 2;
            this.add(l_password, c);
            c.gridx = 1;
            this.add(p_password, c);
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 2;
            this.add(b_login, c);
            this.setVisible(true);
        }

        public boolean verifChamp() // verifie que tous les champs sont remplis
        {
            if ((t_user.getText().equals("")) || (p_password.getText().equals(""))) {
                l_warning.setText("Veuillez renseigner tous les champs");
                return false;
            }
            if (!(t_user.getText().matches("[0-9]+"))) // utiliser les expressions regulieres pour verifier que se sont biens que des chiffres
            {
                l_warning.setText("Identifiant incorrect");
                return false;
            } else {
            }
            return true;
        }

        public boolean verifConnexion(int id, String pswd) throws SQLException {
            Connexion co = new Connexion("QCM.sqlite");
            co.connect();
            ResultSet res = co.query("select count(id), id, nom, prenom, prof from Personne where id=" + id + " and mdp='" + pswd + "';");
            res.next();
            if (res.getInt(1) == 0) {
                l_warning.setText("Identifiant ou mot de passe incorrect");
                return false;
            }
            if ((boolean) res.getBoolean(5)) {
                pers = new Professeur(res.getString(3), res.getString(4), res.getInt(2));
            } else {
                pers = new Etudiant(res.getString(3), res.getString(4), res.getInt(2));
            }
            System.out.println(pers);
            initialisation();
            rafraichir();
            return true;
        }
    }

    public FenetrePrincipal() {
        pers = null;
        pano = new PanelConnexion(pers);
        this.setTitle("Qcm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //PanelCreationQcm pan = new PanelCreationQcm();
        initialisation();
        rafraichir();
    }

    public void initialisation() {
        this.remove(pano);
        if (pers != null) {
            if (pers.getClass() == Professeur.class) {
                pano = new PanelProfesseur((Professeur) pers, this);
            } else {
                pano = new PanelEtudiant((Etudiant) pers);
            }
        }
        this.add(pano);
        //        int sizeX=650;
        //        int sizeY=800;
        //        System.out.println();
        //        this.setPreferredSize(new Dimension(sizeX, sizeY));
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    public void rafraichir() {
        this.pack();
        this.setVisible(true);
    }

    @SuppressWarnings("empty-statement")
    public void attendreCo() {
        while (pers == null);
    }
}
