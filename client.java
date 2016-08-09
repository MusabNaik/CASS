import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class DesEncrypter {
  Cipher ecipher;

  Cipher dcipher;

  DesEncrypter(SecretKey key) throws Exception {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }

  public String encrypt(String str) throws Exception {
    // Encode the string into bytes using utf-8
    byte[] utf8 = str.getBytes("UTF8");

    // Encrypt
    byte[] enc = ecipher.doFinal(utf8);

    // Encode bytes to base64 to get a string
    return new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String str) throws Exception {
    // Decode base64 to get bytes
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

    byte[] utf8 = dcipher.doFinal(dec);

    // Decode using utf-8
    return new String(utf8, "UTF8");
  }
}


public class client 
{
 public static void main(String args[])throws Exception
 {

   SecretKey key = KeyGenerator.getInstance("DES").generateKey();
   DesEncrypter encrypter = new DesEncrypter(key);
   
   try
        {
          DataInputStream d=new DataInputStream(System.in);
          Scanner sc =new Scanner(System.in);
          System.out.print("\nEnter your msg:");

           String msg = new String (sc.nextLine());
           String encrypted = encrypter.encrypt(msg);
           System.out.println("Encrypted msg :");
           //String decrypted = encrypter.decrypt(encrypted);
           
           //System.out.print("\nEnter number:");
           //int num=Integer.parseInt(d.readLine());

           Socket s=new Socket("localhost",1024);

           PrintStream ps=new PrintStream(s.getOutputStream());
           ps.println(encrypted);
           //ps.println(num+"");

          DataInputStream dis=new DataInputStream(s.getInputStream());
           String response=dis.readLine();
                 System.out.println(response);
                 
                 System.out.println("Msg from server:"+response);
                 String decrypted = encrypter.decrypt(response);
                 System.out.println("Decrypted Msg :"+decrypted);


                s.close();
        }
        catch(Exception ex)
        {

        }
 }


}