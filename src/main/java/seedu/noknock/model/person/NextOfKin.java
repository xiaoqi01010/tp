package seedu.noknock.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.noknock.model.tag.Tag;

/**
 * Returns a next of kin object
 */
public class NextOfKin extends Person {
    private final Patient patient;
    private final Relationship relationship;
    private final List<Person> nextOfKinList = new ArrayList<>();
    /**
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public NextOfKin(Name name, Phone phone, Email email, Address address,
                      Set<Tag> tags, Relationship relationship, Patient patient) {
        super(name, phone, email, address, tags);
        this.relationship = relationship;
        this.patient = patient;
    }
}
