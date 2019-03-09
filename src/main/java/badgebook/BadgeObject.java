package badgebook;

public class BadgeObject {
	private String badge;
	private int numBadges;
	
	public BadgeObject(String badge, int numBadges){
		this.badge = badge;
		this.numBadges = numBadges;
	}
	
	public String getBadge() {
		return badge;
	}
	
	public int getNumBadges() {
		return numBadges;
	}
}
