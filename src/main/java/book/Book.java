package book;

import exception.ISBNFormatException;

public abstract class Book {
	private String title;
	private String author;
	private String genre;
	private String isbn;
	
	public Book() {}
	
	public Book(String title, String author, String genre, String isbn) {
		setTitle(title);
		setAuthor(author);
		setGenre(genre);
		setIsbn(isbn);
	}
	
	private void setTitle(String title) {
		try {
			this.title = title;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void setAuthor(String author) {
		try {
			this.author = author;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void setGenre(String genre) {
		try {
			this.genre = genre;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void setIsbn(String isbn) {
		try {
			
			
			if(isbn.indexOf("-") > -1) {
				String[] isbnArr = isbn.split("-", 13);
				StringBuilder isbnStringSB = new StringBuilder();
				for(int i = 0; i < isbnArr.length; i++)
					isbnStringSB.append(isbnArr[i]);
				isbn = isbnStringSB.toString();
			}
			
			if(isCorrectLength(isbn)) 
				this.isbn = isbn;
					
		} 
		catch (ISBNFormatException e) {
			System.err.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			
		}
		catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	private boolean isCorrectLength(String isbn) throws ISBNFormatException {
		if(isbn.length() == 10 || isbn.length() == 13)
			return true;
		else
			throw new ISBNFormatException();
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getIsbn() {
		return isbn;
	}

}
