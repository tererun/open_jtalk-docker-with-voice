FROM ubuntu:latest
MAINTAINER u6k <u6k.apps@gmail.com>

# ビルドに必要なソフトウェアをインストール
RUN apt-get update && \
    apt-get install -y wget git build-essential unzip

WORKDIR /usr/local/src/

# Open JTalk関連のソフトウェアをセットアップ
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

RUN wget http://downloads.sourceforge.net/open-jtalk/hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    tar zxvf hts_voice_nitech_jp_atr503_m001-1.05.tar.gz && \
    cp -r hts_voice_nitech_jp_atr503_m001-1.05 /usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05

RUN wget https://ja.osdn.net/frs/g_redir.php?m=jaist&f=mmdagent%2FMMDAgent_Example%2FMMDAgent_Example-1.8%2FMMDAgent_Example-1.8.zip && \
    unzip MMDAgent_Example-1.8.zip && \
    cp -r MMDAgent_Example-1.8 /usr/local/lib/MMDAgent_Example-1.8

RUN wget https://github.com/icn-lab/htsvoice-tohoku-f01/raw/master/tohoku-f01-neutral.htsvoice && \
    mkdir /usr/local/lib/tohoku-f01
    cp -r tohoku-f01-neutral.htsvoice /usr/local/lib/tohoku-f01

RUN wget http://downloads.sourceforge.net/open-jtalk/open_jtalk_dic_utf_8-1.09.tar.gz && \
    tar zxvf open_jtalk_dic_utf_8-1.09.tar.gz && \
    cp -r open_jtalk_dic_utf_8-1.09 /usr/local/lib/open_jtalk_dic_utf_8-1.09

# エントリーポイントをセットアップ
COPY ./entrypoint.sh /usr/local/bin/entrypoint.sh

RUN chmod a+x /usr/local/bin/entrypoint.sh

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
