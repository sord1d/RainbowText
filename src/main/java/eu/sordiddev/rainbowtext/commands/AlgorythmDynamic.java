package eu.sordiddev.rainbowtext.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AlgorythmDynamic implements CommandExecutor {

    //get the color variable. 1 -> red; 2-> green; 3 -> blue; 4 -> yellow; 5 -> rainbow
    List<String> colors = new ArrayList<>();
    int color;
    Color loopcolor;
    public AlgorythmDynamic(int i) {
        color = i;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] text) {

        //Check whether command sender has permissions to use the command, cancel event when permissions are not set
        if(!sender.hasPermission("rainbowtext.use")){
            sender.sendMessage(String.valueOf(
                    ChatColor.RED) + ChatColor.BOLD + "Rainbow" + ChatColor.GOLD + ChatColor.BOLD + "Text: " + ChatColor.YELLOW + "You are not allowed to execute this command. "+
                    ChatColor.DARK_GRAY + "[You are lacking the permission node rainboxtext.use]");
            return true;
        }

        //getting the text length and the input text
        String inputtext = String.join(" ",text);
        String outputtext = "";
        int textlength = inputtext.length();

    /*
    color codes
    1 - RED         ff0000  255 0 0
    2 - GREEN       6fff12  111 255 18
    3 - BLUE        00f5ff  0 254 255
    4 - YELLOW      ffff00  255 255 0
     */

        //Define start values for the colors depending on the command -
        float hue1 = Color.RGBtoHSB(10, 1, 10, null)[0];
        float hue2 = Color.RGBtoHSB(100, 100, 110, null)[0];

        if ( color == 1){
            hue1 = Color.RGBtoHSB(255, 0, 0, null)[0];
            hue2 = Color.RGBtoHSB(255, 255, 0, null)[0];
        } else if ( color == 2){
            hue1 = Color.RGBtoHSB(111, 255, 18, null)[0];
            hue2 = Color.RGBtoHSB(0, 255, 255, null)[0];
        } else if ( color == 3){
            hue1 = Color.RGBtoHSB(0, 254, 255, null)[0];
            hue2 = Color.RGBtoHSB(128, 0, 128, null)[0];
        } else if ( color == 4){
            hue1 = Color.RGBtoHSB(255, 255, 0, null)[0];
            hue2 = Color.RGBtoHSB(111, 255, 18, null)[0];
        } else if ( color == 5 ){
            hue1 = Color.RGBtoHSB(255, 0, 0, null)[0];
            hue2 = Color.RGBtoHSB(255, 0, 4, null)[0];
        }


        //defining text length n and starting a loop for n steps

        int n = textlength;
        for (int i = 0; i < n; i++) {

            //calculating a gradient due to the power of maths - yay maths (I googled the maths formula)
           loopcolor =  Color.getHSBColor(hue1 + (i * (hue2 - hue1) / n), 1, 1);


           //getting rgb values and converting it to hex, adding the color to the color list
            int red = loopcolor.getRed();
            int green = loopcolor.getGreen();
            int blue = loopcolor.getBlue();
            String hex = String.format("#%02x%02x%02x", red, green, blue);
            colors.add(hex);

        }

        if ( textlength != 0 ) {
            int currentposition = 0;
            char currentletter;
            while (textlength > currentposition) {
                String hex = colors.get(currentposition);
                currentletter = inputtext.charAt(currentposition);
                if (Character.isSpaceChar(currentletter)){
                    outputtext = new String (outputtext + " ");
                }else{
                    outputtext = new String(outputtext + "&" + hex + currentletter);
                }
                currentposition = currentposition +1;


            }

        }
        if ( text.length == 0 ) {
            sender.sendMessage(
                    " ",ChatColor.YELLOW + "----------------------------------------------------",
                    ChatColor.DARK_RED + "                        "+ ChatColor.RED +  "sord1d/" + ChatColor.GOLD + ChatColor.BOLD + "Rainbow Text",
                    ChatColor.YELLOW + "----------------------------------------------------",
                    ChatColor.RED + "/rt  <text>" + ChatColor.GOLD+ "      add a rainbow gradient to your text",
                    ChatColor.RED + "/rtr <text>" + ChatColor.GOLD+ "     add a red gradient to your text",
                    ChatColor.RED + "/rtg <text>" + ChatColor.GOLD+ "     add a green gradient to your text",
                    ChatColor.RED + "/rtb <text>" + ChatColor.GOLD+ "     add a blue gradient to your text",
                    ChatColor.RED + "/rtg <text>" + ChatColor.GOLD+ "     add a green gradient to your text",
                    ChatColor.YELLOW + "----------------------------------------------------",
                    ChatColor.GOLD + "For adding a static gradient use /rtrs, /rtgs, /rtbs or /rtys");
            return true;

        }

        ((Player) sender).chat(outputtext);
        colors = new ArrayList<>();


        return false;
    }





}
