Explanation of my approach:
1. Framework is written to test public api - NASA APOD 
   Please find the API documentation here "https://api.nasa.gov"
2. The public API I picked are REST APIs for which I used Spring framework, Java, JUnit and Maven
3. Covered functional, negative, destructive, security, performance - load, stress, Query parameter testing
4. Added 42 tests in total 
//TODO give a breakdown of different types of tests
5. what query parameters have I tested

Build/Run test configuration steps
Install and setup Maven
Install and setup JAVA
mvn test -Dtest="**"

PROS:


CONS:
1. loggers?
2. penetration testing?
3. multiple asynch requests to the API
4. Timeout
5. exception handling
6. Key limit 
7. could have missed combination of test cases or more extension
performance testing 
8. more security testing