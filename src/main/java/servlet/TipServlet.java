package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.LangRepository;
import database.TipRepository;
import entity.Tip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TipService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Tip", urlPatterns={"/api/tips"})
public class TipServlet extends HttpServlet  {
    private TipRepository tipRepository;
    private ObjectMapper objectMapper;
    private TipService tipService;
    private final Logger logger = LoggerFactory.getLogger(TipServlet.class);
    @SuppressWarnings("unused")
    public TipServlet() {
        this(new TipRepository(), new ObjectMapper(), new TipService());
    }

    public TipServlet(TipRepository tipRepository, ObjectMapper objectMapper, TipService tipService) {
        this.tipRepository = tipRepository;
        this.objectMapper = objectMapper;
        this.tipService = tipService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            logger.info("Request got on TipServlet");
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(tipService.prepareSummaryTipMessage());




    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

     public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Tip tip = tipRepository.addTip(objectMapper.readValue(req.getInputStream(), Tip.class));
        objectMapper.writeValue(resp.getOutputStream(), tip);

    }
}
