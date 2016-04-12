//This class contains the common-information for all sticky notes which can be changed by the user. 
package stickyclient;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
/**
 *
 * @author aditi
 */
public class HeaderInfo implements Serializable{
    boolean isPassSet;
    String createdOn;
    String password;
    String font;
    int size;
    int style;
    Color foreColor;
    Color bkgColor;
}
