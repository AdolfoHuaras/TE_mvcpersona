
<%@page import="com.emergentes.modelo.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MVC Persona</title>
    </head>
    <body>
        <%
            if(session.getAttribute("listaest") == null){
                ArrayList<Persona> listaux = new ArrayList<Persona>();
                session.setAttribute("listaest", listaux);
            }
            ArrayList<Persona> lista = (ArrayList<Persona>) session.getAttribute("listaest");
        %>
        <h1>Lista de personas</h1>
        <a href="Controlador?op=1">Nuevo</a>
        <table>
            <%
                if(lista != null){
                    for (Persona item : lista){
            %>
            <tr>
                <th><%= item.getId()%></th>
                <th><%= item.getNombres()%></th>
                <th><%= item.getApellidos()%></th>
                <th><%= item.getEdad()%></th>
                <th><a href="Controlador?op=2&id=<%=item.getId()%>">Modificar</a></th>
                <th><a href="Controlador?op=3&id=<%=item.getId()%>" 
                       onclick='return confirm("Esta seguro de eliminar el registro ?");'>Eliminar</a></th>
            </tr>
            <%
                }
               } 
            %>
        </table>
        
    </body>
</html>
