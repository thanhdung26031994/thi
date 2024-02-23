<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 23/02/2024
  Time: 08:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa hoá đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">

    <div class="row justify-content-center mt-4">
        <form class="col-md-6" method="post">
            <h1 class="">Chỉnh sửa học sinh</h1>
            <input type="hidden" class="form-control" name="id" id="id" value="${requestScope["student"].getId()}">

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" id="name" value="${requestScope["student"].getName()}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" id="email" value="${requestScope["student"].getEmail()}">
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date Of Birth</label>
                <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth" value="${requestScope["student"].getDateOfBirth()}">
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" id="address" value="${requestScope["student"].getAddress()}">
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" name="phone" id="phone" value="${requestScope["student"].getPhone()}">
            </div>
            <div class="form-group">
                <label for="idClass">Lớp</label>
                <select id="idClass" class="form-control" name="idClass">
                    <c:forEach items="${classes}" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group mt-2">
                <button class="btn btn-success" role="button" type="submit">Lưu thông tin</button>
                <a href="#">Quay lại</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

