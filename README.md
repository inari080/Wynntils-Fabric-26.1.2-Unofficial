# Wynntils 4.2.13 — unofficial Fabric port for Minecraft 26.1.2

**日本語案内:** Wynntils 4.2.13をMinecraft 26.1.2 / Fabric向けに移植した非公式ビルドです。Team Wynntilsとは無関係で、公式版としてサポートされているものではありません。

## 状況

Minecraft 26.1.2のFabric環境で起動し、Wynncraftへ接続して通常プレイできることを確認しました。全機能の網羅的な動作確認は済んでいません。別バージョン、NeoForge、ほかのMinecraftサーバーでは確認していません。

## ダウンロードと導入

現在、ビルド済みJARはGitHub Releasesでは配布していません。以下の手順でソースからビルドしてください。

1. Java 25をインストールします。
2. このリポジトリをGitHubの **Code → Download ZIP** から取得して展開します。
3. 展開先でビルドします。

Windows PowerShell:

```powershell
.\gradlew.bat :fabric:shadowJar --no-daemon
```

macOS / Linux:

```sh
./gradlew :fabric:shadowJar --no-daemon
```

4. `fabric/build/libs/` に生成された `wynntils-4.2.13-fabric+MC-26.1.2.jar` を、Minecraft 26.1.2 / Fabric Loaderのプロファイルにある `mods` フォルダーへ入れます。
5. 既存のWynntils JARがある場合は重複を避けるため置き換え、Fabricプロファイルで起動します。

## 不具合の報告

起動失敗やゲーム内の問題があれば、再現手順と発生した操作を添えて [Issues](https://github.com/inari080/Wynntils-Fabric-26.1.2-Unofficial/issues) に報告してください。必要に応じて、その時点の `latest.log` のエラー周辺も添えてください。ログに個人情報が含まれていないか、投稿前に確認してください。

## 変更点

Minecraft 26.1.2向けにFabricのビルド設定、Mixinの注入先、Access Widenerの登録、リソースパック読み込み処理などを調整しています。GitHub ActionsでFabric JARのビルドを確認しています。

## ライセンスとクレジット

WynntilsはGNU Lesser General Public License v3.0 onlyでライセンスされています。このリポジトリには元の `LICENSE` と著作権表示を含め、移植部分も同ライセンスで公開しています。Wynntilsおよび同プロジェクトのアセットはTeam Wynntilsに帰属します。詳細は[元プロジェクト](https://github.com/Wynntils/Wynntils)をご覧ください。

この移植は非公式のコミュニティ版です。「Wynntils」は元プロジェクトを示す目的で使用しています。

---

## English summary

Unofficial Fabric port of Wynntils 4.2.13 for Minecraft 26.1.2. It has been launched and used for normal Wynncraft gameplay, but not every feature has been exhaustively tested. No prebuilt JAR is currently published; build with Java 25 using `:fabric:shadowJar`. See the Japanese instructions above. This project is not affiliated with Team Wynntils; see the upstream repository and included LGPL-3.0-only license for attribution and license terms.
