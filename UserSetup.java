package main;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

public class UserSetup 
{
	//formatted as 'username_admin?(y/n)_desiredpassword'
	private String[] usernames;
	private boolean[] adminPerms;
	private String[] pwds;
	
	public UserSetup(File input) throws IOException
	{
		Scanner s = new Scanner(input);
		List<String> lines = new ArrayList<String>();
		while(s.hasNext())
			lines.add(s.next());
		usernames = new String[lines.size()];
		adminPerms = new boolean[lines.size()];
		pwds = new String[lines.size()];
		String[] line;
		int i = 0;
		for(String str: lines)
		{
			line = str.split("_");
			usernames[i] = line[0];
			adminPerms[i] = line[1].equals("y") ? true : false;
			pwds[i] = line[2];
			i++;
		}
		s.close();
		try
		{
			this.process();
		}
		catch(Exception e)
		{
			System.out.println("An Error Occured:\n");
			e.printStackTrace();
		}
	}
	
	private void process() throws Exception
	{
		ProcessBuilder builder = new ProcessBuilder();
		Process p;
		
		//disable guest account
		/*builder = new ProcessBuilder("cmd.exe", "/c", "net user Guest /active:no");
		p = builder.start();*/
		
		//generate list of documented users
		builder.command("cmd.exe", "/c", "net user");
		builder.redirectErrorStream(true);
		p = builder.start();
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
		String[] sysUsers = genUserArray(lines);
		ArrayList<String[]> comp1 = compArrays(usernames, sysUsers);
		System.out.println(Arrays.toString(comp1.get(0)));
		System.out.println(Arrays.toString(comp1.get(1)));
		
		String[] add = comp1.get(0);
		String[] remove = comp1.get(1);
		
		for(int i = 0; i < remove.length; i++)
		{
			builder.command("cmd.exe", "/c", "net user \"" + remove[i] + " /delete");
			p = builder.start();
		}
		
		builder.command("cmd.exe", "/c", "net localgroup Administrators");
		p = builder.start();
		r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		List<String> adminLines = new ArrayList<String>();
		while(true)
		{
			line = r.readLine();
			if(line == null) break;
			adminLines.add(line);
		}
		r.close();
		String[] sysAdmins = genUserArray(adminLines);
		ArrayList<String[]> comp2 = compArrays(usernames, sysUsers);
		System.out.println(Arrays.toString(sysAdmins));
	}
	
	private String[] genUserArray(List<String> lines)
	{
		//trim header of output
		while(true)
		{
			if(lines.get(0).compareTo("") != 0)
			{
				if(lines.get(0).charAt(1) != '-')
					lines.remove(0);	
				else
					break;	
			}
			else
				lines.remove(0);
		}
		lines.remove(0);
		
		//trim footer of output
		while(true)
		{
			if(lines.get(lines.size()-1).contains("command"))
			{
				lines.remove(lines.size()-1);
				break;	
			}
			else
				lines.remove(lines.size()-1);
		}
		
		//convert list of lines of list of usernames
		List<String> names = new ArrayList<String>();
		Scanner s;
		for(String line: lines)
		{
			s = new Scanner(line);
			while(s.hasNext())
				names.add(s.next());
			s.close();
		}
		String[] result = new String[names.size()];
		
		//return names as an array
		for(int i = 0; i < names.size(); i++)
			result[i] = names.get(i);
		return result;
	}
	
	private ArrayList<String[]> compArrays(String[] arr1, String[] arr2)
	{
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		//find indexes of elements in arr1 that are not also in arr2
		ArrayList<Integer> index1 = new ArrayList<Integer>();
		boolean flag;
		for(int i = 0; i < arr2.length; i++)
		{
			flag = false;
			for(String s: arr1)
			{	
				if(arr2[i].equals(s))
				{
					
					flag = true;
				}
			}
			if(!flag)
			{
				index1.add(i);
			}
		}

		//find indexes of elements in arr2 that are not also in arr1
		List<Integer> index2 = new ArrayList<Integer>();
		for(int i = 0; i < arr1.length; i++)
		{
			flag = false;
			for(int j = 0; j < arr2.length; j++)
			{
				if(arr1[i].equals(arr2[j]))
				{
					flag = true;
				}
			}
			if(!flag)
			{
				index2.add(i);
			}
		}
		
		//return list of two arrays - forst is items in arr1 not in arr2, second is items in arr2 not in arr1
		String[] half1 = new String[index1.size()];
		for(int i = 0; i < index1.size(); i++)
		{
			half1[i] = arr1[index1.get(i)];
		}
		
		String[] half2 = new String[index2.size()];
		for(int i = 0; i < index2.size(); i++)
		{
			half2[i] = arr2[index2.get(i)];
		}
		
		result.add(half1);
		result.add(half2);
		
		return result;
	}
	
	public static void main(String[] args) throws IOException
	{
		/*if(!adminTest())
		{
			System.out.println("Please Run as an Admin");
			System.exit(0);
		}*/
		final JFileChooser fc = new JFileChooser();
		fc.showDialog(null, "Use File");
		new UserSetup(fc.getSelectedFile());
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
