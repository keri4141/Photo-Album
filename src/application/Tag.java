package application;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2718453092474771261L;
	private String tagName;
	private String tagValue;
	
	
	public Tag(String tagName, String tagValue)
	{
		this.tagName=tagName;
		this.tagValue=tagValue;
	}
	public String toString()
	{
		return this.tagName+": "+this.tagValue;
	}
	
	

}
