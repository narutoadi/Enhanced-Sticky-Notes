/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author aditi
 */
public class Options extends JPanel implements ActionListener {

    JLabel lblPassSet;
    JButton btnPass,btnOK;
    JPasswordField tareaPass; 
    JPasswordField tareaPassCnfrm; 
    public Options()
    {
        this.setLayout(null);
        btnPass = new JButton("Set Password");
        this.btnPass.setBounds(10, 20, 200, 20);
        this.add(this.btnPass);
        this.btnPass.addActionListener(this);
        btnOK = new JButton("OK");

        
        this.btnOK.addActionListener(this);
        this.tareaPass = new JPasswordField();
        this.tareaPassCnfrm = new JPasswordField();
        //this.add(this.tareaPass);
        
        //this.add(this.tareaPassCnfrm);
        
        this.lblPassSet=new JLabel("The Password is Set :)");
       // this.add(this.lblPassSet);
        
        this.tareaPass.setVisible(false);
        this.tareaPassCnfrm.setVisible(false);
        this.btnOK.setVisible(false);
        this.lblPassSet.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      if(ae.getSource()==this.btnPass)
      {
          this.tareaPass.setBounds(10,50,200,20);
          this.add(this.tareaPass);
          this.tareaPass.setVisible(true);
          this.tareaPassCnfrm.setBounds(10,90,200,20);
          this.add(this.tareaPassCnfrm);
          this.tareaPassCnfrm.setVisible(true);
          
          this.btnOK.setBounds(10, 120, 80, 20);
          this.add(this.btnOK);
          this.btnOK.setVisible(true);
      }
      if(ae.getSource()==this.btnOK)
      {
          String a=new String(this.tareaPass.getPassword());
          CommRes.hInfo.password=a;
          CommRes.hInfo.isPassSet=true;
          this.lblPassSet.setBounds(10, 150, 350, 20);
          this.add(this.lblPassSet);
          this.lblPassSet.setVisible(true);
      }
    }
    
}
