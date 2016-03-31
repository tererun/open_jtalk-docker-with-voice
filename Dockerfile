FROM u6kapps/open-jtalk-api-base:0.6.1
MAINTAINER u6k <u6k.apps@gmail.com>

# 依存ライブラリをキャッシュ
COPY m2/ /root/.m2/

# プロジェクトをビルド
COPY open-jtalk-api/ /usr/local/src/open-jtalk-api/

WORKDIR /usr/local/src/open-jtalk-api/

RUN mvn clean package && \
    cp target/open-jtalk-api-0.7.0-SNAPSHOT.jar /usr/local/lib/open-jtalk-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/open-jtalk-api.jar"]
