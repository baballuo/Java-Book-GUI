package Date;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PieTest extends JFrame {
	public PieTest() {
		setTitle("����ͼ����ı���");
		setBounds(50, 50, 800, 600);
		setResizable(false); // ���ɸı䴰�ڴ�С
		// ��ȡ��Ļ��С�͵�ǰframe�Ĵ�С
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// ʹ��������λ����Ļ��������
		this.setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		this.setLocationRelativeTo(null);
		// ���õ������ڵġ��رա���ťʱ��������Ӧ�Ķ���
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		// ��ӱ���ͼ
		this.add(new PieChart().getChartPanel());
	}
}
