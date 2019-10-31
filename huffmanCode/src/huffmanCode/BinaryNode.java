package huffmanCode;

import java.awt.List;

/**
 * @author Renln
 * 实现二叉树模板
 */
public class BinaryNode implements Comparable<BinaryNode> {
 
	//定义组成基本变量
	int weight;
	BinaryNode left;
	BinaryNode right;
	BinaryNode parents;
	String data;
	
	//将节点构造成树
	public BinaryNode(int weight,BinaryNode left,BinaryNode right,BinaryNode parents,String data) {
		this.weight = weight;
		this.left=left;
		this.right=right;
		this.parents=parents;
		this.data =data;
	} 
	
	@Override
	public int compareTo(BinaryNode o) {
		return weight-o.weight;
	}	
	public boolean isLeftChild() {
        return parents != null && parents.left == this;
    }

    public boolean isRightChild() {
     return parents != null && parents.right == this;
    }
    public boolean isChild(){
    	return parents !=null &&parents.right ==null &&parents.left ==null;
    }
}
