package es.uniovi.asw.voterCount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.leader.Candidate;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Candidature;
import es.uniovi.asw.dbManagement.model.ClosedList;
import es.uniovi.asw.dbManagement.model.Constituency;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.OpenList;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.model.VoteReferendum;
import es.uniovi.asw.dbManagement.persistence.Repository;

@Component("BeanRecuento")
@Scope("session")
public class BeanRecuentoVotos {
	
	private String nombreEleccion;
	public Map<String,Integer> votosReferendum = new HashMap<>();
	
	public Map<String, Integer> getVotosReferendum() {
		return votosReferendum;
	}

	public void recuentoVotos(Election e) {;

		if (e instanceof Referendum) {
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
			setNombreEleccion(e.getName());
			HashSet<Vote> votos = (HashSet<Vote>) e.getVotes();
			
			
			List<Candidate> candidatos =  Repository.candidatureR.findByElectionsId(e.getId());
			
			HashMap<String, Integer> votosCerradas = new HashMap<>();
			
		
			//por cada circunscripcion y colegio
			for(Candidate c:candidatos){
				HashMap<Integer, Integer> votosC = new HashMap<>();
				//Buscar votos del candidatos y almacenarlos dividiendolos por el nºescaños
			}
			
			//Repartir los escaños
		}
	}

	public String getNombreEleccion() {
		return nombreEleccion;
	}

	public void setNombreEleccion(String nombreEleccion) {
		this.nombreEleccion = nombreEleccion;
	}

}
