import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies;
    private Scanner fileScanner;
    private Scanner scan;


    public MovieCollection() {
        importData();
        sortMovies(movies);
        mainMenu();
    }

    public void importData() {
        movies = new ArrayList<>();
        try {
            File myFile = new File("src//movies_data.csv");
            fileScanner = new Scanner(myFile);
            int idx = 0;
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] movieData = data.split(",");
                if (idx > 0) {
                    movies.add(new Movie(movieData[0], movieData[1], movieData[2], movieData[3], Integer.parseInt(movieData[4]), Double.parseDouble(movieData[5])));
                }
                idx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sortMovies(ArrayList<Movie> movies) {
        for (int i = 0; i < movies.size() - 1; i++) {
            String min = movies.get(i).getTitle();
            int minIDX = i;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(j).getTitle().compareTo(min) < 0) {
                    min = movies.get(j).getTitle();
                    minIDX = j;
                }
            }
            Movie temp = movies.get(i);
            movies.set(i, movies.get(minIDX));
            movies.set(minIDX, temp);
        }
    }

    public void sort(ArrayList<String> array) {
        for (int i = 1; i < array.size(); i++) {
            String str = array.get(i);
            int index = i;
            while (index > 0 && str.compareTo(array.get(i - 1)) < 0) {
                array.set(index, array.get(index - 1));
                index--;
            }
            array.set(index, str);
        }
    }

    public ArrayList<String> searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String name = scan.nextLine().toLowerCase();
        ArrayList<String> list = new ArrayList<>();
        for (Movie movie : movies) {
            for (String member : movie.getCastMembers()) {
                if (member.toLowerCase().contains(name) && !list.contains(member)) {
                    list.add(member);
                }
            }
        }
        sort(list);
        return list;
    }

    public ArrayList<Movie> searchTitles() {
        System.out.print("Enter a title search term: ");
        String title = scan.nextLine().toLowerCase();
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title)) {
                list.add(movie);
            }
        }
        return list;
    }

    public String movieInformation(ArrayList<Movie> titles, int userChoice) {
        Movie chosenMovie =  titles.get(userChoice - 1);
        return chosenMovie.movieInfo();
    }


    public void mainMenu () {
        scan = new Scanner(System.in);
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
                ArrayList<Movie> list = searchTitles();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).getTitle());
                }
                System.out.println("What movie would you like to learn more about?");
                System.out.print("Enter number: ");
                int num = scan.nextInt();
                scan.nextLine();
                System.out.println(movieInformation(list, num));
            } else if (menuOption.equals("c")) {
                ArrayList<String> list = searchCast();
                sort(list);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println("Which actor do you want to see all movies for?");
                System.out.print("Enter a number: ");
                int actorIndex = scan.nextInt();
                ArrayList<Movie> actorMovies = new ArrayList<>();
                for (Movie movie : movies) {
                    for (String member : movie.getCastMembers()) {
                        if (member.equals(list.get(actorIndex))) {
                            actorMovies.add(movie);
                        }
                    }
                }
                for (int i = 0; i < actorMovies.size(); i++) {
                    System.out.println((i + 1) + ". " + actorMovies.get(i));
                }
                System.out.println("What movie would you like to learn more about?");
                System.out.print("Enter number: ");
                int num = scan.nextInt();
                scan.nextLine();
                System.out.println(movieInformation(actorMovies, num));
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}