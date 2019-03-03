package team.nlp.MailSystem.butevent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.mail.*;

import team.nlp.MailSystem.function.BasicInformation;
import team.nlp.MailSystem.function.checkMailaccount;
import team.nlp.MailSystem.ui.MainPane;
/***
 * 
 * @author jun
 * ��¼��ť�¼�
 */
public class loginbutEvent implements ActionListener{
	private JTextField mailName=null;
	private JPasswordField passWord=null;
	private JFrame frame=null;
	private boolean flag=false;
	public loginbutEvent(JTextField mailfield,JPasswordField passwordfield,JFrame jframe)
	{
		mailName=mailfield;
		passWord=passwordfield;
		frame=jframe;
	}
	public void actionPerformed(ActionEvent e)
	{
		String nameStr=mailName.getText();
		String pwStr=String.valueOf(passWord.getPassword());
		if(new checkMailaccount(nameStr).result())
		{
			JOptionPane.showMessageDialog(frame, "�����˺Ÿ�ʽ�Ƿ�");
		}
		else if(pwStr==" ")
			JOptionPane.showMessageDialog(frame, "����Ϊ��!!");
		else
		{
			try
			{
				BasicInformation account=new BasicInformation();
				account.setter(nameStr,pwStr);//���û�����Ϣ
				
				new MainPane(account);
				frame.dispose();
			}catch(Exception e2)
			{
				JOptionPane.showMessageDialog(frame, e2.getMessage());
			}
		}
	}
}
