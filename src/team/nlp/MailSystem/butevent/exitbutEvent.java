package team.nlp.MailSystem.butevent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
public class exitbutEvent implements ActionListener{
	private JFrame frame=null;
	public exitbutEvent(JFrame parentframe)
	{
		frame=parentframe;
	}
	public void actionPerformed(ActionEvent e)
	{
		frame.dispose();
	}
}