public class MFile implements MItem
{
	private String path;
	
	public MFile(String p)
	{
		path = p;
	}
	
	public boolean isFile()
	{
		return true;
	}
	
	public boolean isFolder()
	{
		return false;
	}
	
	public void delete() 
	{
		try
		{
			ProcessBuilder b = new ProcessBuilder("cmd.exe", "/c", "del " + path);
			Process p = b.start();
			p.destroy();
		}
		catch(Exception e)
		{
			System.out.println("An Error Occured!:");
			e.printStackTrace();
		}
	}
	
	public String path()
	{
		return path;
	}
	
	public String toString()
	{
		return path;
	}
}
