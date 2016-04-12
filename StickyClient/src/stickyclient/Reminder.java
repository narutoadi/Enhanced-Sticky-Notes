/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 *
 * @author aditi
 */
public class Reminder extends JPanel implements ActionListener
{
   public static int height=20;
   boolean isSet=false;
  // JLabel lblRem;
   JButton btnSet,btnDate;
   JTextArea tareaDate;
   StickyNote note;
   public Reminder(StickyNote nt)
   {
       note=nt;
       btnSet=new JButton(new ImageIcon("alarm.png"));
     //  btnSet.setPreferredSize(new Dimension(height,20));
       btnDate=new JButton(new ImageIcon("Calendar.png"));
     //  btnDate.setPreferredSize(new Dimension(height,20));
       tareaDate=new JTextArea("7-4-2016 3:30 ");
     //  tareaDate.setPreferredSize(new Dimension(height,150));
       btnSet.setBackground(Color.pink.brighter());
       btnDate.setBackground(Color.pink.brighter());
       tareaDate.setBackground(Color.pink.brighter());
    //   lblRem=new JLabel("Set the date and time for alarm");
       this.setLayout(null);
       this.add(this.btnDate);
       this.btnDate.setBounds(0, 0, 20, height);
       this.add(this.tareaDate);
       this.tareaDate.setBounds(20, 0, 100, height);
       this.add(this.btnSet); 
       this.btnSet.setBounds(180, 0, 20, height);
   }


   @Override
    public void actionPerformed(ActionEvent ae) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(ae.getSource()==this.btnSet)
       {
           // JCalendar cal=new JCalendar(); 
           if(isSet==false)
           {
                isSet=true;
                btnSet.setIcon(new ImageIcon("delete.png")); 
                // Lets remove the panel
                
           }
           
       }
       
    }
    
}
