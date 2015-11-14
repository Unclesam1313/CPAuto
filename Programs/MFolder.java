import java.util.*;

public class MFolder implements MItem 
{
	private String path;
	private List<MItem> contents;
	
	public MFolder(String p)
	{
		path = p;
		contents = new ArrayList<MItem>();
	}
	
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
	
	public void addFolder(String p)
	{
		contents.add(new MFolder(path));
	}
	
	public void addFile(String p)
	{
		contents.add(new MFile(path));
	}
}
