FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
RUN apk add curl openssl perl
RUN mkdir /tmp/certs
COPY ./target/*.jar /app.jar
COPY ./rds-combined-ca-bundle.pem /rds-combined-ca-bundle.pem
COPY ./trust_store.sh /trust_store.sh
RUN sh /trust_store.sh
EXPOSE 8080

CMD ["java", "-Djavax.net.ssl.keyStore=/tmp/certs/rds-truststore.jks" ,"-Djavax.net.ssl.keyStoreType=jks", "-Djavax.net.ssl.keyStorePassword=truststorePassword", "-jar", "/app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
