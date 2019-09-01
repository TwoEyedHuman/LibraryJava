
public class Book {
	// Instance variables
	String title;
	String author;
	int pages;
	String coverFileName;
	
	public Book(String title, String author, int pages) {
		this.title = title;
		this.author = author;
		this.pages = pages;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public int pageCount() {
		return this.pages;
	}
	
	public String toString() {
		String returnString = String.format("%s by %s with %d pages.", this.title, this.author, this.pages);
		return returnString;
	}
	
	public String toHTML() {
		String returnString = String.format("<html> %s <br> %s <br> %d pages", this.title, this.author, this.pages);
		return returnString;
	}
}
