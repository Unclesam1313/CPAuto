
public class SecPolSetup 
{
	public SecPolSetup(Process p)
	{
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("cmd.exe", "/c", "Secedit /import /db \"C:\\%Windows%\\security\\database\\secedit.sdb\" /cfg \"C:\\CPAuto\\StandPol.inf\"");
		builder.redirectErrorStream(true);
		p = builder.start();
	}

	public static void main(String[] args) throws Exception 
	{
		new SecPolSetip(new Process());
	}

}
