import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    public Book(String title, String author, String genre, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Price: $" + price + ", Quantity: "
                + quantity;
    }
}

class Transaction {
    private String bookTitle;
    private int quantity;
    private double totalPrice;
    private String paymentMethod;
    private Date date;

    public Transaction(String bookTitle, int quantity, double totalPrice, String paymentMethod) {
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Book: " + bookTitle + ", Quantity: " + quantity + ", Total Price: $" + totalPrice + ", Payment Method: "
                + paymentMethod + ", Date: " + date;
    }
}

public class BookstoreManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bookstore Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Books");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Sell Book");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");
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
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    sellBook();
                    break;
                case 7:
                    viewTransactionHistory();
                    break;
                case 8:
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

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, author, genre, price, quantity);
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

    private static void updateBook() {
        System.out.print("Enter the title of the book to update: ");
        String title = scanner.nextLine();

        Book bookToUpdate = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (bookToUpdate == null) {
            System.out.println("Book not found.\n");
            return;
        }

        System.out.print("Enter new Title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            bookToUpdate.setTitle(newTitle);
        }

        System.out.print("Enter new Author (leave blank to keep current): ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.isEmpty()) {
            bookToUpdate.setAuthor(newAuthor);
        }

        System.out.print("Enter new Genre (leave blank to keep current): ");
        String newGenre = scanner.nextLine();
        if (!newGenre.isEmpty()) {
            bookToUpdate.setGenre(newGenre);
        }

        System.out.print("Enter new Price (enter -1 to keep current): ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        if (newPrice != -1) {
            bookToUpdate.setPrice(newPrice);
        }

        System.out.print("Enter new Quantity (enter -1 to keep current): ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine();
        if (newQuantity != -1) {
            bookToUpdate.setQuantity(newQuantity);
        }

        System.out.println("Book updated successfully.\n");
    }

    private static void deleteBook() {
        System.out.print("Enter the title of the book to delete: ");
        String title = scanner.nextLine();

        boolean removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));

        if (removed) {
            System.out.println("Book deleted successfully.\n");
        } else {
            System.out.println("Book not found.\n");
        }
    }

    private static void sellBook() {
        System.out.print("Enter the title of the book to sell: ");
        String title = scanner.nextLine();

        Book bookToSell = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (bookToSell == null) {
            System.out.println("Book not found.\n");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int quantityToSell = scanner.nextInt();
        scanner.nextLine();

        if (quantityToSell > bookToSell.getQuantity()) {
            System.out.println("Not enough stock available.\n");
            return;
        }

        bookToSell.setQuantity(bookToSell.getQuantity() - quantityToSell);
        double totalPrice = quantityToSell * bookToSell.getPrice();

        System.out.print("Enter payment method (Cash, Credit, Debit): ");
        String paymentMethod = scanner.nextLine();

        Transaction transaction = new Transaction(bookToSell.getTitle(), quantityToSell, totalPrice, paymentMethod);
        transactions.add(transaction);

        System.out.println("Book sold successfully. Total Price: $" + totalPrice + "\n");
    }

    private static void viewTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.\n");
            return;
        }

        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println();
    }
}
