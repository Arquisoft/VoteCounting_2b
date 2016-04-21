package es.uniovi.asw.voterResult;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.infrastructure.ParamsManager;


@Component("busqueda")
@Scope("request")
public class BeanBusqueda
{
	/*
	 * Variable que indica si se debe mostrar al usuario una tabla con
	 * los resultados de la búsqueda de elecciones que cumplan unos
	 * determinados criterios.
	 * 
	 */
	private boolean mostrarTablaResultados = false;
	
	
	
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
		
		
		mostrarTablaResultados = true;   // Si se han pasado unos criterios válidos entonces mostrar el resultado
		
		return "exito";
	}
	
	
	private String evaluarParametrosBusqueda()
	{
		String criterios = "";
		
		if( ParamsManager.areStringsNotNullAndNotEmpty(nombreProceso) )
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
	
	
	public void validarFecha(String fecha)
	{
		if( !ParamsManager.isDateValid(formatoFechas, fechaFinProceso) )
		{
			throw new ValidationException("");
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
	
	
	public boolean getMostrarTablaResultados()
	{
		return mostrarTablaResultados;
	}
	
	public void setMostrarTablaResultados(boolean mostrarTablaResultados)
	{
		this.mostrarTablaResultados = mostrarTablaResultados;
	}
}