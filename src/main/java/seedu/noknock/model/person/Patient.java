package seedu.noknock.model.person;

import java.util.Set;

import seedu.noknock.model.tag.Tag;

/**
 *
 */
public class Patient extends Person {
    private final IC ic;
    private final Ward ward;

    /**
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param ic
     * @param ward
     */
    public Patient(Name name, Phone phone, Email email, Address address, Set<Tag> tags, IC ic, Ward ward) {
        super(name, phone, email, address, tags);
        this.ic = ic;
        this.ward = ward;
    }

    public IC getIC() {
        return ic;
    }
    public Ward getWard() {
        return ward;
    }
}
