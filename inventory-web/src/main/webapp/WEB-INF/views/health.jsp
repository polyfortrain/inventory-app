<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>環境構築チェック</title>
</head>
<body>
    <h1>環境構築 OK</h1>
    <p>Servlet と JSP の連携を確認しました。</p>
    <p>Servletから渡された結果：<strong>${status}</strong></p>
    <p>※ この確認ではPostgreSQLへの接続は行っていません。</p>
    <p><a href="${pageContext.request.contextPath}/">トップに戻る</a></p>
</body>
</html>
