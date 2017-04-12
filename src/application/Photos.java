package application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Photos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -501614198631312081L;
	private String caption;
	private List<Tag> tag;
	private boolean isStock;
	private String pathtoImage;
	private Calendar date;
	
	public Photos(String caption,ArrayList<Tag> tag, Calendar date)
	{
		this.caption=caption;
		this.tag=tag; //this may cause a bug
		this.date=date;
		//may not need to edit the isStock field;
		
	}
	
	public Photos(String pathtoImage)
	{
		this.pathtoImage = pathtoImage;
		this.caption="";
		this.tag=new ArrayList<Tag>();
	}
	
	//sets fields of a new photo to be empty
	public Photos()
	{
		this.caption="";
		this.tag=new ArrayList<Tag>();
		this.date=null;
		
		
		
	}
	
	public void setCaption(String caption)
	{
		this.caption=caption;
	}
	
	public String getCaption()
	{
		return this.caption;
	}
	
	public void setTag(Tag tag)
	{
		this.tag.add(tag);
		
	}
	
	public List<Tag> getTagList()
	{
		return this.tag;
	}
	
	public String toString()
	{
		return this.pathtoImage;
	}
}
