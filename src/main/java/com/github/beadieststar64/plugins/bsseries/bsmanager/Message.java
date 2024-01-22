package com.github.beadieststar64.plugins.bsseries.bsmanager;

import com.github.beadieststar64.plugins.bsseries.bscore.AbstractYamlReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    private final Pattern pattern = Pattern.compile("<[a-zA-Z0-9]*>");
    private final BSManager plugin;

    public Message(BSManager plugin) {
        this.plugin = plugin;
    }

    public String convertMessage(String ymlPath, Object[] replaceVar) {
        AbstractYamlReader reader = new AbstractYamlReader(plugin, "", "message.yml");
        String str = reader.getString(ymlPath);
        Matcher matcher = pattern.matcher(str);
        int i = 0;
        while (matcher.find()) {
            String var = str.substring(matcher.start(), matcher.end());
            str = str.replaceFirst(var, replaceVar[i].toString());
            matcher = pattern.matcher(str);
            i++;
        }
        return str;
    }
}
