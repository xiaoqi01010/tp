package seedu.noknock.testutil;

import static seedu.noknock.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_WARD_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.noknock.model.AddressBook;
import seedu.noknock.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Person ALICE = new PatientBuilder().withName("Alice Pauline")
            .withWard("2A").withIC("S1234567A")
            .withTags("friends").build();
    public static final Person BENSON = new PatientBuilder().withName("Benson Meier")
            .withWard("2A").withIC("S1234567A")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PatientBuilder().withName("Carl Kurz")
            .withWard("2A").withIC("S1234567A").build();
    public static final Person DANIEL = new PatientBuilder().withName("Daniel Meier").withWard("2A")
            .withIC("S1234567A").withTags("friends").build();
    public static final Person ELLE = new PatientBuilder().withName("Elle Meyer").withWard("2A")
            .withIC("S1234567A").build();
    public static final Person FIONA = new PatientBuilder().withName("Fiona Kunz").withWard("2A")
            .withIC("S1234567A").build();
    public static final Person GEORGE = new PatientBuilder().withName("George Best").withWard("2A")
            .withIC("S1234567A").build();

    // Manually added
    public static final Person HOON = new PatientBuilder().withName("Hoon Meier").withWard("2A")
            .withIC("S1234567A").build();
    public static final Person IDA = new PatientBuilder().withName("Ida Mueller").withWard("2A")
            .withIC("S1234567A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PatientBuilder().withName(VALID_NAME_AMY)
            .withWard(VALID_WARD_AMY).withIC(VALID_IC_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PatientBuilder().withName(VALID_NAME_BOB)
            .withWard(VALID_WARD_AMY).withIC(VALID_IC_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
