package team.nlp.MailSystem.butevent;
import java.awt.event.*;
import javax.swing.*;

/**
* @author Cianc
* @version 创建时间：2019年2月21日 下午8:51:58
* @ClassName spambuttonEvent
* @Description 该类为垃圾邮件按钮得功能实现，具体实现功能为：
* 				
*/
public class spambuttonEvent implements ActionListener{
	JPanel spamPanel = null;
	JScrollPane scrollPane = new JScrollPane();
	public spambuttonEvent(JPanel panel){
		spamPanel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		spamPanel.removeAll();
		scrollPane.setBounds(0,0,spamPanel.getWidth(),spamPanel.getHeight());
		JLabel titleLabel = new JLabel(" /*在之后进行补充*/ ");
		
		titleLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				displayTitle();
			}
		});
		spamPanel.add(scrollPane);
		spamPanel.updateUI();
	}
	
	private void displayTitle() {
		spamPanel.removeAll();
		scrollPane.setBounds(0,0,spamPanel.getWidth(),spamPanel.getHeight());
		
		spamPanel.add(scrollPane);
		spamPanel.updateUI();
	}
}


