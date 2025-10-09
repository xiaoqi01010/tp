package seedu.noknock.model.person;

/**
 */
public class Ward {
    private final WardType wardType;
    private final int numberOfBeds;

    /**
     * Return a Ward object
     * @param wardType
     * @param numberOfBeds
     */
    public Ward(WardType wardType, int numberOfBeds) {
        this.wardType = wardType;
        this.numberOfBeds = numberOfBeds;
    }
    public WardType getWardType() {
        return wardType;
    }
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

}
