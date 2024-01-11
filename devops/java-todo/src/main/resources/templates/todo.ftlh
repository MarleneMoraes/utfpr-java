<!DOCTYPE html>
<html>
<head>
    <title>Todo App</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Todo App</a>
  </div>
</nav>
<div class="container mt-4">
<form method="post" action="/add" class="mt-4">
    <div class="input-group mb-3">
      <input type="text" name="task" class="form-control" placeholder="Add a new to-do...">
      <button type="submit" class="btn btn-primary">Add</button>
    </div>
  </form>
  </div>

<div class="container mt-4">
  <ul class="list-group">
    <#list todos as todo>
      <li class="list-group-item d-flex justify-content-between align-items-center">
        ${todo.task}
        <div class="btn-group" role="group" aria-label="Todo actions">
        <#if todo.status>
  <a href="#" class="btn btn-success" disabled>Done!</a>
<#else>
        <a href="/update?id=${todo.id}" class="btn btn-primary">Mark as done</a>
</#if>
        <a href="/remove?id=${todo.id}" class="btn btn-danger">Delete</a>
        </div>
      </li>
    </#list>
  </ul>
</div>

<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="/webjars/popper.js/2.10.2/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

</body>
</html>