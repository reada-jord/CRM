    package Controller;

    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.web.WebEngine;
    import javafx.scene.web.WebView;
    import javafx.stage.Stage;


    public class DashboardClientController {



        @FXML
        private Button logout;

        @FXML
        private WebView webView;

        @FXML
        private void initialize() {
                // Get the WebEngine
                WebEngine webEngine = webView.getEngine();
            System.out.println("Loading HTML file...");
                // Load the HTML file into WebView
                webEngine.load(getClass().getResource("@../../../assets/map.html").getFile());
                webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                    // Once the page has loaded, add a marker
                    addMarker(webEngine, 37.7749, -122.4194, "Marker Title");
                }
            });
        }

        @FXML
        public void onlogout(){
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
        }
        private void addMarker(WebEngine webEngine, double latitude, double longitude, String title) {
            // Execute JavaScript code to add a marker
            String addMarkerScript = String.format("addMarker(%f, %f, '%s');", latitude, longitude, title);
            webEngine.executeScript(addMarkerScript);
        }

    }
