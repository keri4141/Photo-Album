package application;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable{

		private String userName;
		private boolean isAdmin=false;
		private ArrayList<Album> album; //maybe LIST will be better
		
	public User(String userName)
	{
		this.userName=userName;
		this.album=new ArrayList<Album>();
		//add arraylist for album later
		
	}
	
	//set the users name
	public void setuserName(String name)
	{
		this.userName=name;
		
	}
	
	//adds the album to the users list of albums
	public void setAlbum(Album album)
	{
		
		this.album.add(album);
	}
	
	public String getuserName()
	{
		return this.userName;
	}
	
	public String toString()
    {
        return this.userName;

    }
	
	
}
