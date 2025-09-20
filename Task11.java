import java.util.*;

public class Task11 {

}

class test{

    public static void main(String[] args) {
        
        Book b1 = new Book("Java Programming", "John Smith", 2021, 39.99);
        Book b2 = new Book("Python Basics", "Alice Johnson", 2019, 29.99);
        Book b3 = new Book("C++ Advanced", "Robert Brown", 2022, 49.99);

        System.out.println("Book 1:\n" + b1.toString() + "\n");
        System.out.println("Book 2:\n" + b2.toString() + "\n");
        System.out.println("Book 3:\n" + b3.toString() + "\n");
    }
}
    class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;

    public Book(String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Title: \"" + title + "\"\n" +
               "Author: \"" + author + "\"\n" +
               "Year Published: " + yearPublished + "\n" +
               "Price: $" + price;
    }
}
