package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.container;

import java.util.*;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyMatcher;

/**
 * Represents a set of tracks and a (possibly empty) subset of those tracks which are selected.
This class is a container for unique tracks. 
It does not accept null tracks, 
nor can a track which is already contained be added again.
Additionally, it supports the notion of selection, 
meaning that some tracks can be selected. 
The selection is a subset of all tracks currently held by the container. 
The selection may at times be empty and it may at other times contain all tracks.
The container provides methods to filter, sort, and retrieve the selection.
Tracks can only be removed from this container by removing all currently selected tracks. 
Removing tracks therefore requires the creation of a proper selection first. The usual process of creating a selection is:

    select ALL tracks (by resetting the selection)
    (possibly repeatedly) filter the selection with a matcher. The filter is applied to the current selection!
    if desired, sort the selection.
    remove selected tracks from container OR retrieve the selection as an array of tracks. 
 * @author besar
 *
 */
public class MyTrackContainer {
	//--------------------------------------------------------------//	
	//----------------------class blueprint-------------------------//	
	//--------------------------------------------------------------//	

	/** 	The selected tracks in this container.
	Initially empty.*/
	private List<Track> selection = new ArrayList<Track>();

	/** 	The tracks in this container.
	Initially empty. */
	private Set<Track> 	tracks = new HashSet<Track>();
	
	
	//--------------------------------------------------------------//	
	//-----------------------Constructor----------------------------//	
	//--------------------------------------------------------------//	

	/**
	 * 	Creates a default MyTrackContainer.
	A default container has no tracks and an empty selection.
	 */
	public MyTrackContainer(){
		
	}

	
	/**
	 * 	Creates a container from an iterable object of tracks.
	All tracks of the argument are added to this container.
	 * @param t
	 */
	public MyTrackContainer(Iterable<Track> t){
		
		// t = List with Tracks... ListTyp unknown
		for(Track tempTrack: t){
			tracks.add(tempTrack);
			selection.add(tempTrack);
		}
	}

	
	/**
	 * 	Creates a container from an array of tracks.
	All tracks of the argument are added to this container.
	 * @param t
	 */
	public MyTrackContainer(Track[] t){		
	
		for(Track tempTrack: t){
			tracks.add(tempTrack);
			selection.add(tempTrack);
		}
	}

	
	//--------------------------------------------------------------//	
	//------------------MyTrackConatiner - Methods------------------//	
	//--------------------------------------------------------------//
		
	
	/**
	 * 
	Sorts the selection of tracks of this container.
	The currently selected tracks are sorted 
	in the sense defined by the first argument. 
	The second argument controls the scheme (ascending/descending order).
	
	Parameters:
	theComp - the comparator defining the sorting order
	asc - the sorting scheme. true stands for ascending 
	(from smallest to highest element) false for descending.
	Hint:
	make use of the Collections class 
	 * @param theComp
	 * @param asc
	 */
	public void sort(java.util.Comparator<Track> theComp, boolean asc){
		
		if(asc == false){
		Collections.sort(selection, theComp.reversed());
		}else{
		Collections.sort(selection, theComp);}
		
	}

	/**
	 * 
	Filters the selection.
	Applies the filter defined by the argument to the selection, 
	
	keeping only those elements that match. 
	The filter is applied to the selection and the selection only, 
	i.e. the selection cannot grow in size during this operation. 
	If all elements of a selection match the specified filter, 
	the selection remains unchanged.

	Parameters:
	    matcher - the filter defining which of the tracks of the selection to keep.
	Returns:
	    the number of elements removed from the selection during this operation.
	Hint:
	    use an iterator to remove elements from the selection
	 * @param matcher
	 * @return
	 */
	public int filter(MyMatcher<Track> matcher){

		int i = 0;
		
		List<Track> filteredSelection = new ArrayList<Track>();
		
		for(Track actualTrack : selection){
			if(matcher.matches(actualTrack)){
				filteredSelection.add(actualTrack);
			}else{
				i++;
			}
						
		}
		selection = filteredSelection;
	
		return i;
		
	}
 

