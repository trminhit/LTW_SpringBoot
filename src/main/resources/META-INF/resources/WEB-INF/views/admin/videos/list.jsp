<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="card mt-4">
    <div class="card-header bg-white d-flex justify-content-between align-items-center">
        <h4>Quản lý Video</h4>
        <a href="<c:url value='/admin/videos/add'/>" class="btn btn-primary">
            <i class="fa-solid fa-plus"></i> Thêm mới
        </a>
    </div>
    <div class="card-body">
        <form action="<c:url value='/admin/videos'/>" method="get" class="mb-3 d-flex">
            <input type="text" name="title" value="${title}" class="form-control me-2" placeholder="Nhập tên video để tìm...">
            <button class="btn btn-outline-success">Tìm kiếm</button>
        </form>

        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>

        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Poster</th>
                    <th>Tiêu đề</th>
                    <th>Danh mục</th>
                    <th>Lượt xem</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${videos}" var="v">
                    <tr>
                        <td>${v.videoId}</td>
                        <td class="text-center">
                            <c:if test="${v.poster != null}">
                                <img src="<c:url value='/image?fname=${v.poster}'/>" width="100" />
                            </c:if>
                        </td>
                        <td>${v.title}</td>
                        <td>
                            <span class="badge bg-info">${v.category.categoryName}</span>
                        </td>
                        <td>${v.views}</td>
                        <td>${v.active == 1 ? 'Hiện' : 'Ẩn'}</td>
                        <td>
                            <a href="<c:url value='/admin/videos/edit/${v.videoId}'/>" class="btn btn-sm btn-warning">Sửa</a>
                            <a href="<c:url value='/admin/videos/delete/${v.videoId}'/>" class="btn btn-sm btn-danger" 
                               onclick="return confirm('Bạn có chắc muốn xóa video này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty videos}">
                    <tr><td colspan="7" class="text-center">Không tìm thấy video nào.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>