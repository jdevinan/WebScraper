package com.sb.webscraper;

import static org.junit.Assert.*;

import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sb.webscraper.controller.WebScraperController;
import com.sb.webscraper.model.Product;

/**
 *
 * @author 
 */
public class WebScraperTest {
    
	WebScraperController webScraperController;
    URL url;
    
    public WebScraperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        try {
            url = new URL("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
            webScraperController = new WebScraperController(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebScraperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        webScraperController = null;
    }

    
    @Test
    public void testScrapeResultAsJsonIsNotEmpty() {
        String json = webScraperController.getResultAsJSON(url);
        assertTrue(!json.isEmpty());
        System.out.println("\n TEST-1 Scraped URL:" + url + "\n Result JSON: \n" + json);

    }

    @Test
    public void testScrapeResultHasKeywordsTotalAndResults() {
        String json = webScraperController.getResultAsJSON(url);
        assertTrue(json.contains("total") && json.contains("results"));
    }

    @Test
    public void testGetProductsWithNotPermittedUrlReturnsZeroProducts() {
    	URL  urlStr=null;
    	try {
        	urlStr = new URL ( "http://www.amazon.com");
	        List<Product> products = webScraperController.getProducts(urlStr);
	        assertTrue(products.size() == 0); // No products will be returned from the amazon.com web site since we are not fetching data from permitted API.
	        for(Product product: products) {     
	            assertNull(product);
	        }	        
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebScraperTest.class.getName()).log(Level.SEVERE, null, ex);
   			System.out.println("\n Scraped URL:" + urlStr 
   					+ "\n Someimes, fetching data fails with the 'HTTP error fetching URL' ! " + ex.toString());
        }
            
    }
    

    @Test
    public void testGetProductWithFruitAvocadoProductSpecificWebPageUrlReturnsAvocadoProductDetails() {
        try {
			// URL of "Sainsbury's Avocado, Ripe & Ready x2:":
			URL urlStr = new URL(
					"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-avocado--ripe---ready-x2.html");
			Product product = webScraperController.getProduct(urlStr.toString());

			System.out.println("\n TEST-4: \n Scraped URL:" + urlStr + "\n Result, product.toString(): " + product.toString());
			// Product{title=Sainsbury's Avocado, Ripe & Ready x2, size=39.0, unit_price=1.8, description=Avocados}
			assertNotNull(product);
			assertNotNull(product.getTitle());
			assertTrue(product.getSize() == 39.0f);
			assertTrue(product.getUnit_Price() == 1.8f);
			assertNotNull(product.getDescription());
			assertTrue((product.getDescription().length() > 0) && (product.getDescription().contains("Avocado")));
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebScraperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testGetProductWithFruitKiwiProductSpecificWebPageUrlReturnsKiwiProductDetails() {
        try {
        	//URL of "Sainsbury's Golden Kiwi x4":
        	URL  urlStr = new URL ( "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44.html" );
        	Product product = webScraperController.getProduct(urlStr.toString());
        	
	        System.out.println("\n TEST-5: \n Scraped URL:" + urlStr + "\n Result, products.toString(): " + product.toString() );	
	        //Product{title=Sainsbury's Golden Kiwi x4, size=35.0, unit_price=1.8, description=Gold Kiwi}
            assertNotNull(product);
	        assertNotNull(product.getTitle());
	        assertTrue(product.getSize() == 35.0f);
	        assertTrue(product.getUnit_Price() == 1.8f);
	        assertNotNull(product.getDescription());
	        assertTrue( (product.getDescription().length() > 0) && (product.getDescription().contains("Kiwi")) );
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebScraperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testScrapeResultContainsFruitAvacado() {
        String json = webScraperController.getResultAsJSON(url);
        assertTrue( "TEST-6: Result should contain the word 'Avocado'..", json.contains("Avocado") );
        
/*        System.out.println("\n TEST-6: \n Scraped URL:" + url + "\n Result contains the description of the Fruit 'Avocado'..\n  "
        		+ "json.contains('Avocado'): " + ( json.contains("Avocado") ? "true" : "false" ) + "\n  " 
        		+ "json.contains('Mango'): " + ( json.contains("Mango") ? "true" : "false" ) );*/
    }

    
    @Test
    public void testScrapeResultContainsFruitApricot() {
        String json = webScraperController.getResultAsJSON(url);
        assertTrue(json.contains("Apricot"));
        System.out.println("\n TEST-7: \n Scraped URL:" + url + "\n Result contains the description of the Fruit 'Apricot'..\n  "
        		+ "json.contains('Apricot'): " + json.contains("Apricot")  );
    }
    
    @Test
    public void testScrapeResultDoNotContainFruitMango() {
        String json = webScraperController.getResultAsJSON(url);
        assertFalse( "TEST-8: Test for 'Mango', which is not in the expected result..", json.contains("Mango") );
    }
}
