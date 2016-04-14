package es.uniovi.asw.voterCount;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;

@Component("BeanListaElecciones")
@Scope("request")
public class BeanListaElecciones {
	
	public  List<Election> elecciones;

	@PostConstruct
	public void init(){
		this.elecciones= (List<Election>) Repository.electionR.findAll();
		System.out.println("BeanListaElecciones creado");
	}
	

	public List<Election> getElecciones() {
		return elecciones;
	}

	public void setElecciones(List<Election> elecciones) {
		this.elecciones = elecciones;
	}
	
	
}
