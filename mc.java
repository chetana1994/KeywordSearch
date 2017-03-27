import java.net.*;
import java.io.*;
import java.util.*;
import java.security.Key;
import java.security.SecureRandom;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import javax.crypto.Cipher;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
public class mc
{
public static final int w=100000;
public static String[] by=new String[w];
public static String[] sk=new String[w];
public static String[] pos=new String[w];
public static String[] sf=new String[w];
public static int[] f=new int[w];
public static String[] seed=new String[w];
public static String[] c=new String[w];
public static String[] sd=new String[w];
public static String[] t=new String[w];
public static Key[] ki=new Key[w];
public static String[] x=new String[w];
public static String[] cmp=new String[w];
public static String[] z=new String[w];
public static String[] s=new String[w];
public static int n,l,i1=0;
public static String[] to=new String[w];
public static String search=" ";
private static String algorithm = "AES";
final private static byte[] keyValue=new byte[]
{ 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
  public static String encrypt(String plainText,int q) throws Exception

        {
                Key key = generateKey();
                Cipher chiper = Cipher.getInstance(algorithm);
                chiper.init(Cipher.ENCRYPT_MODE, key);
                byte[] encVal = chiper.doFinal(plainText.getBytes());
                String encryptedValue = new BASE64Encoder().encode(encVal);
                z[i1]=encryptedValue;
//System.out.println(z[i1]);i1++;
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
private static Key generateKey() throws Exception
        {

                Key key = new SecretKeySpec(keyValue, algorithm);
                return key;
        }
public static String encrypt(String plainText,int q,Key key) throws Exception

        {
                //Key key = generateKey();
                Cipher chiper = Cipher.getInstance(algorithm);
                chiper.init(Cipher.ENCRYPT_MODE, key);
                byte[] encVal = chiper.doFinal(plainText.getBytes());
                String encryptedValue = new BASE64Encoder().encode(encVal);
                z[i1]=encryptedValue;i1++;
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
/*public static Key[] a1() throws Exception
{
for(int i=0;i<=10000;i++)
{
KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
SecureRandom random = new SecureRandom(); // cryptograph. secure random 
keyGen.init(random);
SecretKey secretKey= keyGen.generateKey();
ki[i]=secretKey;
//BASE64Encoder encoder=new BASE64Encoder();
//sk[i]=encoder.encode(ki[i].getEncoded());
}
return ki;
}*/
public static void main(String[] args)
{

try
{
System.out.println("enter no of files");
InputStreamReader is=new InputStreamReader(System.in);
         BufferedReader ir=new BufferedReader(is);
l=Integer.parseInt(ir.readLine());
for(int i=1;i<=l;i++)
{
s[i]=ir.readLine();
}
/*int q=1;
File path=new File("/home/student/Desktop/tf/");
File f[]=null;
f=path.listFiles();
for(File df:f)
{
if(df.isFile())
s[q]=df.getName();
System.out.println(s[q]);
q++;
}*/

for(int h=1;h<=l;h++)
{
 BufferedReader br = new BufferedReader(new FileReader(new File(s[h])));
 String strLine = "";
                       int  tokenNumber = 0;
                        while( (strLine = br.readLine()) != null)
                      {
    String OPERATORS = "; ,.?*";
    StringTokenizer st = new StringTokenizer(strLine, OPERATORS, true);
                                while(st.hasMoreTokens())
                               {
                                        tokenNumber++;
                                        to[tokenNumber]=st.nextToken();
                                }
                        }
               n=tokenNumber;
BufferedReader ur = new BufferedReader( new FileReader(new File("k.txt")));
  strLine = "";
    tokenNumber = 0;
                        while( (strLine = ur.readLine()) != null)
                      {
String op=" ";
   StringTokenizer  st1 = new StringTokenizer(strLine, op, false);
                                while(st1.hasMoreTokens())
                               {
                                        tokenNumber++;
                                        sk[tokenNumber]=st1.nextToken();
BASE64Decoder decoder=new BASE64Decoder();
byte[] dk=decoder.decodeBuffer(sk[tokenNumber]);
SecretKey p=new SecretKeySpec(dk,0,dk.length,"AES");
ki[tokenNumber]=p;
}
}

 for(int i=1;i<=n;i++)
             {
             x[i]   = encrypt(to[i],8);
             }
/*for(int i=0;i<=n;i++)
{
KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
SecureRandom random = new SecureRandom(); // cryptograph. secure random 
keyGen.init(random);
SecretKey secretKey= keyGen.generateKey();
ki[i]=secretKey;
BASE64Encoder encoder=new BASE64Encoder();
sk[i]=encoder.encode(ki[i].getEncoded());
}*/
//long o=Math.round(Math.log((double)n)/Math.log(2.0));
/*for(int i=1;i<=n;i++)
{
BASE64Decoder decoder=new BASE64Decoder();
byte[] dk=decoder.decodeBuffer(sk[i]);
SecretKey p=new SecretKeySpec(dk,0,dk.length,"AES");
ki[i]=p;
}*/
//ki=a1();
int y=1;
for(int i=1;i<=n;i++)
{
//System.out.println(ki[i]);
String z1=Integer.toBinaryString(y);
seed[i]=z1;
while(seed[i].length()<24)
seed[i]="0"+seed[i];
y++;
sd[i]=encrypt(z1,7,ki[i]);
t[i]=seed[i]+sd[i];
c[i]=xor(x[i],t[i]);
}
String e="/home/student/Desktop/ef/"+s[h].substring(0,(s[h].length()-4))+".dat";

PrintWriter ot=new PrintWriter(e);
//PrintWriter otp=new PrintWriter("k"+h+".txt");
for(int i=1;i<=n;i++)
{
 ot.print(c[i]);
ot.print(" ");
//otp.print(sk[i]);
//otp.print(" ");
 }
ot.close();
}

/*PrintWriter otp=new PrintWriter("k.txt");
for(int i=1;i<=10000;i++)
{
// ot.print(c[i]);
//ot.print(" ");
otp.print(ki[i]);
otp.print(" ");
 }
//ot.close();
otp.close();

//otp.close();
*/}catch(Exception e)
{}
}
}

