package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import utils.DBUtil;

/**
 * ����Ա�޸��Լ���¼����Ľ���
 * 
 * @author ������
 *
 */
public class RootXiugaiMima extends JFrame {
	private JLabel jLabel1 = new JLabel("ԭ  ��  �룺");
	private JLabel jLabel2 = new JLabel("��  ��  �룺");
	private JLabel jLabel3 = new JLabel("�ظ����룺");
	private JLabel JLabel4 = new JLabel("��  ��  ��  ��");
	private JPasswordField JP1 = new JPasswordField();
	private JPasswordField JP2 = new JPasswordField();
	private JPasswordField JP3 = new JPasswordField();
	private JPanel panLab = new JPanel();
	private JPanel panBtn = new JPanel();
	private JButton jButton1 = new JButton("ȷ ��");
	private JButton jButton2 = new JButton("�� ��");

	public RootXiugaiMima() {
		setTitle("�޸�����_����Ա");
		setSize(400, 300);
		setResizable(false); // ���ɸı䴰�ڴ�С
		// ��ȡ��Ļ��С�͵�ǰframe�Ĵ�С
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// ʹ��������λ����Ļ��������
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// ���õ������ڵġ��رա���ťʱ��������Ӧ�Ķ���
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		JLabel4.setFont(new Font("����", 0, 24));
		JLabel4.setHorizontalAlignment(JLabel.CENTER);
		JLabel4.setForeground(new Color(255, 51, 51));

		jLabel1.setSize(100, 100);
		jLabel1.setLocation(50, 5);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		JP1.setSize(150, 30);
		JP1.setLocation(160, 40);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(50, 60);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		JP2.setSize(150, 30);
		JP2.setLocation(160, 95);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(50, 115);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		JP3.setSize(150, 30);
		JP3.setLocation(160, 150);

		panLab.setLayout(null);
		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(JP1);
		panLab.add(JP2);
		panLab.add(JP3);

		panBtn.add(jButton1);
		panBtn.add(jButton2);

		add(JLabel4, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panLab, BorderLayout.CENTER);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Old = new String(JP1.getPassword()).trim();
				String New = new String(JP2.getPassword()).trim();
				String Again = new String(JP3.getPassword()).trim();
				String username = LibraryRootFrame.USER_NAME;// ��ȡ��ǰ��¼�û���

				Statement stmt = DBUtil.getStatement();
				String sql = "update root set rootPWD ='" + New
						+ "' where rootName = '" + username + "';";
				String sql2 = "select * from root where rootName='" + username
						+ "' AND rootPWD='" + Old + "';";
				try {
					ResultSet rs = stmt.executeQuery(sql2);
					if (rs.next()) {
						if (!New.equals(Again)) {
							JOptionPane.showMessageDialog(null, "�����������벻һ�£�");
						} else {
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
						}
					} else {
						JOptionPane.showMessageDialog(null, "ԭ�����������");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JP1.setText("");
				JP2.setText("");
				JP3.setText("");
			}
		});
	}
}
