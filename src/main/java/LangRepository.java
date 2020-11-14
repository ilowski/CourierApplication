import java.util.ArrayList;
import java.util.List;

public class LangRepository {

    private List<Lang> languages;

    public LangRepository() {
        this.languages = new ArrayList<Lang>();
        languages.add(new Lang(1L,"Hello", "en"));
        languages.add(new Lang(2L,"Witaj", "pl"));
    }

    public Lang findById(Long id){
        for (Lang x:languages) {
            if (x.getId() == id) {
                return x;
            }
        }
        return languages.get(0);
    }
}
