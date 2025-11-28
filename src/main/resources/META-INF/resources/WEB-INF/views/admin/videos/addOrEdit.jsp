<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="card mt-4" style="max-width: 800px; margin: 0 auto;">
    <div class="card-header bg-primary text-white">
        <h5>${video.videoId != null ? 'Cập nhật Video' : 'Thêm Video Mới'}</h5>
    </div>
    <div class="card-body">
        <form action="<c:url value='/admin/videos/saveOrUpdate'/>" method="post" enctype="multipart/form-data">
            
            <div class="mb-3">
                <label>Mã Video (ID):</label>
                <input type="text" name="videoId" value="${video.videoId}" class="form-control" 
                       ${video.videoId != null ? 'readonly' : 'required'} >
            </div>

            <div class="mb-3">
                <label>Tiêu đề:</label>
                <input type="text" name="title" value="${video.title}" class="form-control" required>
            </div>

            <div class="mb-3">
                <label>Danh mục:</label>
                <select name="categoryId" class="form-select">
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.categoryId}" ${c.categoryId == video.category.categoryId ? 'selected' : ''}>
                            ${c.categoryName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label>Poster:</label>
                <input type="file" name="imageFile" class="form-control">
                <c:if test="${video.poster != null}">
                    <div class="mt-2">
                        <img src="/images/${video.poster}" width="100" class="img-thumbnail"/>
                        <br><small>Ảnh hiện tại</small>
                    </div>
                </c:if>
            </div>

            <div class="mb-3">
                <label>Mô tả:</label>
                <textarea name="description" class="form-control" rows="3">${video.description}</textarea>
            </div>
            
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label>Lượt xem:</label>
                    <input type="number" name="views" value="${video.views}" class="form-control">
                </div>
                <div class="col-md-6 mb-3">
                    <label>Trạng thái:</label>
                    <select name="active" class="form-select">
                        <option value="1" ${video.active == 1 ? 'selected' : ''}>Hoạt động</option>
                        <option value="0" ${video.active == 0 ? 'selected' : ''}>Khóa</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Lưu lại</button>
            <a href="<c:url value='/admin/videos'/>" class="btn btn-secondary">Hủy</a>
        </form>
    </div>
</div>