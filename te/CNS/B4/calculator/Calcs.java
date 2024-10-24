// Title:-Write a program using TCP socket for wired network for following
//         a.Say Hello to Each other ( For all students)
//         b.File transfer ( For all students)
//         c.Calculator (Arithmetic) (50% students)
//         d.Calculator (Trigonometry) (50% students)
//         Demonstrate  the  packets  captured  traces  using  Wireshark  Packet  Analyzer  Tool  for  peer  to 
//          peer mode.
// ---------------------------------------------------------------------------------------------------------------- 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Calcs
{
  public static void main(String args[])throws IOException
  {
   ServerSocket ss = new ServerSocket(8000);
   Socket s=ss.accept();

  DataInputStream dis=new DataInputStream(s.getInputStream());
  DataOutputStream dos=new DataOutputStream(s.getOutputStream());

 while(true)
{
  String input=dis.readUTF();
  if(input.equals("bye"))
  break;

  System.out.println("Equation received:-" + input);
  int result;
  StringTokenizer st=new StringTokenizer(input);

  int oprnd1=Integer.parseInt(st.nextToken());
  String operation=st.nextToken();
   int oprnd2=Integer.parseInt(st.nextToken());

  if(operation.equals("+"))
  {
   result=oprnd1 + oprnd2;
  }

   else if(operation.equals("-"))
 {

    result=oprnd1 - oprnd2;
} 

  else if(operation.equals("*"))
  {
   result=oprnd1 * oprnd2;
  }

   else
{
 result=oprnd1 / oprnd2;
}

  System.out.println("Sending the result...");
 
  dos.writeUTF(Integer.toString(result));
 }
}
}
// ---------------------------------output----------------------------
// student@student-Lenovo-M5800:~$ sudo su
// [sudo] password for student: 
 
// root@student-Lenovo-M5800:/home/student# cd Desktop
 
// root@student-Lenovo-M5800:/home/student/Desktop# javac Calcs.java
// root@student-Lenovo-M5800:/home/student/Desktop# java Calcs
// Equation received:-1 + 1
// Sending the result...
// Equation received:-2 - 2
// Sending the result...
// Equation received:-4 * 4
// Sending the result...
// Equation received:-4 / 2
// Sending the result...




