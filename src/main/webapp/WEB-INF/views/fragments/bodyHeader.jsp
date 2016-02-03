<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/static/images/drawing.svg" var="brand"/>

  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<spring:url value="/" htmlEscape="true"  />" >
                 <img alt = "Polyglot" src="${brand}"/>
          </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="<spring:url value="/" htmlEscape="true" />"> Home</a></li>
            <li><a href="<spring:url value="/listlanguages" htmlEscape="true" />"> Languages</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Words<span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="<spring:url value="/words/find" htmlEscape="true" />">Find word</a></li>
                  <li><a href="<spring:url value="/listwords" htmlEscape="true" />">All words</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="<spring:url value="/words/newword-inlang-" htmlEscape="true" />">Add new word</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Words(SPARQL)<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<spring:url value="/sparql" htmlEscape="true" />">Add new word</a></li>
                </ul>
            </li>
            <li><a href="contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
  </nav>
