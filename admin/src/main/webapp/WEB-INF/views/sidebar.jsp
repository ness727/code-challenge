<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="sidebar" data-background-color="dark">
    <div class="sidebar-logo">
        <!-- Logo Header -->
        <div class="logo-header" data-background-color="dark">
            <a href="" class="logo">
                <img
                        src="/assets/img/kaiadmin/logo_light.svg"
                        alt="navbar brand"
                        class="navbar-brand"
                        height="20"
                />
            </a>
            <div class="nav-toggle">
                <button class="btn btn-toggle toggle-sidebar">
                    <i class="gg-menu-right"></i>
                </button>
                <button class="btn btn-toggle sidenav-toggler">
                    <i class="gg-menu-left"></i>
                </button>
            </div>
            <button class="topbar-toggler more">
                <i class="gg-more-vertical-alt"></i>
            </button>
        </div>
        <!-- End Logo Header -->
    </div>
    <div class="sidebar-wrapper scrollbar scrollbar-inner">
        <div class="sidebar-content">
            <ul class="nav nav-secondary">

                <c:if test="${not empty auth}">
                    <li class="nav-item active">
                    <a data-bs-toggle="collapse" href="#base">
                        <i class="fas fa-layer-group"></i>
                        <p>관리 내역</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse show" id="base">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="/user">
                                    <span class="sub-item">Users</span>
                                </a>
                            </li>
                            <li>
                                <a href="/problem/list?size=5">
                                    <span class="sub-item">Problems</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>