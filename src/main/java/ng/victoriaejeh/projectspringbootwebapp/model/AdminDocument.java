package ng.victoriaejeh.projectspringbootwebapp.model;

/**
 * Represents an administrative document with a title and description.
 */
public class AdminDocument {

    private String title;  // The title of the document
    private String description;  // A brief description of the document

    /**
     * Constructor to initialize AdminDocument with a title and description.
     *
     * @param title The title of the document.
     * @param description A brief description of the document.
     */
    public AdminDocument(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Retrieves the title of the document.
     *
     * @return The title of the document.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the document.
     *
     * @param title The new title of the document.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the description of the document.
     *
     * @return The description of the document.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the document.
     *
     * @param description The new description of the document.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
