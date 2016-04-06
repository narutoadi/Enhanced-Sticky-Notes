// This class has a tabbed pane with 3 tabs and 3 buttons 'Apply', 'Ok', 'Cancel' 
package stickyclient;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author aditi
 */
public class Settings extends JFrame{
    public static boolean isOpen=false;
    FontSettings fontSettings;
    Reset reset;
    NetworkSettings networkSettings;
//    JButton btnApply;
//    JButton btnCancel;
//    JButton btnOk;
    JTabbedPane jtb;
    
    public Settings(){
        this.setLayout(null);
       // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                Settings.isOpen=false;
                Settings.this.dispose();
            }
        });
        
        this.jtb=new JTabbedPane();
        this.fontSettings=new FontSettings();
        this.reset=new Reset();
        this.networkSettings=new NetworkSettings();
//        this.btnApply=new JButton("APPLY");
//        this.btnCancel=new JButton("CANCEL");
//        this.btnOk=new JButton("OK");
//        
        this.jtb.addTab("FONT", fontSettings);
        this.jtb.addTab("RESET", reset);
        this.jtb.addTab("NETWORK", networkSettings);
        
        this.design();
    }
    
    private void design(){
        this.setPos(this.jtb,2,10,450,360);
//        this.setPos(this.btnApply, 220,320, 80, 20);
//        this.setPos(this.btnOk, 300, 320, 60, 20);
//        this.setPos(this.btnCancel, 360, 320, 100, 20);
    }
     private void setPos(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
}
