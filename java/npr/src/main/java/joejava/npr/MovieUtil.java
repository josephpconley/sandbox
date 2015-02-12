package joejava.npr;

import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieUtil {
    public static Set<String> listMoviesByName() throws Exception{
        final WebClient webClient = new WebClient();
        webClient.setRefreshHandler(new WaitingRefreshHandler());
        webClient.setCssEnabled(false);
        webClient.setJavaScriptEnabled(false);

        String[] suffixes = new String[]{"_numbers", "_A", "_B", "_C", "_D", "_E", "_F", "_G", "_H", "_I", "_J-K", "_L", "_M", "_N-O", "_P", "_Q-R", "_S", "_T", "_U-W", "_X-Z"};
        List<String> bad = Arrays.asList("Create account", "Recent changes", "Privacy policy");
        Set<String> movies = new HashSet<String>();
    //            Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        System.out.println("start");
        for(String s : suffixes){
            String url = "http://en.wikipedia.org/wiki/List_of_films:" + s;
            HtmlPage page = (HtmlPage) webClient.getPage(url);
            HtmlDivision div = (HtmlDivision)page.getByXPath("//div[@id='mw-content-text']").get(0);
            List<HtmlAnchor> films = (List<HtmlAnchor>)div.getByXPath("//a");
            for(HtmlAnchor a : films){
                movies.add(a.asText());
            }
        }
        System.out.println(movies.size());

    //            Collections.sort(h);
    //            for(String s : h){
    //                System.out.println(s);
    //            }
        System.out.println("end");
        return movies;
    }
}
