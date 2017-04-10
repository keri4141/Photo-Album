package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Album implements Serializable,Comparable<Album>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072840136991012841L;
	private String albumName;
	private List<Photos> photos;
	
	
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
	
	public void setPhoto(Photos p)
	{
		this.photos.add(p);
	}
	
	public String toString()
	{
		return this.albumName;
	}
	
	public List<Photos> getPhotoList()
	{
		return this.photos;
	}
	
	
	
}
