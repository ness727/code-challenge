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
                <form method="post" action="/problem?page=${param.page}&search=${param.title}&size=${param.size}&sort=${param.sort}" class="card">
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
                              value="${problem.id}"
                              readonly
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="title"
                                  class="col-md-3 col-form-label"
                          >Title</label
                          >
                          <div class="">
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="title"
                                    name="title"
                                    value="${problem.title}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="level"
                                  class="col-md-3 col-form-label"
                          >Level</label
                          >
                          <div>
                            <select name="level" id="level" class="form-select">
                              <c:forEach var="level" items="${levels}">
                                <option value="${level.levelName}" ${problem.level == level ? 'selected' : ''}>
                                        ${level.levelString}
                                </option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="score"
                                  class="col-md-3 col-form-label"
                          >score</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="score"
                                    name="score"
                                    value="${problem.score}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="params"
                                  class="col-md-3 col-form-label"
                          >Params</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="params"
                                    name="params"
                                    value="${problem.params}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="returnType"
                                  class="col-md-3 col-form-label"
                          >ReturnType</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="returnType"
                                    name="returnType"
                                    value="${problem.returnType}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="description"
                                  class="col-md-3 col-form-label"
                          >Description</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="description"
                                    name="description"
                                    value="${problem.description}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="limitation"
                                  class="col-md-3 col-form-label"
                          >Limitation</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="limitation"
                                    name="limitation"
                                    value="${problem.limitation}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="inputOutput"
                                  class="col-md-3 col-form-label"
                          >InputOutput</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="inputOutput"
                                    name="inputOutput"
                                    value="${problem.inputOutput}"
                                    placeholder="Enter Input"
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline mt-5">
                          <h5>TestcaseList</h5>
                          <div>
                            <c:forEach var="testcase" items="${problem.testcaseList}" varStatus="status">
                              <label
                                      for="testcase.id"
                                      class="col-md-3 col-form-label"
                              >TestcaseId</label
                              >
                              <input
                                      type="text"
                                      class="form-control input-full"
                                      id="testcase.id"
                                      name="testcase.id"
                                      value="${testcase.id}"
                                      placeholder="Enter Input"
                                      readonly
                              />
                              <label
                                      for="testcase.paramData"
                                      class="col-md-3 col-form-label"
                              >ParamData</label
                              >
                              <input
                                      type="text"
                                      class="form-control input-full"
                                      id="testcase.paramData"
                                      name="testcase.paramData"
                                      value="${testcase.paramData}"
                                      placeholder="Enter Input"
                              />
                              <label
                                      for="testcase.result"
                                      class="col-md-3 col-form-label"
                              >Result</label
                              >
                              <input
                                      type="text"
                                      class="form-control input-full"
                                      id="testcase.result"
                                      name="testcase.result"
                                      value="${testcase.result}"
                                      placeholder="Enter Input"
                              />
                              <hr/>
                            </c:forEach>
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="solvedCount"
                                  class="col-md-3 col-form-label"
                          >SolvedCount</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="solvedCount"
                                    value="${problem.solvedCount}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="tryCount"
                                  class="col-md-3 col-form-label"
                          >TryCount</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="tryCount"
                                    value="${problem.tryCount}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>
                        <div class="form-group form-inline">
                          <label
                                  for="correctRate"
                                  class="col-md-3 col-form-label"
                          >CorrectRate</label
                          >
                          <div>
                            <input
                                    type="text"
                                    class="form-control input-full"
                                    id="correctRate"
                                    value="${problem.correctRate}"
                                    placeholder="Enter Input"
                                    disabled
                            />
                          </div>
                        </div>

                      </div>
                    </div>
                  </div>
                  <div class="card-action">
                    <input type="submit" class="btn btn-success" value="저장" />
                    <a href="/problem/list?page=${param.page}&title=${param.title}&size=${param.size}&sort=${param.sort}" class="btn btn-danger">취소</a>
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
