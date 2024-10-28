package xyz.devcmb.cmbMinigamesServer.CMBase.misc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class Database {
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection partyCollection;

    public static void connect(){
        FileConfiguration config = CmbMinigamesServer.getPlugin().getConfig();
        String uri = config.getString("database.uri");
        String databaseName = config.getString("database.databaseName");
        String partyCollectionName = config.getString("database.partyCollection");

        if(
            (uri == null || !uri.startsWith("mongodb"))
            || (databaseName == null || databaseName.isEmpty())
            || (partyCollectionName == null || partyCollectionName.isEmpty())
        ){
            CmbMinigamesServer.LOGGER.severe("Database configuration is invalid");
            CmbMinigamesServer.PluginDisabled = true;
            return;
        }

        client = MongoClients.create(uri);
        database = client.getDatabase(databaseName);
        partyCollection = database.getCollection(partyCollectionName);
    }
}
