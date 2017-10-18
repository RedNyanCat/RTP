package me.RedNyanCat.rpt;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.RedNyanCat.Api.Api;

public class rpt extends JavaPlugin{

	public void onEnable(){

		saveDefaultConfig();

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if((cmd.getName().equalsIgnoreCase("rtp")) || (cmd.getName().equalsIgnoreCase("wild")) || (cmd.getName().equalsIgnoreCase("random"))){

			if(args.length == 0){

				if(sender.hasPermission("rtp.tp")){

					if(sender instanceof Player){

						Player p = (Player)sender;

						double randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
						double randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
						double randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

						Location loc = new Location(p.getWorld(), p.getLocation().getX() + randomX, p.getLocation().getY() + randomY, p.getLocation().getZ() + randomZ);

						boolean isOnLand = false;
						
						isTping.setTping(true);

						while (isOnLand == false){

							loc.setY(randomY);

							isTping.setTping(true);

							if(loc.getY() < 20){

								randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
								randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
								randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

							}

							if(isTping.getTping()){

								if((loc.getBlock().getLocation().add(0, 1.2, 0).getBlock().getType() == Material.AIR) && (loc.getBlock().getType() == Material.AIR) && (loc.getBlock().getLocation().add(0, -1.2, 0).getBlock().getType() != Material.AIR)){

									isOnLand = true;

									isTping.setTping(false);

									//new runnable(this, 3, p, loc);//countdown(p, loc);

									countdown(p,loc);

								}

							} else {

								return true;

							}

							randomY = randomY -1;

						}

						Api.sendMsg(p.getUniqueId(), Api.convertColorCodes(tpMsg("", "")));

					} else {

						sender.sendMessage(Api.convertColorCodes(unknownCmd("", "")));

					}

				} else {

					sender.sendMessage(Api.convertColorCodes(noPerm("", "")));

				}

			} else {

				if(sender.hasPermission("rtp.tp.other")){

					if(sender instanceof Player){

						Player target = Bukkit.getPlayer(args[0]);

						Player p = (Player)sender;

						double randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
						double randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
						double randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

						Location loc = new Location(target.getWorld(), target.getLocation().getX() + randomX, target.getLocation().getY() + randomY, target.getLocation().getZ() + randomZ);

						boolean isOnLand = false;
						
						isTping.setTping(true);

						while (isOnLand == false){

							loc.setY(randomY);

							isTping.setTping(true);

							if(loc.getY() < 20){

								randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
								randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
								randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

							}

							if(isTping.getTping()){

								if((loc.getBlock().getLocation().add(0, 1.2, 0).getBlock().getType() == Material.AIR) && (loc.getBlock().getType() == Material.AIR) && (loc.getBlock().getLocation().add(0, -1.2, 0).getBlock().getType() != Material.AIR)){

									isOnLand = true;

									isTping.setTping(false);

									//new runnable(this, 3, target, loc);//countdown(target, loc);

									countdown(target,loc);

								}

							} else {

								return true;

							}

							randomY = randomY -1;

						}

						Api.sendMsg(p.getUniqueId(), Api.convertColorCodes(senderMsg(sender.getName().toString(), target.getName().toString())));

						for(Player pl : Bukkit.getServer().getOnlinePlayers()){

							if(pl.hasPermission("rpt.notify")){

								if(pl != p){

									Api.sendMsg(pl.getUniqueId(), Api.convertColorCodes(notifyWild(sender.getName().toString(), target.getName().toString())));
									
								}
								
							}

						}

						if(args.length > 1){

							if((args[1].equalsIgnoreCase("-s"))){

								return true;

							} else {

								Api.sendMsg(target.getUniqueId(), Api.convertColorCodes(forceWild(sender.getName().toString(), target.getName().toString())));

							}

						} else {

							Api.sendMsg(target.getUniqueId(), Api.convertColorCodes(forceWild(sender.getName().toString(), target.getName().toString())));

						}

					} else {

						Player target = Bukkit.getPlayer(args[0]);

						double randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
						double randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
						double randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

						Location loc = new Location(target.getWorld(), target.getLocation().getX() + randomX, target.getLocation().getY() + randomY, target.getLocation().getZ() + randomZ);

						boolean isOnLand = false;
						
						isTping.setTping(true);

						while (isOnLand == false){

							loc.setY(randomY);

							if(loc.getY() < 20){

								randomX = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.x"));
								randomY = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.y"));
								randomZ = ThreadLocalRandom.current().nextDouble(getConfig().getDouble("rtp.z"));

							}

							if(isTping.getTping()){

								if((loc.getBlock().getLocation().add(0, 1.2, 0).getBlock().getType() == Material.AIR) && (loc.getBlock().getType() == Material.AIR) && (loc.getBlock().getLocation().add(0, -1.2, 0).getBlock().getType() != Material.AIR)){

									isOnLand = true;

									isTping.setTping(false);

									//new runnable(this, 3, target, loc);//countdown(target, loc);

									countdown(target,loc);

								}

							} else {

								return true;

							}

							randomY = randomY -1;

						}

						sender.sendMessage(Api.convertColorCodes(senderMsg(sender.getName().toString(), target.getName().toString())));

						for(Player pl : Bukkit.getServer().getOnlinePlayers()){

							if(pl.hasPermission("rpt.notify")){

								Api.sendMsg(pl.getUniqueId(), Api.convertColorCodes(notifyWild(sender.getName().toString(), target.getName().toString())));

							}

						}

						if(args.length > 1){

							if((args[1].equalsIgnoreCase("-s"))){

								return true;

							} else {

								Api.sendMsg(target.getUniqueId(), Api.convertColorCodes(forceWild(sender.getName().toString(), target.getName().toString())));

							}

						} else {

							Api.sendMsg(target.getUniqueId(), Api.convertColorCodes(forceWild(sender.getName().toString(), target.getName().toString())));

						}

					}

				} else {

					sender.sendMessage(Api.convertColorCodes(unknownCmd("", "")));

				}

			}

			return true;

		}

		return true;

	}

