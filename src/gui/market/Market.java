/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.market;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;

/**
 *
 * @author FeepFoop
 */
public class Market extends JPanel implements ActionListener{
    
    /**
     * @param args the command line arguments
     */
    
    
    public Market()
    {
        GridLayout c0 = new GridLayout(3,3);
        j = new JPanel();
        f.setLayout(c0);
        b[0] = new JButton("Start");
        b[0].setText("Start");
        f.add(b[0]);
        
        f.setVisible(true);
        //f.add(j);
        /*
        button = new JButton("Button 1");
        c.weightx = 0.5;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        j.add(button, c);
        
        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        j.add(button, c);
        
        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        j.add(button, c);
        
        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        j.add(button, c);
        
        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        j.add(button, c);
        */
        
        
    }
    
    
    private static JFrame f;
    private static JPanel j; 
    private static JButton[] b;
    
    
    
    public static void main(String[] args) {
        b = new JButton[10];
        f = new JFrame("True Insight");
        f.setSize(400, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(new Market());
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
