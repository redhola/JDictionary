    package dictionary;

    import java.time.*;

    public class Translation {
        private String category;
        private String translation;

        private LocalDateTime translationUploadDate;

        public Translation(String category, String translation, LocalDateTime translationUploadDate) {
            this.category = category;
            this.translation = translation;
            this.translationUploadDate = translationUploadDate;
        }

        public String getCategory() {

            return category;
        }

        public String getTranslation() {

            return translation;
        }
    }
