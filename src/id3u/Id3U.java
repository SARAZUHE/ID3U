/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3u;

import Tree.DecisionTree;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author SARA
 */
public class Id3U {
    public static DecisionTree decisionTreeLearner(String [][] dataSet, String [][] p_dataSet, String assegnation) throws Exception {
        
        if(dataSet.length == 0){
            System.out.println("Entro condicion 1");
            return new DecisionTree(ManagerDataSetU.majorityElement(p_dataSet, 0),assegnation);
        }
        if(ManagerDataSetU.sameClass(dataSet)){
            System.out.println("entro condicion 2");
            return new DecisionTree(dataSet[1][0],assegnation);
        }
        if(dataSet[1].length==1){
            System.out.println("entro a condicion 3");
            return new DecisionTree(ManagerDataSetU.majorityElement(dataSet, 0),assegnation);
        }       
        
        
        System.out.println("Entro condicion 4");

        
        int A = Id3U.selectAttribute(dataSet);   
        System.out.println(A+"antes arbol");
        
        DecisionTree tree = new DecisionTree(A,assegnation);
        System.out.println("ATRRIBUTO ARBOL:"+tree.getAttribute());
        
        Enumeration values = Collections.enumeration(ManagerDataSetU.enumerateValues(dataSet, A));
        
       
              
        while(values.hasMoreElements()){

            String value = (String) values.nextElement();
                        String v_dataSet [][] = new String[(int)ManagerDataSetU.ocurrence(dataSet, value, A)+1][dataSet[0].length]; //NOTA: corregi porque antes tenia: (int)ManagerDB.ocurrence(dataSet, v, A, numberInstances)
            int v_instance = 1;
            
            for(int j=0;j<dataSet[1].length;j++){
                v_dataSet[0][j]=dataSet[0][j];
            }           
            for(int i=1; i<dataSet.length;i++){
                if(dataSet[i][A].equals(value)){                     
                    for(int j=0; j<dataSet[i].length; j++){
                        v_dataSet[v_instance][j]=dataSet[i][j];                        
                    }
                    v_instance++;
                }
            } 
            v_dataSet = ManagerDataSetU.remove(v_dataSet, A);
            
            tree.setChild(decisionTreeLearner(v_dataSet,dataSet,value),tree);              
        }        
        return tree;       
    }
    

    public static Integer selectAttribute(String dataSet [][]) throws Exception{
	Integer ind;
        Integer A;
        
        double gain, maxGain;
        
        A = 1;       
        gain = ManagerInfoGainU.calc_infoGain(dataSet,A);        
        maxGain= gain;        
        ind=1; 
        
        for(int j=1;j<dataSet[1].length;j++){            
            A = j;            
            gain = ManagerInfoGainU.calc_infoGain(dataSet, A);
            
            if(gain>maxGain){                
                maxGain = gain;                
                ind = Integer.parseInt(dataSet[0][j]);  
            }          
        }
        System.out.println("Atributo: "+ind); 
        return ind;    
	}
    
    
}
