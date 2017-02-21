package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    //METHOD THAT LOADS OUR LEVEL FILE
    //THE path PARAMETER IS PASSED BY World.java, METHOD loadWorld(), VARIABLE String file  LINE Nr. -44!!!
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while(line != null) {
                builder.append(line + "\n");
                line = br.readLine();
            }
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static int parseWorldFile(String number) {
        try{
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