	/**Resets the selection, thereby selecting ALL tracks in this container. */
	public void reset(){
	 selection.clear();
	 
	 for(Track tempTrack : tracks){
		 selection.add(tempTrack);
	 }
	 
	}

	
	/**
	 * 
	Removes the selected tracks from this container.
	All currently selected tracks are removed from this container. 
	After this operation all remaining tracks are selected (the selection is reset).

	Returns:
	    the number of removed tracks 
	 * @return
	 */
	public int remove(){
		
		int countRemovedTracks = 0;
		for(Track removeTrack : selection){
			tracks.remove(removeTrack);
			countRemovedTracks++;
		}
		
		reset();
		return countRemovedTracks;
	}

	
	/**
	 * 
	Bulk operation to add tracks.
	All tracks of the argument are added to this container.

	Parameters:
	    t - the tracks to add
	Returns:
	    the number of tracks added
	 * @param t
	 * @return
	 */
	public int addAll(Track[] t){
		
		int count = 0;
		
		for(Track tempTrack : t){
			tracks.add(tempTrack);
			count++;
		}
		
		return count;
	}
 
	
	/**
	 * 	The number of tracks currently held by this container.
	Note: this is not the size of the selection.

	Returns:
	    the number of tracks 
	 * @return
	 */
	public int size(){
		return tracks.size();
		
	}

	/**
	 * 
	Gets the selected tracks.
	The currently selected tracks of this container 
	are returned as an array of tracks. 
	The tracks are returned in their current order.
	If the selection is empty an array of size 0 is returned.

	Returns:
	    the selected tracks.
	Hint:
	    use List's toArray 

	 * @return
	 */
	public Track[] selection(){
		
		if(selection != null){

			Track[] tempTrackArray = new Track[selection.size()];
			
			int idx = 0;
			for(Track tempTrack : selection){
				if(tempTrack != null){
					tempTrackArray[idx++] = tempTrack;
				}
			}
			return tempTrackArray;
		}
		
		Track[] tempTrackArray = new Track[0];
		return tempTrackArray;
		
	}

	
	/**
	 * 	Add a single track.
	The argument is attempted to be added to this container. 
	If successfully added, it is NOT added to the selection. 
	Tracks already added cannot be added again. 
	Null tracks cannot be added either.

	Parameters:
	    t - the track to add
	Returns:
	    whether the argument could be added 
	 * @param t
	 * @return
	 */
	public boolean add(Track t){
		
		if(t == null) return false;
		if(tracks.contains(t)) return false;
		
		return tracks.add(t);
		
	}

	public boolean removeTrackFromSelection(Track trackToRemove){
		
		if(trackToRemove!=null){selection.remove(trackToRemove);return true;}
		
		return false;		
		
	}
	
	public boolean addTrackToSelection(Track t){
		
		if(t != null){
			selection.add(t);
			return true;
		}
		return false;
		
	}
	
	public Set<Track> getActualTracksFromTracksSet(){
		return tracks;
	}
	
	public void printTracksformTracksList(){
		
		if(tracks.size() != 0){
		
			System.out.print("\nList of Tracks in this Album\n");
			
			int idx = 1;
			
			for(Track printTempTrack : tracks){
				if(printTempTrack !=null){
				System.out.printf("[%02d]"+printTempTrack.getString()+"\n", idx);
				}idx++;
			}
			
		}
		else{System.out.println("There are no Tracks in this Album");
		}
	}
	
	
	public boolean editTrackFromTrackSetByIndex(int indexPostiotion){
		
		if(indexPostiotion < 0 || indexPostiotion > tracks.size()) return false;
		
		List<Track> tempTrackArrayList = new ArrayList<Track>(tracks);
				
		if(tempTrackArrayList.get(indexPostiotion).scan()){
			return true;
		}
		return false;
		
		
		
		
		
	}
		
}
