package badgebook;

public class PoliticsBook implements BadgeBook {
	private String title;
	private String author;
	
	public PoliticsBook(String title, String author) {
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
