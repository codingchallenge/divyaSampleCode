import org.junit.Assert;
import org.junit.Test;


public class TreeLibraryTest {

	/***
	 * Positive test case to verify binary search tree is converted into sorted linked list
	 */
	@Test
	public void convertTreeToLinkedListTest() {
		
		TreeNode<Integer> tree = constructTree(1,6);
		TreeNode<Integer> root = TreeLibrary.binaryTreeToLinkedList(tree);
		
		Assert.assertTrue(checkList(root, 6));
	}
	
	/***
	 * Verify the operation handles null parameters
	 */
	@Test
	public void convertEmptyTreeTest() {		
		TreeNode<Integer> root = TreeLibrary.binaryTreeToLinkedList(null);
		Assert.assertNull(root);
	}
	
	/***
	 * Verify the convert operation for single node
	 */
	@Test
	public void convertSingleNodeToLinkedListTest() {
		
		TreeNode<Integer> tree = constructTree(1,1);
		TreeNode<Integer> root = TreeLibrary.binaryTreeToLinkedList(tree);
		
		Assert.assertTrue(checkSingleNode(root));
	}
	
	/***
	 * Verify operation for large trees
	 */
	@Test
	public void convertLargeTreeToLinkedListTest() {
		
		TreeNode<Integer> tree = constructTree(1,50);
		TreeNode<Integer> root = TreeLibrary.binaryTreeToLinkedList(tree);
		
		Assert.assertTrue(checkList(root, 50));
	}
	
	/***
	 * In case of single node, left and right should point to same node
	 * @param root
	 * @return
	 */
	private boolean checkSingleNode(TreeNode<Integer> root) {
		if(root == null)
			return false;
		if(root.left != root.right)
			return false;
		
		return true;
	}
	
	/***
	 * Verify the linked list is in sorted order
	 * @param root
	 * @param size
	 * @return
	 */
	private boolean checkList(TreeNode<Integer> root, int size) {
		if(root == null)
			return false;
		
		int count = size-1;
		while(count > 0) {
			if(root.data > root.right.data)
				return false;
			root = root.right;
			count--;
		}
		return true;
	}
	
	/***
	 * Construct balanced binary search tree which is used for testing 
	 * @param start
	 * @param end
	 * @return
	 */
	private TreeNode<Integer> constructTree(int start, int end) {
		if((end-start) < 0)
			return null;
		else if(end == start){
			TreeNode<Integer> tree = new TreeNode<Integer>(start);
			return tree;
		}
		
		int mid = start + (end-start)/2;
		TreeNode<Integer> tree = new TreeNode<Integer>(mid);
		
		tree.left = constructTree(start,mid-1);
		tree.right = constructTree(mid+1,end);
		return tree;
	}

}
