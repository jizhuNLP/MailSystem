package team.nlp.MailSystem.function;
import javax.mail.*;
import java.util.Date;
import java.util.Properties;
/**
* @author Cianc
* @version ����ʱ�䣺2019��3��3�� ����10:45:05
* @ClassName obtainmail	
* @Description ���ڻ�ȡ�ʼ�
*/
public class obtainmail {
	private int mailSize;
	private Folder folder = null;
	private Store store = null;
	Message message = null;
	public obtainmail(String server, Session obtainSession, String accont, String password) {
		try {
			store = obtainSession.getStore("pop3");
			store.connect(server, accont, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			mailSize = folder.getMessageCount();
			message = folder.getMessage(mailSize);
//			//�����ʼ�����
//	        String from = message.getFrom()[0].toString();
//	        String subject = message.getSubject();
//	        Date date = message.getSentDate();
//	        System.out.println("From: " + from);
//	        System.out.println("Subject: " + subject);
//	        System.out.println("Date: " + date);  
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
