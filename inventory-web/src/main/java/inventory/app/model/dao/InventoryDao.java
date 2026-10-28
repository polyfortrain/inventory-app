package inventory.app.model.dao;

import java.util.List;

import inventory.app.model.dto.Inventory;

/**
 * 「在庫データ（inventoryテーブル）を操作すること」
 * を定義したインターフェース。
 */
public interface InventoryDao {
	// Issue#33対応。（機能要件FR-01参照）
	List<Inventory> findAll(); 
}
