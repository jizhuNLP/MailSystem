package team.nlp.MailSystem.butevent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.mail.*;
import team.nlp.MailSystem.function.obtainmail;

/**
* @author Cianc
* @version ����ʱ�䣺2019��2��21�� ����8:51:58
* @ClassName spambuttonEvent
* @Description ����Ϊ�����ʼ���ť�ù���ʵ�֣�����ʵ�ֹ���Ϊ��
* 				
*/
public class spambuttonEvent implements ActionListener{
	JPanel spamPanel = null;
	JPanel spamBox = null;
	boolean isNorm = false;
	Queue<Message> queue = new LinkedList<Message>();
	Queue<Message> temp = null;
	public spambuttonEvent(JPanel panel, boolean isNorm){
		spamPanel = panel;
		this.isNorm = isNorm;
	}
	
	private void init() {
		if (isNorm) {
			temp = obtainmail.normMail;
			copy(temp);
		} else {
			temp = obtainmail.rabbMail;
			copy(temp);
		}
	}
	
	private void copy(Queue q){
		int num = q.size();
		Message msg = null;
		while(num-->0) {
			msg = (Message) q.poll();
			q.add(msg);
			queue.add(msg);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		spamPanel.removeAll();
		init();
		JScrollPane scrollPane = new JScrollPane();
		spamPanel.setLayout(null);
		spamBox = new JPanel();
		spamBox.setLayout(null);
		spamBox.setBackground(Color.white);
		int numMail = 0;
		int height = 20;
		int leftWidth = 405;
		int rightWidth = 135;
		while(!queue.isEmpty()) {
			Message msg = queue.poll();
			JLabel leftLabel = null,rightLabel = null;
			try {
				leftLabel = new JLabel(msg.getSubject());
				rightLabel = new JLabel(msg.getSentDate().toString());
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				continue;
			}
			leftLabel.setBounds( 5, numMail*height, leftWidth, height);
			rightLabel.setBounds( leftWidth, numMail*height, rightWidth, height);
			leftLabel.setHorizontalTextPosition(JLabel.LEFT);
			rightLabel.setHorizontalTextPosition(JLabel.RIGHT);
			leftLabel.setFont(new Font("����",Font.CENTER_BASELINE,20));
			rightLabel.setFont(new Font("����",Font.CENTER_BASELINE,20));
			spamBox.add(leftLabel);
			spamBox.add(rightLabel);
			numMail++;
			leftLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					displayTitle(msg);
				}
			});
			rightLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					displayTitle(msg);
				}
			});
		}
		spamBox.setPreferredSize(new Dimension(spamPanel.getWidth(), height * numMail));
		scrollPane.setViewportView(spamBox);
		scrollPane.setBounds(0,0,spamPanel.getWidth(),spamPanel.getHeight());
		spamPanel.add(scrollPane);
		spamPanel.updateUI();
	}
	
	private void displayTitle(Message msg) {
		spamPanel.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0,0,spamPanel.getWidth(),spamPanel.getHeight());
		try {
			JLabel []title = {
					new JLabel("�����ˣ�" + msg.getFrom()[0].toString()),
					new JLabel("�������⣺" + msg.getSubject()),
					new JLabel("����ʱ�䣺" + msg.getSentDate().toString())
			};
			for(int index = 0; index < title.length; index++) {
				title[index].setBounds(5, (25 * index) + 10, 405, 25);
				spamPanel.add(title[index]);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		JButton button = new JButton("����");
		button.setBounds(460, 10, 80, 75);
		JTextArea text = new JTextArea();
		scrollPane.setViewportView(text); // ������
		if(isNorm) {
			button.addActionListener(new spambuttonEvent(spamPanel, true));
		} else {
			button.addActionListener(new spambuttonEvent(spamPanel, false));
		}
		spamPanel.add(button);
		spamPanel.add(scrollPane);
		spamPanel.updateUI();
	}
}


