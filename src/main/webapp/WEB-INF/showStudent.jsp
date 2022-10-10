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
  <h1 class="text-center">${student.name}'s Courses</h1>
  <div class="d-flex flex-row" style="gap: 1rem">
    <a href="/dorms">Dorms</a>
    <a href="/courses">Courses</a>
    <a href="/students">Students</a>
  </div>
  <h2><strong>Dorm:</strong> <a href="/dorms/${student.dorm.id}">${student.dorm.name}</a></h2>
  <c:if test="${otherCourses.size() != 0}">
    <%--@elvariable id="studentId" type=""--%>
    <form action="/students/${student.id}/add" method="post">
      <table class="table table-striped table-hover table-bordered text-center">
        <tr>
          <td>
            <label for="courseId">Classes</label>
            <select class="form-control" id="courseId" name="courseId">
            <c:forEach var="course" items="${otherCourses}">
              <!--- Each option VALUE is the id of the student --->
              <option value="${course.id}">
                <!--- This is what shows to the user as the option --->
                <c:out value="${course.name}"/>
              </option>
            </c:forEach>
          </select>
          </td>
          <td>
            <input type="submit" value="Add">
          </td>
        </tr>
      </table>
    </form>
  </c:if>
  <hr>
  <h2>Courses:</h2>
  <table class="table table-striped table-hover">
      <tr>
        <th>Course</th>
        <th>Actions</th>
      </tr>
    <c:forEach var="course" items="${student.courses}">
      <tr>
        <td>
          <a href="/courses/${course.id}">${course.name}</a>
        </td>
        <td>
          <a href="/students/${student.id}/remove/${course.id}">Drop</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <a href="/students">All Students</a>
</body>
</html>