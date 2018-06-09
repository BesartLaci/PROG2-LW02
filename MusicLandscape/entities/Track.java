 package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

import bwi.prog.utils.TextIO;
import bwi.prog2V.SS2017.wi15b099_Laci.ES06.MusicLandscape.util.ConsoleScanable;

/**
 represents a piece of music 
 that has been released on some kind of media (CD, vinyl, video, ...)
 */
public class Track implements ConsoleScanable, Comparable<Track>{
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//	
	
	/**the duration of this track in seconds the duration is a non-negative number, duration 0 (zero) represents unknown duration*/
	private int duration;
	
	/**the artist who performs this track the performer cannot be null*/
	private Artist performer = new Artist(); 
	
	/**the title of this track. a null title represents an unknown title*/
	private String title; 
	
	/**the artist who wrote this track the writer cannot be null*/
	private Artist writer = new Artist(); 
	
	/**the year in which the Track was or will be produced valid years are between 1900- 2999*/
	private int year; 
	
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * creates a default track 
	 * a default track has the following values: 
	 * unknown title 
	 * duration 0 
	 * default writer 
	 * and performer year 1900
	 */
	public Track(){
//		this.setTitle("unknown");
//		this.setDuration(0);
//		this.writer.setName("unknown");
//		this.setYear(1900);
		
		title = "unknown title";
		duration = 0;
//		writer = new Artist();
		year = 1900;
	}
	
	/** creates a track with a certain title
	 * @param title
	 *  
	 * the resulting track has the specified title, 
	 * all other values are default 
	 */
	public Track(String title){
		this();
		this.title = title;
		
	}
	
	/** creates a deep copy of a Track
	 * @param t
	 * 
	 */
	public Track(Track t){
//		setTitle(t.getTitle());
//		setDuration(t.getDuration());
//		setWriter(new Artist());
//		setPerformer(new Artist());
//		setYear(t.getYear());
		
		title = t.title;
		duration = t.duration;
		writer = new Artist(t.writer);
		performer = new Artist(t.performer);
		year = t.year;
		
	}
	
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//	
	
	/**
	 * @return the duration
	 * gets the duration of this track
	 */
	public int getDuration() {
		return duration;
	}
	
	/** 
	 * @param duration the duration to set
	 * the duration to set
	 * sets the duration a negative value is ignored, 
	 * the object remains unchanged
	 */
	public void setDuration(int duration) {
		
		this.duration = (duration < 0)?0:duration;
	}
	
	/**
	 * @return the performer
	 * returns the performer of this track
	 */
	public Artist getPerformer() {
		return performer;
	}
	
	/**
	 * @param performer 
	 * sets the performer of this track null arguments are ignored
	 */
	public void setPerformer(Artist performer) {
		this.performer = (performer == null)?this.performer:performer;
	}
	
	/**
	* @return the title
	* gets the title of this track.
	* if the title is not known (null) "unknown title" is returned 
	* (without quotes)
	 */
	public String getTitle() {
		
		return (title==null)?"unknown title":title;
	}
	
	/**
	 * @param title 
	 * sets the title of this track.
	 */
	public void setTitle(String title) {
		
		if(validateTitle(title)){
			this.title = title;
		}
		
	}
	
	/**
	* @return the writer
	* returns the writer of this track
	 */
	public Artist getWriter() {
		return writer;
	}
	
	/**
	 * @param writer
	 * sets the the writer of this track null arguments are ignored 
	 */
	public void setWriter(Artist writer) {
		
		this.writer = (writer!=null)?writer:this.writer;

	}
	
	/**
	 * @return the year
	 * gets the production year of this track
	 */
	public int getYear() {
		return year;
	}
	
	/**
	* @param year 
	* sets the production year of this track 
	* valid years are between 1900 and 2999 
	* other values are ignored, 
	* the object remains unchanged
	 */
	public void setYear(int year) {
		this.year = (year<2999 && year>=1900)?year:this.year;
	}
	
