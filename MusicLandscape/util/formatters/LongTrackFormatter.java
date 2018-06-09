package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyFormatter;

public class LongTrackFormatter 
implements MyFormatter<Track>{

	
	public LongTrackFormatter(){
		
	}
	
	/**
	 * 	Description copied from interface: MyFormatter
	Get the headers for the table as a single string.
	Contains the names for all columns separated by the correct number of blanks.

	Specified by:
	    header in interface MyFormatter<Track>
	Returns:
	    the header string.
	 */
	public String header(){
		return String.format("[Title, Writer (min:sec)]");
	}
	
	
	/**
	 * 	Description copied from interface: MyFormatter
	A line of text to be used between header and data.

	Specified by:
	    topSeparator in interface MyFormatter<Track>
	Returns:
	    the separator.
	 */
	public String topSeparator(){
		return "";
	}
	
	/**
	 * 	Description copied from interface: MyFormatter
	Creates a String representation for an object..
	
	long format [Title Writer (min:sec)]

	Specified by:
	    format in interface MyFormatter<Track>
	Parameters:
	    t - the object to be formatted
	Returns:
	    the formatted representing the object
	 */
	public String format(Track t){
		if(t != null){
		return String.format("["+t.getTitle() + " " + t.getWriter() + " ("+ t.getDuration()/60 + ":"+t.getDuration()%60+")]" );
		}
		return String.format("");
	}

	
	/**
	 * 	Overrides:
	    toString in class Object 
	 */
	@Override
	public String toString(){
		return "Long format [Title, Writer (min:sec)]";
	}


 

 

 
}

