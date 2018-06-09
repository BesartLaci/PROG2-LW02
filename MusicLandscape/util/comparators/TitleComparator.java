package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;

public class TitleComparator 
extends Object
implements java.util.Comparator<Track>{
	
	public TitleComparator(){
		
	}
	


    /**
     *  Compares two tracks by title.
     * @param o1
     * @return
   
    Comparison is performed lexicographically on the titles of the two tracks. 
    This comparator does not handle null tracks.

    Specified by:
        compare in interface java.util.Comparator<Track>
    Programming Problem Aspect:
        concrete method implementation
     */
    public int compare(Track o1, Track o2){
    	
    	return o1.compareTo(o2);
    }

    /**
     *     the string representation is "by title" (without quotes)

    Overrides:
        toString in class Object 
     */
    @Override
    public String toString(){
    	
    	return "by title";
    }




}
