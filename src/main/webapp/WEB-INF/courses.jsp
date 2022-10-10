<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Tacos</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
  <script src="/webjars/jquery/jquery.min.js"></script>
  <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1 class="text-center">Courses</h1>
<div class="row justify-content-center">
  <div class="d-flex flex-row" style="gap: 1rem">
    <a href="/dorms">Dorms</a>
    <a href="/courses" class="text-danger">Courses</a>
    <a href="/students">Students</a>
  </div>
  <div class="row justify-content-center">
  <div class="col-auto">
    <div class="card">
      <div class="card-body">
        <table class="table table-hover table-striped">
          <tr>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          <c:forEach var="course" items="${courses}">
            <tr>
              <td><a href="/courses/${course.id}"><c:out value="${course.name}"/></a></td>
              <td>
                <div class="d-flex">
                  <a class="btn btn-success me-3" href="/courses/${course.id}/edit">Edit</a>
                  <form action="/courses/${course.id}" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <input type="submit" value="Delete" class="btn btn-danger">
                  </form>
                </div>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</div>
  <a href="/courses/new">Add Course</a>
</div>
</body>
</html>