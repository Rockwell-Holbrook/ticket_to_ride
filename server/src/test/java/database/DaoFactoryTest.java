package database;


import com.example.shared.interfaces.IDaoFactory;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DaoFactoryTest {
    @Test
    public void sqlFactoryTest() {
        try{
            IDaoFactory ourFac = getFactory("C:\\Users\\taylo\\StudioProjects\\ticket_to_ride\\server", "SQLPlugin.jar", "com.example.sqlplugin.SQLDaoFactory");
            IGameDao ourGD = ourFac.createGameDao();
            Game ourGame = new Game(new Player("testing", true, Player.PlayerColor.BLUE), 5, "testing game");
            ourGD.saveGame(ourGame);
            Game fromDB = ourGD.getGame(ourGame.getGameId());
            Assert.assertEquals(fromDB, ourGame);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void ffFactoryTest() {
        try{
            IDaoFactory ourFac = getFactory("C:\\Users\\taylo\\StudioProjects\\ticket_to_ride\\server",
                    "FlatFilePlugin.jar", "com.example.flatfileplugin.FFDaoFactory");
            IGameDao ourGD = ourFac.createGameDao();
            Game ourGame = new Game(new Player("testing", true, Player.PlayerColor.BLUE), 5, "testing game");
            ourGD.saveGame(ourGame);
            Game fromDB = ourGD.getGame(ourGame.getGameId());
            Assert.assertEquals(fromDB, ourGame);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private IDaoFactory getFactory(String pluginDirectory, String pluginJarName, String pluginClassName) throws Exception {
        // Get a class loader and set it up to load the jar file
        File pluginJarFile = new File(pluginDirectory, pluginJarName);
        URL pluginURL = pluginJarFile.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});
        // Load the jar file's plugin class, create and return an instance
        Class<? extends IDaoFactory> factoryClass = (Class<? extends IDaoFactory>) loader.loadClass(pluginClassName);
        return factoryClass.getDeclaredConstructor(null).newInstance();
    }
}