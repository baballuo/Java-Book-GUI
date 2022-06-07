package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import utils.DBUtil;

/**
 * �����ǻ�ȡ������Ϣ���Ա���ͼ�鿴��
 * 
 * @author ������
 *   
 */
public class PieChart {
	ChartPanel frame1;

	public PieChart() {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("ͼ�������", data, true,
				false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
				"{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
	}

	private static DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql1 = "select count(BookType = '�ഺ��ѧ' OR null) as '�ഺ��ѧ',count(BookType = '��ѧ' OR null) as '��ѧ' ,count(BookType = '�Ƽ���' OR null) as '�Ƽ���',count(BookType = '��ѧ��' OR null) as '��ѧ��',count(BookType = '������Ȼ' OR null) as '������Ȼ',count(BookType = '�ۺ���' OR null) as '�ۺ���',count(BookType = '������' OR null) as '������' from borrow;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.setValue("�ഺ��ѧ", rs1.getInt(1));
				dataset.setValue("��ѧ", rs1.getInt(2));
				dataset.setValue("�Ƽ���", rs1.getInt(3));
				dataset.setValue("��ѧ��", rs1.getInt(4));
				dataset.setValue("������Ȼ", rs1.getInt(5));
				dataset.setValue("�ۺ���", rs1.getInt(6));
				dataset.setValue("������", rs1.getInt(7));
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
