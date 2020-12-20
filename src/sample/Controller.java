package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
                ConnectionToAPI ctapi = new ConnectionToAPI();
                ctapi.createResponse(ConnectionToAPI.TypeResponse.SCHEDULE, ConnectionToAPI.ObjectResponse.AUDITORIUM, aud.getId().toString(), start, end);
                ctapi.openConnection();
                Schedule sch = new Schedule(ctapi.getJsonArray(), start, end);
                ScheduleAnalyzer analyzer = new ScheduleAnalyzer(sch);
                lblAnswer.setText(String.format("%.2f %s", analyzer.getWorkloadPercent(), "%"));
            }
        }
    }

    public void textEnteredCB(KeyEvent keyEvent) throws IOException, ParseException {
        String resp = cbAud.getEditor().getText();
        if (resp.length() >= 2) {
            ArrayList<Auditorium> alA = new ArrayList<>();
            ConnectionToAPI cta = new ConnectionToAPI();
            cta.createResponse(ConnectionToAPI.TypeResponse.SEARCH, ConnectionToAPI.ObjectResponse.AUDITORIUM,resp);
            cta.openConnection();
            for (var obj :
                    cta.getJsonArray()) {
                alA.add(new Auditorium((JSONObject) obj));
            }
            cbAud.setItems(FXCollections.observableArrayList(alA));
        }
    }
}
