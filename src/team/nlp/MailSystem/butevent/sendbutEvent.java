package team.nlp.MailSystem.butevent;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import team.nlp.MailSystem.function.BasicInformation;
import team.nlp.MailSystem.function.checkMailaccount;

/***
 * 
 * @author jun
 * 发送按钮事件
 */
public class sendbutEvent implements ActionListener{
	JFrame parentframe=null;
	JPanel functional_zone=null;
	Transport transport=null;
	JTextField subfield=null;//主题
	JTextArea contentarea=null;//内容
	JTextField revfield=null;//收件人地址
	BasicInformation basicinformation=null;
	public sendbutEvent(BasicInformation basicinformation,JPanel functionzone)
	{
		this.basicinformation=basicinformation;
		this.transport=basicinformation.getTransport();
		this.functional_zone=functionzone;
	}
	public void actionPerformed(ActionEvent e)
	{
		functional_zone.removeAll();
		revfield=new JTextField(50);
		JButton sendbut=new JButton("发送");
		JButton savebut=new JButton("保存");
		JLabel revlab=new JLabel("收信人:");
		JLabel sublab=new JLabel("主    题:");
		subfield=new JTextField(50);
		JScrollPane scrollpane=new JScrollPane();
		contentarea=new JTextArea();
		
		functional_zone.setLayout(null);
		
		revlab.setBounds(10, 10, 50, 35);
		sublab.setBounds(10, 60, 50, 35);
		revfield.setBounds(65, 10, 300, 35);
		subfield.setBounds(65, 60, 465, 35);
		sendbut.setBounds(370, 10, 78, 35);
		savebut.setBounds(450, 10, 78, 35);
		scrollpane.setBounds(10, 100, 520, 220);
		scrollpane.setViewportView(contentarea);
		
		sendbut.addActionListener(new sendbutEve());//发送按钮事件
		savebut.addActionListener(null);//保存按钮事件
		
		functional_zone.add(revlab);
		functional_zone.add(revfield);
		functional_zone.add(sublab);
		functional_zone.add(subfield);
		functional_zone.add(sendbut);
		functional_zone.add(savebut);
		functional_zone.add(scrollpane);
		functional_zone.updateUI();
	}
	class sendbutEve implements ActionListener{//发送功能的发送按钮事件
		public MimeMessage getMimeMessage(Session session)throws Exception{
			MimeMessage msg=new MimeMessage(basicinformation.getSendSession());
			msg.setFrom(new InternetAddress(basicinformation.getName()));//设置发件人地址
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(revfield.getText()));//设置收件人地址
			msg.setSubject(subfield.getText(),"UTF-8");//设置主题
			msg.setContent(contentarea.getText(),"text/html;charset=UTF-8");//设置邮件正文
			msg.setSentDate(new Date());//设置发送时间
			return msg;
		}
		public void actionPerformed(ActionEvent e)
		{
			if(new checkMailaccount(revfield.getText()).result())
				JOptionPane.showMessageDialog(functional_zone, "收信人邮箱地址非法");
			else
			{
				Session session=basicinformation.getSendSession();
				session.setDebug(true);
				try {
					Message msg=getMimeMessage(session);
					transport.sendMessage(msg, msg.getAllRecipients());
					JOptionPane.showMessageDialog(functional_zone, "发送成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(functional_zone, "发送失败");
					e1.printStackTrace();
				}
			}
		}
	}
}
