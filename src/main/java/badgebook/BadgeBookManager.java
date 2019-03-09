package badgebook;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BadgeBookManager {
	HashMap<String, ArrayList<BadgeBook>> bookBadges;
	private static BadgeBookManager instance;
	
	private BadgeBookManager() {
		bookBadges = new HashMap<>();
		loadBadgeBookData();
	}
	
	public static BadgeBookManager getInstance() {
		if(instance == null)
			instance = new BadgeBookManager();
		return instance;
	}
	
	private void loadBadgeBookData() {
		
		ArrayList<String> bookAreas = new ArrayList<>();
		//Taken from: https://www.bookbub.com/blog/2017/04/20/classic-books-to-read-in-lifetime-challenge
		bookAreas.add("Classics");
		//Taken from: https://io9.gizmodo.com/10-books-that-will-change-how-you-see-history-1687648942
		bookAreas.add("History");
		//Taken from: https://www.independent.co.uk/extras/indybest/arts-books/non-fiction-books/best-economics-books-a7487521.html
		bookAreas.add("Economics");
		//Taken from: https://www.goodreads.com/shelf/show/geography
		bookAreas.add("Geography");
		//https://www.forbes.com/sites/grrlscientist/2017/12/31/the-10-best-biology-books-of-2017/#279827e37a24
		bookAreas.add("Biology");
		//http://wavefunction.fieldofscience.com/2015/04/top-10-popular-chemistry-books-for.html
		bookAreas.add("Chemistry");
		//https://www.susanjfowler.com/blog/2016/8/13/so-you-want-to-learn-physics
		//https://www.goodreads.com/shelf/show/physics
		bookAreas.add("Physics");
		//https://www.signature-reads.com/2017/12/expand-your-view-12-newly-released-books-on-politics-to-read-now/
		bookAreas.add("Politics");
		//https://medium.com/@Gregory_Sadler/the-10-best-philosophy-books-for-beginners-6d1326f81d5
		bookAreas.add("Philosophy");
		//https://www.lifehack.org/articles/communication/15-best-autobiographies-everyone-should-read-least-once-their-life.html
		bookAreas.add("Autobiography");
		//https://thoughtcatalog.com/ryan-holiday/2014/02/25-recommendations-for-life-changing-biographies-for-voracious-readers/
		bookAreas.add("Biography");
		
		for(String category : bookAreas) {
			
			bookBadges.put(category, new ArrayList<BadgeBook>());
			
			try {
				
				Scanner input = new Scanner(Paths.get(category));
				
				while(input.hasNext()) {
					String fullLine = input.nextLine();
					String[] fullLineArr = fullLine.split(",");
					String title = fullLineArr[0];
					String author = fullLineArr[1];
					bookBadges.get(category).add(BadgeBookFactory.createBadgeBook(category, title, author));
				}
				input.close();
			} 
			catch (IOException ex) {
				System.err.println("Error opening " + category + ".txt.");
				System.exit(1);
			}
		}
	}
	
	public ArrayList<BadgeBook> getBookBadgeList(String category) {
		return bookBadges.get(category);
	}
}
