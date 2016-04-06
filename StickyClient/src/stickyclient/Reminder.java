/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author aditi
 */
public class Reminder extends JPanel implements ActionListener
{
   public static int height=20;
  // JLabel lblRem;
   JButton btnSet,btnDate,btnTime;
   public Reminder()
   {
       btnSet=new JButton("SET");
       btnDate=new JButton(new ImageIcon("Calendar.png"));
       btnTime=new JButton(new ImageIcon("clock.png"));
       btnSet.setBackground(Color.pink.brighter());
       btnDate.setBackground(Color.pink.brighter());
       btnTime.setBackground(Color.pink.brighter());
    //   lblRem=new JLabel("Set the date and time for alarm");
       this.setLayout(new GridLayout());
       this.add(this.btnDate);
       this.add(this.btnTime);
       this.add(this.btnSet); 
       
   }


   @Override
    public void actionPerformed(ActionEvent ae) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(ae.getSource()==this.btnSet)
       {
                   
       }
       
    }
    
}
