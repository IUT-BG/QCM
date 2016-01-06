/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 *
 * @author toshiba
 */
public class FenetrePrincipal extends JFrame{
    
    public FenetrePrincipal(){
        
        this.setTitle("Qcm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        
        initialisation();
        rafraichir();
    }
    
    public void initialisation(){
        int sizeX=450;
        int sizeY=600;
        System.out.println();
        this.setPreferredSize(new Dimension(sizeX, sizeY));    
    }
    
    public void rafraichir(){
        
        this.pack();
        this.setVisible(true);
    }
} 
