//This class contains the common-information for all sticky notes. 

package stickyclient;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author aditi
 */
public class CommRes {
   
    public static boolean isFirst;  //True if the application is run for the first time.
    public static final int WIDTH=200;  
    public static Font font=new Font("Arial",Font.ITALIC,12);
    public static Vector notes;     //Vector to store all the note windows.
    public static HeaderInfo hInfo; // Class that stores common but alterable information. 
    public static ArrayList<NotesInfo> notesColl;  //Arraylist to transfet all notes between file and corresponding window.
      public static void updateFile(){
        try{
            FileOutputStream fout=new FileOutputStream("Notes.stk",false);
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(hInfo);
            out.writeObject(notesColl);
            out.close();
            fout.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Problem while Saving/Updating Sickey Notes!!!","Sticky Notes",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static NotesInfo getInfoObject(String id){
        for(int i=0;i<notesColl.size();i++){
            if(notesColl.get(i).id.equals(id))
                return notesColl.get(i);
        }
        return null;
    }
}
