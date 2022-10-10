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
<h1 class="text-center">Dorms</h1>
<div class="row justify-content-center">
  <div class="d-flex flex-row" style="gap: 1rem">
    <a href="/dorms" class="text-danger">Dorms</a>
    <a href="/courses">Courses</a>
    <a href="/students">Students</a>
  </div>
  <div class="col-auto">
    <div class="card">
      <div class="card-body">
        <table class="table table-hover table-striped">
          <tr>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          <c:forEach var="dorm" items="${dorms}">
            <tr>
              <td><a href="/dorms/${dorm.id}"><c:out value="${dorm.name}"/></a></td>
              <td>
                <div class="d-flex">
                  <a class="btn btn-success me-3" href="/dorms/${dorm.id}/edit">Edit</a>
                  <form action="/dorms/${dorm.id}" method="post">
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
  <a href="/dorms/new">Add Dorm</a>
</div>
</body>
</html>