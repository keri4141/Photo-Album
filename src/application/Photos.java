package application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class Photos implements Serializable{

	private String caption;
	private ArrayList<String>tag;
	private boolean isStock;
	private Calendar date;
	
	public Photos(String caption,ArrayList<String> tag, Calendar date)
	{
		this.caption=caption;
		this.tag=tag; //this may cause a bug
		this.date=date;
		//may not need to edit the isStock field;
		
	}
	
	//sets fields of a new photo to be empty
	public Photos()
	{
		this.caption="";
		this.tag=new ArrayList<String>();
		this.date=null;
		
		
		
	}
	
	public void setCaption(String caption)
	{
		this.caption=caption;
	}
	
	public void setTag(String tag)
	{
		this.tag.add(tag);
		
	}
}
