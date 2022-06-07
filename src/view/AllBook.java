package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

 /*
     *@Ballas_code
     *create_time:2022/5/22 9:29
     @param null
     *@return
 */
public class AllBook extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();

	private int row;
	private MyModel model;

	public AllBook() {
		setTitle("��ʾ����ͼ��");
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

		model = new MyModel();
		table.setModel(model);

		table.getColumnModel().getColumn(0).setHeaderValue("���");
		table.getColumnModel().getColumn(1).setHeaderValue("����");
		table.getColumnModel().getColumn(2).setHeaderValue("����");
		table.getColumnModel().getColumn(3).setHeaderValue("����");
		table.getColumnModel().getColumn(4).setHeaderValue("�۸�");
		table.getColumnModel().getColumn(5).setHeaderValue("�Ƿ񱻽�");

		// ���ÿɼ���ͼ�Ľӿ�
		jsp.setViewportView(table);
		// ������ ��500���߶�400
		jsp.setPreferredSize(new Dimension(500, 400));
		// ���ú���ʹ�ֱ�������ɼ�
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setLayout(new BorderLayout());
		this.add(jsp);
	}

	class MyModel extends AbstractTableModel {
		private int row;
		private int column;
		private ResultSet rs;
		private Statement stmt;
		private String sql = "select * from book;";

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
