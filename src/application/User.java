package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alvin Chau and Andy Phan 
 *
 */

public class User implements Serializable{

	private static final long serialVersionUID = -661828491489966978L;

		private String userName;
	
		private List<Album> album; //maybe LIST will be better
	
	/**
	 * 
	 * @param userName
	 */
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
	
	/**
	 * adds the album to the users list of albums
	 * @param album
	 */
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
	/**
	 * 
	 * @return album
	 */
	public List<Album> getAlbumList()
	{
		return this.album;
	}
	
	
}
