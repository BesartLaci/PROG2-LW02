package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import bwi.prog.utils.TextIO;

//import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.*;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.container.MyTrackContainer;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Artist;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.Track;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyFormatter;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.MyMatcher;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators.DurationComparator;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators.PerformerComparator;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators.TitleComparator;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators.WriterComparator;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.comparators.YearComparator;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters.CSVTrackFormatter;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters.LongTrackFormatter;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.formatters.ShortTrackFormatter;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.io.MyTrackCSVReader;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.io.MyWriter;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.matcher.DurationMatcher;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.util.matcher.TitleMatcher;


public class MainProvided {

	private MyTrackContainer db = new MyTrackContainer();
	private List<Comparator<Track>> comparators = new LinkedList<Comparator<Track>>();
	private List<MyFormatter<Track>> formatters = new LinkedList<MyFormatter<Track>>();
	private List<MyMatcher<Track>> matchers = new LinkedList<MyMatcher<Track>>();

	private Comparator<Track> theComp;
	private boolean asc = true;

	private MyFormatter<Track> theFormat;
	private MyMatcher<Track> placeboMatcher = new TitleMatcher("");
	private Menu menu = new Menu();

	{

		comparators.add(theComp = new TitleComparator());
		comparators.add(new DurationComparator());
		comparators.add(new WriterComparator());
		comparators.add(new PerformerComparator());
		comparators.add(new YearComparator());

		matchers.add(placeboMatcher);
		matchers.add(new DurationMatcher());

		formatters.add(theFormat = new LongTrackFormatter());
		formatters.add(new ShortTrackFormatter());
		formatters.add(new CSVTrackFormatter());

	}

	private static final String WELCOME_TEXT = "Welcome to the FinalTrackDataBase";
	private static final String GOOD_BYE_TEXT = "Thank you for using FinalTrackDataBase";

	private static abstract class MenuItem {
		String text;
		static int nextID = 0;
		final int id = nextID++;

		abstract void execute();

		MenuItem(String s) {
			text = s;
		};

		public String toString() {
			return id + "\t" + text;
		}
	}

	//-------------------------------------------//
	//---------------MENU Constructor------------//
	//-------------------------------------------//
	private class Menu {

