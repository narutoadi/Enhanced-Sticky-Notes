/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickyclient;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
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
        
//        try{
//            File file=new File("Notes.stk");
//            if(file==null){
//                CommRes.isFirst=true;
//                FileOutputStream fout=new FileOutputStream("Notes.stk");
//                ObjectOutputStream out=new ObjectOutputStream(fout);
//                HeaderInfo hinfo=new HeaderInfo();
//                out.writeObject(hinfo);
//                out.close();
//                fout.close();
//            }else{
//                CommRes.isFirst=false;
//                FileInputStream fin=new FileInputStream(file);
//                ObjectInputStream in=new ObjectInputStream(fin);
//                HeaderInfo hinfo=(HeaderInfo)in.readObject();
//                //read all notes info
//            }
//        }catch(Exception ex){
//            
//        }
    }
    
}
