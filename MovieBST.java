package movietitles;

/**
 * This class is a Binary Search Tree using the MovieNode class.
 *
 * @author Francis Rigor
 */
import java.util.ArrayList;
import java.util.List;

public class MovieBST<E extends Comparable<E>> {

    private MovieNode root;
    private int size = 0;   // The number of nodes in this tree
    ArrayList<String> list = new ArrayList<>(); // To be used to make a sublist

    public MovieBST() {
        root = null;
    }

    public int getSize() {
        return size;
    }

    public void insert(String title) {
        MovieNode newNode = new MovieNode();
        newNode.movieTitle = title;
        if (root == null) {
            root = newNode; // Create a new root if the root node is empty
            size++;
        } else { // if root is occupied
            MovieNode current = root;
            MovieNode parent;
            while (true) {
                if (title.compareTo(current.movieTitle) < 0) { // go left
                    parent = current;
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        size++;
                        return;
                    }
                } else if (title.compareTo(current.movieTitle) > 0) { // go right 
                    parent = current;
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        size++;
                        return;
                    }
                }
            }
        }
    }

    public void getSublist(int num1, int num2) {
        // Sub ArrayList
        List<String> sublist = list.subList(num1, num2);
        System.out.println(sublist);
    }
    
    //--------------------Traversal methods---------------------------------
    public void inorderTraversal() {
        System.out.println("Inorder traversal: ");
        inOrder(root);
    }
    
    private void preOrder(MovieNode node) {
        if (node != null) {
            System.out.print("\n" + node.movieTitle);
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    private void inOrder(MovieNode root) {
        if (root != null) {
            inOrder(root.leftChild);
            System.out.print("\n" + root.movieTitle);
            list.add(root.getMovieTitle());
            inOrder(root.rightChild);
        }
    }

    private void postOrder(MovieNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print("\n" + node.movieTitle);
        }
    }

}
