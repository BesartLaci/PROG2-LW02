package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

/**
 * This class represents a release of a specific artist, like an album or a music video.
 * @author besar
 *
 */
public abstract class Release {
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//	

	/**The artist that released this release. A null artist represents an unknown artist.*/
	private Artist artist;
	
	/**	The title of this release. 	A null title represents an unknown title.*/
	private java.lang.String title;

	/** The year in which this release was released. The year cannot be negative, value 0 (zero) representing unknown year. */
	private int year = 1900;
	

	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * 	Creates a default release.
	 * A default release has default values for all fields (see there).
	 */
	public Release(){
		artist = new Artist();		
	}
	

	/**
	 * 	Creates a copy of a release.
	 * This release is a deep copy of an existing release - the original.
	 * @param orig
	 */
	public Release(Release orig){
		artist = new Artist(orig.artist);
		title = orig.title;
		year = orig.year;
	}
	

	/**
	 * 	Create a release with a specific title of a specific artist in a specific year.
	 * @param title
	 * @param artist
	 * @param year
	 */
	public Release(java.lang.String title, Artist artist, int year){
		this.artist = artist;
		this.title = title;
		this.year = year;
	}
	
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	/**
	 * @return the title
	 * Get the title of this release.
	 * If the title is unknown a null String is returned.
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 * Set the year of this release.
	 * If the argument is outside the allowed range it is ignored.
	 */
	public void setYear(int year) {
		if(year >=0 ){
			this.year = year;
		}
	}

	//--------------------------------------------------------------//	
	//-----------------------Release - Methods----------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * Get a textual representation of this release.
	The string representation of a release is 	
	"title-artist-year-totaltime" 
	(without quotes and all names substituted with their respective values) 
	unknown field are represented by the string "unknown" (without quotes) *
	 */
	@Override
	public java.lang.String toString(){
		return ((getTitle() == null)?"unknown":getTitle()) + "-"
				+ ((getArtist() == null)?"unkonwn":getArtist()) + "-"
						+ ((getYear() == 0)?"unknown":getYear()) + "-"
								+totalTime();
	}
	
	/**Get the total time of this release.
	 * @return
	 * 	
	 * The implementation of this method is left to concrete subclasses.
	 */
	public abstract int totalTime();
		
	

	
	
}
