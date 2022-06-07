package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

/**
 * ����Ա�޸ĺ�ɾ���û�����
 * 
 * @author ������
 *
 */
public class User_Update_Delete extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();
	private JButton btn1 = new JButton("�޸�");
	private JButton btn2 = new JButton("ɾ��");
	private int row;
	private MyModel model;

	private JLabel jLabel1 = new JLabel("�û�ID��");
	private JLabel jLabel2 = new JLabel("�� �� ����");
	private JLabel jLabel3 = new JLabel("�û����룺");
	private JLabel jLabel4 = new JLabel("�û����䣺");
	private JLabel jLabel5 = new JLabel("�û���ַ��");
	private JLabel jLabel6 = new JLabel("�û��绰��");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JPasswordField jPasswordField3 = new JPasswordField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();

	private Panel panBtn = new Panel();
	private Panel panLab = new Panel();

	public User_Update_Delete() {
		this.setSize(600, 500);
		setTitle("�û���Ϣ�޸���ɾ��");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		model = new MyModel();
		table.setModel(model);
		// �޸�������
		table.getColumnModel().getColumn(0).setHeaderValue("�û�ID");
		table.getColumnModel().getColumn(1).setHeaderValue("�� �� ��");
		table.getColumnModel().getColumn(2).setHeaderValue("�û�����");
		table.getColumnModel().getColumn(3).setHeaderValue("�û�����");
		table.getColumnModel().getColumn(4).setHeaderValue("�û���ַ");
		table.getColumnModel().getColumn(4).setHeaderValue("�û��绰");

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
		jLabel1.setLocation(10, 10);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		jTextField1.setSize(150, 30);
		jTextField1.setLocation(110, 45);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(280, 10);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		jTextField2.setSize(150, 30);
		jTextField2.setLocation(380, 45);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(10, 70);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		jPasswordField3.setSize(150, 30);
		jPasswordField3.setLocation(110, 105);

		jLabel4.setSize(100, 100);
		jLabel4.setLocation(280, 70);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);
		jTextField4.setSize(150, 30);
		jTextField4.setLocation(380, 105);

		jLabel5.setSize(100, 100);
		jLabel5.setLocation(10, 130);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);
		jTextField5.setSize(150, 30);
		jTextField5.setLocation(110, 165);

		jLabel6.setSize(100, 100);
		jLabel6.setLocation(280, 130);
		jLabel6.setHorizontalAlignment(JLabel.RIGHT);
		jTextField6.setSize(150, 30);
		jTextField6.setLocation(380, 165);

		// �ѱ�ǩ���ı���ӵ�panLab�����
		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jLabel6);

		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(jPasswordField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);
		panLab.add(jTextField6);

		this.add(jsp, BorderLayout.NORTH);
		this.add(panLab, BorderLayout.CENTER);
		this.add(panBtn, BorderLayout.SOUTH);

		// ��ȡ�����ֵ
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id, name, pwd, email, addr, phone;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				pwd = table.getValueAt(selRow, 2).toString().trim();
				email = table.getValueAt(selRow, 3).toString().trim();
				addr = table.getValueAt(selRow, 4).toString().trim();
				phone = table.getValueAt(selRow, 5).toString().trim();
				jTextField1.setText(id);
				jTextField2.setText(name);
				jPasswordField3.setText(pwd);
				jTextField4.setText(email);
				jTextField5.setText(addr);
				jTextField6.setText(phone);
			}
		});

		// ʹ��ŵ��ı��򲻿ɱ༭
		jTextField1.setEditable(false);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				String name = jTextField2.getText().trim();
				String pwd = jPasswordField3.getText().trim();
				String email = jTextField4.getText().trim();
				String addr = jTextField5.getText().trim();
				String phone = jTextField6.getText().trim();

				Statement stmt = DBUtil.getStatement();
				String sql = "update user set username = '" + name
						+ "',userpsw = '" + pwd + "',useremail = '" + email
						+ "',useraddr = '" + addr + "',userphone = '" + phone
						+ "' where userid = " + i + ";";
				try {
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					// ʵʱ���±�
					model = new MyModel();
					table.setModel(model);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?",
						"��ʾ", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					Statement stmt = DBUtil.getStatement();
					String sql = "delete from user where userid = " + i + ";";
					try {
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "��ɾ����");
						// ʵʱ���±������
						model = new MyModel();
						table.setModel(model);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	class MyModel extends AbstractTableModel {
		private int row;
		private int column;
		private ResultSet rs;
		private Statement stmt;
		private String sql = "select * from user;";

		public MyModel() {
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
