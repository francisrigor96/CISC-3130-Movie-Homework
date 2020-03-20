package movietitles;

/**
 * This class represents a single node of the Binary Search Tree.
 * 
 * @author Francis Rigor
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class MovieNode<E> {

    // A single node contains the following:
    public int movieID;             // The movie's position in the file (not really important)
    public int releaseYear;         // The movie's release year
    public String movieTitle;       // The title of the movie (also the key)
    public String[] genres;         // The genres this movie is labeled with
    
    // Nodes
    public MovieNode leftChild, rightChild;

    //-----------------Constructor--------------------------------------
    public MovieNode(int id, String title, int year, String[] arr) {
        this.movieID = id;
        this.movieTitle = title;
        this.releaseYear = year;
        this.genres = arr;
    }
    
    public MovieNode() {
        this.movieTitle = null;
    }
    
    //-----------------Getter methods-----------------------------------
    public String getMovieTitle() {
        return movieTitle;
    }

    public int getMovieID() {
        return movieID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getMovieGenres() {
        return Arrays.toString(this.genres);
    }

    //-------------------Other methods------------------------------------
    /**
     * This method is for testing purposes only to see if the data fields
     * are being read into.
     * 
     * @return A line containing the data fields entries
     */
    @Override
    public String toString() {
        return movieTitle + "| " + releaseYear + " |";
    }

    /**
     * This method creates a node by reading from the file provided.
     * 
     * @param br The file to be read from
     * @return A newly created node for the tree
     * @throws IOException 
     */
    public static MovieNode read(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] toSplit = line.split("(,())");
        int movieID = Integer.parseInt(toSplit[0]);

        // Split toSplit[1] into the movie title and year of release
        String[] splitMovieTitle = toSplit[1].split("[()]");
        String movieTitle = splitMovieTitle[0];
        int releaseYear = Integer.parseInt(splitMovieTitle[1]);

        // Split toSplit[2] into genres and copy the contents into a new array
        String[] splitGenres = toSplit[2].split("[|]");
        String[] genres = new String[splitGenres.length];
        for (int i = 0; i < genres.length; i++) {
            genres[i] = splitGenres[i];
        }

        return new MovieNode(movieID, movieTitle, releaseYear, genres);
    }

}
