package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Lang", urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {
    private LangRepository langRepository;
    private final Logger logger = LoggerFactory.getLogger(LangServlet.class);
    private ObjectMapper objectMapper;

    @SuppressWarnings("unused")
    public LangServlet() {
        this(new LangRepository(), new ObjectMapper());
    }

    public LangServlet(LangRepository langRepository, ObjectMapper objectMapper) {
       this.langRepository = langRepository;
       this.objectMapper = objectMapper;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("reques got");
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getOutputStream(), langRepository.findAll());

    }
}
