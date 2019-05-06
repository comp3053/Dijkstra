package controller;

import view.HomeView;

public class BrewReciptController {
    public BrewReciptController(){

    }
    public void finish(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
