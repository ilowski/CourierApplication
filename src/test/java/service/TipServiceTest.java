package service;

import database.TipRepository;
import entity.Tip;
import org.junit.Assert;
import org.junit.Test;

public class TipServiceTest {
    private TipService tipService;
    @Test
    public void TipWithTipMessageAndValueTest(){
        //is
        tipService = new TipService();
        String tipMessage = "superkozak";
        Float value = 50.0f;
        //then
        String result = tipService.prepareTipMessage(tipMessage, value);

        //excepted
        Assert.assertEquals(value +"$ tip: " + tipMessage, result);

    }
@Test
    public void TipMessageIsNullTest(){
        //is
        tipService = new TipService();
        String tipMessage = null;
        Float value = 50.0f;
        //then
        String result = tipService.prepareTipMessage(tipMessage, value);

        //excepted
        Assert.assertEquals( value +"$ tip: Thank you for it!" , result);

    }

    @Test
    public void TipValueIsNullTest(){
        //is
        tipService = new TipService();
        String tipMessage = "nice streams";
        Float value = null;
        //then
        String result = tipService.prepareTipMessage(tipMessage, value);

        //excepted
        Assert.assertEquals( "0.0$ tip: " + tipMessage, result);

    }


    @Test
    public void TipValueAndTipMessageAreNullTest(){
        //is
        tipService = new TipService();
        String tipMessage = null;
        Float value = null;
        //then
        String result = tipService.prepareTipMessage(tipMessage, value);

        //excepted
        Assert.assertEquals(   "0.0$ tip: Thank you for it!" , result);

    }

    @Test
    public void TipValueNegativeTest(){
        //is
        tipService = new TipService();
        String tipMessage = null;
        Float value = -5f;
        //then
        String result = tipService.prepareTipMessage(tipMessage, value);

        //excepted
        Assert.assertEquals("You can't give me negative tip", result);

    }

    @Test
    public void TipSummaryMessage(){
        //is
        tipService = new TipService();
        TipRepository tipRepository = new TipRepository();
        Float summary = 0f;
        for (Tip x : tipRepository.findAllTips()) {
            summary += x.getValue();
        }
        //then
        String result = tipService.prepareSummaryTipMessage();

        //excepted
        Assert.assertEquals(  "Current balance of tips: " + summary, result);

    }
}
