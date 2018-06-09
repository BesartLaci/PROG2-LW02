package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

/**
 * 
 * @author besar
 *represents a concert of a certain artist with a certain set list as a specific event. 
 *As an extension of a generic event a concert provides the possibility to store the setlist.
 *The setlist is the sequence of non-null tracks played at a concert. 
 *Class concert provides methods to add tracks to the (end of the) tracklist 
 *and to reset the tracklist all together (empty it). 
 */
public class Concert extends Event {
		
		//--------------------------------------------------------------//	
		//----------------------class blueprint-------------------------//	
		//--------------------------------------------------------------//	
			
		/** next free index	*/
		private int nextIdx = 0;
		
		/**array holding the tracks of the setlist	*/
		private Track[] setList;		
		
		
			
		//--------------------------------------------------------------//	
		//-----------------------Constructor----------------------------//	
		//--------------------------------------------------------------//	
		
//		public Concert(int nextIdx, Track[] setList) {
//			super();
//			this.nextIdx = nextIdx;
//			this.setList = setList;
//		}
//		
		public Concert(){
			
		}
		
		//--------------------------------------------------------------//	
		//-----------------------Geter - Seter--------------------------//	
		//--------------------------------------------------------------//
	

		public int getNextIdx() {
			return nextIdx;
		}
		
		public void setNextIdx(int nextIdx) {
			this.nextIdx = nextIdx;
		}

		
		/**gets the setlist This method returns a defensive copy, 
		 * @return
		 *  
		 *  meaning it returns a copy of the setlist, 
		 *  which contains (deep) copies of the tracks in the setlist. 
		 * The returned array does not contain any null entries. 
		 *  If the setlist is empty an array of length 0 is returned.
		 */
		public Track[] getSetList() {
			
			if(setList != null){
				Track[] setListCopy = new Track[setList.length];
				
				int i = 0;
				for(Track tempTrack : setList){
					if(tempTrack != null){
						setListCopy[i++] = new Track(tempTrack);
					}
				}
				return setListCopy;
			}
			 Track[] emptySetList = new Track[0];
			 return emptySetList;
		}	
		

		/**
		 * 
		 * @param tracks
	 	sets the setList 
	 	
	 	This method creates a defensive copy, 
	 	meaning it sets the setlist of this concert to contain (deep copies of) 
	 	all non-null tracks of the argument (and only those) 
	 	thereby preserving the relative ordering of entries.
	 	 */
		public void setSetList(Track[] tracks) {
			
			
			if(tracks != null){
					
				setList = new Track[tracks.length];
					
				for(Track tempTrack : tracks){
					if(tempTrack != null){
						setList[nextIdx++] = new Track(tempTrack);
						
					}
				}
				
				
			}
		
		}
		
		
		//--------------------------------------------------------------//	
		//-----------------------Concert - Methods----------------------//	
		//--------------------------------------------------------------//
		
		/**adds a track to the set list 
		 * Tracks are added to the end of the list 
		 * with the first track played at the concert
		 *  being stored at the beginning of the list.	*/
	 	public boolean addTrack(Track t){
			
	 		if(t != null){ 			
	 			
	 				ensureCapacity(1);
	 				setList[nextIdx++] = t;
	 				return true;
	 			}
	 			return false;
	 		
	 	}
	 	
		/**calculates the total duration (in seconds)
		 * @return
		 *   
		 *  of all tracks in the setlist 
		 *  More specifically the method returns an estimation (lower bound) 
		 *  since tracks with unknown duration are treated having duration 0.
		 */
	 	public int 	duration(){
	 		
	 		if(setList != null){
	 			
	 			int duration = 0;
	 			
		 			for(Track tempTrack : setList){
		 				
		 				if(tempTrack != null)
		 				duration += tempTrack.getDuration();
		 			}
	 			return duration;
	 		}return 0;
	 	}
	 	
