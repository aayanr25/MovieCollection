import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies;
    private Scanner fileScanner;
    private Scanner scan;




    public MovieCollection() {

    }

    public void importData() {
        movies = new ArrayList<>();
        try {
            File myFile = new File("src//movies_data.csv");
            fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sort() {
        for (int i = 1; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            int index = i;
            while (index > 0 && movie.getTitle().compareTo(movies.get(i - 1).getTitle()) < 0) {
                movies.set(index, movies.get(index - 1));
                index--;
            }
            movies.set(index, movie);
    }
    public void mainMenu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }
}
