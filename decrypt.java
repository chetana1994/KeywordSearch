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
public class decrypt 
{
public static final int w=100000;
public static String[] by=new String[w];
public static String[] sk=new String[w];
public static String[] pos=new String[w];
public static String[] sf=new String[w];
public static int[] f=new int[w];
public static Key[] ki=new Key[w];
public static String[] seed=new String[w];
public static String[] c=new String[w];
public static String[] sd=new String[w];
public static String[] t=new String[w];
public static String[] x=new String[w];
public static String[] cmp=new String[w];
public static String[] z=new String[w];
public static int n=0,q,i1=0;
public static String[] to=new String[w];
public static String search=" ";
private static String algorithm = "AES";
final private static byte[] keyValue=new byte[]
{ 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

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


 public static String decrypt(String encryptedText) throws Exception
        {
                int i=0;
                int bin;
                int l=encryptedText.length();
                String s="";
                String str="";
                while(i!=l)
                {
                        s=encryptedText.substring(i,i+8);
                        i+=8;
                        bin=Integer.parseInt(s,2);
                    str+=(char)bin;
                }
                Key key = generateKey();


                Cipher chiper = Cipher.getInstance(algorithm);

                chiper.init(Cipher.DECRYPT_MODE, key);

                byte[] decordedValue = new BASE64Decoder().decodeBuffer(str);

                byte[] decValue = chiper.doFinal(decordedValue);

                String decryptedValue = new String(decValue);
                return decryptedValue;

        }
private static Key generateKey() throws Exception
        {

                Key key = new SecretKeySpec(keyValue, algorithm);
                return key;
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

public static void bl(String s,int h)
{
try
{
 BufferedReader br = new BufferedReader( new FileReader(new File(s)));
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
while(seed[i].length()<24)
seed[i]="0"+seed[i];
y++;
sd[i]=encrypt(z1,7,ki[i]);
t[i]=seed[i]+sd[i];
x[i]=xor(c[i],t[i]);
}
 for(int i=1;i<=n;i++)
           {
         to[i]   = decrypt(x[i]);
       }
PrintWriter ot=new PrintWriter("pf"+h+".txt");
for(int i=1;i<=n;i++)
{
 ot.print(to[i]);
}
ot.close();
}catch(Exception e)
{}
}
}

