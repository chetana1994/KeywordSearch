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

public class keygen
{
public static String[] sk=new String[100000];
public static Key[] ki=new Key[100000];
private static String algorithm = "AES";
 
public static Key[] a1() throws Exception
{
for(int i=1;i<=10000;i++)
{
KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
SecureRandom random = new SecureRandom(); // cryptograph. secure random 
keyGen.init(random);
SecretKey secretKey= keyGen.generateKey();
ki[i]=secretKey;
BASE64Encoder encoder=new BASE64Encoder();
sk[i]=encoder.encode(ki[i].getEncoded());
}
 
PrintWriter ot=new PrintWriter("k.txt");
for(int i=1;i<=10000;i++)
{
 ot.print(sk[i]);
ot.print(" ");
 }
return ki;
}
public static void main(String[] args)
{
try
{
a1();
}
catch(Exception e){}
}
}

