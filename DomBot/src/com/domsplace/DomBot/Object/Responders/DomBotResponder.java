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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Masters
 */
public class DomBotResponder extends Responder {
    private List<String> waiting = new ArrayList<String>();
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(waiting.contains(response.getPlayer().getName().toLowerCase())) {
            DomBotResponse newresponse = new DomBotResponse(response.getPlayer(), "DomBot " + response.getCommand());
            DomBotResponseThread newthread = new DomBotResponseThread(newresponse);
            waiting.remove(newresponse.getPlayer().getName().toLowerCase());
            return false;
        }
        
        if(deSymbolise(response.getCommand()).equalsIgnoreCase("DomBot")) {
            talk(new String[] {
                "Yes?",
                "How can I help " + Base.getDisplayName(response.getPlayer()) + Base.ChatDefault + "?",
                "What's up?",
                "Yep, I'm here, go ahead.",
                "I was called?",
                "Who's asking?",
                "Need somethin'?",
                "How can I assist today?"
            });
            waiting.add(response.getPlayer().getName().toLowerCase());
            return false;
        }
        return true;
    }
}
