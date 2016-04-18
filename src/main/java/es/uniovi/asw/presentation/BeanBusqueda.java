package es.uniovi.asw.presentation;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.infrastructure.ParamsManager;
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
		
		return "exito";
	}
	
	
	private String evaluarParametrosBusqueda()
	{
		/*
		
		if()
		{
			
		}
		
		if()
		{
			
		}
		
		if()
		{
			
		}
		*/
		return "";
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
		this.nombreProceso = nombreProceso;
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