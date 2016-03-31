FROM u6kapps/open_jtalk:0.6.1
MAINTAINER u6k <u6k.apps@gmail.com>

# Oracle JDK 8をセットアップ
RUN apt-get install -y software-properties-common && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    apt-get install -y oracle-java8-installer

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN java -version

# Mavenをセットアップ
RUN apt-get install -y maven

CMD ["java", "-version"]
