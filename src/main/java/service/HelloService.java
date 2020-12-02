package service;

import database.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Integer FALLBACK_LANG_ID = 1;
    private LangRepository langRepository;

    Logger logger = LoggerFactory.getLogger(HelloService.class);
    public HelloService() {
        this(new LangRepository());
    }

   public HelloService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }



    public String prepareGreeting(String name, String lang)  {
        Integer langId;
        try {

             langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG_ID);
        }
        catch (NumberFormatException e) {
            logger.warn("Non-numeric format of lang");
            langId = FALLBACK_LANG_ID;
        }
        String welcomeMessage = Optional.ofNullable(langRepository.findById(langId)).orElse(langRepository.findById(FALLBACK_LANG_ID)).getWelcomeMessage();
        String nameToWelcomeMessage = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        String tipText = Optional.ofNullable(langRepository.findById(langId)).orElse(langRepository.findById(FALLBACK_LANG_ID)).getTipMessage();

        return welcomeMessage + " " + nameToWelcomeMessage + ". " + tipText ;
    }
}

