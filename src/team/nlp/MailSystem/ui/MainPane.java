package team.nlp.MailSystem.ui;
import java.awt.Color;

import javax.mail.*;
import javax.swing.*;

import team.nlp.MailSystem.butevent.*;
import team.nlp.MailSystem.function.BasicInformation;

/***
 * 
 * @author jun
 * 主界面UI
 */
public class MainPane extends JFrame{
	private JPanel functional_zone=null;//功能区
	private JFrame frame=null;
	public MainPane(BasicInformation account)
	{
		this.setSize(800, 480);
		this.setLocation(600, 200);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		frame=this;
		
		JButton sendbut=new JButton("发邮件");
		JButton inbox=new JButton("收信箱");
		JButton spambut=new JButton("垃圾邮件");
		JButton draftsbut=new JButton("草稿箱");
		JButton exitbut=new JButton("退出");
		functional_zone=new JPanel();
		JLabel mes=new JLabel("功能区");
		functional_zone.setBackground(Color.WHITE);
		
		sendbut.setBounds(50, 50, 150, 50);
		inbox.setBounds(50, 120, 150, 50);
		spambut.setBounds(50, 190, 150, 50);
		draftsbut.setBounds(50, 260, 150, 50);
		exitbut.setBounds(50, 330, 150, 50);
		functional_zone.setBounds(220, 50, 540,330);
		
		//功能按钮事件添加
		sendbut.addActionListener(null);
		inbox.addActionListener(null);
		spambut.addActionListener(null);
		draftsbut.addActionListener(null);
		exitbut.addActionListener(new exitbutEvent(frame));
		
		this.add(sendbut);
		this.add(inbox);
		this.add(spambut);
		this.add(draftsbut);
		this.add(exitbut);
		this.add(functional_zone);

	}
	public static void main(String[] args)
	{
		new MainPane(null);
	}
}