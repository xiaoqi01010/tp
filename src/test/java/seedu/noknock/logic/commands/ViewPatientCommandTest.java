package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.logic.Messages.MESSAGE_VIEW_PATIENT_SUCCESS;
import static seedu.noknock.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.noknock.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.ModelManager;
import seedu.noknock.model.person.Patient;
import seedu.noknock.testutil.PatientBuilder;

public class ViewPatientCommandTest {

    private Model model;
    private Patient alice;
    private Patient bob;

    @BeforeEach
    public void setUp() {
        alice = new PatientBuilder().withName("Alice").withIC("S1111111A").build();
        bob = new PatientBuilder().withName("Bob").withIC("S2222222B").build();
        model = new ModelManager();
        model.addPatient(alice);
        model.addPatient(bob);
    }
    //valid index
    @Test
    public void execute_validIndex_success() throws Exception {
        ViewPatientCommand command = new ViewPatientCommand(INDEX_FIRST_PERSON);
        CommandResult result = command.execute(model);

        String expectedMessage = String.format(MESSAGE_VIEW_PATIENT_SUCCESS, alice.getName().fullName);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
    //index out of bound -1
    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        ViewPatientCommand command = new ViewPatientCommand(Index.fromZeroBased(2));
        assertThrows(CommandException.class, () -> command.execute(model));
    }
    //null command
    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ViewPatientCommand command = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }
    //tests empty patient list with index 1
    @Test
    public void execute_emptyPatientList_throwsCommandException() {
        Model emptyModel = new ModelManager();
        ViewPatientCommand command = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertThrows(CommandException.class, () -> command.execute(emptyModel));
    }
    //tests if command is equal to itself
    @Test
    public void equals_sameInstance_returnsTrue() {
        ViewPatientCommand command = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertTrue(command.equals(command));
    }
    //tests if equivalent indices will result in equivalent ViewPatientCommand
    @Test
    public void equals_sameIndex_returnsTrue() {
        ViewPatientCommand first = new ViewPatientCommand(INDEX_FIRST_PERSON);
        ViewPatientCommand second = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertTrue(first.equals(second));
    }
    //tests if different indices will result in different ViewPatientCommand
    @Test
    public void equals_differentIndex_returnsFalse() {
        ViewPatientCommand first = new ViewPatientCommand(INDEX_FIRST_PERSON);
        ViewPatientCommand second = new ViewPatientCommand(INDEX_SECOND_PERSON);
        assertFalse(first.equals(second));
    }
    //tests equals function with null and string
    @Test
    public void equals_nullOrDifferentType_returnsFalse() {
        ViewPatientCommand command = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertFalse(command.equals(null));
        assertFalse(command.equals("string"));
    }
}
