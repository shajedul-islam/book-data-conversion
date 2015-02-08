# Book Data Conversion #

This application will convert book data of one text format to another text format. It should take 
input of a filename and a target text format. It should read the content of the file and convert it 
to another format and show it on output.  

### First Phase ###

A program ( no UI is needed ) where you can pass two command line arguments. 
First argument will be treated as file name. The content of the files should have data of a book in plain text or in XML format which will be converted to another format specified by second argument. This program should be able to guess the text format of the input text and display the format in output before converting.

### Second Phase ###
Data elements will be enhanced in this phase. ISBN number of a book will be added as an 
element in book data. All data should contain ISBN number or conversion should be failed 
with an error message mentioning this.   
 
After a conversion is take place, open a file and append the book data in the file in plain text format. After conversion, show the book name in capital letter in output, but capital name 
should not be stored in the file.  

### How To Set Up The Project ###

* Download the source code
* Import the project as an existing java project in eclipse

### How To Test The Project In Command Line###

* Inside the project root folder there is a folder named "Test This Project" 
* This folder contains the jar package of the project BookDataConversion.jar and two book data input files
* Book_text.txt contains a book data in plain text format with colon separated key value pair 
* Book_xml.txt contains a book data in xml format
* Open command line, change directory and navigate to project folder BookDataConversion >> Test This Project
* Type the command: java -jar BookDataConversion.jar <fileName> <outputFormat>
* Try giving various combination of input arguments and test. For example wrong file name or wrong output format 
* Try modifying both text and xml data and test with various combination.


### How To Test The Project In Eclipse ###

* Edit Run Configuration for BookDataConverter class which has the main method. 
* Add two command line arguments in arguments tab: <fileName> <outputFormat>
* There are two test input files provided in project's root folder; 1) Book_text.txt and 2) Book_xml.txt. 
* Book_text.txt contains a book data in plain text format with colon separated key value pair 
* Book_xml.txt contains a book data in xml format
* Test with them to convert xml to text or vice versa
* Sample arguments: 1) Book_text.txt xml  2) Book_xml.txt text
* Run BookDataConverter.java
* Try giving various combination of input arguments and test. For example wrong file name or wrong output format 
* Try modifying both text and xml data and test with various combination.