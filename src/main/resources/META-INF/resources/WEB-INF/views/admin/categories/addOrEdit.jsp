<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Management</title>
</head>
<body>
    <div class="container mt-4">
        <form action="<c:url value='/admin/categories/saveOrUpdate' />" method="POST" enctype="multipart/form-data">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h5 class="m-0">${category.isEdit ? 'Edit Category' : 'Add New Category'}</h5>
                </div>
                <div class="card-body">
                    <input type="hidden" name="isEdit" value="${category.isEdit}">
                    
                    <div class="mb-3">
                        <label class="form-label">Category ID:</label> 
                        <input type="text" readonly class="form-control" 
                               value="${category.categoryId}" name="categoryId" placeholder="Auto Generate">
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Category Code:</label> 
                        <input type="text" class="form-control" 
                               value="${category.categoryCode}" name="categoryCode" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Category Name:</label> 
                        <input type="text" class="form-control" 
                               value="${category.categoryName}" name="categoryName" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Category Image:</label>
                        <input type="file" name="imageFile" class="form-control" accept="image/*">
                        
                        <c:if test="${category.images != null}">
                            <div class="mt-2">
                                <img src="<c:url value='/image?fname=${category.images}'/>" 
                                     class="img-thumbnail" width="100" style="object-fit: cover;">
                                <small class="text-muted">Current Image</small>
                            </div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Status:</label>
                        <select name="status" class="form-select">
                            <option value="1" ${category.status == 1 ? 'selected' : ''}>Active</option>
                            <option value="0" ${category.status == 0 ? 'selected' : ''}>Inactive</option>
                        </select>
                    </div>

                </div>
                <div class="card-footer d-flex justify-content-between">
                    <a href="<c:url value='/admin/categories' />" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Back to List
                    </a>
                    <button class="btn btn-success" type="submit">
                        <i class="fas fa-save"></i> ${category.isEdit ? 'Update' : 'Save'}
                    </button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>