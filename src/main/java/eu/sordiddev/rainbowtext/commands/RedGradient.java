package eu.sordiddev.rainbowtext.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.sound.sampled.Clip;
import java.awt.datatransfer.Clipboard;
import java.awt.print.Paper;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RedGradient implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] text) {

        int currentposition = 1;
        if (text.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "");
            sender.sendMessage(ChatColor.GREEN + "RainbowText Help");
            sender.sendMessage(ChatColor.GREEN + "");
            sender.sendMessage(ChatColor.GREEN + "/rtr <text> - adds a gradient to your text starting with red");
            sender.sendMessage(ChatColor.GREEN + "/rtg <text> - adds a gradient to your text starting with green");
            sender.sendMessage(ChatColor.GREEN + "/rtb <text> - adds a gradient to your text starting with blue");
            sender.sendMessage(ChatColor.GREEN + "/rty <text> - adds a gradient to your text starting with yellow");
            sender.sendMessage(ChatColor.GREEN + "");
            sender.sendMessage(ChatColor.GREEN + "In case of any issues visit github.com/sord1d/RainbowText");

            return true;

        }


        List<String> colors = Arrays.asList("#ff0000", "#ff1200", "#ff2400", "#ff3700", "#ff4900", "#ff5b00", "#ff6d00", "#ff8000", "#ff9200", "#ffa400", "#ffb600", "#ffc800", "#ffdb00", "#ffed00", "#ffff00", "#f5ff00", "#ecff00", "#e2ff00", "#d8ff00", "#ceff00", "#c5ff00", "#bbff00", "#b1ff00", "#a8ff00", "#9eff00", "#94ff00", "#8aff00", "#81ff00", "#77ff00", "#6fff12", "#66ff24", "#5eff37", "#55ff49", "#4dff5b", "#44ff6d", "#3cff80", "#33ff92", "#2bffa4", "#22ffb6", "#1affc8", "#11ffdb", "#09ffed", "#00ffff", "#00f5ff", "#00ecff", "#00e2ff", "#00d8ff", "#00ceff", "#00c5ff", "#00bbff", "#00b1ff", "#00a8ff", "#009eff", "#0094ff", "#008aff", "#0081ff", "#0077ff", "#126fed", "#2466db", "#375ec8", "#4955b6", "#5b4da4", "#6d4492", "#803c80", "#92336d", "#a42b5b", "#b62249", "#c81a37", "#db1124", "#ed0912", "#ff0000");
        String inputtext = String.join(" ",text);
        String outputtext = "";
        //todo remove code below
        sender.sendMessage(inputtext);
        int color = 1;
        int textlength = inputtext.length();
        currentposition = 0;
        char currentletter;
        //todo irgendwie output hinbekommen
        if (textlength != 0) {
            while (textlength > currentposition) {
                String hex = colors.get(color);
                currentletter = inputtext.charAt(currentposition);
                if (Character.isSpaceChar(currentletter)){
                    outputtext = new String (outputtext + " ");
                }else{
                    outputtext = new String(outputtext + "&" + hex + currentletter);
                }
                currentposition = currentposition +1;

                if (color < 71) {
                    color = color + 1;
                } else {
                    color = 1;
                }

            }

        }


        sender.sendMessage(outputtext);
        ((Player) sender).chat(outputtext);



        return true;
    }




}