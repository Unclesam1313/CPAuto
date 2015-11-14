public class ServicesSetup {

	private Process p;

	public ServicesSetup()
	{
		try
		{
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "sc config tlntsvr start= disabled");
			builder.redirectErrorStream(true);
			p = builder.start();
			builder.command("cmd.exe", "/c", "net stop telnet");
		}
		catch(Exception e)
		{
			System.out.println("An Error Occured:");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new ServicesSetup();
	}

}