		private MenuItem[] menu = {

				
		//-------------MenuItems--------------//
				
		new MenuItem("show menu") {	//MenuItem "show menu"
			void execute() {
				display();
			}
		
		},// end of MenuItem

		new MenuItem("display selection") {	// MenuItem "display selection"
			void execute() {
				System.out.printf("displaying selection:\n");

				MainProvided.this.display(db);
			}
    	
		},// end of MenuItem 

		new MenuItem("edit") {	// MenuItem "edit"
			void execute() {
				System.out.printf("edit:\n");
				
				db.printTracksformTracksList();
				
				if(db.getActualTracksFromTracksSet().size() != 0){					
					System.out.print("Choose Track you want to edit by ID: \n");
					
					String indexID = TextIO.getln();
					do{
						if(db.editTrackFromTrackSetByIndex(Integer.parseInt(indexID))){
							System.out.println("\nTrack with indexID: ["+indexID+"] has been edit");
									
						}else{
							System.out.println("\nThere is no Track on this indexPosition\n");
						}
						
					}while(Integer.parseInt(indexID) > 0 
							&& (Integer.parseInt(indexID)-1) < db.getActualTracksFromTracksSet().size());					
					
				}else{
					System.out.println("\nYou have to import some Tracks into this Album\n");
				}				
			}
			    	
		},// end of MenuItem

		new MenuItem("filter") {	// MenuItem "filter
			void execute() {
				filter();

				
			}						
    	
		},// end of MenuItem

		new MenuItem("reset") {	// MenuItem "reset"
			void execute() {
				
				if(db.size() > 0){
					db.reset();
					System.out.printf("\nDie Selection wurde resetet:\n");
				}else{
					System.out.printf("\nDie Selection konnte nicht resetet werden... DB == NULL");
				}
				
			}						
    	
		},// end of MenuItem

		new MenuItem("remove selection") { 	// MenuItem "remove Selection"
			void execute() {
				
				int indexPostion = 1;
				Track[] tempTrackArray = db.selection();
				
				for(Track tempTrack:tempTrackArray){
					System.out.printf("\n["+indexPostion++ +"] "+tempTrack.toString());
				}
				System.out.printf("\nremove from selection Track with IndexNR.:\n");

				indexPostion = TextIO.getInt();
				Track trackToRemove = tempTrackArray[indexPostion-1];
				
				if(db.removeTrackFromSelection(trackToRemove)){
					System.out.printf("\nTrack mit IndexPositon ["+indexPostion+"] wurde von der Selection entfernt\n");
				}else{System.out.printf("\nKein Track mit IndexPositon ["+indexPostion+"] vorhanden\n");}
			}			
    	
		},// end of MenuItem

		new MenuItem("add") {	// MenuItem "add"
			void execute() {
				System.out.printf("add:\n");
				
				Track newTrack = new Track();
				String userInput = "";
				
				do{
					System.out.printf("\n New Title: \n");
					userInput= TextIO.getln();
					
					if(userInput.trim().length() == 0){
						System.out.printf("\nBitte einen \"Titel\" eingeben\n");
					}
				} while(newTrack.validateTitle(userInput));
				
				newTrack.setTitle(userInput);
				
				
				do{
					System.out.printf("\n New Writer: \n");
					userInput= TextIO.getln();
					
					if(userInput.trim().length() == 0){
						System.out.printf("\nBitte einen \"Writer\" eingeben\n");
					}
				} while(userInput.trim().length() == 0);
				
				newTrack.setWriter(new Artist(userInput));
				
				
				do{
					System.out.printf("\n New Performer: \n");
					userInput= TextIO.getln();
					
					if(userInput.trim().length() == 0){
						System.out.printf("\nBitte einen \"Performer\" eingeben\n");
					}
				} while(userInput.trim().length() == 0);
				
				newTrack.setPerformer(new Artist(userInput));

				
				do{
					System.out.printf("\n New Year: \n");
					userInput= TextIO.getln();
					
					if(userInput.trim().length() == 0){
						System.out.printf("\nBitte einen \"New Year\" eingeben\n");
					}
				} while(userInput.trim().length() == 0
						&& newTrack.validateYear(Integer.parseInt(userInput.trim())) );
				
				newTrack.setYear(Integer.parseInt(userInput.trim()));
				
				
				do{
					System.out.printf("\n New Duration: \n");
					userInput= TextIO.getln();
					
					if(userInput.trim().length() == 0){
						System.out.printf("\nBitte einen \"New Duration\" eingeben\n");
					}
				} while(userInput.trim().length() == 0
						&& newTrack.validateDuration(Integer.parseInt(userInput.trim())) );
				
				newTrack.setDuration(Integer.parseInt(userInput.trim()));
				
				
				if(db.add(newTrack) == true){
					System.out.printf("\n New Track: "+ newTrack.toString() +" wurde zur Liste hinzugefügt\n");
					
					if(db.addTrackToSelection(newTrack) == true){
						System.out.printf("\n New Track: "+ newTrack.toString() +" wurde zur Selection hinzugefügt\n");
					}else{
						System.out.printf("\n New Track: "+ newTrack.toString() +" konnte nicht zur Selection hinzugefügt werden\n");
					}

				}else{
					System.out.printf("\n New Track: "+ newTrack.toString() +" konnte nicht zur Liste hinzugefügt werden\n");
				}
										
			}						
    	
		},// end of MenuItem "add"
		
		new MenuItem("save selection") {	// MenuItem	"save selection"
			void execute() {
				
				String filename = null;
				TextIO.putf("enter file name: \n");
				filename = TextIO.getlnString();
				
				try{saveSelection(filename);				
				} catch ( IOException e) {
					// TODO Auto-generated catch block
					TextIO.putf("\n\""+ filename + "\" not found\n");
					e.printStackTrace();
					}
				
			}						
    	
		}, // end of MenuItem
		
		new MenuItem("load") {	// MenuItem "load"
			void execute() {
				
//				String filename = null;
//				TextIO.putf("enter target file name: \n");
//				filename = TextIO.getlnString();
				
				String filename = "mytracks.csv";
				
				try{load(filename);				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					TextIO.putf("\n\""+ filename + "\" not found\n");
					e.printStackTrace();
					}			

				
			}						
    	
		},// end of MenuItem "load"
		
		new MenuItem("reverse sorting order") {
			void execute() {

				if(asc == true){
					System.out.printf("\n asc = true\n");
					asc = false;
					System.out.printf("\n switch auf asc = false\n");
					db.sort(theComp, asc);
					System.out.printf("selection sorted "+theComp.toString()+" (descending).\n");					
				}else{
					System.out.printf("\n asc = false\n");
					asc = true;
					System.out.printf("\n switch auf asc = true\n");
					db.sort(theComp, asc);
					System.out.printf("selection sorted "+theComp.toString()+" (ascending).\n");
				}
				
			}						
    	
		},// end of MenuItem "reverse sorting order"
		
		new MenuItem("select sorting") {
			void execute() {
			
				int cnt = 1;
				for(Comparator<Track> tempComparator:comparators){
					System.out.printf(cnt++ +": "+tempComparator.toString()+"\n");
				}
				System.out.printf("select Sorting: \n");
				int comparatorIndex = TextIO.getInt();
								
				if(comparatorIndex >=1 && comparatorIndex <= comparators.size()){					
					if(setSortingComparator(comparatorIndex)){
						System.out.printf("selection sorted "+theComp.toString()+" (ascending).\n");
					}else{
						System.out.print("kein comparator auf Index["+comparatorIndex+"] in comparatorListe\n");
					}					
				}else{
					System.out.printf("\n bitte geben Sie eine Zahl > 0 ein\n");
				}
			
				
			}						
    	
		},// end of MenuItem "select sorting"
		
		new MenuItem("select formating") {
			void execute() {
								
				int cnt = 1;
				for(MyFormatter<Track> tempFormatter:formatters){
					System.out.printf(cnt++ +": "+tempFormatter.toString()+"\n");
				}
				System.out.printf("select formatting: \n");
				int formatIndex = TextIO.getInt();
				
				if(formatIndex >=1 && formatIndex <= formatters.size()){
					if(setFormat(formatIndex)){
						System.out.printf("\n theFormat wurde auf formatIndex["+formatIndex+"] geändert\n");
						System.out.printf("%s\n",theFormat.toString());
					}else{System.out.printf("formatIndex ["+formatIndex+"] nicht vorhanden\n");}
				}else{System.out.printf("bitte geben Sie eine Zahl > 0 ein\n");}
				
				
			}						
    	
		}// end of MenuItem "select formating"
		

		};// end of MenuItemArray

