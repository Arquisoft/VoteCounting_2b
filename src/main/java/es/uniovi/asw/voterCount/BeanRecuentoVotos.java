package es.uniovi.asw.voterCount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.leader.Candidate;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.net.SyslogOutputStream;
import es.uniovi.asw.dbManagement.model.Candidature;
import es.uniovi.asw.dbManagement.model.ClosedList;
import es.uniovi.asw.dbManagement.model.Constituency;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.OpenList;
import es.uniovi.asw.dbManagement.model.PollingPlace;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteClosedList;
import es.uniovi.asw.dbManagement.model.VoteReferendum;
import es.uniovi.asw.dbManagement.persistence.Repository;

@Component("BeanRecuento")
@Scope("session")
public class BeanRecuentoVotos {
	
	public static int escaños =8;
	
	private String tipoEleccion;
	private String nombreEleccion;
	public Map<String,Integer> votosReferendum = new HashMap<>();
	public Map<String,Integer> votosListaCerrada;
	
	public Map<String, Integer> getVotosReferendum() {
		return votosReferendum;
	}

	public void recuentoVotos(Election e) {;

		if (e instanceof Referendum) {
			setTipoEleccion("Referendum");
			setNombreEleccion(e.getName());
			HashSet<Vote> votos = (HashSet<Vote>) e.getVotes();
			
			int yes = 0;
			int no = 0;

			for (Vote v : votos) {
				VoteReferendum vr = (VoteReferendum) v;
				yes += vr.getYeses();
				no += vr.getNoes();
			}
			
			votosReferendum.put("yes", yes);
			votosReferendum.put("no", no);
		}
		
		if (e instanceof ClosedList) {
			setTipoEleccion("ListaCerrada");
			setNombreEleccion(e.getName());
			
			HashMap<String, List<Double>> votosCerradas = new HashMap<>();
			HashMap<String, Integer> escañosPartidos = new HashMap<>();
			
			List<PollingPlace> colegios = (List<PollingPlace>) Repository.pollingPlaceR.findAll();
			
			for(PollingPlace pp : colegios){
				List<VoteClosedList> votosColegio = Repository.voteR.findByElectionAndPollingPlace(e, pp);
				for(VoteClosedList votoPartido:votosColegio){	
					
					double votosPartido = votoPartido.getNumVotes();
					List<Double> lista = new LinkedList<>();
					for(int i=1;i<=escaños;i++){
						lista.add(votosPartido/i);
					}
	
					votosCerradas.put(votoPartido.getCandidature().getName(), lista);
					escañosPartidos.put(votoPartido.getCandidature().getName(), 0);
				}
			}
			
			//Repartir los escaños
			for(int i=1;i<=escaños;i++){
				String partidoMax="";
				double max=0;
				for(String key: votosCerradas.keySet()){
					List<Double> listaHont = votosCerradas.get(key);
					double maxHont = listaHont.get(0);
					if(maxHont>max){
						max=maxHont;
						partidoMax=key;
					}
				}
				List<Double> listaMax = votosCerradas.get(partidoMax);
				listaMax.remove(max);
				votosCerradas.replace(partidoMax, listaMax);
				
				escañosPartidos.replace(partidoMax, escañosPartidos.get(partidoMax)+1);
			}
			System.out.println("Repartos de escaños realizados");
			votosListaCerrada=escañosPartidos;
		}
	}

	public String getNombreEleccion() {
		return nombreEleccion;
	}

	public void setNombreEleccion(String nombreEleccion) {
		this.nombreEleccion = nombreEleccion;
	}

	public String getTipoEleccion() {
		return tipoEleccion;
	}

	public void setTipoEleccion(String tipoEleccion) {
		this.tipoEleccion = tipoEleccion;
	}

}
