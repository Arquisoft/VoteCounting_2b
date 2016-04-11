package es.uniovi.asw.presentation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("BeanPrueba")
@Scope("request")
public class BeanPrueba
{
	public String prueba()
	{
		return "Resultados";
	}
	
	
	public String buscar(String nombre, String fechaInicio, String fechaFin)
	{
		
		
		return "exito";
	}
}