		//---------------------------------------------------------------//
		//-----------------------MenuItem - Methods----------------------//
		//---------------------------------------------------------------//
		
		public boolean execute(int input) {
			for (MenuItem m : menu) {
				if (m != null && m.id == input) {
					m.execute();
					return true;
				}
			}
			return false;

		}
		/**
		 * prints the Main Menu with all MenuItem in db
		 */
		void display() {
			for (MenuItem m : menu) {
				System.out.println(m);
			}
		}
		
		/**
		 * loads the CSV File and put the Tracks in the db.MyTrackContainer
		 * @param filename
		 * @throws FileNotFoundException
		 */
		void load(String filename) throws FileNotFoundException{
			
			if(filename.trim().length() != 0 && filename != null){
	//			TextIO.putf("\n"+filename+" : String nicht leer\n");	

				BufferedReader br = new BufferedReader(new FileReader(filename));
				MyTrackCSVReader incomeStream = new MyTrackCSVReader(br);

					
				List<Track> tempTrackArrayList = new ArrayList<Track>();
				Track tempTrack = new Track();
				
				while(null != (tempTrack = incomeStream.get())){
						tempTrackArrayList.add(tempTrack);
					}
					
				db = new MyTrackContainer(tempTrackArrayList);
				
				for(Track tempTrackIncomeArray:tempTrackArrayList){
					TextIO.putf(theFormat.format(tempTrackIncomeArray)+"\n");
				
				}
					
				TextIO.putf(tempTrackArrayList.size()+" Tracks imported\n");
			}
		}
		
		
		/**
		 * saves the current Selction and writes a File.
		 * @param filename
		 * @throws IOException 
		 */
		void saveSelection(String filename) throws IOException{				
			
			if(filename.trim().length() != 0 && filename != null){	
//				BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
				
				int countTracks = 0;
				
				for(Track tempTrack : db.selection()){
					FileWriter fr = new FileWriter(filename, true);
					MyWriter<Track> fileWriter = new MyWriter<Track>(fr, theFormat);
					
					fileWriter.put(tempTrack);
					
					countTracks++;
					fileWriter.close();
					fr.close();
				}				
					
				TextIO.putf(countTracks+" Tracks exportiert\n");
			}					
			
		}


