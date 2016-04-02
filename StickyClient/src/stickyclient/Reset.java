/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author aditi
 */
public class Reset extends JPanel {
    JButton btnReset;   //convention : every button name starts with btn
    
    public Reset()
    {
        this.setLayout(null);
        this.btnReset=new JButton("Reset");
        this.add(this.btnReset);
        this.btnReset.setBounds(10,20,100,20);
    }
}
