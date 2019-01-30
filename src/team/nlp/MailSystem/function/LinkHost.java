package team.nlp.MailSystem.function;
import java.security.Security;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
/***
 * 
 * @author jun
 * 连接smtp服务器
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
						throw new Exception("联网失败");
					else if(e.getMessage().contains("535"))
						throw new Exception("账号或密码错误");
					else
						throw new Exception("未知错误");
				}
			return transport;
		} catch (NoSuchProviderException e) {
			throw new Exception("未知错误");
		}
	}
}
