#!/bin/sh

# Run Java JAR file and allow passing additional options like -Xmx through JAVA_OPTS environment variable
exec java $JAVA_OPTS -jar /application.jar