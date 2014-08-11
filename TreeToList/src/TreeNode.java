
public class TreeNode<E extends Comparable<? super E>> {

	E data;
	TreeNode<E> left;
	TreeNode<E> right;
	
	public TreeNode(E val) {
		data = val;
	}
	
	public TreeNode(E val, TreeNode<E> l, TreeNode<E> r) {
		data = val;
		left = l;
		right = r;
	}
	
}
