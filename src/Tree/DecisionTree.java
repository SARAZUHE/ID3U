/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author SARA
 */
public class DecisionTree {

	private ArrayList<DecisionTree> childrens;
	private Integer attribute;
	private String edge;
	private String result;
	private MutableTreeNode node;

	public DecisionTree(int attribute,String edge){
		childrens=new ArrayList<DecisionTree>();
		this.attribute=attribute;
		this.edge=edge;
		this.result=null;
		node = new DefaultMutableTreeNode("-"+ attribute+"-      edge: -"+edge+"-");
	
	}
	//leaf nodes
	public DecisionTree(String result,String edge){
		//this.attribute=(Integer)null;
		this.edge=edge;
		this.result=result;
		node = new DefaultMutableTreeNode("-"+result+"-      edge: -"+edge+"-");
	}
	
	public void setChild(DecisionTree child,DecisionTree parent){
		childrens.add(child);
		parent.getNode().insert(child.getNode(), 0);
		
	}
	public MutableTreeNode getNode() {
		return node;
	}
	public Integer getAttribute() {
		return attribute;
	}
	public String getEdge() {
		return edge;
	}
	public String getResult() {
		return result;
	}
	public ArrayList<DecisionTree> getChildrens() {
		return childrens;
	}
	
}

