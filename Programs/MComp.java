import java.io.*;
import java.util.*;

public class MComp
{
	private final String EXTENSIONS = "*.avi *.mpg *.vob *.mp4 *.m2ts *.mov *.3gp *.mkv *.m4a *.m4b *.mp3 *.wav *.wma *.webm *.wv *.flac";
	public List<String> lines;

	public MComp() throws IOException
	{

		ProcessBuilder b = new ProcessBuilder("cmd.exe", "/c","cd /d C:\\ && dir /s /b " + EXTENSIONS);
		b.redirectErrorStream(true);
		Process p = b.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		lines = new ArrayList<String>();
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
		MItem base = MBuild.genList();
		/*for(String path: lines)
			System.out.println(path);*/

		for(int i = lines.size() - 1; i >= 0; i--)
		{
			String[] path = lines.get(i).split("[\\\\]");
			if(path.length > 1 && (path[1].equals("Program Files") || path[1].equals("Program Files (x86)")|| path[1].equals("Program Data")))
				lines.remove(i);
			else if(search(base, path, 0))
				lines.remove(i);
		}

		/*System.out.println("\nItems to delete:\n");
		for(String path: lines)
			System.out.println(path);*/
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
