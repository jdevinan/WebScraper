package com.sb.webscraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sb.webscraper.controller.WebScraperController;

/**
 * A class which contains the entry point.
 * @author 
 */
public class WebScraperMain {

    public static final void main(String[] args) {
    	// The default Web Page to Scrape through.
        String urlStr = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
        
        if (args.length == 1) {
            // Use the URL provided by the User.
            urlStr = args[0];
        }
        
        try {
            URL url = new URL(urlStr);
            // Instantiate Controller class
            WebScraperController webScraperController = new WebScraperController(url);
            
            //TODO: Create a View class (SWING - GUI) in the future.
            System.out.println("\n Final JSON Object: \n" + webScraperController.getResultAsJSON(url) );         
            //System.out.println("\n Final Product List: \n" + webScraperController.getProducts(url).toString() ); 
        } catch (MalformedURLException ex) {
            System.out.println("The given web-address '" + urlStr + "' is not valid. Exiting....");
            Logger.getLogger(WebScraperMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
	        Logger.getLogger(WebScraperMain.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("WebScraperMain.main(): Can not connect to the Web page.\n" + ex.toString());  
	    }
        
    }
}
