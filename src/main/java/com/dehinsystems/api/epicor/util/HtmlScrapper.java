/**
 * 
 */
package com.dehinsystems.api.epicor.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author tgbaxi
 * 
 */
public class HtmlScrapper {
	
  public static List<String> fetchImageUrls(final String url) throws IOException {
	  List<String> imageUrls = new ArrayList<String>(0);
	  Document doc = Jsoup.connect(url).get();
	  Elements media = doc.select("[src]");
	  for (Element src : media) {
	      String tagName = src.tagName();
	      if (tagName.equals("img") && src.attr("alt") != null && src.attr("alt").equalsIgnoreCase("Part Photo")) {
	    	imageUrls.add(src.attr("abs:src"));
	      }
	  }
	  return imageUrls;
  }
  
  /*public static void main(String[] args) throws IOException {
    String url = "http://c2c.activant.com/ctoc/index.jsp?ID=ZBS1rACQiPnNlKFN9CEIKeWxBG1R0BVABGwAA";
    Document doc = Jsoup.connect(url).get();
    Elements media = doc.select("[src]");
    // print("\nMedia: (%d)", media.size());
    for (Element src : media) {
      String tagName = src.tagName();
      if (tagName.equals("img") && src.attr("alt") != null && src.attr("alt").equalsIgnoreCase("Part Photo")) {
        System.out.println(src.tagName() + " : " + src.attr("abs:src"));
      }
    }
  }*/
}
