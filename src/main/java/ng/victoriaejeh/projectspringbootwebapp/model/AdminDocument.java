package ng.victoriaejeh.projectspringbootwebapp.model;

public class AdminDocument {

    private String title;  // The title of the document
    private String description;  // A brief description of the document

    /**
     * Constructor to initialize AdminDocument with a title and description
      */

    public AdminDocument(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Getter for the title
     */

    public String getTitle() {
        return title;
    }

    /**
     *   Setter for the title
      */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *   Getter for the description
      */

    public String getDescription() {
        return description;
    }

    /**
     *     Setter for the description
     */

    public void setDescription(String description) {
        this.description = description;
    }
}
