package com.redemptionroleplay.RedemptionRoleplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class RedemptionRoleplayPrisonCommands implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private final RedemptionRoleplay plugin;
	
	public RedemptionRoleplayPrisonCommands(RedemptionRoleplay plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("co")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				
				if(args[0].equalsIgnoreCase("onduty")){
					if(player.hasPermission("roleplay.co") || player.hasPermission("roleplay.*")){
						if(!(RedemptionRoleplay.arrestList.containsKey(player.getName()))){
							if(!(RedemptionRoleplay.cuffList.contains(player))){
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Correctional-Officer");
								player.sendMessage("[RedemptionRoleplay] You have been added to the §5Correctional-Officer§f group! For help with commands type: /co help");
								RedemptionRoleplay.coList.add(player);
								player.sendMessage("[RedemptionRoleplay] You are now an §bON DUTY§f Correctional-Officer!");
								Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has switched to ON DUTY.");
							}else{
								player.sendMessage("[RedemptionRoleplay] You cannot go on duty whilst you are handcuffed!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] You cannot go on duty whilst you are arrested!");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the Correctional department!");
					}
				}
				else if(args[0].equalsIgnoreCase("offduty")){
					if(RedemptionRoleplay.coList.contains(player)){
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
						player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
						player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
						player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
						player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Citizen");
						player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty Correctional-Officer list and your inventory has been wiped!");
						player.sendMessage("[RedemptionRoleplay] You are now an §7OFF DUTY Correctional-Officer (Citizen)!");
						RedemptionRoleplay.coList.remove(player);
						Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has switched to §7OFF DUTY.");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("help")){
					player.sendMessage("[RedemptionRoleplay] List of correctional commands:");
					player.sendMessage("[RedemptionRoleplay] /co help -- Displays a list of correctional commands and formatting.");
					player.sendMessage("[RedemptionRoleplay] /co onduty -- Changes your current status to an on duty officer.");
					player.sendMessage("[RedemptionRoleplay] /co offduty -- Changes your current status to an off duty officer (Citizen).");
					player.sendMessage("[RedemptionRoleplay] /co lockdown -- Initiates a maximum security lockdown of the prison (ONLY TO BE USED UNDER CIRCUMSTANCES!)");
					player.sendMessage("[RedemptionRoleplay] /co unlockdown -- Ends the maximum security lockdown of the prison.");
					player.sendMessage("[RedemptionRoleplay] /co loadout -- Gives you the standard Correctional-Officer loadout.");
					player.sendMessage("[RedemptionRoleplay] /co cuff <Player> -- Handcuffs the player if you're close enough.");
					player.sendMessage("[RedemptionRoleplay] /co uncuff <Player> -- Releases the player from handcuffs if you're close enough.");
				}
				else if(args[0].equalsIgnoreCase("lockdown")){
					if(RedemptionRoleplay.coList.contains(player)){
						player.sendMessage("[RedemptionRoleplay] You have initiated a maximum security lockdown.");
						Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has initiated a maximum security lockdown.");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("unlockdown")){
					if(RedemptionRoleplay.coList.contains(player)){
						player.sendMessage("[RedemptionRoleplay] You have ended the maximum security lockdown.");
						Bukkit.broadcastMessage("§5Correctional-Officer " + player.getName() + " has ended the maximum security lockdown.");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("loadout")){
					if(RedemptionRoleplay.coList.contains(player)){
						PlayerInventory inventory = player.getInventory();
						ItemStack tazer = new ItemStack(Material.BOW,1);
						ItemStack arrow = new ItemStack(Material.ARROW,1);
						ItemStack helmet = new ItemStack(Material.LEATHER_HELMET,1);
						ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE,1);
						ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS,1);
						ItemStack boots = new ItemStack(Material.LEATHER_BOOTS,1);
						ItemStack stick = new ItemStack(Material.STICK,1);
						
						ItemMeta tMeta = tazer.getItemMeta();
						ItemMeta aMeta = arrow.getItemMeta();
						ItemMeta hMeta = helmet.getItemMeta();
						ItemMeta cMeta = chest.getItemMeta();
						ItemMeta lMeta = legs.getItemMeta();
						ItemMeta bMeta = boots.getItemMeta();
						ItemMeta sMeta = stick.getItemMeta();
						
						tMeta.addEnchant(Enchantment.ARROW_INFINITE, 10, true);
						tMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						tMeta.setDisplayName("CO Tazer");

						sMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						sMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
						sMeta.setDisplayName("CO Baton");

						aMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						aMeta.setDisplayName("Tazer Bullet");

						hMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						hMeta.setDisplayName("CO Headgear");

						cMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						cMeta.setDisplayName("CO Vest");

						lMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						lMeta.setDisplayName("CO Trousers");

						bMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						bMeta.setDisplayName("CO Boots");
						
						tazer.setItemMeta(tMeta);
						helmet.setItemMeta(hMeta);
						chest.setItemMeta(cMeta);
						legs.setItemMeta(lMeta);
						boots.setItemMeta(bMeta);
						stick.setItemMeta(sMeta);

						inventory.addItem(tazer);
						inventory.addItem(arrow);
						inventory.addItem(helmet);
						inventory.addItem(chest);
						inventory.addItem(legs);
						inventory.addItem(boots);
						inventory.addItem(stick);
						
						player.sendMessage("[RedemptionRoleplay] You have been given a standard issue correctional loadout!");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("cuff")){
					if(RedemptionRoleplay.coList.contains(player)){
						if(args.length == 2){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(!(RedemptionRoleplay.cuffList.contains(target))){
									double playerX = player.getLocation().getX();
									double playerY = player.getLocation().getY();
									double playerZ = player.getLocation().getZ();

									double targetX = target.getLocation().getX();
									double targetY = target.getLocation().getY();
									double targetZ = target.getLocation().getZ();

									if((playerX - 3.00 < targetX && targetX < playerX + 3.00) && (playerY - 3.00 < targetY && targetY < playerY + 3.00) && (playerZ - 3.00 < targetZ && targetZ < playerZ + 3.00)){
										RedemptionRoleplay.cuffList.add(target);
										player.sendMessage("[RedemptionRoleplay] The suspect has been handcuffed.");
										player.chat("Hands behind your back!");
										target.sendMessage("Correctional-Officer " + player.getName() + " puts your hands behind your back and places the handcuffs on firmly.");
									}else{
										player.sendMessage("[RedemptionRoleplay] The suspect is too far away.");
									}
								}else{
									player.sendMessage("[RedemptionRoleplay] The suspect is already handcuffed.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /co cuff <Player>");

						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("uncuff")){
					if(RedemptionRoleplay.coList.contains(player)){
						if(args.length == 2){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(RedemptionRoleplay.cuffList.contains(target)){
									if(!(RedemptionRoleplay.cuffList.contains(player))){
										RedemptionRoleplay.cuffList.remove(target);
										player.sendMessage("[RedemptionRoleplay] The suspect has been unhandcuffed.");
										target.sendMessage("Correctional-Officer " + player.getName() + " slowly releases you from the handcuffs.");
									}else{
										player.sendMessage("[RedemptionRoleplay] You cannot unhandcuff someone whilst you are handcuffed!");
									}
								}else{
									player.sendMessage("[RedemptionRoleplay] The suspect isn't handcuffed.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /co uncuff <Player>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty Correctional-Officer!");
					}
				}
			}
		}
		
		return false;
	}
	
}
