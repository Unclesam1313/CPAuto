
public class SecPolSetup {

	public static void main(String[] args) throws Exception {
		ProcessBuilder builder = new ProcessBuilder();
		Process p;
		builder.command("cmd.exe", "/c", "Secedit /import /db \"C:\\%Windows%\\security\\database\\secedit.sdb\" /cfg \"C:\\CPAuto\\StandPol.inf\"");
		builder.redirectErrorStream(true);
		p = builder.start();

		
	}

}
