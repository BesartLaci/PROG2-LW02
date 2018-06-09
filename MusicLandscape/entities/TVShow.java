package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

public class TVShow extends Event{
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//

	/**the name of this TV show The name of a TVShow cannot be empty or composed of whitespace only.
	 */
	private String name;
	
	/**	the number of viewers of this TVShow must be non-negative 0 means unknown
	 */
	private int viewers = 0;

	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//
	
	/** creates a default TVShow a default TVShow is a default event with an unknown name and an unknown number of viewers.
	 * Hint:
    (1) Call the super constructor, 
    (2) study this: what happens if the constructor is omitted or if it is omitted in Event?
	 */
	public TVShow(){
		super();
//		name = "unknown";
//		viewers = 0;
	}
	
	
	/** creates a deep copy of a TVShow
	 *@param tvs
	 *	
	Hint:
    call super constructor
	 */
	public TVShow(TVShow tvs){
		super(tvs);
		this.name = tvs.name;
		this.viewers = tvs.viewers;
	}
	
	
	/**this constructor performs some kind of promotion
	 *@param e
 	  
	 such that it takes a generic event and creates a TV show which is a (deep) copy of the original event. 
	 The resulting TV show has unknown name and unknown viewers.
	 */
	public TVShow(Event e){
		super(e);
//		setName("unknown");
//		setViewers(0);
	}
	
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//
	
	
	/**gets the name of this TVShow
	 * @return the name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**sets the name of this TVShow illegal arguments are ignored
	 * @param name the name to set
	 * 
	 */
	public void setName(String name) {
		if(name == null || name.trim().isEmpty()){
			this.name = name;
		}
		else {this.name = name;}
	}

	/**gets the number of viewers of this TVShow
	 * @return the viewers
	 * 
	 */
	public int getViewers() {
		return viewers;
	}

	/**sets the viewers of this TVshow illegal arguments are ignored
	 * @param viewers the viewers to set
	 * 
	 */
	public void setViewers(int viewers) {
		if(viewers >= 0){
			this.viewers = viewers;}
		else {viewers = 0;}
	}
	
	//--------------------------------------------------------------//	
	//----------------------- Methods ------------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 *returns a String representation of this TV show the string representation of a TV show is (without quotes):

	    "artist name" @ "show name" on "date"
	    "description"
	    ("audience" attending ("impact"))

	audience are all viewers and attendees of a show

	Overrides:
	    toString in class Event
	 */
	@Override
	public String toString(){
		
		return this.getArtist() + " @ " + this.getName()
				+ " on " + this.getDate() + "\n" + this.getDescription()
				+ "\n" + "(" + (this.getAttendees()+this.getViewers()) + " attending (" + this.impact() + "))";
		
	}

	/**
	 * 
	returns the impact of this event the impact is an estimation of the number of people who took notice of this event for a TV show event, the impact is the audience times 2. audience are all viewers and attendees of a show

	Overrides:
	    impact in class Event
	 */
	public int impact(){
		return (this.getAttendees()+this.getViewers())*2;
	}

	
}
