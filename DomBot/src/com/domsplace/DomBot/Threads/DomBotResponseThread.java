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

package com.domsplace.DomBot.Threads;

import com.domsplace.DomBot.Bases.Base;
import com.domsplace.DomBot.Bases.DomsThread;
import com.domsplace.DomBot.Object.DomBotResponse;
import com.domsplace.DomBot.Object.Responder;

/**
 *
 * @author Dominic Masters
 */
public class DomBotResponseThread extends DomsThread {
    public static long NEXT_ID = 0;
    
    private final long id;
    private final DomBotResponse response;
    
    public DomBotResponseThread(final DomBotResponse response) {
        super(Base.DebugMode ? 0 : 2, Long.MAX_VALUE, true);
        this.id = NEXT_ID += 1;
        this.response = response;
    }
    
    @Override
    public void run() {
        this.stopThread();
        for(Responder r : Responder.RESPONDERS) {
            try {
                if(!r.hasPermission(this.response.getPlayer())) continue;
                if(r.response(response, this)) continue;
            } catch(Exception e) {
                if(!this.response.getCommand().toLowerCase().startsWith("dombot")) return;
                Responder failsafe = new Responder();
                failsafe.talk(new String[] {
                    "OH! YOU BROKE ME"
                });
                failsafe.deregister();
            }
            return;
        }
        if(!this.response.getCommand().toLowerCase().startsWith("dombot")) return;
        Responder failsafe = new Responder();
        failsafe.talk(failsafe.noidea());
        failsafe.deregister();
    }
}
