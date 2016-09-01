package com.wipro.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SearchCrawler {
	// stores links already scrawled in unique form
	private Set<String> linkCrawled = new HashSet<String>();
	// stores all links found page to page
	private List<String> linkToCrawl = new LinkedList<String>();

	public void search(String url) {

		String currentUrl;
		Crawler cwl = new Crawler();
		do {
			// First time checking link to crawl is empty
			if (this.linkToCrawl.isEmpty()) {
				currentUrl = url;
				this.linkCrawled.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			// Calling Crawler class crawl method which uses Jsoup api's to get
			// all related links
			cwl.crawl(currentUrl);
			// stores all links in a pages into linkToCrawl list
			this.linkToCrawl.addAll(cwl.getLinks());
		} while (!this.linkToCrawl.isEmpty());
	}

	private String nextUrl() {
		String nextUrl = "";
		do {
			try {
				nextUrl = this.linkToCrawl.remove(0);
			} catch (IndexOutOfBoundsException e) {
				// linkToCrawl list will be empty and exception will be thrown
				System.out.println("About " + linkCrawled.size() + " results");
				System.exit(0);
			}
		} while (this.linkCrawled.contains(nextUrl));// checks uniqueness of url
														// to be crawled, url
														// should not be crawls
														// twice
		this.linkCrawled.add(nextUrl);
		return nextUrl;
	}

}
