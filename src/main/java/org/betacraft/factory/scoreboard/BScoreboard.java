package org.betacraft.factory.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class BScoreboard {


    private final Player player;
    private Objective objective;
    private Scoreboard board;

    public BScoreboard(Player player) {

        this.player = player;

        this.board = Bukkit.getScoreboardManager().getNewScoreboard();

        this.objective = board.registerNewObjective("", player.getName());

        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(board);

    }

    public void setScoreBoard(Player player) {

        Score onlineName = this.objective.getScore(ChatColor.GRAY + "� Online");
        onlineName.setScore(15);
        Team onlineCounter = board.registerNewTeam("onlineCounter");

        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);

        if (Bukkit.getOnlinePlayers().size() == 0) {
            onlineCounter.setPrefix(ChatColor.DARK_RED + "0" + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        } else {
            onlineCounter.setPrefix("" + ChatColor.DARK_RED + Bukkit.getOnlinePlayers().size() + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        }

        this.objective.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);

        Score money = this.objective.getScore(ChatColor.GRAY + "� Money");
        money.setScore(13);

        Team moneyCounter = board.registerNewTeam("moneyCounter");
        moneyCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        moneyCounter.setPrefix(ChatColor.GREEN + "$" + 100);
        this.objective.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(12);


        player.setScoreboard(board);

    }

}
