package ng.victoriaejeh.projectspringbootwebapp.model;

public class ManagerInfo {

    private String title;
    private String infoText;

    /**
     * Constructor to initialize ManagerInfo with a title and information text
      */

    public ManagerInfo(String title, String infoText) {
        this.title = title;
        this.infoText = infoText;
    }

    /**
     *     Getter for title
     */

    public String getTitle() {
        return title;
    }

    /**
     *     Setter for title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for information text
     */

    public String getInfoText() {
        return infoText;
    }

    /**
     * Setter for information text
     */

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}
