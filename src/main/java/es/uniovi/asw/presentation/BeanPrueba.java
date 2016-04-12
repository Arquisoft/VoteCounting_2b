package es.uniovi.asw.presentation;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.persistence.Repository;


@Component("BeanPrueba")
@Scope("request")
public class BeanPrueba
{
	public String prueba()
	{
		return "Resultados";
	}
	
	
	public String buscar()//String nombre, String fechaInicio, String fechaFin)
	{
		List<Election> referendum;
		/*
		Calendar c = Calendar.getInstance();
		 	
		 referendum.setName("Referendum");
		 referendum.setStartDate(c.getTime());
		 c.add(Calendar.DATE, 2);
		 referendum.setExpiryDate(c.getTime());
		 referendum.setQuestion("Question");
		 	*/
		 referendum=(List<Election>) Repository.electionR.findAll();
		System.out.println();
		return "exito";
	}
}
