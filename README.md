# open_jtalk

Open JTalkをDockerコンテナーで動作させます。

## 使い方

```
docker run -v /tmp:/tmp u6kapps/open_jtalk
```

`/tmp/hello.txt`を読み取り、`/tmp/hello.wav`を出力します。現時点では、各種パラメータを指定することを想定していません。

## 使用する主なソフトウェア

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
