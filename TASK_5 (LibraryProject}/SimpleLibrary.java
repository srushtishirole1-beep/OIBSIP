import java.util.*;

class Book {
    int id;
    String title;
    boolean issued;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.issued = false;
    }
}

public class SimpleLibrary {
    static ArrayList<Book> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nLogin as (admin/user/exit): ");
            String who = sc.nextLine();

            if (who.equals("admin")) admin();
            else if (who.equals("user")) user();
            else break;
        }
    }

    // ---------------- ADMIN ----------------
    static void admin() {
        System.out.println("Admin: add/update/delete/show/back");

        while (true) {
            System.out.print("Action: ");
            String a = sc.nextLine();

            if (a.equals("add")) addBook();
            else if (a.equals("update")) updateBook();
            else if (a.equals("delete")) deleteBook();
            else if (a.equals("show")) show();
            else break;
        }
    }

    static void addBook() {
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Name: ");
        String n = sc.nextLine();
        list.add(new Book(id, n));
        System.out.println("Added.");
    }

    static void updateBook() {
        System.out.print("Id to update: ");
        int id = Integer.parseInt(sc.nextLine());
        for (Book b : list) {
            if (b.id == id) {
                System.out.print("New name: ");
                b.title = sc.nextLine();
                System.out.println("Updated.");
                return;
            }
        }
        System.out.println("Not found.");
    }

    static void deleteBook() {
        System.out.print("Id to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        Iterator<Book> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                System.out.println("Deleted.");
                return;
            }
        }
        System.out.println("Not found.");
    }

    // ---------------- USER ----------------
    static void user() {
        System.out.println("User: view/search/issue/return/back");

        while (true) {
            System.out.print("Action: ");
            String a = sc.nextLine();

            if (a.equals("view")) show();
            else if (a.equals("search")) search();
            else if (a.equals("issue")) issue();
            else if (a.equals("return")) giveBack();
            else break;
        }
    }

    static void show() {
        if (list.isEmpty()) {
            System.out.println("No books.");
            return;
        }
        for (Book b : list) {
            System.out.println(b.id + " - " + b.title + " - " +
                    (b.issued ? "Issued" : "Available"));
        }
    }

    static void search() {
        System.out.print("Word: ");
        String k = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Book b : list) {
            if (b.title.toLowerCase().contains(k)) {
                System.out.println(b.id + " " + b.title);
                found = true;
            }
        }
        if (!found) System.out.println("No result.");
    }

    static void issue() {
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Book b : list) {
            if (b.id == id) {
                if (!b.issued) {
                    b.issued = true;
                    System.out.println("Issued.");
                } else {
                    System.out.println("Already issued.");
                }
                return;
            }
        }
        System.out.println("Not found.");
    }

    static void giveBack() {
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Book b : list) {
            if (b.id == id) {
                b.issued = false;
                System.out.println("Returned.");
                return;
            }
        }
        System.out.println("Not found.");
    }
}