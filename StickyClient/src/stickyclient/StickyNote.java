/*

 */

package stickyclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author aditi
 */
public class StickyNote extends JFrame implements ActionListener{
    // A unique id for each sticky note that stores the date and time of creation
    String id;
    // To give same button (btnEdit) 2 features at a time 
    // When isEditable
    boolean isEditable;
    boolean isNew;
    JTextArea tareaTitle,tareaDesc;
    JScrollPane jspTitle,jspDesc;
 //   JLabel lblTitle,lblDesc;
    JPanel pnlAction;
    JButton btnNew,btnDelete,btnEdit,btnSub,btnRem,btnShare,btnSetting;
    
    public StickyNote(){
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                NotesInfo nInfo=new NotesInfo();
                    nInfo.id=StickyNote.this.id;
                    nInfo.title=StickyNote.this.tareaTitle.getText();
                    nInfo.desc=StickyNote.this.tareaDesc.getText();
                    nInfo.createdOn=new Date().toString();
                    nInfo.subnotes=new ArrayList<String>();
                    nInfo.X=StickyNote.this.getX();
                    nInfo.Y=StickyNote.this.getY();
                    StickyNote.this.isNew=false;
                    
                    for(int i=0;i<CommRes.notesColl.size();i++){
                if(CommRes.notesColl.get(i).id==StickyNote.this.id)
                {
                    CommRes.notesColl.remove(i);
                }    
            }
                    CommRes.notesColl.add(nInfo);
                    CommRes.updateFile();
                    
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
        
 //       this.lblTitle=new JLabel("Title");
   //     this.lblDesc=new JLabel("Description");
        this.tareaTitle=new JTextArea();
        this.tareaTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.tareaDesc=new JTextArea();
        this.tareaDesc.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.jspTitle=new JScrollPane(this.tareaTitle,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     //   this.jspTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.jspDesc=new JScrollPane(this.tareaDesc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jspDesc.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.pnlAction=new JPanel();
       // this.pnlAction.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.tareaTitle.setWrapStyleWord(true);
        this.tareaTitle.setLineWrap(true);
        this.tareaDesc.setWrapStyleWord(true);
        this.tareaDesc.setLineWrap(true);
      //  this.add(this.jspTitle);
      //  this.jspTitle.setBounds(0, 0, CommRes.WIDTH+1, 30);
        this.design();
       
        
//ActionPanel
        this.btnNew=new JButton(new ImageIcon("file-add-1.png"));
        this.btnNew.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnEdit=new JButton(new ImageIcon("save.png"));
        this.btnEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnDelete=new JButton(new ImageIcon("DeleteRed.png"));
        this.btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnSub=new JButton(new ImageIcon("button-add-icon.png"));
        this.btnSub.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnRem=new JButton(new ImageIcon("alarm-icon.png"));
        this.btnRem.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnSetting=new JButton(new ImageIcon("setting.png"));
        this.btnSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnShare=new JButton(new ImageIcon("share_this_icon.png"));
        this.btnShare.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.pnlAction.setLayout(new GridLayout());
        this.pnlAction.add(this.btnNew);
        this.pnlAction.add(this.btnEdit);
        this.pnlAction.add(this.btnDelete);
        this.pnlAction.add(this.btnRem);
        this.pnlAction.add(this.btnShare);
        this.pnlAction.add(this.btnSetting);
        this.pnlAction.add(this.btnSub);
        this.pnlAction.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
        this.btnNew.addActionListener(this);
        this.btnEdit.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnRem.addActionListener(this);
        this.btnShare.addActionListener(this);
        this.btnSetting.addActionListener(this);
  //       this.applySettings();
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==this.btnNew){
            StickyNote note=new StickyNote();
            // What does toolkit class do?
            note.applySettings();
            Toolkit tool=Toolkit.getDefaultToolkit();
            Dimension size=tool.getScreenSize();
            final int HEIGHT=250;
            note.setBounds(size.width/2-CommRes.WIDTH/2, size.height/2-HEIGHT/2, CommRes.WIDTH, HEIGHT);
            note.setVisible(true);
            note.setEditable();
            note.id=new Date().toString();
            note.setTitle(note.id);
            note.isNew=true;
            CommRes.notes.add(note);
        }
        
        // Operations on save: 
        if(ae.getSource()==this.btnEdit){
             if(this.isEditable){
                //Save Operation
                this.tareaTitle.setEditable(false);
                this.tareaDesc.setEditable(false);
                this.btnEdit.setIcon(new ImageIcon("apps-accessories-text-editor-icon.png"));
                this.isEditable=false;
                this.btnEdit.setToolTipText("EDIT");
                if(this.isNew){ //saving new note
                    NotesInfo nInfo=new NotesInfo();
                    nInfo.id=this.id;
                    nInfo.title=this.tareaTitle.getText();
                    nInfo.desc=this.tareaDesc.getText();
                    nInfo.createdOn=new Date().toString();
                    nInfo.subnotes=new ArrayList<String>();
                    this.isNew=false;
                    CommRes.notesColl.add(nInfo);
                }else{ //saving update of existing note
                   NotesInfo nInfo=CommRes.getInfoObject(this.id);
                    nInfo.title=this.tareaTitle.getText();
                    nInfo.desc=this.tareaDesc.getText();
                    nInfo.createdOn=new Date().toString();
                }
                CommRes.updateFile(); 
                if(CommRes.isFirst){
                    
                }
            }else{
                this.setEditable();
            }
        }
        if(ae.getSource()==this.btnDelete){
            // Remove elements from array list
            for(int i=0;i<CommRes.notesColl.size();i++){
                if(CommRes.notesColl.get(i).id==this.id)
                    CommRes.notesColl.remove(i);
                
            }
            // Rewrote the arraylist to file
            CommRes.updateFile();
            // Removed the note from display vector
            for(int i=0;i<CommRes.notes.size();i++){
                
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);
                    if(note.id==this.id){
                        CommRes.notes.remove(i);
                        break;
                    }
                }
                //disposed the window
                this.dispose();
        }
        
