package application;
import java.util.Calendar;


public class Photos {

	private String caption;
	private String tag;
	private boolean isStock;
	private Calendar date;
	
	public Photos(String caption,String tag, Calendar date)
	{
		this.caption=caption;
		this.tag=tag; //may have to be a hashtable/hashmap
		this.date=date;
		//may not need to edit the isStock field;
		
	}
	
	public Photos()
	{
		this.caption="";
		this.tag="";
		this.date=null;
		
		
		
	}
	
	public void setCaption(String caption)
	{
		this.caption=caption;
	}
	
	public void setTag(String tag)
	{
		this.tag=tag;
		
	}
}
