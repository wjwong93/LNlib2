package lib.ln.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String title;
    private LocalDate releaseDate;
    private DateTimeFormatter inputFormat, outputFormat;
    private boolean inLibrary;
    private boolean completed;
    private String bookCoverSrc;

    public String getBookCoverSrc() {
        return bookCoverSrc;
    }

    public void setBookCoverSrc(String bookCoverSrc) {
        this.bookCoverSrc = bookCoverSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = LocalDate.parse(releaseDate, inputFormat);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = LocalDate.parse(releaseDate.format(inputFormat));
    }

    public boolean isInLibrary() {
        return inLibrary;
    }

    public void setInLibrary(boolean inLibrary) {
        this.inLibrary = inLibrary;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Book(String title, String releaseDate, String inLibrary, String completed, String bookCoverSrc) {
        this.title = title;
        this.inputFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
//        this.inputFormat = DateTimeFormatter.ofPattern("MMMM d, uuuu");
        this.outputFormat = DateTimeFormatter.ofPattern("MMMM dd, uuuu");
        this.releaseDate = LocalDate.parse(releaseDate, inputFormat);
        this.inLibrary = Boolean.parseBoolean(inLibrary);
        this.completed = Boolean.parseBoolean(completed);
        this.bookCoverSrc = bookCoverSrc;
    }

    public String printReleaseDate() {
        return releaseDate.format(outputFormat);
    }

    public String toJSON() {
        return "{title: " + title +
                ", release-date: " + releaseDate +
                ", in-library: " + inLibrary +
                ", completed: " + completed +
                ", book-cover-source: " + bookCoverSrc + "}";
    }

    public String toCSV() {
        return title + ", "
                + releaseDate + ", "
                + inLibrary + ", "
                + completed + ", "
                + bookCoverSrc;
    }
}
