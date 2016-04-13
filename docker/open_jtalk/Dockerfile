FROM ubuntu:latest
MAINTAINER u6k <u6k.apps@gmail.com>

# ビルドに必要なソフトウェアをインストール
RUN apt-get update && \
    apt-get install -y wget git build-essential unzip

WORKDIR /usr/local/src/

# Open JTalkをセットアップ
RUN wget http://downloads.sourceforge.net/hts-engine/hts_engine_API-1.10.tar.gz && \
    tar zxvf hts_engine_API-1.10.tar.gz && \
    cd hts_engine_API-1.10/ && \
    ./configure && \
    make && \
    make install

COPY manobi.patch manobi.patch

RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk-1.09.tar.gz && \
    tar zxvf open_jtalk-1.09.tar.gz && \
    cd open_jtalk-1.09/ && \
    patch -p0 < /usr/local/src/manobi.patch && \
    ./configure --with-hts-engine-header-path=/usr/local/include --with-hts-engine-library-path=/usr/local/lib --with-charset=UTF-8 && \
    make && \
    make install

# 辞書をセットアップ
RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk_dic_utf_8-1.09.tar.gz && \
    tar zxvf open_jtalk_dic_utf_8-1.09.tar.gz && \
    cp -r open_jtalk_dic_utf_8-1.09 /usr/local/lib/open_jtalk_dic

# 音響モデルをセットアップ
RUN mkdir /usr/local/lib/htsvoice/

RUN wget http://downloads.sourceforge.net/open-jtalk/hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    tar zxvf hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    cp hts_voice_nitech_jp_atr503_m001-1.05/nitech_jp_atr503_m001.htsvoice /usr/local/lib/htsvoice/

RUN wget http://downloads.sourceforge.net/mmdagent/MMDAgent_Example-1.6.zip && \
    unzip MMDAgent_Example-1.6.zip && \
    cp MMDAgent_Example-1.6/Voice/mei/*.htsvoice /usr/local/lib/htsvoice/

# エントリーポイントをセットアップ
COPY ./entrypoint.sh /usr/local/bin/entrypoint.sh

RUN chmod a+x /usr/local/bin/entrypoint.sh

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
