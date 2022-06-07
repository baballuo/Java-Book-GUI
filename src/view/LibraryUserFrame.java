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

/**
 * �û���¼���������
 * 
 * @author ������
 *
 */
public class LibraryUserFrame extends JFrame {
	public static String USER_NAME;

	public LibraryUserFrame() {
		this.setLayout(null);
		ImageIcon img = new ImageIcon(
				"image/3.jpg");
		// Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		// ������ͼ���ڱ�ǩ�
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// ��������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);

		setSize(1000, 750);
		setTitle("ͼ��ݹ���ϵͳ_�û�");
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
		JMenuItem j1_1 = new JMenuItem("����ͼ��");
		j1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookSearch().setVisible(true);
			}
		});
		JMenuItem j1_2 = new JMenuItem("�鿴����ͼ��");
		j1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllBook().setVisible(true);
			}
		});
		JMenuItem j1_3 = new JMenuItem("����ͼ��");
		j1_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserBorrow().setVisible(true);
			}
		});

		JMenuItem j1_4 = new JMenuItem("�黹ͼ��");
		j1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserReturn().setVisible(true);
			}
		});

		j1.add(j1_1);
		j1.add(j1_2);
		j1.add(j1_3);
		j1.add(j1_4);

		JMenu j2 = new JMenu("��������");
		JMenuItem j2_1 = new JMenuItem("�޸�����");
		j2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserXiugaimima().setVisible(true);
			}
		});
		j2.add(j2_1);

		JMenu j3 = new JMenu("ϵͳ");
		JMenuItem j3_1 = new JMenuItem("ע����¼");
		j3_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LogIn().setVisible(true);
			}
		});
		j3.add(j3_1);

		menuBar.add(j1);
		menuBar.add(j2);
		menuBar.add(j3);

		this.setJMenuBar(menuBar);

	}
}
