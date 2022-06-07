package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

/*
    *@Ballas_code
    *create_time:2022/5/28 22:00
    @param null
    *@return
*/
public class UserReturn extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();
	private JButton btn1 = new JButton("����");
	private JButton btn2 = new JButton("�˳�");
	private int row;
	private MyModel model;

	private JLabel jLabel1 = new JLabel("���鶩���ţ�");
	private JLabel jLabel2 = new JLabel("��   �ţ�");
	private JLabel jLabel3 = new JLabel("��   ����");
	private JLabel jLabel4 = new JLabel("��   �ࣺ");
	private JLabel jLabel5 = new JLabel("������ID��");
	private JLabel jLabel6 = new JLabel("����������");
	private JLabel jLabel7 = new JLabel("����ʱ�䣺");
	private JLabel jLabel8 = new JLabel("����ʱ�䣺");
	private JLabel jLabel9 = new JLabel("��       �ڣ�");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JTextField jTextField7 = new JTextField();
	private JTextField jTextField8 = new JTextField();
	private JTextField jTextField9 = new JTextField();

	private Panel panBtn = new Panel();
	private Panel panLab = new Panel();

	private Statement st;
	ResultSet rs;

	public UserReturn() {
		setTitle("���鴰��");
		setSize(600, 600);
		setResizable(false); // ���ɸı䴰�ڴ�С
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		st = DBUtil.getStatement();

		model = new MyModel();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setHeaderValue("���鶩����");
		table.getColumnModel().getColumn(1).setHeaderValue("���");
		table.getColumnModel().getColumn(2).setHeaderValue("����");
		table.getColumnModel().getColumn(3).setHeaderValue("����");
		table.getColumnModel().getColumn(4).setHeaderValue("������ID");
		table.getColumnModel().getColumn(5).setHeaderValue("������");
		table.getColumnModel().getColumn(6).setHeaderValue("����ʱ��");
		table.getColumnModel().getColumn(7).setHeaderValue("����ʱ��");
		table.getColumnModel().getColumn(8).setHeaderValue("�Ƿ��ѻ�");

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
		// ����ǩ���ı�������λ��
		jLabel1.setSize(100, 100);
		jLabel1.setLocation(10, 20);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		jTextField1.setSize(150, 30);
		jTextField1.setLocation(110, 55);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(280, 20);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		jTextField2.setSize(150, 30);
		jTextField2.setLocation(380, 55);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(10, 80);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		jTextField3.setSize(150, 30);
		jTextField3.setLocation(110, 115);

		jLabel4.setSize(100, 100);
		jLabel4.setLocation(280, 80);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);
		jTextField4.setSize(150, 30);
		jTextField4.setLocation(380, 115);

		jLabel5.setSize(100, 100);
		jLabel5.setLocation(10, 140);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);
		jTextField5.setSize(150, 30);
		jTextField5.setLocation(110, 175);

		jLabel6.setSize(100, 100);
		jLabel6.setLocation(280, 140);
		jLabel6.setHorizontalAlignment(JLabel.RIGHT);
		jTextField6.setSize(150, 30);
		jTextField6.setLocation(380, 175);

		jLabel7.setSize(100, 100);
		jLabel7.setLocation(10, 200);
		jLabel7.setHorizontalAlignment(JLabel.RIGHT);
		jTextField7.setSize(150, 30);
		jTextField7.setLocation(110, 235);

		jLabel8.setSize(100, 100);
		jLabel8.setLocation(280, 200);
		jLabel8.setHorizontalAlignment(JLabel.RIGHT);
		jTextField8.setSize(150, 30);
		jTextField8.setLocation(380, 235);

		jLabel9.setSize(100, 100);
		jLabel9.setLocation(10, 250);
		jLabel9.setHorizontalAlignment(JLabel.RIGHT);
		jTextField9.setSize(150, 30);
		jTextField9.setLocation(110, 285);

		// �Զ���ȡ����ʱ��(��ǰʱ��)
		Calendar c2 = Calendar.getInstance();//c2�ǻ��Calendar��һ�����࣬���е�ʱ����ǻ��c2ʱ��ʱ��
		// ��ȡ������
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int date2 = c2.get(Calendar.DATE);
		String time2 = year2 + "-" + month2 + "-" + date2;
		jTextField8.setText(time2);

		String sql = "select userid from user where username = '"
				+ LibraryUserFrame.USER_NAME + "';";

		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jLabel6);
		panLab.add(jLabel7);
		panLab.add(jLabel8);
		panLab.add(jLabel9);
		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(jTextField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);
		panLab.add(jTextField6);
		panLab.add(jTextField7);
		panLab.add(jTextField8);
		panLab.add(jTextField9);

		this.add(jsp, BorderLayout.NORTH);
		this.add(panLab, BorderLayout.CENTER);
		this.add(panBtn, BorderLayout.SOUTH);

		// ��ȡ�����ֵ
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id, bookid, bookname, booktype, userid, username, btime, rtime;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				bookid = table.getValueAt(selRow, 1).toString().trim();
				bookname = table.getValueAt(selRow, 2).toString().trim();
				booktype = table.getValueAt(selRow, 3).toString().trim();
				userid = table.getValueAt(selRow, 4).toString().trim();
				username = table.getValueAt(selRow, 5).toString().trim();
				btime = table.getValueAt(selRow, 6).toString().trim();
				rtime = table.getValueAt(selRow, 7).toString().trim();
				jTextField1.setText(id);
				jTextField2.setText(bookid);
				jTextField3.setText(bookname);
				jTextField4.setText(booktype);
				jTextField5.setText(userid);
				jTextField6.setText(username);
				jTextField7.setText(btime);

				// ʱ���
				SimpleDateFormat myFormatter = new SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar c2 = Calendar.getInstance();
				// ��ȡ������
				int year2 = c2.get(Calendar.YEAR);
				int month2 = c2.get(Calendar.MONTH) + 1;
				int date2 = c2.get(Calendar.DATE);
				String nowtime = year2 + "-" + month2 + "-" + date2;
				try {
					Date date1 = myFormatter.parse(nowtime);
					Date mydate = myFormatter.parse(rtime);
					long day = (date1.getTime() - mydate.getTime())
							/ (24 * 60 * 60 * 1000);
					if (day >= 0) {
						jTextField9.setText("����" + day + "��");
					} else {
						jTextField9.setText("δ����");
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
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
		jTextField8.setEditable(false);
		jTextField9.setEditable(false);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				String id2 = jTextField2.getText().trim();
				int i2 = Integer.parseInt(id2);
				String bookname = jTextField3.getText().trim();
				String booktype = jTextField4.getText().trim();
				String userid = jTextField5.getText().trim();
				String username = jTextField6.getText().trim();
				String btime = jTextField7.getText().trim();
				String rtime = jTextField8.getText().trim();

				String sq = "select IsReturn from borrow where borrowid = " + i
						+ ";";
				try {
					rs = st.executeQuery(sq);
					rs.next();
					String B = rs.getString(1);
					if (B.equals("��")) {
						String sql = "update book set IsBorrow = '��' where BookId = "
								+ i2 + ";";
						String sql2 = "update borrow set ReturnTime = '"
								+ rtime
								+ "' , IsReturn = '��' where BorrowId = " + i
								+ ";";
						try {
							st.executeUpdate(sql);
							st.executeUpdate(sql2);
							JOptionPane.showMessageDialog(null, "����ɹ���");
							dispose();
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "�����ѻ���");
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		// private Statement stmt;
		// ��ȡ��ǰ�û���
		String s = LibraryUserFrame.USER_NAME;
		// ���ҵ�ǰ�û��Ľ����¼
		String sql2 = "select * from borrow where username = '" + s + "';";

		public MyModel() {
			try {
				rs = st.executeQuery(sql2);
				rs.last();// ������Ƶ����һ��
				row = rs.getRow();// ��ȡ�к�(���������)
				ResultSetMetaData rsmd = rs.getMetaData();// ͨ���������������ȡ
				column = rsmd.getColumnCount();// ��ȡ����
				rs.beforeFirst();
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
