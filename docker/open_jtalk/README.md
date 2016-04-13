# open_jtalk

Open JTalkをDockerコンテナーで動作させます。

## Open JTalkとは

Open JTalkは音声合成ソフトウェアです。日本語の音声を合成することができます。

Open JTalkはいくつかのソフトウェアで構成されており、セットアップがなかなか面倒です。このDockerイメージは、セットアップ済みのOpen JTalkを提供することで、簡単にOpen JTalkを使用できるようにしました。

## 使い方

発声させる文字列を標準入力から受け取り、生成した音声wavデータを標準出力に出力します。以下のように使用します。

```
echo こんにちは | docker run -i --rm u6kapps/open_jtalk > hello.wav
```

環境変数で`open_jtalk`コマンドに渡すオプションを指定できます。

|環境変数|意味|デフォルト値|
|---|---|---|
|`OPEN_JTALK_OPTIONS`|音声ファイル、辞書ディレクトリ以外の`open_jtalk`に渡すオプション|`-s 48000 -p 240`|
|`OPEN_JTALK_VOICE_FILE`|`-m`で指定する音声ファイル。「指定できる音声ファイル」を参照|`/usr/local/lib/htsvoice/nitech_jp_atr503_m001.htsvoice`|
|`OPEN_JTALK_DIC_DIR`|`-x`で指定する辞書ディレクトリ。変更する必要はありません|`/usr/local/lib/open_jtalk_dic`|

環境変数を指定する場合は、以下のように指定します。

```
echo こんばんは | docker run -i --rm -e OPEN_JTALK_OPTIONS="-s 48000 -p 300" u6kapps/open_jtalk > hello.wav
```

## 指定できる音声ファイル

* /usr/local/lib/htsvoice/nitech_jp_atr503_m001.htsvoice
* /usr/local/lib/htsvoice/mei_angry.htsvoice
* /usr/local/lib/htsvoice/mei_bashful.htsvoice
* /usr/local/lib/htsvoice/mei_happy.htsvoice
* /usr/local/lib/htsvoice/mei_normal.htsvoice
* /usr/local/lib/htsvoice/mei_sad.htsvoice

## 使用している主なソフトウェア

* hts_engine API version 1.10
    * Webサイト: [hts_engine API](http://hts-engine.sourceforge.net/)
    * ライセンス: 修正BSDライセンス
* Open JTalk version 1.09
    * Webサイト: [Open JTalk](http://open-jtalk.sourceforge.net/)
    * ライセンス: 修正BSDライセンス
* Open JTalk version 1.09向け辞書 (UTF-8)
    * Webサイト: [Open JTalk](http://open-jtalk.sourceforge.net/)
    * ライセンス: 修正BSDライセンス
* Nitech Japanese Speech Database "NIT ATR503 M001"
    * Webサイト: [HMM-based speech synthesis system (HTS)](http://hts.sp.nitech.ac.jp/)
    * ライセンス: [Creative Commons Attribution 3.0](https://creativecommons.org/licenses/by/3.0/)
* MMDAgent

## リンク

* GitHub: [u6k/open_jtalk-docker](https://github.com/u6k/open_jtalk-docker)
* Docker Hub: [u6kapps/open_jtalk](https://hub.docker.com/r/u6kapps/open_jtalk/)
* Redmine: [open_jtalk-docker](https://myredmine-u6kapps.rhcloud.com/projects/openjtalk-docker)
* Blog: [u6k.log()](http://blog.u6k.me/)
