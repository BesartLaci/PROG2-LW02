package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.*;


/**represents a generic event of an artist in a certain venue on certain date */
public class Event {

		//--------------------------------------------------------------//	
		//----------------------class blueprint-------------------------//	
		//--------------------------------------------------------------//
			
		/**the artist who appeared at this event the artist of an event cannot be null*/
		private Artist artist = new Artist();
			
		/**the number of attendees of this event.*/
		private int attendees;
		
		/**the date on which this event takes place a null date represents an unknown date*/
		private Date date;
		
		/**description of this event default description is an empty String like*/
		private String description;
		
		/**the venue at which this event takes place a null venue represents an unknown venue*/
		private Venue venue;
		
		//--------------------------------------------------------------//	
		//-----------------------Constructor----------------------------//	
		//--------------------------------------------------------------//
		
		/**
		 * creates a default event 
		 * a default event has 
		 * a default artist 
		 * and an empty description.
		 */
		public Event(){
			setArtist(new Artist());
			setDescription("");
			
		}
		
		
		/**
		 * public Event(Event e)
		creates a deep copy of an event
		Hint:
	    make sure that reference-type members 
	    are also "deeply" copied (call their copy constructor(s))
		 */
		public Event(Event e){
			setArtist(new Artist(e.artist));
			setAttendees(e.attendees);
			setDate(new Date(e.date));
			setDescription(e.description);
			setVenue(new Venue());
			
		}
		
		//--------------------------------------------------------------//	
		//-----------------------Geter - Seter--------------------------//	
		//--------------------------------------------------------------//
		
		/**
		 * @return the artist
		 * gets the artist of this event
		 */
		public Artist getArtist() {
			return artist;
		}

		/**
		 * @param artist 
		 * sets the artist of this event the artist of an event cannot be null
		 */
		public void setArtist(Artist artist) {
			this.artist = (artist==null)?this.artist:artist;
		}

		/**
		 * @return the attendees
		 * gets the number of attendees of this event
		 */
		public int getAttendees() {
			return attendees;
		}

		/**
		 * @param attendees 
		 * sets the number of attendess of this event 
		 * the number of attendees must be a non-negative number
		 */
		public void setAttendees(int attendees) {
			this.attendees = (attendees>=0)?attendees:this.attendees;
		}

		/**
		 * @return the date
		 * gets the date of this event 
		 * this method is defensive 
		 * in the sense that it returns a copy of the date
		 */
		public Date getDate() {
			return (date==null)?null:new Date(date);
		}

		/**
		 * @param date 
		 * sets the date of this event 
		 * an unknown date is represented by a null date. 
		 * this method is defensive 
		 * in the sense that this event keep a copy of the original date
		 */
		public void setDate(Date date) {
			this.date = (date==null)?null:new Date(date);
		}

		/**
		 * @return the description
		 * gets the description of this event
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description
		 * sets the description of this event 
		 * description can not be null
		 */
		public void setDescription(String description) {
			this.description = (description!=null)?description:this.description;
		}

		/**
		 * @return the venue
		 * gets the venue of this event
		 */
		public Venue getVenue() {
			return venue;
		}

		/**
		 * @param venue 
		 * sets the venue of this event
		 */
		public void setVenue(Venue venue) {
			this.venue = venue;
		}
		
		//--------------------------------------------------------------//	
		//-----------------------Event - Methods------------------------//	
		//--------------------------------------------------------------//
		
		/**
		 * returns a String representation of this event 
		 * the string representation of an event is 
		 * (without quotes, but including line breaks):
		
		"artist" @ "venue name" on "date"
		"description"
		("attendees" attending ("impact"))

		if a value is not available, replace it with "unknown" 
		 */
		public String toString(){
			
		return String.format("%s @ %s on %s\n"
							+ "%s\n"
							+ "(%d attending (%d))", 
							artist.toString(),
							(venue == null)?"unknown":(venue.getName() == null)?"unknwon":venue.getName(),
							(date == null)?"null":date.toString(),
							(description == null)?"unknown":description.toString(),
							getAttendees(),
							impact());
			
		}

		
		/**
		 * returns the impact of this event 
		 * the impact is an estimation of the number of people 
		 * who took notice of this event for a generic event, 
		 * the impact is the number of attendees times 2.
		 * @return
		 */
		public int impact(){
			return getAttendees()*2;
		}

		
		
	}
