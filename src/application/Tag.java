package application;

import java.io.Serializable;

/**
 * 
 * @author Alvin Chau and Andy Phan 
 *
 */
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 2718453092474771261L;
	private String tagName;
	private String tagValue;
	
	/**
	 * 
	 * @param tagName
	 * @param tagValue
	 */
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
