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
public class ManagerInfoGainU {
    
      public static double calc_entropy(String dataSet [][]){
        int classAttribute = 0;
        ArrayList<String> enumValues;        
        double entropy = 0;   
        
        enumValues = ManagerDataSetU.enumerateValues(dataSet, classAttribute);
        Enumeration en = Collections.enumeration(enumValues);
        while(en.hasMoreElements()){
            String v = (String)en.nextElement();
            double occ = ManagerDataSetU.ocurrence(dataSet, v, classAttribute);
            if(occ!=0){
                entropy+= (occ/dataSet.length)*(Math.log1p(dataSet.length/occ)/Math.log1p(2));
            }
        }
        return entropy;
    }
    
    public static double calc_infoGain(String dataSet [][], int A){
        double infoGain = calc_entropy(dataSet);
        ArrayList<String> enumValues;
        
        enumValues = ManagerDataSetU.enumerateValues(dataSet, A);
        Enumeration en = Collections.enumeration(enumValues);   
        
        while(en.hasMoreElements()){
            String v = (String) en.nextElement();            
            String v_dataSet [][] = new String[(int)ManagerDataSetU.ocurrence(dataSet, v, A)+1][dataSet[0].length]; //NOTA: corregi porque antes tenia: (int)ManagerDB.ocurrence(dataSet, v, A, numberInstances)
            int v_instance = 1;
            
            for(int j=0;j<dataSet[1].length;j++){
                v_dataSet[0][j]=dataSet[0][j];
            }           
            for(int i=1; i<dataSet.length;i++){
                if(dataSet[i][A].equals(v)){                     
                    for(int j=0; j<dataSet[i].length; j++){
                        v_dataSet[v_instance][j]=dataSet[i][j];                        
                    }
                    v_instance++;
                }
            }
            infoGain-=ManagerDataSetU.ocurrence(dataSet, v, A)/dataSet.length*calc_entropy(v_dataSet);  
        }    
        return infoGain;    
    }
    
}
