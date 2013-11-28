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

import com.domsplace.DomBot.Object.DomBotResponse;
import com.domsplace.DomBot.Object.Responder;
import com.domsplace.DomBot.Threads.DomBotResponseThread;

/**
 *
 * @author Dominic Masters
 */
public class DomBotWhat extends Responder {
    public DomBotWhat() {
        super();
        this.setPermission("learn");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(response.getCleanArgs().length < 4) return true;
        if(!response.hasArgStartsWith("what")) return true;
        String search = response.getCleanArgs()[response.getCleanArgs().length - 1];
        
        if(!doIKnow(search)) {
            talk(this.noidea());
            return false;
        }
        
        talk(new String[] {
            "I think it's " + whatIs(search),
            "I'm not sure.. but is it " + whatIs(search) + "?"
        });
        return false;
    }
}
