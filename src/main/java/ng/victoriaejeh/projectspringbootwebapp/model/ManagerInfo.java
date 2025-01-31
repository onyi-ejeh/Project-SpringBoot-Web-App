package ng.victoriaejeh.projectspringbootwebapp.model;

/**
 * Represents information related to a manager, including a title and descriptive text.
 */
public class ManagerInfo {

    private String title;  // Title related to the manager's information
    private String infoText;  // Descriptive text providing additional information

    /**
     * Constructs a ManagerInfo instance with a specified title and information text.
     *
     * @param title The title of the manager's information.
     * @param infoText The descriptive text providing additional details.
     */
    public ManagerInfo(String title, String infoText) {
        this.title = title;
        this.infoText = infoText;
    }

    /**
     * Retrieves the title of the manager's information.
     *
     * @return The title of the information.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the manager's information.
     *
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the descriptive information text.
     *
     * @return The information text.
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * Sets the descriptive information text.
     *
     * @param infoText The new information text.
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}
