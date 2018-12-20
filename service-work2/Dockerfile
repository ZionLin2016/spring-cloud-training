FROM java:8
VOLUME /tmp
ADD ./target/service-work2.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8070
ENTRYPOINT ["java","-jar","/app.jar"]