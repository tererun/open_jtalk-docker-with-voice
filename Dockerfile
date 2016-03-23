FROM ubuntu:latest
MAINTAINER u6k <u6k.apps@gmail.com>

# ビルドに必要なソフトウェアをインストール
RUN apt-get update && \
    apt-get install -y wget git build-essential

WORKDIR /usr/local/src/

# Open JTalk関連のソフトウェアをセットアップ
RUN wget http://downloads.sourceforge.net/hts-engine/hts_engine_API-1.10.tar.gz && \
    tar zxvf hts_engine_API-1.10.tar.gz && \
    cd hts_engine_API-1.10/ && \
    ./configure && \
    make && \
    make install && \
    cd ../ && \
    rm -r hts_engine_API-1.10*

RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk-1.09.tar.gz && \
    tar zxvf open_jtalk-1.09.tar.gz && \
    cd open_jtalk-1.09/ && \
    ./configure --with-hts-engine-header-path=/usr/local/include --with-hts-engine-library-path=/usr/local/lib --with-charset=UTF-8 && \
    make && \
    make install && \
    cd ../ && \
    rm -r open_jtalk-1.09*

RUN wget http://downloads.sourceforge.net/open-jtalk/hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    tar zxvf hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    mv hts_voice_nitech_jp_atr503_m001-1.05 /usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05 && \
    rm hts_voice_nitech_jp_atr503_m001-1.05.tar.gz

RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk_dic_utf_8-1.09.tar.gz && \
    tar zxvf open_jtalk_dic_utf_8-1.09.tar.gz && \
    mv open_jtalk_dic_utf_8-1.09 /usr/local/lib/open_jtalk_dic_utf_8-1.09 && \
    rm open_jtalk_dic_utf_8-1.09.tar.gz

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

# Open JTalk APIをセットアップ
COPY ./open-jtalk-api /usr/local/src/open-jtalk-api

RUN cd open-jtalk-api/ && \
    mvn clean package && \
    cp /usr/local/src/open-jtalk-api/target/open-jtalk-api-0.0.1-SNAPSHOT.jar /usr/local/lib/open-jtalk-api.jar && \
    rm -r ~/.m2/ && \
    rm -r /usr/local/src/open-jtalk-api/

EXPOSE 8080

CMD ["java", "-jar", "/usr/local/lib/open-jtalk-api.jar"]
