# テーブル定義書

[物理データモデル](physical-data-model.md)に基づき、PostgreSQLの在庫テーブルを定義する。

## 1. テーブル情報

| 項目 | 内容 |
|---|---|
| テーブル名 | `inventory` |
| 用途 | 在庫ID・在庫名・数量を保存する |
| 主キー | `inventory_id` |

## 2. カラム定義

| カラム名 | データ型 | NULL | 制約・設定 |
|---|---|---|---|
| `inventory_id` | `INTEGER` | 不可 | PK、`GENERATED ALWAYS AS IDENTITY` |
| `inventory_name` | `VARCHAR(100)` | 不可 | 1〜100文字、空白のみ禁止 |
| `quantity` | `INTEGER` | 不可 | `CHECK (quantity BETWEEN 0 AND 999999)` |

- 在庫名の重複は許容する（UNIQUE制約なし）。
- 在庫名の空白のみの入力はCHECK制約でも防止する。
- 主キー用のインデックス以外は、現段階では追加しない。

## 3. DDL

[PostgreSQL用CREATE TABLE文](sql/inventory.sql)

※ DDLは設計成果物であり、データベースへの実行は実装工程で行う。
