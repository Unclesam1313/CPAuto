
public class SecPolSetup 
{
	private Process p;
	
	public SecPolSetup()
	{	
		try
		{
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("cmd.exe", "/c", "Secedit /import /db \"C:\\%Windows%\\security\\database\\secedit.sdb\" /cfg \"C:\\CPAuto\\StandPol.inf\"");
		builder.redirectErrorStream(true);
		p = builder.start();
		}
		catch(Exception e)
		{
			System.out.println("An error occured:");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception 
	{
		new SecPolSetup();
	}
}
