package badgebook;

public class BadgeBookFactory {
	
	private BadgeBookFactory() {}
	
	public static BadgeBook createBadgeBook(String type, String title, String author) {
		switch(type) {
			case "Classics":
				return new ClassicsBook(title, author);
			case "History":
				return new HistoryBook(title, author);
			case "Economics":
				return new EconomicsBook(title, author);
			case "Geography":
				return new GeographyBook(title, author);
			case "Biology":
				return new BiologyBook(title, author);
			case "Chemistry":
				return new ChemistryBook(title, author);
			case "Physics":
				return new PhysicsBook(title, author);
			case "Politics":
				return new PoliticsBook(title, author);
			case "Philosophy":
				return new PhilosophyBook(title, author);
			case "Autobiography":
				return new AutobiographyBook(title, author);
			case "Biography":
				return new BiographyBook(title, author);
		}
		return null;
	}

}
