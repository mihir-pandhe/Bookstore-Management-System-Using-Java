import java.util.*;
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
        return String.format("Title: %s, Author: %s, Genre: %s, Price: $%.2f, Quantity: %d", title, author, genre,
                price, quantity);
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
        return String.format("Book: %s, Quantity: %d, Total Price: $%.2f, Payment Method: %s, Date: %s", bookTitle,
                quantity, totalPrice, paymentMethod, date);
    }
}

public class BookstoreManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Book> books = new ArrayList<>();
    private static final List<Transaction> transactions = new ArrayList<>();

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
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBooks();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> sellBook();
                case 7 -> viewTransactionHistory();
                case 8 -> {
                    System.out.println("Exiting the system.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
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

        books.add(new Book(title, author, genre, price, quantity));
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

        Comparator<Book> comparator = switch (sortOption) {
            case 1 -> Comparator.comparing(Book::getTitle);
            case 2 -> Comparator.comparing(Book::getAuthor);
            case 3 -> Comparator.comparing(Book::getGenre);
            case 4 -> Comparator.comparingDouble(Book::getPrice);
            default -> null;
        };

        if (comparator != null) {
            books.sort(comparator);
        } else {
            System.out.println("Invalid option. Displaying unsorted list.\n");
        }

        books.forEach(System.out::println);
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
            case 1 -> searchByField(Book::getTitle, "Enter Book Title: ");
            case 2 -> searchByField(Book::getAuthor, "Enter Book Author: ");
            case 3 -> searchByField(Book::getGenre, "Enter Book Genre: ");
            case 4 -> searchByPriceRange();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private static void searchByField(java.util.function.Function<Book, String> fieldGetter, String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();

        List<Book> result = books.stream()
                .filter(book -> fieldGetter.apply(book).equalsIgnoreCase(value))
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
            result.forEach(System.out::println);
            System.out.println();
        }
    }

    private static void updateBook() {
        System.out.print("Enter the title of the book to update: ");
        String title = scanner.nextLine();

        Optional<Book> bookToUpdateOpt = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (bookToUpdateOpt.isEmpty()) {
            System.out.println("Book not found.\n");
            return;
        }

        Book bookToUpdate = bookToUpdateOpt.get();

        updateField(bookToUpdate::setTitle, "Enter new Title (leave blank to keep current): ");
        updateField(bookToUpdate::setAuthor, "Enter new Author (leave blank to keep current): ");
        updateField(bookToUpdate::setGenre, "Enter new Genre (leave blank to keep current): ");
        updatePrice(bookToUpdate::setPrice, "Enter new Price (enter -1 to keep current): ");
        updateQuantity(bookToUpdate::setQuantity, "Enter new Quantity (enter -1 to keep current): ");

        System.out.println("Book updated successfully.\n");
    }

    private static void updateField(java.util.function.Consumer<String> setter, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            setter.accept(input);
        }
    }

    private static void updatePrice(java.util.function.Consumer<Double> setter, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            try {
                double price = Double.parseDouble(input);
                if (price >= 0) {
                    setter.accept(price);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Keeping current price.");
            }
        }
    }

    private static void updateQuantity(java.util.function.Consumer<Integer> setter, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            try {
                int quantity = Integer.parseInt(input);
                if (quantity >= 0) {
                    setter.accept(quantity);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Keeping current quantity.");
            }
        }
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

        Optional<Book> bookToSellOpt = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (bookToSellOpt.isEmpty()) {
            System.out.println("Book not found.\n");
            return;
        }

        Book bookToSell = bookToSellOpt.get();

        System.out.print("Enter quantity to sell: ");
        int quantityToSell = scanner.nextInt();
        scanner.nextLine();

        if (quantityToSell > bookToSell.getQuantity()) {
            System.out.println("Not enough stock.\n");
            return;
        }

        bookToSell.setQuantity(bookToSell.getQuantity() - quantityToSell);

        double totalPrice = quantityToSell * bookToSell.getPrice();

        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        transactions.add(new Transaction(title, quantityToSell, totalPrice, paymentMethod));

        System.out.println("Book sold successfully. Total price: $" + totalPrice + "\n");
    }

    private static void viewTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.\n");
        } else {
            transactions.forEach(System.out::println);
            System.out.println();
        }
    }
}
