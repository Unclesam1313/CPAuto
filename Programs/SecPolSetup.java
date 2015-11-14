public class SecPolSetup 
{
	private Process p;
	
	public SecPolSetup()
	{	
		try
		{
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("cmd.exe", "/c", "Secedit /configure /db secedit.sdb /cfg C:\\CPAuto\\StandPol.inf /overwrite /quiet");
		builder.redirectErrorStream(true);
		p = builder.start();
		System.out.println("HereASD");
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
