import java.io.*;

import javax.swing.JFileChooser;

public class Runner {
	public static void main(String[] args) throws IOException
	{
		if(!adminTest())
		{
			System.out.println("Please Run as an Admin!");
			System.exit(0);
		}
		final JFileChooser fc = new JFileChooser();
		fc.showDialog(null, "Use File");
		new UserSetup(fc.getSelectedFile());
		new FirewallSetup();
		new SecPolSetup();
		new UACSetup();
		//new UserSetup(null);
	}
	
	public static boolean adminTest()  
	{
	    File dir = new File("C:/Program Files");
		
	    if (dir.canWrite() == false)
	        return false;

	    if (!dir.isDirectory())
	        return false;

	    File fileTest = null;
	    try 
	    {
	        fileTest = File.createTempFile("writeableArea", ".dll", dir);
	    } 
	    catch (IOException e) 
	    {
	        return false;
	    } 
	    finally 
	    {
	        if (fileTest != null)
	            fileTest.delete();
	    }
	    return true;
	}
}
