package uniandes.ecos.psp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

/**
*clase que contiene las varibles y metodos que permiten calcular las lineas logicas de los archivos
*@author juvenal
*@version 1.0, 18/02/2017
*/
public class Lector 
{
	/**lista de Partes*/
	ArrayList<Parte> partes;
	
	/**
	* constructor de la clase
	*/
	public Lector()
	{
		partes = new ArrayList<Parte>();
	}
	
	/**
	* devuelve la lista de archivos java de un folder
	* @param ruta del folder
	* @return lista de archivos
	*/
	public ArrayList<String> listarArchivosJava(String rutaFolder)
	{   
		ArrayList<String> listaArchivos= new ArrayList<String>();
		Stack<File> stack = new Stack<File>();
		  
		try
		{  
		    Path pathFolder = Paths.get(rutaFolder);
		    
		    if (Files.exists(pathFolder))
		    {
		    	File file = new File(rutaFolder);
		    	stack.push(file);
				while(!stack.isEmpty())
				{
				  File child = stack.pop();
				  if (child.isDirectory()) 
				  {
				    for(File f : child.listFiles()) 
				    {
				    	stack.push(f);
				    }
				  }
				  else if (child.isFile())
				  {
				    if (child.getPath().contains(".java"))
				     {
				    	listaArchivos.add(child.getPath());
				     }
				  }
				 }
				return listaArchivos;
		    }
		    else
		    {
		    	return null;
		    }
		}
		catch (Exception e)
		{
			return null;
		}
		
	}
	
	/**
	* devuelve la listas de lineas de un archivo java
	* @param ruta del archivo
	* @return lista de lineas
	*/
	public  ArrayList<String> leerArchivosJava(String rutaArchivo)
	{
		ArrayList<String> listaLineas = new ArrayList<String>();
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		
			try 
			{
				String line = br.readLine();
			
				
				while (line != null) 
				{
					
					if (line.matches("\\s*")==false)
					{
						
						listaLineas.add(line);
						
					}
					line = br.readLine();
				}					
				
		    }
			catch (IOException e) 
			{
				
			}
			br.close();
			return listaLineas;
		} 
			
