package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.DBUtil;

/**
 * �û���ע�����
 * 
 * @author ������
 *
 */
public class ZhuceFrame extends JFrame {
	private JLabel labTitle = new JLabel("ͼ����Ϣ����ϵͳע��");
	private Font font = new Font("����", 0, 24);
	private JButton btnSure = new JButton("ע��");
	private JButton btnCancel = new JButton("����");
	private JPanel panBtn = new JPanel();

	private JLabel labLoginName = new JLabel("�û���:");
	private JLabel labPWD = new JLabel("��    ��:");
	private JLabel labEmail = new JLabel("��    ��:");
	private JLabel labAddr = new JLabel("ס    ַ:");
	private JLabel labPhone = new JLabel("��    ��:");
	private JTextField jtfLoginName, jtfAddr, jtfPhone, jtfEmail;
	private JPasswordField jpfPWD;
	private JPanel panMain = new JPanel();

	public ZhuceFrame() {
		setTitle("�û�ע�����");
		this.setSize(400, 300);
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
		labTitle.setBackground(Color.BLUE);

		jtfLoginName = new JTextField("", 10);
		jpfPWD = new JPasswordField(16);
		jtfAddr = new JTextField("", 20);
		jtfEmail = new JTextField("", 20);
		jtfPhone = new JTextField("", 20);

		labLoginName.setSize(100, 80);
		labLoginName.setLocation(50, 20);
		labLoginName.setHorizontalAlignment(JLabel.RIGHT);

		labPWD.setSize(100, 80);
		labPWD.setLocation(50, 40);
		labPWD.setHorizontalAlignment(JLabel.RIGHT);

		labEmail.setSize(100, 80);
		labEmail.setLocation(50, 60);
		labEmail.setHorizontalAlignment(JLabel.RIGHT);

		labAddr.setSize(100, 80);
		labAddr.setLocation(50, 80);
		labAddr.setHorizontalAlignment(JLabel.RIGHT);

		labPhone.setSize(100, 80);
		labPhone.setLocation(50, 100);
		labPhone.setHorizontalAlignment(JLabel.RIGHT);

		jtfLoginName.setSize(150, 20);
		jtfLoginName.setLocation(160, 50);

		jpfPWD.setSize(150, 20);
		jpfPWD.setLocation(160, 70);
		jpfPWD.setEchoChar('*');

		jtfEmail.setSize(150, 20);
		jtfEmail.setLocation(160, 90);

		jtfAddr.setSize(150, 20);
		jtfAddr.setLocation(160, 110);

		jtfPhone.setSize(150, 20);
		jtfPhone.setLocation(160, 130);

		panBtn.add(btnSure);
		panBtn.add(btnCancel);
		panMain.setLayout(null);

		panMain.add(labLoginName);
		panMain.add(labPWD);
		panMain.add(labAddr);
		panMain.add(labEmail);
		panMain.add(labPhone);

		panMain.add(jtfLoginName);
		panMain.add(jpfPWD);
		panMain.add(jtfAddr);
		panMain.add(jtfPhone);
		panMain.add(jtfEmail);

		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡ�û��������Ϣ
				String name = jtfLoginName.getText().trim();
				String addr = jtfAddr.getText().trim();
				String email = jtfEmail.getText().trim();
				String phone = jtfPhone.getText().trim();
				String password = jpfPWD.getText().trim();

				Statement stmt = DBUtil.getStatement();
				String sql = "select * from user where username='" + name
						+ "';";
				String sql2 = "insert into user(username,userpsw,useremail,useraddr,userphone) value('"
						+ name
						+ "','"
						+ password
						+ "','"
						+ email
						+ "','"
						+ addr + "','" + phone + "')";
				try {
					ResultSet rs = stmt.executeQuery(sql); // �鿴�û��Ƿ�����
					if (!rs.next()) {
						stmt.execute(sql2);// �����ݿ�������û���Ϣ��ע��ɹ�
						JOptionPane.showMessageDialog(null, "��ϲ����ע��ɹ�", "��ϲ",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�˺��Ѵ��ڣ�");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfLoginName.setText("");
				jpfPWD.setText("");
				jtfAddr.setText("");
				jtfPhone.setText("");
				jtfEmail.setText("");
			}
		});
	}
}
