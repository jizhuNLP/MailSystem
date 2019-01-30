package team.nlp.MailSystem.function;
import java.security.Security;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
/***
 * 
 * @author jun
 * ����smtp������
 */
public class LinkHost {
	public Transport SMTPlink(Session sendSession,String name,String aucode) throws Exception
	{
		try {
			Transport transport=sendSession.getTransport("smtp");
			try {
					transport.connect(name,aucode);
				}catch(Exception e){
					if(e.getMessage().contains("Unknown SMTP host"))
						throw new Exception("����ʧ��");
					else if(e.getMessage().contains("535"))
						throw new Exception("�˺Ż��������");
					else
						throw new Exception("δ֪����");
				}
			return transport;
		} catch (NoSuchProviderException e) {
			throw new Exception("δ֪����");
		}
	}
}
