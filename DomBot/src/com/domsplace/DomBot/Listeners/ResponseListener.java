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

package com.domsplace.DomBot.Listeners;

import com.domsplace.DomBot.Bases.Base;
import com.domsplace.DomBot.Bases.DomsListener;
import com.domsplace.DomBot.Object.DomBotResponse;
import com.domsplace.DomBot.Threads.DomBotResponseThread;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Dominic Masters
 */
public class ResponseListener extends DomsListener {
    @EventHandler(ignoreCancelled=true)
    public void handleChat(AsyncPlayerChatEvent e) {
        if(e.isCancelled()) return;
        if(e.getMessage().replaceAll(" ", "").equals("")) return;
        if(!Base.hasPermission(e.getPlayer(), "DomBot.dombot")) return;
        String str = e.getMessage();
        
        DomBotResponse response = new DomBotResponse(e.getPlayer(), str);
        DomBotResponseThread thread = new DomBotResponseThread(response);
    }
}
