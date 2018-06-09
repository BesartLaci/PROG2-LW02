package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Artist;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyFormatter;

/**
 * Generic class for writing objects to files in a specific format.
 * @author besar
 *
 * @param <T>
 */
public class MyWriter<T> {

	/**
	 * The underlying stream.
	 * Cannot be null.
	 */
	protected java.io.FileWriter out;
	
	/**
	 * The format in which data is written to file.
	 * Cannot be null.
	 */
	private MyFormatter<T> theFormat;
	
	
	/**
	 * Constructs a MyWriter with a specific target file and format.
	In case null objects are passed to this constructor IllegalArgumentException is thrown.
	
	Parameters:
    file - the file to which to save the data.
    theFormat - the format in which to store the data. 
	 */
	public MyWriter(java.io.FileWriter file, MyFormatter<T> theFormat){
		
		if(file == null){
			throw new IllegalArgumentException("expected non-null FileWriter");
		}		
		
		if(theFormat == null){
			throw new IllegalArgumentException("expected non-null MyFormatter");
		}
		
		this.theFormat = theFormat;
		this.out = file;
				
	}

	

	/**
	 * 
	Writes a single object to the underlying file.
	The object passed to this method is written to file in the format of this MyWriter.
	A newline character is appended at the end of data. 
	This method handles all IOExceptions that might occur and returns false in such a case.
	
	Parameters:
	    t - the object to be written to file
	Returns:
	    true if the object was written to file successfully, false otherwise. 
	 * @param t
	 * @return
	 */
	public final boolean put(T t){
		
		if(out == null) return false;
		
		if(t!=null){
						
			try(BufferedWriter output = new BufferedWriter(out)){
				
				output.write(theFormat.format(t)+"\n");
				return true;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}return false;
	}

	
	/**
	Closes the underlying stream.
	All exceptions are ducked.

	Throws:
	    java.io.IOException - the exception thrown by closing the underlying stream 
	 */
	public void close() throws java.io.IOException{
		
		out.close();
		
	}

	
}
