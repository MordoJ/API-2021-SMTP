FROM openjdk:11-jre
MAINTAINER Victoria Logan <victoria.logan@heig-vd.ch>

# Install netcat
RUN apt-get update && \
    apt-get -y install netcat && \
    apt-get clean

#
# When we build the image, we copy the executable jar in the image file system
#
COPY MockMock.jar /opt/app/MockMock.jar

EXPOSE 2525 8282

CMD ["java", "-jar", "/opt/app/MockMock.jar", "-p", "2525"]