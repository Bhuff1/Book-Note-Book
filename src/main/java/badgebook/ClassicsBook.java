package badgebook;

public class ClassicsBook implements BadgeBook{
	private String title;
	private String author;
	
	public ClassicsBook(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
}
