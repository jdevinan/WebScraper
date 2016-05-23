webscraper
===========

*webscraper* is a console application that scrapes the Sainsbury’s grocery site - Ripe Fruits page and prints out 
a JSON array of all the products on the page.
This solution is implemented with the spirit of MVC design pattern.

GitHub project repository: TODO: update project repository URL on github.com.

source tree
-----------

Project's source tree looks like:
TODO: update.
    .
    `-- src
        |-- main
        |   `-- java
        |       `-- com
        |           `-- sb
        |               `-- webscraper
        `-- test
            `-- java
                `-- com
                    `-- sb
                        `-- webscraper

As you can see, the code resides in the com.sb.webscraper package (both testing, and regular).

dependencies
------------

*webscraper* depends on just few popular libraries/frameworks:

- json-simple - for JSON data manipulation.
- jsoup - for HTML parsing and DOM manipulation as well as web connections.
- junit and hamcrest - for testing.

running the application
-----------------------

The simplest way to run the application is to clone the repository, and use Maven to compile and run it:

    cd /tmp
    git clone git@github.com:dejlek/webscraper.git
    cd webscraper
    mvn compile
    mvn exec:java

I have also configured maven to package all dependencies in a single, executable JAR. To generate this JAR file
execute

    mvn package

After that you can run the application with simple:

    java -jar target/webscraper-1.0-SNAPSHOT.jar

The webscraper-1.0-SNAPSHOT.jar can then be copied anywhere and executed independently. 

testing
-------

To execute tests run:

`mvn test`

Here is an example output:

    .
    .
    .
    ------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Picked up _JAVA_OPTIONS: -Dsun.java2d.noddraw=true -Dsun.java2d.xrender=true
    Running com.sb.webscraper.WebScraperTest
    ProductInfo{title=Sainsbury's Avocado Ripe & Ready XL Loose 300g, size=35.0, unitPrice=1.5, description=Avocados}
    Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.648 sec

    Results :

    Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
