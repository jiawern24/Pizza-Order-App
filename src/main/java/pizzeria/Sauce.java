package pizzeria;

/**
 * Sauce enum class to classify the type of sauces available for pizza
 *
 * @author Jia Wern Chong
 */
public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo");

    private String sauceName;

    /**
     * Sauce constant with a value for each type of sauce
     * @param sauceName Sauce name for each type of sauce
     */
    Sauce(String sauceName){
        this.sauceName = sauceName;
    }

    /**
     * Gets sauce value for the sauce
     * @return Sauce value of the sauce
     */
    public String getSauceName(){
        return sauceName;
    }

}
