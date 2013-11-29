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
public class DomBotOp extends Responder {
    public DomBotOp() {
        super();
        this.setPermission("op");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.getBasicResponse().toLowerCase().startsWith("dombot")) return true;
        if(!response.hasArgStartsWith("can") || !response.hasArgStartsWith("op")) return true;
        if(!response.getPlayer().isOnline()) return true;
        
        Player guess = null;
        for(String s : response.getCleanArgs()) {
            guess = Base.getPlayer(response.getPlayer().getPlayer(), s);
            if(guess != null) break;
        }
        if(guess == null) return true;
        
        if(guess.isOp()) {
            talk(new String[] {
                "They're already an operator",
                "Already are opped",
                "They are an op already"
            });
            return false;
        }
        
        guess.setOp(true);
        talk(new String[] {
            "Opped " + guess.getDisplayName(),
            guess.getDisplayName() + Base.ChatDefault + " is now an op.",
            "Yep, opped them.",
            "All opped out.",
            "Welcome to the rank of op you poor sad sack"
        });
        return false;
    }
}
