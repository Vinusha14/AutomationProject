# NASA Astronomy Picture of the day (APOD) Public API Automation Project

Automation framework written using JAVA, Spring, JUnit and MAVEN

##Table of Contents
1. [Explanation of my approach](#Explanation_of_my_approach)
2. [Setup environment to run tests](#setup_environment)
   1. [Install and Setup JAVA in Windows](#setup_java)
   2. [Install and Setup MAVEN in Windows](#setup_maven)
   3. [Run framework tests from command prompt](#run_tests_command_prompt)
3. [Pros](#pros)
4. [Cons](#cons)


## Explanation of my approach: <a name="Explanation_of_my_approach"></a>
1. Purpose of choosing to automate NASA API is to work with data handling of high resolution image/video information.
As MNTN is into performance marketing platform, MNTN's APIs would be handling a lot of information containing high resolution images and videos 
2. This Automation Framework is built to test NASA APOD public api 
   Please find the API documentation here "https://api.nasa.gov"
3. There is one endpoint /<version>/apod in the service which takes 2 optional fields as parameters to get a HTTP GET request and 7 URL search/query string parameters
4. I chose Rest template in Spring framework to interact with the Rest Client as it provides many functionalities to interact 
with the API 
5. Other tools/technologies used are : Java, JUnit, Maven, IntelliJ, JSON, JSON Parser, command prompt 
6. Added 42 tests in total covering below testing strategies
   1. Functional testing
   2. Negative testing with valid and invalid inputs
   3. Destructive testing
   4. Basic Security and Authorization sanity testing
   5. Performance sanity testing with load and stress testing
   6. Query parameter testing (covered testing all parameters in framework - API_KEY, count, date, start_date, end_date, hd, thumbs; concept_tags functionality turned off in current service)
  
##Build/Run test configuration steps <a name="setup_environment"></a>

##Install JAVA on Windows <a name="setup_java"></a>
1. Download or save the appropriate JDK version for Windows here: https://www.oracle.com/java/technologies/downloads/
2. Right-click the Computer icon on your desktop and select Properties.
3. Click Advanced system settings.
4. Click Environment variables.
5. Under User variables, click New.
6. Enter JAVA_HOME as the variable name.
7. Enter the path to the JDK as the variable value. For example, C:\Program Files (x86)\IBM\Java70\.
8. Click OK.
   Locate the PATH variable.
   Append the path to the JDK (up to the bin folder) as the PATH value. For example, C:\Program Files (x86)\IBM\Java70\bin.
9. Repeat the same for System variables.
   Click OK.
10. To ensure the commands from JAVA JDK are in your PATH environment variable run java -version from command prompt
should display the below similar example message:
      java version "1.8.0_301"
      Java(TM) SE Runtime Environment (build 1.8.0_301-b09)
      Java HotSpot(TM) 64-Bit Server VM (build 25.301-b09, mixed mode)

##Install and setup MAVEN <a name="setup_maven"></a>
1. Maven can be downloaded from this location https://maven.apache.org/download.cgi (You can choose your own location)
2. Right-click the Computer icon on your desktop and select Properties.
3. Click Advanced system settings.
4. Click Environment variables.
5. Under System variables, click New.
6. Enter MAVEN_HOME as the variable name.
7. And point it to the directory apache maven zip file is download eg: C:\opt\apache-maven-3.6.0
8. Under system variable find PATH variable and edit
9. In "Edit environment variable" dialog click on the New button and add this %MAVEN_HOME%\bin
10. To ensure the commands from MAVEN are in your PATH environment variable run mvn -version from command prompt 
should display the below similar example message: 
      Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-25T02:41:47+08:00)
      Maven home: C:\opt\apache-maven-3.6.0\bin\..
      Java version: 10.0.1, vendor: Oracle Corporation, runtime: C:\opt\Java\jdk-10
      Default locale: en_MY, platform encoding: Cp1252
      OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

##Run Tests of the Framework from command prompt in Windows:<a name="run_tests_command_prompt"></a>
1. Download the code repository from the below GitHub repo link under Code Tab- Download Zip link
https://github.com/Vinusha14/AutomationProject
2. Unzip the downloaded project into your desired location eg: C:\Users\Vinusha\GitHubProjects
3. Search and Open command prompt in Windows
4. cd to the project location 
    cd C:\Users\Vinusha\GitHubProjects\AutomationProject-master\
5. Run the maven test command to execute all tests in the framework
   mvn test -Dtest="**"

##PROS:<a name="pros"></a>
1. Spring Rest Template is thread safe 
2. Spring Rest Template provides higher level of abstraction which can avoid a user to make an HTTP Call, create a HttpClient, pass request and form parameters, setup accept headers and perform unmarshalling of response 
3. Test cases part of this automation framework cover extensive (positive, negative with valid and invalid inputs, boundary value and destructive) testing of all query parameters API exposes
4. Framework also covers basic Performance sanity tests to measure the response time from the API calls including stress and load tests
5. Security and authorization testing aspect included in the framework (APIKeyParameterTest)
6. Handling multiple HTTP status code responses from API not just 200 and happy path. eg: 400 bad request, 405 method not allowed,
7. Clear and self-explanatory documentation of tests for ease of a user to execute future extension of framework 
8. Negative testing with unsupported HTTP methods for endpoint
10. Testing API requests in isolation
11. Testing multiple query parameters together
12. Validate HTTP headers of the API
13. Validating API payload - JSON
    
##CONS: <a name="cons"></a>
1. Including Loggers in the tests for easy categorization of the type of error or to send Logging information to files/databases
2. Spring Rest Template uses the thread-per-request model which could degrade the performance of the application when there is multiple user access 
3. Handling of Asynchronous requests to the API could be later added in the scope of the framework
4. Would later want to extend the framework to fetch re-generated new API Keys from this location https://api.nasa.gov/ from the framework 
especially if we want to add scalability in a framework to allow more than 1000 requests/hour with the api key provided  (could use selenium webdriver to automate)