	public ArrayList<String> playersOnCoolDown = new ArrayList<String>();
	
	public void countdown(Player p, Location loc){

		if(getConfig().getBoolean("countdown") == true){

			playersOnCoolDown.add(p.getName().toString());

			isTping.j = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

				int i = getConfig().getInt("timer");

				public void run(){

					if(playersOnCoolDown.contains(p.getName().toString())){

						if(i == 0){

							isTping.setTping(true);

							Api.teleportPlayer(loc, p.getUniqueId());

							playersOnCoolDown.remove(p.getName().toString());

							Bukkit.getScheduler().cancelTask(isTping.j);

						} else {

							Api.sendMsg(p.getUniqueId(), Api.convertColorCodes("&3&lYou will be teleported in &2" + i + " &3&lseconds."));

							i--;

						}

					}

				}

			}, 0, 20L);

		} else {
			
			isTping.setTping(true);

			Api.teleportPlayer(loc, p.getUniqueId());
			
		}

	}
	
	public String tpMsg(String sender, String target){

		String tpMsg = getConfig().getString("msgs.tpMsg");

		tpMsg = tpMsg.replace("<sender>", sender);
		tpMsg = tpMsg.replace("<target>", target);

		return tpMsg;

	}

	public String senderMsg(String sender, String target){

		String tpMsg = getConfig().getString("msgs.senderMsg");

		tpMsg = tpMsg.replace("%sender%", sender);
		tpMsg = tpMsg.replace("%target%", target);

		return tpMsg;

	}

	public String notifyWild(String sender, String target){

		String tpMsg = getConfig().getString("msgs.notifyWild");

		tpMsg = tpMsg.replace("%sender%", sender);
		tpMsg = tpMsg.replace("%target%", target);

		return tpMsg;

	}

	public String forceWild(String sender, String target){

		String tpMsg = getConfig().getString("msgs.forceWild");

		tpMsg = tpMsg.replace("%sender%", sender);
		tpMsg = tpMsg.replace("%target%", target);

		return tpMsg;

	}
	
	public String unknownCmd(String sender, String target){

		String tpMsg = getConfig().getString("msgs.unknownCmd");

		tpMsg = tpMsg.replace("%sender%", sender);
		tpMsg = tpMsg.replace("%target%", target);

		return tpMsg;

	}
	
	public String noPerm(String sender, String target){

		String tpMsg = getConfig().getString("msgs.noPerm");

		tpMsg = tpMsg.replace("%sender%", sender);
		tpMsg = tpMsg.replace("%target%", target);

		return tpMsg;

	}

}
