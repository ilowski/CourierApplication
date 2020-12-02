package servlet;

import database.TipRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Tip", urlPatterns={"/api/tips"})
public class TipServlet extends HttpServlet  {
    private TipRepository tipRepository;

}
