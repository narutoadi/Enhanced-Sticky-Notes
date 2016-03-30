/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickyclient;
import java.io.*;

/**
 *
 * @author aditi
 */
public class StickyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            File file=new File("Notes.stk");
            if(file==null)
            {
                CommRes.isFirst=true;
                //Since there is no file, so lets create the only file
                //which is going to store the whole information
                FileOutputStream fout = new FileOutputStream("Notes.stk");
                /*
                Lets decide the structure of the file
                the file will first have the object of class 'headerInfo'
                and then objects of class 'note'
                */
                ObjectOutputStream out = new ObjectOutputStream(fout);
                HeaderInfo hinfo =new HeaderInfo();
                out.writeObject(hinfo);
                out.close();
                fout.close();
            }
            else
            {
                CommRes.isFirst=false;
                FileInputStream fin = new FileInputStream("Notes.stk");
                ObjectInputStream in = new ObjectInputStream(fin);
                in.readObject();
                HeaderInfo hinfo = (HeaderInfo) in.readObject();
                
                // Now store all these objects in a vector and invoke a window for 
                //each object.
            }
            
        }catch(Exception e){
            
        }
    }
    
}
