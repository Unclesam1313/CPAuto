public class FirewallSetup 
{
	private Process p;

	public FirewallSetup()
	{
		try
		{
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "NetSh Advfirewall set allprofiles state on");
			builder.redirectErrorStream(true);
			p = builder.start();
		}
		catch(Exception e)
		{
			System.out.println("An Error Occured:");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new FirewallSetup();
	}
}
