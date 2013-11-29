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

import com.domsplace.DomBot.Bases.DataManager;
import com.domsplace.DomBot.Object.DomBotResponse;
import com.domsplace.DomBot.Object.Responder;
import com.domsplace.DomBot.Threads.DomBotResponseThread;

/**
 *
 * @author Dominic Masters
 */
public class DomBotLearn extends Responder {
    public DomBotLearn() {
        super();
        this.setPermission("learn");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.getBasicResponse().toLowerCase().startsWith("dombot")) return true;
        if(response.getCleanArgs().length < 4) return true;
        if(!response.getCleanArgs()[1].equalsIgnoreCase("learn")) return true;
        
        String learnt = response.getArgs()[2];
        if(doIKnowExactly(learnt)) {
            talk(new String[] {
                "I already knew that..",
                "I thought " + learnt + " meant " + whatIs(learnt)
            });
            return false;
        }
        
        String learntData = "";
        
        for(int i = 4; i < response.getArgs().length; i++) {
            learntData += response.getArgs()[i];
            if(i < (response.getArgs().length - 1)) {
                learntData += " ";
            }
        }
        
        BRAIN.put(learnt, learntData);
        talk(new String[] {
            "I never knew that..",
            "Wow really?"
        });
        DataManager.saveAll();
        return false;
    }
}
