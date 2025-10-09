package seedu.noknock.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.noknock.model.tag.Tag;

/**
 * Represents a Next of Kin in the system.
 * A NextOfKin is a person related to one or more patients, but does not store patient references.
 */
public class NextOfKin extends Person {

    private final Relationship relationship;
    private final boolean isEmergencyContact;

    /**
     * Constructs a {@code NextOfKin}.
     */
    public NextOfKin(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     Relationship relationship, boolean isEmergencyContact) {
        super(name, phone, email, address, tags);
        this.relationship = Objects.requireNonNull(relationship);
        this.isEmergencyContact = isEmergencyContact;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public boolean isEmergencyContact() {
        return isEmergencyContact;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NextOfKin)) {
            return false;
        }

        NextOfKin otherNok = (NextOfKin) other;
        return super.equals(otherNok)
                && relationship.equals(otherNok.relationship)
                && isEmergencyContact == otherNok.isEmergencyContact;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), relationship, isEmergencyContact);
    }

    @Override
    public String toString() {
        return super.toString() + " Relationship: " + relationship
                + " Emergency Contact: " + (isEmergencyContact ? "Yes" : "No");
    }
}
