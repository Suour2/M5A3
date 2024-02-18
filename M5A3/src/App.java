
/* Bailey Garrett
 * module 5 assignment 3 
 * 2/17/24 
 * Circle color change on mouse pressed and released 
 */
// add java fx library files to referenced libraries in java project tab
// click run at the top and add configurations
// copy paste last line of code in the launch.json file, the vmArgs command
// code goes into source folder file.
// have not figured out how to change the name of the app from App, doing so causes errors.
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private Text text;
    private ScrollBar redSlider, greenSlider, blueSlider, opacitySlider;

    @Override
    public void start(Stage primaryStage) {
        // create text
        text = new Text("Color Selector");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));

        // create color and opacity sliders
        redSlider = createSlider("Red", 0, 255, 0);
        greenSlider = createSlider("Green", 0, 255, 0);
        blueSlider = createSlider("Blue", 0, 255, 0);
        opacitySlider = createSlider("Opacity", 0, 100, 100);

        // create Vbox
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(text, createColorSlider("Red", redSlider), createColorSlider("Green", greenSlider),
                createColorSlider("Blue", blueSlider), createColorSlider("Opacity", opacitySlider));

        // create scene and add it to stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();

        // color is updated based on slider values
        updateColor();
    }

    // create slider with name, min, max and initial value
    private ScrollBar createSlider(String name, double min, double max, double value) {
        ScrollBar slider = new ScrollBar();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(value);
        slider.setUnitIncrement(1);
        slider.setBlockIncrement(10);

        // add listener to update color changes
        slider.valueProperty().addListener((observable, oldValue, newValue) -> updateColor());

        return slider;
    }

    // create grid pane with label and slider
    private GridPane createColorSlider(String color, ScrollBar slider) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label nameLabel = new Label(color);
        gridPane.add(nameLabel, 0, 0);

        gridPane.add(slider, 1, 0);

        return gridPane;
    }

    // update color of text based off slider value
    private void updateColor() {
        int red = (int) redSlider.getValue();
        int green = (int) greenSlider.getValue();
        int blue = (int) blueSlider.getValue();
        double opacity = opacitySlider.getValue() / 100.0;

        text.setFill(Color.rgb(red, green, blue, opacity));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
