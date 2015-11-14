public interface MItem 
{
	public boolean isFile();
	public boolean isFolder();
	public void delete();
	public String path();
}
