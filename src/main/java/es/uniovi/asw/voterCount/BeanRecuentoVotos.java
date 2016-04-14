package es.uniovi.asw.voterCount;

import java.util.HashSet;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteReferendum;

@Component("BeanRecuento")
@Scope("session")
public class BeanRecuentoVotos {

	private PieChartModel pieModel1 = new PieChartModel();
	private BarChartModel barModel = new BarChartModel();

	public PieChartModel getPieModel1() {
		return pieModel1;
	}
	
	public BarChartModel getBarModel() {
		return barModel;
	}

	public String recuentoVotos(Election e) {
		// Election e = Repository.electionR.findById(id);

		//if (e instanceof Referendum) {

			HashSet<Vote> votos = (HashSet<Vote>) e.getVotes();

			int yes = 0;
			int no = 0;

			for (Vote v : votos) {
				VoteReferendum vr = (VoteReferendum) v;
				yes += vr.getYeses();
				no += vr.getNoes();
			}

			pieModel1.set("Yes", yes);
			pieModel1.set("No", no);

			pieModel1.setTitle(e.getName());
			pieModel1.setLegendPosition("w");

			ChartSeries yesS = new ChartSeries();
			yesS.setLabel("Yes");
			yesS.set("Yes", yes);

			ChartSeries noS = new ChartSeries();
			noS.setLabel("No");
			noS.set("No", no);
			
			barModel.clear();
			barModel.addSeries(yesS);
			barModel.addSeries(noS);

			barModel.setTitle(e.getName());
			barModel.setLegendPosition("ne");
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("Options");

			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Num Votes");
			yAxis.setMin(0);
			yAxis.setMax(10);

			return "exito";
		//}
		//return null;
	}

}
