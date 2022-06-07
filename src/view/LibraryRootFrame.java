package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Date.BarTest;
import Date.PieChart;
import Date.PieTest;
/**
 * ����Ա��½���������
 * 
 * @author ������
 *
 */
public class LibraryRootFrame extends JFrame {
	public static String USER_NAME;

	public LibraryRootFrame() {
		this.setLayout(null);
		ImageIcon img = new ImageIcon("image/3.jpg");
		//Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		//������ͼ���ڱ�ǩ�
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//��������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); 

		setSize(1000, 730);
		setTitle("ͼ��ݹ���ϵͳ��������Ա");
		setResizable(false); // ���ɸı䴰�ڴ�С
		// ��ȡ��Ļ��С�͵�ǰframe�Ĵ�С
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// ʹ��������λ����Ļ��������
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// ���õ������ڵġ��رա���ťʱ��������Ӧ�Ķ���
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar(); // �����˵���
		// �����˵�
		JMenu j1 = new JMenu("�鼮����");
		JMenuItem J1_1 = new JMenuItem("����鼮");
		J1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookAddFrame().setVisible(true);
			}
		});

		JMenuItem J1_2 = new JMenuItem("���º�ɾ���鼮");
		J1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Book_Update_Delete().setVisible(true);
			}
		});
		
		JMenuItem J1_3 = new JMenuItem("�����鼮");
		J1_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookSearch().setVisible(true);
			}
		});
		
		JMenuItem J1_4 = new JMenuItem("�鿴�����鼮");
		J1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllBook().setVisible(true);
			}
		});
		j1.add(J1_1);
		j1.add(J1_2);
		j1.add(J1_3);
		j1.add(J1_4);

		JMenu j2 = new JMenu("�û�����");
		JMenuItem J2_1 = new JMenuItem("����û�");
		J2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserAddFrame().setVisible(true);
			}
		});

		JMenuItem J2_2 = new JMenuItem("���º�ɾ���û�");
		J2_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new User_Update_Delete().setVisible(true);
			}
		});

		JMenuItem J2_3 = new JMenuItem("��ѯ�û�");
		J2_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserSearch().setVisible(true);
			}
		});
		j2.add(J2_1);
		j2.add(J2_2);
		j2.add(J2_3);

		JMenu j3 = new JMenu("�����¼");
		JMenu J3 = new JMenu("�������ݷ���");
		JMenuItem J3_1 = new JMenuItem("����ͼ�鿴");
		J3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PieTest().setVisible(true);
			}
		});
		JMenuItem J3_2 = new JMenuItem("����ͼ�鿴");
		J3_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BarTest().setVisible(true);
			}
		});
		JMenuItem j3_1 = new JMenuItem("�鿴�����¼");
		j3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllBorrow().setVisible(true);
			}
		});
		J3.add(J3_1);
		J3.add(J3_2);
		j3.add(J3);
		j3.add(j3_1);
		
		JMenu j4 = new JMenu("ϵͳ����");
		JMenuItem j4_1 = new JMenuItem("�޸�����");
		j4_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RootXiugaiMima().setVisible(true);
			}
		});

		JMenuItem j4_2 = new JMenuItem("ע����¼");
		j4_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LogIn().setVisible(true);
			}
		});
		j4.add(j4_1);
		j4.add(j4_2);

		menuBar.add(j1);
		menuBar.add(j2);
		menuBar.add(j3);
		menuBar.add(j4);

		this.setJMenuBar(menuBar);
	}
	public static void main(String[] args) {
		new LibraryRootFrame().setVisible(true);
	}
}
