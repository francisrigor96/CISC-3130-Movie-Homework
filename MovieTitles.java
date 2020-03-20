package movietitles;

/**
 * An implementation of the MovieNode and MovieBST classes.
 *
 * @author Francis Rigor
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieTitles {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        try {
            BufferedReader br = new BufferedReader(new FileReader("movies.csv"));       // The file to be read-in
            Scanner input = new Scanner(System.in);                                     // Keyboard input
            int fileLineReadCount = 0;                  // Limits the number of lines to be read since there are ALOT of lines in the file

            MovieBST<String> tree = new MovieBST<>();       // The binary search tree
            MovieNode node;                                 // A movie node

            /**
             * This "small" file has tens of thousands of lines so for demonstration purposes
             * we will just read in a maximum of 100.
             */
            while (fileLineReadCount < 100) {
                node = MovieNode.read(br);
                tree.insert(node.getMovieTitle());
                fileLineReadCount++;
            }

            System.out.println("Nodes in tree: " + tree.getSize());

            // Traverse the tree via inorder
            tree.inorderTraversal();
            System.out.println();
            System.out.println("\n-------------------------------");

            String key;
            int sublistCount = 0;
            
            while (true) {
                System.out.println("Do you want to check for movie sublists? Type yes to do so. Typing anything else will end the program.");
                key = input.next();

                if (key.equals("yes")) {
                    System.out.println("Enter a number between 0 and " + tree.getSize());
                    int num1 = input.nextInt();
                    System.out.println("Enter another number above " + num1 + " but below that of " + tree.getSize());
                    int num2 = input.nextInt();
                    System.out.println("These are your movies: ");
                    tree.getSublist(num1, num2);
                    sublistCount++;
                    System.out.println();
                } else {
                    break;
                }
            }
            
            System.out.println("Number of sublists done: " + sublistCount);

        } catch (FileNotFoundException a) {
            System.out.println("***FileNotFoundException*** " + a.getMessage());
        } catch (IOException b) {
            System.out.println("***IOException*** " + b.getMessage());
        } catch (Exception c) {
            System.out.println("***Exception*** " + c.getMessage());
        }

    } // end of main method

} // end of class

