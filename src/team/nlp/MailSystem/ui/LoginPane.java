package team.nlp.MailSystem.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import team.nlp.MailSystem.function.checkMailaccount;
import team.nlp.MailSystem.butevent.exitbutEvent;
import team.nlp.MailSystem.butevent.loginbutEvent;
import team.nlp.MailSystem.function.BasicInformation;
import team.nlp.MailSystem.ui.MainPane;
/***
 * 
 * @author jun
 * 功能:登录界面的UI
 */
public class LoginPane extends JFrame{
	private JFrame frame=null;
	private JTextField mailfield=null;
	private JPasswordField codefield=null;
	LoginPane()
	{
		frame=this;
		this.setSize(500, 230);
		this.setLocation(700, 400);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel maillab=new JLabel("邮    箱:");
		JLabel codelab=new JLabel("授权码:");
		mailfield=new JTextField(20);
		codefield=new JPasswordField(10);
		JButton logbut=new JButton("登录");
		JButton exitbut=new JButton("退出");
		
		maillab.setBounds(75, 60, 50, 15);
		codelab.setBounds(75, 110, 50, 15);
		mailfield.setBounds(125, 55, 280, 25);
		codefield.setBounds(125, 105, 280, 25);
		logbut.setBounds(160, 150, 80, 35);
		exitbut.setBounds(260, 150, 80, 35);
		
		logbut.addActionListener(new loginbutEvent(mailfield,codefield,frame));
		exitbut.addActionListener(new exitbutEvent(frame));
		
		this.add(maillab);
		this.add(codelab);
		this.add(mailfield);
		this.add(codefield);
		this.add(logbut);
		this.add(exitbut);
		
		this.setVisible(true);
	}
	public static void main(String[] args)
	{
		new LoginPane();
	}
}
