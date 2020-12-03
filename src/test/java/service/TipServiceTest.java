package service;

import database.TipRepository;
import entity.Tip;
import org.junit.Assert;
import org.junit.Test;

public class TipServiceTest {
    private TipService tipService;
    @Test
    public void TipWithNameAndValueTest(){
        //is
        tipService = new TipService();
        String nickName = "Kozak";
        Float value = 50.0f;
        //then
        String result = tipService.prepareTipMessage(nickName, value);

        //excepted
        Assert.assertEquals(nickName + " thank you for " + value + "$ tip!", result);

    }
@Test
    public void TipNameIsNullTest(){
        //is
        tipService = new TipService();
        String nickName = null;
        Float value = 50.0f;
        //then
        String result = tipService.prepareTipMessage(nickName, value);

        //excepted
        Assert.assertEquals( "Unnamed thank you for " + value + "$ tip!", result);

    }

    @Test
    public void TipValueIsNullTest(){
        //is
        tipService = new TipService();
        String nickName = "kozak";
        Float value = null;
        //then
        String result = tipService.prepareTipMessage(nickName, value);

        //excepted
        Assert.assertEquals( nickName + " thank you for " + "0.0$ tip!", result);

    }


    @Test
    public void TipValueIsNullNickNameIsNullTest(){
        //is
        tipService = new TipService();
        String nickName = null;
        Float value = null;
        //then
        String result = tipService.prepareTipMessage(nickName, value);

        //excepted
        Assert.assertEquals(  "Unnamed thank you for " + "0.0$ tip!", result);

    }

    @Test
    public void TipValueNegativeTest(){
        //is
        tipService = new TipService();
        String nickName = null;
        Float value = -5f;
        //then
        String result = tipService.prepareTipMessage(nickName, value);

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
