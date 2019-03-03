package team.nlp.MailSystem.function;
import javax.mail.*;
import java.util.Properties;
/***
 * 
 * @author jun
 * 基础配置
 * return: name
 * 		   autcode
 *         host
 *         sendSession
 *         recSession
 */
public class BasicInformation {
	private String name=null;
	private String autcode=null;
	private String targeSever=null;
	private Session sendSession=null;
	private Session obtainSession=null;
	private Transport transport=null;
	public void setter(String Name,String Autcode) throws Exception
	{
		name=Name;
		autcode=Autcode;
		targeSever=Name.split("@")[1];	// 126.com or qq.com .....etc..
		setSendSession();
	}
	private void setSendSession() throws Exception//smtp服务器会话
	{
		Properties props=new Properties();
		Properties pop3props=new Properties();
		// smtp信息保存
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp."+targeSever);
		props.setProperty("mail.smtp.auth", "true");
		// pop3信息保存
		pop3props.setProperty("mail.pop3.ssl.enable", "true"); // 是否使用SSL进行加密
		pop3props.setProperty("mail.pop3.host", "pop."+targeSever);
		pop3props.setProperty("mail.transport.protpcol", "pop");  // 协议保存
		sendSession=Session.getDefaultInstance(props);
		obtainSession=Session.getDefaultInstance(pop3props);
		new obtainmail("pop."+targeSever, obtainSession, name, autcode);
		try {
			transport=sendSession.getTransport("smtp");
			try {
					transport.connect(name,autcode);
				}catch(Exception e){
					if(e.getMessage().contains("SMTP host"))
						throw new Exception("联网失败");
					else if(e.getMessage().contains("535"))
						throw new Exception("账号或密码错误");
					else
						throw new Exception("未知错误");
				}
		} catch (NoSuchProviderException e) {
			throw new Exception("未知错误");
		}
	}
	public String getName() {return name;}
	public String getAutcode() {return autcode;}
	public String getSever() {return targeSever;}
	public Session getSendSession(){return sendSession;}
	public Transport getTransport() {return transport;}
}
