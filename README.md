# DeepLink App

Androidアプリとローカルホストで動作するWebページ間でディープリンクを使ったデータ通信を実現するサンプルアプリです。

## 機能

- WebViewでローカルホストのWebサイトを表示
- Webページのテキストボックスから任意の文字列を入力
- カスタムスキーマ（`myapp://`）を使ったディープリンクでアプリに戻る
- ディープリンクで送信されたデータをアプリ内で表示

## 技術スタック

- **言語**: Kotlin
- **最小SDKバージョン**: 24 (Android 7.0)
- **ターゲットSDKバージョン**: 34

## セットアップ

### 1. プロジェクトのクローン

```bash
git clone https://github.com/USERNAME/deeplinkapp.git
cd deeplinkapp
```

### 2. Android Studioで開く

1. Android Studioを起動
2. "Open an Existing Project"を選択
3. クローンしたプロジェクトフォルダを選択

### 3. ローカルホストのWebサーバー起動

HTMLファイル（`index.html`）を任意のディレクトリに配置し、以下のコマンドでWebサーバーを起動※HTMLファイルは別のリポジトリ：

```bash
python3 -m http.server 8080
```

### 4. IPアドレスの設定

**エミュレータの場合**: `MainActivity.kt`の28行目はそのまま（`10.0.2.2:8080`）

**実機の場合**: MacのIPアドレスを確認して設定

```bash
# MacのIPアドレスを確認
ifconfig | grep "inet " | grep -v 127.0.0.1
```

`MainActivity.kt`の28行目を変更：
```kotlin
webView.loadUrl("http://YOUR_MAC_IP:8080/index.html")
```

### 5. アプリを実行

Android StudioでRunボタンをクリックしてアプリを起動

## 使い方

1. アプリを起動すると「ディープリンクサイトを表示」ボタンが表示される
2. ボタンをタップしてWebサイトを表示
3. テキストボックスに任意の文字列を入力
4. 「アプリに送信」ボタンをタップ
5. アプリに戻り、送信したデータが表示される

## ディープリンクの形式

```
myapp://callback?data=送信したい文字列
```

## ライセンス

MIT License

## 作者

Akinari Hashimoto
