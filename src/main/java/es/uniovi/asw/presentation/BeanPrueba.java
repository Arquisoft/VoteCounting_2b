package es.uniovi.asw.presentation;

import javax.faces.bean.ApplicationScoped;
import org.springframework.stereotype.Service;

@Service
@ApplicationScoped
public class BeanPrueba {

	public String getPrueba() {
		return "Hello guys!";
	}
	
	public String getMostrar(){
		System.out.println("Pase");
		return "exito";
	}
}
