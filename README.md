# open-jtalk-api

Open JTalkをWebAPIとして使用します。

## コンテナー起動方法

```
docker -p 8080:8080 u6kapps/open-jtalk-api
```

## 使い方

音声ファイルを取得

```
curl -v -o hello.wav http://localhost:8080/voice?message=こんにちは
```

## Dockerビルドの注意

`m2/`にローカルPCの`~/.m2/`の内容をコピーすると、Dockerビルドを高速化することができます。具体的には、Maven依存ライブラリのダウンロードを省略することで、ビルドを高速化します。
