package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.DBUtil;

/**
 * ����Ա����鼮����
 * 
 * @author ������
 *
 */
public class BookAddFrame extends JFrame {
	private JPanel panBtn = new JPanel();
	private JPanel panLab = new JPanel();
	// private JLabel jLabel1 = new JLabel("ͼ��������");
	private JLabel jLabel2 = new JLabel("ͼ�����ƣ�");
	private JLabel jLabel4 = new JLabel("ͼ�����ߣ�");
	private JLabel jLabel3 = new JLabel("ͼ�����ͣ�");
	private JLabel jLabel5 = new JLabel("ͼ��۸�");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JButton jButton1 = new JButton("���");
	private JButton jButton2 = new JButton("����");
	private JLabel jLabel6 = new JLabel();

	public BookAddFrame() {
		setTitle("����鼮");
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

		jLabel6.setFont(new Font("����", 0, 24));
		jLabel6.setHorizontalAlignment(JLabel.CENTER);
		jLabel6.setForeground(new Color(255, 51, 51));
		jLabel6.setText("��  ��  ͼ  ��");

		// jLabel1.setSize(100, 80);
		// jLabel1.setLocation(50, 20);
		// jLabel1.setHorizontalAlignment(JLabel.RIGHT);

		jLabel2.setSize(100, 80);
		jLabel2.setLocation(50, 5);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);

		jLabel3.setSize(100, 80);
		jLabel3.setLocation(50, 40);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);

		jLabel4.setSize(100, 80);
		jLabel4.setLocation(50, 80);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);

		jLabel5.setSize(100, 80);
		jLabel5.setLocation(50, 120);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);

		// jTextField1.setSize(150, 20);
		// jTextField1.setLocation(160,50);

		jTextField2.setSize(150, 25);
		jTextField2.setLocation(160, 33);

		// jTextField3.setSize(150, 20);
		// jTextField3.setLocation(160, 90);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("�Ƽ���");
		comboBox.addItem("�ഺ��ѧ");
		comboBox.addItem("��ѧ��");
		comboBox.addItem("������");
		comboBox.addItem("������Ȼ");
		comboBox.addItem("��ѧ");
		comboBox.addItem("�ۺ���");
		comboBox.setSize(150, 25);
		comboBox.setLocation(160, 70);

		jTextField4.setSize(150, 25);
		jTextField4.setLocation(160, 110);

		jTextField5.setSize(150, 25);
		jTextField5.setLocation(160, 150);

		panBtn.add(jButton1);
		panBtn.add(jButton2);
		panLab.setLayout(null);

		// panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);

		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(comboBox);
		panLab.add(jTextField4);
		panLab.add(jTextField5);

		add(jLabel6, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panLab, BorderLayout.CENTER);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// String count2 = jTextField1.getText().trim();
				// int count = Integer.parseInt(count2);
				String bookname = jTextField2.getText().trim();
				String booktype = comboBox.getSelectedItem().toString();
				String writer = jTextField4.getText().trim();
				String bookprice = jTextField5.getText().trim();

				double p = Double.parseDouble(bookprice);

				Statement stmt = DBUtil.getStatement();
				String sql = "select * from book where BookName='" + bookname
						+ "';";
				String sql2 = "insert into book(BookName,BookType,Writter,Price) value('"
						+ bookname
						+ "','"
						+ booktype
						+ "','"
						+ writer
						+ "',"
						+ p + ");";
				try {
					ResultSet rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "��ӳɹ���");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "�����Ѵ��ڣ�");
						jTextField2.setText("");
						jTextField3.setText("");
						jTextField4.setText("");
						jTextField5.setText("");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextField5.setText("");
			}
		});
	}
}
