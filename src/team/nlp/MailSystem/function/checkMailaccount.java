package team.nlp.MailSystem.function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/***
 * 
 * @author jun
 * 检查邮箱账号的正确性模块
 */
public class checkMailaccount {
	private String name=null;
	public checkMailaccount(String Name)
	{
		name=Name;
	}
	public boolean result()
	{
		String regex=".+?@\\w+?\\.com";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(name);
		return !m.matches();
	}
}
