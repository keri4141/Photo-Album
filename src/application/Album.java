package application;

import java.io.Serializable;
import java.util.ArrayList;


public class Album implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072840136991012841L;
	private String albumName;
	private ArrayList<Photos> photos;
	
	
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
