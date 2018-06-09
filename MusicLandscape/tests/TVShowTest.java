
// **************************************************
// 
// 
// 
//   THIS IS GENERATED CODE - > DO NOT EDIT!! 
//
// 
// ***************************************************/

package bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.tests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.Date;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.Venue;
import bwi.prog2V.SS2017.wi15b099_Laci.LW02.MusicLandscape.entities.*;



public class TVShowTest {
	TVShow toTest;
	
	@BeforeMethod
	private void init(){
		toTest=new TVShow();
	}
	

	
	/**
	   * tries to get a private field of the given object
	   * @param myObject Object of which the field should be reached
	   * @param fieldName String which defines the field to be retrieved
	   * @return new Field object if found, fails if NoSuchFieldException
	   */
		private <T> Field getPrivateField(T myObject, String fieldName, boolean isBase){
			Field privateStringField;
			try {
				privateStringField = (isBase)?myObject.getClass().getDeclaredField(fieldName):myObject.getClass().getSuperclass().getDeclaredField(fieldName);
				privateStringField.setAccessible(true);
				return privateStringField;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				fail("member \""+fieldName+"\" not found");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
}
