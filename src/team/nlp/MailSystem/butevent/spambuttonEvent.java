package team.nlp.MailSystem.butevent;
import java.awt.event.*;
import javax.swing.*;

/**
* @author Cianc
* @version ����ʱ�䣺2019��2��21�� ����8:51:58
* @ClassName spambuttonEvent
* @Description ����Ϊ�����ʼ���ť�ù���ʵ�֣�����ʵ�ֹ���Ϊ��
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
		JLabel titleLabel = new JLabel(" /*��֮����в���*/ ");
		
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