	 	/**
	 	 * ensures sufficient storage for a specific number of tracks in the setlist 
	 	 * @param length
	 	
	 	If the requested capacity can not be ensured before the call, 
	 	this method increases storage thereby keeping all existing entries.
	 	Track[] 	getSetList()
	 	gets the setlist 
	 	This method returns a defensive copy, 
	 	meaning it returns a copy of the setlist, 
	 	which contains (deep) copies of the tracks in the setlist.
	 	 */
	 	public void ensureCapacity(int length){
	 		
		 	if(setList != null){
		 		
		 		if(length > setList.length-nextIdx-1){
		 		
		 		Track[] tempTrackList = new Track[nextIdx+length];
		 		int i = 0;
		 		
			 		for(Track tempTrack : setList){
			 			
			 			tempTrackList[i] = tempTrack;
			 			
			 			i++;
			 		}
		 		
		 		nextIdx = i;
		 		setList = tempTrackList;
		 		}
		 		
		 	}else{
		 		setList = new Track[length];
		 		
		 	}
	 		
	 		
	 	}
	 	
	 	/**
	 	 * returns the impact of this event 
	 	 * @return
		returns the impact of this event 
		the impact is an estimation of the number of people who took notice of this event. 
		For a concert, the impact is calculated 
		from the number of attendees and the length of the concert. 
		The number of attendees is mulitplied by the duration factor, 
		which is initially 1 but increases by one for every started half hour the concert lasts. 
		E.G: 400 people attending the concert. 
		75 minutes duration; 
		duration factor=3 
		(two full half hours, plus one started half hour) impact therefore is 400*3.
		
		TestFile ConcertTest
		"with baselist and 10 attendees, impact should be 30 
		-> duration is 4500, attendees 30"
	 	 */
	 	@Override
	 	public int impact(){
		
	 		if(setList != null){
	 				//durationFactor = 4500 / 1800 = 2,5
	 		 float durationFactor = ((float)duration())/(1800); // 60 sec = 1 Min * 30 Min = 1 Punkt
	 		 		// 2,5 % 1 = 5 
	 		 if(durationFactor % 1 > 0) {durationFactor += 1;}
	 		 
	 		 int impact = ((int) durationFactor ) * getAttendees(); //Attendees = 10
	 		 
	 		 return impact;
	 			
	 		}return 0;
	 		
	 	}
	 	
	 	/**
	 	get the length of the playlist the length of the playlist is the number of entries in the setlist.
	 	 */
	 	public int nrTracks(){
			return (nextIdx != 0)?nextIdx:0;
	 		
	 	}

	 	/**
	 	removes all tracks from the setlist
	 	 */
	 	public void resetSetList(){
	 		
	 		if(setList != null){
	 			
	 			for(int i = 0; i < setList.length; i++){
	 				setList[i] = null;
	 			}
	 			
	 		}else{setList = new Track[1];}
	 		
	 	
	 		nextIdx = 0;
	 		
	 	}

	 	/**
	 	 * 
	 	returns a String representation of this concert * 
	 	the string representation of a concert 
	 	appends the following line 
	 	to the string representation of a generic event (without quotes): 
	 	"number of tracks" tracks played, total duration "time". 
	 	
	 	time is displayed in the format hh:mm with leading zeros

	 	Overrides:
	 	    toString in class Event
	 	Returns:
	 	    the string representation
	 	Programming Problem Aspect:
	 	    Overriding
	 	Introduced in:
	 	    ExerciseSheet03
	 	Hint:
	 	    use the super class's toString method and study how the correct impact version is called! 
	 	 */
	 	@Override
	 	public String toString(){
	 		return super.toString() 
	 				+ String.format("\n")
	 				+ nrTracks() + " tracks played, total duration " 
	 				+ String.format("%02d:%02d.", (duration()/60/60), ((duration()/60)%60));
	 		 				
	 		
	 	}

	 	 	
	 	
	 	
		
}
