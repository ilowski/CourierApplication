import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Long FALLBACK_LANG_ID = 1L;
    private LangRepository langRepository;
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    public HelloService() {
        this(new LangRepository());
    }

   public HelloService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }



    String prepareGreeting(String name, String lang)  {
        Long langId;
        try {

             langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG_ID);
        }
        catch (NumberFormatException e) {
            logger.warn("Non-numeric format of lang");
            langId = FALLBACK_LANG_ID;
        }
        String welcomeMessage = Optional.ofNullable(langRepository.findById(langId)).orElse(langRepository.findById(FALLBACK_LANG_ID)).getWelcomeMessage();
        String nameToWelcomeMessage = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMessage + " " + nameToWelcomeMessage;
    }
}

