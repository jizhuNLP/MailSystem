package team.nlp.MailSystem.function;
/***
 * 
 * @author jun
 * »ù´¡ÅäÖÃ(ÓÊÏä+ÊÚÈ¨Âë)
 */
public class BasicInformation {
	private String name=null;
	private String autcode=null;
	private String targeSever=null;
	public void setter(String Name,String Autcode)
	{
		name=Name;
		autcode=Autcode;
		targeSever="."+Name.split("@")[1];
	}
	public String getName() {return name;}
	public String getAutcode() {return autcode;}
	public String getSever() {return targeSever;}
}
