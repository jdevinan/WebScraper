package com.sb.webscraper.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import com.sb.webscraper.model.Product;

/**
 * Utility class to convert the List of Products into a JSON Array.
 * This simple JSON conversions are achieved by using "json-simple" libraries.
 * TODO: This can be enhanced by replacing the "json-simple" libraries with "Jackson-databind" libraries.
 * 
 * @author 
 */
public class JSONConverter {


	/**
     * This method converts each Product into a JSONOBject and then adds it to the "JSON-Array"
     * The resultant JSONArray contains the complete list of Products.
     * 
     * @param allProducts, the List of Products.
     * @return JSONArray, contains the complete list of Products.
     */
    public static JSONArray toJSONArray(List<Product> allProducts) {
    	
    	JSONArray jsonArray = new JSONArray();
    	
        for(Product product: allProducts) {                
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put( "title", product.getTitle() );
	    	jsonObject.put( "size", product.getSize() );
	    	jsonObject.put( "unit_price", product.getUnit_Price() );
	    	jsonObject.put( "description", product.getDescription() );

	    	jsonArray.add(jsonObject);
        } 
        
        return jsonArray;
    }   
    
}
