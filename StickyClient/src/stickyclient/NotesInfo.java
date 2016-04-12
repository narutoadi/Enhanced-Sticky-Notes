/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickyclient;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author pc
 */
public class NotesInfo  implements Serializable{
    String id;
    String title;
    String desc;
    String createdOn;
    ArrayList<String> subnotes; //Arraylist to transfer subnotes-content between file and window
    
    
    
    int X;
    int Y;
}
