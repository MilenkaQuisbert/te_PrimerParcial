<%@page import="com.emergentes.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Producto> lista = (ArrayList<Producto>)session.getAttribute("listapro");

 %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <table border="1">
            <td>
        <p>PRIMER PARCIAL TEM-742</p>
        <p>Nombre: Milenka Melisa Quisbert Machaca</p>
        <p>Carnet:9186706</p>
            </td>
        </table>
        <h1 class="center">Productos</h1>
        </center>
         <div style="text-align: center;">
        <a href="MainController?op=nuevo">Nuevo Producto</a>
        
        <table border="1" style="margin:0 auto;">
            <tr>
                <th>Id</th>
                <th>Descripción</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th>Editar</th>
                <th>Eliminar</th>
                
               
            </tr>
            <%
                if(lista != null){
                for(Producto item : lista){
                
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td><a href="MainController?op=editar&id=<%= item.getId() %>">Editar</a></td>
                <td><a href="MainController?op=eliminar&id=<%= item.getId() %>"
                       onclick="return confirm('Se eliminara un registro. ¿Esta seguro?');">Eliminar</a></td>
                
            </tr>
            <%
                     }
                }

            %>
        </table>
        </div>
    </body>
</html>
