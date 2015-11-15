import java.util.*;

public class MFolder implements MItem 
{
	private String path;
	private ArrayList<MItem> contents;
	
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
	
	public ArrayList<MItem> contents()
	{
		return contents;
	}
	
	public ArrayList<MFolder> folders()
	{
		ArrayList<MFolder> f = new ArrayList<MFolder>();
		for(MItem i: contents)
		{
			if(i.isFolder())
				f.add((MFolder)i);
		}
		return f;
	}
	
	public ArrayList<MFile> files()
	{
		ArrayList<MFile> f = new ArrayList<MFile>();
		for(MItem i: contents)
		{
			if(i.isFile())
				f.add((MFile)i);
		}
		
		return f;
	}
	
	public void addFolder(String p)
	{
		contents.add(new MFolder(p));
	}
	
	public void addFile(String p)
	{
		contents.add(new MFile(p));
	}
	
	public void addItem(MItem i)
	{
		contents.add(i);
	}
}
