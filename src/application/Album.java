package application;

import java.util.ArrayList;


public class Album {

	private String albumName;
	private ArrayList<Photos> photos;
	
	
	public Album(String name,Photos photos)
	{
		this.albumName=name;
		//make an array list later
		
	}
	
	public Album()
	{
		this.albumName="";
		this.photos=new ArrayList<Photos>();
	}
	
	public void setAlbumName(String name)
	{
		this.albumName=name;
		
	}
}
