package joejava.npr;

import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: josep_000
 * Date: 10/25/13
 *
 * http://puzzles.blainesville.com/2012/11/npr-sunday-puzzle-nov-18-2012-common.html
 *
 * Q:Think of a familiar five-letter word in two syllables. Change the middle letter to the preceding letter of the alphabet, and you'll get a familiar five-letter word in three syllables. What words are these?
 *
 * A:alpha -> aloha
 */
public class CommonFiveLetterWords {
    public static void main(String[] args) throws Exception{
        List<String> words = FileUtils.readLines(new File("words.csv"));
        List<String> subset = new ArrayList<String>();
        for(String w : words){
            String word = w.split(",")[1];
            if(word.length() == 5){
//				System.out.println(word + " " + (char)(word.charAt(2) - 1));
                subset.add(word);
            }
        }

        for(String s : subset){
            String newWord = s.substring(0,2) + (char)(s.charAt(2) - 1) + s.substring(3,5);
            if(subset.contains(newWord)){
                System.out.println(s + " " + newWord);
            }
        }
    }


    public static void getWords() throws FileNotFoundException, IOException{
        final WebClient webClient = new WebClient();
        webClient.setRefreshHandler(new WaitingRefreshHandler());
        webClient.setCssEnabled(false);
        webClient.setJavaScriptEnabled(false);

        String[] suffixes = new String[]{"1-1000", "1001-2000", "2001-3000", "3001-4000", "4001-5000", "5001-6000", "6001-7000", "7001-8000", "8001-9000", "9001-10000",
                "10001-12000", "12001-14000", "14001-16000", "16001-18000", "18001-20000", "20001-22000", "22001-24000", "24001-26000", "26001-28000", "28001-30000",
                "30001-32000", "32001-34000", "34001-36000", "36001-38000", "38001-40000"};

        List<String> words = new ArrayList<String>();
        for(String s : suffixes){
            String url = "http://en.wiktionary.org/wiki/Wiktionary:Frequency_lists/TV/2006/" + s;
            HtmlPage page = (HtmlPage) webClient.getPage(url);
            HtmlTable table = (HtmlTable)page.getByXPath("//table").get(0);
            for(HtmlTableRow tr : table.getBodies().get(0).getRows()){
                List<String> row = new ArrayList<String>();
                for(HtmlTableCell td : tr.getCells()){
                    row.add(td.asText());
                }
                words.add(StringUtils.join(row, ","));
            }
        }

        File f = new File("words.csv");
        IOUtils.writeLines(words, null, new FileOutputStream(f));
    }
}
