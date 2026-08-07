import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class library {
    static class Book {
        int id;
        String title;
        String author;
        String genre;
        boolean available;
        String borrower;

        Book(int id, String title, String author, String genre) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.available = true;
            this.borrower = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Book> books = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Borrowed Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt(sc);

            switch (choice) {
                case 1 -> addBook(sc, books);
                case 2 -> viewBooks(books);
                case 3 -> searchBook(sc, books);
                case 4 -> borrowBook(sc, books);
                case 5 -> returnBook(sc, books);
                case 6 -> viewBorrowedBooks(books);
                case 7 -> {
                    System.out.println("Thank you for using the Library Management System!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner sc, List<Book> books) {
        System.out.print("Enter Book ID: ");
        int id = readInt(sc);

        for (Book book : books) {
            if (book.id == id) {
                System.out.println("Book ID already exists.");
                return;
            }
        }

        System.out.print("Enter Book Title: ");
        String title = readLine(sc);

        System.out.print("Enter Author Name: ");
        String author = readLine(sc);

        System.out.print("Enter Genre: ");
        String genre = readLine(sc);

        books.add(new Book(id, title, author, genre));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.println("ID: " + book.id +
                    " | Title: " + book.title +
                    " | Author: " + book.author +
                    " | Genre: " + book.genre +
                    " | Status: " + (book.available ? "Available" : "Borrowed"));
        }
    }

    private static void searchBook(Scanner sc, List<Book> books) {
        System.out.print("Enter title or author to search: ");
        String keyword = readLine(sc).toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword) || book.author.toLowerCase().contains(keyword)) {
                System.out.println("ID: " + book.id +
                        " | Title: " + book.title +
                        " | Author: " + book.author +
                        " | Genre: " + book.genre +
                        " | Status: " + (book.available ? "Available" : "Borrowed"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static void borrowBook(Scanner sc, List<Book> books) {
        System.out.print("Enter Book ID to borrow: ");
        int id = readInt(sc);

        for (Book book : books) {
            if (book.id == id) {
                if (!book.available) {
                    System.out.println("This book is already borrowed.");
                    return;
                }

                System.out.print("Enter borrower name: ");
                String borrower = readLine(sc);
                book.available = false;
                book.borrower = borrower;
                System.out.println("Book borrowed successfully!");
                return;
            }
        }

        System.out.println("Book ID not found.");
    }

    private static void returnBook(Scanner sc, List<Book> books) {
        System.out.print("Enter Book ID to return: ");
        int id = readInt(sc);

        for (Book book : books) {
            if (book.id == id) {
                if (book.available) {
                    System.out.println("This book is not currently borrowed.");
                    return;
                }

                book.available = true;
                book.borrower = null;
                System.out.println("Book returned successfully!");
                return;
            }
        }

        System.out.println("Book ID not found.");
    }

    private static void viewBorrowedBooks(List<Book> books) {
        boolean found = false;
        for (Book book : books) {
            if (!book.available) {
                System.out.println("ID: " + book.id +
                        " | Title: " + book.title +
                        " | Borrower: " + book.borrower);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No borrowed books at the moment.");
        }
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(readLine(sc));
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please try again: ");
            }
        }
    }

    private static String readLine(Scanner sc) {
        return sc.nextLine().trim();
    }
}