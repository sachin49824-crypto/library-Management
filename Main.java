import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// 1. BOOK ENTITY CLASS
// ==========================================
class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return
         isIssued; }
    public void setIssued(boolean issued) { this.isIssued = issued; }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Title: " + title + " | Author: " + author + 
               " | Status: " + (isIssued ? "Borrowed" : "Available");
    }
}

// ==========================================
// 2. MEMBER ENTITY CLASS
// ==========================================
class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    @Override
    public String toString() {
        return "Member ID: " + memberId + " | Name: " + name + " | Books Held: " + borrowedBooks.size();
    }
}

// ==========================================
// 3. LIBRARY BUSINESS LOGIC SYSTEM
// ==========================================
class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println(" Book registered successfully!");
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println(" Member registered successfully!");
    }

    public void viewCatalog() {
        if (books.isEmpty()) {
            System.out.println(" No books available in the inventory.");
            return;
        }
        System.out.println("\n--- Current Library Catalog ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) return b;
        }
        return null;
    }

    private Member findMember(String id) {
        for (Member m : members) {
            if (m.getMemberId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    public void issueBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println(" Error: Book ISBN code not found.");
        } else if (member == null) {
            System.out.println(" Error: Library Member ID not found.");
        } else if (book.isIssued()) {
            System.out.println(" Error: Book is checked out by someone else.");
        } else {
            book.setIssued(true);
            member.borrowBook(book);
            System.out.println(" Success: '" + book.getTitle() + "' issued to " + member.getName() + ".");
        }
    }

    public void returnBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println(" Error: Invalid Book ISBN or Member ID entry.");
            return;
        }

        if (member.getBorrowedBooks().contains(book)) {
            book.setIssued(false);
            member.returnBook(book);
            System.out.println(" Success: Book returned back to active inventory.");
        } else {
            System.out.println(" Error: This specific member did not rent this book.");
        }
    }
}

// ==========================================
// 4. MAIN USER INTERFACE LOOP
// ==========================================
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Pre-populating inventory sample tracking data
        library.addBook(new Book("101", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("102", "Clean Code", "Robert Martin"));
        library.registerMember(new Member("M01", "Alice Smith"));

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Catalog Add Book");
            System.out.println("2. Profile Register Member");
            System.out.println("3. View Inventory Status");
            System.out.println("4. Book Borrow Transaction");
            System.out.println("5. Book Return Transaction");
            System.out.println("6. Shutdown System");
            System.out.print("Enter choice (1-6): ");

            int decision;
            try {
                decision = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Entry Error: Please input an integer choice.");
                continue;
            }

            switch (decision) {
                case 1:
                    System.out.print("Provide ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Provide Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Provide Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(isbn, title, author));
                    break;
                case 2:
                    System.out.print("Provide Unique ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Provide Name: ");
                    String name = scanner.nextLine();
                    library.registerMember(new Member(id, name));
                    break;
                case 3:
                    library.viewCatalog();
                    break;
                case 4:
                    System.out.print("Scan Book ISBN: ");
                    String bIsbn = scanner.nextLine();
                    System.out.print("Scan Member ID: ");
                    String mId = scanner.nextLine();
                    library.issueBook(bIsbn, mId);
                    break;
                case 5:
                    System.out.print("Scan Return ISBN: ");
                    String rIsbn = scanner.nextLine();
                    System.out.print("Scan Member ID: ");
                    String rId = scanner.nextLine();
                    library.returnBook(rIsbn, rId);
                    break;
                case 6:
                    System.out.println(" Closing execution terminal loop. System stopped.");
                    scanner.close();
                    return;
                default:
                    System.out.println(" Invalid choice index. Range 1 to 6 allowed.");
            }
        }
    }
}


