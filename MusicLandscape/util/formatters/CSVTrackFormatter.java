package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyFormatter;

/**
 * This class represents the concept of csv-formatting of a track.
 * @author besar
 *
 */
public class CSVTrackFormatter
extends Object
implements MyFormatter<Track> {
	
	public CSVTrackFormatter(){
		
	}
	
	
	/**	comma separated list of all column names in the following order:
	 * 
	 * @return
	 * 
	    Title
	    Writer
	    Performer
	    duration
	    year 

	No new line is added at the end of the String!.

	Specified by:
	    header in interface MyFormatter<Track>
	Returns:
	    the header string. 
	 */
	public String header(){
		return String.format("Title;Writer;Performer;duration;year");
	}
	
	
	/**
	 * 
	 * @return
	 * 
	the top separator for this format is the empty string.
	No new line is added at the end of the String!.

	Specified by:
	    topSeparator in interface MyFormatter<Track>
	Returns:
	    the separator.
	 */
	public String topSeparator(){
		return "";
	}
	
	/**
	 * 
	 * @param t
	 * @return
	 * 
	 * 	
	Creates a CSV format of a track.
	The csv representation of a track is 
	"title","performer","writer","year","duration"; (without quotes) 
	No new line is added at the end of the String!.

	Specified by:
	    format in interface MyFormatter<Track>
	Parameters:
	    t - the object to be formatted
	Returns:
	    the formatted representing the object 
	 */
	public String format(Track t){
		
		return String.format(t.getTitle() + ", " + t.getWriter() + ", " + t.getPerformer() + ", " + t.getDuration() + ", " + t.getYear());
	}
	
	//laut MethodenBeschreibung... aber in ES06 wird die Ausgabe wie oben angeführ verlangt
//	public String format(Track t){
//		
//		return String.format(t.getTitle() + "," + t.getPerformer() + "," + t.getWriter() + "," + t.getYear() + "," + t.getDuration() + ";");
//	}
	
	/**
	 * 
	the string representation is "CSV format [Title, Writer, Performer, duration, year]" (without quotes)

	Overrides:
	    toString in class Object 
	 */
	@Override
	public String toString(){
		return "CSV format [Title, Writer, Performer, duration, year]";
	}

	    

 

	
}
