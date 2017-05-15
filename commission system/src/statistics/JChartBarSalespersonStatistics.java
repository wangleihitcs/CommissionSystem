package statistics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class JChartBarSalespersonStatistics {
	private List<Integer> salelist;
	
	public static void main(String[] args) {
		new JChartBarSalespersonStatistics().execute();
	}

	public CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int i = 0; i < salelist.size(); i++) {
			List<SalespersonSoldStatistics> soldlist = new SalespersonSoldStatistics().getSoldStatistics(salelist.get(i));
			int k = soldlist.size() - 1;
			int sum = soldlist.get(k).getLocks() + soldlist.get(k).getStocks() + soldlist.get(k).getBarrels();
			dataset.setValue(sum, i + soldlist.get(k).getId()+"", "SP" + soldlist.get(k).getId());
		}
		
		return dataset;
	}

	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart("", "salespersons", "sold num", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		return chart;
	}

	public void execute() {
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		try {
			String path = System.getProperty("user.dir");
			OutputStream os = new FileOutputStream(path + "/salespersonbar.jpeg");// 图片是文件格式的，故要用到FileOutputStream用来输出。
			ChartUtilities.writeChartAsJPEG(os, chart, 480, 260);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getSalelist() {
		return salelist;
	}

	public void setSalelist(List<Integer> salelist) {
		this.salelist = salelist;
	}

	
}
