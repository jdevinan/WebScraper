package com.sb.webscraper.model;

/**
 * A simple data container class to hold information about the product.
 * 
 * @author 
 */
public class Product {

	    private String title;
	    private float size;
	    private float unit_price;
	    private String description;

	    public Product() {

		}

	    public Product(String title, float size, float unit_price, String description) {
	        this.title = title;
	        this.size = size;
	        this.unit_price = unit_price;
	        this.description = description;
	    }
	    

		public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public float getSize() {
	        return size;
	    }

	    public void setSize(float size) {
	        this.size = size;
	    }

	    public float getUnit_Price() {
	        return unit_price;
	    }

	    public void setUnit_Price(float unit_price) {
	        this.unit_price = unit_price;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    @Override
	    public String toString() {
	        return "Product{" + "title=" + title + ", size=" + size + ", unit_price=" + unit_price + ", description=" + description + '}';
	    }
	    
}
