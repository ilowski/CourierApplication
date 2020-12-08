package service;

import database.TipRepository;
import entity.Tip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class TipService {
    TipRepository tipRepository;

    Logger logger = LoggerFactory.getLogger(TipService.class);

    public TipService() {this(new TipRepository());}

    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public String prepareTipMessage(String tipMessage, Float value) {
       Float valueForMessage;
       if (value != null) {
           valueForMessage = value;
       } else {
           valueForMessage = 0.0f;
       }
        if (valueForMessage >= 0){
        String tipMessageReturn =  valueForMessage  + "$ tip: " + Optional.ofNullable(tipMessage).orElse("Thank you for it!");
        return tipMessageReturn;}
        else {
            return "You can't give me negative tip";
        }

    }

    public String summaryOfTips() {
        Float summary = 0f;
        List<Tip> listAllTips = tipRepository.findAllTips();
        for (Tip x : listAllTips) {
            summary += x.getValue();
        }
       return summary.toString();
    }

    public String prepareSummaryTipMessage () {
       String summary = summaryOfTips();
        String summaryTipMessage = "Current balance of tips: " + summary;
        return summaryTipMessage;
    }
}
