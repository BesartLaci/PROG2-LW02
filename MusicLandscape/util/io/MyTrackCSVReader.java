package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.io;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.io.MyReader;


import java.io.IOException;

import bwi.prog.utils.TextIO;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Artist;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;

/**
 * Reads a line of data from a csv file and returns a Track object populated with the corresponding values.
More specifically this MyReader can load Tracks from a csv file created with the CSVTrackFormatter class.
 * @author besar
 *
 */
public class MyTrackCSVReader
extends MyReader<Track>{
	
	/** these static fields define which information is found at which position in a line of the CSV. */
	private static final int DURATION = 3;
	
	/** these static fields define which information is found at which position in a line of the CSV. */
	private static final int PERFORMER = 2;
	
	/** these static fields define which information is found at which position in a line of the CSV. 
	 * You may find them useful to target a specific information when creating a new Track */
	private static final int TITLE = 0;
	
	/**these static fields define which information is found at which position in a line of the CSV. 
	 * You may find them useful to target a specific information when creating a new Track */
	private static final int WRITER = 1;
	
	/** these static fields define which information is found at which position in a line of the CSV. */
	private static final int YEAR = 4;


	
	public MyTrackCSVReader(java.io.BufferedReader in){
		
		
			super(in);
		
		
	}
	
	/**
	 * 
	reads the current line of the BufferedReader, 
	displays it as is at the console and returns the contained Track of this line
	displays "Error reading." in case of an IOException
	displays "Error parsing" in case of any other exception

	Specified by:
	    get in class MyReader<Track>
	Returns:
	    Track in case a new Track was created successfully, null otherwise 
	 */
	public Track get(){
		
		String line;		
		try{while(null!=(line = super.in.readLine())){				
				
			if(line.split(",").length != 0){				
					String[] tokens = line.split(",");
					
						Track newTrack = new Track(tokens[0].trim());
						newTrack.setWriter(new Artist(tokens[1].trim()));
						newTrack.setPerformer(new Artist(tokens[2].trim()));
						
						newTrack.setDuration(Integer.parseInt(tokens[3]));
						newTrack.setYear(Integer.parseInt(tokens[4]));
											
						return newTrack; 
				}
			}
			in.close();
			
		} 
		catch (IOException e) {TextIO.putf("Error reading.");}		
//		  catch (Exception e){System.out.println("Error parsing.");}
			
		return null;
	}

	

}
