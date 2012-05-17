<%@ page contentType="application/xml" %><?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<error status="<%=request.getAttribute("javax.servlet.error.status_code") %>"><%=request.getAttribute("javax.servlet.error.message") %></error>