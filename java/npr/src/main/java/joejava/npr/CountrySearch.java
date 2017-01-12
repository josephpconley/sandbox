package joejava.npr;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * User: josep_000
 * Date: 10/25/13
 *
 * NPR puzzle 7/7/13
 *
 * http://puzzles.blainesville.com/2013/07/npr-sunday-puzzle-jul-7-2013-country.html
 *
 * "Rearrange the letters of INDIA + BELARUS to name two other countries. What are they?"
 */
public class CountrySearch {
    public static void main(String[] args) throws Exception{
        String key = "indiabelarus";
        int sum = 0;
        for(char c : key.toCharArray()){
            sum += c;
        }
        System.out.println(key + " " + sum);
        char[] letters = key.toCharArray();
        Arrays.sort(letters);
        System.out.println(letters);
        List<String> countries = FileUtils.readLines(new File("countries.txt"));
        for(int i=0; i<countries.size(); i++){
            for(int j=i; j<countries.size(); j++){
                String k = countries.get(i).toLowerCase() + "" + countries.get(j).toLowerCase();
                int s = 0;
                char[] letterArray = k.toCharArray();
                for(char c : k.toCharArray()){
                    s += c;
                }
                Arrays.sort(letterArray);
                if(s == sum && new String(letters).equals(new String(letterArray))){
                    System.out.println(countries.get(i) + " " + countries.get(j));
                }
            }
        }
    }
}
