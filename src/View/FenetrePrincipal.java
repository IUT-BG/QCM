/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author toshiba
 */
public class FenetrePrincipal extends JFrame{
    
    public FenetrePrincipal(){
        JPanel panel_etu = new PanelEtudiant(this);
        this.setTitle("Qcm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        
        PanelCreationQcm pan = new PanelCreationQcm();
        this.add(pan);
        this.add(panel_etu);
        
        initialisation();
        rafraichir();
    }
    
    public void initialisation(){
        int sizeX=650;
        int sizeY=800;
        System.out.println();
        this.setPreferredSize(new Dimension(sizeX, sizeY));    
    }
    
    public void rafraichir(){
        this.pack();
        this.setVisible(true);
    }
            
} 
