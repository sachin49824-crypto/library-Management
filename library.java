import java.util.Scanner;

class Ticket {
    int id;
    String name;
    String destination;

    Ticket(int id, String name, String destination) {
        this.id = id;
        this.name = name;
        this.destination = destination;
    }
}

public class TicketManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Ticket[] tickets = new Ticket[100];
        int count = 0;

        while (true) {
            System.out.println("\n===== Ticket Management System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Tickets");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();

                    tickets[count] = new Ticket(id, name, destination);
                    count++;

                    System.out.println("Ticket Booked Successfully!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No Tickets Booked.");
                    } else {
                        System.out.println("\nBooked Tickets:");
                        for (int i = 0; i < count; i++) {
                            System.out.println("Ticket ID: " + tickets[i].id);
                            System.out.println("Passenger: " + tickets[i].name);
                            System.out.println("Destination: " + tickets[i].destination);
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Ticket ID to Cancel: ");
                    int cancelId = sc.nextInt();

                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (tickets[i].id == cancelId) {
                            for (int j = i; j < count - 1; j++) {
                                tickets[j] = tickets[j + 1];
                            }
                            tickets[count - 1] = null;
                            count--;
                            found = true;
                            System.out.println("Ticket Cancelled Successfully!");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Ticket ID Not Found.");
                    }
                    break;

                case 4:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}