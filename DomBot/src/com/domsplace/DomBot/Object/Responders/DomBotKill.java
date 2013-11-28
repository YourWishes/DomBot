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

package com.domsplace.DomBot.Object.Responders;

import com.domsplace.DomBot.Bases.Base;
import com.domsplace.DomBot.Object.DomBotResponse;
import com.domsplace.DomBot.Object.Responder;
import com.domsplace.DomBot.Threads.DomBotResponseThread;
import org.bukkit.entity.Player;

/**
 *
 * @author Dominic Masters
 */
public class DomBotKill extends Responder {
    public DomBotKill() {
        super();
        this.setPermission("kill");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.hasArgStartsWith("can") || !response.hasArgStartsWith("kill")) return true;
        if(!response.getPlayer().isOnline()) return true;
        
        Player guess = null;
        for(String s : response.getCleanArgs()) {
            guess = Base.getPlayer(response.getPlayer().getPlayer(), s);
            if(guess != null) break;
        }
        if(guess == null) return true;
        
        guess.setHealth(0.0d);
        talk(new String[] {
            "Gladly",
            "With pleasure",
            "mwahaha",
            ":}",
            "OH that was fun.",
            "I think I had a kill-gasm"
        });
        return false;
    }
}
