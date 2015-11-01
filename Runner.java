package main;

import java.io.*;

import javax.swing.JFileChooser;

public class Runnner {
	public static void main(String[] args) throws IOException
	{
		if(!UserSetup.adminTest())
		{
			System.out.println("Please Run as an Admin!");
			System.exit(0);
		}
		final JFileChooser fc = new JFileChooser();
		fc.showDialog(null, "Use File");
		new UserSetup(fc.getSelectedFile());
		//new UserSetup(null);
	}

}
