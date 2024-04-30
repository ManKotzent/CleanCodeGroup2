Build:
run gradle/gradlew/gradlew.bat build command in CleanCodeGroup2 directory. After that, you can run gradle/gradlew/gradlew.bat jar in CleanCodeGroup 2 directory to create a jar in build/libs directory

Run:
Option a) IDE - main.java.org.cleanCode.Main has the main(String[] args) method to run the application
Option b) Jar executable - go to .../CleanCodeGroup2/build/libs directory which contains a jar executable in case if it was build in Build step. In that directory run command java -jar CleanCodeGroup2-1.0-SNAPSHOT.jar
Dialog steps:
 i. enter url for the website to begin crawling (Can't be empty)
 ii. enter depth for how deep the crawling must be (Accepts positive integers, not including zero; If input is invalid the depth will be set to 1)
 iii. Language (Optional) - type yes to use the translate feature or no to skip it. In case of yes, enter source language (original language of the headings) and target language (language to which you want to translate the headings)
 
 Caution: Depth of 2 or more may lead to long waiting time.

Testing:
Option a) IDE - test.java.AllTests contains the TestSuite to run all tests at once.
Option b) Gradle - In CleanCodeGroup2 you can run command gradle/gradlew/gradlew.bat test to run the tests in the project
