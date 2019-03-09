package service;

import badgebook.BadgeBook;
import badgebook.BadgeBookManager;
import utilities.Utility;
import book.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import formobjects.ReviewedBookForm;
import formobjects.WishListForm;

public class BookService {
	
	private static Logger log = Logger.getLogger(BookService.class.getName());

 	private static final String DB_DRIVER = "org.h2.Driver";
    
    private String connectionURL = "jdbc:h2:mem:example-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private String userName = "sa";
    private String password = "sa";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    static  {
        try {
            Class.forName(DB_DRIVER);
            
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public BookService() {
    	
    	try {
            Class.forName(DB_DRIVER);      
        } catch (Exception ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
     
    public void dropTables() {
    	try {
    	Connection connection = getConnection();
    	PreparedStatement stmt = connection.prepareStatement("DROP TABLE RecordedBooks");
    	stmt.executeUpdate();
    	connection.commit();
    	stmt = connection.prepareStatement("DROP TABLE WishList");
    	stmt.executeUpdate();
    	connection.commit();
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
    public void createTables() {
    	try {
    	Connection connection = getConnection();
    	PreparedStatement stmt = connection.prepareStatement("CREATE TABLE WishList (\r\n" + 
        		"	USER_ID int NOT NULL,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	GENRE varchar(100),\r\n" + 
        		"	ISBN bigint,\r\n" + 
        		"\r\n" + 
        		"	PRIMARY KEY (USER_ID, TITLE, AUTHOR)\r\n" + 
        		");");
    	
        stmt.executeUpdate();
        connection.commit();
        
        
        stmt = connection.prepareStatement("CREATE TABLE RecordedBooks (\r\n" + 
        		"	USER_ID int NOT NULL,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	GENRE varchar(100),\r\n" + 
        		"	ISBN varchar(50),\r\n" + 
        		"	DATE_READ Date,\r\n" + 
        		"	RATING int,\r\n" +
        		"	REVIEW varchar(2000),\r\n" +
        		"\r\n" + 
        		"	PRIMARY KEY (USER_ID, TITLE, AUTHOR)\r\n" + 
        		")");
        
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Users (\r\n" + 
        		"	LOGIN_ID varchar(50) NOT NULL,\r\n" + 
        		"	PASSWORD varchar(50) NOT NULL,\r\n" + 
        		"	USER_ID int NOT NULL,\r\n" + 
        		"\r\n" + 
        		"	PRIMARY KEY (LOGIN_ID)\r\n" + 
        		");");
    	
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Autobiography (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Biography (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Biology (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Chemistry (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Classics (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Economics (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Geography (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE History (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Philosophy (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Physics (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        stmt = connection.prepareStatement("CREATE TABLE Politics (\r\n" + 
        		"	BOOK_ID int NOT NULL AUTO_INCREMENT,\r\n" + 
        		"	TITLE varchar(250) NOT NULL,\r\n" + 
        		"	AUTHOR varchar(150) NOT NULL,\r\n" + 
        		"	PRIMARY KEY (BOOK_ID)\r\n" + 
        		")");
        
        stmt.executeUpdate();
        connection.commit();
        
        ArrayList<String> badgeTableNames = new ArrayList<>();
        badgeTableNames.add("Autobiography");
        badgeTableNames.add("Biography");
        badgeTableNames.add("Biology");
        badgeTableNames.add("Chemistry");
        badgeTableNames.add("Classics");
        badgeTableNames.add("Economics");
        badgeTableNames.add("Geography");
        badgeTableNames.add("History");
        badgeTableNames.add("Philosophy");
        badgeTableNames.add("Physics");
        badgeTableNames.add("Politics");
        
        for(String category : badgeTableNames) {
        	insertIntoBookBadgeTables(category);
        }
        
        stmt.close();
        connection.close();
    	} catch (SQLException e) {
    		System.err.println(e.getMessage());
    	}
    	
    }
    
    public void seedDatabase() {
    
    	try {
    		Connection connection = getConnection();
    		PreparedStatement stmt = connection.prepareStatement("INSERT INTO WishList(USER_ID, TITLE, AUTHOR, GENRE, ISBN) VALUES (?, ?, ?, ?, ?)");

    		WishListBook b1 = new WishListBook(1, "Fear and Loathing in Las Vegas: A Savage Journey to the Heart of the American Dream", "Hunter S. Thompson", "Gonzo Journalis",  "9780679785897");
    		WishListBook b2 = new WishListBook(1, "The Silkworm", "Robert Galbraith", "Crime fiction",  "9780316206891");
    		WishListBook b3 = new WishListBook(1, "War and Peace", "Leo Tolstoy", "Historical fiction",  "9780486816432");
            
            ArrayList<WishListBook> wishList = new ArrayList<>();
            wishList.add(b1);
            wishList.add(b2);
            wishList.add(b3);
            
            for(WishListBook b : wishList) {
            	stmt.setInt(1, b.getUserId());
            	stmt.setString(2, b.getTitle());
            	stmt.setString(3,  b.getAuthor());
            	stmt.setString(4, b.getGenre());
            	stmt.setString(5,  b.getIsbn());
            	stmt.executeUpdate();
            }
            
            connection.commit();
            
            stmt = connection.prepareStatement("INSERT INTO Users (LOGIN_ID, PASSWORD, USER_ID) VALUES (?, ?, ?)");
            
            stmt.setString(1, "demo");
        	stmt.setString(2, "demo");
        	stmt.setInt(3, 1);
        	stmt.executeUpdate();
            
            
            stmt = connection.prepareStatement("INSERT INTO RecordedBooks (USER_ID, TITLE, AUTHOR, GENRE, ISBN, DATE_READ, RATING, REVIEW) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            ArrayList<ReviewedBook> recordedList = new ArrayList<>();
            String harryPotterReview = "I read this book when I was in middle school. If I remember correctly, it was lent to me by " +
            							"a friend. I've re-read it multiple times since then. Even though I've changed as a person, " +
            							"the things that make the book enjoyable and meaningful to me haven't changed. J.K. Rowling " +
            							"has a way of crafting characters and putting them on a path that makes you feel what they feel--" +
            							"when they have a victory, you celebrate with them; when they laugh, you laugh with them. The " +
            							"first book in the series introduces the wizarding world, and we get to experience it with Harry, " +
            							"The Boy Who Lived. When he arrives at Hogwarts, he finds friends, challenges, and (some) answers " +
            							"about how and why he ended up living with his awful Aunt and Uncle.";
            String animalFarmReview = "This took me longer than it should have to read; about 3 weeks. This worked, in a way, " +
            							"though, because the narrative itself is a few words over at least a decade. The metaphors " +
            							"are powerful and clear. The enshrinement of a set of commands being eroded so gradually " +
            							"and cleverly, sometimes with threats, to the point where everyone knows something is wrong " +
            							"but is either too tired, afraid, or ignorant to act. The fact that the animals' victory over " +
            							"Farmer Jones resulted in animals becoming dictators and thugs, is a sad reminder that in every " +
            							"one of us are elements of the worst of us.";
            String americanPastoralReview = "A deep, painful, honest, introspective, surprising, and at times, shocking " +
					"novel. The Swede is a guy you think you know, but you really don't; the guy was painfully self-aware " +
            		"and had been thrown on the rocks by his daughters actions, his wife's infidelity, and the inner turmoil " +
					" it produced. Reading this made me want to see my parents and tell them I love them: to call my fiance " +
            		"and make sure she's ok. Certainly not a fun read, but I read it in less than a week. It's a hard book " +
					"to describe other than to say it was intense, emotional, and real.";
            String oneHundredYearsOfSolitudeReview = "After reading some reviews and some contextual information about the book " +
					"and the author, my response to this book would be that it's original, entertaining in a weird way, strange, " +
            		"poetic, and at times beautiful. Marquez said he wrote the story in the style, or voice, of how his grandmother " +
					"told stories, where you weren't sure what was true and what was false because she'd say the extraordinary and the " +
            		"ordinary in the same voice, and simply at that. This is how the whole novel is written. Characters describe unusual things " +
					", like ghosts visiting regularly, or a young woman ascending to heavevn with a sheet, in the same plain-spoken language " +
            		"in which they would describe a tree. I mainly viewed the story as a series of surreal, scandalous, or humorous episodes, some " +
					"of them boring, some of them fascinating.";
            ReviewedBook b4 = new ReviewedBook(1, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", "9781408883747", "2001/07/20 00:00:00", 5, harryPotterReview);
            ReviewedBook b5 = new ReviewedBook(1, "Animal Farm", "George Orwell", "Fiction", "0451526341", "2018/06/05 00:00:00", 4, animalFarmReview);
            ReviewedBook b6 = new ReviewedBook(1, "American Pastoral", "Philip Roth", "Fiction", "9780375701429", "2015/05/23 00:00:00", 4, americanPastoralReview);
            ReviewedBook b7 = new ReviewedBook(1, "One Hundred Years of Solitude", "Gabriel Garcia Marquez", "Magical Realism", "9780679444657", "2017/09/03 00:00:00", 3, oneHundredYearsOfSolitudeReview);
            recordedList.add(b4);
            recordedList.add(b5);
            recordedList.add(b6);
            recordedList.add(b7);
            
            for(ReviewedBook b : recordedList) {
            	stmt.setInt(1, b.getUserId());
            	stmt.setString(2, b.getTitle());
            	stmt.setString(3,  b.getAuthor());
            	stmt.setString(4, b.getGenre());
            	stmt.setString(5, b.getIsbn());
            	stmt.setDate(6, b.getDateRead());
            	stmt.setInt(7, b.getRating());
            	stmt.setString(8, b.getReview());
            	stmt.executeUpdate();
            }

            connection.commit();
            
            stmt.close();
            connection.close();
            
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
    public boolean isAuthenticated(String loginId, String password) {
    	ResultSet rs;
    	try {
    		Connection connection = getConnection();
	    	Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM Users WHERE LOGIN_ID = '" + loginId + "' AND PASSWORD = '" + password + "'");
    		
    		if(rs.next()) 
    			return true;
    			
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    	return false;
    }
    
    private void insertIntoBookBadgeTables(String category) {
    	try {
    		Connection connection = getConnection();
    		PreparedStatement stmt = connection.prepareStatement("INSERT INTO `" + category + "` (TITLE, AUTHOR) VALUES (?, ?)");
    		ArrayList<BadgeBook> badgeBookList = BadgeBookManager.getInstance().getBookBadgeList(category);
    		
    		for(BadgeBook book : badgeBookList) {
            	stmt.setString(1, book.getTitle());
            	stmt.setString(2, book.getAuthor());
            	stmt.executeUpdate();
            }
            connection.commit();  
            stmt.close();
            connection.close();
    	}
    	catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
    public ResultSet getBadgesForBooks(String tableName, String title, String author) {
    	ResultSet rs;
    	try {
    		Connection connection = getConnection();
    		Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM " + tableName + " WHERE TITLE = '" 
									+ title + "' AND AUTHOR = '" + author + "'");
			
			return rs;
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}  
    	return null;
    }
    
    public boolean hasWishListBook(String isbn, int userId) {
    	ResultSet rs;
    	try {
    		Connection connection = getConnection();
	    	Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM WishList WHERE ISBN = " + isbn + " AND USER_ID = " + userId);
    		
    		if(rs.next()) 
    			return true;
    			
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    	return false;
    }
    
    public ArrayList<WishListBook> getWishListBooks(int userId){
    	ResultSet rs = null;
    	ArrayList<WishListBook> wishListBooks = new ArrayList<>();
    	
    	try {
    		Connection connection = getConnection();
	    	Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM WishList WHERE USER_ID = " + userId);
    		while(rs.next()) {
    			wishListBooks.add(new WishListBook(userId, rs.getString("TITLE"), rs.getString("AUTHOR"),
        				rs.getString("GENRE"), rs.getString("ISBN")));
    		}
    		
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    	return wishListBooks;
    }
 
    public void addToWishList(WishListForm wlf) {
    	try {
    		
    		String title = wlf.getTitle();
    	    
    		if(title.indexOf(',') > -1 ) {
    			CharSequence s1 = ",,,,";
    		    CharSequence s2 = "";
    			title = title.replace(s1, s2);
    			title = title.replace(',', ' ');
    			title = title.trim();
    		}
    		
    		if(hasWishListBook(wlf.getIsbn(), wlf.getUserId())) {
    			updateWishList(wlf);
    		}
    		else {
    			Connection connection = getConnection();
    			PreparedStatement stmt = connection.prepareStatement("INSERT INTO WishList (USER_ID, TITLE, AUTHOR, GENRE, ISBN) VALUES (?, ?, ?, ?, ?)");
    			stmt.setInt(1, wlf.getUserId());
    			stmt.setString(2, wlf.getTitle());
    			stmt.setString(3, wlf.getAuthor());
    			stmt.setString(4, wlf.getGenre());
    			stmt.setString(5, wlf.getIsbn());
    			stmt.executeUpdate();
    			connection.commit();
    		}
    	} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
    }
    
    public void updateWishList(WishListForm wlf) {
    	try {
	    	Connection connection = getConnection();
	    	PreparedStatement stmt = connection.prepareStatement("UPDATE WishList " +
	    															"SET TITLE = ?," +
	    															"AUTHOR = ?," +
	    															"GENRE = ? " +
	    															"WHERE ISBN = ? AND USER_ID = ?");
    	
    	
	    	stmt.setString(1, Utility.removeCommas(wlf.getTitle()));
	    	stmt.setString(2,  Utility.removeCommas(wlf.getAuthor()));
	    	stmt.setString(3, Utility.removeCommas(wlf.getGenre()));
	    	stmt.setString(4,  Utility.removeCommas(wlf.getIsbn()));
	    	stmt.setInt(5,  wlf.getUserId());
	    	stmt.executeUpdate();
	    	connection.commit();
	    	connection.close();	
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    } 
    
    public void deleteFromWishList(String isbn, int userId) {
    	try {
	    	Connection connection = getConnection();
	    	PreparedStatement stmt = connection.prepareStatement("DELETE FROM WishList WHERE ISBN = ? AND USER_ID = ?");
    
	    	stmt.setString(1,  isbn);
	    	stmt.setInt(2, userId);
	    	stmt.executeUpdate();
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }  
    
    public boolean hasRecordedBook(String isbn, int userId) {
    		
    	ResultSet rs;
    	
    	try {
    		Connection connection = getConnection();
	    	Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM RecordedBooks WHERE ISBN = '" 
    									+ isbn + "' AND USER_ID = " + userId);
    		
    		if(rs.next()) 
    			return true;
    			
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    	return false;
    }
    
    public ArrayList<ReviewedBook> getRecordedBooks(int userId){
    	ResultSet rs = null;
    	ArrayList<ReviewedBook> reviewedBooks = new ArrayList<>();
    	
    	try {
    		Connection connection = getConnection();
	    	Statement statement = connection.createStatement();
    		rs = statement.executeQuery("SELECT * FROM RecordedBooks WHERE USER_ID = " + userId);
    		
    		
    		while(rs.next()) {
    			String date = Utility.formatDate(rs.getDate("DATE_READ").toString());
    			
    			reviewedBooks.add(new ReviewedBook(userId, rs.getString("TITLE"), rs.getString("AUTHOR"),
        				rs.getString("GENRE"), rs.getString("ISBN"), date, Integer.parseInt(rs.getString("RATING")), rs.getString("REVIEW")));
    		}
    		
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    	return reviewedBooks;
    }
    
    public void addToRecordedBooks(ReviewedBookForm rbf) {
    	try {
    		
    		String title = rbf.getTitle();
    
    		if(title.indexOf(',') > -1 ) {
    			CharSequence s1 = ",,,,";
    		    CharSequence s2 = "";
    			title = title.replace(s1, s2);
    			title = title.replace(',', ' ');
    			title = title.trim();
    		}
    		
    		if(hasRecordedBook(rbf.getIsbn(), rbf.getUserId())) {
    			updateRecordedBooks(rbf);
    		}
    		else {
    			Connection connection = getConnection();
    			PreparedStatement stmt = connection.prepareStatement("INSERT INTO RecordedBooks (USER_ID, TITLE, AUTHOR, GENRE, ISBN, DATE_READ, RATING, REVIEW) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    			stmt.setInt(1, rbf.getUserId());
    			stmt.setString(2, rbf.getTitle());
    			stmt.setString(3,  rbf.getAuthor());
    			stmt.setString(4, rbf.getGenre());
    			stmt.setString(5, rbf.getIsbn());
        	
    			String unformattedDateRead = rbf.getDateRead().trim();
    			String formattedDate = Utility.formatDate(unformattedDateRead);
    			LocalDateTime ldt = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss") );	
    			long millisecs = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    			Date dateRead = new Date(millisecs);
    	
    			stmt.setDate(6, dateRead);
    			stmt.setInt(7, rbf.getRating());
    			stmt.setString(8, rbf.getReview());
    			stmt.executeUpdate();
    			connection.commit();
    			connection.close();
    			BadgeService badgeService = new BadgeService();
    			badgeService.updateBadges(rbf.getUserId(), Utility.removeApostrophes(rbf.getTitle()), Utility.removeCommas(rbf.getAuthor()));
    		}
    	} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
    	
    }
    
    public void updateRecordedBooks(ReviewedBookForm rbf) {
    	try {
	    	Connection connection = getConnection();
    	
	    	PreparedStatement stmt = connection.prepareStatement("UPDATE RecordedBooks " + 
	    															"SET TITLE = ?, " +
	    															"AUTHOR = ?, " +
	    															"GENRE = ?, " + 
	    															"ISBN = ?, " +
	    															"DATE_READ = ?, " +
	    															"RATING = ?, " + 
	    															"REVIEW = ? " +
	    															"WHERE ISBN = ? AND USER_ID = ?");
	    	
	    	String unformattedDateRead = rbf.getDateRead().trim();
        	String formattedDate = Utility.formatDate(unformattedDateRead);
        	LocalDateTime ldt = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss") );	
    		long millisecs = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    		Date dateRead = new Date(millisecs);
	    	
	    	stmt.setString(1, Utility.removeCommas(rbf.getTitle()));
	    	stmt.setString(2, Utility.removeCommas(rbf.getAuthor()));
	    	stmt.setString(3,  Utility.removeCommas(rbf.getGenre()));
	    	stmt.setString(4, Utility.removeCommas(rbf.getIsbn()));
	    	stmt.setDate(5,  dateRead);
	    	stmt.setInt(6, rbf.getRating());
	    	stmt.setString(7, Utility.removeCommas(rbf.getReview()));
	    	stmt.setString(8, rbf.getIsbn());
	    	stmt.setInt(9, rbf.getUserId());
	    	stmt.executeUpdate();
	    	connection.commit();
	    	connection.close();
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
    public void deleteFromRecordedBooks(String isbn, int userId) {
    	try {
	    	Connection connection = getConnection();
	    	PreparedStatement stmt = connection.prepareStatement("DELETE FROM RecordedBooks WHERE ISBN = ? AND USER_ID = ?");
    
	    	stmt.setString(1,  isbn);
	    	stmt.setInt(2, userId);
	    	stmt.executeUpdate();
    	} catch (SQLException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
}