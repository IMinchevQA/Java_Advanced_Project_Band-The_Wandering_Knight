package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    //METHOD THAT LOADS OUR LEVEL FILE
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while(line != null) {

                builder.append(line + "\n");
                line = br.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    //RETURNING LEVEL TERRAIN IDS AS INTEGERS
    public static int parseInt(String number) {
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
