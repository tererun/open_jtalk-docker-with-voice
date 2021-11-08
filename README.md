# open_jtalk-docker-with-voice

Open JTalkをWebAPIとして使用します。

## コンテナー起動方法

以下のコマンドで、open-jtalk-apiコンテナーが起動して、WebAPIで待ち受けるようになります。

```
docker run -d -p 8080:8080 u6kapps/open-jtalk-api
```

## 使い方

とりあえずデフォルトのパラメータ値で音声データを取得するには、以下のように実行します。

```
curl -v -o hello.wav http://localhost:8080/voice?text=こんにちは
```

リクエスト・パラメータに指定できる項目は、Open JTalkのオプションにほぼ準拠しています(`text`だけ独自)。具体的には、以下のパラメータを指定できます。

|パラメータ名|型|意味|省略時|設定範囲|
|---|---|---|---|---|
|text|string|voice text|required||
|voice|string|voice path|/usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05/nitech_jp_atr503_m001.htsvoice||
|s|integer|sampling frequency|auto|1--|
|p|integer|frame period (point)|auto|1--|
|a|float|all-pass constant|auto|0.0--1.0|
|b|float|postfiltering coefficient|0.0|0.0--1.0|
|r|float|speech speed rate|1.0|0.0--|
|fm|float|additional half-tone|0.0||
|u|float|voiced/unvoiced threshold|0.5|0.0--1.0|
|jm|float|weight of GV for spectrum|1.0|0.0--|
|jf|float|weight of GV for log F0|1.0|0.0--|
|g|float|volume (dB)|0.0||
|z|integer|audio buffer size (if i==0, turn off)|0|0--|

パラメータを指定する場合は、例えば、以下のように実行します。

```
curl -v -o hello.wav http://localhost:8080/voice?text=こんにちは\&s=44000\&p=128\&a=0.5\&b=0.1\&r=1.5\&fm=0.1\&u=0.6\&jm=1.1\&jf=1.1\&g=60.0\&z=0
```

HTMLページに埋め込む場合は、以下のようにタグを記述します。

```
<audio src="http://localhost:58080/voice?text=こんにちは" controls></audio>
```

## Dockerビルドの注意

`m2/`にローカルPCの`~/.m2/`の内容をコピーすると、Dockerビルドを高速化することができます。具体的には、Maven依存ライブラリのダウンロードを省略することで、ビルドを高速化します。

**Dockerはネットワークエラー起こしやすいので、もしbuild時にエラーが出たらここに依存関係を入れる事を推奨します。**