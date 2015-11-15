import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class MBuild {
	
	private static ArrayList<String> items;
	private static MFolder root = new MFolder("C:\\");

	public MBuild()
	{
		

		
	}
	
	public static void main(String[] args) 
	{
		new MBuild();
	}
	

	
	public static void place(String s)
	{
		String[] layers = s.split("[\\\\]");
		MFolder currentDir = root;
		for(int i = 1; i<layers.length-1; i++)
		{
			boolean match = false;
			for(MFolder f: currentDir.folders())
			{
				String path = f.path();
				int mark = path.lastIndexOf('\\');
				String folderName = path.substring(mark+1);
				if(folderName.equals(layers[i]))
				{
					match=true;
				}
			}
			if(match==false)
			{
				currentDir.addItem(new MFolder(currentDir.path()+layers[i]+"\\"));
				ArrayList<MFolder> content = currentDir.folders();
				currentDir=content.get(content.size()-1);
			}
			else
			{
				for(MFolder f: currentDir.folders())
				{
					String path = f.path();
					int mark = path.lastIndexOf('\\');
					String folderName = path.substring(mark+1);
					if(folderName.equals(layers[i]))
					{
						currentDir=f;
						break;
					}
				}
			}
			
		}
		currentDir.addFile(currentDir.path()+layers[layers.length-1]);
		
	}
	
	public static MFolder genList()
	{
		File f = new File("DefFiles.txt");
		Scanner s;
		try 
		{
			s = new Scanner(f);
		}
		catch(Exception e)
		{
			s=null;
			System.out.println("An Error Occured:");
			e.printStackTrace();
		}

		ArrayList<String> items = new ArrayList<String>();
		while(s.hasNextLine())
		{
			items.add(s.nextLine());
		}
		
		
		
		for(String path : items)
		{
			place(path);
		}
		
		
		return root;
	}

}
