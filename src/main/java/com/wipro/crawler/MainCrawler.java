package com.wipro.crawler;

public class MainCrawler {

	public static void main(String[] args) {

		SearchCrawler sCrawler = new SearchCrawler();
		// search url to be crawled
		sCrawler.search("http://wiprodigital.com/");
	}
}
