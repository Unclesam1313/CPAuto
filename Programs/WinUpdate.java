public class WinUpdate
{
    public WinUpdate()
    {
        Process b;
        ProcessBuilder pb;
        try
        {
            pb = new ProcessBuilder("cmd.exe", "/c","reg add \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\WindowsUpdate" +
                    "\\Auto Update\" /v AUOptions /t REG_DWORD /d 0 /f");
            b = pb.start();
            pb.command("cmd.exe", "/c", "sc config wuauserv start= auto");
            b = pb.start();
            pb.command("cmd.exe", "/c", "net start wuauserv");
            b = pb.start();
        }
        catch(Exception  e)
        {
            e.printStackTrace();
        }
    }
}
