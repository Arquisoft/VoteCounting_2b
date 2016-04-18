package es.uniovi.asw.presentation;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.infrastructure.ParamsManager;
import javassist.tools.framedump;
import scala.annotation.meta.param;


@Component("busqueda")
@Scope("request")
public class BeanBusqueda
{
	// formato usado en las fechas
	private final String formatoFechas = "dd/MM/yyyy";
	
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
		
		switch (criteriosBusqueda)
		{
			case "nom":
				
				break;
				
			case "nom ini ":
				
				break;
				
			case "nom ini fin":
				
				break;
				
			case "ini fin":
				
				break;
				
			case "nom fin":
				
				break;
				
			default: // fin
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
			// Poner un mensaje que indique si no hay ningun parámetro de búsqueda válido
			//
			//
			// Con está implementación no se comprueb en el servidor que el usuario se
			// equivocó al escribir un parámetro. Si tiene un valor inválido o no se indicó
			// tendrá el valor null
			//
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
}