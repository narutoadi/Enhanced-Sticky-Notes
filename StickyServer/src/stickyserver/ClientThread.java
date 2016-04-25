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
                if(msg.equals("Uploading")){
                    System.out.println("I am uploading here0");
                    String uname =in.readObject().toString(); // sender
                    System.out.println("I am uploading here1"+uname);
                    String title =in.readObject().toString();
                    System.out.println("I am uploading here2"+title);
                    String descr =in.readObject().toString();
                    System.out.println("I am uploading here3"+descr);
                    String noteid=in.readObject().toString();
                    System.out.println("I am uploading here4"+noteid);
                   // String[] rname = null;
                    String[] subnotes=null;
                    System.out.println("I am uploading here5");
                    String x=in.readObject().toString();
                    if(x.equals("ReceiverNames"))
                    {
                        System.out.println("I am uploading here6");
                        String[] rname=(String[])in.readObject();
                        System.out.println("I am uploading here7");
                    
                    
                    /*
                     String query="insert into usermaster set " +
                                  "Firstname='"+ fName + "',"+
                                  "Lastname='"+lName+"',"+
                                 "Userid='" + usrName + "', "+
                                  "Password='"+pass+"'"  ;
                    n=this.setData(query);
                    */
                    if(rname.length>0)
                    {
                          String query;
                          int n,num = 0;
//                        System.out.println("I am uploading here8");
//                        String query= "insert into notesmaster set "+
//                                "title='"+title+"', "+
//                                "description='"+descr+"', "+
//                                "noteid='"+noteid+"'";
//                        int n=this.setData(query);
//                        System.out.println("I am uploading here"+n);
                        
                            subnotes=(String[])in.readObject();
                         for(int i=0;i<rname.length;i++)
                        {
                            System.out.println("I am uploading here i ="+i);
                            query= "insert into transaction set "+
                                    "senderid='"+ uname +"', "+
                                    "receiverid='"+rname[i]+"', "+
                                    "title= '"+title+"' ,"+
                                    "description= '"+descr+"', "+
                                    "nid='"+noteid+"';";
                            n=this.setData(query);
                            System.out.println("I am uploading here"+n);
                            query = "select max(id) as xyz from transaction;";
                            ResultSet rs=this.getData(query);
                            if(rs.next())
                            {
                                num=Integer.parseInt(rs.getString("xyz"));
                            }
                            for(int j=0;j<subnotes.length;j++)
                        {
                            query="insert into subnotemaster set "+
                                    "description='"+subnotes[j]+"', "+
                                    "transactionid='"+num+"',"+
                                    "nid='"+noteid+"';";
                            n=this.setData(query);
                            System.out.println("I am uploading here"+n);
                        }
                        }
                    }
                    
                }
                }
                if(msg.equals("Check"))
                {
                    String count = null;
                    String sender = null;
                    Vector vSender = new Vector();
                    String id =null;
                    Vector vId = new Vector();
                    String descr =null;
                    Vector vDescr = new Vector();
                    String title = null;
                    Vector vTitle = new Vector();
                    String nid = null;
                    Vector vNid = new Vector();
                    String uname=in.readObject().toString();
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    String query= "select count(*) as xyz from transaction where receiverid = '"+uname+"';";
                    ResultSet rs = this.getData(query);
                    if(rs.next())
                    {
                        count=rs.getString("xyz");
                        out.writeObject(count);
                    }
                    int n=Integer.parseInt(count);
                    query = "select senderid, title, nid, id, description from transaction where receiverid = '"+uname+"';";
                    rs = this.getData(query);
                    while(rs.next())
                    {
                        System.out.println(n);
                        sender = rs.getString("senderid");
                        title = rs.getString("title");
                        nid = rs.getString("nid");
                        id =rs.getString("id");
                        descr=rs.getString("description");
                        vSender.add(sender);
                        vTitle.add(title);
                        vNid.add(nid);
                        vId.add(id);
                        vDescr.add(descr);
                    }
                        out.writeObject(vSender);
                        out.writeObject(vTitle);
                        out.writeObject(vNid);
                        out.writeObject(vId);
                        out.writeObject(vDescr);
                    
                }
                if(msg.equals("Delete"))
                {
                    String x =in.readObject().toString();
                    String query = "delete from transaction where id = "+x+" ;";
                    int n = this.setData(query);
                    query = "delete from subnotemaster where transactionid = "+x+" ;";
                    n=this.setData(query);
                }
                if(msg.equals("Download"))
                {
                    String x = in.readObject().toString();
                    String query = "select id, description from subnotemaster where nid = "+x+" ;";
                    ResultSet rs = this.getData(query);
                    String Subnote;
                    Vector vSubnote = new Vector();
                    
                    while(rs.next())
                    {
                        Subnote = rs.getString("description");
                        vSubnote.add(Subnote);
                    }
                    ObjectOutputStream out;
                    out = new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(vSubnote);
                }
            }
        }catch(Exception ex){
            System.out.println("Error on Server :" +ex);
        }
    }
}
