<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav
        class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom"
>
    <div class="container-fluid">


<%--    로그인 했을 때만 표시    --%>
        <c:if test="${not empty auth}">
            <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">

            <li class="nav-item topbar-icon dropdown hidden-caret">
                <a
                        class="nav-link"
                        href="/logout"
                        id="messageDropdown"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false"
                >
                    Logout
<%--                    <i class="fa fa-envelope"></i>--%>
                </a>
            </li>

            <li class="nav-item topbar-user dropdown hidden-caret">
                <a
                        class="dropdown-toggle profile-pic"
                        data-bs-toggle="dropdown"
                        href="#"
                        aria-expanded="false"
                >
                    <div class="avatar-sm">
                        <img
                                src="/assets/img/profile.jpg"
                                alt="..."
                                class="avatar-img rounded-circle"
                        />
                    </div>
                    <span class="profile-username">
                      <span class="op-7">Hi,</span>
                      <span class="fw-bold">${auth.nickname}</span>
                    </span>
                </a>
                <ul class="dropdown-menu dropdown-user animated fadeIn">
                    <div class="dropdown-user-scroll scrollbar-outer">
                        <li>
                            <div class="user-box">
                                <div class="avatar-lg">
                                    <img
                                            src="/assets/img/profile.jpg"
                                            alt="image profile"
                                            class="avatar-img rounded"
                                    />
                                </div>
                                <div class="u-text">
                                    <h4>Hizrian</h4>
                                    <p class="text-muted">hello@example.com</p>
                                    <a
                                            href="profile.html"
                                            class="btn btn-xs btn-secondary btn-sm"
                                    >View Profile</a
                                    >
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">My Profile</a>
                            <a class="dropdown-item" href="#">My Balance</a>
                            <a class="dropdown-item" href="#">Inbox</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Account Setting</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Logout</a>
                        </li>
                    </div>
                </ul>
            </li>
        </ul>
        </c:if>
    </div>
</nav>