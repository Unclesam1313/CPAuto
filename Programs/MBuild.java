import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class MBuild {

	public MBuild()
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
		ArrayList<String> items = populate(s);
		System.out.print(items);
		
	}
	
	public static void main(String[] args) 
	{
	}
	
	public ArrayList<String> populate(Scanner s)
	{
		ArrayList<String> a = new ArrayList<String>();
		while(s.hasNextLine())
		{
			a.add(s.nextLine());
		}
		return a;
	}

}