		public boolean setFormat(int formatIndex){
			
			if(formatters.get(formatIndex-1) != null ){				
				theFormat = formatters.get(formatIndex-1);
				return true;
				}
			
			return false;
		}
		
		
		public boolean setSortingComparator(int comparatorIndex){
			
			if(comparators.get(comparatorIndex-1) != null){
				theComp = comparators.get(comparatorIndex-1);
				db.sort(theComp, asc);
				return true;
			}
			
			return false;
			
		}
		
		
		void filter(){
			
			System.out.printf("available filters:\n"
							+"1: title starts with ()\n"
							+"2: duration in range (0 2147483647), args)\n"
							);
			
			int matcherIndex = TextIO.getInt();
			
			if(matcherIndex >=1 && matcherIndex <= matchers.size()){
				placeboMatcher = matchers.get(matcherIndex-1);
				
				switch(matcherIndex-1){
				// 0 = TitleMatcher
				case 0: Scanner patternScanner = new Scanner(System.in);
					
						System.out.printf("enter pattern: \n");
						String pattern = patternScanner.nextLine();						
						
						placeboMatcher.setPattern(pattern);
						int filteredTracksByTitle = db.filter(placeboMatcher);
						System.out.printf("title starts with ("+pattern+") filter applied ("+filteredTracksByTitle+" records filtered).");
						break;
						
				// 1 = DurationMatcher
				case 1: Scanner durationScanner = new Scanner(System.in);
				
						System.out.printf("enter duration \"min max\") in seconds: \n");
						String durationPattern = durationScanner.nextLine();
						
						placeboMatcher.setPattern(durationPattern);
						int filteredTracksByDuration = db.filter(placeboMatcher);
						System.out.printf("Titles with a "+placeboMatcher.toString()+" ("+filteredTracksByDuration+" records fitered).");
						break;					
				}
			}
			
		
							
			
			
		}
		
		//---------------------------------------------------------------//
		//-----------------MenuItem - Methods - ENDE---------------------//
		//---------------------------------------------------------------//

	}
	//---------------------------------------------------------------//
	

	public void go() {

		System.out.println(WELCOME_TEXT);
		Scanner sc = new Scanner(System.in);
		menu.execute(0);
		while (true) {
			// display(db);

			// get choice
			System.out.print(": ");
			int input = Integer.parseInt(sc.nextLine());
			if (menu.execute(input))
				continue;

			System.out.print("exit? (1=yes)");
			if (Integer.parseInt(sc.nextLine()) == 1)
				break;
		}

		System.out.println(GOOD_BYE_TEXT);
		sc.close();
	}


	public static void main(String[] args) {

		new MainProvided().go();

	}

	public void display(MyTrackContainer db) {

		if (db.size() == 0) {
			System.out.print("no records stored.\n");
			return;
		}
		if (db.selection().length == 0) {
			System.out.print("selection empty.\n");
			return;
		}

		System.out.println('\n' + theFormat.header());
		System.out.println(theFormat.topSeparator());
		for (Track tt : db.selection())
			System.out.println(theFormat.format(tt));
		System.out.println();

		System.out.printf("%d out of %d records selected\n", db.selection().length,
				db.size());
	}

}
