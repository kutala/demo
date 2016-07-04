package com.kuta.demo.doublelinkedlist;

//双向列表节点
public class Node<T> {
	
	//上一个节点
	public Node<T> prev;
	//下一个节点
	public Node<T> next;
	//节点数据
	public T data;
	
	public boolean equals(Node<T> node){
		if(data.equals(node.data)){
			return true;
		}
		return false;
	}
	
	public int hashCode(){
		return data.hashCode();
	}
	
	public String toString(){
		return data.toString();
	}
	
}
