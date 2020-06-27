package arrow1.file;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList ;

import arrow1.Domain.User;
public class FileOperate{


public ArrayList< User> readFile(){
ArrayList< User> users = new ArrayList< User> ();
FileInputStream fis = null;
ObjectInputStream ois=null;
try {
File file = new File ("users.txt");
if(! file.exists()) {
return null;
}
fis = new FileInputStream (file);
ois = new ObjectInputStream(fis);
byte[] bytes=new byte[4];

while(true)
{
User u =(User)ois.readObject();
System.out.println(u);
users.add(u) ;
}
}
catch(EOFException eofe) {
return users;
}
catch(FileNotFoundException fnfe)
{
	fnfe.printStackTrace();
}
catch(ClassNotFoundException cnfe)
{
	cnfe.printStackTrace();
	}
catch(IOException ioe)
{
	ioe.printStackTrace();
}
finally {
try {
if(ois!=null) 
{
ois.close();
}
if(fis!=null) 
{
fis.close();
}
}
catch(Exception e) 
{
e.printStackTrace();
}
}
return users;
}

public void writeFile(ArrayList<User> users) {
	FileOutputStream fos=null;
	ObjectOutputStream oos=null;
	try 
	{
	File file = new File ("users.txt");
	fos = new FileOutputStream (file);
	oos = new ObjectOutputStream (fos) ;
	for( int i = 0 ; i < users.size();i ++)
	{
		oos.writeObject(users.get (i)) ;
	}
		oos . flush () ;
		}
	catch (FileNotFoundException fnfe) 
	{
	fnfe.printStackTrace();
	}
	catch(IOException ioe) 
	{
	ioe.printStackTrace();
	}
	finally {
	try {
	if(fos!=null) 
	{
	fos.close();
	}
	if(oos!=null)
	{
	oos.close();
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
}
}
}
}
