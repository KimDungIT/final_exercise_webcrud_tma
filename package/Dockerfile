FROM ubuntu:16.04

RUN apt-get update

# Install Java.
RUN apt install openjdk-8-jdk openjdk-8-jre -y
RUN apt-get install -y wget && \
  rm -rf /var/lib/apt/lists/*
 
# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Install Apache Karaf
ENV KARAF_VERSION 4.2.8

#RUN cd /tmp \
#	&& wget -q -O "apache-karaf-$KARAF_VERSION.tar.gz" "http://archive.apache.org/dist/karaf/"$KARAF_VERSION"/apache-#karaf-"$KARAF_VERSION".tar.gz" \
#	&& tar -zxvf apache-karaf-$KARAF_VERSION.tar.gz \
#	&& mv /tmp/apache-karaf-$KARAF_VERSION /opt/karaf 


WORKDIR /opt
COPY karaf.zip /opt
RUN apt-get -y update
RUN apt-get -y upgrade
RUN apt-get install -y zip unzip
RUN unzip karaf.zip
RUN chmod 777 /opt/apache-karaf-4.2.8/bin/*

EXPOSE 1099 8101 44444 8181
WORKDIR /opt/apache-karaf-4.2.8

#ENTRYPOINT ["/opt/apache-karaf-4.2.8/bin/start"]
#ENTRYPOINT ["/opt/apache-karaf-4.2.8/bin/karaf", "&"]
#CMD ["/opt/apache-karaf-4.2.8/bin/karaf", "test"]
CMD ["/opt/apache-karaf-4.2.8/bin/karaf", "run"]

