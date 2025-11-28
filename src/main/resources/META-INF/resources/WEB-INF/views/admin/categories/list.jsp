<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Danh mục</title>
    </head>
<body>
    <div class="card mt-4">
        <div class="card-header bg-white d-flex justify-content-between align-items-center">
            <h4 class="m-0 text-primary">Quản lý Danh mục</h4>
            <a href="<c:url value='/admin/categories/add'/>" class="btn btn-success">
                <i class="fa-solid fa-plus"></i> Thêm mới
            </a>
        </div>
        
        <div class="card-body">
            <c:if test="${not empty message}">
                <div class="alert alert-info alert-dismissible fade show" role="alert">
                    <i class="fa-solid fa-bell me-2"></i>${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <form action="<c:url value='/admin/categories/search'/>" method="get" class="mb-3 d-flex">
                <input type="text" name="name" class="form-control me-2" placeholder="Nhập tên danh mục để tìm...">
                <button class="btn btn-outline-primary">Tìm kiếm</button>
            </form>

            <table class="table table-bordered table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th class="text-center" width="5%">ID</th>
                        <th class="text-center" width="15%">Hình ảnh</th>
                        <th width="15%">Mã Code</th>
                        <th>Tên Danh mục</th>
                        <th class="text-center" width="15%">Trạng thái</th>
                        <th class="text-center" width="15%">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td class="text-center fw-bold">${category.categoryId}</td>
                            
                            <td class="text-center">
                                <c:if test="${category.images != null}">
                                    <img src="<c:url value='/image?fname=${category.images}'/>" 
                                         style="width: 70px; height: 50px; object-fit: cover; border-radius: 5px; border: 1px solid #ddd;">
                                </c:if>
                                <c:if test="${category.images == null}">
                                    <span class="text-muted small">No Image</span>
                                </c:if>
                            </td>

                            <td>
                                <span class="badge bg-light text-dark border">${category.categoryCode}</span>
                            </td>

                            <td class="fw-semibold text-primary">${category.categoryName}</td>
                            
                            <td class="text-center">
                                <c:if test="${category.status == 1}">
                                    <span class="badge bg-success">Hoạt động</span>
                                </c:if>
                                <c:if test="${category.status == 0}">
                                    <span class="badge bg-danger">Khóa</span>
                                </c:if>
                            </td>

                            <td class="text-center">
                                <a href="<c:url value='/admin/categories/edit/${category.categoryId}'/>" 
                                   class="btn btn-sm btn-warning text-white me-1" title="Sửa">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                                
                                <a href="<c:url value='/admin/categories/delete/${category.categoryId}'/>" 
                                   class="btn btn-sm btn-danger" 
                                   title="Xóa"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty categories}">
                        <tr>
                            <td colspan="6" class="text-center py-4 text-muted">
                                <i class="fa-solid fa-folder-open fa-2x mb-2"></i><br>
                                Chưa có dữ liệu danh mục nào.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>