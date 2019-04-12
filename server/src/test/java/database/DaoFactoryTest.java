package database;


import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DaoFactoryTest {
    @Test
    public void factoryTest() {
        try{
            IDaoFactory ourFac = getFactory("C:\\Users\\taylo\\StudioProjects\\ticket_to_ride\\server", "SQLPlugin.jar", "com.example.sqlplugin.SQLDaoFactory");
            IGameDao ourGD = ourFac.createGameDao();
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