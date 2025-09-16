import java.util.Scanner;

public class BookTest {
    public static void main(String[] args) {
        Book[] books = new Book[5];
        books[0] = new Book("Java");
        books[1] = new Book("C++");
        books[2] = new Book("Python");
        books[3] = new Book("Javascript");
        books[4] = new Book("C#");

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Please select operation: " +
                    "1. Borrow. 2. Return. 3. Overdue. 0. To Stop the process");
            int op = in.nextInt();
            if (op == 0)
                break;
            switch (op) {
                case 1:
                    borrowBook(in, books);
                    break;
                case 2:
                    returnBook(in, books);
                    break;
                case 3:
                    overdueAll(books);
                    break;
            }
        }
    }

    public static void borrowBook(Scanner in, Book[] books) {
        for (Book b : books) {
            if (b.getStatus() == BookStatus.IDLE)
                System.out.println(b);
        }
        System.out.println("Please input the book id:");
        int id = in.nextInt();
        Book book = books[id - 1];
        if (book.getStatus() == BookStatus.IDLE) {
            book.setStatus(BookStatus.BORROWED);
            System.out.printf("borrow %s successfully\n", book.getName());
        }
    }

    public static void returnBook(Scanner in, Book[] books) {
        for (Book b : books) {
            if (b.getStatus() != BookStatus.IDLE)
                System.out.println(b);
        }
        System.out.println("Please input the book id:");
        int id = in.nextInt();
        Book book = books[id - 1];
        if (book.getStatus() != BookStatus.IDLE) {
            book.setStatus(BookStatus.IDLE);
            System.out.printf("return %s successfully\n", book);
        }
    }

    public static void overdueAll(Book[] books) {
        System.out.println("Setting all borrowed book to overdue");
        for (Book b : books) {
            if (b.getStatus() == BookStatus.BORROWED) {
                b.setStatus(BookStatus.OVERDUE);
                System.out.println(b);
            }
        }
    }
}
