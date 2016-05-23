package com.sb.webscraper.service;

import java.net.URL;
import java.util.List;

import com.sb.webscraper.model.Product;

/*
 * This interface exposes the methods to Connect and Scrape the Web Page,  
 * returns the result as a "JSON String" and also as "List of Product". 
 */
public interface IWebScraperService {

	/* 
	 * Builds JSON representation of the Products.
	 *  
	 * @param url, A String object containing URL (of the Web Site to Scrape through).
	 * @return String, the JSON Object, containing all the Product details .

	 * @return String, JSON Object as a String. 
	 */
	String getResultAsJSON(URL url);

	/* 
	 * Opens a HTTP(S) connection to the given URL argUrl, and returns a
	 * 'Product' object populated with scraped information (title, size, unit price and product description).
	 *  
	 * @param url, the URL of the Web Site to Scrape through.
	 * @return List of Products, obtained through Scraping of the target WebSite
	 */
	List<Product> getProducts(URL url);

    /* 
	 * @param url, the "URL" String to Scrape through the Web Page and fetch the Product information.
     * @return Product, populated during the Scrape process from the target WebSite 
	 */
    public Product getProduct(String url);
    
	/* 
	 * @return URL, the URL of the target Web Site to Scrape through.
	 */
	URL getUrl();

	/* 
	 * @param argUrl, the URL of the target Web Site to Scrape through.
	 */
	void setUrl(URL argUrl);

}