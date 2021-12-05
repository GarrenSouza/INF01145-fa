package fbd.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author garren
 */
public class Input {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void waitEnterKeyPress() throws IOException{
        System.out.print("\nPress Enter to continue...");
        reader.readLine();
    }
    
    public static String readStringFromUserInput() throws IOException{
        return reader.readLine();
    }
}
