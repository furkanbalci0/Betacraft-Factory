package org.betacraft.factory.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BScoreboard {


    //VARIABLES
    private final Player player;
    private final Objective objective;
    private final Scoreboard board;
    private final Map<Integer, String> lines;


    /**
     *
     * @param player Whose scoreboard will be given.
     */
    public BScoreboard(Player player) {

        this.lines = new HashMap<>();

        this.player = player;

        this.board = Bukkit.getScoreboardManager().getNewScoreboard();

        this.objective = board.registerNewObjective("124124", "dummy");

        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(board);

    }

    /**
     * Removing player's scoreboard.
     */
    public void removeScoreboard(){
        this.player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }

    /**
     *
     * @param line Line should be just from 0 to 15.
     * @param text Each line must be different from each other. If you are going to write the same line, you should add a different color code to the beginning.
     */
    public void setLine(int line, String text) {

        if(line < 0 || line > 15){
            throw new NumberFormatException("Line should be just from 0 to 15.");
        }

        String oldLine = lines.get(line);

        text = ChatColor.translateAlternateColorCodes('&', text.replace('§', '&'));

        lines.put(line, text);

        if (oldLine != null) {

            board.resetScores(oldLine);

        }

        Team t = board.getTeam(line + "");

        if (t == null) {
            t = board.registerNewTeam(line + "");

        }

        t.setPrefix(text);

        Score score = this.objective.getScore(text);

        score.setScore(line);

        Random random = new Random();

        int colorLength = ChatColor.values().length;

        t.addEntry(Arrays.stream(ChatColor.values()).toArray()[random.nextInt(colorLength)] + "" + Arrays.stream(ChatColor.values()).toArray()[random.nextInt(colorLength)]);

        this.player.setScoreboard(this.board);

    }

    /**
     *
     * @param line The line you want to delete from the scoreboard.
     */
    public void removeLine(int line){

        String oldLine = lines.get(line);

        if(oldLine == null) return;

        lines.remove(line);

        this.board.resetScores(oldLine);

    }



}
