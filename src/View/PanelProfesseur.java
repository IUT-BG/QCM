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
import javafx.scene.layout.Border;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author toshiba
 */
public class PanelProfesseur extends JPanel{
    
    private JFrame frame;
    
    private JPanel actif;
    private ArrayList<JLabel> nom_qcm;
    private ArrayList<JButton> note;
    private JButton creer;
    private JButton gerer;
    private ArrayList<JButton> supprimer;
    
    private Professeur pers;
    private ArrayList<JLabel> titre;
    private JPanel pano;

    public JLabel l_test;

    private JTextField infos;

    
    
    public PanelProfesseur(Professeur pers, JFrame frame){
        this.frame = frame;
        this.pers=pers;
        

        //Cont  rainte de positionnement
        this.setLayout(new GridBagLayout());
        GridBagConstraints global = new GridBagConstraints();
        global.gridx = global.gridy = 0;
        
        init();
        l_test = new JLabel("  " + pers.getNom() + "   " + pers.getPrenom() + "  ");
    }
    
    public void init(){
        //Allocation de la mémoire
            nom_qcm = new ArrayList();
            note = new ArrayList();
            supprimer = new ArrayList();
            
            actif = new JPanel();
            actif.setVisible(true);
            actif.setPreferredSize(new Dimension(1200, 500));
            
            System.out.println("cc");
            creer = new JButton("Nouveau QCM");
            gerer = new JButton("Gérer les QCM");
            
            creer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    actif.removeAll();
                    actif.add(new PanelCreationQcm(frame, pers));
                    actif.validate();
                }
            });
            
            titre = new ArrayList<JLabel>();

            ArrayList<String> liste = pers.voirQcm();
            
            GridBagConstraints global = new GridBagConstraints();
            global.gridx = global.gridy = 0;
            
            for(int i=0;i<liste.size(); i++){
                titre.add(new JLabel(liste.get(i)));
                note.add(new JButton("Notes"));
                supprimer.add(new JButton("Supprimer"));
                
                global.gridy = i;
                global.gridx = 0;
                this.add(titre.get(i), global);

                global.gridx++;
                this.add(note.get(i), global);

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
        JButton retour = new JButton("Retour");
        ArrayList<Note> liste_notes = new ArrayList<Note>();
            
        Connexion connexion = new Connexion("QCM.sqlite");
        connexion.connect();
            
        ResultSet resultSet = connexion.query("SELECT * FROM Note WHERE id_qcm="+Integer.toString(pers.getId_qcm().get(ind))+";");
        try {
            while (resultSet.next()) {
                liste_notes.add(new Note(resultSet.getFloat("note"),resultSet.getInt("id_etu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nom_classe="";
        resultSet = connexion.query("SELECT access FROM Qcm WHERE id="+Integer.toString(pers.getId_qcm().get(ind))+";");
        try {
            while (resultSet.next()) {
                nom_classe = resultSet.getString("access");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(nom_classe);
        ArrayList<String> liste_etu = new ArrayList<String>();
        ArrayList<Integer> liste_id = new ArrayList<Integer>();
        //On remplit la liste pour avoir le nom des étudiants
        resultSet = connexion.query("SELECT id,nom FROM Personne WHERE classe = '"+nom_classe+"' AND prof!=1;");
        try {
            while (resultSet.next()) {
                liste_etu.add(resultSet.getString("nom"));
                liste_id.add(resultSet.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String col[] = {"Classe","Etudiant","Note"};
        System.out.println(liste_etu.size());
        DefaultTableModel tableModel = new DefaultTableModel(null,col);
        for(int i=0; i<liste_etu.size(); i++){
            String nom = liste_etu.get(i);
            String note="";
            if(liste_notes.size()>0){
                if(liste_id.get(i) == liste_notes.get(i).getId_etu()){
                    note = Float.toString(liste_notes.get(i).getNote());
                    System.out.println(Float.toString(liste_notes.get(i).getNote()));
                }
                else
                    note = "Qcm non effectué";
            }
             else
                    note = "Qcm non effectué";
            
            Object[] data = {nom_classe,nom, note};
            
            tableModel.addRow(data);
        }
        
        JScrollPane scrollPane = new JScrollPane();
        pano.add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        retour.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retour();
            }
        });
        
        this.removeAll();
        this.add(pano);
        frame.setPreferredSize(new Dimension(700,800));
        frame.pack();
        this.add(retour);
        this.updateUI();
        
    }
    
    public void update(){
        actif.removeAll();
        actif.validate();
    }
    
    public void retour(){
       this.removeAll();
       init();
       this.updateUI();
    }

}
