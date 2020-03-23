/*
 * MIT License
 *
 * Copyright (c) 2020 AshDev (Ashley Tonkin)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tv.ashdev;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tv.ashdev.commands.SetLocationCommand;

/**
 * The type Blockwarp.
 */
public final class Blockwarp extends JavaPlugin implements Listener {

  /**
   * On enable.
   */
  @Override
  public void onEnable() {
    // Plugin startup logic
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    reloadConfig();
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    Objects.requireNonNull(getCommand("setwarp")).setExecutor(new SetLocationCommand(this));
  }

  /**
   * On player interaction.
   *
   * @param event the event
   */
  @EventHandler
  public void onPlayerInteraction(PlayerToggleSneakEvent event) {
    Player player = event.getPlayer();
    World world = player.getWorld();
    double x = getConfig().getDouble("teleport.x");
    double y = getConfig().getDouble("teleport.y");
    double z = getConfig().getDouble("teleport.z");
    float yaw = getConfig().getInt("teleport.yaw");
    float pitch = getConfig().getInt("teleport.pitch");
    Location location = new Location(world, x, y, z, yaw, pitch);
    player.teleport(location);

  }

}
