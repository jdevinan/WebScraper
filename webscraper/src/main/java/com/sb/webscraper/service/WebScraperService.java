package com.sb.webscraper.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sb.webscraper.model.Product;
import com.sb.webscraper.util.JSONConverter;

/**
 * The main application logic is in this class. Its scrape() method is responsible for fetching the product page,
 * following links to all product pages, grabbing information from each page and producing a JSON output.
 * 
 * @author 
 */
public class WebScraperService implements IWebScraperService {
	private URL url;
	
	public WebScraperService(URL argUrl) {
        url = argUrl;
    }

	
	/**
	 * 
	 * @return Connection, the "Jsoup.Connection" object.
	 */
	private Connection connectToURL(URL url) {
		
		Connection connection = null;
		
		try {
			connection = Jsoup.connect(url.toString());
			
        } catch (Exception ex) {
            Logger.getLogger(WebScraperService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("WebScraperService.connect(URL): Can not connect to the Web page.\n" + ex.toString());  
        }
        
		return connection;
	} 
	
	
    /* 
     * Builds JSON representation of the Products. 
	 * @return String, the result JSON Object as a String. 
	 */
  	@Override
	public String getResultAsJSON(URL url) {
       
        // Build JSON representation of all the Products.
  		List<Product> allProducts = getProducts(url); // Get the complete list of Products.
        if ( allProducts.size() == 0) {
            // If the Product list is empty, return an empty JSON document.
            return "{}";
        }
          		
        JSONArray jsonArray = JSONConverter.toJSONArray(allProducts); // Build JSON Array, which contains the list of Products. 
    	JSONObject jsonObject = new JSONObject(); // Final Result as a JSON object, contains "Sum of Unit Prices" and "JSON Array" 
        jsonObject.put("results", jsonArray); 

        // calculating the total of "Unit Price of each Product".
        float total = 0.0f; // total unit price.
        for(Product product: allProducts) {
        	total += product.getUnit_Price();
        }
        
        // Set the total price /unit for all products.
        jsonObject.put("total", total); // contains "Sum of Unit Prices".     
        
        return jsonObject.toJSONString();
    }


    /* 
     * @param url, A String object containing URL (of the Web Site to Scrape through).
     * @return List of Products, obtained through Scrape process from the target WebSite
	 */
	@Override
	public List<Product> getProducts(URL url) {

       	List<Product> products = new ArrayList<Product>();

        // Connect to the URL of the Web page.
        Connection con = connectToURL(url);

    	// If we can't connect, we return an empty List of Products.
        if (con == null) {
            return products;
        }        
     	               
        try {
            Element el = con.get().select("ul.productLister").first();
            if (el == null) {
                // There is no list of products so there is no need to continue...
                return products;
            }
            
            Elements els = el.getElementsByTag("li");
            for (Element element: els) {
                Element pinfoel = element.select("div.productInfo").first();
                Element linkel = pinfoel.getElementsByTag("a").first();
                
                // System.out.println(linkel.attr("abs:href")); // if we need absolute URL
                String infoUrl = linkel.attr("href");      
                
               // System.out.println("\n\n WebScraperService.getProducts():  URL to specific Product: infoUrl :: \n:" +infoUrl ); 
                
                Product product = getProduct(infoUrl);
                
                products.add(product);                
            }
        } catch (IOException ex) {
        	System.out.println("\n\n WebScraperService.getProducts(): EXCEPTION !! \n:" + ex.toString());  
            Logger.getLogger(WebScraperService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }


    /* 
	 * @param url, the "URL" String to Scrape through the Web Page and fetch the Product information.
     * @return Product, populated during the Scrape process from the target WebSite 
	 */
    public Product getProduct(String url) {

    	String title = "";
        float size = 0.0f;
        float unitPrice = 0.0f;
        String description = "";
        
        try {
            Document doc = Jsoup.connect(url).get();
  
            Element el = doc.select("div.productTitleDescriptionContainer").first();
            if (el == null) {
                return null;
            } else {
                // Let's get the product title
                Element titleElement = el.getElementsByTag("h1").first();
                title = titleElement.text();
                
                // size of the web-page (in kb)
                size = doc.toString().length() / 1024;
            }
            
            // let's get price per unit
            el = doc.select("p.pricePerUnit").first();
            if (el == null) {
                return null;
            } else {
                String ptxt = el.text();
                ptxt = ptxt.replace("/unit", "");
                ptxt = ptxt.replace("£", "");
                float ppunit = Float.parseFloat(ptxt);
                unitPrice = ppunit;
            }
            
            // Let's get the description.
            // Assuming that description part comes always first...
            el = doc.select("div.productText").first();
            if (el == null) {
                return null;
            } else {
                description = el.text();
            }
        } catch (IOException ex) {
        	System.out.println("\n\n WebScraperService.getProduct(): EXCEPTION !! \n:" + ex.toString());  
            Logger.getLogger(WebScraperService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //System.out.println("WebScraperService.getProduct(): END of getProduct(): \n: title:" + title + "size:" + size + "unitPrice:" + unitPrice + "description:" + description);
        
        return new Product(title, size, unitPrice, description);
        
    } 
    
    
    
    /* 
	 * @return URL, the URL of the target Web Site to Scrape through.
	 */
	@Override
	public URL getUrl() {
        return url;
    }


    /* 
	 * @param argUrl, the URL of the target Web Site to Scrape through.
	 */
	@Override
	public void setUrl(URL argUrl) {
        url = argUrl;
    }
    
} 
