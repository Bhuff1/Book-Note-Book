package book;

import java.sql.Date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ReviewedBook extends Book {
	
	private int userId;
	private Date dateRead;
	private int rating;
	private String review;
	
	public ReviewedBook() { super(); }
	
	public ReviewedBook(int userId, String title, String author, String genre, String isbn, String dateRead, int rating, String review) {
		super(title, author, genre, isbn);
		setUserId(userId);
		setDateRead(dateRead);
		setRating(rating);
		setReview(review);
	}
	
	private void setUserId(int userId) {
		this.userId = userId;
	}
	
	private void setDateRead(String dateRead) {
		LocalDateTime ldt = LocalDateTime.parse(dateRead, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss") );	
		long millisecs = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		this.dateRead = new Date(millisecs);
	}
	
	private void setRating(int rating) {
		this.rating = rating;
	}
	
	private void setReview(String review) {
		this.review = review;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public Date getDateRead() {
		return dateRead;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getReview() {
		return review;
	}
	
	
}
