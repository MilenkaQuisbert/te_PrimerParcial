
package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        if(ses.getAttribute("listapro") == null)
        {
            ArrayList<Producto> listayu =new ArrayList<Producto>(); 
            ses.setAttribute("listapro", listayu);
        }
        
        ArrayList<Producto> lista=(ArrayList<Producto>)ses.getAttribute("listapro");
        
        
        String op= request.getParameter("op");
        String opcion = (op !=null) ? op:"view";
        
        Producto obj1 = new Producto();
        int id,pos;
        
        switch(opcion){
            
            case "nuevo":
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                obj1=lista.get(pos);
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                lista.remove(pos);
                ses.setAttribute("listapro", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista=(ArrayList<Producto>) ses.getAttribute("listapro");
        
        Producto obje1=new Producto();
         obje1.setId(Integer.parseInt(request.getParameter("id")));
         obje1.setDescripcion(request.getParameter("descripcion"));
         obje1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
         obje1.setPrecio(Float.valueOf(request.getParameter("precio")));
         obje1.setCategoria(request.getParameter("categoria"));
         
         int idte= obje1.getId();
         
         if(idte ==0)
         {
             
             int utId;
             utId=ultimoId(request);
             obje1.setId(utId);
             lista.add(obje1);
         }
         else
         {
             lista.set(buscarIndice(request,idte),obje1);
             
         }
         ses.setAttribute("listapro", lista);
         response.sendRedirect("index.jsp");
        
    }
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Producto> Lista = (ArrayList<Producto>)ses.getAttribute("listapro");
        
        int i=0;
        if(Lista.size() >0){
            while(i < Lista.size()){
                if(Lista.get(i).getId()==id){
                    break;
                }
                else
                {
                    i++;
                }
            }
        }
        return i;
    }
    private int ultimoId(HttpServletRequest request)
    {
         HttpSession ses = request.getSession();
        ArrayList<Producto> Lista = (ArrayList<Producto>)ses.getAttribute("listapro");
        
        int ida=0;
        for(Producto item:Lista)
        {
            ida=item.getId();
        }
        return ida+1;
        
    }

}
