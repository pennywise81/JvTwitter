import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class JvTwitter extends Application {

  private BorderPane rootPane;
  private GridPane statusbar;
  private GridPane menubar;
  private Label appSizeLbl;
  private Label refreshDateLbl;
  private Button refreshBtn;
  private VBox timelineView;

  @Override
  public void start(Stage primaryStage) {

    rootPane = new BorderPane();
    rootPane.setBottom(initStatusbar());
    rootPane.setCenter(initTimelineView());
    rootPane.setTop(initMenubar());

    Scene scene = new Scene(rootPane, 300, 250);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public GridPane initMenubar() {
    menubar = new GridPane();
    menubar.setGridLinesVisible(true);
    ColumnConstraints menubarCol1 = new ColumnConstraints();
    menubarCol1.setPercentWidth(100);
    menubar.getColumnConstraints().addAll(menubarCol1);

    refreshBtn = new Button("Refresh");
    refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("Button Pressed");
      }
    });

    menubar.add(refreshBtn, 0, 0);

    return menubar;
  }

  public VBox initTimelineView() {
    timelineView = new VBox();

    BackgroundFill myBF = new BackgroundFill(Color.YELLOW, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));// or null for the padding
    timelineView.setBackground(new Background(myBF));

    Text blindtext = new Text("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient");
    GridPane tmpBox = new GridPane();
    //tmpBox.setGridLinesVisible(true);

    ColumnConstraints tmpCol1 = new ColumnConstraints();
    tmpCol1.setPercentWidth(50);
    ColumnConstraints tmpCol2 = new ColumnConstraints();
    tmpCol2.setPercentWidth(50);
    tmpBox.getColumnConstraints().addAll(tmpCol1, tmpCol2);

    TextFlow text = new TextFlow();

    Hyperlink link = new Hyperlink("Link");
    link.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println(e.getTarget().toString());
        link.setVisited(false);

        try {
          Desktop.getDesktop().browse(new URI("http://www.google.com/"));
        }
        catch (IOException e1) {}
        catch (URISyntaxException e1) {}
      }
    });

    text.getChildren().addAll(link, blindtext);

    tmpBox.add(new Label("Test"), 0, 0);
    tmpBox.add(new Label("Test2"), 1, 0);
    tmpBox.add(text, 0, 1, 2, 1);

    /* AB HIER TEST */
    String tmpStatusUserName = new String("Carsten Knobloch");
    String tmpStatusUserTwitterName = new String("caschy");
    String tmpStatusText = new String("Bleep mit Foto-Sharing http://wp.me/p4KRN-35s");
    String tmpStatusUrl = new String("https://twitter.com/caschy/status/585064737341218817");
    Date tmpStatusDate = new Date();
    DateFormat df = new SimpleDateFormat("HH:mm");

    GridPane timelineItem = new GridPane();
    timelineItem.setGridLinesVisible(true);
    ColumnConstraints timelineItemCol1 = new ColumnConstraints();
    tmpCol1.setPercentWidth(50);
    ColumnConstraints timelineItemCol2 = new ColumnConstraints();
    tmpCol2.setPercentWidth(50);
    timelineItem.getColumnConstraints().addAll(timelineItemCol1, timelineItemCol2);

    Hyperlink tmpUserProfileLink = new Hyperlink(tmpStatusUserName);
    tmpUserProfileLink.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String tmpUrl = new String("https://twitter.com/" + tmpStatusUserTwitterName + "/");
        link.setVisited(false);

        try {
          Desktop.getDesktop().browse(new URI(tmpUrl));
        }
        catch (IOException e1) {}
        catch (URISyntaxException e1) {}
      }
    });

    Hyperlink tmpStatusLink = new Hyperlink(df.format(tmpStatusDate));
    tmpStatusLink.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String tmpUrl = new String(tmpStatusUrl);
        link.setVisited(false);

        try {
          Desktop.getDesktop().browse(new URI(tmpUrl));
        }
        catch (IOException e1) {}
        catch (URISyntaxException e1) {}
      }
    });

    timelineItem.add(tmpUserProfileLink, 0, 0);
    timelineItem.add(tmpStatusLink, 1, 0);

    timelineView.getChildren().add(timelineItem);
    /* BIS HIER TEST */

    timelineView.getChildren().add(tmpBox);

    return timelineView;
  }

  public GridPane initStatusbar() {
    statusbar = new GridPane();
    statusbar.setGridLinesVisible(true);
    ColumnConstraints statusbarCol1 = new ColumnConstraints();
    ColumnConstraints statusbarCol2 = new ColumnConstraints();
    statusbarCol1.setPercentWidth(50);
    statusbarCol2.setPercentWidth(50);
    statusbarCol2.setHalignment(HPos.RIGHT);
    statusbar.getColumnConstraints().addAll(statusbarCol1, statusbarCol2);

    refreshDateLbl = new Label("{REFRESH_DATE}");
    appSizeLbl = new Label("{APP_SIZE}");

    statusbar.add(refreshDateLbl, 0, 0);
    statusbar.add(appSizeLbl, 1, 0);

    return statusbar;
  }
}
