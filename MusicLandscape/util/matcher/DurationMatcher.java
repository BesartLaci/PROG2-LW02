package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.matcher;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyMatcher;

/**
 * Encapsulates the concept of matching a track based on its duration.
This class is used to test whether given a given track's duration lies in a certain range, the range being the pattern of this matcher.
The pattern is a simple string consisting of the (white-space separated) lower and upper bounds (inclusive) of the range of duration s (in seconds) accepted by this matcher.
More precisely, a valid pattern is a String that can be interpreted as

    either a single integer number (leading and trailing whitespace are ignored, if present) which then represents the lower bound
    or two integer numbers, separated by (any number of) whitespace, which then represent lower and upper bound. 

The bounds are understood to be inclusive.
 * @author besar
 *
 */
public class DurationMatcher
extends MyMatcher<Track> {
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//
	
	/** 	the lower bound of the accepted range. */
	private int lower;

	/** the upper bound of the accepted range. */
	private int upper;
	
	
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//	
	
	/** Creates a default duration matcher.
	By default a matcher matches any duration, 
	including unknown duration. */
	public DurationMatcher(){
		super(String.format("0 "+Integer.MAX_VALUE));
	}
				
		
	/** Creates a duration matcher with a specified pattern.*/
	public DurationMatcher(String pat){
		super(pat);
	}
	
	
	//--------------------------------------------------------------//	
	//-----------------------Geter - Seter--------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * @return the lower
	 */
	public int getLower() {
		return lower;
	}

	/**
	 * @param lower the lower to set
	 */
	public void setLower(int lower) {
		this.lower = lower;
	}
	
	
	/**
	 * @return the upper
	 */
	public int getUpper() {
		return upper;
	}

	/**
	 * @param upper the upper to set
	 */
	public void setUpper(int upper) {
		this.upper = upper;
	}
	
	
	//--------------------------------------------------------------//	
	//------------------DurationMatcher - Methods-------------------//	
	//--------------------------------------------------------------//
		
	/**
	 * 
	A track matches if its duration is in the range accepted by this matcher.

	Specified by:
	    matches in class MyMatcher<Track>
	Parameters:
	    t - the object to match
	Returns:
	    whether t matches the pattern of this matcher. 
	 */
	public boolean matches(Track t){
		
		if(t.getDuration() >= lower && t.getDuration() <= upper) return true;
		return false;
	}
	
	
	/**
	 * 
	Sets the pattern of this matcher.
	Interprets the argument as described in the class documentation. 
	First sets the lower, then the upper bound. 
	The bounds specified are set if and only if at the time of setting 
	they are actually lower (for the lower bound) or higher (for the upper bound) 
	than the other or at least equal to the other.

	Specified by:
	    setPattern in class MyMatcher<Track>
	Parameters:
	    pat - the pattern to set
	Hint:
	    trim and then split the argument around whitespace 
	    and interpret the fragments as integers using Integer.parseInt 
	 */
	public void setPattern(String pat){
		
		Pattern str = Pattern.compile("\\d+\\s+\\d+");
		 Matcher matcher = str.matcher(pat);
	
		 if (matcher.find()) {
			 String test = matcher.group(0);
			 while (test.contains("  ")) {
				 test = test.replace("  ", " ");
				}
			 	// (55405 66544)
			 String[] result = test.split(" ");
			 int a = Integer.parseInt(result[0]);
			 int b = Integer.parseInt(result[1]);
			 
			 this.lower = Integer.parseInt(result[0]);
			 if(b>=a)
			 this.upper = Integer.parseInt(result[1]);
			 else
				 this.upper = Integer.MAX_VALUE;
		 }

		
//		pat.trim();
//		
//		String pattermachter = "[\\d+\\s+\\d+]";
//		if(pat.matches(pattermachter)){
//	
//			int newLower = getLower();
//			int newUpper = getUpper();
//			
//			for(int i = 0; i < pat.length(); i++){
//				System.out.printf("\ni = "+i+"["+pat.substring(i,i+1)+"]");
//				if(pat.substring(i,i+1) == " "){
//					
//					setLower(Integer.parseInt(pat.substring(0,i-1)));
//					System.out.printf("setLower = "+getLower()+"\n");
////					newLower = Integer.parseInt(pat.substring(0,i-1));
////					if(newLower < lower)setLower(newLower);
//					
//					if(getUpper()<getLower()){setUpper(Integer.MAX_VALUE);}
//					else{setUpper(Integer.parseInt(pat.substring(i+1,pat.length())));}
////				    newUpper = Integer.parseInt(pat.substring(i+1,pat.length()));
////					if(newUpper > upper) setUpper(newUpper);
//					
//				}
//			}
//		}
		
	}

	/**
	 * 
	the valid pattern is
	LOWER UPPER
	separated by whitespace.

	Specified by:
	    getPattern in class MyMatcher<Track>
	Returns:
	    the pattern 
	 */
	public String getPattern(){
		return getLower() + " " + getUpper();
	}
	
	/**
	 * 
	the string representation is duration in range (RANGE)
	with range as described in getPattern

	Overrides:
	    toString in class Object
	 */
	@Override
	public String toString(){
		return "duration in range (" + getPattern() + ")";
	}
 


	
	

}
