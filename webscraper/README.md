webscraper
===========

*webscraper* is a console application, which can scrape through the Sainsbury’s Fruit Product offerings Web Page and prints out a JSON object.
JSON object contains following two fields:
	1). The first Object contains the "total" field, which contains the sum of the "unit prices" of individual products on the Web page.  
	2). The second object contains the "JSON Array", which contains the complete details of all the product offerings on the web page.

This solution is implemented with the spirit of MVC (Model View Controller) design pattern. But, please observe that this application is only console based and does not contain any special GUI (Graphical User Interface). But, the GUI module can be 
implemented and plugged to this project seamlessly.

GitHub project repository: https://github.com/jdevinan/WebScraper.git

Source code package structure:
------------------------------
Project's source code is organised into different Java packages to reflect the 
MVC design paradigm.

	|src
		|test
		|	|java
		|		|com
		|			|sb
		|				|webscrapper
		|					|
		|					|-> WebScraperTest (JUnit based Test class)
		|main
			|java
				|com
					|sb
						|webscrapper
							|
							|-> WebScraperMain (application entry class)
							|
							|-> controller 
									|-> Controller (controller layer) 
							|
							|-> model 
									|-> Product (model layer, consists of Java Beans) 
							|
							|-> service (service layer)
									|-> IWebScraperService (public-API)
									|-> WebScraperService  
							|-> util
									|-> JSONConverterr (utility class for JSON conversions)
							
	
NOTE: Database layer can be added easily by adding any JPA implementation (e.g. Hibernate or Spring-Data etc..).


Dependencies
------------
*webscraper* depends on following frameworks:

1). "json-simple" - for JSON data manipulation. (NOTE: "jackson" library can replace this)
2). "jsoup" - for connecting to the Web page and for extraction of data by parsing HTML tags.
3). "junit" - for implementing automated Unit Tests.


How to Run the application on Windows-OS?
---------------------------

Download the ZIP file from the location "https://codeload.github.com/jdevinan/WebScraper/zip/master".
Extract the ZIP file contents to a temporary directory ( e.g. by name "tmp" ).

cd \tmp\WebScraper-master\WebScraper-master\webscraper>

The dependency management tool called "Maven" is configured to package all dependencies in a single executable JAR file. Execute the following command to to generate this JAR file.

    mvn package

After that you can run the application with simple:

    java -jar target/webscraper-1.0-SNAPSHOT.jar

The "webscraper-1.0-SNAPSHOT.jar" can then be copied anywhere and executed independently. 

To execute tests run:

`mvn test`


TESTING
-------

    ------------------------------------------------------
     T E S T S
    -------------------------------------------------------

1). Execute the following command: 

	java -jar target/webscraper-1.0-SNAPSHOT.jar
	
	OUTPUT:
	-------
	 Final JSON Object:
	{"total":15.1,"results":[{"size":34.0,"description":"Apricots","title":"Sainsbury's Apricot Ripe & Ready x5","unit_price":3.5},{"size":35.0,"description":"Avocados","title":"Sainsbury's Avocado Ripe & Ready XL Loose 300g","unit_price":1.5},{"size":39.0,"description":"Avocados","title":"Sainsbury's Avocado, Ripe & Ready x2","unit_price":1.8},{"size":35.0,"description":"Avocados","title":"Sainsbury's Avocados, Ripe & Ready x4","unit_price":3.2},{"size":35.0,"description":"Conference","title":"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)","unit_price":1.5},{"size":35.0,"description":"Gold Kiwi","title":"Sainsbury's Golden Kiwi x4","unit_price":1.8},{"size":36.0,"description":"Kiwi","title":"Sainsbury's Kiwi Fruit, Ripe & Ready x4","unit_price":1.8}]}

-------------------------------------------------------

2). Execute the following command:

	mvn test

	OUTPUT:
	-------
	-------------------------------------------------------
	 T E S T S
	-------------------------------------------------------
	Running com.sb.webscraper.WebScraperTest
	
	 TEST-5:
	 Scraped URL:http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44.html
	 Result, products.toString(): Product{title=Sainsbury's Golden Kiwi x4, size=35.0, unit_price=1.8, description=Gold Kiwi}
	
	 TEST-4:
	 Scraped URL:http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-avocado--ripe---ready-x2.html
	 Result, product.toString(): Product{title=Sainsbury's Avocado, Ripe & Ready x2, size=39.0, unit_price=1.8, description=Avocados}
	
	 TEST-7:
	 Scraped URL:http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html
	 Result contains the description of the Fruit 'Apricot'..
	  json.contains('Apricot'): true
	
	 TEST-1 Scraped URL:http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html
	 Result JSON:
	{"total":15.1,"results":[{"size":34.0,"description":"Apricots","title":"Sainsbury's Apricot Ripe & Ready x5","unit_price":3.5},{"size":35.0,"description":"Avocados","title":"Sainsbury's Avocado Ripe & Ready XL Loose 300g","unit_price":1.5},{"size":39.0,"description":"Avocados","title":"Sainsbury's Avocado, Ripe & Ready x2","unit_price":1.8},{"size":35.0,"description":"Avocados","title":"Sainsbury's Avocados, Ripe & Ready x4","unit_price":3.2},{"size":35.0,"description":"Conference","title":"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)","unit_price":1.5},{"size":35.0,"description":"Gold Kiwi","title":"Sainsbury's Golden Kiwi x4","unit_price":1.8},{"size":36.0,"description":"Kiwi","title":"Sainsbury's Kiwi Fruit, Ripe & Ready x4","unit_price":1.8}]}
	Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.665 sec
	
	Results :
	
	Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
	
