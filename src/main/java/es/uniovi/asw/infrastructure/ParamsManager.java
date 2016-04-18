package es.uniovi.asw.infrastructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParamsManager
{
	private ParamsManager() {}
	
	
	public static boolean areParamsNull(Object... params)
	{
		for(Object element : params)
		{
			if(element == null)
			{
				return true;
			}
		}
		
		
		return false;
	}
	
	
	public static Date parseStringToDate(String formato, String fechaParsear)
	{
		try
		{
			return new SimpleDateFormat(formato).parse(fechaParsear);
		}
		
		catch(ParseException excep)
		{
			return null;
		}
	}
	
	
	public static String parseDateToString(String formato, Date fechaParsear)
	{
		if(fechaParsear != null)
		{
			return new SimpleDateFormat(formato).format(fechaParsear);
		}
		
		return null;
	}
}