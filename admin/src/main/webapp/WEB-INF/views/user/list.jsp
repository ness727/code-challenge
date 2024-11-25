<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Admin</title>
    <meta
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
      name="viewport"
    />
    <link
      rel="icon"
      href="../assets/img/kaiadmin/favicon.ico"
      type="image/x-icon"
    />

    <!-- Fonts and icons -->
    <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
      WebFont.load({
        google: { families: ["Public Sans:300,400,500,600,700"] },
        custom: {
          families: [
            "Font Awesome 5 Solid",
            "Font Awesome 5 Regular",
            "Font Awesome 5 Brands",
            "simple-line-icons",
          ],
          urls: ["../assets/css/fonts.min.css"],
        },
        active: function () {
          sessionStorage.fonts = true;
        },
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../assets/css/plugins.min.css" />
    <link rel="stylesheet" href="../assets/css/kaiadmin.min.css" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="../assets/css/demo.css" />
  </head>
  <body>
    <div class="wrapper">
      <!-- Sidebar -->
      <jsp:include page="../sidebar.jsp"/>
      <!-- End Sidebar -->

      <div class="main-panel">
        <div class="main-header">
          <div class="main-header-logo">
            <!-- Logo Header -->
            <div class="logo-header" data-background-color="dark">
              <a href="../../../webapp/WEB-INF/views/index.jsp" class="logo">
                <img
                  src="../assets/img/kaiadmin/logo_light.svg"
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
          <!-- Navbar Header -->
          <jsp:include page="../navbar.jsp"/>
          <!-- End Navbar -->
        </div>

        <div class="container">
          <div class="page-inner">
            <div class="page-header">
              <h3 class="fw-bold mb-3">Users</h3>
            </div>
            <div class="row">

              <div class="col-md-12">
                <div class="card">
                  <div class="card-header">
                    <div class="card-title">
                      <!-- 검색 상자 -->
                      <nav
                              class="col-md-4 navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex"
                      >
                        <form action="/user/list" method="get" class="input-group">
                          <input
                                  type="text"
                                  placeholder="nickname으로 검색..."
                                  class="form-control"
                                  name="search"
                                  value="${param.nickname}"
                          />
                          <input type="hidden" name="size" value="5" />
                          <div class="input-group-prepend" style="padding-right: 10px">
                            <button type="submit" class="btn btn-search pe-1">
                              <i class="fa fa-search search-icon"></i>
                            </button>
                          </div>
                        </form>
                      </nav>
                    </div>
                  </div>
                  <div class="card-body overflow-x-auto">
                    <table class="table table-hover">
                      <thead>
                        <tr class="container">
                          <th scope="col">id</th>

                          <th scope="col" class="d-flex">
                            providerEnum
                          </th>

                          <th scope="col">providerId</th>
                          <th scope="col">providerNickname</th>
                          <th scope="col">nickname</th>
                          <th scope="col">solveCount</th>
                          <th scope="col" class="d-flex">
                            score
                            <a href="?sort=score,asc&search=${param.nickname}&page=${param.page}"><i class="fa fa-sort-up search-icon"></i></a>
                            <a href="?sort=score,desc&search=${param.nickname}&page=${param.page}"><i class="fa fa-sort-down search-icon"></i></a>
                          </th>
                          <th scope="col">role</th>
                          <th scope="col" style="min-width: 200px;"></th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="user" items="${userPage.getContent()}">
                        <c:set var="provider" value="${user.provider}"/>
                        <tr>
                          <td>${user.id}</td>
                          <td>${provider.providerEnum}</td>
                          <td>${provider.providerId}</td>
                          <td class="text-truncate" style="max-width: 30px;">${provider.providerNickname}</td>
                          <td>${user.nickname}</td>
                          <td>${user.solveCount}</td>
                          <td>${user.score}</td>
                          <td>${user.role}</td>
                          <td class="container col-auto">
                            <div class="row">
                              <div class="col px-0">
                                <a class="btn btn-secondary mx-2" href="/user/${user.id}?page=${param.page}&search=${param.nickname}&size=${param.size}&sort=${param.sort}">수정</a>
                              </div>
                              <form class="col px-0" method="post" action="/user?page=${param.page}&search=${param.nickname}&size=${param.size}&sort=${param.sort}">
                                <input type="hidden" name="_method" value="DELETE" />
                                <input type="hidden" name="id" value="${user.id}" />
                                <input type="submit" class="btn btn-danger mx-2" value="삭제">
                              </form>
                            </div>
                          </td>
                        </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>

                  <!-- 페이지네이션 -->
                  <nav aria-label="..." class="row justify-content-md-center mt-4 mb-4">
                    <ul class="pagination col-md-auto">

                      <c:set var="page" value="${(param.page == null) ? 1 : param.page}" />
                      <c:set var="startNum" value="${page - (page - 1) % 5}" />

                      <!-- 이전 페이지 버튼 -->
                      <li class="page-item ${startNum <= 1 ? 'disabled' : ''} ">
                        <a href="?page=${param.page - 5}&search=${param.nickname}&size=${param.size}&sort=${param.sort}" class="page-link">Previous</a>
                      </li>

                      <!-- 페이지 숫자 버튼 -->
                      <c:forEach var="i" begin="0" end="4">
                        <c:if test="${startNum + i <= userPage.totalPages}">
                          <li class="page-item ${startNum + i == page ? 'active' : ''}">
                            <a class="page-link" href="?page=${startNum + i}&search=${param.nickname}&size=${param.size}&sort=${param.sort}">${startNum + i}</a>
                          </li>
                        </c:if>
                      </c:forEach>

                      <!-- 다음 페이지 버튼 -->
                      <li class="page-item">
                        <a class="page-link ${startNum + 5 > userPage.totalPages ? 'disabled' : ''}"
                           href="?page=${param.page + 5 > userPage.totalPages ? userPage.totalPages : param.page + 5}&search=${param.nickname}&size=${param.size}&sort=${param.sort}">Next</a>
                      </li>
                    </ul>
                  </nav>

                </div>
              </div>
            </div>
          </div>
        </div>

        <footer class="footer">
          <div class="container-fluid d-flex justify-content-between">
            <nav class="pull-left">
              <ul class="nav">
                <li class="nav-item">
                  <a class="nav-link" href="http://www.themekita.com">
                    ThemeKita
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#"> Help </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#"> Licenses </a>
                </li>
              </ul>
            </nav>
            <div class="copyright">
              2024, made with <i class="fa fa-heart heart text-danger"></i> by
              <a href="http://www.themekita.com">ThemeKita</a>
            </div>
            <div>
              Distributed by
              <a target="_blank" href="https://themewagon.com/">ThemeWagon</a>.
            </div>
          </div>
        </footer>
      </div>

      <!-- Custom template | don't include it in your project! -->
      <div class="custom-template">
        <div class="title">Settings</div>
        <div class="custom-content">
          <div class="switcher">
            <div class="switch-block">
              <h4>Logo Header</h4>
              <div class="btnSwitch">
                <button
                  type="button"
                  class="selected changeLogoHeaderColor"
                  data-color="dark"
                ></button>
                <button
                  type="button"
                  class="selected changeLogoHeaderColor"
                  data-color="blue"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="purple"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="light-blue"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="green"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="orange"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="red"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="white"
                ></button>
                <br />
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="dark2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="blue2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="purple2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="light-blue2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="green2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="orange2"
                ></button>
                <button
                  type="button"
                  class="changeLogoHeaderColor"
                  data-color="red2"
                ></button>
              </div>
            </div>
            <div class="switch-block">
              <h4>Navbar Header</h4>
              <div class="btnSwitch">
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="dark"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="blue"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="purple"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="light-blue"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="green"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="orange"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="red"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="white"
                ></button>
                <br />
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="dark2"
                ></button>
                <button
                  type="button"
                  class="selected changeTopBarColor"
                  data-color="blue2"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="purple2"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="light-blue2"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="green2"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="orange2"
                ></button>
                <button
                  type="button"
                  class="changeTopBarColor"
                  data-color="red2"
                ></button>
              </div>
            </div>
            <div class="switch-block">
              <h4>Sidebar</h4>
              <div class="btnSwitch">
                <button
                  type="button"
                  class="selected changeSideBarColor"
                  data-color="white"
                ></button>
                <button
                  type="button"
                  class="changeSideBarColor"
                  data-color="dark"
                ></button>
                <button
                  type="button"
                  class="changeSideBarColor"
                  data-color="dark2"
                ></button>
              </div>
            </div>
          </div>
        </div>
<%--        <div class="custom-toggle">--%>
<%--          <i class="icon-settings"></i>--%>
<%--        </div>--%>
      </div>
      <!-- End Custom template -->
    </div>
    <!--   Core JS Files   -->
    <script src="../assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="../assets/js/core/popper.min.js"></script>
    <script src="../assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Kaiadmin JS -->
    <script src="../assets/js/kaiadmin.min.js"></script>
  </body>
</html>
