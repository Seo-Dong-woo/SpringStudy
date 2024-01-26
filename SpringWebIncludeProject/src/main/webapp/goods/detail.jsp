<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="5">
						<img src="${vo.goods_poster }" style="width: 290px; height: 400px">
					</td>
					<td colspan="2">
						<h3>${vo.goods_name }&nbsp;<span style="color: orange">${vo.goods_sub }</span></h3>
					</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">첫구매할인가</th>
					<td width="50%">${vo.goods_first_price }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">할인율</th>
					<td width="50%">${vo.goods_discount }%</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">가격</th>
					<td width="50%">${vo.goods_price }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">배송비</th>
					<td width="50%">${vo.goods_delivery }</td>
				</tr>
				<tr>
					<td colspan="3">
						<pre style="white-space: pre-wrap; border: none; background-color: white"></pre>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
					<a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>