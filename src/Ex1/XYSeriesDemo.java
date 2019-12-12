package Ex1;






import java.awt.Color;

import org.jfree.chart.ChartFactory;
 import org.jfree.chart.ChartPanel;
 import org.jfree.chart.JFreeChart;
 import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.data.xy.XYSeries;
 import org.jfree.data.xy.XYSeriesCollection;
 import org.jfree.ui.ApplicationFrame;
 import org.jfree.ui.RefineryUtilities;


public class XYSeriesDemo extends ApplicationFrame {

/**
 * A demonstration application showing an XY series containing a null value.
 *
 * @param title  the frame title.
 */
public XYSeriesDemo(Functions_GUI fg,int width, int height, Range rx, Range ry, int resolution) {

    super("Standart draw");
    final XYSeriesCollection data = new XYSeriesCollection();
    
    
    for (int j = 0; j < fg.size(); j++) {
		
	
    	final XYSeries series = new XYSeries("f "+j);
    	double X_step = Math.abs(rx.get_max() - rx.get_min()) /resolution ;
    	function f = fg.get(j);
    	for (int i = 0; i < resolution; i++) {
    		double x = rx.get_min()+X_step*i;
    		double y = f.f(x);
    		y = Math.min(y, ry.get_max());
    		y = Math.max(y, ry.get_min());
    		series.add(x, y);
    	}
    	data.addSeries(series);

    }
    //XYSeries series2  = new XYSeries("Random 2 Data");
    //series2.add(25.6, -734.4);
    //series2.add(30.0, 453.2);
    
    
    
    //final XYSeriesCollection data = new XYSeriesCollection(series);
   // data.addSeries(series2);
    
    final JFreeChart chart = ChartFactory.createXYLineChart(
        "XY Series Demo",
        "X", 
        "Y", 
        data,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );
    //color
    XYPlot plot = chart.getXYPlot();
    
    AbstractRenderer r = (AbstractRenderer) plot.getRenderer(0);   
    
    r.setSeriesPaint(0, Color.GREEN);
    r.setSeriesPaint(1, Color.BLUE);


    
    final ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
    setContentPane(chartPanel);

}

// ****************************************************************************
// * JFREECHART DEVELOPER GUIDE                                               *
// * The JFreeChart Developer Guide, written by David Gilbert, is available   *
// * to purchase from Object Refinery Limited:                                *
// *                                                                          *
// * http://www.object-refinery.com/jfreechart/guide.html                     *
// *                                                                          *
// * Sales are used to provide funding for the JFreeChart project - please    * 
// * support us so that we can continue developing free software.             *
// ****************************************************************************

/**
 * Starting point for the demonstration application.
 *
 * @param args  ignored.
 */
public static void main(final String[] args) {

    //final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo");
    //demo.pack();
    //RefineryUtilities.centerFrameOnScreen(demo);
    //demo.setVisible(true);

}

}