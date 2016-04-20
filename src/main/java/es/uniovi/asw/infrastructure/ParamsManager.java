package es.uniovi.asw.infrastructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParamsManager
{
	private ParamsManager() {}
	
	
	public static boolean areStringsNotNullAndNotEmpty(String... params)
	{
		for(String element : params)
		{
			if(element == null || element == "")
			{
				return false;
			}
		}
		
		
		return true; // Ninguno de los Strings tiene el valor null o ""
	}
	
	
	public static boolean isDateValid(String formato, String fechaParsear)
	{
		if(fechaParsear == null || fechaParsear == "")
		{
			return false;
		}
		
		
		try
		{
			new SimpleDateFormat(formato).parse(fechaParsear);
		}
		
		catch(ParseException excep)
		{
			return false;
		}
		
		return true; // Fecha válida
	}
}