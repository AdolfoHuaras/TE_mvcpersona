
package com.emergentes.controlador;

import com.emergentes.modelo.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaest");
        
        Persona obj1 = new Persona();
        int id, pos;
        int opcion = Integer.parseInt(request.getParameter("op"));
        
        switch (opcion) {
            case 1: //Insertar nuevo registro
                request.setAttribute("miPersona", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case 2: //Modificar registro
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                obj1 = lista.get(pos);
                request.setAttribute("miPersona", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case 3: //Eliminar registro
                pos = buscarIndice(request, Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                ses.setAttribute("listaest", lista);
                response.sendRedirect("index.jsp");
                break;
            case 4: //Grabar
                String nuevo = request.getParameter("nuevo");
                
                id = Integer.parseInt(request.getParameter("id"));
                int edad = Integer.parseInt(request.getParameter("edad"));
                
                obj1.setId(id);
                obj1.setNombres(request.getParameter("nombres"));
                obj1.setApellidos(request.getParameter("apellidos"));
                obj1.setEdad(edad);
                
                if (nuevo.equals("true")){
                    lista.add(obj1);
                }else{
                    pos = buscarIndice(request, id);
                    lista.set(pos, obj1);
                }
                ses.setAttribute("listaest", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int buscarIndice(HttpServletRequest request, int id){
    HttpSession ses = request.getSession();
    ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaest");

    int i = 0;
    if (lista.size() > 0){
        while (i < lista.size()){
            if(lista.get(i).getId() == id){
                break;
            }
            else i++;
            }
        }   
        return i;
    }
}

