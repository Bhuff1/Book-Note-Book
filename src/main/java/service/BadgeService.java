package service;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bson.Document;
import badgebook.BadgeObject;

public class BadgeService {
	
    private MongoClient mongoClient;
    private MongoCollection<Document> db;

    private static final String USER_NAME = "dbtest";
    private static final String PASSWORD = "dbtest01!";
    private static final String MONGO_URL = "mongodb://"+USER_NAME+":"+PASSWORD + "@ds121163.mlab.com:21163/se452";
    private static final String COLLECTION_NAME = "BookBadges";

    public BadgeService() {
        MongoClientURI uri  = new MongoClientURI(MONGO_URL);
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase()).getCollection(COLLECTION_NAME);
    }
    
    public void addBadge(int userId, String badgeType) {
    	
    	BasicDBObject dbSearch = new BasicDBObject();
        dbSearch.put("userId", userId);

        BasicDBObject dbUpdate = new BasicDBObject();
        dbUpdate.put("$inc", new BasicDBObject().append(badgeType, 1));

        db.updateOne(dbSearch, dbUpdate);	
    }
    
    public void addNewUser(int userId) {
    	db.insertOne(new Document().
                append("userId", userId).
                append("Autobiography", 0).
                append("Biography", 0).
                append("Biology", 0).
                append("Chemistry", 0).
                append("Classics", 0).
                append("Economics", 0).
                append("Geography", 0).
                append("History", 0).
                append("Philosophy", 0).
                append("Physics", 0).
                append("Politics", 0));
    }
    
    public void deleteUser(int userId) {
    	db.deleteMany(new Document("userId", userId));
    }
    
    public ArrayList<BadgeObject> getBadges(int userId) {
    	
    	ArrayList<BadgeObject> badges = new ArrayList<>();
    	FindIterable<Document> doc = db.find(new Document().append("userId", userId));
    	Document d = doc.first();
    	
    	badges.add(new BadgeObject("Autobiography", d.get("Autobiography", Integer.class)));
    	badges.add(new BadgeObject("Biography", d.get("Biography", Integer.class)));
    	badges.add(new BadgeObject("Biology", d.get("Biology", Integer.class)));
    	badges.add(new BadgeObject("Chemistry", d.get("Chemistry", Integer.class)));
    	badges.add(new BadgeObject("Classics", d.get("Classics", Integer.class)));
    	badges.add(new BadgeObject("Economics", d.get("Economics", Integer.class)));
    	badges.add(new BadgeObject("Geography", d.get("Geography", Integer.class)));
    	badges.add(new BadgeObject("History", d.get("History", Integer.class)));
    	badges.add(new BadgeObject("Philosophy", d.get("Philosophy", Integer.class)));
    	badges.add(new BadgeObject("Physics", d.get("Physics", Integer.class)));
    	badges.add(new BadgeObject("Politics", d.get("Politics", Integer.class)));
    	
    	return badges;
    }
    
    public void seedDatabase() {
            updateBadges(1, "Harry Potter and the Philosopher''s Stone", "J.K. Rowling");
            updateBadges(1, "Animal Farm", "George Orwell");
            updateBadges(1, "American Pastoral", "Philip Roth");
            updateBadges(1, "One Hundred Years of Solitude", "Gabriel Garcia Marquez");
    }
    
    public void updateBadges(int userId, String title, String author) {
    	//check tables to see if title && author are in them
    	//if they are, update Badges
    	
    	BookService bookService = new BookService();
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
        
        for(String badgeCategory : badgeTableNames) {
        	try {
        		ResultSet rs = bookService.getBadgesForBooks(badgeCategory, title, author);
        		if(rs.first()) 
        			addBadge(userId, badgeCategory);
        		
        	} catch (SQLException ex) {
        		System.err.println(ex.getMessage());
        	}  
        }
    } 
    
}
