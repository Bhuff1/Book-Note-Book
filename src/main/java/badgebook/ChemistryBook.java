package badgebook;

public class ChemistryBook implements BadgeBook {
	private String title;
	private String author;
	
	public ChemistryBook(String title, String author) {
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
