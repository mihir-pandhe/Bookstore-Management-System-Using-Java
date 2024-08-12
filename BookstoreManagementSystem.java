import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("3. Exit");
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
        } else {
            System.out.println("Book List:");
            for (Book book : books) {
                System.out.println(book);
            }
            System.out.println();
        }
    }
}
