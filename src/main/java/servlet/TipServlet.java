package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.LangRepository;
import database.TipRepository;
import entity.Tip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TipService;
import validator.TipValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "Tip", urlPatterns = {"/api/tips"})
public class TipServlet extends HttpServlet {
    private TipRepository tipRepository;
    private ObjectMapper objectMapper;
    private TipService tipService;
    private TipValidator tipValidator;
    private final Logger logger = LoggerFactory.getLogger(TipServlet.class);

    @SuppressWarnings("unused")
    public TipServlet() {
        this(new TipRepository(), new ObjectMapper(), new TipService(), new TipValidator());
    }

    public TipServlet(TipRepository tipRepository, ObjectMapper objectMapper, TipService tipService, TipValidator tipValidator) {
        this.tipRepository = tipRepository;
        this.objectMapper = objectMapper;
        this.tipService = tipService;
        this.tipValidator = tipValidator;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        try {
            Tip tip = objectMapper.readValue(req.getInputStream(), Tip.class);
            if (tipValidator.isValidate(tip.getValue(), tip.getTipMessage())) {
                tipRepository.addTip(tip);
                logger.info("Tip added to database!");
                doGet(req, resp);
            } else {
                logger.info("Wrong value of tip");
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write("GIVE MORE THAN 0 AND ADD YOUR MESSAGE" + "!");
            }
        } catch (Exception e) {
            logger.info("Wrong format of tip");
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("GIVE CORRECT TIP" +
                    "!");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got on TipServlet");
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(tipService.prepareSummaryTipMessage());


    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}
