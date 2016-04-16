package es.uniovi.asw.voterResult.webService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uniovi.asw.voterResult.request.OpcionesMostrarResultados;


@Controller
public class ShowResultController implements ShowResult
{
	@Override
	public String mostrarResultados(OpcionesMostrarResultados opcionesMostrar)
	{ 
		// Habra que tomar los datos de ese parámetro y las opciones y después
		// según el tipo de elección habrá que ejecutar una lógica u otra.
		
		// Para contemplar la aparición de nuevas elecciones
		// 
		// 1) clase (a parte de esta) ---> mira el tipo de gráfico ----> devuelve una clase capaz de cargarlo,
		//    probablemente devuelva un bean con ámbito de sesión.
		// 
		// 2) clase capaz de cargarlo ---> se le pasan las opciones ----> devuelve el nombre de la vista que
		//    hay que cargar. Además, habrá dejado accesibles los datos que necesita la vista para cargarse
		// 
		//    Si no pudo cargarla lanzará una excepción que será tratada en esta clase y cargará una vista
		//    de error
		
		
		return "";
	}
	
	
	@ExceptionHandler(RuntimeException.class) // Cambiar por la expcepción adecuada
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(RuntimeException excep, Model model)
	{
		model.addAttribute("error", excep.getMessage());
		
		return "error";
	}
}