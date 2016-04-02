////This class defines the gui for font Tab inside settings window.
package stickyclient;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author aditi
 */
public class FontSettings extends JPanel {
    
    JComboBox cmbFont;  //convention: Every combobox object will start with cmb.
    
    JComboBox cmbSize;
    
    JComboBox cmbStyle;
  
    JComboBox cmbColor;
    
    JLabel lblFont; //convention: Every label object will start with lbl.
    JLabel lblSize;
    JLabel lblStyle;
    JLabel lblColor;
    
   
    
    @SuppressWarnings("empty-statement")
    public FontSettings(){
     this.setLayout(null);
        
     //Initializing the labels
        this.lblFont=new JLabel("Font");
        this.lblSize=new JLabel("Size");
        this.lblStyle=new JLabel("Style");
        this.lblColor=new JLabel("Color");
              
    // Initializing the comboboxes -> First create the string array then initialize :)    
        this.cmbFont = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
                
        String[] sizearray={ "12","13","14","15","16" };
        this.cmbSize = new JComboBox(sizearray);
        
                        
        String[] stylearray={ "BOLD","ITALICS"};
        this.cmbStyle = new JComboBox(stylearray);
        
        this.design();
    }
    private void design(){
        
        // convention: design method will always be uset to fix the positions of various 
        // components inside a window.
       this.setPos(this.lblFont,10,60,100,20);
       this.setPos(this.lblSize,10,100,100,20);
       this.setPos(this.lblStyle,10,140,100,20);
       this.setPos(this.lblColor,10,180,100,20);
       this.setPos(this.cmbFont,50,60,100,20);
       this.setPos(this.cmbSize,50,100,100,20);
       this.setPos(this.cmbStyle,50,140,100,20);
       
    }
    //User defined method (just for ease of readability) component to set each component
    private void setPos(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
}
