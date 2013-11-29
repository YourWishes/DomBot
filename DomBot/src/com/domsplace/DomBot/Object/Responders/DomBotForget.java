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
public class DomBotForget extends Responder {
    public DomBotForget() {
        super();
        this.setPermission("forget");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.getBasicResponse().toLowerCase().startsWith("dombot")) return true;
        if(response.getCleanArgs().length < 3) return true;
        if(!response.getCleanArgs()[1].equalsIgnoreCase("forget")) return true;
        
        String learnt = response.getArgs()[2];
        if(!doIKnowExactly(learnt)) {
            talk(noidea());
            return false;
        }
        DataManager.BRAIN_MANAGER.setCFG(DataManager.removeFromYml(learnt, DataManager.BRAIN_MANAGER.getCFG()));
        forget(learnt);
        DataManager.saveAll();
        talk(new String[] {
            "What'd I forget? I don't remember.."
        });
        return false;
    }
}
