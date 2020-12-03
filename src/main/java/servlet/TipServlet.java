package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.LangRepository;
import database.TipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger = LoggerFactory.getLogger(TipServlet.class);
    @SuppressWarnings("unused")
    public TipServlet() {
        this(new TipRepository(), new ObjectMapper());
    }

    public TipServlet(TipRepository tipRepository, ObjectMapper objectMapper) {
        this.tipRepository = tipRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Request got on TipServlet");
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getOutputStream(), tipRepository.findAllTips());

    }

    protected void doPut()

}
