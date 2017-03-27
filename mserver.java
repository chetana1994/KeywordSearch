import java.security.Key;
import java.security.SecureRandom;
import java.io.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import sun.misc.*;
import java.net.*;
import java.util.*;
public class mserver extends Thread
{
   private ServerSocket serverSocket;
private static String algorithm = "AES";
final private static byte[] keyValue=new byte[]
{ 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

public static final int w=100000; 
 
public static int[] p=new int[w]; 
public static String[] t=new String[w]; 
public static String[] s=new String[w]; 
public static String[] sk=new String[w]; 
public static Key[] ki=new Key[w]; 
public static int[] pos=new int[w];
public static String[] cmp=new String[w]; 
public static String x=""; 
public static int n,l1=0,l2=0;
public static String[] sd=new String[w]; 
public static String[] seed=new String[w]; 
public static String[] c=new String[w];
public static String[] k=new String[w]; 
public static int[] f=new int[w]; 
public static String xor(String a,String b)
{

String e="";
StringBuilder sb=new StringBuilder();
for(int j=0;j<a.length();j++)
{
sb.append(a.charAt(j)^b.charAt(j));
}
e=sb.toString();
return e;
}
 public static String encrypt(String plainText,int q,Key key) throws Exception

        {
                                Cipher chiper = Cipher.getInstance(algorithm);
                chiper.init(Cipher.ENCRYPT_MODE, key);
                byte[] encVal = chiper.doFinal(plainText.getBytes());
                String encryptedValue = new BASE64Encoder().encode(encVal);
               
                String a="";
                byte[] infoBin = null;
                infoBin = encryptedValue.getBytes("UTF-8");
                for (byte b : infoBin) {
                        String s =Integer.toBinaryString(b);
                        int l=q-s.length();
                        int l2=l;
                        String p="";
                        while(l!=0)
                        {
                                p+="0";
                                l-=1;
                        }
                        if(l2!=0)
                        {
                                s=p+s;
                        }
                        a=a+s;}
                return a;
        }


   public mserver(int port) throws IOException
 
   {
      serverSocket = new ServerSocket(port);
   }
   public void run()
   {
      while(true)
      {
     
  try
         {

            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
        
  DataInputStream in =
                  new DataInputStream(server.getInputStream());

DataOutputStream out =
                new DataOutputStream(server.getOutputStream());

int l=1;
System.out.println("enter no of files");
InputStreamReader is=new InputStreamReader(System.in);
         BufferedReader ir=new BufferedReader(is);
int l1=Integer.parseInt(ir.readLine());
System.out.println("enter names of encrypted files in which u want to search");
for(int i=1;i<=l1;i++)
{
s[i]=ir.readLine();
}
/*int q=1;
File path=new File("/home/student/Desktop/ef/");
File fs[]=null;
fs=path.listFiles();
for(File df:fs)
{
if(df.isFile())
s[q]=df.getName();
System.out.println(s[q]);
q++;
}*/
x=in.readUTF();
for(int r=1;r<=l1;r++)
{
 BufferedReader br = new BufferedReader( new FileReader(new File(s[r])));
 String strLine = "";
                       int  tokenNumber = 0;
    String OPERATORS = " ";
StringTokenizer st;
                  while( (strLine = br.readLine()) != null)
                      {
     st = new StringTokenizer(strLine, OPERATORS, false);
                                while(st.hasMoreTokens())
                               {
                                        tokenNumber++;
                                        c[tokenNumber]=st.nextToken();
                                }
                        }
               n=tokenNumber;
 BufferedReader ur = new BufferedReader( new FileReader(new File("k.txt")));
  strLine = "";
    tokenNumber = 0;
                        while( (strLine = ur.readLine()) != null)
                      {
         st = new StringTokenizer(strLine, OPERATORS, false);
                                while(st.hasMoreTokens())
                               {
                                        tokenNumber++;
                                        sk[tokenNumber]=st.nextToken();
BASE64Decoder decoder=new BASE64Decoder();
byte[] dk=decoder.decodeBuffer(sk[tokenNumber]);
SecretKey p=new SecretKeySpec(dk,0,dk.length,"AES");
ki[tokenNumber]=p;
}
}

int y=1;
for(int i=1;i<=n;i++)
{
String z1=Integer.toBinaryString(y);
seed[i]=z1;
y++;
while(seed[i].length()<24)
seed[i]="0"+seed[i];

sd[i]=encrypt(z1,7,ki[i]);
cmp[i]=seed[i]+sd[i];
t[i]=xor(x,c[i]);
}

int g=0;
        for(int i=1;i<=n;i++)
        {
        for(int j=1;j<=n;j++)
       {
         if(cmp[i].equals(t[j])){
       	g=g+1;
        pos[g]=j;
 }
  }
}
f[r]=g;
p[r]=r;
System.out.println("Frequency of file "+s[r]+":"+f[r]);
}
int temp;
for(int i=1;i<=l1;i++)
{
for(int j=i+1;j<=l1;j++)
{
if(f[j]>f[i])
{
temp=f[j];
f[j]=f[i];
f[i]=temp;
temp=p[j];
p[j]=p[i];
p[i]=temp;
}
}
}
System.out.println("files retrieved :"+s[p[1]]+" and "+s[p[2]]);
out.writeUTF(s[p[1]]);

out.writeUTF(s[p[2]]);

server.close();
         }catch(Exception s)
         {
      }
}   
}
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
	 Thread t = new mserver(port);
         t.start();
      }catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}
