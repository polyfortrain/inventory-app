# Javaクラス設計書

[システム構成設計書](../external-design/system-architecture.md)と[ユースケース仕様書](../requirements/use-cases.md)をもとに、Java Webアプリケーションの主要クラスを設計する。

## 1. クラス図

![クラス図](diagrams/class-diagram.svg)

- [編集用（draw.io）](diagrams/class-diagram.drawio)
- [編集用（PlantUML）](diagrams/class-diagram.puml)

**設計方針：** 初心者向けのMVC＋DAO構成とし、`InventoryService`は設けない。Controller（Servlet）がDAOインターフェースを利用する。JSPはViewであり、このJavaクラス図には含めない。

図では **現在実装済みのクラス** と **後続Issueで実装予定のクラス** を区別する。クラス内のメソッドは原則として現在の実装状況を表示しており、未実装のCRUDメソッドを実装済みとして扱わない。

## 2. パッケージとクラスの役割

| パッケージ | クラス・インターフェース | 役割 | 状態 |
|---|---|---|---|
| `inventory.app.controller` | `DbCheckServlet` | HTTP要求を受け、DAOから取得した一覧を確認用テキストで返す | 実装済み（Issue #33） |
| `inventory.app.controller` | `InventoryServlet` | HTTP要求、入力値検証、DAO呼出し、画面遷移を担当する | 未実装（Issue #34以降） |
| `inventory.app.model.dto` | `Inventory` | 在庫ID・在庫名・数量を保持するDTO | 実装済み |
| `inventory.app.model.dao` | `InventoryDao` | 在庫データに対する操作を定義するインターフェース | `findAll()`のみ実装済み |
| `inventory.app.model.dao` | `PostgresInventoryDao` | JDBCを用いてPostgreSQLへアクセスし、結果をDTOに変換する | 一覧取得のみ実装済み |
| `inventory.app.model.db` | `DbConnection` | 環境変数から接続情報を取得し、JDBC接続を返す | 実装済み |

`HealthServlet`と`ConnectCheckServlet`は開発・接続確認用であり、主要クラス図から省略する。JSPは`src/main/webapp/WEB-INF/views/`に配置する。

## 3. 依存関係

基本的な処理の流れは次のとおり。

`Servlet（Controller） → InventoryDao（DAOインターフェース） → PostgresInventoryDao（DAO実装） → DbConnection / PostgreSQL`

- `PostgresInventoryDao`は`InventoryDao`を実装する。Controllerは`InventoryDao`型を用いてDAOを呼び出す。
- `Inventory`はDAOが生成し、Controllerへ渡すデータ保持用のDTOである。
- DB接続情報は`DbConnection`が環境変数から読み込む。パスワードをソースコードへ書かない。
- DAOは原則としてSQLとデータ変換を担当し、画面制御や入力エラーメッセージの表示をしない。
- Service層を省略するため、入力値の形式・範囲の検証はControllerが担当する。DBのCHECK制約は別途有効とする。

## 4. CRUD実装予定

| 機能 | DAO操作の予定 | 実装Issue |
|---|---|---|
| 在庫一覧 | `findAll(): List<Inventory>` | #33 実装済み。JSP画面は #34 |
| 在庫登録 | `insert(name, quantity): int` | #36 |
| 在庫更新 | `findById(id)` / `update(id, name, quantity)` | #38 |
| 在庫削除 | `deleteById(id): boolean` | #40 |

在庫名は1〜100文字、空白のみ不可、数量は0〜999,999の整数とする（FR-05）。無効な入力値や存在しない在庫IDによって、他のデータが変更されないようにする。

**注意：** `InventoryServlet`、`insert()`、`update()`、`deleteById()`などは設計上の予定であり、現時点のJavaソースには存在しない。メソッドの詳細な引数・戻り値は各実装Issueで最終決定する。

## 5. 既知の制約と修正予定

現行`PostgresInventoryDao.findAll()`は`SQLException`を捕捉し、`printStackTrace()`の後に結果リストを返す。接続失敗と在庫0件を呼び出し元で区別できない問題があるが、**v0.1.0では変更せず、v0.1.1で修正する方針**とする。

この設計書はクラス配置と責務の変更を反映するものであり、Javaコードの挙動を変更しない。
