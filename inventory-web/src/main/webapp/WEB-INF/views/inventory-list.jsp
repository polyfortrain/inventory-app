<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="inventory.app.model.dto.Inventory" %>
<%
    List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventories");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在庫一覧 | 在庫管理 Webアプリ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventory.css">
</head>
<body>
    <header class="site-header">
        <div class="site-header-inner">在庫管理 Webアプリ</div>
    </header>

    <main class="container">
        <div class="page-header">
            <div>
                <p class="page-code">SCR-01</p>
                <h1>在庫一覧</h1>
                <p class="page-description">登録済みの在庫情報を表示します。</p>
            </div>
            <!-- Issue #36：在庫登録機能で有効化する。 -->
            <button type="button" class="button button-primary" disabled
                    title="新規登録は今後実装予定です">＋ 新規登録</button>
        </div>

        <section class="table-card" aria-label="在庫一覧">
            <div class="table-scroll">
                <table>
                    <thead>
                        <tr>
                            <th scope="col">在庫ID</th>
                            <th scope="col">在庫名</th>
                            <th scope="col" class="number">数量</th>
                            <th scope="col" class="action-heading">操作</th>
                        </tr>
                    </thead>
                    <tbody>
<% if (inventories == null || inventories.isEmpty()) { %>
                        <tr>
                            <td colspan="4" class="empty-message">在庫情報がありません</td>
                        </tr>
<% } else { %>
<%     for (Inventory inventory : inventories) { %>
                        <tr>
                            <td><%= inventory.getId() %></td>
                            <td class="item-name"><%= inventory.getName() %></td>
                            <td class="number"><%= inventory.getQuantity() %></td>
                            <td class="actions">
                                <!-- Issue #38：在庫更新機能で有効化する。 -->
                                <button type="button" class="button button-outline" disabled
                                        title="編集は今後実装予定です">編集</button>
                                <!-- Issue #40：在庫削除機能で有効化する。 -->
                                <button type="button" class="button button-outline" disabled
                                        title="削除は今後実装予定です">削除</button>
                            </td>
                        </tr>
<%     } %>
<% } %>
                    </tbody>
                </table>
            </div>
        </section>
        <p class="footnote">登録・編集・削除の操作は、後続のIssueで実装予定です。</p>
    </main>
</body>
</html>
