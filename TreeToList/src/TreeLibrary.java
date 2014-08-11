
public class TreeLibrary {
	
	/***
	 * Convert given tree into doubly linked list
	 * @param root
	 * @return TreeNode in linked list format
	 */
	public static <E extends Comparable<? super E>> TreeNode<E> binaryTreeToLinkedList(TreeNode<E> root) {
		if(root == null)
			return null;
	
		TreeNode<E> left = binaryTreeToLinkedList(root.left);
		TreeNode<E> right = binaryTreeToLinkedList(root.right);
		
		//Getting the leftEnd and pointing it to root. Root pointing back to leftEnd
		TreeNode<E> leftEnd = root;
		if(left != null) {
			leftEnd = left.left;
			leftEnd.right = root;
			root.left = leftEnd;
			leftEnd = root;
		} else {
			left = root;
		}
		
		// Getting the rightEnd and pointing it back to left linked list
		TreeNode<E> rightEnd = leftEnd;
		if(right != null) {
			rightEnd = right.left;
			leftEnd.right = right;
			right.left = leftEnd;
		} 
		
		//Pointing the head and tail of the linked list to each other to complete the circular linked list
		rightEnd.right = left;
		left.left = rightEnd;
		
		return left;
	}
	
	/***
	 * Insert new value into the given tree to maintain binary search order
	 * @param tree
	 * @param data
	 * @return
	 */
	public static <E extends Comparable<? super E>> TreeNode<E> insert(TreeNode<E> tree, E data){
		if(tree == null) {
			tree = new TreeNode<E>(data,null,null);
			return tree;
		} else if (data.compareTo(tree.data) < 0) {
			tree.left = insert(tree.left, data);
			return tree;		
		} else {
			tree.right = insert(tree.right, data);
			return tree;
		}
	} 
	
	public static <E extends Comparable<? super E>> void printInorder(TreeNode<E> tree) {
		if(tree == null)
			return;
		else {
			printInorder(tree.left);
			System.out.println(tree.data);
			printInorder(tree.right);
		}
	}
}
