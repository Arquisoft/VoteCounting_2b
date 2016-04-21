package es.uniovi.asw.voteSearch.infrastructure;

import java.util.HashMap;
import java.util.Map;


public class Messages
{
	private Map<String, String> mensajesError = new HashMap<>();
	
	
	/**
	 * Almacena un mensaje en un registro para que pueda ser recuperado más adelante
	 * 
	 * @param source   identificador del componente al que se refiere el mensaje
	 * @param message   mensaje a guardar
	 * 
	 */
	public void addMessage(String source, String message)
	{
		mensajesError.put(source, message);
	}
	
	/**
	 * Devuelve un mensaje sobre un componente, tras lo cual lo elimina del registro
	 * 
	 * @param source   identificador del componenter con el que se relaciona el mensaje
	 * @return   mensaje recuperado del registro, o null si no hay ninguno
	 * 
	 */
	public String getMessage(String source)
	{
		String mensaje = mensajesError.get(source);
		mensajesError.remove(source);
		
		return mensaje;
	}
}