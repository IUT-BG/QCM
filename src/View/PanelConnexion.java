/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Basile
 */
public class PanelConnexion extends JPanel{
    private JLabel l_user, l_password,l_warning;
    private JTextField t_user, t_password;
    private JButton b_login;
    
    public PanelConnexion()
    {
        l_warning = new JLabel("");
        l_warning.setForeground(Color.red);
        l_user = new JLabel("Identifiant :");
        l_password = new JLabel("Mot de passe :");
        t_user = new JTextField(8);
        t_password = new JTextField(8);
        b_login = new JButton("Valider");
        b_login.addActionListener((ActionEvent e) -> {
            System.out.println(verifChamp());
        });
        
        init();
    }
    public void init()
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx=10;c.ipady=10;
        c.gridx=0;c.gridy=0;
        c.gridwidth=2;
        this.add(l_warning,c);
        c.gridy=1;
        c.gridwidth=1;
        this.add(l_user,c);
        c.gridx=1;
        this.add(t_user,c);
        c.gridx=0;c.gridy=2;
        this.add(l_password,c);
        c.gridx=1;
        this.add(t_password,c);
        c.gridx=0;c.gridy=3;
        c.gridwidth=2;
        this.add(b_login,c);
        this.setVisible(true);
    }
    public boolean verifChamp()
    {
        return(!(t_user.getText().equals("")||t_password.getText().equals("")));
    }
}
