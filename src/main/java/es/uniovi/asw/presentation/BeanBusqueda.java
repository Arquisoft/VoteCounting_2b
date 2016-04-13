package es.uniovi.asw.presentation;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;


@Component("busqueda")
@Scope("request")
public class BeanBusqueda
{
	/**
	 * Búsqueda según tres parámetros:
	 * 
	 * - Nombre del proceso electoral
	 * - Fecha inicio de ese proceso
	 * - Fecha final de ese proceso
	 * 
	 * @return   exito si la búsqueda tiene éxito o fracaso en caso contrario
	 * 
	 */
	public String buscar()
	{
		
		List<Election> referendum = null;
		/*
		Calendar c = Calendar.getInstance();
		
		referendum.setName("Referendum");
		referendum.setStartDate(c.getTime());
		c.add(Calendar.DATE, 2);
		referendum.setExpiryDate(c.getTime());
		referendum.setQuestion("Question");
		*/
		referendum = (List<Election>) Repository.electionR.findAll();
		
		
		System.out.println("Se pasó por el bean");
		
		return "exito";
	}
}