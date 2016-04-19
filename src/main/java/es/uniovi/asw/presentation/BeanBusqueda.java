package es.uniovi.asw.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.infrastructure.ParamsManager;


@Component("busqueda")
@Scope("request")
public class BeanBusqueda
{
	private List<Election> eleccionesEncontradas;
	
	// formato usado en las fechas
	private final String formatoFechas = "dd/MM/yyyy";
	
	/*
	 * Criterios de búsqueda
	 * 
	 * En esta versión se supone que el escribió correctamente los datos de
	 * búsqueda. Si hay alguno incorrecto, se supone que ese campo estaba
	 * en blanco
	 * 
	 * Habrá que corregirlo para que muertre un error al usuario
	 * 
	 */
	private String nombreProceso;
	private Date fechaInicioProceso;
	private Date fechaFinProceso;
	
	
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
		String criteriosBusqueda = evaluarParametrosBusqueda();
		
		if (criteriosBusqueda.equals("fracaso"))
		{
			return "fracaso";
		}
		
		/*
		 * nom = nombreProceso,  ini = fechaInicio,  fin = fechaFin
		 */
		switch (criteriosBusqueda)
		{
			case "nom ":
				//eleccionesEncontradas = Repository.electionR.findByNameContaining(nombreProceso);
				eleccionesEncontradas = new ArrayList<Election>();
				eleccionesEncontradas.add( Repository.electionR.findByName("Independencia Cataluña") );
				break;
				
			case "nom ini ":
				eleccionesEncontradas = Repository.electionR.findByNameContainingAndStartDate(nombreProceso, fechaInicioProceso);
				break;
				
			case "nom ini fin":
				eleccionesEncontradas = Repository.electionR.findByNameContainingAndStartDateAndExpiryDate(nombreProceso, fechaInicioProceso, fechaFinProceso);
				break;
				
			case "ini ":
				eleccionesEncontradas = Repository.electionR.findByStartDate(fechaInicioProceso);
				break;
				
			case "ini fin":
				eleccionesEncontradas = Repository.electionR.findByStartDateAndExpiryDate(fechaInicioProceso, fechaFinProceso);
				break;
				
			case "nom fin":
				eleccionesEncontradas = Repository.electionR.findByNameContainingAndStartDateAndExpiryDate(nombreProceso, fechaInicioProceso, fechaFinProceso);
				break;
				
			default: // fin
				eleccionesEncontradas = Repository.electionR.findByExpiryDate(fechaFinProceso);
				break;
		}
		
		return "exito";
	}
	
	
	private String evaluarParametrosBusqueda()
	{
		String criterios = "";
		
		if(!ParamsManager.areParamsNull(nombreProceso))
		{
			criterios += "nom ";
		}
		
		if(!ParamsManager.areParamsNull(fechaInicioProceso))
		{
			criterios += "ini ";
		}
		
		if(!ParamsManager.areParamsNull(fechaFinProceso))
		{
			criterios += "fin";
		}
		
		if(criterios.equals(""))
		{
			return "fracaso";
		}
		
		return criterios;
	}
	
	
	
	// ===================
	//  Getters y Setters 
	// ===================
	
	public String getNombreProceso()
	{
		return nombreProceso;
	}
	
	public void setNombreProceso(String nombreProceso)
	{
		if (nombreProceso == "" || nombreProceso == null)
		{
			this.nombreProceso = null;
		}
		
		else
		{
			this.nombreProceso = nombreProceso;
		}
	}
	
	
	public String getFechaInicioProceso()
	{
		return ParamsManager.parseDateToString(formatoFechas, fechaInicioProceso);
	}
	
	public void setFechaInicioProceso(String fechaInicioProceso)
	{
		this.fechaInicioProceso = ParamsManager.parseStringToDate(formatoFechas, fechaInicioProceso);
	}
	
	
	public String getFechaFinProceso()
	{
		return ParamsManager.parseDateToString(formatoFechas, fechaInicioProceso);
	}
	
	public void setFechaFinProceso(String fechaFinProceso)
	{
		this.fechaFinProceso = ParamsManager.parseStringToDate(formatoFechas, fechaFinProceso);
	}
	
	
	public List<Election> getEleccionesEncontradas()
	{
		return eleccionesEncontradas;
	}
	
	public void setEleccionesEncontradas(List<Election> eleccionesEncontradas)
	{
		this.eleccionesEncontradas = eleccionesEncontradas;
	}
}