package team.nlp.MailSystem.function;
import javax.mail.*;
import java.util.Properties;
/***
 * 
 * @author jun
 * »ù´¡ÅäÖÃ
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
	private Transport transport=null;
	public void setter(String Name,String Autcode) throws Exception
	{
		name=Name;
		autcode=Autcode;
		targeSever=Name.split("@")[1];
		setSendSession();
	}
	private void setSendSession() throws Exception//smtp·şÎñÆ÷»á»°
	{
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp."+targeSever);
		props.setProperty("mail.smtp.auth", "true");
		sendSession=Session.getDefaultInstance(props);
		try {
			transport=sendSession.getTransport("smtp");
			try {
					transport.connect(name,autcode);
				}catch(Exception e){
					if(e.getMessage().contains("SMTP host"))
						throw new Exception("ÁªÍøÊ§°Ü");
					else if(e.getMessage().contains("535"))
						throw new Exception("ÕËºÅ»òÃÜÂë´íÎó");
					else
						throw new Exception("Î´Öª´íÎó");
				}
		} catch (NoSuchProviderException e) {
			throw new Exception("Î´Öª´íÎó");
		}
	}
	public String getName() {return name;}
	public String getAutcode() {return autcode;}
	public String getSever() {return targeSever;}
	public Session getSendSession(){return sendSession;}
	public Transport getTransport() {return transport;}
}
