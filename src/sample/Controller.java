package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private ComboBox<Auditorium> cbAud;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button btnCompute;
    @FXML
    private Label lblAnswer;

    @FXML
    public void initialize(){
        cbAud.setConverter(new Auditorium.strConvAud());
        cbAud.getEditor().setOnKeyReleased(keyEvent -> {
            try {
                textEnteredCB(keyEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void clickBtnCompute(ActionEvent event) throws java.text.ParseException, IOException, ParseException {
        var end = this.endDate.getValue();
        var start = this.startDate.getValue();
        var aud = cbAud.getValue();
        if(aud!=null && start !=null && end !=null){
            if(end.compareTo(start)>=0){
                JSONArray jArr = EntityCreator.getJsonArray(EntityCreator.RequestObject.AUDITORIUM, aud.getId().toString(), start, end);
                Schedule sch = new Schedule(jArr, start,end);
                lblAnswer.setText(sch.getCountCouples() / 50.0 * 100 + "%");
            }
        }
    }

    public void textEnteredCB(KeyEvent keyEvent) throws IOException, ParseException {
        String resp = cbAud.getEditor().getText();
        if (resp.length() >= 2) {
            ArrayList<Auditorium> alA = new ArrayList<>();
            for (var obj :
                    EntityCreator.getJsonArray(EntityCreator.RequestObject.AUDITORIUM, resp)) {
                alA.add(new Auditorium((JSONObject)obj));
            }
            cbAud.setItems(FXCollections.observableArrayList(alA));
        }
    }
}
