/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickyclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
/**
 *
 * @author pc
 */
public class StickyNote extends JFrame implements ActionListener{
    String id;
    boolean isEditable;
    JTextArea tareaTitle,tareaDesc;
    JScrollPane jspTitle,jspDesc;
    JLabel lblTitle,lblDesc;
    JPanel pnlAction;
    JButton btnNew,btnDelete,btnEdit,btnSub,btnRem,btnShare,btnSetting;
    
    public StickyNote(){
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                for(int i=0;i<CommRes.notes.size();i++){
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);
                    if(note.id==StickyNote.this.id){
                        CommRes.notes.remove(i);
                        break;
                    }
                }
                //System.exit(1);
                StickyNote.this.dispose();
            }
        });
        
        this.lblTitle=new JLabel("Title");
        this.lblDesc=new JLabel("Description");
        this.tareaTitle=new JTextArea();
        this.tareaDesc=new JTextArea();
        this.jspTitle=new JScrollPane(this.tareaTitle,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jspDesc=new JScrollPane(this.tareaDesc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.pnlAction=new JPanel();
        this.tareaTitle.setWrapStyleWord(true);
        this.tareaTitle.setLineWrap(true);
        this.tareaDesc.setWrapStyleWord(true);
        this.tareaDesc.setLineWrap(true);

        this.design();
        
//ActionPanel
        this.btnNew=new JButton(new ImageIcon("Desert.jpg"));
        this.btnEdit=new JButton(new ImageIcon("Desert.jpg"));
        this.btnDelete=new JButton(new ImageIcon("Desert.jpg"));
        this.btnSub=new JButton(new ImageIcon("Desert.jpg"));
        this.btnRem=new JButton(new ImageIcon("Desert.jpg"));
        this.btnSetting=new JButton(new ImageIcon("Desert.jpg"));
        this.btnShare=new JButton(new ImageIcon("Desert.jpg"));
        this.pnlAction.setLayout(new GridLayout());
        this.pnlAction.add(this.btnNew);
        this.pnlAction.add(this.btnEdit);
        this.pnlAction.add(this.btnDelete);
        this.pnlAction.add(this.btnRem);
        this.pnlAction.add(this.btnShare);
        this.pnlAction.add(this.btnSetting);
        
        this.btnNew.addActionListener(this);
        this.btnEdit.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnRem.addActionListener(this);
        this.btnShare.addActionListener(this);
        this.btnSetting.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==this.btnNew){
            StickyNote note=new StickyNote();
            Toolkit tool=Toolkit.getDefaultToolkit();
            Dimension size=tool.getScreenSize();
            final int HEIGHT=190;
            note.setBounds(size.width/2-CommRes.WIDTH/2, size.height/2-HEIGHT/2, CommRes.WIDTH, HEIGHT);
            note.setVisible(true);
            note.setEditable();
            note.id=new Date().toString();
            note.setTitle(note.id);
            CommRes.notes.add(note);
        }
        if(ae.getSource()==this.btnEdit){
            if(this.isEditable){
                //Save Operation
                this.tareaTitle.setEditable(false);
                this.tareaDesc.setEditable(false);
                this.btnEdit.setIcon(new ImageIcon("Desert.jpg"));
                this.isEditable=false;
                this.btnEdit.setToolTipText("EDIT");
            }else{
                this.setEditable();
            }
        }
        if(ae.getSource()==this.btnDelete){
            for(int i=0;i<CommRes.notes.size();i++){
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);
                    if(note.id==this.id){
                        CommRes.notes.remove(i);
                        break;
                    }
                }
                //System.exit(1);
                this.dispose();
        }
    }
    
    public void setEditable(){
        this.tareaTitle.setEditable(true);
        this.tareaDesc.setEditable(true);
        this.btnEdit.setIcon(new ImageIcon("Chrysanthemum.jpg"));
        this.isEditable=true;
        this.btnEdit.setToolTipText("SAVE");
    }
    private void design(){
        this.setPos(this.lblTitle,0,0,10);
        this.setPos(this.jspTitle,0,11,30);
        this.setPos(this.lblDesc,0,41,10);
        this.setPos(this.jspDesc,0,51,80);
        this.setPos(this.pnlAction,0,131,30);
    }
    private void setPos(Component C,int x,int y,int h){
        this.add(C);
        C.setBounds(x, y, CommRes.WIDTH, h);
        C.setFont(CommRes.font);
    }
}
