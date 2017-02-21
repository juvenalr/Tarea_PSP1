package uniandes.ecos.psp1;

import java.util.ArrayList;

/**
*clase que se encarga de la visualización de menús y resultados
*@author juvenal
*@version 1.0, 18/02/2017
*/
public class Vista 
{
	/**
	* Muestra el menú de ingreso de ruta de archivo en la pantalla
	*/
   public void mostrarMenuRutaArchivo()
   {
	 System.out.println("Benevenido al Porgrama1 PSP1 Conteo LOCS");
	 System.out.println("Por Favor Ingrese la Ruta del Folder del Proyecto Java o Digite S para salir");			
   }
   
   /**
	* Muestra el los resultados en pantalla
	*@param ListaResultados lista de resultados
	*/
   public void mostarTablaParteItemsSize (ArrayList<String> ListaResultados)
   {
	   System.out.println("  Nombre_Parte  " + "|" + "  Numero_Items  " + "|" + "  Tamaño_Parte  |" + "  Tamaño_Total  " );
	  
	   for (String resultado : ListaResultados)
	   {		  		   
		   System.out.println(resultado) ;
	   }
   }
   
   
	
}
