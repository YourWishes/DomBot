/*
 * Copyright 2013 Dominic Masters.
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

package com.domsplace.DomBot.Object;

import org.bukkit.OfflinePlayer;

/**
 *
 * @author Dominic Masters
 */
public final class DomBotResponse {
    private final OfflinePlayer player;
    private final String cmd;
    
    public DomBotResponse(OfflinePlayer player, String cmd) {
        this.player = player;
        this.cmd = cmd;
    }
    
    public OfflinePlayer getPlayer() {return this.player;}
    public String getCommand() {return this.cmd;}
    public String[] getArgs() {return this.cmd.split(" ");}
    public String[] getCleanArgs() {return this.getBasicResponse().split(" ");}
    public String getBasicResponse() {return Responder.deSymbolise(cmd);}

    public boolean hasArgStartsWith(String about) {
        for(String s : this.getCleanArgs()) {
            if(s.toLowerCase().startsWith(about.toLowerCase())) return true;
        }
        
        return false;
    }
}
