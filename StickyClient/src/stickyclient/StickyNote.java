/*

 */

package stickyclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author aditi
 */
public class StickyNote extends JFrame implements ActionListener{
    // A unique id for each sticky note that stores the date and time of creation
    ArrayList<SubNote> subnotecoll;
    String id;
    // To give same button (btnEdit) 2 features at a time 
    // When isEditable
    boolean isEditable;
    boolean isNew;
    boolean showhide; // not pressed even once
    JTextArea tareaTitle,tareaDesc;
    JScrollPane jspTitle,jspDesc;
 //   JLabel lblTitle,lblDesc;
    JPanel pnlAction;
    JButton btnNew,btnDelete,btnEdit,btnSub,btnRem,btnShare,btnSetting,btnShowSub;
    public StickyNote(){
        
        this.showhide=false;
        this.subnotecoll = new ArrayList<SubNote>();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
             if(StickyNote.this.isNew==true)
                {NotesInfo nInfo=new NotesInfo();
                    nInfo.id=StickyNote.this.id;
                    nInfo.title=StickyNote.this.tareaTitle.getText();
                    nInfo.desc=StickyNote.this.tareaDesc.getText();
                    nInfo.createdOn=new Date().toString();
                    nInfo.subnotes=new ArrayList<String>();
                    nInfo.X=StickyNote.this.getX();
                    nInfo.Y=StickyNote.this.getY();
                    StickyNote.this.isNew=false;
                    CommRes.notesColl.add(nInfo);
                }
             else{ //saving update of existing note
                   NotesInfo nInfo=CommRes.getInfoObject(StickyNote.this.id);
                    nInfo.title=StickyNote.this.tareaTitle.getText();
                    nInfo.desc=StickyNote.this.tareaDesc.getText();
                    nInfo.createdOn=new Date().toString();
                    nInfo.X=StickyNote.this.getX();
                    nInfo.Y=StickyNote.this.getY();
                }
//                    for(int i=0;i<CommRes.notesColl.size();i++){
//                if(CommRes.notesColl.get(i).id==StickyNote.this.id)
//                {
//                    CommRes.notesColl.remove(i);
//                }    
//            }
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
  //      this.jspTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 2, 0));
        this.jspDesc=new JScrollPane(this.tareaDesc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jspDesc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        this.pnlAction=new JPanel();
       // this.pnlAction.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.btnShowSub=new JButton(new ImageIcon("actions-arrow-down-icon.png"));
        this.tareaTitle.setWrapStyleWord(true);
        this.tareaTitle.setLineWrap(true);
        this.tareaDesc.setWrapStyleWord(true);
        this.tareaDesc.setLineWrap(true);
        
      //  this.add(this.jspTitle);
      //  this.jspTitle.setBounds(0, 0, CommRes.WIDTH+1, 30);
        this.design();
      // Title and show-hide subnotes icon
      this.add(this.jspTitle);
      this.add(this.btnShowSub);
      this.jspTitle.setBounds(0, 0, CommRes.WIDTH-30, 30);
      this.btnShowSub.setBounds(CommRes.WIDTH-30, 0, 30, 30);
      this.btnShowSub.setBackground(Color.WHITE);
        
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
        this.btnSub.addActionListener(this);
        this.btnShowSub.addActionListener(this);
  //       this.applySettings();
    }
    
    // Inner class for subnotes
    
    class SubNote implements ActionListener
    {
        int index,smallpnlht,largepnlht;
        boolean isNew=true;
        boolean smallshow=true;
        //For small panel :)
        JPanel pnlSubSmall;
        JButton btnSmallShowHide;
        //For large panel :)
        JPanel pnlSubLarge;
        JTextArea tareaSub;
        JScrollPane jsptareaSub;
        JButton btnSubSave,btnSubDelete;
        
        public SubNote()
        {
            //Lets design the small panel.
            this.smallpnlht=16;
            this.largepnlht=60;
            this.pnlSubSmall = new JPanel();
            this.pnlSubSmall.setBackground(Color.WHITE);
            this.pnlSubSmall.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
            this.btnSmallShowHide = new JButton(new ImageIcon("arrows-down-4-icon.png"));
            this.pnlSubSmall.setSize(CommRes.WIDTH,smallpnlht);
            this.pnlSubSmall.setLayout(null);
            this.pnlSubSmall.add(this.btnSmallShowHide);
            this.btnSmallShowHide.setBounds(CommRes.WIDTH-this.smallpnlht,0, this.smallpnlht,this.smallpnlht);

            //Lets design the large panel.
            this.pnlSubLarge = new JPanel();
            this.btnSubSave = new JButton(new ImageIcon("save.png"));
            this.btnSubSave.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            this.btnSubSave.setBackground(Color.WHITE);
            this.btnSubDelete=new JButton(new ImageIcon("delete.png"));
            this.btnSubDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            this.btnSubDelete.setBackground(Color.WHITE);
            this.tareaSub=new JTextArea();
            this.jsptareaSub=new JScrollPane(this.tareaSub,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.jsptareaSub.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
            this.pnlSubLarge.setLayout(null);
            this.pnlSubLarge.add(this.btnSubSave);
            this.btnSubSave.setBounds(CommRes.WIDTH-this.smallpnlht, 0, this.smallpnlht, this.largepnlht/2);
            this.pnlSubLarge.add(this.btnSubDelete);
            this.btnSubDelete.setBounds(CommRes.WIDTH-this.smallpnlht, this.largepnlht/2, this.smallpnlht, this.largepnlht/2);
            this.pnlSubLarge.add(this.jsptareaSub);
            this.jsptareaSub.setBounds(0, 0, CommRes.WIDTH-this.smallpnlht, this.largepnlht);
            this.tareaSub.setWrapStyleWord(true);
            this.tareaSub.setLineWrap(true);           
            this.btnSubDelete.addActionListener(this);
            this.btnSubSave.addActionListener(this);
            this.btnSmallShowHide.addActionListener(this);
        }
        public void ShowSmallPanelatEnd(int y )
        {
            StickyNote.this.setSize(CommRes.WIDTH, StickyNote.this.getHeight()+this.smallpnlht);
            StickyNote.this.add(this.pnlSubSmall);
            this.pnlSubSmall.setVisible(true);
            this.pnlSubSmall.setBounds(0, y, CommRes.WIDTH, this.smallpnlht);
            StickyNote.this.pnlAction.setBounds(0, y+this.smallpnlht, CommRes.WIDTH, StickyNote.this.pnlAction.getHeight());
        }
        public void ShowLargePanelatEnd(int y )
        {
            StickyNote.this.setSize(CommRes.WIDTH, StickyNote.this.getHeight()+this.largepnlht);
            StickyNote.this.add(this.pnlSubLarge);
            this.pnlSubLarge.setVisible(true);
            this.pnlSubLarge.setBounds(0, y, CommRes.WIDTH, this.largepnlht);
            StickyNote.this.pnlAction.setBounds(0, y+this.largepnlht, CommRes.WIDTH, StickyNote.this.pnlAction.getHeight());
        }
        public void Expand(int y) //throws InterruptedException
        {
            StickyNote.this.btnSub.setEnabled(false);
            y=y+smallpnlht;
            this.btnSmallShowHide.setIcon(new ImageIcon("actions-arrow-up-icon.png"));
            this.smallshow=false;
            int x=StickyNote.this.getHeight();
            System.out.println("Window size = "+x);
            StickyNote.this.setSize(CommRes.WIDTH, x+this.largepnlht);
            System.out.println("Size incremented");
//           try{
//            sleep(1000);
//           }catch(Exception e){
//               System.out.print("Can't Sleep");
//           }
          //  this.pnlSubSmall.setVisible(false);
           //StickyNote.this.add(this.pnlSubLarge);
            
            int curht=y+this.largepnlht;
            System.out.println(StickyNote.this.subnotecoll.size());
            for(int i=0;i<this.index;i++)
            {
                StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(false);
            }
            for(int i=this.index+1;i<StickyNote.this.subnotecoll.size();i++)
            {
                System.out.println(i);
                System.out.println(curht);
                //StickyNote.this.add(StickyNote.this.subnotecoll.get(i).pnlSubSmall);
                StickyNote.this.subnotecoll.get(i).pnlSubSmall.setVisible(false);
                StickyNote.this.subnotecoll.get(i).pnlSubSmall.setBounds(0, curht, CommRes.WIDTH,smallpnlht );
                
                StickyNote.this.subnotecoll.get(i).pnlSubSmall.setVisible(true);
                StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(false);
                curht=curht+smallpnlht;
                System.out.println("One small added");
//                try{
//            sleep(1000);
//           }catch(Exception e){
//               System.out.print("Can't Sleep");
//           }
            }
            StickyNote.this.pnlAction.setBounds(0, curht, CommRes.WIDTH,StickyNote.this.pnlAction.getHeight() );
          //  this.pnlSubLarge.setVisible(true);
          NotesInfo nInfo=CommRes.getInfoObject(id);
          this.tareaSub.setText(nInfo.subnotes.get(this.index)); 
          StickyNote.this.add(this.pnlSubLarge);
          StickyNote.this.subnotecoll.get(this.index).pnlSubLarge.setVisible(false);
          StickyNote.this.subnotecoll.get(this.index).pnlSubLarge.setBounds(0, y, CommRes.WIDTH, this.largepnlht);
          StickyNote.this.subnotecoll.get(this.index).pnlSubLarge.setVisible(true);
        }
        public void Shrink(int y)
        {
            StickyNote.this.btnSub.setEnabled(true);
         //   y=y+smallpnlht;
            this.btnSmallShowHide.setIcon(new ImageIcon("arrows-down-4-icon.png"));
            this.smallshow=true;
            
            StickyNote.this.setSize(CommRes.WIDTH, StickyNote.this.getHeight()-this.largepnlht);
            StickyNote.this.subnotecoll.get(this.index).pnlSubLarge.setVisible(false);
            //StickyNote.this.add(this.pnlSubSmall);
            //this.pnlSubSmall.setVisible(true);
            //this.pnlSubSmall.setBounds(0, y, CommRes.WIDTH, this.smallpnlht);
           for(int i=0;i<this.index;i++)
            {
                StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(true);
            }
            int curht=y+smallpnlht;
            for(int i=this.index+1;i<StickyNote.this.subnotecoll.size();i++)
            {
                
                StickyNote.this.subnotecoll.get(i).pnlSubSmall.setBounds(0, curht, CommRes.WIDTH, smallpnlht );
                StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(true);
                curht=curht+this.smallpnlht;
            }
            StickyNote.this.pnlAction.setBounds(0, curht, CommRes.WIDTH,StickyNote.this.pnlAction.getHeight() );
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==this.btnSubSave)
            {
                
            if(this.isNew==false)
            {
                NotesInfo nInfo=CommRes.getInfoObject(StickyNote.this.id);
               // String s=this.tareaSub.getText();
                
                nInfo.subnotes.set(this.index, this.tareaSub.getText());
                CommRes.updateFile();
                this.Shrink(this.pnlSubSmall.getY());
            }
                 else
                 {
                NotesInfo nInfo=CommRes.getInfoObject(StickyNote.this.id);
                  //  this.index=nInfo.subnotes.size();
                    if(nInfo==null)
                    {
                        JOptionPane.showMessageDialog(StickyNote.this, "You need to save the note before saving the subnote :)");
                    }
                  else
                    {
                        nInfo.title=StickyNote.this.tareaTitle.getText();
                        nInfo.desc=StickyNote.this.tareaDesc.getText();
                        nInfo.createdOn=new Date().toString();
                        nInfo.subnotes.add(this.tareaSub.getText());

                        CommRes.updateFile();
                    
                //Hide the note
                    int y=this.pnlSubLarge.getY();
                    System.out.println(y);
                    int ht=StickyNote.this.getHeight();
                    System.out.println(ht);
                    this.pnlSubLarge.setVisible(false);
                    StickyNote.this.setSize(CommRes.WIDTH,ht-this.largepnlht);
                    StickyNote.this.pnlAction.setBounds(0, y, CommRes.WIDTH, 30);
                    StickyNote.this.btnSub.setEnabled(true);
                    StickyNote.this.btnShowSub.setEnabled(true);
                     }
                 }
                //System.out.println("Subnotes in "+CommRes.notesColl.get(j).id+" = "+CommRes.notesColl.get(j).subnotes.size());
            }
            if(ae.getSource()==this.btnSubDelete)
            {
                if(this.isNew==false)
                {
                    NotesInfo nInfo=CommRes.getInfoObject(StickyNote.this.id);
                    nInfo.subnotes.remove(this.index);
                    for(int i=0;i<index;i++)
                    {
                        StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(true);
                    }
                    int y=this.pnlSubSmall.getY();
                    StickyNote.this.subnotecoll.get(this.index).pnlSubSmall.setVisible(false);
                    StickyNote.this.subnotecoll.get(this.index).pnlSubLarge.setVisible(false);
                    StickyNote.this.subnotecoll.remove(this.index);
                    
                    for(int i=index;i<StickyNote.this.subnotecoll.size();i++)
                    {
                      StickyNote.this.subnotecoll.get(i).index=i;
                      StickyNote.this.subnotecoll.get(i).pnlSubSmall.setBounds(0, y, CommRes.WIDTH, smallpnlht );
                      StickyNote.this.subnotecoll.get(i).btnSmallShowHide.setEnabled(true);
                      y=y+smallpnlht;
                    }
                    StickyNote.this.pnlAction.setBounds(0, y, CommRes.WIDTH,StickyNote.this.pnlAction.getHeight() );
                    StickyNote.this.setSize(CommRes.WIDTH, StickyNote.this.getHeight()-smallpnlht-largepnlht);
                }
                else
                {
                    int y=this.pnlSubLarge.getY();
                    int ht=StickyNote.this.getHeight();
                    this.pnlSubLarge.setVisible(false);
                    StickyNote.this.setSize(CommRes.WIDTH,ht-this.largepnlht);
                    StickyNote.this.pnlAction.setBounds(0, y, CommRes.WIDTH, StickyNote.this.pnlAction.getHeight());
                    StickyNote.this.btnSub.setEnabled(true);
                    StickyNote.this.btnShowSub.setEnabled(true);
                }
            }
            if(ae.getSource()==this.btnSmallShowHide)
            {
                if(this.smallshow==true)
                {
                    
                    int y=this.pnlSubSmall.getY();
                    this.Expand(y);
                }
                else
                {
                    int y=this.pnlSubSmall.getY();
                    this.Shrink(y);
                }
                
                
                
            }
           
            
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==this.btnShowSub){
            System.out.println(this.showhide);
            if(this.showhide==false)
            {
                JLabel jPassword = new JLabel("Password");
                JTextField password = new JPasswordField();
                Object[] ob = { jPassword, password};
                int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
 
            if (result == JOptionPane.OK_OPTION)
            {
                this.showhide=true;
                this.btnShowSub.setIcon(new ImageIcon("actions-arrow-up-icon (1).png"));
                NotesInfo nInfo=CommRes.getInfoObject(this.id);
                if(nInfo==null){System.out.println("It is new");}
                else
                {
                    System.out.println(nInfo.subnotes.size());
                    for(int i=0;i<nInfo.subnotes.size();i++){
                      SubNote sub = new SubNote();
                      sub.index=i;
                      sub.isNew=false;
                      this.subnotecoll.add(sub);
                      sub.ShowSmallPanelatEnd(this.pnlAction.getY());
                    }
                }
            }
           }
            else if(this.showhide==true)
            {
                this.showhide=false;
                this.btnShowSub.setIcon(new ImageIcon("actions-arrow-down-icon.png"));
                System.out.println(this.subnotecoll.size());
                for(int i=0;i<this.subnotecoll.size();i++)
                {
                    SubNote sub = this.subnotecoll.get(i);
                    sub.pnlSubSmall.setVisible(false);
                    sub.pnlSubLarge.setVisible(false);
                }
                this.setSize(CommRes.WIDTH,250);
                this.subnotecoll.clear();
                this.pnlAction.setBounds(0, 190,CommRes.WIDTH,30);
                this.pnlAction.setVisible(true);
                
            }
            this.btnSub.setEnabled(true);
        }
        
        
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
            Reminder pnl = new Reminder(this);
            this.add(pnl);
            pnl.setBounds(pnlActionX,pnlActionY,pnlActionWidth, pnlRemHeight);
            pnl.setVisible(true);
        }
        if(ae.getSource()==this.btnSub){
            
            //int n = JOptionPane.showConfirmDialog(this,"Are you sure that you want to delete all sticky notes?","Confirmation!!",JOptionPane.YES_NO_OPTION);
           // JOptionPane.showMessageDialog(this,"Eggs are not supposed to be green.","Inane warning",JOptionPane.WARNING_MESSAGE);
           if(CommRes.hInfo.isPassSet==false)
           {
               JOptionPane.showMessageDialog(this, "Set Password at Settings > Option > Set Password");
           }
           else
           {
             
                JLabel jPassword = new JLabel("Password");
                JTextField password = new JPasswordField();
                Object[] ob = { jPassword, password};
                int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
 
        if (result == JOptionPane.OK_OPTION)
        {
             String passwordValue = password.getText();
             if(passwordValue.equals(CommRes.hInfo.password))  
             {
                 SubNote sub = new SubNote();
                 sub.ShowLargePanelatEnd(this.pnlAction.getY());
                 StickyNote.this.btnSub.setEnabled(false);
                 StickyNote.this.btnShowSub.setEnabled(false);
             }
             else
             {
                 JOptionPane.showMessageDialog(this, "Wrong Password !!!");
                 
             }
           // Here is some validation code
        }        
        }
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
//        this.setPos(this.jspTitle,0,0,30);
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

