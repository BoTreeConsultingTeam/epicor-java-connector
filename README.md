epicor-java-connector
=====================

Note: 

* All paths//folders/files mentioned are relative to project's base directory, unless mentioned explicitly.


Config Changes:

1. Open pom.xml
2. Replace the path for property <war.output.dir> with your local Apache Tomcat's webapps path.

Compile and Package Command:

  mvn clean package

  This would create a WAR file and copy to the path configured in property <war.output.dir> in pom.xml

Start Server:

apache_tomcat_home/bin$ ./startup.sh

Stop Server:

apache_tomcat_home/bin$ ./shutdown.sh

Access Application:

  http://localhost:8080/epicor-web-service/parts/
  
  This should display "Welcome"

