<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container mt-4">
    <h3 class="mb-4 text-secondary">Admin Dashboard</h3>

    <div class="row g-3">
        <div class="col-md-4">
            <a href="<c:url value='/admin/categories'/>" class="card text-decoration-none shadow-sm h-100 border-start border-4 border-primary">
                <div class="card-body d-flex align-items-center p-4">
                    <div class="bg-primary bg-opacity-10 p-3 rounded-circle me-3 text-primary">
                        <i class="fa-solid fa-folder-open fa-2x"></i>
                    </div>
                    <div>
                        <h5 class="card-title mb-0 text-dark">Danh Mục</h5>
                        <small class="text-muted">Quản lý thể loại</small>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="<c:url value='/admin/videos'/>" class="card text-decoration-none shadow-sm h-100 border-start border-4 border-success">
                <div class="card-body d-flex align-items-center p-4">
                    <div class="bg-success bg-opacity-10 p-3 rounded-circle me-3 text-success">
                        <i class="fa-brands fa-youtube fa-2x"></i>
                    </div>
                    <div>
                        <h5 class="card-title mb-0 text-dark">Video</h5>
                        <small class="text-muted">Quản lý phim/clip</small>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>