FROM ubuntu:latest
MAINTAINER u6k <u6k.apps@gmail.com>

RUN apt-get update && \
    apt-get install -y wget build-essential

WORKDIR /usr/local/src/

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

CMD ["open_jtalk", "-m", "/usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05/nitech_jp_atr503_m001.htsvoice", "-x", "/usr/local/lib/open_jtalk_dic_utf_8-1.09/", "-ow", "/tmp/hello.wav", "/tmp/hello.txt"]
