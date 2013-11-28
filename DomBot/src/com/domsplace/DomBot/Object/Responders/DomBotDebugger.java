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

/**
 *
 * @author Dominic Masters
 */
public class DomBotDebugger extends Responder {
    public DomBotDebugger() {
        super();
        this.setPermission("debugger");
    }
    
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        try {if(!response.getArgs()[2].toLowerCase().startsWith("debug")) return true;} catch(Exception e) {return true;}
        if(!response.getCleanArgs()[0].equalsIgnoreCase("DomBot")) return true;
        boolean t = response.getArgs()[1].equalsIgnoreCase("enable");
        if(!t && !response.getArgs()[1].equalsIgnoreCase("disable")) {
            talk(noidea());
            return false;
        }
        if(t == Base.DebugMode) {
            String en = (t ? "enabled" : "disabled");
            talk(new String[] {
                "It's already " + en,
                "Already done",
                "Sorry, too slow",
                "No Way"
            });
            return false;
        }
        Base.DebugMode = t;
        talk(confirm());
        Base.debug("Debug Mode Enabled!");
        return false;
    }
}
