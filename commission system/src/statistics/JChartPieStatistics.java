package statistics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class JChartPieStatistics {
	private String gunsmith;
	
	public PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		List<GunsmithSoldInTownStatistics> list = new GunsmithSoldInTownStatistics().getStatistics(gunsmith);
		int i1 = list.size() - 1;
		dataset.setValue("Locks", list.get(i1).getLocks());
		dataset.setValue("Stocks", list.get(i1).getStocks());
		dataset.setValue("Barrels", list.get(i1).getBarrels());
		
		return dataset;
	}
	
	public JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
		return chart;
	}
	
	public void execute() {
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		try {
			String path = System.getProperty("user.dir");
			OutputStream os = new FileOutputStream(path + "/pie.jpeg");// 图片是文件格式的，故要用到FileOutputStream用来输出。
			ChartUtilities.writeChartAsJPEG(os, chart, 480, 260);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getGunsmith() {
		return gunsmith;
	}

	public void setGunsmith(String gunsmith) {
		this.gunsmith = gunsmith;
	}
}
