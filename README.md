# Placeholder API Test Project

This project tests the functionality of the JSONPlaceholder API using RestAssured, JUnit 5, and Allure for reporting. The tests include CRUD operations on posts and an analysis of the most common words in the post bodies.

## Prerequisites

1. **Java Development Kit (JDK) 17**: Ensure you have JDK 17 installed. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

2. **Gradle**: This project uses Gradle for dependency management and build automation. You can install Gradle by following the instructions [here](https://gradle.org/install/).

3. **Allure Commandline**: Allure CLI is required to generate and serve the Allure reports. You can install it by following the instructions [here](https://docs.qameta.io/allure/#_installing_a_commandline).

4. **Git**: Ensure Git is installed to clone the repository. You can install it from [here](https://git-scm.com/downloads).

## Project Setup

### Clone the Repository

```bash
git clone https://github.com/kripkeroman/placeholder-api-test.git
cd placeholder-api-test
```
## Build the Project
Use the following command to clean and build the project:
```bash
./gradlew clean build
```
## Run the Tests
To execute the tests, use the following command:
```bash
./gradlew test
```
# This will run all the test cases included in the PlaceholderTest class.

## Generate and View Allure Reports
### Generate Allure Report
After running the tests, generate the Allure report using the following command:
```bash
./gradlew allureReport
```
### Serve Allure Report
To view the Allure report in your browser, you can serve the report using:
```bash
./gradlew allureServe
```
This will start an Allure server, and the report will be accessible at http://localhost:port, where port is the port number displayed in your terminal.
## Running Specific Test Cases
If you need to run specific tests, you can use the following command:
```bash
./gradlew test --tests "PlaceholderTest.getPostTest"
```