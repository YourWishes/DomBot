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

package com.domsplace.DomBot;

import com.domsplace.DomBot.Bases.DomsThread;
import com.domsplace.DomBot.Bases.Base;
import com.domsplace.DomBot.Bases.DataManager;
import com.domsplace.DomBot.Listeners.ResponseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author      Dominic
 * @since       12/11/2013
 */
public class DomBotPlugin extends JavaPlugin {
    private boolean enabled = false;
    
    //Commands
    
    //Listeners
    private ResponseListener listener;
    
    //Threads
    
    @Override
    public void onEnable() {
        //Register Plugin
        Base.setPlugin(this);
        
        if(!DataManager.loadAll()) {
            this.disable();
            return;
        }
        
        this.listener = new ResponseListener();
        
        this.enabled = true;
        Base.debug("Finished Loading " + this.getName());
    }
    
    @Override
    public void onDisable() {
        if(!enabled) {
            return;
        }
        
        //Unhook Economy
        DomsThread.stopAllThreads();
        DataManager.saveAll();
    }
    
    public void disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }
}