package seedu.noknock.model.person;

import static seedu.noknock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.noknock.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Identity fields
    private final Name name;
    private Phone phone = new Phone("1234568");
    private Email email = new Email("test@gmail.com");

    // Data fields
    private Address address = new Address("123 Main Street");
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }


    public Name getName() {
        return name;
    }
    //Kept to prevent tests from breaking
    public Email getEmail() {
        return email;
    }
    public Address getAddress() {
        return address;
    }
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }
        if (otherPerson.getName() == this.getName()) {
            return true;
        }
        return false;
    }

}
