
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Nota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda personal</title>
    </head>
    <body>
        <%
            if (session.getAttribute("listaact") == null){
                ArrayList<Nota> listaux = new ArrayList<Nota>();
                session.setAttribute("listaact", listaux);
            }
            ArrayList<Nota> lista = (ArrayList<Nota>) session.getAttribute("listaact");
        %>
        <h1>Agenda de actividades</h1>
        <a href="Controlador?op=1">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Hora</th>
                <th>Actividad</th>
                <th></th>
            </tr>
            
            <%
                if (lista != null){
                    for (Nota item : lista){
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getHora()%></td>
                <td><%= item.getActividad()%></td>
                <td><a href="Controlador?op=2&id=<%=item.getId()%>">Cumplido</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
