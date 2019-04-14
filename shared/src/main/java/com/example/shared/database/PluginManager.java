package com.example.shared.database;

import com.example.shared.interfaces.IDaoFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    static private PluginManager instance = null;
    private String pluginDirectory;
    private String pluginJarName;
    private String pluginClassName;

    static public PluginManager getInstance() {
        if (instance == null) {
            instance = new PluginManager();
        }
        return instance;
    }

    private PluginManager() {}

    public String getPluginDirectory() {
        return pluginDirectory;
    }

    public String getPluginJarName() {
        return pluginJarName;
    }

    public String getPluginClassName() {
        return pluginClassName;
    }

    public void setPluginDirectory(String pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    public void setPluginJarName(String pluginJarName) {
        this.pluginJarName = pluginJarName;
    }

    public void setPluginClassName(String pluginClassName) {
        this.pluginClassName = pluginClassName;
    }

    public IDaoFactory getFactory() throws Exception {
        // Get a class loader and set it up to load the jar file
        File pluginJarFile = new File(pluginDirectory, pluginJarName);
        URL pluginURL = pluginJarFile.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});
        // Load the jar file's plugin class, create and return an instance
        Class<? extends IDaoFactory> factoryClass = (Class<? extends IDaoFactory>) loader.loadClass(pluginClassName);
        return factoryClass.getDeclaredConstructor(null).newInstance();
    }
}
