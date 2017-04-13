package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alvin Chau and Andy Phan 
 *
 */

public class Album implements Serializable,Comparable<Album>{

	private static final long serialVersionUID = -3072840136991012841L;
	private String albumName;
	private List<Photos> photos;
	
	/**
	 * 
	 * @param a
	 * @return int to differentiate albums
	 */
	public int compareTo(Album a)
	{
		return this.albumName.compareTo(a.albumName);
	}
	
	public Album(String name)
	{
		this.albumName=name;
		this.photos=new ArrayList<Photos>();
		
	}
	
	public Album()
	{
		this.albumName="";
	
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setAlbumName(String name)
	{
		this.albumName=name;
		
	}
	/**
	 * 
	 * @param p
	 */
	public void setPhoto(Photos p)
	{
		this.photos.add(p);
	}
	/**
	 * @return string of album
	 */
	public String toString()
	{
		return this.albumName;
	}
	
	public List<Photos> getPhotoList()
	{
		return this.photos;
	}
	
	
	
}
