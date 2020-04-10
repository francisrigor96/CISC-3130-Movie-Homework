package moviegenres;

/**
 * This class implements the Movie class and is used to find the total number of
 * genres overall.
 *
 * Part 1: Total number of each movie genre for the whole file Part 2: Total
 * number of each movie genre for a given year
 *
 * @author Francis Rigor
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.HashMultimap;
import java.util.Collection;

public class MovieGenres {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("movieinfo.csv")); // The file to be read in
            Movie movie = Movie.read(br);                                            // The object to be used for HashMap
            int fileReadCount = 0;                                                   // Keeps track of how many lines were read

            // The HashMap and HashSet objects
            Map<String, LinkedList<String>> map = new HashMap<>();               // This HashMap considers movie names and movie genres. Used for part 1.
            HashMultimap<Integer, LinkedList<String>> multiMap = HashMultimap.create();     // This contains years and genres. Used for part 2.
            HashSet<String> uniqueGenres = new HashSet<>();                      // Keeps track of unique genre names for every single movie. Used in both parts     

            // This LinkedList object is to used to count the occurences of each movie genre
            // We fill this object by getting the LinkedList inside of the Movie class
            LinkedList<String> countGenres = new LinkedList<>();

            // Read through lines of the file
            do {
                // Fill in the map objects for getting movie genres 
                map.put(movie.getMovieTitle(), movie.getLinkedList());
                multiMap.put(movie.getReleaseYear(), movie.getLinkedList());

                // Add to the countGenres list by iterating through the map's value
                addToLinkedList(countGenres, movie);

                addToSet(uniqueGenres, movie.getLinkedList());
                fileReadCount++;
                movie = Movie.read(br);
            } while (fileReadCount < 1500);

            br.close(); // close the file

            //--------------------Part 1: Number of genres mentioned---------------------------------------
            printOverallInfo(map, uniqueGenres, countGenres);
            System.out.println("\n--------------------------------------------------------------");

            // Empty these so that they can be used in Part 2
            countGenres.clear();
            uniqueGenres.clear();

            //---------------Part 2: Number of genres in each given year--------------------------------------
            // Gets all the unique years that were read-in
            Set<Integer> yearSet = multiMap.keySet();

            // Print out info for all years
            System.out.println("Part 2: Counting overall number of each genre in a certain year\n");
            System.out.println("All distinct years: (as a set)\n" + yearSet + "\n");
            printYearInfo(multiMap, uniqueGenres);

        } catch (FileNotFoundException a) {
            System.out.println("***FileNotFoundException*** " + a.getMessage());
        } catch (IOException b) {
            System.out.println("***IOException*** " + b.getMessage());
        } catch (Exception c) {
            System.out.println("***Exception*** " + c.getMessage());
        }

    }

    /**
     * This method adds unique genre names from the LinkedList to the HashSet.
     * This is done in order to get a small list containing every single unique
     * genre name from the movies.
     *
     * @param set The HashSet to be filled
     * @param list The LinkedList that will be used to fill HashSet
     */
    public static void addToSet(Set<String> set, LinkedList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }
    }

    /**
     * Add to a LinkedList object from the Movie object
     *
     * @param list
     * @param movie
     */
    public static void addToLinkedList(LinkedList<String> list, Movie movie) {
        for (String temp : movie.getLinkedList()) {
            list.add(temp);
        }
    }

    /**
     * This method is used in part 2. It prints out all the genres in a given
     * year. It does this by iterating through each key.
     *
     * @param multiMap the HashMultiMap object
     * @param uniqueGenres the HashSet containing the uniqueGenres
     */
    public static void printYearInfo(HashMultimap<Integer, LinkedList<String>> multiMap, HashSet<String> uniqueGenres) {
        for (Integer key : multiMap.keySet()) {
            Collection<LinkedList<String>> values = multiMap.get(key);
            LinkedList<Object> link = new LinkedList<>();
            Object[] gr;
            int count = 0; // Count how many overall genres are associated with the current year

            System.out.println("\nIterating through " + key + "\n");
            // Add to set uniqueGenres and then all of the genre names
            for (LinkedList<String> a : values) {
                addToSet(uniqueGenres, a);
                gr = a.toArray();
                for (int i = 0; i < gr.length; i++) {
                    link.add(gr[i]);
                    count++;
                }
            }

            System.out.println("Unique genres for " + key + ": " + uniqueGenres);
            System.out.println("Number of unique genres for " + key + ": " + uniqueGenres.size());
            System.out.println("Overall number of genres in " + key + ": " + count);
            System.out.println("\nNumber of times each genre was mentioned:");
            for (String s : uniqueGenres) {
                System.out.println(s + ": " + Collections.frequency(link, s));
            }

            // Empty these objects for the next key to be analyzed
            link.clear();
            uniqueGenres.clear();

        }
    }

    /**
     * This method prints the info of the all the years and the genres
     * associated with those years. Used in part 1 only.
     *
     * @param map A Map object
     * @param uniqueGenres A HashSet object
     * @param countGenres A LinkedList object
     */
    public static void printOverallInfo(Map<String, LinkedList<String>> map, HashSet<String> uniqueGenres, LinkedList<String> countGenres) {
        System.out.println("Genre list (as a set): " + uniqueGenres);
        System.out.println("Number of unique genres: " + uniqueGenres.size());
        System.out.println("--------------------------------------------------------\nPart 1: Counting overall number of each genre\n");
        System.out.println("Map size: " + map.size());

        // Count the number of each genre in the list
        System.out.println("\nNumber of times each genre was mentioned:");
        for (String s : uniqueGenres) {
            System.out.println(s + ": " + Collections.frequency(countGenres, s));
        }
    }
    
}
