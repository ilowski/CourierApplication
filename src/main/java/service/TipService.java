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

    public String prepareTipMessage(String nickName, Float value) {
       Float valueForMessage;
       if (value != null) {
           valueForMessage = value;
       } else {
           valueForMessage = 0.0f;
       }
        if (valueForMessage >= 0){
        String tipMessage = Optional.ofNullable(nickName).orElse("Unnamed") + " thank you for " + valueForMessage  + "$ tip!";
        return tipMessage;}
        else {
            return "You can't give me negative tip";
        }

    }

    public String prepareSummaryTipMessage () {
        Float summary = 0f;
        List<Tip> listAllTips = tipRepository.findAllTips();
        for (Tip x : listAllTips) {
            summary += x.getValue();
        }
        String summaryTipMessage = "Current balance of tips: " + summary;

        return summaryTipMessage;
    }
}
