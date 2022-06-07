package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utils.DBUtil;

/*
    *@Ballas_code
    *create_time:2022/5/22 9:28
    @param null
    *@return
*/
public class LogIn extends JFrame {
	private JLabel labTitle = new JLabel("ͼ����Ϣ����ϵͳ��¼");
	private Font font = new Font("����", Font.BOLD, 34);
	private JButton btnSure = new JButton("��¼");
	private JButton btnCancel = new JButton("����");
	private JButton btnZhuce = new JButton("ע��");
	private JPanel panBtn = new JPanel();

	private JLabel labLoginName = new JLabel("�û���:");
	private JLabel labPWD = new JLabel("��    ��:");
	private JTextField jtfLoginName;
	private JPasswordField jpfPWD;
	private JPanel panMain = new JPanel();

	public LogIn() {
		setTitle("��¼����");
		// setDefaultLookAndFeelDecorated(true);
		this.setSize(500, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		add(labTitle, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panMain, BorderLayout.CENTER);
	}

	private void init() {
		labTitle.setFont(font);
		labTitle.setHorizontalAlignment(JLabel.CENTER);
		labTitle.setForeground(Color.RED);
		// labTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		labTitle.setBackground(Color.BLUE);
		// ��ѡ��
		JRadioButton JB1 = new JRadioButton("����Ա��¼");
		JRadioButton JB2 = new JRadioButton("�û���¼");//
		// �����飬������ֿ���������ѡ������
		ButtonGroup bg = new ButtonGroup();
		JB2.setSelected(true);// Ĭ��ѡ��'�û���¼'

		jtfLoginName = new JTextField("", 10);
		jpfPWD = new JPasswordField(16);

		labLoginName.setSize(100, 40);
		labLoginName.setLocation(50, 50);
		labLoginName.setHorizontalAlignment(JLabel.RIGHT);
		labPWD.setSize(100, 40);
		labPWD.setLocation(50, 50 + 40 + 10);
		labPWD.setHorizontalAlignment(JLabel.RIGHT);

		jtfLoginName.setSize(200, 40);
		jtfLoginName.setLocation(50 + 100 + 20, 50);
		jpfPWD.setSize(200, 40);
		jpfPWD.setLocation(50 + 100 + 20, 50 + 40 + 10);
		jpfPWD.setEchoChar('*');

		JB1.setSize(90, 20);
		JB1.setLocation(125, 175);
		JB2.setSize(80, 20);
		JB2.setLocation(275, 175);

		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JB2.isSelected()) {
					Statement stmt = DBUtil.getStatement();
					String name = jtfLoginName.getText().trim();//trim();���Text�е����ݲ���ȥ���ո�
					String pwd = new String(jpfPWD.getPassword()).trim();
					String sql = "select * from user where username='" + name
							+ "' AND userpsw='" + pwd + "';";
					try {
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showConfirmDialog(LogIn.this,
									"��½�ɹ�����ӭ����!", "��½�ɹ�",
									JOptionPane.CLOSED_OPTION);
							dispose();
							new LibraryUserFrame().setVisible(true);
							LibraryUserFrame.USER_NAME = name;
						} else {
							JOptionPane.showMessageDialog(null, "�˺Ż��������");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					/*
					 * UserDao UD = new UserDao(); UserBean uBean =
					 * UD.findUser(name, pwd); if (uBean != null) {
					 * JOptionPane.showConfirmDialog(LogIn.this, "��½�ɹ�����ӭ����!",
					 * "��½�ɹ�", JOptionPane.CLOSED_OPTION); dispose();
					 * LibraryUserFrame LU = new LibraryUserFrame();
					 * LU.setVisible(true); LibraryUserFrame.USER_NAME = name;
					 * }else { JOptionPane.showMessageDialog(null, "�˺Ż��������");
					 * }
					 */

				} else {
					Statement stmt = DBUtil.getStatement();
					String name = jtfLoginName.getText().trim();
					String pwd = new String(jpfPWD.getPassword()).trim();
					String sql = "select * from root where rootName='" + name
							+ "' AND rootPWD='" + pwd + "';";
					try {
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showConfirmDialog(LogIn.this,
									"��½�ɹ�����ӭ����!", "��½�ɹ�",
									JOptionPane.CLOSED_OPTION);
							dispose();
							LibraryRootFrame LR = new LibraryRootFrame();
							LR.setVisible(true);
							LibraryRootFrame.USER_NAME = name;
						} else {
							JOptionPane.showMessageDialog(null, "�˺Ż��������");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfLoginName.setText("");
				jpfPWD.setText("");
			}
		});

		btnZhuce.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();//���ٳ�����ָ����ͼ�ν���
				new ZhuceFrame().setVisible(true);
			}
		});

		panBtn.add(btnSure);
		panBtn.add(btnCancel);
		panBtn.add(btnZhuce);
		// �ѵ�ѡ��ť������
		bg.add(JB1);
		bg.add(JB2);

		panMain.setLayout(null);
		panMain.add(labLoginName);
		panMain.add(labPWD);
		panMain.add(jtfLoginName);
		panMain.add(jpfPWD);
		panMain.add(JB1);
		panMain.add(JB2);
		ImageIcon img = new ImageIcon("image/ZhuCe.png");
		// Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		// ������ͼ���ڱ�ǩ�
		panMain.add(imgLabel, new Integer(Integer.MIN_VALUE));
		// ��������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
	}

	public static void main(String[] args) {
		new LogIn().setVisible(true);
	}
}
