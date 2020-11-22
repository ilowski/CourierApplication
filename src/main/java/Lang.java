import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "languages")
class Lang {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String welcomeMessage;
    private String codeLanguage;

    //constructor for Hibernate use
    @SuppressWarnings("unused")
    Lang() {
    }

    public Lang(Integer id, String welcomeMessage, String codeLanguage) {
        this.id = id;
        this.welcomeMessage = welcomeMessage;
        this.codeLanguage = codeLanguage;
    }

    public Integer getId() {
        return id;
    }


    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }
}
