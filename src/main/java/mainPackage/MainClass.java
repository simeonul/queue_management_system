package mainPackage;

import controller.SimulationManager;
import view.SimulationFrame;

public class MainClass {
    public static void main(String[] args) {
        SimulationFrame view = new SimulationFrame();
        SimulationManager gen = new SimulationManager(view);
    }
}
