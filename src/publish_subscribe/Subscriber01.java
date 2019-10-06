package publish_subscribe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Subscriber01 extends Thread implements Subscriber{
	private Publish publisher;
	private String message;
	private JFrame jFrame = new JFrame("������1");
	private JButton button = new JButton("ˢ��");
	private JButton quit = new JButton("�˳�");
	GridBagLayout gridBagLayout = new GridBagLayout();//���ֹ�����
	GridBagConstraints constraints = null;//���Լ��
	private JTextArea messageArea = new JTextArea("�������ݣ�",5,10);
	JScrollPane scrollPane = new JScrollPane(messageArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	
	public Subscriber01(Publish pub)
	{
		publisher = pub;
		this.publisher.attach(this);
	}
	
	@Override
	public void update()
	{
		this.message = this.publisher.getMessage();
	}
	
	public void run()
	{
		jFrame.setSize(400,400);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setLayout(gridBagLayout);
		place();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
	
	private void place()
	{
		constraints = new GridBagConstraints();
		
		constraints.gridwidth = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		gridBagLayout.addLayoutComponent(button, constraints);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				messageArea.append(message+"\n");
			}
		});
		jFrame.add(button);
		
		constraints.gridwidth = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		gridBagLayout.addLayoutComponent(quit, constraints);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		jFrame.add(quit);
		
		constraints.gridwidth = 0;
		constraints.weightx = 0;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		messageArea.setEditable(false);//����Ϊ���ɱ༭
		messageArea.setLineWrap(true); //�����Զ�����
		messageArea.setWrapStyleWord(true);
		gridBagLayout.addLayoutComponent(scrollPane, constraints);
		jFrame.add(scrollPane);
	}
}
