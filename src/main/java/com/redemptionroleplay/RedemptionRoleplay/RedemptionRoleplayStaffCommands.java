package com.redemptionroleplay.RedemptionRoleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RedemptionRoleplayStaffCommands implements CommandExecutor{

	@SuppressWarnings("unused")
	private final RedemptionRoleplay plugin;

	public RedemptionRoleplayStaffCommands(RedemptionRoleplay plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		//STAFF COMMANDS

		if (cmd.getName().equalsIgnoreCase("staff")){
			if(sender instanceof Player){
				Player player = (Player) sender;

				//STAFF GROUP COMMANDS

				if(args[0].equalsIgnoreCase("mod") || args[0].equalsIgnoreCase("moderator")){
					if(player.hasPermission("staff.moderator") || player.hasPermission("staff.*")){
					// TODO: Move this to the "ModeratorPlayer" constructor {
						if(RedemptionRoleplay.policeList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.policeList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty police officer list!");
							Bukkit.broadcastMessage("§4Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
						else if(RedemptionRoleplay.coList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.coList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty Correctional-Officer list and your inventory has been wiped!");
							player.sendMessage("[RedemptionRoleplay] You are now an §7OFF DUTY Correctional-Officer (Citizen)!");
							Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
					// } END
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Moderator");
						player.sendMessage("[RedemptionRoleplay] You have been added to the §3Moderator §fgroup!");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the moderation team.");
					}
				}else if(args[0].equalsIgnoreCase("admin") || args[0].equalsIgnoreCase("administrator")){
					if(player.hasPermission("staff.administrator") || player.hasPermission("staff.*")){
					// TODO: Move this to the "AdminPlayer constructor {
						if(RedemptionRoleplay.policeList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.policeList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty police officer list!");
							Bukkit.broadcastMessage("§4Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
						else if(RedemptionRoleplay.coList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.coList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty Correctional-Officer list and your inventory has been wiped!");
							player.sendMessage("[RedemptionRoleplay] You are now an §7OFF DUTY Correctional-Officer (Citizen)!");
							Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
					// } END
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Administrator");
						player.sendMessage("[RedemptionRoleplay] You have been added to the §9Administrator §fgroup!");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the administration team.");
					}
				}else if(args[0].equalsIgnoreCase("owner")){
					if(player.hasPermission("staff.*")){
						if(RedemptionRoleplay.policeList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.policeList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty police officer list!");
							Bukkit.broadcastMessage("§4Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
						else if(RedemptionRoleplay.coList.contains(player)){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
							player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
							player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
							player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
							player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
							RedemptionRoleplay.coList.remove(player);
							player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty Correctional-Officer list and your inventory has been wiped!");
							player.sendMessage("[RedemptionRoleplay] You are now an §7OFF DUTY Correctional-Officer (Citizen)!");
							Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Owner");
						player.sendMessage("[RedemptionRoleplay] You have been added to the §9Administrator (Owner) §fgroup!");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the administration team.");
					}
				}

				//STAFF CUSTOM COMMANDS
				else if(args[0].equalsIgnoreCase("help")){
					if(player.hasPermission("staff.moderator")){
						player.sendMessage("[RedemptionRoleplay] List of custom staff commands:");
						player.sendMessage("[RedemptionRoleplay] /staff clearchat -- Clears the in-game chat. (Moderator+)");
						player.sendMessage("[RedemptionRoleplay] /staff mod/moderator -- Sets your group to Moderator. (Moderator+)");
						player.sendMessage("[RedemptionRoleplay] /staff admin/administrator -- Sets your group to Administrator (Administrator+)");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the staff team.");
					}
				}

				else if(args[0].equalsIgnoreCase("clearchat")){
					if(player.hasPermission("staff.moderator")){
						Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage(ChatColor.GOLD + "|-------------------+====+-------------------|");
						Bukkit.broadcastMessage(ChatColor.BOLD + " The chat has been cleared by staff member " + player.getName() + "!");
						Bukkit.broadcastMessage(ChatColor.GOLD + "|-------------------+====+-------------------|");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the staff team.");
					}
				}

			}else{
				sender.sendMessage("[RedemptionRoleplay] You must be a player to run this command!");
			}
		}
		return false;
	}

}
