package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

/**
 * �û�����Ľ���
 * 
 * @author ������
 *
 */
public class UserBorrow extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();
	private JButton btn1 = new JButton("����");
	private JButton btn2 = new JButton("�˳�");
	private int row;
	private MyModel model;

	private JLabel jLabel1 = new JLabel("ͼ���ţ�");
	private JLabel jLabel2 = new JLabel("ͼ�����ƣ�");
	private JLabel jLabel3 = new JLabel("�������ڣ�");
	private JLabel jLabel4 = new JLabel("�������ڣ�");
	private JLabel jLabel5 = new JLabel("��  ��  �ˣ�");
	private JLabel jLabel6 = new JLabel("������ID��");
	private JLabel jLabel7 = new JLabel("��  ��  �ͣ�");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JTextField jTextField7 = new JTextField();

	private Panel panBtn = new Panel();
	private Panel panLab = new Panel();

	public UserBorrow() {
		setTitle("���鴰��");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// ���ÿɼ���ͼ�Ľӿ�
		jsp.setViewportView(table);
		// ������ ��600���߶�200
		jsp.setPreferredSize(new Dimension(600, 200));
		// ���ú���ʹ�ֱ�������ɼ�
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		panBtn.add(btn1);
		panBtn.add(btn2);
		// �����м��panel����Ϊ��
		panLab.setLayout(null);
		JButton jButton1 = new JButton("����");
		JTextField jTextField = new JTextField();
		// ��ѡ��
		JRadioButton JB1 = new JRadioButton("����������");
		JRadioButton JB2 = new JRadioButton("�����߲���");
		JB1.setSelected(true); // Ĭ��ѡ��JB1
		// �����飬������ֿ���������ѡ������
		ButtonGroup bg = new ButtonGroup();
		bg.add(JB1);
		bg.add(JB2);

		JB1.setSize(100, 30);
		JB1.setLocation(40, 30);

		JB2.setSize(100, 30);
		JB2.setLocation(140, 30);

		jTextField.setSize(160, 30);
		jTextField.setLocation(250, 30);

		jButton1.setSize(80, 30);
		jButton1.setLocation(450, 30);

		// ����ǩ���ı�������λ��
		jLabel1.setSize(100, 100);
		jLabel1.setLocation(10, 60);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		jTextField1.setSize(150, 30);
		jTextField1.setLocation(110, 95);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(280, 60);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		jTextField2.setSize(150, 30);
		jTextField2.setLocation(380, 95);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(10, 120);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		jTextField3.setSize(150, 30);
		jTextField3.setLocation(110, 155);

		// �Զ���ȡ����ʱ��(��ǰʱ��)
		Calendar c = Calendar.getInstance();
		// ��ȡ������
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		String time = year + "-" + month + "-" + date;
		jTextField3.setText(time);

		jLabel4.setSize(100, 100);
		jLabel4.setLocation(280, 120);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);
		jTextField4.setSize(150, 30);
		jTextField4.setLocation(380, 155);

		// �Զ���ȡӦ����ʱ�䣨���ý���ʱ��Ϊһ���£�
		Calendar c2 = Calendar.getInstance();// ���Զ�ÿ��ʱ���򵥶��޸�
		// ��ȡ������
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 2;
		int date2 = c2.get(Calendar.DATE);
		String time2 = year2 + "-" + month2 + "-" + date2;
		jTextField4.setText(time2);

		jLabel5.setSize(100, 100);
		jLabel5.setLocation(10, 180);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);
		jTextField5.setSize(150, 30);
		jTextField5.setLocation(110, 215);

		String username = LibraryUserFrame.USER_NAME;
		jTextField5.setText(username);// �Զ���ȡ��ǰ��¼�û���

		jLabel6.setSize(100, 100);
		jLabel6.setLocation(280, 180);
		jLabel6.setHorizontalAlignment(JLabel.RIGHT);
		jTextField6.setSize(150, 30);
		jTextField6.setLocation(380, 215);

		jLabel7.setSize(100, 100);
		jLabel7.setLocation(10, 230);
		jLabel7.setHorizontalAlignment(JLabel.RIGHT);
		jTextField7.setSize(150, 30);
		jTextField7.setLocation(110, 265);

		String sql = "select userid from user where username = '" + username
				+ "';";
		Statement st = DBUtil.getStatement();
		try {
			ResultSet r = st.executeQuery(sql);
			while (r.next()) {
				String sId = r.getString(1);
				jTextField6.setText(sId);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// �ѱ�ǩ���ı���ӵ�panLab�����
		// panLab.add(Label);
		// panLab.add(comboBox);
		panLab.add(JB1);
		panLab.add(JB2);
		panLab.add(jTextField);
		panLab.add(jButton1);

		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jLabel6);
		panLab.add(jLabel7);
		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(jTextField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);
		panLab.add(jTextField6);
		panLab.add(jTextField7);

		this.add(jsp, BorderLayout.NORTH);
		this.add(panLab, BorderLayout.CENTER);
		this.add(panBtn, BorderLayout.SOUTH);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JB1.isSelected()) {
					String s = jTextField.getText().trim();
					String sql = "select * from book where BookName like '%"
							+ s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("���");
					table.getColumnModel().getColumn(1).setHeaderValue("����");
					table.getColumnModel().getColumn(2).setHeaderValue("����");
					table.getColumnModel().getColumn(3).setHeaderValue("����");
					table.getColumnModel().getColumn(4).setHeaderValue("�۸�");
					table.getColumnModel().getColumn(5).setHeaderValue("�Ƿ���");

				} else {
					String s = jTextField.getText().trim();
					String sql = "select * from book where Writter like '%" + s
							+ "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("���");
					table.getColumnModel().getColumn(1).setHeaderValue("����");
					table.getColumnModel().getColumn(2).setHeaderValue("����");
					table.getColumnModel().getColumn(3).setHeaderValue("����");
					table.getColumnModel().getColumn(4).setHeaderValue("�۸�");
					table.getColumnModel().getColumn(5).setHeaderValue("�Ƿ���");
				}
			}
		});

		// ��ȡ�����ֵ
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id, name, type;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				type = table.getValueAt(selRow, 3).toString().trim();
				jTextField1.setText(id);
				jTextField2.setText(name);
				jTextField7.setText(type);
			}
		});

		// ʹ��ŵ��ı��򲻿ɱ༭
		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setEditable(false);
		jTextField4.setEditable(false);
		jTextField5.setEditable(false);
		jTextField6.setEditable(false);
		jTextField7.setEditable(false);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				String bookname = jTextField2.getText().trim();
				String btime = jTextField3.getText().trim();
				String rtime = jTextField4.getText().trim();
				String username = jTextField5.getText().trim();
				String userid2 = jTextField6.getText().trim();
				int userid = Integer.parseInt(userid2);

				String type = jTextField7.getText().trim();

				Statement stmt = DBUtil.getStatement();
				String sql1 = "select IsBorrow from book where BookId = " + i
						+ ";";
				String sql2 = "update book set IsBorrow = '��' where BookId = "
						+ i + ";";
				String sql3 = "insert into borrow(BookId,BookName,BookType,userid,username,BorrowTime,ReturnTime) values("
						+ i
						+ ",'"
						+ bookname
						+ "','"
						+ type
						+ "',"
						+ userid
						+ ",'"
						+ username
						+ "','"
						+ btime
						+ "','"
						+ rtime
						+ "');";
				try {
					ResultSet rs = stmt.executeQuery(sql1);
					rs.next();
					String s = rs.getString(1);
					if (s.equals("��")) {
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "����ɹ���");
						stmt.executeUpdate(sql3);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "�����ѱ�����!");
						dispose();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	class MyModel extends AbstractTableModel {
		private int row;
		private int column;
		private ResultSet rs;
		private Statement stmt;

		public MyModel(String sql) {
			stmt = DBUtil.getStatement();
			try {
				rs = stmt.executeQuery(sql);
				rs.last();// ������Ƶ����һ��
				row = rs.getRow();// ��ȡ�к�(���������)
				ResultSetMetaData rsmd = rs.getMetaData();// ͨ���������������ȡ
				column = rsmd.getColumnCount();// ��ȡ����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public int getColumnCount() {
			return column;
		}

		@Override
		public int getRowCount() {
			return row;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			try {
				rs.absolute(rowIndex + 1);
				value = rs.getString(columnIndex + 1);// ��ȡ���������
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
	}
}
