package com.sb.webscraper.controller;

import java.net.URL;
import java.util.List;

import com.sb.webscraper.model.Product;
import com.sb.webscraper.service.IWebScraperService;
import com.sb.webscraper.service.WebScraperService;

 
/**
 * The main application logic is in this class. Its scrape() method is responsible for fetching the product page,
 * following links to all product pages, grabbing information from each page and producing a JSON output.
 * 
 * @author 
 */
public class WebScraperController {
    private URL url;
    public IWebScraperService iWebScraperService;

    public WebScraperController(URL argUrl) {
        url = argUrl;
        iWebScraperService = new WebScraperService(argUrl); 
    }

 	
 	/* 
 	 * Builds JSON representation of the Products.
 	 *  
 	 * @param url, A String object containing URL (of the Web Site to Scrape through).
 	 * @return String, the JSON Object, containing all the Product details .
 	 */
     public String getResultAsJSON(URL url) {
    	 return iWebScraperService.getResultAsJSON(url);
     }
 
     /**
      * Opens a HTTP(S) connection to the given URL argUrl, and returns a 'Product' object populated with scraped
      * information (title, size, unit price and product description).
      * 
      * @param argUrl A String object containing URL (of the Web Site to Scrape through).
      * @return Product object with data, or null if something went wrong.
      */
     public List<Product> getProducts(URL url){
    	return iWebScraperService.getProducts(url);
     }

     /* 
 	 * @param url, the "URL" String to Scrape through the Web Page and fetch the Product information.
      * @return Product, populated during the Scrape process from the target WebSite 
 	 */
     public Product getProduct(String url){
    	 return iWebScraperService.getProduct(url); 
     }

  	/* 
  	 * @return URL, the URL of the target Web Site to Scrape through.
  	 */
     public URL getUrl() {
         return url;
     }

  	/* 
  	 * @param argUrl, the URL of the target Web Site to Scrape through.
  	 */
     public void setUrl(URL argUrl) {
         url = argUrl;
     }
    
     
} 
