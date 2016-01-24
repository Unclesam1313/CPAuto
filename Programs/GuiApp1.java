import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GuiApp1
{

    public static void main(String[] args)//temp
    {
        new GuiApp1();
    }

    public GuiApp1()
    {
        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("CPAuto");
        guiFrame.setSize(800,550);
        guiFrame.setLocationRelativeTo(null);


        final JPanel buttonsPanel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(boxLayout);

        JLabel buttonsLabel = new JLabel ("Tasks:");
        JCheckBox secPol = new JCheckBox("Import Local Security Policy");
        secPol.setMnemonic(KeyEvent.VK_C);
        secPol.setSelected(true);

        JCheckBox users = new JCheckBox("Edit Users");
        users.setMnemonic(KeyEvent.VK_C);
        users.setSelected(true);

        JCheckBox uac = new JCheckBox("Open UAC Prompt");
        uac.setMnemonic(KeyEvent.VK_C);
        uac.setSelected(true);

        JCheckBox firewall = new JCheckBox("Turn on Firewall");
        firewall.setMnemonic(KeyEvent.VK_C);
        firewall.setSelected(true);

        JCheckBox mediaFiles = new JCheckBox("Search for Media Files");
        mediaFiles.setMnemonic(KeyEvent.VK_C);
        mediaFiles.setSelected(true);

        JCheckBox services = new JCheckBox("Disable Services"); //Add checkable list
        services.setMnemonic(KeyEvent.VK_C);
        services.setSelected(true);

        buttonsPanel.add(buttonsLabel);
        buttonsPanel.add(secPol);
        buttonsPanel.add(users);
        buttonsPanel.add(uac);
        buttonsPanel.add(firewall);
        buttonsPanel.add(mediaFiles);
        buttonsPanel.add(services);



        final JPanel textPanel = new JPanel();
        JTextArea textArea = new JTextArea("");
        textArea.setFont(new Font("Apple Casual", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(775, 300));
        areaScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Plain Text"),BorderFactory.createEmptyBorder(5,5,5,5)),areaScrollPane.getBorder()));
        textPanel.add(areaScrollPane);


        JButton startButton = new JButton("Start");
        startButton.addActionListener(e1 ->
        {
                if(!adminTest())
                {
                    textArea.append("ERROR: Please Run as an Admin!\n");
                }
                else
                {


                    if (firewall.isSelected())
                    {
                        try
                        {
                            textArea.append("Attempting Firewall Setup...\n");
                            int[] x = new int[1];
                            int y = x[1];
                            new FirewallSetup();
                            textArea.append("Firewall Setup: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                    if (secPol.isSelected())
                    {
                        try
                        {
                            textArea.append("Attempting SecPol Import...\n");
                            new SecPolSetup();
                            textArea.append("SecPol Import: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                    if (users.isSelected())
                    {
                        try
                        {
                            final JFileChooser fc = new JFileChooser();
                            fc.showDialog(null, "Use File");
                            textArea.append(fc.getSelectedFile().getName() + " has been chosen.\n");
                            textArea.append("Attempting User Setup...\n");
                            new UserSetup(fc.getSelectedFile());
                            textArea.append("User Setup: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                    if (uac.isSelected())
                    {
                        try
                        {
                            textArea.append("Attempting to open UAC Prompt...\n");
                            new UACSetup();
                            textArea.append("UAC Prompt: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                    if (services.isSelected())
                    {
                        try
                        {
                            textArea.append("Attempting Services Setup...\n");
                            new ServicesSetup();
                            textArea.append("Services Setup: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                    if (mediaFiles.isSelected())
                    {
                        try
                        {
                            textArea.append("Attempting Media File Search...\n");
                            new MComp();
                            textArea.append("Media Files: COMPLETE\n\n");
                        }
                        catch(Exception e)
                        {
                            for(StackTraceElement s: e.getStackTrace())
                                textArea.append(s.toString()+"\n");
                            textArea.append("\n");
                        }
                    }
                }
        });


        guiFrame.add(buttonsPanel, BorderLayout.NORTH);
        guiFrame.add(textPanel, BorderLayout.WEST);  //make sure the JFrame is visible
        guiFrame.add(startButton,BorderLayout.SOUTH);
        guiFrame.setVisible(true);

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
