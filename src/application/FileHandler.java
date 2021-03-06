package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.User;

/**
 * 
 * @author Alvin Chau and Andy Phan 
 *
 */
public class FileHandler {

	  public static List<User> fileofUsers = new ArrayList<User>();
		
		
		
	  private static final String path = "./Userlist.bin"; //stores the bytes for the list of songs

	    public static void ReadFile()
	    {
	        try {
	            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(path));
	            fileofUsers = (List<User>)objInputStream.readObject();
	            objInputStream.close();
	        }catch(Exception e){

	        }
	    }

	    public static void WriteFile()
	    {
	        try {
	            ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream(path));
	            objOutputStream.writeObject(fileofUsers);
	            objOutputStream.close();
	        }catch(Exception e){

	        }

	    }
	    /**
	     * 
	     * @return
	     * @throws ClassNotFoundException
	     */
	    public static List<User> retrieveUsers() throws ClassNotFoundException
	    {
	    	List<User> listU = null;
	    	try
	    	{
	    		 ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
	    		listU=(List<User>)in.readObject();
	    	}catch(IOException ex )
	    	{
	    		System.out.printf("Problem occurred during deserializing");
	    	}
	    	return listU;
	    }
	
}
