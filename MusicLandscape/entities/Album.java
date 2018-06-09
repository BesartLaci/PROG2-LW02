package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

/**
 * This class represents an album as a concrete release of a specific artist.
 * An album has a list of tracks, which, in this class, is implemented as a (singly) linked lists of tracks.
 * @author besar
 *
 */
public class Album extends Release{
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * The tracks of this album.
	More specifically this is the head of the linked list of tracks of this album.
	 */
	private TrackListItem trackListHead;


	/**
	 * A single item of a linked list of tracks.
	 * A single list item consists of the primary data, in our case a track, and a reference to its successor, which is another list item.
	 * @author besar
	 */
	private class TrackListItem{
		Track track;
		TrackListItem nextTrack;
	}	

	
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//
	
	/**
	 * 	Creates a default Album.
	 * A default album is a default release with an empty track list.
	 */
	public Album(){
		super();
		trackListHead = null;
	}

	
	/**
	 * Creates a copy of an album.
	 * All release parts of this album are copied as described in the release copy constructor.
	 * @param orig
	 */
	public Album(Album orig){
		super(orig);
		trackListHead = orig.trackListHead;		
		
	}

	
	/**
	 * 	Create a album with a specific title of a specific artist in a specific year.
	 * @param title
	 * @param artist
	 * @param year
	 */
	public Album(java.lang.String title, Artist artist, int year){
		super(title, artist, year);
		
	}

	
	//--------------------------------------------------------------//	
	//------------------------Album - Methods-----------------------//	
	//--------------------------------------------------------------//
	

	/** Adds a track to the list of tracks.
	 * @param t
	 * @return
	 *     
    Tracks are added to the end of the list. 
    Null tracks are not accepted. 
    The method returns whether the list was modified. 
    true means success (track was added) false means no success (track was NOT added).

    Parameters:
        t - the track to add
    Returns:
        whether the list was modified (added successfully) or not
    Programming Problem Aspect:
        linked list operation add (back)
	 */
    public boolean addTrack(Track t){
    	if(t != null){
    		
    		TrackListItem newTrackItem = new TrackListItem();
    		newTrackItem.track = t;
    		    		
    		if(trackListHead == null){
    			trackListHead = newTrackItem;
    			return true;
    		}
    		else{
    			for(TrackListItem cur = trackListHead; cur != null; cur = cur.nextTrack){
    				if (cur.nextTrack == null){
    					cur.nextTrack = newTrackItem;
    					return true;
    				}
    			}
    		}
    	}return false;
    }


    /** Removes a track from the track from the list of tracks.
     * @param n
     * @return
     * 
    
    Removes and returns the track at position n from the list of tracks. 
    Element numbering starts at 0, 
    such that in a list containing a single element the position of that element is 0 (zero). 
    If the requested element does not exist in the list null is returned.

    Parameters:
        n - the (zero-based) position of the track to be removed.
    Returns:
        the removed track or null
    Programming Problem Aspect:
        linked list operation remove

     */
    public Track removeTrack(int n){
    	
    	if(n < 0 || trackListHead == null){return null;}
    	
    	int idx = 0;
    	TrackListItem searchedTrack = new TrackListItem();
    	TrackListItem previousTrack = new TrackListItem();
    	
    	if(n == 0){
    		searchedTrack = trackListHead;
    		trackListHead = searchedTrack.nextTrack;
    		return searchedTrack.track;
    	}
    	
    	for(TrackListItem currentTrack = trackListHead; currentTrack != null; currentTrack = currentTrack.nextTrack){
			
    		if(idx != n){previousTrack = currentTrack;}
    		
			if(idx == n){
				searchedTrack = currentTrack; 
				previousTrack.nextTrack = searchedTrack.nextTrack;
				break;
				}
			
    		idx++;	
			}    	
				
    	return searchedTrack.track;
    	
    }


    /** Gets the number of tracks on this album.
     * @return
     *     

    Returns:
        the number of tracks
    Programming Problem Aspect:
        linked list operation size
     */
    public int nrTracks(){
    	if(trackListHead == null){return 0;}
    	
    	int count = 0;
    	for(TrackListItem currentTrack = trackListHead; currentTrack != null; currentTrack = currentTrack.nextTrack){
    		count++;
    	}
    	return count;
    }


    /**
     * 
     *  Gets the tracks of this album.
     *  @return
    This method creates an array containing all tracks of this album preserving their current order. 
    If the album has no tracks an array of size zero is returned. 
    The tracks in the returned array are NOT (deep) copies of the tracks currently maintained by this album, 
    meaning that the caller can modify the tracks of this album, 
    however modification of their ordering in the list is not possible from outside. *

    Returns:
        the tracks of this album in order
    Programming Problem Aspect:
        linked list
     */
    public Track[] getTracks(){
    	
    	if(trackListHead != null){
    		Track[] trackArrayList = new Track[nrTracks()];
    		int i = 0;
    		
    		for(TrackListItem currentTrack = trackListHead; currentTrack != null; currentTrack = currentTrack.nextTrack){
    			trackArrayList[i++] = currentTrack.track;
    			
    		}
    		return trackArrayList;
    	}
    	
    	Track[] trackArrayList = new Track[0];
    	return trackArrayList;
    }

    
    /**
     * Gets the total running time of this album.
    The running time is the sum of the running times of all tracks in this album. 
    The time is returned in seconds.

    Specified by:
        totalTime in class Release
    Returns:
        the total running time in seconds.
    Programming Problem Aspect:
        non-trivial getter, aggregation
     */
    public int totalTime(){
    	if(trackListHead != null){
    		
    		int albumDuration = 0;
    		
    		for(TrackListItem currentTrack = trackListHead; currentTrack != null; currentTrack = currentTrack.nextTrack){
    			albumDuration += currentTrack.track.getDuration();
    			
    		}
    		return albumDuration;
    	}
    	return 0;
    	
    }


    /**
     * Gets a String representation of this album.
    The String representation of an album adds the titles of all tracks to the release String representation. 
    The list of track names is enclosed by opening and closing brackets ([,]). 
    Track titles are also enclosed by opening and closing brackets.

    Overrides:
        toString in class Release
    Programming Problem Aspect:
        Override toString
     */
    @Override
    public java.lang.String toString(){
    	
    	String album = super.toString();
    	
    	String albumTrackList = "\n[";
    	
    	if(trackListHead != null){
    		for(TrackListItem currentTrack = trackListHead; currentTrack != null; currentTrack = currentTrack.nextTrack){
    			albumTrackList += "["+currentTrack.track.getTitle()+"]";    	
    		}    		
    	}
    	albumTrackList += "]";   
		album += albumTrackList;
		
    	return album;
    }



	
}
