package dictionary;

import java.util.*;
import java.time.*;

public class Word {
    public String word;
    public Set<Translation> translations = new LinkedHashSet<>();

    public Word(String word, Translation translation) {
        this.word = word;
        this.translations.add(translation) ;
    }

    public Word(String word){

        this.word = word;
    }

    public void addTranslation(String category, String translation) {
        LocalDateTime translationUploadDate = LocalDateTime.now();
        translations.add(new Translation(category,translation,translationUploadDate));
    }

    public Set<Translation> getTranslations() {

        return translations;
    }
    

    /*    public void addTranslation(String category, String translation) {
        Translation newTranslation = new Translation(category,translation);
        translations.add(newTranslation);

    }*/

}
