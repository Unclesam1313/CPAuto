import java.io.*;
import java.util.*;

public class MComp 
{
	private final String EXTENSIONS = "*.bmp *.asf *.wma *.wmv *.wm *.asx *.wax *.wvx *.wmx *.wpl *.dvr-ms *.wmd *.avi *.mpg *.mpeg *.m1v *.mp2 *.mp3 "
			+ "*.mpa *.mpe *.m3u *.mid *.midi *.rmi *.aif *.aifc *.aif *.au *.snd *.wav *.cda *.ivf *.wmz *.wms *.mov *.m4a *.mp4 *.m4v *.mp4v *.3g2 "
			+ "*.3gp2 *.3gp *.3gpp *.aac *.adt *.adts *.m2ts *.tif *.tiff *.gif *.jpeg *.jpg *.jif *.jfif *.jp2 *.jpx *.j2k *.j2c *.fpx *.pcd *.png *.pdf";
	
	public MComp() throws IOException
	{

		ProcessBuilder b = new ProcessBuilder("cmd.exe", "/c","cd C:\\ && dir /s /b " + EXTENSIONS);
		b.redirectErrorStream(true);			
		Process p = b.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		List<String> lines = new ArrayList<String>();
		while(true)
		{
			line = r.readLine();
			if(line == null) break;
			lines.add(line);
		}
		r.close();
		
		process(lines);
	}
	
	private void process(List<String> lines)
	{
		for(String path: lines)
			System.out.println(path);
		for(int i = lines.size() - 1; i >= 0; i--)
		{
			String[] path = lines.get(i).split("[\\\\]");
			if(path.length > 1 && path[1].equals("Program Files") || path[1].equals("Program Files (x86)")|| path[1].equals("Program Data"))
				lines.remove(i);
		}
		for(int i = lines.size() - 1; i >= 0; i--)
		{
			String[] path = lines.get(i).split("[\\\\]");
			if(search(MBuild.genList(), path, 0))
				lines.remove(i);
		}
		System.out.println("\nItems to delete:\n");
		for(String path: lines)
			System.out.println(path);
	}
	
	private boolean search(MItem dir, String[] path, int i)
	{
		String[] dirPath = dir.path().split("[\\\\]");
		if(dirPath[i].equals(path[i]))
		{
			if(i == path.length-1)
			{
				return true;
			}
			else if (dir.isFolder())
			{
				for(MItem item: ((MFolder)dir).contents())
				{
					if(search(item, path, i+1))
						return true;
				}
				return false;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public static void main(String[] args)
	{
		try
		{
			new MComp();
		}
		catch (Exception e)
		{
			System.out.println("An error occured:\n");
			e.printStackTrace();
		}
	}
}
