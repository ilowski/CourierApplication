import org.junit.Assert;
import org.junit.Test;


public class HelloServiceTest {
    private HelloService helloService;
    @Test
    public void testPrepareGreetingWithNameAndEnglishLanguage() {
        //given
        helloService = new HelloService();
        String name = "kasia";
        String language="1";

        //then
        String result = helloService.prepareGreeting(name,language);

        //excepted
        Assert.assertEquals("Hello " + name, result);

    }
    @Test
    public void testPrepareGreetingWithNameAndPolishLanguage() {
        //given
        helloService = new HelloService();
        String name = "kasia";
        String language="2";

        //then
        String result = helloService.prepareGreeting(name,language);

        //excepted
        Assert.assertEquals("Witaj " + name, result);

    }
    @Test
    public void testPrepareGreetingNullWithUseFallBack() {
        //given
        helloService = new HelloService();
        String name=null;
        String language = null;

        //then
        String result = helloService.prepareGreeting(name,language);

        //excepted
        Assert.assertEquals("Hello " + helloService.FALLBACK_NAME, result);

    }

    @Test
    public void testPrepareGreetingWithNameAndLanguageNegative() {
        //given
        helloService = new HelloService();
        String name = "kasia";
        String language="-7";

        //then
        String result = helloService.prepareGreeting(name,language);

        //excepted
        Assert.assertEquals("Hello " + name, result);

    }

    @Test
    public void testPrepareGreetingWithFallBackNameAndLanguageNonNumericFormat() {
        //given
        helloService = new HelloService();
        String name = null;
        String language="abc";

        //then
        String result = helloService.prepareGreeting(name,language);

        //excepted
        Assert.assertEquals("Hello " + helloService.FALLBACK_NAME, result);

    }
}
