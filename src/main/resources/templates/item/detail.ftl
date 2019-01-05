<!DOCTYPE html>
<html>
<head lang="en">
<title>Spring Boot Demo - FreeMarker</title>
</head>
<body>
    <h2>首页<h2>
    本地预览的商品id为${item.id}<br>
     商品名称为  
    <font>${item.productName!''}</font><br>
  <img src="${item.thumb!''}">
    
    
</body>
</html>