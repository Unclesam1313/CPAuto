public class UACSetup 
{
	private Process p;
	
	public UACSetup()
	{
		try
		{
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "C:\\Windows\\System32\\cmd.exe /k %windir%\\System32\\reg.exe ADD  HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System /v  EnableLUA /t REG_DWORD /d 1 /f");
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
		new UACSetup();
	}
}
