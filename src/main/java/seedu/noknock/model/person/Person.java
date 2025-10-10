package seedu.noknock.model.person;

import static seedu.noknock.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public abstract boolean isSamePerson(Person otherPerson);
}
