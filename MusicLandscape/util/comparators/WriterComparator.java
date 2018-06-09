package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;

public class WriterComparator 
	extends Object
	implements java.util.Comparator<Track>{
	
	public WriterComparator(){
		
	}
	
	/**
	 * 
	Compares two tracks by writer.
	The natural ordering of artists is used.
	
	Specified by:
	compare in interface java.util.Comparator<Track>
	Programming Problem Aspect:
	concrete method implementation 
	 */
	public int compare(Track o1,Track o2){
		
		return o1.getWriter().compareTo(o2.getWriter());	
		
	}
	

	/**
	 * 
	the string representation is "by writer" (without quotes)

	Overrides:
	    toString in class Object 
	 */
	public String toString(){
		
		return "by writer";
	}



}