	//--------------------------------------------------------------//	
	//----------------------- Methods ------------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * returns a formatted String containing all information of this track
	 * the String representation is (without quotes):
		 * "title by writer performed by performer (min:sec)"	
		 * 
		 *  where
		    -)title stands for the title (exactly 10 chars wide) if not set, return unknown
		    -)writer stands for the writer name (exactly 10 chars wide)
		    -)performer stands for the performer name (exactly 10 chars wide)
		    -)min is the duration's amount of full minutes (at least two digits, leading zeros)
		    -)sec is the duration's remaining amount of seconds (at least two digits, leading zeros) 
		
	 * @return
	 */
	public String getString(){
		
	return	String.format(String.format("%10.10s",(title==null)?"unknown":getTitle())+			
						  String.format(" by %10.10s", (writer==null || writer.getName()==null)?"unknown":this.getWriter().getName())+								  
						  String.format(" performed by %10.10s", (performer==null || performer.getName()==null)?"unknown":this.performer.getName())+								  
						  String.format(" (%02d", getDuration()/60)+						  
						  String.format(":%02d)", getDuration()%60)					  
						  );			
	}
		
	
	/**
	returns a String representation of this track the string representation of this track is described in getString()
	 */
	@Override
	public String toString(){
		return getString();   
	}

		
	/** this getter is used to check if the writer of this Track is known. 
	 * @param writer
	 * @return
	 * this getter is used to check if the writer of this Track is known. 
	 * A writer is considered as known if the name is not equal to null .
	 */
	public boolean writerIsKnown(){
		return (writer==null)?false:(writer.getName()==null)?false:true;
	}
	
	
	/**
	 * Guides the user through a process that allows scanning/modifying of this track with a text-based user interface.
	 * @return
	 * 
	This method allows modification of the following fields, in the order listed:
	
	    title
	    duration 
	
	For each modifiable field the process is the following:
	
	    field name and current value are displayed
	    new value is read and validated
	    if input is valid, field is set, otherwise a short message is shown and input of this field is repeated. 
	
	Old values can be kept for all fields by entering an empty string. The operation cannot be cancelled, instead the user must keep all former values by repeatedly entering empty strings.
	
	Specified by:
	    scan in interface ConsoleScanable
	Returns:
	    whether this object was altered or not
	Programming Problem Aspect:
	    interface method implementation
	Introduced in:
	    ExerciseSheet04
	Hint:
    When adding support for changing writer, performer you might simply consider implementing ConsoleScanable for Artist 
	 */	
	public boolean scan(){
		boolean fieldChanged = false, 
				objectChanged = false;
		String input;
 
		// scanning title
		do {
			TextIO.putf("current title: %s\n", this.title);
			TextIO.putf("enter new title (leave empty to keep):");
			input = TextIO.getlnString();
 
			if (input.length() == 0) { // keep old value?
				fieldChanged = false;
				break;
			}
 

			fieldChanged = true;
			break;
		} while (true);
 
		if (fieldChanged) {
			setTitle(input);
		}
 
		objectChanged = objectChanged || fieldChanged;
 
		fieldChanged = false; // set up for next field
		
		
		String newDuration;
		// scanning duration
				do {
										
					TextIO.putf("current duration: %d\n", this.duration);
					TextIO.putf("enter new duration (leave empty to keep):");
					newDuration = TextIO.getlnString();
		 
						
						if (newDuration.length() == 0) { // keep old value?
							fieldChanged = false;
							break;
						}
						if (Integer.parseInt(newDuration)<0) {// keep old value?
							fieldChanged = false;
							continue;
						}					
			 				
						
						fieldChanged = true;
						break;
															
				} while (fieldChanged = true);
		 
				if (fieldChanged) {
					setDuration(Integer.parseInt(newDuration));
				}
		 
				objectChanged = objectChanged || fieldChanged;
		 
				fieldChanged = false; // set up for next field
				
				return objectChanged;
	}

	/**Naturally, tracks are lexicographically compared by title. It is assumed that this track is only compared to non-null tracks.
	 * 
	 * 	 
	 * @param arg0
	 * @return
	 * 
	 * Specified by:
	    compareTo in interface Comparable<Track>
	 */
	public int compareTo(Track arg0){
		
		return title.compareTo(arg0.title);
	}
	
	
	/**
	 * 
	TODO docu

	Parameters:
	    year - the year to validate
	Returns:
	    true if the argument is a valid year, false otherwise 
	 * @param year
	 * @return
	 */
	public static boolean validateYear(int year){
		
		if(year > 1900 && year < 2999) return true;
		return false;
		
	}

	
	/**
	 * 
	Validates a String as a title.
	The purpose of this method is to separate validation of values from the respective setters, 
	since validation might be necessary in several places (e.g. constructors, scan).
	This validation method is added for clarity of structure only; 
	since all Strings are accepted as title it has no particular practical importance.
	Note that it is marked final, 
	this way it can be used in constructors and setters without having the possibly unwanted 
	side-effect that subclasses redefine criteria for validity. 
	Note also that it is marked static. 
	The reason is that classes should provide methods 
	that allow validation of arguments before calling setters and even more importantly constructors. 
	This way expensive exception mechanisms at runtime are avoided.

	Parameters:
	    title - String to validate as title
	Returns:
	    whether the argument is a valid title or not (here, always true) 
	 * @param title
	 * @return
	 */
	public static final boolean validateTitle(String title){
		
		if(title != null) return true;
		return false;
	}

	
	/**
	 * 
	TODO docu

	Parameters:
	    duration - the duration to validate
	Returns:
	    true if the argument is valid, false otherwise 
	
	 * @param duration
	 * @return
	 */
	public static boolean validateDuration(int duration){
		
		if(duration < 0)return true;
		return false;
		
	}


	

}
