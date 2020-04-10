package moviegenres;

/**
 *  This class is used to hold information about a single movie. 
 *  In particular, we are interested in counting the total number of genres for each movie overall
 *  in the file and also the total number of each genre in a given year
 * 
 * @author Francis Rigor
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Movie {

    private int movieID;             // The movie's position in the file (not really important)
    private int releaseYear;         // The movie's release year
    private String movieTitle;       // The title of the movie (also the key)
    private LinkedList<String> list = new LinkedList<>();     // To be used to grab the genre names from each movie. This is the most imporant data field.
  
    //------------------Constructor-------------------------------------
    public Movie(int id, String title, int year, LinkedList<String> list) {
        this.movieID = id;
        this.movieTitle = title;
        this.releaseYear = year;
        this.list = list;
    }

    //------------------Getter methods-------------------------------------
    public String getMovieTitle() {
        return movieTitle;
    }

    public int getMovieID() {
        return movieID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public LinkedList<String> getLinkedList() {
        return list;
    }

    //-------------------Other methods------------------------------------
    
    /**
     * This method is for testing purposes only to see if the data fields are
     * being read into.
     * It really isn't meant to be used. This method is only used as
     * the program is being developed. Once I'm satisfied that everything is 
     * working fine, this method is more or less not needed anymore.
     * 
     * @return A line containing the data fields entries
     * 
     * Sample line:
     * 1 | Toy Story | 1995 | [Adventure, Animation, Children, Comedy, Fantasy]
     */
    @Override
    public String toString() {
        return movieID + " | " + movieTitle + "| " + releaseYear + " | " + getLinkedList();
    }

    /**
     * This method creates a Movie object by reading from the file provided.
     *
     * @param br The file to be read from
     * @return A newly created object
     * @throws IOException
     */
    public static Movie read(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] toSplit = line.split("(,())");
        int movieID = Integer.parseInt(toSplit[0]);

        // Split toSplit[1] into the movie title and year of release
        String[] splitMovieTitle = toSplit[1].split("[()]");
        String movieTitle = splitMovieTitle[0];
        int releaseYear = Integer.parseInt(splitMovieTitle[1]);

        // Split toSplit[2] into genres and copy the contents into a Linked List
        String[] splitGenres = toSplit[2].split("[|]");
        LinkedList<String> list = new LinkedList<>();
        for (String splitGenre : splitGenres) {
            list.add(splitGenre);
        }

        return new Movie(movieID, movieTitle, releaseYear, list);
    }
}
