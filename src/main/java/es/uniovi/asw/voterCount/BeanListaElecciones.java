package es.uniovi.asw.voterCount;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.ClosedList;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.OpenList;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.persistence.Repository;

@Component("BeanListaElecciones")
@Scope("request")
public class BeanListaElecciones {

	public List<Election> elecciones;
	public List<Referendum> referendums;
	public List<OpenList> listasAbiertas;
	public List<ClosedList> listasCerradas;

	@PostConstruct
	public void init() {
		this.elecciones = (List<Election>) Repository.electionR.findAll();
		
		referendums = new LinkedList<>();
		for(Election e:elecciones){
			if(e instanceof Referendum){
				referendums.add((Referendum) e);
			}
		}
		System.out.println("BeanListaElecciones creado");
	}

	public List<Election> getElecciones() {
		return elecciones;
	}

	public void setElecciones(List<Election> elecciones) {
		this.elecciones = elecciones;
	}

	public List<Referendum> getReferendums() {
		return referendums;
	}

	public void setReferendums(List<Referendum> referendums) {
		this.referendums = referendums;
	}

	public List<OpenList> getListasAbiertas() {
		return listasAbiertas;
	}

	public void setListasAbiertas(List<OpenList> listasAbiertas) {
		this.listasAbiertas = listasAbiertas;
	}

	public List<ClosedList> getListasCerradas() {
		return listasCerradas;
	}

	public void setListasCerradas(List<ClosedList> listasCerradas) {
		this.listasCerradas = listasCerradas;
	}

	
}
