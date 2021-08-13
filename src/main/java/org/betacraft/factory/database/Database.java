package org.betacraft.factory.database;

import org.betacraft.factory.database.enums.DatabaseType;

public interface Database {

    void onLoad();

    void onUnload();

}
