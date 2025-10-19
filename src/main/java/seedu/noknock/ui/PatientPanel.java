package seedu.noknock.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.noknock.commons.core.LogsCenter;
import seedu.noknock.model.person.Patient;

/**
 * Panel containing the list of persons.
 */
public class PatientPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientPanel.class);

    @FXML
    private ListView<Patient> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PatientPanel(ObservableList<Patient> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PersonListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
