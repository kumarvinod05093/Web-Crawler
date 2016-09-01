package com.wipro.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	private List<String> links = new LinkedList<String>();

	public boolean crawl(String url) {
		try {

			System.out.println(url);
			// list html document of url supplied
			Document doc = Jsoup.connect(url).get();
			// list all of the links containg href attribute of url supplied
			Elements linksOnPage = doc.select("a[href]");

			for (Element link : linksOnPage) {
				// compares if link contains supplied domain
				if (link.attr("href").contains("wiprodigital.com/")) {
					// adds all of the valid links to linked lists
					this.links.add(link.attr("abs:href"));
				}
			}
			return true;
		} catch (IOException ioe) {
			return false;
		}
	}

	// returns link list of all valid urls of a single page
	public List<String> getLinks() {
		return this.links;
	}

}
