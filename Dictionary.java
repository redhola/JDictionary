    package dictionary;

    import java.lang.*;
    import java.time.*;
    import java.util.*;
    import java.util.stream.Collectors;
    import java.util.stream.Stream;


    public class Dictionary {
        private final String fromLanguage;
        private final String toLanguage;

        private LocalDateTime lastModifyDate;
        private LocalDateTime firstModifyDate;

        public TreeMap<String, Word> words;

        public Dictionary(String fromLanguage, String toLanguage) {
            this.fromLanguage = fromLanguage;
            this.toLanguage = toLanguage;
            this.words = new TreeMap<>();
        }

        public void addWord(String written, String category, String translation) {
            Word word = words.get(written);
            LocalDateTime modificationTime = LocalDateTime.now();
            if (word == null) {
                word = new Word(written);
                words.put(written, word);
            }
            word.addTranslation(category, translation);
            updateModifyDate();
        }

        public Word search(String written) {

            return words.get(written);
        }

        //NEW
        public Stream<Word> searchCategory(String written) {
            return words.values().stream()
                    .filter(word -> word.getTranslations().stream()
                    .anyMatch(translation -> translation.getCategory().equalsIgnoreCase(written)));
        }

        public  void deleteWord(String written) {
            LocalDateTime modificationTime = LocalDateTime.now();
            words.remove(written);
            updateModifyDate();
        }

        private void updateModifyDate() {
            LocalDateTime modificationTime = LocalDateTime.now();
            if (firstModifyDate == null) {
                firstModifyDate = modificationTime;
            }
            lastModifyDate = modificationTime;
        }

        public LocalDateTime getFirstModifyDate() {

            return firstModifyDate;
        }

        public LocalDateTime getLastModifyDate() {

            return lastModifyDate;
        }

        public void top10Words(int top10) {
            Map<String, Long> wordFrequencies = words.values().stream() //Kelimeleri value kısımlarına göre aldığım için long kullandım
                    .flatMap(word -> word.getTranslations().stream()) // Flatmap ile buradaki gibi iç içe geçmiş kısımlarda stream sırasında kolayca sıralanabilir bir hale getirilebildiğini öğrendim ve kullandım
                    .collect(Collectors.groupingBy(Translation::getTranslation, Collectors.counting())); //Assignment'da kelimeler translationları ile istenildiği için onlarla beraber grupladım

            List<Map.Entry<String, Long>> mostCommonWords = wordFrequencies.entrySet().stream() //kelime sıklıklarına göre stream edip
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // listeyi en çoktan aza göre sıralattım
                    .limit(10)// top10
                    .collect(Collectors.toList());

            System.out.println(top10);
            for (Map.Entry<String, Long> entry : mostCommonWords) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }


    /*        List<LocalDateTime> tarihler = Collections.singletonList(lastModifyDate);
            IntSummaryStatistics summary = tarihler.stream()
                    .collect(Collectors.summarizingInt(LocalDateTime::Integer));*/
    /*        Dictionary dictionary = new Dictionary("tr", "en");
            dictionary.addWord("a", "a", "a");
            dictionary.addWord("a", "a", "a");
            dictionary.addWord("a", "a", "a");
            for (Translation translation : dictionary.search("a").getTranslations()) {
                System.out.println(translation.getCategory() + "#" + translation.getTranslation());
            }*/

