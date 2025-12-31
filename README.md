# Terminal commands

- mvn installed
- surefire reports plugin

## Run tests using the terminal mvn

Use -D to add parameters to the test

```bash
# run all tests in the folder test
$ mvn test 

# Run a xml file 
$ mvn clean test -Dsurefire.suiteXmlFiles=/path/to/testng.xml

# Run passing browser as parameter(check: BaseTest line 46)
$ mvn clean test -Dsurefire.suiteXmlFiles=testng.xml -Dbrowser=firefox


```

## Setup Jenkins

1. go to [jankins](https://www.jenkins.io/download/)
2. Download the java package (.war)
3. Open the terminal where the file was saved
4. Run the following command

    ```bash

    # Starts jenkins on localhost:3030
        $ java -jar jenkins.war --httpPort=3030

    ```

5. install Maven Integration plugin
6. Create a job of maven projects
7. Add the Source Code Management git url
