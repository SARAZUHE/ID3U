/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import Tree.DecisionTree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import id3u.*;

/**
 *
 * @author SARA
 */
public class Id3UProject {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        String [][] dataSet, v_dataSet; 
    
        System.out.println("INDIQUE EL NÚMERO DE ATRIBUTOS PARA EL ANÁLISIS --NÚMERO ENTRE 1 Y 5--");
        Scanner in = new Scanner (System.in); //Creación de un objeto Scanner
        String option =in.nextLine();
        //Genera el dataSet según el archivo elegido  
        dataSet = dataSetUpload(option);      
        
        
        /*
        v_dataSet = ManagerDataSetA.remove(dataSet, 2);
        
        for(int i =0; i<v_dataSet.length; i++){
                    for(int j=0; j<v_dataSet[0].length;j++){
                        System.out.print(v_dataSet[i][j]+"/");
                    }    
                    System.out.println("");
        }  
        
        int A = ID3A.selectAttribute(v_dataSet); 
        System.out.println(A);        
        */
        
        //double entropy = ManagerInfoGainZ.calc_entropy(dataSet);
        //System.out.println(entropy);  
        //double gain = ManagerInfoGainZ.calc_infoGain(dataSet,1);
        //System.out.println(gain);        
        //int at = ID3A.selectAttribute(dataSet);
        //System.out.println(at);
        //String attr = ManagerDataSetA.majorityElement(dataSet, 1);
        //System.out.println(attr);       
      
        
        DecisionTree id3_tree = Id3U.decisionTreeLearner(dataSet,null , null);
        System.out.println("MUESTRA ARBOL");
        ManagerDisplayU.displayTree(id3_tree);            
        System.out.println("TERMINA");
        
    }
    


 private static String [][] dataSetUpload(String option) throws FileNotFoundException, Exception{   
        
        StringTokenizer tokensInstances, tokensAttributes;
        BufferedReader b;
        String readDataSet;
        String [][] dataSet;        
        
        b = new BufferedReader(new FileReader ("C:\\Users\\SARA\\Documents\\NetBeansProjects\\ID3A\\src\\data\\7_HCVL_"+ option + ".csv")); 
                //convierte la base de datos a una cadena para poder pasarla a una matriz
                readDataSet   = readBuffer(b);
                
                //En un ArrayList coloca cada una de  las instancias del dataSet
                tokensInstances = new StringTokenizer(readDataSet, "\n");
                ArrayList<String> instance  = new ArrayList ();
                while(tokensInstances.hasMoreElements()){
                    instance.add(tokensInstances.nextToken());
                }
                
                //Calcula en número de atributos para poder generar la matriz que almacene al DataSet
                String toContAttributes = instance.get(0);
                tokensAttributes = new StringTokenizer(toContAttributes,";");
                ArrayList<String> atr = new ArrayList<>();
                int k = 0;
                while(tokensAttributes.hasMoreElements()){
                    atr.add(tokensAttributes.nextToken());
                    k++;
                }
                int numberInstances = instance.size();
                int numberAttributes = atr.size();
                
                //Llena la matriz por instancia 
                dataSet = new String [numberInstances][numberAttributes];
                for(int i=0; i<numberInstances; i++){
                    String S = instance.get(i);
                    StringTokenizer tokens = new StringTokenizer(S,";");
                    int j=0;
                    while(tokens.hasMoreElements()){
                        dataSet[i][j] = tokens.nextToken();
                        j++;
                    }
                }                
        return dataSet;    
    }
    
    
        public static String readBuffer(BufferedReader buffer) throws Exception {
        String retorno = null;

        String lineaSalida = "";
        StringBuffer contenido = new StringBuffer();
        String separador = "";

        while ((lineaSalida = buffer.readLine()) != null){
            contenido.append(separador + lineaSalida);
            separador = "\n";
        }

        retorno = contenido.toString();

        return retorno;
    }    
    
}
