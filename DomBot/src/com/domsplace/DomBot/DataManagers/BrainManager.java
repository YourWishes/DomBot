/*
 * Copyright 2013 Dominic.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domsplace.DomBot.DataManagers;

import com.domsplace.DomBot.Bases.DataManager;
import com.domsplace.DomBot.Enums.ManagerType;
import com.domsplace.DomBot.Object.Responder;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author      Dominic
 * @since       11/10/2013
 */
public class BrainManager extends DataManager {
    private YamlConfiguration config;
    private File configFile;
    
    public BrainManager() {
        super(ManagerType.BRAIN);
    }
    
    public YamlConfiguration getCFG() {
        return config;
    }
    
    @Override
    public void tryLoad() throws IOException {
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        this.configFile = new File(getDataFolder(), "brain.yml");
        if(!this.configFile.exists()) configFile.createNewFile();
        this.config = YamlConfiguration.loadConfiguration(configFile);
        
        Responder.BRAIN.clear();
        
        /*** GENERATE DEFAULT CONFIG ***/
        for(String s : config.getKeys(false)) {
            String learn = this.config.getString(s);
            Responder.BRAIN.put(s, learn);
        }
        
        //Save Data
        this.trySave();
    }
    
    @Override
    public void trySave() throws IOException {
        for(String s : Responder.BRAIN.keySet()) {
            String learn = Responder.BRAIN.get(s);
            this.config.set(s, learn);
        }
        this.config.save(configFile);
    }

    public void setCFG(YamlConfiguration removeFromYml) {
        this.config = removeFromYml;
    }
}
