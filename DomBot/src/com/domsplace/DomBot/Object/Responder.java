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

package com.domsplace.DomBot.Object;

import com.domsplace.DomBot.Bases.Base;
import com.domsplace.DomBot.Object.Responders.*;
import com.domsplace.DomBot.Threads.DomBotResponseThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.OfflinePlayer;

/**
 *
 * @author Dominic Masters
 */
public class Responder {
    public static final List<Responder> RESPONDERS = new ArrayList<Responder>();
    public static final Map<String, String> BRAIN = new HashMap<String, String>();
    
    public static final DomBotResponder DOM_BOT_RESPONDER = new DomBotResponder();
    public static final DomBotDebugger DOM_BOT_DEBUGGER = new DomBotDebugger();
    public static final DomBotBasicInfo DOM_BOT_BASIC_INFO = new DomBotBasicInfo();
    public static final DomBotLove DOM_BOT_LOVE = new DomBotLove();
    public static final DomBotComplexInfo DOM_BOT_COMPLEX_INFO = new DomBotComplexInfo();
    public static final DomBotReloader DOM_BOT_RELOADER = new DomBotReloader();
    public static final DomBotOp DOM_BOT_OP = new DomBotOp();
    public static final DomBotDeOp DOM_BOT_DEOP = new DomBotDeOp();
    public static final DomBotKill DOM_BOT_KILL = new DomBotKill();
    public static final DomBotAge DOM_BOT_AGE = new DomBotAge();
    public static final DomBotWhatTime DOM_BOT_WHAT_TIME = new DomBotWhatTime();
    public static final DomBotWhatWeather DOM_BOT_WHAT_WEATHER = new DomBotWhatWeather();
    public static final DomBotLearn DOM_BOT_LEARN = new DomBotLearn();
    public static final DomBotWhat DOM_BOT_WHAT = new DomBotWhat();
    
    public static boolean doIKnow(String whatever) {
        return whatIs(whatever) != null;
    }
    
    public static String whatIs(String whatever) {
        for(String s : BRAIN.keySet()) {
            if(s.equalsIgnoreCase(whatever)) return BRAIN.get(s);
        }
        for(String s : BRAIN.keySet()) {
            if(s.toLowerCase().startsWith(whatever.toLowerCase())) return BRAIN.get(s);
        }
        for(String s : BRAIN.keySet()) {
            if(s.toLowerCase().contains(whatever.toLowerCase())) return BRAIN.get(s);
        }
        return null;
    }
    
    public static final String deSymbolise(String x) {
        return x.replaceAll("[^\\w\\s]", "");
    }
    
    //Instance
    private String perm = null;
    
    public Responder() {
        this.register();
    }
    
    public final void register() {RESPONDERS.add(this);}
    public final void deregister() {RESPONDERS.add(this);}
    
    public String getPermission() {return this.perm;}
    public void setPermission(String p) {this.perm = p;}
    
    public boolean hasPermission(OfflinePlayer p) {
        return Base.hasPermission(p, "DomBot." + perm);
    }
    
    public final void talk(String[] responses) {
        Random r = new Random();
        Base.broadcast("§6DomBot§c: " + Base.ChatDefault + responses[r.nextInt(responses.length)]);
    }
    
    public final String[] confirm() {
        return new String[] {
            "Yes",
            "Sure",
            "Ok",
            "Can do",
            "mhm"
        };
    }
    
    public final String[] noidea() {
        return new String[] {
            "I have no idea what that is",
            "eh?",
            "I don't understand..",
            "Sorry.. not fluent in smartie pants language"
        };
    }
    
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        return true;
    }
}
