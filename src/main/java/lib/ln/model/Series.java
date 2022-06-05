package lib.ln.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Series {
    private String name;
    private String author;
    private String illustrator;
    private List<Book> books;
    private String fileName;

    public Series(String title, String author, String illustrator, String fileName) {
        this.name = title;
        this.author = author;
        this.illustrator = illustrator;
        this.fileName = fileName;
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBook(Book newBook) {
        if (!books.contains(newBook)) {
            books.add(newBook);
            return true;
        } else {
            System.out.println("Book already exists");
            return false;
        }
    }

    public String toCSV() {
        StringBuilder result = new StringBuilder();
        result.append("name, author, illustrator\n");
        result.append(name + ", " + author + ", " + illustrator + "\n\n");

        result.append("title, release date, in library, completed, book cover source\n");

        for (Book book : books) {
            result.append(book.toCSV() + "\n");
        }

//        System.out.println(result);
        return result.toString();
    }

    public String toJSON() {
        StringBuilder result = new StringBuilder();
        result.append("{\n"
                + "name: " + name + ",\n"
                + "author: " + author + ",\n"
                + "illustrator: " + illustrator + ",\n"
                + "books: [\n");

        for (Book book : books) {
            result.append("\t" + book.toJSON() + "\n");
        }

        result.append("]\n}");

        System.out.println(result);
        return result.toString();
    }

    public void toFile() {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName, false))
        )) {
            out.write(toCSV());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Series load(String fileName) {
        Series series = null;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            String[] seriesInfo;
            String[] bookInfo;

            in.readLine();
            line = in.readLine();

            seriesInfo = line.split(",");
            for (int i=0; i< seriesInfo.length; i++) {
                seriesInfo[i] = seriesInfo[i].strip();
//                System.out.println(seriesInfo[i]);
            }
            series = new Series(seriesInfo[0], seriesInfo[1], seriesInfo[2], fileName);

            in.readLine();
            in.readLine();

            while ((line = in.readLine()) != null) {
                bookInfo = line.split(",");
                for (int i=0; i<bookInfo.length; i++) {
                    bookInfo[i] = bookInfo[i].strip();
//                    System.out.println(bookInfo[i]);
                }
                series.addBook(new Book(bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3], bookInfo[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return series;
    }

    public int inLibCount() {
        int count = 0;
        for (Book book : books)
            if (book.isInLibrary()) count++;
        return count;
    }

    public int completeCount() {
        int count = 0;
        for (Book book : books)
            if (book.isCompleted()) count++;
        return count;
    }
}
