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
 * ���Ͱ�ť�¼�
 */
public class sendbutEvent implements ActionListener{
	JFrame parentframe=null;
	JPanel functional_zone=null;
	Transport transport=null;
	JTextArea contentarea=null;//����
	JTextField revfield=null;//�ռ��˵�ַ
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
		JButton sendbut=new JButton("����");
		JButton savebut=new JButton("����");
		JLabel revlab=new JLabel("������:");
		JScrollPane scrollpane=new JScrollPane();
		contentarea=new JTextArea();
		
		functional_zone.setLayout(null);
		
		revlab.setBounds(10, 10, 50, 35);
		revfield.setBounds(65, 10, 300, 35);
		sendbut.setBounds(370, 10, 78, 35);
		savebut.setBounds(450, 10, 78, 35);
		scrollpane.setBounds(10, 60, 520, 260);
		scrollpane.setViewportView(contentarea);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		sendbut.addActionListener(new sendbutEve());//���Ͱ�ť�¼�
		savebut.addActionListener(null);//���水ť�¼�
		
		functional_zone.add(revlab);
		functional_zone.add(revfield);
		functional_zone.add(sendbut);
		functional_zone.add(savebut);
		functional_zone.add(scrollpane);
;		functional_zone.updateUI();
	}
	class sendbutEve implements ActionListener{//���͹��ܵķ��Ͱ�ť�¼�
		public MimeMessage getMimeMessage(Session session)throws Exception{
			MimeMessage msg=new MimeMessage(basicinformation.getSendSession());
			msg.setFrom(new InternetAddress(basicinformation.getName()));//���÷����˵�ַ
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(revfield.getText()));//�����ռ��˵�ַ
			msg.setSubject("","UTF-8");//��������
			msg.setContent(contentarea.getText(),"text/html;charset=UTF-8");//�����ʼ�����
			msg.setSentDate(new Date());//���÷���ʱ��
			return msg;
		}
		public void actionPerformed(ActionEvent e)
		{
			if(new checkMailaccount(revfield.getText()).result())
				JOptionPane.showMessageDialog(functional_zone, "�����������ַ�Ƿ�");
			else
			{
				Session session=basicinformation.getSendSession();
				session.setDebug(true);
				try {
					Message msg=getMimeMessage(session);
					transport.sendMessage(msg, msg.getAllRecipients());
					JOptionPane.showMessageDialog(functional_zone, "���ͳɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(functional_zone, "����ʧ��");
					e1.printStackTrace();
				}
			}
		}
	}
}
