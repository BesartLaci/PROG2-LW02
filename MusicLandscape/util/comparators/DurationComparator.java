
package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;

/**
 * Encapsulates the concept of comparing two tracks by duration.
 * @author besar
 *
 */
public class DurationComparator
extends Object
implements java.util.Comparator<Track>{
	
	
	public DurationComparator(){
		
	}

	
	/**
	 * 
	Compares two tracks by duration.
	This comparator assumes non-null arguments.
	
	Specified by:
	compare in interface java.util.Comparator<Track>
	Programming Problem Aspect:
	concrete method implementation 
	 */
	public int compare(Track arg0, Track arg1){
		
//		if(arg0 == null) return -1;
//		if(arg1 == null) return 1;
		
		return arg0.getDuration()-arg1.getDuration();
		
	}
	
	/**
	 * 
	the string representation is "by duration" (without quotes)

	Overrides:
	    toString in class Object
	 */
	@Override
	public String toString(){
		
		return "by duration";
	}
 

}