        if(ae.getSource()==this.btnSetting){
            if(Settings.isOpen==false)
            {
                Settings.isOpen=true;
                Settings win=new Settings();
                Toolkit tool=Toolkit.getDefaultToolkit();
                Dimension size=tool.getScreenSize();
                final int WIDTH=500;
                final int HEIGHT=400;
                win.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
                win.setTitle("Settings");
                win.setResizable(false);
                win.setVisible(true);
            }
        }
        
        if(ae.getSource()==this.btnRem){
            
            int prevHeight=this.getHeight();
            int pnlRemHeight=Reminder.height;
            this.setSize(CommRes.WIDTH, prevHeight+pnlRemHeight);
            int pnlActionHeight=this.pnlAction.getHeight();
            int pnlActionWidth=this.pnlAction.getWidth();
            int pnlActionX=this.pnlAction.getX();
            int pnlActionY=this.pnlAction.getY();
            this.pnlAction.setBounds(pnlActionX, pnlActionY+pnlRemHeight, pnlActionWidth, pnlActionHeight);
            Reminder pnl = new Reminder();
            this.add(pnl);
            pnl.setBounds(pnlActionX,pnlActionY,pnlActionWidth, pnlRemHeight);
            pnl.setVisible(true);
//this.setSize(WIDTH, HEIGHT);
        }
    }
    
    public void setEditable(){
        this.tareaTitle.setEditable(true);
        this.tareaDesc.setEditable(true);
        this.btnEdit.setIcon(new ImageIcon("save.png"));
        this.isEditable=true;
        this.btnEdit.setToolTipText("SAVE");
    }
    private void design(){
//        this.setPos(this.lblTitle,0,0,10);
        this.setPos(this.jspTitle,0,0,30);
 //       this.setPos(this.lblDesc,0,41,10);
        this.setPos(this.jspDesc,0,30,160);
        this.setPos(this.pnlAction,0,190,30);
    }
    private void setPos(Component C,int x,int y,int h){
        this.add(C);
        C.setBounds(x, y, CommRes.WIDTH+1, h);
       // C.setFont(CommRes.font);
    }
    public void applySettings(){
        
        
                Font fontTemp=new Font(CommRes.hInfo.font,CommRes.hInfo.style,CommRes.hInfo.size);
                
                this.jspTitle.setBackground(CommRes.hInfo.bkgColor);
                this.jspTitle.setForeground(CommRes.hInfo.foreColor);
                
                this.jspDesc.setBackground(CommRes.hInfo.bkgColor);
                this.jspDesc.setForeground(CommRes.hInfo.foreColor);
                
                this.tareaTitle.setBackground(CommRes.hInfo.bkgColor);
                this.tareaTitle.setForeground(CommRes.hInfo.foreColor);
                this.tareaTitle.setFont(fontTemp);
               
                this.tareaDesc.setBackground(CommRes.hInfo.bkgColor);
                this.tareaDesc.setForeground(CommRes.hInfo.foreColor);
                this.tareaDesc.setFont(fontTemp);
                
                
                this.btnDelete.setBackground(Color.WHITE);
                this.btnEdit.setBackground(Color.WHITE);
                this.btnNew.setBackground(Color.WHITE);
                this.btnRem.setBackground(Color.WHITE);
                this.btnSetting.setBackground(Color.WHITE);
                this.btnShare.setBackground(Color.WHITE);
                this.btnSub.setBackground(Color.WHITE);
                
                this.pnlAction.setBackground(Color.WHITE);
                this.pnlAction.setForeground(CommRes.hInfo.foreColor);
                
                this.setBackground(CommRes.hInfo.bkgColor);
                
        }
    }
  //  public void setColors(){
        
    //}

