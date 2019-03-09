package badgebook;

public class PhysicsBook implements BadgeBook {
	private String title;
	private String author;
	
	public PhysicsBook(String title, String author) {
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
