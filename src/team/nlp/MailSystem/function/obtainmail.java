package team.nlp.MailSystem.function;
import javax.mail.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
* @author Cianc
* @version 创建时间：2019年3月3日 上午10:45:05
* @ClassName obtainmail	
* @Description 用于获取邮件
*/
public class obtainmail {
	private int mailSize;
	private Folder folder = null;
	private Store store = null;
	// 服务器地址
	private String ipv4 = "39.108.113.86";
	private int port = 42000;
	public static Queue<Message> normMail = new LinkedList<Message>();
	public static Queue<Message> rabbMail = new LinkedList<Message>();
	// 创建字节输出与输入流
	private InputStream in = null;
	private OutputStream out = null;
	Message []message = null;
	public obtainmail(String server, Session obtainSession, String accont, String password) {
		try {
			store = obtainSession.getStore("pop3");
			store.connect(server, accont, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			int z = 0;
			message = folder.getMessages();
			mailSize = message.length - 1;
			while(mailSize >= 0) {
				try {
					Socket s = new Socket(ipv4,port);
					in = s.getInputStream();
					out = s.getOutputStream();
					out.write(message[mailSize].getSubject().getBytes("UTF-8"));
					out.flush();
					byte[] bytes = new byte[102400];
					int n = in.read(bytes);
					String str = new String(bytes, 0 , n,"utf-8");
					if(str == "1") {
						rabbMail.add(message[mailSize]);
					} else if(str == "0") {
						normMail.add(message[mailSize]);
					} else {
						rabbMail.add(message[mailSize]);
					}
					in.close();
					out.close();
					s.close();
					mailSize--;
					z++;
					if(z > 100) {
						break;
					}
				} catch (MessageRemovedException e){
					continue;
				}
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

////解析邮件内容
//String from = message.getFrom()[0].toString();
//String subject = message.getSubject();
//Date date = message.getSentDate();
//System.out.println("From: " + from);
//System.out.println("Subject: " + subject);
//System.out.println("Date: " + date);  