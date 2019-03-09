package badgebook;

public class BiologyBook implements BadgeBook {
	private String title;
	private String author;
	
	public BiologyBook(String title, String author) {
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
