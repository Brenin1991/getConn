package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sample.model.Downloads;
import sample.model.Favorites;
import sample.model.History;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserController implements Initializable {
    @FXML
    private JFXButton btVoltar = new JFXButton();
    @FXML
    private JFXButton btAvancar  = new JFXButton();
    @FXML
    private JFXButton btHome = new JFXButton();
    @FXML
    private JFXButton btRecarregar = new JFXButton();
    @FXML
    private JFXTextField tfUrl = new JFXTextField();
    @FXML
    private JFXButton btSearch = new JFXButton();
    @FXML
    private JFXButton btFavorites = new JFXButton();
    @FXML
    private JFXButton btDownloads = new JFXButton();
    @FXML
    private JFXButton btHistory = new JFXButton();
    @FXML
    private WebView wvBrowser = new WebView();

    private History history;
    private Favorites favorites;
    private Downloads downloads;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wvBrowser.getEngine().setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Version/8.0 Safari/537.36");
        controls();
        loadWebView();
        loadUrl();
    }

    public void loadWebView(){
        wvBrowser.getEngine().load("https://www.google.com");
    }

    public void controls(){
        btVoltar.setOnMouseClicked(event1 -> {
            wvBrowser.getEngine().getHistory().go(-1);
        });
        btAvancar.setOnMouseClicked(event1 -> {
            wvBrowser.getEngine().getHistory().go(1);
        });
        btHome.setOnMouseClicked(event1 -> {
            loadWebView();
        });
        btRecarregar.setOnMouseClicked(event1 -> {
            wvBrowser.getEngine().reload();
        });
        btSearch.setOnMouseClicked(event1 -> {
            wvBrowser.getEngine().load("https://"+tfUrl.getText());
        });
        btFavorites.setOnMouseClicked(event1 -> {
            FavoritesWindow();
        });
        btDownloads.setOnMouseClicked(event1 -> {
            DownloadsWindow();
        });
        btHistory.setOnMouseClicked(event1 -> {
            HistoryWindow();
        });
    }

    public void loadUrl(){
        tfUrl.setText(wvBrowser.getEngine().getLocation());
    }

   /* //Metodo fechar janela
    public void Sair(){
        login.GetStage().close();
    }*/
    public void FavoritesWindow(){
        favorites  = new Favorites();
        try {
            favorites.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DownloadsWindow(){
        downloads = new Downloads();
        try {
            downloads.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void HistoryWindow(){
        history  = new History();
        try {
            history.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
