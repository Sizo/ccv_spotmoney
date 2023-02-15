# Credit Card verifier
credit card verification Restful API

##Description
This is a RESTful application that aims to serve the purpose of demonstrating use of the Spring Boot framework. The following requirements have been catered for:

- Provide an endpoint to allow the user to submit a credit card number. Check in which country the card was issued and if that country exists in a list of banned
countries.
- Make the list of banned countries configurable.
- If the card is valid – store it somewhere but don’t store duplicates.
- Provide an endpoint Ability to retrieve all credit card captured
- Provide an end point to retrieve a single credit card
- Provide a Postman Collection that can be used to test the application.
- Provide an Open Api 3 Specification yml detailing the endpoints
exposed.
- The project needs to include Unit Tests


##How to Install and Run the Project
###Install
1. Install [Java 17](https://www.oracle.com/za/java/technologies/downloads/) 
2. Install [GIT](https://git-scm.com/downloads)
3. Install [Maven](https://maven.apache.org/install.html)
4. Clone this repo. [ZIP](https://github.com/Sizo/ccv_spotmoney/archive/refs/heads/master.zip)
5. Download & install [Postman](https://www.postman.com/downloads/)

###Run from project root
1. Run unit tests `mvn clean test`
2. Build project `mvn clean install`
3. Run project `mvn spring-boot:run`

###Testing
By default the application is set to run on port 8080.This can be ammended from the **application.yml**.

- Import Postman collection located under `/src/test/ccv.postman_collection.json` to run manual tests
