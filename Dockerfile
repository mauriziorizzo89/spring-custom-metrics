FROM openjdk:8-jdk-alpine
RUN addgroup -S k8s-group && adduser -S k8s-user -G k8s-group
#TODO: da cambiare
RUN mkdir /config
RUN chown -R k8s-user:k8s-group /home/k8s-user
RUN chown -R k8s-user:k8s-group /config
USER k8s-user:k8s-group
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
#TODO: aggiungere i parametri necessari per l'ottimizzazione del GC nell'ottica di utilizzare al meglio la ram
#Esempio, i valori riportati sono indicativi: ENTRYPOINT ["java", "-Xmx32m","-Xss256k","-XX:MaxRAM=56m","-XX:+UseSerialGC","-jar", "/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]