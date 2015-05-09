
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import market.Market;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FeepFoop
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Insight extends JPanel implements ActionListener {
	public static void main(String[] args) {
		f = new JFrame();
		f.add(new Insight());
		f.setSize(800, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(200, 200);
		f.setVisible(true);
		f.setResizable(false);
		
	}
	
	public  Insight( ) {
		
		//f.setLocation(200, 200); //f.setVisible(true); //visuals
		 f.setSize(800,800); f.setLayout(null);
		 

	
		b[0] = new JButton("Start");
		b[1] = new JButton("");
		b[2] = new JButton("");
		b[3] = new JButton("");
		b[4] = new JButton("");
		b[5] = new JButton("");


		
		
		for (int i = 0; i < 5; i++) {
                    b[i].addActionListener(this);
		}
	
		f.setVisible(true);
	}
		
	private static JFrame f;
	JButton[] b = new JButton[10];


    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
