package es.uniovi.asw.voterResult;

import org.springframework.web.bind.annotation.RequestBody;

import es.uniovi.asw.voterResult.request.OpcionesMostrarResultados;


/**
 * Muestra los resultados de una votación
 * 
 */
public interface ShowResult
{
	/**
	 * @param opcionesMostrar   indica sobre que elección mostrar los resultados y algunas opciones de configuración
	 * 
	 * @return nombre de la vista que hay que mostrar
	 * 
	 * @Exception ProcessingResultsExeption   ha ocurrido un error durante el procesamiento de la petición
	 * 
	 */
	public String mostrarResultados(@RequestBody OpcionesMostrarResultados opcionesMostrar);
}