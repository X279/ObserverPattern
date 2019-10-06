package publish_subscribe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Publish extends Thread {
	//���ı����ںŵ��û�
	private List<Subscriber> subscribers = new ArrayList<Subscriber>();
	private String message;
	GridBagLayout gridBagLayout = new GridBagLayout();//���ֹ�����
	GridBagConstraints constraints = null;//���Լ��
	private JFrame jFrame = new JFrame("���ں�ƽ̨");
	private JButton release = new JButton("����");
	private JButton clear = new JButton("���");
	private JButton quit = new JButton("�˳�");
	private JTextArea messageArea = new JTextArea("������Ϣ��",10,10);
	JScrollPane scrollPane = new JScrollPane(messageArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	public void run()
	{
		jFrame.setSize(500,450);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setLayout(gridBagLayout);
		palceComponents();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
	
	private void palceComponents() {
		// TODO Auto-generated method stub
		constraints = new GridBagConstraints();
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		gridBagLayout.addLayoutComponent(release, constraints);
		release.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("����������");
				String[] str = messageArea.getText().split("������Ϣ��");
				System.out.println("������ϢΪ��"+Arrays.toString(str));
				setMessage(Arrays.toString(str));
			}
		});
		jFrame.add(release);
		
		constraints.gridwidth = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		gridBagLayout.addLayoutComponent(clear, constraints);
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				messageArea.setText("������Ϣ��");
			}
		});
		jFrame.add(clear);
		
		constraints.gridwidth = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		gridBagLayout.addLayoutComponent(quit, constraints);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		jFrame.add(quit);
		
		constraints.gridwidth = 0;
		constraints.weightx = 0;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		messageArea.setEditable(true);//����Ϊ�ɱ༭
		messageArea.setLineWrap(true); //�����Զ�����
		messageArea.setWrapStyleWord(true);
		gridBagLayout.addLayoutComponent(scrollPane, constraints);
		jFrame.add(scrollPane);
	}
	
	public void setMessage(String message)
	{
		this.message = message;
		notifyAllSubscriber();
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void attach(Subscriber subscriber)
	{
		subscribers.add(subscriber);
	}
	
	public void notifyAllSubscriber()
	{
		for(Subscriber subscriber : subscribers)
		{
			subscriber.update();
		}
	}
}
