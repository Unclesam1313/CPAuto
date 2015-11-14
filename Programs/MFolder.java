package media;

import java.util.List;

public class MFolder implements MItem 
{
	private String path;
	private List<MItem> contents;
	
	public boolean isFile()
	{
		return false;
	}
	
	public boolean isFolder()
	{
		return true;
	}
	
	public void delete()
	{
		System.out.println("Oops: Tried to delete folder at: " + path);
	}
	
	public String path()
	{
		return path;
	}
	
	public List<MItem> contents()
	{
		return contents;
	}
}
