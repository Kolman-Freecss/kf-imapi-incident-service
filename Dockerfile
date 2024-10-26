#####
## TODO: Use multi-stage Dockerfile to build and run a Spring Boot application (Troubleshooting: Not JDK 23 image to use)
## Multi-stage Dockerfile to build and run a Spring Boot application
## This will serve to optimize the final image size decoupling the build environment from the runtime environment
## Also its better for a correct organization of Dockerfile
## ----
## The first stage will build the application using Maven
## The second stage will run the application using OpenJDK
####

# Use Ubuntu as the base image
FROM ubuntu:22.04 AS runner_kf

# Install required tools: wget and necessary dependencies
RUN apt-get update && \
    apt-get install -y wget && \
    rm -rf /var/lib/apt/lists/*

# Manually download and install Java 23 (Temurin)
RUN wget https://github.com/adoptium/temurin23-binaries/releases/download/jdk-23%2B37/OpenJDK23U-jdk_x64_linux_hotspot_23_37.tar.gz \
    && mkdir /opt/java23 \
    && tar -xzf OpenJDK23U-jdk_x64_linux_hotspot_23_37.tar.gz -C /opt/java23 --strip-components=1 \
    && rm OpenJDK23U-jdk_x64_linux_hotspot_23_37.tar.gz

# Set Java 23 as the default JDK
ENV JAVA_HOME=/opt/java23
ENV PATH="$JAVA_HOME/bin:$PATH"

# Install Maven from Apache
RUN wget https://downloads.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz \
    && tar -xzf apache-maven-3.9.4-bin.tar.gz -C /opt/ \
    && ln -s /opt/apache-maven-3.9.4/bin/mvn /usr/bin/mvn \
    && rm apache-maven-3.9.4-bin.tar.gz

# Verify that Java and Maven are using the correct versions
RUN java -version && mvn -version

# Set the working directory
WORKDIR /app

# Copy the Maven configuration file (pom.xml) and install dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the source code and build the application using Java 23
COPY src ./src
RUN mvn package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application with the generated .war file
ENTRYPOINT ["java", "-jar", "/app/target/kf-imapi-incident-service-0.0.1-SNAPSHOT.war"]
