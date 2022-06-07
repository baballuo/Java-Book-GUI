package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

/**
 * ����Ա�����鼮����
 * 
 * @author mac
 *
 */
public class BookSearch extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();

	private int row;
	private MyModel model;
	private JButton jButton1 = new JButton("����");
	private JLabel label1 = new JLabel("���ҷ�ʽ��");
	private JTextField jTextField = new JTextField();

	private JLabel labTitle = new JLabel("��  ��  ͼ  ��");
	private Font font = new Font("����", Font.BOLD, 34);

	public BookSearch() {
		setTitle("ͼ�����");
		setSize(500, 400);
		setResizable(false); // ���ɸı䴰�ڴ�С
		// ��ȡ��Ļ��С�͵�ǰframe�Ĵ�С
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// ʹ��������λ����Ļ��������
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// ���õ������ڵġ��رա���ťʱ��������Ӧ�Ķ���
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setLocationRelativeTo(null);

		labTitle.setFont(font);
		labTitle.setHorizontalAlignment(JLabel.CENTER);
		labTitle.setForeground(Color.RED);

		// ���ÿɼ���ͼ�Ľӿ�
		jsp.setViewportView(table);
		// ������ ��500���߶�250
		jsp.setPreferredSize(new Dimension(500, 250));
		// ���ú���ʹ�ֱ�������ɼ�
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel jp = new JPanel();
		jp.setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("����������");
		comboBox.addItem("�����߲���");

		label1.setSize(80, 80);
		label1.setLocation(30, 5);

		comboBox.setSize(100, 30);
		comboBox.setLocation(100, 30);

		jTextField.setSize(130, 32);
		jTextField.setLocation(220, 30);

		jButton1.setSize(80, 30);
		jButton1.setLocation(370, 30);

		jp.add(label1);
		jp.add(comboBox);
		jp.add(jTextField);
		jp.add(jButton1);

		this.setLayout(new BorderLayout());
		this.add(labTitle, BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		this.add(jsp, BorderLayout.SOUTH);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡcomboBox����ѡ��������
				String com = (String) comboBox.getSelectedItem();
				// System.out.println(com);
				if (com.equals("����������")) {
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
					table.getColumnModel().getColumn(5).setHeaderValue("�Ƿ񱻽�");
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
					table.getColumnModel().getColumn(5).setHeaderValue("�Ƿ񱻽�");
				}
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
