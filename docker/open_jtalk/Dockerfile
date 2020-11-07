FROM ubuntu:latest as builder
LABEL maintainer="u6k <u6k.apps@gmail.com>"

ENV HTS_VERSION 1.10
ENV JTALK_VERSION 1.11
ENV DICT_VERSION 1.11
ENV VMODEL_VERSION 1.05
ENV MMD_VERSION 1.8

# ビルドに必要なソフトウェアをインストール
RUN apt-get update && \
    apt-get install -y wget git build-essential unzip

WORKDIR /usr/local/src/

# Open JTalkをセットアップ
RUN wget http://downloads.sourceforge.net/hts-engine/hts_engine_API-${HTS_VERSION}.tar.gz && \
    tar zxvf hts_engine_API-${HTS_VERSION}.tar.gz && \
    cd hts_engine_API-${HTS_VERSION}/ && \
    ./configure && \
    make && \
    make install

COPY manobi.patch manobi.patch

RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk-${JTALK_VERSION}.tar.gz && \
    tar zxvf open_jtalk-${JTALK_VERSION}.tar.gz && \
    cd open_jtalk-${JTALK_VERSION}/ && \
    patch -p0 < /usr/local/src/manobi.patch && \
    ./configure --with-hts-engine-header-path=/usr/local/include --with-hts-engine-library-path=/usr/local/lib --with-charset=UTF-8 && \
    make && \
    make install

# 辞書をセットアップ
RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk_dic_utf_8-${DICT_VERSION}.tar.gz && \
  tar zxvf open_jtalk_dic_utf_8-${DICT_VERSION}.tar.gz && \
  cp -r open_jtalk_dic_utf_8-${DICT_VERSION} /usr/local/lib/open_jtalk_dic

# 音響モデルをセットアップ
RUN mkdir /usr/local/lib/htsvoice/

RUN wget http://downloads.sourceforge.net/open-jtalk/hts_voice_nitech_jp_atr503_m001-${VMODEL_VERSION}.tar.gz && \
  tar zxvf hts_voice_nitech_jp_atr503_m001-${VMODEL_VERSION}.tar.gz && \
  cp hts_voice_nitech_jp_atr503_m001-${VMODEL_VERSION}/nitech_jp_atr503_m001.htsvoice /usr/local/lib/htsvoice/

RUN wget http://downloads.sourceforge.net/mmdagent/MMDAgent_Example-${MMD_VERSION}.zip && \
  unzip MMDAgent_Example-${MMD_VERSION}.zip && \
  cp MMDAgent_Example-${MMD_VERSION}/Voice/mei/*.htsvoice /usr/local/lib/htsvoice/

RUN rm -rf /usr/local/src/

FROM ubuntu:latest
WORKDIR /
COPY --from=builder /usr/local/ /usr/local

# エントリーポイントをセットアップ
COPY ./entrypoint.sh /usr/local/bin/entrypoint.sh

RUN chmod a+x /usr/local/bin/entrypoint.sh

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
