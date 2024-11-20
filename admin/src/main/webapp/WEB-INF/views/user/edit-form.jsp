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
      <jsp:include page="../sidebar.jsp"/>

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
          <jsp:include page="../navbar.jsp"/>
        </div>

        <div class="container">
          <div class="page-inner">
            <div class="page-header">
              <h3 class="fw-bold mb-3">Edit Form</h3>
            </div>
            <div class="row">
              <div class="col-md-12">
                <form method="post" action="/user?page=${param.page}&search=${param.nickname}&size=${param.size}&sort=${param.sort}" class="card">
                  <input type="hidden" name="_method" value="PUT" />

                  <div class="card-header">
                    <div class="card-title">수정하기</div>
                  </div>
                  <div class="card-body">
                    <div class="row">
                      <div>
                        <div class="form-group form-inline">
                          <label
                            for="id"
                            class="col-md-3 col-form-label"
                            >ID</label
                          >
                          <div>
                            <input
                              type="text"
                              class="form-control input-full"
                              placeholder="Enter Input"
                              id="id"
                              name="id"
                              value="${user.id}"
                              readonly
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="provider"
                                  class="col-md-3 col-form-label"
                          >Provider</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="provider"
                                    name="provider"
                                    value="${user.provider}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="providerId"
                                  class="col-md-3 col-form-label"
                          >ProviderId</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="providerId"
                                    name="providerId"
                                    value="${user.providerId}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="providerNickname"
                                  class="col-md-3 col-form-label"
                          >ProviderNickname</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="providerNickname"
                                    name="providerNickname"
                                    value="${user.providerNickname}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="nickname"
                                  class="col-md-3 col-form-label"
                          >Nickname</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="nickname"
                                    name="nickname"
                                    value="${user.nickname}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="solveCount"
                                  class="col-md-3 col-form-label"
                          >SolveCount</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="solveCount"
                                    name="solveCount"
                                    value="${user.solveCount}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="score"
                                  class="col-md-3 col-form-label"
                          >Score</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="score"
                                    name="score"
                                    value="${user.score}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="role"
                                  class="col-md-3 col-form-label"
                          >Role</label
                          >
                          <div>
                            <select name="role" id="role" class="form-select">
                              <c:forEach var="role" items="${roles}">
                                <option value="${role.name()}">${role.name()}</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-action">
                    <input type="submit" class="btn btn-success" value="저장" />
                    <a href="/user/list?page=${param.page}&title=${param.nickname}&size=${param.size}&sort=${param.sort}" class="btn btn-danger">취소</a>
                  </div>
                </form>
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
    </div>
    <!--   Core JS Files   -->
    <script src="../assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="../assets/js/core/popper.min.js"></script>
    <script src="../assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

    <!-- Chart JS -->
    <script src="../assets/js/plugin/chart.js/chart.min.js"></script>

    <!-- jQuery Sparkline -->
    <script src="../assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

    <!-- Chart Circle -->
    <script src="../assets/js/plugin/chart-circle/circles.min.js"></script>

    <!-- Datatables -->
    <script src="../assets/js/plugin/datatables/datatables.min.js"></script>

    <!-- Bootstrap Notify -->
    <script src="../assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

    <!-- jQuery Vector Maps -->
    <script src="../assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
    <script src="../assets/js/plugin/jsvectormap/world.js"></script>

    <!-- Google Maps Plugin -->
    <script src="../assets/js/plugin/gmaps/gmaps.js"></script>

    <!-- Sweet Alert -->
    <script src="../assets/js/plugin/sweetalert/sweetalert.min.js"></script>

    <!-- Kaiadmin JS -->
    <script src="../assets/js/kaiadmin.min.js"></script>
  </body>
</html>
