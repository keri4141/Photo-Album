package application;

import java.util.ArrayList;


public class User {

		private String userName;
		private boolean isAdmin=false;
		private ArrayList<Album> album=new ArrayList<Album>();
		
	public User(String userName)
	{
		this.userName=userName;
		//add arraylist for album later
		
	}
	
	public User()
	{
		this.userName="";
		
	}
	
	public void setuserName(String name)
	{
		this.userName=name;
		
	}
}
