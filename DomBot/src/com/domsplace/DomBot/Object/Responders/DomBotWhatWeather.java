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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Dominic Masters
 */
public class DomBotWhatWeather extends Responder {
    @Override
    public boolean response(DomBotResponse response, DomBotResponseThread thread) {
        if(!response.getBasicResponse().toLowerCase().startsWith("dombot")) return true;
        if(!response.hasArgStartsWith("what")) return true;
        if(!response.hasArgStartsWith("weather")) return true;
        if(!response.hasArgStartsWith("in")) return true;
        
        String[] args = response.getArgs();
        int index = -1;
        for(int i = 0; i < args.length; i++) {
            if(!args[i].equalsIgnoreCase("in")) continue;
            index = i;
            break;
        }
        if(index < 0) return true;
        if(index >= args.length - 1) return true;
        
        String loc = "";
        for(int i = index + 1; i < args.length; i++) {
            loc += args[i] + " ";
        }
        loc = Base.trim(loc, loc.length() - 1);
        
        loc = loc.replaceAll(", ", ",");
        try {
            loc = "http://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(loc, "ISO-8859-1");
            URL url = new URL(loc);
            
            URLConnection urlCon = url.openConnection();
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String r = reader.readLine();

            JSONObject obj = (JSONObject) JSONValue.parse(r);
            //JSONObject obj = (JSONObject) array.get(0);
            
            String country = ((JSONObject) obj.get("sys")).get("country").toString();
            String weather = ((JSONObject)((JSONArray) obj.get("weather")).get(0)).get("main").toString();
            String temp = ((JSONObject) obj.get("main")).get("temp").toString();
            double temperature = Base.getDouble(temp) - 273.15d;
            String location = obj.get("name").toString();
            
            talk(new String[] {
                "The weather in " + location + ", " + country + " is " + weather + ", " + Base.twoDecimalPlaces(temperature) + "°C"
            });
            return false;
        } catch (Exception ex) {
            talk(new String[] {
                "I don't know where that is."
            });
            return false;
        }
    }
}
