////This class defines the gui for font Tab inside settings window.
package stickyclient;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JColorChooser;
/**
 *
 * @author aditi
 */
public class FontSettings extends JPanel implements ActionListener{
    
    JComboBox cmbStyle,cmbFont,cmbSize;  //convention: Every combobox object will start with cmb
//convention: Every label object will start with lbl.
    JLabel lblFont,lblSize,lblStyle,lblForeColor,lblBkgColor;
    JButton btnChooseForeColor,btnChooseBkgColor,btnApply;
    JTextArea foreColorPrw,bkgColorPrw,fontSettingsPrw,overallPrw;
    @SuppressWarnings("empty-statement")
    public FontSettings(){
     this.setLayout(null);
        
     //Initializing the labels
        this.lblFont=new JLabel("Font");
        this.lblSize=new JLabel("Size");
        this.lblStyle=new JLabel("Style");
        this.lblForeColor=new JLabel("Foreground Color");
        this.lblBkgColor=new JLabel("Background Color");
    // Initializing the comboboxes -> First create the string array then initialize :)    
        this.cmbFont = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        this.bkgColorPrw=new JTextArea();
        this.foreColorPrw=new JTextArea();
        this.overallPrw=new JTextArea("This is Preview");
        this.btnChooseBkgColor=new JButton("Choose");
        this.btnChooseForeColor=new JButton("Choose");
        this.fontSettingsPrw=new JTextArea();
        this.btnApply=new JButton("Apply");
        String[] sizearray={ "12","13","14","15","16" };
        this.cmbSize = new JComboBox(sizearray);
        
        String[] stylearray={ "BOLD","ITALICS","PLAIN"};
        this.cmbStyle = new JComboBox(stylearray);
        
        this.design();
        // Setting Default values to combo boxes.
        this.cmbFont.setSelectedItem(CommRes.hInfo.font);
        this.cmbSize.setSelectedIndex(CommRes.hInfo.size-12);
        if(CommRes.hInfo.style==Font.BOLD)
        this.cmbStyle.setSelectedIndex(0);
        else if(CommRes.hInfo.style==Font.ITALIC)
        this.cmbStyle.setSelectedIndex(1);
        else if(CommRes.hInfo.style==Font.PLAIN)
        this.cmbStyle.setSelectedIndex(2);
    }
    private void design(){
        
        // convention: design method will always be uset to fix the positions of various 
        // components inside a window.
       this.setPos(this.lblFont,10,60,150,20);
       this.setPos(this.lblSize,10,100,150,20);
       this.setPos(this.lblStyle,10,140,150,20);
       this.setPos(this.lblForeColor,10,180,150,20);
       this.setPos(this.lblBkgColor,10,220,150,20);
       this.setPos(this.cmbFont,160,60,100,20);
       this.setPos(this.cmbSize,160,100,100,20);
       this.setPos(this.cmbStyle,160,140,100,20);
       this.setPos(this.btnChooseForeColor,160,180,100,20);
       this.setPos(this.foreColorPrw, 265, 180, 30, 20);
       this.setPos(this.btnChooseBkgColor,160,220,100,20);
       this.setPos(this.bkgColorPrw, 265, 220, 30, 20);
       this.setPos(this.overallPrw, 280, 62, 150, 50);
       this.foreColorPrw.setBackground(CommRes.hInfo.foreColor);
       this.bkgColorPrw.setBackground(CommRes.hInfo.bkgColor);
       this.overallPrw.setBackground(CommRes.hInfo.bkgColor);
       this.overallPrw.setForeground(CommRes.hInfo.foreColor);
       this.overallPrw.setFont(new Font(CommRes.hInfo.font,CommRes.hInfo.style,CommRes.hInfo.size));
       this.setPos(this.btnApply,260,300,100,20);
       this.setPos(this.fontSettingsPrw, WIDTH, WIDTH, WIDTH, WIDTH);
       this.btnChooseBkgColor.addActionListener(this);
       this.btnChooseForeColor.addActionListener(this);
       this.btnApply.addActionListener(this);
       this.cmbFont.addActionListener(this);
       this.cmbSize.addActionListener(this);
       this.cmbStyle.addActionListener(this);
    }
    //User defined method (just for ease of readability) component to set each component
    private void setPos(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Font font = new Font("Verdana", Font.BOLD, 12);
            
            String tempFont=CommRes.hInfo.font;
            int tempSize=CommRes.hInfo.size;
            int tempStyle=CommRes.hInfo.style;
            Font temp;
            Color fColor,bColor;
        if(ae.getSource()==this.btnChooseBkgColor)
        {
            bColor = JColorChooser.showDialog(this,"Background Color", CommRes.hInfo.bkgColor);
            this.bkgColorPrw.setBackground(bColor);
            this.overallPrw.setBackground(bColor);
        }
        if(ae.getSource()==this.btnChooseForeColor)
        {
            fColor = JColorChooser.showDialog(this,"Foreground Color", CommRes.hInfo.foreColor);
            this.foreColorPrw.setBackground(fColor);
            this.overallPrw.setForeground(fColor);
        }
        if(ae.getSource()==this.cmbFont)
        {
            tempFont=this.cmbFont.getSelectedItem().toString();
            tempSize=Integer.parseInt(this.cmbSize.getSelectedItem().toString());
            String abc=this.cmbStyle.getSelectedItem().toString();
            if(abc=="BOLD")tempStyle=Font.BOLD;
            if(abc=="ITALICS")tempStyle=Font.ITALIC;
            if(abc=="PLAIN")tempStyle=Font.PLAIN;
            temp=new Font(tempFont,tempStyle,tempSize);
            this.overallPrw.setFont(temp);
        }
        if(ae.getSource()==this.cmbSize)
        {
            tempFont=this.cmbFont.getSelectedItem().toString();
            tempSize=Integer.parseInt(this.cmbSize.getSelectedItem().toString());
            String abc=this.cmbStyle.getSelectedItem().toString();
            if(abc=="BOLD")tempStyle=Font.BOLD;
            if(abc=="ITALICS")tempStyle=Font.ITALIC;
            if(abc=="PLAIN")tempStyle=Font.PLAIN;
            temp=new Font(tempFont,tempStyle,tempSize);
            this.overallPrw.setFont(temp);
        }
        if(ae.getSource()==this.cmbStyle)
        {
            tempFont=this.cmbFont.getSelectedItem().toString();
            tempSize=Integer.parseInt(this.cmbSize.getSelectedItem().toString());
            String abc=this.cmbStyle.getSelectedItem().toString();
            if(abc=="BOLD")tempStyle=Font.BOLD;
            if(abc=="ITALICS")tempStyle=Font.ITALIC;
            if(abc=="PLAIN")tempStyle=Font.PLAIN;
            temp=new Font(tempFont,tempStyle,tempSize);
            this.overallPrw.setFont(temp);
        }
        if(ae.getSource()==this.btnApply)
        {
            CommRes.hInfo.font = cmbFont.getSelectedItem().toString();
            String x= cmbStyle.getSelectedItem().toString();
            String y= cmbSize.getSelectedItem().toString();
            if(x=="BOLD"){ CommRes.hInfo.style = Font.BOLD;}
            if(x=="ITALICS"){ CommRes.hInfo.style = Font.ITALIC;}
            if(x=="PLAIN"){ CommRes.hInfo.style = Font.PLAIN;}
            CommRes.hInfo.size= Integer.parseInt(y);
            CommRes.hInfo.foreColor=this.foreColorPrw.getBackground();
            CommRes.hInfo.bkgColor=this.bkgColorPrw.getBackground();
            CommRes.updateFile();
            for(int i=0;i<CommRes.notes.size();i++){
                    StickyNote note=(StickyNote)CommRes.notes.elementAt(i);;
                    note.applySettings();
            }
        }
       
    }
    
}
