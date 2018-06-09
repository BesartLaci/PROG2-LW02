package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;


/**
holds the name of the artist 
initial value should be unchanged
 */
public class Artist implements Comparable<Artist> {
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//	
	
	/**name of an artist cannot be null or empty.	*/
	private String name;
		
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//	
	
	/**
	 * creates a default artist 
	 * a default artists name is the String "unknown" (without quotes)
	 */
	public Artist(){
	
//		this.name = (this.name==null)?"unknown":this.name;
		this.name = "unknown";
	}
	
	/** creates a copy of an artist
	 * @param a
	 * 
	 */
	public Artist(Artist a){
		this.name = a.name;
	}
		
	/**
	 * creates n artist with a certain name
	 * @param name
	 * 
	 */
	public Artist(String name){
		this.name = (name == null)?this.name:name;
	}
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//

	/**gets the name of this artist
	 * @return the name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**sets the name of this artist.
	 * @param name 
	 *  
 	   name of an artist cannot be null or empty. 
 	   if an invalid argument is passed to the method 
 	   the state of the object remains unchanged
	 */
	public void setName(String name) {
		
		this.name = (name == null)?this.name:(name.trim().isEmpty())?this.name:name;
	}
	
	//--------------------------------------------------------------//	
	//------------------------Artist - Methods----------------------//	
	//--------------------------------------------------------------//
		
	/**
	 * returns a String representation of this Artist
		This should be either the name of the Artist, or "unknown" if the name is not available
	 */
	@Override
	public String toString(){
//		return String.format("%s",(name==null)?"unkown":getName());
		if (name == null) return "unknown";
		else return name;
	}

	
	/** Naturally, artists are lexicographically compared by name. 
	 * It is assumed that this artist is only compared to non-null artists.
	 * 
	 * @param arg0
	 * @return
	 * 
	Specified by:
	    compareTo in interface Comparable<Artist>
	Introduced in:
	    ExerciseSheet05
	 */
	public int compareTo(Artist arg0){
		
		return this.name.compareTo(arg0.name);
	}

 
	
	
}

