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
<h1>Edit Student</h1>
<div class="row justify-content-center">
  <div class="col-auto">
    <div class="card">
      <div class="card-body">
        <%--@elvariable id="student" type=""--%>
        <form:form action="/students/${student.id}" method="post" modelAttribute="student">
          <input type="hidden" name="_method" value="put">
          <p class="form-control d-flex flex-column">
            <form:label path="name">Name</form:label>
            <form:errors path="name" cssClass="text-danger"/>
            <form:input path="name"/>
          </p>
          <p class="form-control d-flex flex-column">
          <form:label path="dorm">Dorm</form:label>
          <form:errors path="dorm" cssClass="text-danger"/>
          <form:select path="dorm">
            <form:option value="${student.dorm.id}">
              <c:out value="${student.dorm.name}"/>
            </form:option>
            <c:forEach var="oneDorm" items="${dorms}">
              <!--- Each option VALUE is the id of the dorm --->
              <form:option value="${oneDorm.id}">
                <!--- This is what shows to the user as the option --->
                <c:out value="${oneDorm.name}"/>
              </form:option>
            </c:forEach>
          </form:select>
        </p>
          <input class="mt-3 btn btn-primary" type="submit" value="Submit">
        </form:form>
      </div>
    </div>
  </div>
  <a class="btn btn-secondary" href="/students">Cancel</a>
</div>
</body>
</html>