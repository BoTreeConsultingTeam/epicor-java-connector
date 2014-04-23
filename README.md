epicor-java-connector
=====================

**Pre-requisites** (*unless specified explicitly use the latest stable release*):

* Sun Java JDK
* Apache Maven
* Apache Tomcat

**Note**: 

* All paths/folders/files mentioned are relative to project's base directory, unless mentioned explicitly.


## Config Changes:

1. Open pom.xml
2. Replace the path configured in property <war.output.dir> with your local Apache Tomcat's `webapps` folder path.

## Compile and Package Command:

  `mvn clean package`

  This would create a WAR file and copy to the path configured in property <war.output.dir> in pom.xml

## Start Server:

`<apache_tomcat_home>/bin$ ./startup.sh`

## Stop Server:

`<apache_tomcat_home>/bin$ ./shutdown.sh`

## Access Application:

  `http://localhost:8080/epicor-web-service/parts/`
  
  This should return a dummy JSON for now:
  
  ```
  [
    {
      "uniqueId": "test-id",
      "name": "test part",
      "description": "test part description"
    }
  ]
  ```

