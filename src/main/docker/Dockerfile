FROM adoptopenjdk:11-jre-hotspot

ADD *.jar application.jar

# if you want to add some entrypoint script
COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
# if not
#ENTRYPOINT java $JAVA_OPTS -jar /application.jar

