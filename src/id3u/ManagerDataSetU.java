/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3u;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author SARA
 */
public class ManagerDataSetU {
    
       //Retorna el elemento de mayor frecuencia en el atributo A   
    public static String majorityElement(String dataSet [][], int A){
        
        ArrayList enumValue = enumerateValues(dataSet, A); 
        int size = enumValue.size();
        double [] occurrences = new double [size];
        String [] values = new String[size];
        Enumeration en = Collections.enumeration(enumValue);
        int index =0;
        while(en.hasMoreElements()){
            String value= (String) en.nextElement();
            occurrences[index] = ocurrence(dataSet, value, A);
            values[index]=value;
            index++;
        }
        int max = 0; 
        for(int i =0; i<size; i++){
            if(occurrences[i]>occurrences[max])
                max=i;            
        }   
        return values[max];
    }
    
    
    
    //Revisa si todas las instancias pertencen a la misma clase revisando el atributo de clase m[i][0] VERIFICADOooooooo
    public static boolean sameClass(String dataSet [][]){
        String val = dataSet[1][0];
        for(int i = 1; i<dataSet.length; i++){
            if(!dataSet[i][0].equals(val))
                return false;
            val = dataSet[i][0];        
        }
        return true;   
    }    
    
    //CALCULA CUANTAS VECES ESTÁ EL VALOR value EN EL ATRIBUTO A VERIFICADOooooo
    public static double ocurrence(String dataSet [][], String value, int A){        
        double occ = 0;        
        for(int i=1; i<dataSet.length; i++){
            if(dataSet[i][A].equals(value))
                occ++;  
        }        
        return occ;    
    }
    
    //Coloca en un arreglo los nucléotidos presentes en un atributo VERIFICADOoooo
    public static ArrayList enumerateValues(String dataSet [][], int A){        
        ArrayList <String>enumerateValues = new ArrayList<>();
        for(int i=1; i<dataSet.length; i++){
            if(!enumerateValues.contains(dataSet[i][A])){
                enumerateValues.add(dataSet[i][A]); 
            }
        }        
        return enumerateValues;    
    }  
    
    //Elimina el atributo A y devuelve un dataSet sin ese atributo VERIFICADO
     public static String [][] remove (String [][] dataSet, int A){
        
        int numberAttributes = dataSet[1].length;
        int numberInstances = dataSet.length;
        
        String v_dataSet [][] = new String [numberInstances][numberAttributes-1];       
        
        for(int i = 0; i<numberInstances; i++){
            for(int j =0; j<A; j++){
                v_dataSet[i][j]= dataSet[i][j];
            }      
        }        
        for(int i = 0; i<numberInstances; i++){
            for(int j=A+1; j<numberAttributes; j++){
                v_dataSet[i][j-1]= dataSet[i][j];
            }      
        }
        return v_dataSet;
    }  
    
}
