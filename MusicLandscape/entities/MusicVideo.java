package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

/**
 * This class represents a music video as a concrete release of a specific artist.
 * A music video is a video presentation for a single track and has the same duration as the track.
 * @author besar
 *
 */
public class MusicVideo extends Release{
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//	
	
	/** The track of this music video. A null track represents an unknown track */
	private Track track;

	
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//
	
	public MusicVideo(){
//		track = new Track();
		
	}
	
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(Track track) {
		this.track = track;
	}
	
	//--------------------------------------------------------------//	
	//----------------------MusicVideo - Methods--------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * 	Gets the total running time of this music video.
		
		Specified by:
		    totalTime in class Release
		Returns:
		    the total running time in seconds.
		Programming Problem Aspect:
		    non-trivial getter 
	 */
	public int totalTime(){
		return ((track == null)?0:track.getDuration());
	}

	/**
	 * Gets a String representation of this music video.
	The String representation of a music video simply adds "-(Video)" (without quotes) to the String representation of a general release.
	
	Overrides:
	    toString in class Release
	Programming Problem Aspect:
	    Override toString 
	 */
	@Override
	public java.lang.String toString(){
		return super.toString() + "-(Video)";
	}
	

}
