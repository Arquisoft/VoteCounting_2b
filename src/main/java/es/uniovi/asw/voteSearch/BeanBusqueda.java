package es.uniovi.asw.voteSearch;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voteSearch.infrastructure.ParamsManager;


@Component("busqueda")
@Scope("request")
public class BeanBusqueda
{
	// Lista de elecciones que encajan con los criterios de búsqueda
	private List<Election> eleccionesEncontradas;
	
	// formato usado en las fechas
	private final String formatoFechas = "dd/MM/yyyy";
	
	/*
	 * Criterios de búsqueda
	 * 
	 * En esta versión se supone que todos los posibles valores que
	 * el usuario ponga en el campo nombre del proceso son válidos
	 * (a excepción de "")
	 * 
	 */
	private String nombreProceso;
	private String fechaInicioProceso;
	private String fechaFinProceso;
	
	
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
			// Si la busqueda va mal no se carga la tabla de resultados
			// Hay que cambiar el atributo renderer a false 
			//
			return null;
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
		
		
		// Si la busqueda va bien se carga la tabla de resultados
		// Hay que cambiar el atributo renderer a false 
		//
		return null;
	}
	
	
	private String evaluarParametrosBusqueda()
	{
		String criterios = "";
		
		if( ParamsManager.areStrings_NotNull_And_NotEmpty(nombreProceso) )
		{
			criterios += "nom ";
		}
		
		if(!ParamsManager.isDateValid(formatoFechas, fechaInicioProceso))
		{
			criterios += "ini ";
		}
		
		if(!ParamsManager.isDateValid(formatoFechas, fechaFinProceso))
		{
			criterios += "fin";
		}
		
		if(criterios.equals(""))
		{
			return "fracaso";
		}
		
		return criterios;
	}
	
	
	public void validarFecha(String id, String fecha)
	{
		if(ParamsManager.areStrings_NotNull_And_NotEmpty(fecha) && !ParamsManager.isDateValid(formatoFechas, fecha) )
		{
			FacesContext.getCurrentInstance().addMessage(id, new FacesMessage("La fecha indicada no es válida"));
			
			throw new ValidationException();
		}
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
		return fechaInicioProceso;
	}
	
	public void setFechaInicioProceso(String fechaInicioProceso)
	{
		this.fechaInicioProceso = fechaInicioProceso;
	}
	
	
	public String getFechaFinProceso()
	{
		return fechaInicioProceso;
	}
	
	public void setFechaFinProceso(String fechaFinProceso)
	{
		this.fechaFinProceso = fechaFinProceso;
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