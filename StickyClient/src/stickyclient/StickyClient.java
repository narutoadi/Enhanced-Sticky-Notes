//

package stickyclient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author pc
 */
public class StickyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CommRes.notes=new Vector();
//        StickyNote note=new StickyNote();
//        Toolkit tool=Toolkit.getDefaultToolkit();
//        Dimension size=tool.getScreenSize();
//        final int HEIGHT=250;
//        note.setBounds(size.width/2-CommRes.WIDTH/2, size.height/2-HEIGHT/2, CommRes.WIDTH, HEIGHT);
//        note.setVisible(true);
//        note.setEditable();
//        note.id=new Date().toString();
//        note.setTitle(note.id);
//        CommRes.notes.add(note);
//        
        try{
            File file=new File("Notes.stk");
            if(!file.exists()){
                CommRes.isFirst=true;
                CommRes.hInfo=new HeaderInfo();
                // Initializing members of headerinfo (with default values)
                // This values can be later changed by settings tab.
                CommRes.hInfo.createdOn="";
                CommRes.hInfo.isPassSet=false;
                CommRes.hInfo.password="";
                CommRes.hInfo.font="Arial";
                CommRes.hInfo.size=12;
                CommRes.hInfo.style=Font.PLAIN;
                CommRes.hInfo.foreColor=Color.BLACK;
                CommRes.hInfo.bkgColor=Color.WHITE;
                CommRes.notesColl=new ArrayList<NotesInfo>();
                
                // Sticky note GUI handling
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
            }else{
                CommRes.isFirst=false;
                FileInputStream fin=new FileInputStream(file);
                ObjectInputStream in=new ObjectInputStream(fin);
                
                // Reading hInfo and notesColl from file and initializing them.
                CommRes.hInfo=(HeaderInfo)in.readObject();
                CommRes.notesColl=(ArrayList<NotesInfo>)in.readObject();
                //read all notes info
                if(CommRes.notesColl.size()==0){
                    // Sticky note GUI handling
                StickyNote note=new StickyNote();
                // TO show the first note at the centre of screen we need to
                // get size of the screen first
                Toolkit tool=Toolkit.getDefaultToolkit();
                Dimension size=tool.getScreenSize();
                // Default height of a sticky note
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
                for(int i=0;i<CommRes.notesColl.size();i++){
                    StickyNote note=new StickyNote();
                 //   Toolkit tool=Toolkit.getDefaultToolkit();
                   // Dimension size=tool.getScreenSize();
                   note.applySettings(); 
                   final int HEIGHT=250;
                    note.setBounds(CommRes.notesColl.get(i).X,CommRes.notesColl.get(i).Y, CommRes.WIDTH, HEIGHT);
                    note.setVisible(true);
                    note.tareaTitle.setText(CommRes.notesColl.get(i).title);
                    note.tareaDesc.setText(CommRes.notesColl.get(i).desc);
                    note.id=CommRes.notesColl.get(i).id;
                    System.out.println("Subnotes in "+CommRes.notesColl.get(i).id+" = "+CommRes.notesColl.get(i).subnotes.size());
                    note.isNew=false;
                    note.setTitle(note.id);
                    CommRes.notes.add(note);
                }
            }
        }catch(Exception ex){
            
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    
}
