/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickyserver;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author pc
 */



public class ClientThread  extends Thread{

    Socket client;
    public ClientThread(Socket client){
        this.client=client;
        this.start();
    }
    
    private int setData(String query)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/stickynote","root","aditi");
        Statement stmt=con.createStatement();
        int n=stmt.executeUpdate(query);
        return n;
    }
    private ResultSet getData(String query)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/stickynote","root","aditi");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }
    public void run(){
        try{
            while(true){
                ObjectInputStream in=new ObjectInputStream(this.client.getInputStream());
                String msg=in.readObject().toString();
                if(msg.equals("Register")){
                    String fName=in.readObject().toString();
                    String lName=in.readObject().toString();
                    String usrName=in.readObject().toString();
                    String pass=in.readObject().toString();
                 
                    String query2="select * from usermaster where userid = '"+usrName+"'";
                    ResultSet rs = this.getData(query2);
                    int n=1;
                    
                    if(rs.next())
                    {
                        n=0;
                    }
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(n + "");
            //        Date date; 
              //      date = new Date();
                if(n==1)
                {
                    String query="insert into usermaster set " +
                                  "Firstname='"+ fName + "',"+
                                  "Lastname='"+lName+"',"+
                                 "Userid='" + usrName + "', "+
                                  "Password='"+pass+"'"  ;
                    n=this.setData(query);
                  //  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(n + "");
                }
                }
                if(msg.equals("Login"))
                {
                    String usrName=in.readObject().toString();
                    String pass=in.readObject().toString();
                    int n;
                    String query1="select * from usermaster where userid = '"+usrName+"'";
                    ResultSet rs;
                    rs = this.getData(query1);
                    n=0;
                    if(rs.next()){
                        n=1;
                        String query2 ="select * from usermaster where userid = '"+usrName+"'and "+
                                "password = '"+pass+"'";
                        ResultSet rs1;
                        rs1 = this.getData(query2);
                        if(rs1.next())
                        {
                            n=2;
                        }
                    }
                    
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(n+"");
                }
                if(msg.equals("GetAllUsers"))
                {
                    String query2="select userid from usermaster;";
                    ResultSet rs2;
                    rs2 = this.getData(query2);
                    Vector users=new Vector();
                    while(rs2.next())
                    {
                        String user=rs2.getString("userid");
                        users.add(user);
                    }
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(users);

                }
            }
        }catch(Exception ex){
            System.out.println("Error on Server :" +ex);
        }
    }
}
