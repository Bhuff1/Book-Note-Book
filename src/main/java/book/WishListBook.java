package book;

public class WishListBook extends Book{

	private int userId;
	
	public WishListBook(int userId, String title, String author, String genre, String isbn) {
		super(title, author, genre, isbn);
		setUserId(userId);
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
}