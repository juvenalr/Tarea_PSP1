package uniandes.ecos.psp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
*clase que contiene el método Main. Se encarga de gestionar las entradas del usuario, ejecuta el conteo a traves de la clase Lector y envia los resultados a la clase Vista
*@author juvenal
*@version 1.0, 18/02/2017
*/
public class Controlador
{
	/**Objeto de tipo Lector que se encarga de los procesos de conteo*/
	public Lector pLector;
	
	/**Objeto de tipo Vista que se encarga de mostar menus y resultados en pantalla*/
	public Vista pVista;
	
	/**
	* constructor de la clase
	*/
	public Controlador()
	{
		pLector= new Lector();
		pVista = new Vista();
	}
	
	/**
	* Lee desde la consola de comandos las entradas del usuario
	*/
	public String leerLineaConsola()
	{ String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			 line = reader.readLine();
		} 
		catch (IOException e)
		{
		}
		return line;
	}
	
	/**
	* Carga los archivos .java y ejecuta los conteos
	*/
	public void cargarArchivo()
	{
		pVista.mostrarMenuRutaArchivo();;
		String val= leerLineaConsola();
		int totalTamano=0;
		if (val != null )
		{
			if(val.toLowerCase().equals("s"))
			{	
		        System.exit(0) ;
			}
			
			ArrayList<String> listaArchivosJava= pLector.listarArchivosJava(val);
			pLector.contarLineasLogicas(listaArchivosJava);			
			ArrayList<String> resultados= new ArrayList<String>();
			
			for(Parte parte: pLector.getPartes())
			{
				String lineaSalida= textoCentrado(parte.getNombreParte(),16) +" " + textoCentrado( Integer.toString(parte.getTotalItems()),16)+ " "+textoCentrado( Integer.toString(parte.getTotalLineasLogicas()),16);
				totalTamano += parte.getTotalLineasLogicas();
				resultados.add(lineaSalida);
			}
			 
			String lineaTotal= "                                                           " + Integer.toString( totalTamano);
			resultados.add(lineaTotal);			
			pVista.mostarTablaParteItemsSize(resultados);
		}
		else
		{
				cargarArchivo();
		}
					
		
	}
	
	/**
 	* centra el texto dado un tamaño de columa
 	* @param texto texto a centrar
 	* @param sizeColumna tamaño de la columna
 	* @return texto centrado
 	*/
 	public  String textoCentrado(String texto, int sizeColumna)
	   {
		   try
		   {
		
		     int longitudtexto = texto.length();
		     int diferencia = sizeColumna  -longitudtexto;
		     if (diferencia <=0)
		     {
			   return texto;
			   
		     }
		     else
		     {
			   int promedio= diferencia/2;
			   String espacios="";
			   for(int i= 1; i<=promedio;i++)
			   {
				   espacios=espacios+ " ";
			   }
			   
			   if(promedio*2< diferencia)
			   {
				   return espacios + texto +espacios +" ";
			   }
			   else
			   {
				   return espacios + texto +espacios;
			   }			
		     }
		   }
		   catch(Exception e)
		   {
			   return texto;
		   }
	   }

 	/**
 	 * función principal main
 	*/
 	static public void main(String args[])
 	{
 	 Controlador aplicacion = new Controlador();
 	 aplicacion.cargarArchivo(); 	 
 	}
}
