/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecurityapp;

/**
 *
 * @author Dungs
 */
public class AsciiConverter 
{
    
    //int ascii = (int) character;
    //char character = (char)ascii;
    public static int CharToASCII(final char character)
    {
	return (int)character;
    }
    
    public static char ASCIIToChar(final int ascii)
    {
         return (char)ascii;		
    }

   
    
    // DataProvider
	public Object[][] ValidDataProvider() 
        {
		return new Object[][]{
				{ 'A', 65 },{ 'a', 97 },
				{ 'B', 66 },{ 'b', 98 },
				{ 'C', 67 },{ 'c', 99 },
				{ 'D', 68 },{ 'd', 100 },
				{ 'Z', 90 },{ 'z', 122 },
				{ '1', 49 },{ '9', 57 },
				
		};
	}
	
}
