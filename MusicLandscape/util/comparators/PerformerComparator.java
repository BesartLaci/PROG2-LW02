package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;

public class PerformerComparator extends Object
implements java.util.Comparator<Track>{
	
	public PerformerComparator(){
		
	}

	/**
	 * 
	Compares two tracks by performer.
	The natural ordering of artists is used.
	
	Specified by:
	compare in interface java.util.Comparator<Track>
	Programming Problem Aspect:
	concrete method implementation 
	 */
	public int compare(Track o1,Track o2){
		
		return o1.getPerformer().compareTo(o2.getPerformer());	
		
	}


	/**
	 * 
	the string representation is "by performer" (without quotes)
	
	Overrides:
	    toString in class Object 
	 */
	@Override
	public String toString(){
		
		return "by performer";
	}

}
