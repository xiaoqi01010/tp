package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.logic.Messages.MESSAGE_INVALID_NOK_DISPLAYED_INDEX;
import static seedu.noknock.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.noknock.commons.core.GuiSettings;
import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.testutil.NextOfKinBuilder;
import seedu.noknock.testutil.PatientBuilder;

public class DeleteNextOfKinCommandTest {

    private Patient aliceBase;
    private NextOfKin nok1;
    private NextOfKin nok2;

    private Patient aliceWithTwoNoks;

    private ModelStubCapturingSet model;

    @BeforeEach
    public void setUp() {
        // Base patient
        aliceBase = new PatientBuilder().withName("Alice").withIC("S1111111A").build();

        // Two NOKs
        nok1 = new NextOfKinBuilder().withName("Lee").withPhone("0651111111").withRelationship("daughter").build();
        nok2 = new NextOfKinBuilder().withName("Chan").withPhone("0652222222").withRelationship("son").build();

        // Patient with 2 NOKs
        aliceWithTwoNoks = aliceBase.withNextOfKinList(List.of(nok1, nok2));

        // Model with a single patient in filtered list
        model = new ModelStubCapturingSet(List.of(aliceWithTwoNoks));
    }

    //valid patient index & valid NOK index -> success, NOK removed, list refreshed
    @Test
    public void execute_validIndices_success() throws Exception {
        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        CommandResult result = cmd.execute(model);

        // Message
        assertTrue(result.getFeedbackToUser().contains("Deleted NextOfKin"));

        // Model.setPatient called with edited patient
        assertSame(aliceWithTwoNoks, model.lastSetOriginal);
        assertNotNull(model.lastSetEdited);

        // NOK removed
        List<NextOfKin> editedNoks = model.lastSetEdited.getNextOfKinList();
        assertEquals(1, editedNoks.size());
        assertEquals(nok2, editedNoks.get(0)); // only nok2 remains

        // List refreshed to show all persons
        assertTrue(model.updateCalled);
        assertEquals(Model.PREDICATE_SHOW_ALL_PERSONS, model.lastPredicate);
    }

    //patient index out-of-bounds (>= size) leads to CommandException(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX)
    @Test
    public void execute_outOfBoundsPatientIndex_throwsCommandException() {
        int outOfBounds = model.getFilteredPatientList().size(); // 1
        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(outOfBounds), Index.fromZeroBased(0));

        CommandException ex = assertThrows(CommandException.class, () -> cmd.execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, ex.getMessage());
    }

    // P3: NOK index out-of-bounds high (>= size) leads to CommandException(MESSAGE_INVALID_NOK_DISPLAYED_INDEX)
    @Test
    public void execute_outOfBoundsNokIndex_throwsCommandException() {
        // alice has 2 NOKs: valid indices 0,1 so using 2 triggers out-of-bounds
        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(2));

        CommandException ex = assertThrows(CommandException.class, () -> cmd.execute(model));
        assertEquals(MESSAGE_INVALID_NOK_DISPLAYED_INDEX, ex.getMessage());
    }

    //NOK list empty
    @Test
    public void execute_emptyNokList_throwsCommandException() {
        Patient aliceNoNok = aliceBase.withNextOfKinList(List.of());
        model = new ModelStubCapturingSet(List.of(aliceNoNok));

        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        CommandException ex = assertThrows(CommandException.class, () -> cmd.execute(model));
        assertEquals(MESSAGE_INVALID_NOK_DISPLAYED_INDEX, ex.getMessage());
    }

    //model is null → NullPointerException
    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    /* =========================
       equals() EP partitions
       ========================= */

    @Test
    public void equals_sameObject_true() {
        DeleteNextOfKinCommand cmd =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(1));
        assertTrue(cmd.equals(cmd));
    }

    @Test
    public void equals_sameIndices_true() {
        DeleteNextOfKinCommand a =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(1));
        DeleteNextOfKinCommand b =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(1));
        assertTrue(a.equals(b));
    }

    @Test
    public void equals_differentPatientIndex_false() {
        DeleteNextOfKinCommand a =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(1));
        DeleteNextOfKinCommand b =
                new DeleteNextOfKinCommand(Index.fromZeroBased(1), Index.fromZeroBased(1));
        assertFalse(a.equals(b));
    }

    @Test
    public void equals_differentNokIndex_false() {
        DeleteNextOfKinCommand a =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));
        DeleteNextOfKinCommand b =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(1));
        assertFalse(a.equals(b));
    }

    @Test
    public void equals_nullOrDifferentType_false() {
        DeleteNextOfKinCommand a =
                new DeleteNextOfKinCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));
        assertFalse(a.equals(null));
        assertFalse(a.equals("not-a-command"));
    }

    @Test
    public void toString_containsIndices() {
        DeleteNextOfKinCommand a =
                new DeleteNextOfKinCommand(Index.fromZeroBased(3), Index.fromZeroBased(5));
        String s = a.toString();
        assertTrue(s.contains("patientIndex"));
        assertTrue(s.contains("nokIndex"));
    }
    //A stub for replacing actual model
    private static class ModelStubCapturingSet implements Model {
        private final ObservableList<Patient> filtered;
        private Patient lastSetOriginal;
        private Patient lastSetEdited;
        private GuiSettings guiSettings;

        private boolean updateCalled = false;
        private Predicate<Patient> lastPredicate;

        ModelStubCapturingSet(List<Patient> filtered) {
            this.filtered = FXCollections.observableArrayList(filtered);
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            return filtered;
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            this.lastSetOriginal = target;
            this.lastSetEdited = editedPatient;

            int idx = filtered.indexOf(target);
            if (idx >= 0) {
                filtered.set(idx, editedPatient);
            }
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            this.updateCalled = true;
            this.lastPredicate = predicate;
        }

        @Override
        public void setUserPrefs(seedu.noknock.model.ReadOnlyUserPrefs userPrefs) {
            throw new UnsupportedOperationException();
        }
        @Override
        public seedu.noknock.model.ReadOnlyUserPrefs getUserPrefs() {
            throw new UnsupportedOperationException();
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            this.guiSettings = guiSettings;
        }

        @Override
        public java.nio.file.Path getAddressBookFilePath() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void setAddressBookFilePath(java.nio.file.Path addressBookFilePath) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void setAddressBook(seedu.noknock.model.ReadOnlyAddressBook addressBook) {
            throw new UnsupportedOperationException();
        }
        @Override
        public seedu.noknock.model.ReadOnlyAddressBook getAddressBook() {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPatient(Patient patient) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void addPatient(Patient patient) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void deletePatient(Patient target) {
            throw new UnsupportedOperationException();
        }
        public void addNextOfKin(Patient patient, NextOfKin nok) {
            throw new UnsupportedOperationException();
        }
        public void setNok(Patient patient, NextOfKin target, NextOfKin editedNok) {
            throw new UnsupportedOperationException();
        }
        public void deleteNextOfKin(Patient patient, NextOfKin nok) {
            throw new UnsupportedOperationException();
        }
    }
}
