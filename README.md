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
    cd C:\Users\Vinusha\GitHubProjects\AutomationProject-master\AutomationProject-master
5. Run the maven test command to execute all tests in the framework
   mvn test -Dtest="**"

##PROS:<a name="pros"></a>
1. Rest template is thread safe 
2. If we want to make an HTTP Call, we need to create an HttpClient, pass request and form parameters, setup accept headers and perform unmarshalling of response, all by yourself, 
Spring Rest Templates tries to take the pain away by abstracting all these details from you.
3. Test cases part of this automation framework cover extensive testing of all query parameters API exposes
4. Calling the API with different parameter values
5. covered API boundary value conditions
6. lot of negative and destructive testing
7. Basic Peformance test sanity tes cases added - stress and load
8. Security testing aspect considered 
9. Handling multiple HTTP status code responses from API not just 200 and happy path. eg: 400 bad request, 405 method not allowed,
other HTTPExceptions, parseexceptions,HTTPServerErrorExceptions and HTTPClientErrorExceptions
10. Clear documentation of tests for extension of framework - self explanatory
11. included HTTP method testing
12. return field testing
13. Extended positive testing with optional parameters
    Negative testing with valid input
    Negative testing with invalid input
    Destructive testing
    1.Intentionally attempt to fail the API to check its robustness:
    Malformed content in request
    Overflow parameter values. E.g.:
    – Attempt to create a user configuration with a title longer than 200 characters
    Basic Security, authorization - check HTTP/HTTPS
14. Testing requests in isolation
15. testing multiple query parameters together
16. Validate HTTP headers of the API
17. Validating payload - JSON
18. Negative testing - invalid input 
    1. Missing or invalid authorization token
    2. Missing required parameters
    3. Payload with incomplete model (missing fields or required nested entities)
       – Invalid values in nested entity fields
       – Invalid values in HTTP headers
       – Unsupported methods for endpoints
    
##CONS: <a name="cons"></a>
19. loggers?
20. penetration testing?
21. multiple asynch requests to the API
22. Timeout
23. exception handling
24. Key limit 
25. could have missed combination of test cases or more extension
performance testing 
26. more security testing