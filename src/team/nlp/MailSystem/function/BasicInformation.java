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
	private Session recSession=null;
	public void setter(String Name,String Autcode)
	{
		name=Name;
		autcode=Autcode;
		targeSever=Name.split("@")[1];
		setSendSession();
		setRecSession();
	}
	private void setSendSession()//smtp服务器会话
	{
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp."+targeSever);
		props.setProperty("mail.smtp.auth", "true");
		sendSession=Session.getDefaultInstance(props);
	}
	private void setRecSession()//pop3服务器会话
	{
		Properties props=new Properties();
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", "pop.qq.com");
		props.setProperty("mail.pop3.ssl.enable", "true");//ssl连接设置
		recSession=Session.getDefaultInstance(props);
	}
	public String getName() {return name;}
	public String getAutcode() {return autcode;}
	public String getSever() {return targeSever;}
	public Session getSendSession(){return sendSession;}
	public Session getRecSession() {return recSession;}
}
