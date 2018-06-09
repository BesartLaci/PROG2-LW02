package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.tests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.*;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.*;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters.*;


public class ShortTrackFormatterTest {
	ShortTrackFormatter form;
	@BeforeMethod
	public void init(){
		form= new ShortTrackFormatter();
	}


		@Test(description="checks if ShortTrackFormatter implements correct interface")
		public void checkInterface(){
			assertTrue(new ShortTrackFormatter() instanceof MyFormatter, "the class should implement the interface ");
		}

  @Test(description="checks format of a track with a long title using format()")
  public void formatLongTrack() {
		  //"title","performer","writer","year","duration"
		  Track myTrack= new Track("Ain't No Sunshine");
		  myTrack.setPerformer(new Artist("me first and the gimme gimmes"));
		  myTrack.setWriter(new Artist("super cool writer"));
		  myTrack.setYear(2015);
		  myTrack.setDuration(360);
		  
		  String result= form.format(myTrack);
		  System.out.println(result);
		  assertEquals(result, "Ain't No S (06:00)", "Strings should be equal");

  }
  
  @Test(description="formats a track with a short title using format()")
  public void formatShortTrack() {
		  //"title","performer","writer","year","duration"
		  Track myTrack= new Track("Daylight");
		  myTrack.setPerformer(new Artist("Party Girls"));
		  myTrack.setWriter(new Artist("super cool writer"));
		  myTrack.setYear(2015);
		  myTrack.setDuration(123);
		  
		  String result= form.format(myTrack);
		  System.out.println(result);
		  assertEquals(result, "Daylight   (02:03)", "Strings should be equal");

  }

  @Test(description="checks return value of header()")
  public void header() {
	  assertEquals(form.header(),"Title      (min:sec)", "header() should return another value" );
  }

  @Test(description="checks return value of toString()")
  public void testtoString() {
	  assertEquals(form.toString(),"short format [Title (min:sec)]", "toString() should return another value" );
  }

  @Test(description="checks return value of topSeparator()")
  public void topSeparator() {
	  assertEquals(form.topSeparator(),"--------------------", "topSeparator() should return another value" );
  }
}
