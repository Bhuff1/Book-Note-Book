package badgebook;

public class PhilosophyBook implements BadgeBook {
	private String title;
	private String author;
	
	public PhilosophyBook(String title, String author) {
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