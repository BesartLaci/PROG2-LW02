package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyFormatter;

public class ShortTrackFormatter extends Object
implements MyFormatter<Track>{
	
	public ShortTrackFormatter(){
		
	}
	
	
	/**
	 * 
	Description copied from interface: MyFormatter
	Get the headers for the table as a single string.
	Contains the names for all columns separated by the correct number of blanks.

	Specified by:
	    header in interface MyFormatter<Track>
	Returns:
	    the header string. 
	 */
	public String header(){
		return "Title      (min:sec)";
	}
	
	
	/**
	 * 
	Creates a short format of a track.
	The short representation of a track is "title (duration)" (without quotes).
	Title is exactly ten characters wide with leading blanks (if any). 
	Duration is in the format minutes:seconds, 
	both at least two digits wide with leading zeros.

	Specified by:
	    format in interface MyFormatter<Track>
	Parameters:
	    t - the object to be formatted
	Returns:
	    the formatted representing the object
	 */
	public String format(Track t){
		
		return String.format("%-10.10s (%02d:%02d)",t.getTitle(), t.getDuration()/60, t.getDuration()%60);
		
	}
	
	
	/**
	 * 
	the string representation of this formatter is
	"short format [Title (min:sec)]" (without quotes)

	Overrides:
	    toString in class Object 
	 */
	public String toString(){
		return String.format("short format [Title (min:sec)]");
	}
	
	
	/**
	 * 
	top separator consists of dashes (-) only. It is exactly as wide as the header.

	Specified by:
	    topSeparator in interface MyFormatter<Track>
	Returns:
	    the separator. 
	 */
	public String topSeparator(){
		return "--------------------";
	}


}
