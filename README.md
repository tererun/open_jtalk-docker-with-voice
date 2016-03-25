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
