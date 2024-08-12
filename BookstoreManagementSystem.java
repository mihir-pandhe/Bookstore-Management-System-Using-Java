import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private String genre;
    private double price;

    public Book(String title, String author, String genre, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Price: $" + price;
    }
}

public class BookstoreManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bookstore Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Books");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Book Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Book Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Book newBook = new Book(title, author, genre, price);
        books.add(newBook);

        System.out.println("Book added successfully.\n");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.\n");
            return;
        }

        System.out.println("Sort by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Price");
        System.out.print("Select an option: ");

        int sortOption = scanner.nextInt();
        scanner.nextLine();

        switch (sortOption) {
            case 1:
                books.sort(Comparator.comparing(Book::getTitle));
                break;
            case 2:
                books.sort(Comparator.comparing(Book::getAuthor));
                break;
            case 3:
                books.sort(Comparator.comparing(Book::getGenre));
                break;
            case 4:
                books.sort(Comparator.comparingDouble(Book::getPrice));
                break;
            default:
                System.out.println("Invalid option. Displaying unsorted list.\n");
                break;
        }

        System.out.println("Book List:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();
    }

    private static void searchBooks() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Price Range");
        System.out.print("Select an option: ");

        int searchOption = scanner.nextInt();
        scanner.nextLine();

        switch (searchOption) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByAuthor();
                break;
            case 3:
                searchByGenre();
                break;
            case 4:
                searchByPriceRange();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void searchByTitle() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        List<Book> result = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        displaySearchResults(result);
    }

    private static void searchByAuthor() {
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        List<Book> result = books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());

        displaySearchResults(result);
    }

    private static void searchByGenre() {
        System.out.print("Enter Book Genre: ");
        String genre = scanner.nextLine();

        List<Book> result = books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());

        displaySearchResults(result);
    }

    private static void searchByPriceRange() {
        System.out.print("Enter Minimum Price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter Maximum Price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Book> result = books.stream()
                .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        displaySearchResults(result);
    }

    private static void displaySearchResults(List<Book> result) {
        if (result.isEmpty()) {
            System.out.println("No books found.\n");
        } else {
            System.out.println("Search Results:");
            for (Book book : result) {
                System.out.println(book);
            }
            System.out.println();
        }
    }
}
