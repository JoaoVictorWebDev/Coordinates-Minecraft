package org.dqc.coordinates;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Coordinates extends JavaPlugin implements Listener, CommandExecutor {
    private boolean showCoordinates;

    @Override
    public void onEnable() {
        getLogger().info("Plugin de Coordenadas ativado!");
        registerEvents();
        getCommand("coordenadas").setExecutor(this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin de Coordenadas desativado!");
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (showCoordinates) {
            Player player = event.getPlayer();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            player.sendMessage("Suas coordenadas: X=" + x + ", Y=" + y + ", Z=" + z);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("coordenadas")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();

                player.sendMessage("Suas coordenadas: X=" + x + ", Y=" + y + ", Z=" + z);
            } else {
                sender.sendMessage("Este comando só pode ser usado por jogadores!");
            }
            return true;
        }
        return false;
    }
}