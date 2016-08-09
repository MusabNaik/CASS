import java.io.*;
import java.net.*;
public class server {

  public static void main(String args[])throws Exception
  {

try
    {

    ServerSocket ss=new ServerSocket(1024);
       System.out.print("\nWaiting for client.....");
       Socket s=ss.accept();
       System.out.println("\nConnected");

       DataInputStream d=new DataInputStream(s.getInputStream());

       String name =new String (d.readLine());
       

        PrintStream ps=new PrintStream(s.getOutputStream());
        
        ps.println( name);
        System.out.println("Msg recived from client "+name);

          ss.close();
          s.close();
    }
    
catch(Exception ex)
{


}
}}