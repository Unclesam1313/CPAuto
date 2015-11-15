import java.io.*;
import java.util.*;

public class MComp 
{
	//REMOVE *.txt AFTER TESTING
	private final String EXTENSIONS = "*.bmp *.asf *.wma *.wmv *.wm *.asx *.wax *.wvx *.wmx *.wpl *.dvr-ms *.wmd *.avi *.mpg *.mpeg *.m1v *.mp2 *.mp3 "
			+ "*.mpa *.mpe *.m3u *.mid *.midi *.rmi *.aif *.aifc *.aif *.au *.snd *.wav *.cda *.ivf *.wmz *.wms *.mov *.m4a *.mp4 *.m4v *.mp4v *.3g2 "
			+ "*.3gp2 *.3gp *.3gpp *.aac *.adt *.adts *.m2ts *.tif *.tiff *.gif *.jpeg *.jpg *.jif *.jfif *.jp2 *.jpx *.j2k *.j2c *.fpx *.pcd *.png *.pdf"
			+ "* .txt";
	
	public MComp() throws IOException
	{

		ProcessBuilder b = new ProcessBuilder("cmd.exe", "/c","cd C:\\test && dir /s /b " + EXTENSIONS);
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
		for(String l: lines)
		{
			System.out.println(l);
		}
		
		process(lines);
	}
	
	private void process(List<String> lines)
	{
		for(int i = lines.size() - 1; i >= 0 ; i++)
		{
			String[] path = lines.get(i).split("[\\\\]");
			if(!search(MBuild.genList(), path, 0))
				lines.remove(i);
		}
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
				boolean flag = false;
				for(MItem item: ((MFolder)dir).contents())
				{
					if(search(item, path, i+1))
						flag = true;
				}
				return flag;
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
