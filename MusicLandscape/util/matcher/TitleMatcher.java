package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.matcher;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyMatcher;

/**
 * Encapsulates the concept of matching a track based on its title.
This class is used to test whether a given track's title starts with a certain string, the starting string being the pattern of this matcher.
The pattern is a simple string. Null patterns are not accepted.
 * @author besar
 *
 */
public class TitleMatcher extends MyMatcher<Track>{
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//
	
	private String pattern;
	
	
	
	/** Creates a title matcher with a specified pattern. */
	public TitleMatcher(String pat){
		super(pat);
	}


	/**
	 * the valid pattern is simply the pattern

	Specified by:
	    getPattern in class MyMatcher<Track>
	Returns:
    the pattern 
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}


	/**
	 * public final void setPattern(String pat)
	
	Any non-null String is an acceptable pattern.
	
	Specified by:
	    setPattern in class MyMatcher<Track>
	Parameters:
	    pat - the pattern to set 
	 * @param pattern the pattern to set
	 */
	public final void setPattern(String pattern) {
		if(pattern != null)this.pattern = pattern;
	}

	/**
	 * a track matches if its title starts with the pattern of this matcher.

	Specified by:
	    matches in class MyMatcher<Track>
	Parameters:
	    t - the object to match
	Returns:
    whether t matches the pattern of this matcher. 
	 */
	@Override
	public boolean matches(Track t) {
		return t.getTitle().startsWith(pattern);
		
	}
	
	/**
	 * 
	the string representation is title starts with (PATTERN)
	with range as described in getPAttern

	Overrides:
	    toString in class Object 
	 */
	public String toString(){
		return String.format("title starts with (%s)", pattern);
	}

	
	
	
	
}
