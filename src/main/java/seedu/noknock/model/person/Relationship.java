package seedu.noknock.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.noknock.commons.util.AppUtil.checkArgument;

/**
 * Represents the relationship of a Next of Kin to a Patient in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidRelationship(String)}
 */
public class Relationship {

    public static final String MESSAGE_CONSTRAINTS =
            "Relationship should only contain alphabetic characters and spaces, "
                    + "and it should not be blank.";

    /*
     * The first character must be a letter and not a whitespace.
     * This prevents inputs like just spaces or empty strings.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z][A-Za-z ]*";

    public final String value;

    /**
     * Constructs a {@code Relationship}.
     *
     * @param relationship A valid relationship descriptor.
     */
    public Relationship(String relationship) {
        requireNonNull(relationship);
        checkArgument(isValidRelationship(relationship), MESSAGE_CONSTRAINTS);
        value = relationship.trim();
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidRelationship(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Relationship)) {
            return false;
        }

        Relationship otherRelationship = (Relationship) other;
        return value.equalsIgnoreCase(otherRelationship.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
