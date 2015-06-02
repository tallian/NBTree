import java.util.ArrayList;


public class NBTreeNode<T> {
	NBTreeNode<T> firstLink;
	ArrayList<Comparable<T>> pageInt;
	ArrayList<NBTreeNode<T>> pageLink;
	NBTreeNode<T> parent;
	Integer size;	
	
	NBTreeNode(Comparable<T> e, NBTreeNode<T> parent){
		this.parent = parent;
		pageInt = new ArrayList<Comparable<T>>();
		pageInt.add(e);
		pageLink = new ArrayList<NBTreeNode<T>>();
		pageLink.add(null);
		firstLink = null;
		size = 1;
	}
	
	NBTreeNode(ArrayList<Comparable<T>> v){
		pageInt = v;
		size = v.size();
		pageLink = new ArrayList<NBTreeNode<T>>();
		for (int i = 0; i < size; i++) {
			pageLink.add(null);
		}
	}
	
	public void delete(int index){
		if (index == 0) {
			this.firstLink = pageLink.get(index);
		}
		this.pageInt.remove(index);
		this.pageLink.remove(index);
		this.size--;
	}
	
	public void addElement(Integer previous, Comparable<T> e, NBTreeNode<T> link) {
		pageInt.add(previous+1, e);
		pageLink.add(previous+1, link);
		size++;
	}
	
	public void addLink(Integer i, NBTreeNode<T> node){
		pageLink.add(i, node);
	}
}
