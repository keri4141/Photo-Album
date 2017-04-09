package application;

import java.io.Serializable;
import java.util.ArrayList;


public class Album implements Serializable,Comparable<Album>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072840136991012841L;
	private String albumName;
	private ArrayList<Photos> photos;
	
	
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
	
	public void setAlbumName(String name)
	{
		this.albumName=name;
		
	}
	
	public String toString()
	{
		return this.albumName;
	}
}
