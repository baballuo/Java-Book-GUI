package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.DBUtil;


public class BarChart {
	ChartPanel frame1;

	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("��ͬ������Ľ�����", // ͼ�����
				"�������", // Ŀ¼�����ʾ��ǩ
				"������", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);

		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

	}

	private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql1 = "select count(BookType = '�ഺ��ѧ' OR null) as '�ഺ��ѧ',count(BookType = '��ѧ' OR null) as '��ѧ' ,count(BookType = '�Ƽ���' OR null) as '�Ƽ���',count(BookType = '��ѧ��' OR null) as '��ѧ��',count(BookType = '������Ȼ' OR null) as '������Ȼ',count(BookType = '�ۺ���' OR null) as '�ۺ���',count(BookType = '������' OR null) as '������' from borrow;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.addValue(rs1.getInt(1), "�ഺ��ѧ", "�ഺ��ѧ");
				dataset.addValue(rs1.getInt(2), "��ѧ", "��ѧ");
				dataset.addValue(rs1.getInt(3), "�Ƽ���", "�Ƽ���");
				dataset.addValue(rs1.getInt(4), "��ѧ��", "��ѧ��");
				dataset.addValue(rs1.getInt(5), "������Ȼ", "������Ȼ");
				dataset.addValue(rs1.getInt(6), "�ۺ���", "�ۺ���");
				dataset.addValue(rs1.getInt(7), "������", "������");
				return dataset;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;
	}
}
