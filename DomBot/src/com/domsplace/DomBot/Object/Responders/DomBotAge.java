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
import java.util.Date;

/**
 *
 * @author Dominic Masters
 */
public class DomBotAge extends Responder {
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.getBasicResponse().toLowerCase().startsWith("dombot")) return true;
        if(!response.hasArgStartsWith("old")) return true;
        if(!response.hasArgStartsWith("you")) return true;
        talk(new String[] {
            "I am " + Base.getTimeDifference(new Date(1385600400000L), new Date()) + " old."
        });
        return false;
    }
}