		catch (Exception e) 
		{
			System.out.println("El Archivo No Se Pudo Abrir");
			return null;
		}
	}
     
	/**
	* cuenta las lineas de cada archivo y las guerda en la lista de partes
	* @param lista de archivos java
	*/
	public void contarLineasLogicas(ArrayList<String> listaArchivosJava)
	{
		try
		{
			for (String archivoJava: listaArchivosJava)
			{
				File file = new File(archivoJava);
				int numeroLineas=0;
				int numeroItems=0;
				Parte parte = new Parte();
				ArrayList<String> lineas = leerArchivosJava(archivoJava);
				
				for(String linea: lineas)
				{
				
					if (esComentario(linea)==false)
					{
						
						if(esClase(linea) || esCorchete(linea)||esPalabraClave(linea)||esSentencia(linea))
						{
							numeroLineas+=1;
							
						}
						else if(esMetodo(linea))
						{
							numeroLineas+=1;
							numeroItems+=1;
						}
					}
				}
				
				parte.setNombreParte(file.getName().replace(".java", ""));
				parte.setLineas(lineas);
				parte.setTotalLineasLogicas(numeroLineas);
				parte.setTotalItems(numeroItems);
				
				partes.add(parte);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Error en contarLineasLogicas");
		
		}
	}
	
	/**
	* retorna el listado de partes
	*
	*/
	public ArrayList<Parte> getPartes() {
		return partes;
	}

	

	/**
	* establece si una linea de codigo es comentario
	* @param liena de codigo
	* @return es comentario si o no
	*/
    public Boolean esComentario(String linea)
    { 
    	try
    	{
	    	int numerodeCaracter=0;
	    	String lineasinEspacios = linea.replace(" ","");
	    	lineasinEspacios = linea.replace("\t","");
	    	for (char x : lineasinEspacios.toCharArray())
	    	{
	    		if (x != ' ' )
	    		{
	    			numerodeCaracter+=1;    			
	    			if((x== '*' || x== '/') && numerodeCaracter == 1)
	    			{
	    				return true;
	    			}
	    			
	    			else
	    			{
	    				return false;
	    			}
	    		}
	    	}
	    	return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esComentario");
			return false;
		}
    }
    
	/**
	* establece si una linea de codigo es package
	* @param liena de codigo
	* @return es comentario si o no
	*/
    public Boolean esPackage(String linea)
    { 
    	try
    	{
    		int numerodeCaracter=0;
    		String lineasinEspacios = linea.replace("\t","");
    		String[] lineaDividida = lineasinEspacios.split(" ");
    		
    		for (String subLinea :lineaDividida )
    		{
    			System.out.println("Error en esPackage");
    		 if (subLinea != " ")
    		 {
    			 numerodeCaracter+=1; 
    			 
    			 if(subLinea.equals( "package") && numerodeCaracter==1 )
    			 {
    				
    				 return true;
    			 }
    				 
    			 else
    			 {
    				 return false;
    			 }    			 
    		 }
    		}
	    	return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esPackage");
			return false;
		}
    }

    /**
	* establece si una linea de codigo es import
	* @param liena de codigo
	* @return es importe si o no
	*/
    public Boolean esImport(String linea)
    { 
    	try
    	{
    		int numerodeCaracter=0;
    		String lineasinEspacios = linea.replace("\t","");
    		String[] lineaDividida = lineasinEspacios.split(" ");
    		
    		for (String subLinea :lineaDividida )
    		{
    		 if (subLinea != " ")
    		 {
    			 numerodeCaracter+=1; 
    			 
    			 if(subLinea == "import" && numerodeCaracter==1 )
    			 {
    				 return true;
    			 }
    				 
    			 else
    			 {
    				 return false;
    			 }    			 
    		 }
    		}
	    	return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esImport");
			return false;
		}
    }

    /**
	* establece si una linea de codigo es clase
	* @param liena de codigo
	* @return es clase si o no
	*/
    public Boolean esClase(String linea)
    { 
    	try
    	{
    		String lineasinEspacios = linea.replace("\t","");
    		String[] lineaDividida = lineasinEspacios.split(" ");
    		
    		for (String subLinea :lineaDividida )
    		{
    			if (subLinea.equals("class"))
    			{    				
    				return true;
    			}
	    	
    		}
    		return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esPackage");
			return false;
		}
    }

    /**
	* establece si una linea de codigo es corchete
	* @param liena de codigo
	* @return es corchete si o no
	*/    
    public Boolean esCorchete(String linea)
    { 
    	try
    	{
    		int numerodeCaracter=0;
    		String lineasinEspacios = linea.replace("\t","");
	    	for (char x : lineasinEspacios.toCharArray())
	    	{
	    		if (x != ' ')
	    		{
	    			numerodeCaracter+=1;    			
	    			if((x== '{' || x== '}') && numerodeCaracter == 1)
	    			{
	    				
	    				return true;
	    			}
	    			
	    			else
	    			{
	    				return false;
	    			}
	    		}
	    	}
	    	return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esCorchete");
			return false;
		}
    	
    	
    }
   
    /**
	* establece si una linea de codigo es un Metodo
	* @param liena de codigo
	* @return es metodo si o no
	*/ 
 
    public Boolean esMetodo(String linea)
    { 
    	try
    	{
    		
    		if (linea.contains(";")== false)
    		{
    			    String lineasinEspacios = linea.replace("\t","");
    	    		String[] lineaDividida = lineasinEspacios.split(" ");
    	    	
    	    		for (String subLinea :lineaDividida )
    	    		{
    	    			if (subLinea.equals(" ")== false)
    	    			{
    		    			if (subLinea.equals("public") || subLinea.equals("private") || subLinea.equals("protected"))
    		    			{
    		    				
    		    				return true;
    		    			}
    		    			
    	    			}
    	    		}
    		}
    		return false;
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esMetodo");
			return false;
		}    	
    	
    }
    
    /**
	* establece si una linea de codigo es una palabre clave
	* @param liena de codigo
	* @return es palabra clave si o no
	*/ 
    public Boolean esPalabraClave(String linea)
    { 
    	try
    	{
    		if (linea.contains(";")== false)
    		{
	    		    		
	    		
	    	
	    		 if (linea.contains("public") || linea.contains("private") || linea.contains("protected") )
	    		 {
	    			 return false;
	    		 }
	    		
	    		 else
	    		 {
	    		
	    			 return true;
	    		 }
    		}
    		else
    		{
    			return false;
    		}
    		
    		
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esPalabraClave");
			return false;
		}  	
    	
    }
    
    /**
   	* establece si una linea de codigo es una sentencia
   	* @param liena de codigo
   	* @return es sentencia clave si o no
   	*/ 
    public Boolean esSentencia(String linea)
    { 
    	try
    	{
    		if (linea.contains(";")== true)
    		{
	    	 return true;	
    		}
    		
    		return false;
    		
    	}
    	catch (Exception e) 
		{
			System.out.println("Error en esSentencia");
			return false;
		}  	
    	
    }

}
