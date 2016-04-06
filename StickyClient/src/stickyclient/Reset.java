/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author aditi
 */
public class Reset extends JPanel implements ActionListener {
    JButton btnReset;   //convention : every button name starts with btn
    JButton btnDefault;
    JLabel lblReset;
    JLabel lblDefault;
    
    public Reset()
    {
        this.setLayout(null);
        this.lblReset=new JLabel("Delete all notes and restore factory settings:");
        this.btnReset=new JButton("Reset");
        this.lblDefault=new JLabel("Restore factory settings to all notes: ");
        this.btnDefault=new JButton("Default");
        this.setPos(this.lblReset,10,20,400,20);
        this.setPos(this.btnReset,10,50,100,20);
        this.setPos(this.lblDefault,10,80,300,20);
        this.setPos(this.btnDefault,10,110,100,20);
        this.btnDefault.addActionListener(this);
        this.btnReset.addActionListener(this);
    }
    public void setPos(Component c,int x,int y,int w, int h){
        this.add(c);
        c.setBounds(x, y, w, h);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==this.btnDefault)
        {
                CommRes.hInfo.createdOn="";
                CommRes.hInfo.password="";
                CommRes.hInfo.font="Arial";
                CommRes.hInfo.size=12;
                CommRes.hInfo.style=Font.PLAIN;
                CommRes.hInfo.foreColor=Color.BLACK;
                CommRes.hInfo.bkgColor=Color.WHITE;
                CommRes.updateFile();
                for(int i=0;i<CommRes.notes.size();i++)
                {
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);;
                    note.applySettings();
                }
        }
        if(ae.getSource()==this.btnReset)
        {
            int n = JOptionPane.showConfirmDialog(new Settings(),"Are you sure that you want to delete all sticky notes?","Confirmation!!",JOptionPane.YES_NO_OPTION);
        //    System.out.println(n);
            if(n==0)
            {
                // Remove elements from array list
            for(int i=0;i<CommRes.notesColl.size();i++)
                    CommRes.notesColl.remove(i);
            // Rewrote the arraylist to file
            for(int i=0;i<CommRes.notes.size();i++){    
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);
                    note.dispose();
            }
                CommRes.isFirst=true;
                CommRes.hInfo=new HeaderInfo();
                // Initializing members of headerinfo (with default values)
                // This values can be later changed by settings tab.
                CommRes.hInfo.createdOn="";
                CommRes.hInfo.password="";
                CommRes.hInfo.font="Arial";
                CommRes.hInfo.size=12;
                CommRes.hInfo.style=Font.PLAIN;
                CommRes.hInfo.foreColor=Color.BLACK;
                CommRes.hInfo.bkgColor=Color.WHITE;
                CommRes.notesColl=new ArrayList<NotesInfo>();
                
                CommRes.updateFile();
                                StickyNote note=new StickyNote();
                // TO show the first note at the centre of screen we need to
                // get size of the screen first
                Toolkit tool=Toolkit.getDefaultToolkit();
                Dimension size=tool.getScreenSize();
                // Default height of a sticky note
                note.applySettings();
                final int HEIGHT=250;
                note.setBounds(size.width/2-CommRes.WIDTH/2, size.height/2-HEIGHT/2, CommRes.WIDTH, HEIGHT);
                note.setVisible(true);
                note.setEditable();
                note.id=new Date().toString();
                // When a note is created it will remain new before it is saved
                note.isNew=true;
                note.setTitle(note.id);
                // Adding the created note the vector. 
                // When we will save it, we will insert it in the Arraylist and
                // will write to the file.
                CommRes.notes.add(note);
            }
        }

//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

