/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Connexion;
import Model.Note;
import Model.Professeur;
import Model.Qcm;
import Test.TestQcm;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author toshiba
 */
public class PanelProfesseur extends JPanel{
    
    private ArrayList<JButton> note;
    private JButton creer;
    private JButton modifier;
    private JButton supprimer;
    private Professeur pers;

    public JLabel l_test;

    private JTextField infos;

    public PanelProfesseur(Professeur pers) {
        this.pers = pers;
        l_test = new JLabel(pers.toString());

        //Cont  rainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        
        note = new ArrayList<JButton>();
        modifier = new ArrayList<JButton>();
        supprimer = new ArrayList<JButton>();
        titre = new ArrayList<JLabel>();
        creer = new JButton();
        
        p =new Professeur();
        
        ArrayList<String> liste = p.voirQcm();
        
        for(int i=0;i<liste.size(); i++){
            titre.add(new JLabel(liste.get(i)));
            note.add(new JButton("Notes"));
            creer.add(new JButton("Creer"));
            modifier.add(new JButton("Modifier"));
            supprimer.add(new JButton("Supprimer"));
            
            global.gridy = i;
            global.gridx = 0;
            this.add(titre.get(i), global);
            
            global.gridx++;
            this.add(note.get(i), global);
            
            global.gridx++;
            this.add(creer.get(i), global);
            
            global.gridx++;
            this.add(modifier.get(i), global);
            
            global.gridx++;
            this.add(supprimer.get(i), global);
            
            note.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //pour savoir sur quel boutton on a cliqué.
                    int indx = note.indexOf( e.getSource() );
                    initpano(indx);
                }
            });
        }
    }
    public void initpano(int ind){
        
        pano = new JPanel();
        ArrayList<Note> liste_notes = new ArrayList<Note>();
            
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();
            
        ResultSet resultSet = connexion.query("SELECT * FROM Note WHERE id_qcm="+Integer.toString(p.getId_qcm().get(ind))+";");
        try {
            while (resultSet.next()) {
                liste_notes.add(new Note(resultSet.getInt("id_etu"), resultSet.getFloat("note")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nom_classe="";
        resultSet = connexion.query("SELECT access FROM Qcm WHERE id="+Integer.toString(p.getId_qcm().get(ind))+";");
        try {
            while (resultSet.next()) {
                nom_classe = resultSet.getString("access");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ArrayList<String> liste_etu = new ArrayList<String>();
        ArrayList<Integer> liste_id = new ArrayList<Integer>();
        //On remplit la liste pour avoir le nom des étudiants
        resultSet = connexion.query("SELECT id,nom FROM Personne WHERE classe="+nom_classe+";");
        try {
            while (resultSet.next()) {
                liste_etu.add(resultSet.getString("nom"));
                liste_id.add(resultSet.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String col[] = {"Classe","Etudiant","Note"};
        System.out.println(liste_notes.size());
        DefaultTableModel tableModel = new DefaultTableModel(null,col);
        for(int i=0; i<liste_etu.size(); i++){
            String nom = liste_etu.get(i);
            String note="";
            if(liste_id.get(i) == liste_notes.get(i).getId_etu())
                note = Float.toString(liste_notes.get(i).getNote());
            else
                note = "Qcm non effectué";
            
            Object[] data = {nom, note};
            
            tableModel.addRow(data);
        }
        
        JScrollPane scrollPane = new JScrollPane();
        pano.add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        this.removeAll();
        this.add(pano);
        this.setPreferredSize(new Dimension(550,600));
        this.repaint();
        
    }

}
