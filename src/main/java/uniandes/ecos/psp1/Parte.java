package uniandes.ecos.psp1;

import java.util.ArrayList;

/**
*representa el proxy elegido para el conteo de los LOCs
*@author juvenal
*@version 1.0, 18/02/2017
*/
public class Parte 
{
	/**nombre de la parte*/
	String nombreParte;
	
	/**lineas de código incluidas en la Parte lógicas y no lógicas*/
	ArrayList<String> lineas;
	
	/**Total de lineas Lógicas de la Parte*/
	int totalLineasLogicas;
	
	/**Total de Items de la Parte*/
	int totalItems;

	/**
	* constructor de la clase
	*/
	public Parte()
	{
		this.setLineas(null);
		this.setTotalItems(0);
		this.setTotalItems(0);
		this.setNombreParte("");
	}
	
	/**
	* obtiene el nombre de la Parte
	*@return nombre de la Parte
	*/
	public String getNombreParte() 
	{
		return nombreParte;
	}
	
	/**
	*asigna el nombre a la Parte 
	*@param nombreParte nombre de la parte
	*/
	public void setNombreParte(String nombreParte) 
	{
		this.nombreParte = nombreParte;
	}
	
	/**
	* obtiene el listado de lieneas de la Parte
	*@return lista de lineas de la Parte
	*/
	public ArrayList<String> getLineas() 
	{
		return lineas;
	}
	
	/**
	*asigna el listado de lineas a la Parte
	*@param lineas lista de lineas
	*/
	public void setLineas(ArrayList<String> lineas) 
	{
		this.lineas = lineas;
	}

	/**
	* obtiene el total de líneas lógicas de la Parte
	*@return total de líneas lógicas de la Parte
	*/
	public int getTotalLineasLogicas()
	{
		return totalLineasLogicas;
	}

	/**
	*asigna el total de líneas lógicas de la Parte
	*@param totalLineasLogicas total de lienas de la Parte
	*/
	public void setTotalLineasLogicas(int totalLineasLogicas) 
	{
		this.totalLineasLogicas = totalLineasLogicas;
	}

	/**
	* obtiene el total de items de la Parte
	*@return  total de items de la Parte
	*/
	public int getTotalItems()
	{
		return totalItems;
	}

	/**
	*asigna el total de items de la Parte
	*@param totalItems total de items de la Parte
	*/
	public void setTotalItems(int totalItems) 
	{
		this.totalItems = totalItems;
	}	
	
	
}
