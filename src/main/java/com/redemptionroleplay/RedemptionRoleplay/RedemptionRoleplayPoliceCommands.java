package com.redemptionroleplay.RedemptionRoleplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class RedemptionRoleplayPoliceCommands implements CommandExecutor{

	@SuppressWarnings("unused")
	private final RedemptionRoleplay plugin;

	public RedemptionRoleplayPoliceCommands(RedemptionRoleplay plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		//POLICE COMMANDS

		if (cmd.getName().equalsIgnoreCase("police")){
			if (sender instanceof Player) {
				Player player = (Player) sender;

				//ONDUTY/OFFDUTY COMMANDS

				if(args[0].equalsIgnoreCase("onduty")){
					if (player.hasPermission("roleplay.police") || player.hasPermission("roleplay.*")){
						if(!(RedemptionRoleplay.arrestList.containsKey(player.getName()))){
							if(!(RedemptionRoleplay.cuffList.contains(player))){
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Police");
								player.sendMessage("[RedemptionRoleplay] You have been added to the §bPolice§f group! For help with commands type: /police help");
								RedemptionRoleplay.policeList.add(player);
								if(player.hasPermission("roleplay.policesupervisor")){
									RedemptionRoleplay.policeSupervisorList.add(player);
									player.sendMessage("[RedemptionRoleplay] You are now an §bON DUTY§f police supervisor!");
									Bukkit.broadcastMessage("§4Supervisor " + player.getName() + " has switched to ON DUTY.");
								}else{
									player.sendMessage("[RedemptionRoleplay] You are now an §bON DUTY§f police officer!");
									Bukkit.broadcastMessage("§4Officer " + player.getName() + " has switched to ON DUTY.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] You cannot go on duty whilst you are handcuffed!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] You cannot go on duty whilst you are arrested!");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not a member of the police force!");
					}
				}
				else if(args[0].equalsIgnoreCase("offduty")){
					if(RedemptionRoleplay.policeList.contains(player)){
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + player.getName());
						player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
						player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
						player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
						player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group set Citizen");
						player.sendMessage("[RedemptionRoleplay] You have been removed from the on-duty police officer list and your inventory has been wiped!");
						player.sendMessage("[RedemptionRoleplay] You are now an §7OFF DUTY police officer (Citizen)!");
						RedemptionRoleplay.policeList.remove(player);
						if(RedemptionRoleplay.policeSupervisorList.contains(player)){
							RedemptionRoleplay.policeSupervisorList.remove(player);
							Bukkit.broadcastMessage("§4Supervisor " + player.getName() + " has switched to §7OFF DUTY.");
						}else{
							Bukkit.broadcastMessage("§4Officer " + player.getName() + " has switched to §7OFF DUTY.");
						}
						
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				

				//HELP COMMAND
				else if(args[0].equalsIgnoreCase("help")){
					player.sendMessage("[RedemptionRoleplay] List of police commands:");
					player.sendMessage("[RedemptionRoleplay] /police help -- Displays a list of police commands and formatting.");
					player.sendMessage("[RedemptionRoleplay] /police onduty -- Changes your current status to an on duty officer.");
					player.sendMessage("[RedemptionRoleplay] /police sonduty -- Changes your current status to an on duty supervisor.");
					player.sendMessage("[RedemptionRoleplay] /police offduty -- Changes your current status to an off duty officer (Citizen).");
					player.sendMessage("[RedemptionRoleplay] /police cuff <Player> -- Handcuffs the player if you are close enough.");
					player.sendMessage("[RedemptionRoleplay] /police uncuff <Player> -- Releases the player from handcuffs if you are close enough.");
					player.sendMessage("[RedemptionRoleplay] /police arrest <Player> <Length(s = seconds, m = minutes h = hours> <Reason> -- Arrests the player if they are handcuffed.");
					player.sendMessage("[RedemptionRoleplay] /police maxarrest <Player> <Length(s = seconds, m = minutes h = hours> <Reason> -- Arrests the player in max security if they are handcuffed.");
					player.sendMessage("[RedemptionRoleplay] /police release <Player> <Reason> -- Releases the player from either jail.");
					player.sendMessage("[RedemptionRoleplay] /police loadout -- Gives you your standard police loadout.");
					player.sendMessage("[RedemptionRoleplay] /police fine <Player> <Amount> <Reason> -- Fines a player for the set amount and reason.");
					player.sendMessage("[RedemptionRoleplay] /police wanted <Player> <Reason> -- Adds the player to the wantedlist with the reason specified.");
					player.sendMessage("[RedemptionRoleplay] /police unwanted <Player> <Reason> -- Removes the player from the wantedlist with the reason specified.");
					player.sendMessage("[RedemptionRoleplay] /police wantedlist -- Outputs the wantedlist into your chat.");
					player.sendMessage("[RedemptionRoleplay] /police sloadout -- Gives a police supervisor his loadout.");
					player.sendMessage("[RedemptionRoleplay] /police listsupervisors -- Lists all active police supervisor's.");
					player.sendMessage("[RedemptionRoleplay] /pdgate <Open/Close> <GateID> -- Opens/Closes the gate specified.");
				}

				//POLICE POWERS
				else if(args[0].equalsIgnoreCase("listsupervisors")){
					if(RedemptionRoleplay.policeList.contains(player)){
						player.sendMessage("[RedemptionRoleplay] List of currently active supervisors:");
						for(int i = 0; i < RedemptionRoleplay.policeSupervisorList.size(); i++){
							player.sendMessage("    - " + RedemptionRoleplay.policeSupervisorList.get(i).getName());
						}
						player.sendMessage("[RedemptionRoleplay] End of supervisor list.");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				
				else if(args[0].equalsIgnoreCase("fine")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 4){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(!(RedemptionRoleplay.fineList.containsKey(target))){
									if(!(RedemptionRoleplay.policeList.contains(target))){
										int amount = Integer.parseInt(args[2]);
										StringBuilder strBuilder = new StringBuilder();
										
										for (int i=3; i<args.length; i++)
										{
											strBuilder.append(args[i]);
											strBuilder.append(" ");
										}
										String reason = strBuilder.toString();

										target.sendMessage("[RedemptionRoleplay] You are currently being fined by Officer " + player.getName() + ", Amount: " + amount + ", Reason: " + reason);
										player.sendMessage("[RedemptionRoleplay] You are currently issuing a fine to " + target.getName() + ", Amount: " + amount + ", Reason: ");
										player.chat("**Hands over the formal written fine to " + target.getName() + "** Sign here please. **Points to a signature box**");
										RedemptionRoleplay.fineList.put(target, amount);
										RedemptionRoleplay.officerFineList.put(target, player);
									}else{
										player.sendMessage("[RedemptionRoleplay] You cannot fine another Police Officer!");
									}
								}else{
									player.sendMessage("[RedemptionRoleplay] That player is already being fined.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police fine <Player> <Amount> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}

				else if(args[0].equalsIgnoreCase("loadout")){
					if(RedemptionRoleplay.policeList.contains(player)){
						PlayerInventory inventory = player.getInventory();
						ItemStack tazer = new ItemStack(Material.BOW,1);
						ItemStack arrow = new ItemStack(Material.ARROW,1);
						ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET,1);
						ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE,1);
						ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS,1);
						ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS,1);
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
						tMeta.setDisplayName("Police Tazer");

						sMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						sMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
						sMeta.setDisplayName("Police Baton");

						aMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						aMeta.setDisplayName("Tazer Bullet");

						hMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						hMeta.setDisplayName("Police Headgear");

						cMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						cMeta.setDisplayName("Police Vest");

						lMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						lMeta.setDisplayName("Police Trousers");

						bMeta.addEnchant(Enchantment.DURABILITY, 10, true);
						bMeta.setDisplayName("Police Boots");

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

						player.sendMessage("[RedemptionRoleplay] You have been given a standard issue police loadout!");
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				
				else if(args[0].equalsIgnoreCase("sloadout")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(player.hasPermission("roleplay.policesupervisor")){
							PlayerInventory inventory = player.getInventory();
							ItemStack tazer = new ItemStack(Material.BOW,1);
							ItemStack arrow = new ItemStack(Material.ARROW,1);
							ItemStack helmet = new ItemStack(Material.IRON_HELMET,1);
							ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE,1);
							ItemStack legs = new ItemStack(Material.IRON_LEGGINGS,1);
							ItemStack boots = new ItemStack(Material.IRON_BOOTS,1);
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
							tMeta.setDisplayName("Police Tazer");

							sMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							sMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
							sMeta.setDisplayName("Police Baton");

							aMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							aMeta.setDisplayName("Tazer Bullet");

							hMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							hMeta.setDisplayName("Police Headgear");

							cMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							cMeta.setDisplayName("Police Vest");

							lMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							lMeta.setDisplayName("Police Trousers");

							bMeta.addEnchant(Enchantment.DURABILITY, 10, true);
							bMeta.setDisplayName("Police Boots");

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

						player.sendMessage("[RedemptionRoleplay] You have been given a supervisor issue police loadout!");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}

				else if(args[0].equalsIgnoreCase("wantedlist")){
					if(RedemptionRoleplay.policeList.contains(player)){
						player.sendMessage("[RedemptionRoleplay] List of currently wanted players:");
						for (String key : RedemptionRoleplay.wantedList.keySet()) {
							player.sendMessage("[RedemptionRoleplay] " + key + " - " + RedemptionRoleplay.wantedList.get(key));
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("wanted")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(!(RedemptionRoleplay.wantedList.containsKey(target))){
									StringBuilder strBuilder = new StringBuilder();
									for (int i=2; i<args.length; i++)
									{
										strBuilder.append(args[i]);
										strBuilder.append(" ");
									}
									String reason = strBuilder.toString();
									RedemptionRoleplay.wantedList.put(target.getName(),reason);
									Bukkit.broadcastMessage("§4Officer " + player.getName() + " has added " + target.getName() + " to the wanted list with reason: " + reason);
								}else{
									player.sendMessage("[RedemptionRoleplay] The suspect is already wanted.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police wanted <Player> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("unwanted")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(RedemptionRoleplay.wantedList.containsKey(target)){
									if(!(RedemptionRoleplay.wantedList.containsKey(player))){
										StringBuilder strBuilder = new StringBuilder();
										for (int i=2; i<args.length; i++)
										{
											strBuilder.append(args[i]);
											strBuilder.append(" ");
										}
										String reason = strBuilder.toString();
										RedemptionRoleplay.wantedList.remove(target.getName());
										Bukkit.broadcastMessage("§4Officer " + player.getName() + " has removed " + target.getName() + " from the wanted list with reason: " + reason);
									}else{
										player.sendMessage("[RedemptionRoleplay] You cannot remove yourself from the wanted list!");
									}
								}else{
									player.sendMessage("[RedemptionRoleplay] The suspect isn't wanted.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police unwanted <Player> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("arrest")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(RedemptionRoleplay.cuffList.contains(target)){
									String time = args[2];
									StringBuilder strBuilder = new StringBuilder();
									for (int i=3; i<args.length; i++)
									{
										strBuilder.append(args[i]);
										strBuilder.append(" ");
									}
									String reason = strBuilder.toString();
									if(RedemptionRoleplay.policeList.contains(target)){
										RedemptionRoleplay.policeList.remove(target);
										if(RedemptionRoleplay.policeSupervisorList.contains(target)){
											RedemptionRoleplay.policeSupervisorList.remove(target);
										}
										Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + target.getName() + " group set Citizen");
										target.sendMessage("[RedemptionRoleplay] You have been changed to off-duty due to being arrested.");
									}
									RedemptionRoleplay.arrestList.put(target.getName(), reason);
									RedemptionRoleplay.cuffList.remove(target);
									Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "jail " + target.getName() + " policejail " + time);
									Bukkit.broadcastMessage("§4Officer " + player.getName() + " has arrested " + target.getName() + " for " + time + " with reason: " + reason);
								}else{
									player.sendMessage("[RedemptionRoleplay] The suspect isn't handcuffed.");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police arrest <Player> <Length> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("maxarrest")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
							if(RedemptionRoleplay.cuffList.contains(target)){
								if(target.isOnline()){
									if(RedemptionRoleplay.cuffList.contains(target)){
										String time = args[2];
										StringBuilder strBuilder = new StringBuilder();
										for (int i=3; i<args.length; i++)
										{
											strBuilder.append(args[i]);
											strBuilder.append(" ");
										}
										String reason = strBuilder.toString();
										if(RedemptionRoleplay.policeList.contains(target)){
											RedemptionRoleplay.policeList.remove(target);
											if(RedemptionRoleplay.policeSupervisorList.contains(target)){
												RedemptionRoleplay.policeSupervisorList.remove(target);
											}
											Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + target.getName() + " group set Citizen");
											target.sendMessage("[RedemptionRoleplay] You have been changed to off-duty due to being arrested.");
										}
										RedemptionRoleplay.arrestList.put(target.getName(), reason);
										RedemptionRoleplay.cuffList.remove(target);
										Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "jail " + target.getName() + " fedjail " + time);
										Bukkit.broadcastMessage("§4Officer " + player.getName() + " has arrested " + target.getName() + " in maximum security for " + time + " with reason: " + reason);
									}else{
										player.sendMessage("[RedemptionRoleplay] The suspect isn't handcuffed.");
									}
								}else{
									player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
								}
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't handcuffed!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police maxarrest <Player> <Length> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("release")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								StringBuilder strBuilder = new StringBuilder();
								for (int i=2; i<args.length; i++)
								{
									strBuilder.append(args[i]);
									strBuilder.append(" ");
								}
								String reason = strBuilder.toString();
								RedemptionRoleplay.arrestList.remove(target.getName());
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unjail " + target.getName());
								Bukkit.broadcastMessage("§4Officer " + player.getName() + " has released " + target.getName() + " with reason: " + reason);
							}else{
								player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police release <Player> <Reason>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("cuff")){
					if(RedemptionRoleplay.policeList.contains(player)){
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
										target.sendMessage("Officer " + player.getName() + " puts your hands behind your back and places the handcuffs on firmly.");
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
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police cuff <Player>");

						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
				else if(args[0].equalsIgnoreCase("uncuff")){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args.length == 2){
							Player target = Bukkit.getPlayer(args[1]);
							if(target.isOnline()){
								if(RedemptionRoleplay.cuffList.contains(target)){
									if(!(RedemptionRoleplay.cuffList.contains(player))){
										RedemptionRoleplay.cuffList.remove(target);
										player.sendMessage("[RedemptionRoleplay] The suspect has been unhandcuffed.");
										target.sendMessage("Officer " + player.getName() + " slowly releases you from the handcuffs.");
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
							player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /police uncuff <Player>");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}
			} else {
				sender.sendMessage("[RedemptionRoleplay] You must be a player to run this command!");
			}
		}

		if(cmd.getName().equalsIgnoreCase("pdgate")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 2){
					if(RedemptionRoleplay.policeList.contains(player)){
						if(args[0].equalsIgnoreCase("open")){
							if(args[1].equalsIgnoreCase("1")){
								Block bottomLeft = Bukkit.getWorld("world").getBlockAt(-263, 71, -118);
								Block bottomRight = Bukkit.getWorld("world").getBlockAt(-263, 71, -119);
								Block topLeft = Bukkit.getWorld("world").getBlockAt(-263, 72, -118);
								Block topRight = Bukkit.getWorld("world").getBlockAt(-263, 72, -119);

								bottomLeft.setType(Material.AIR);
								bottomRight.setType(Material.AIR);
								topLeft.setType(Material.AIR);
								topRight.setType(Material.AIR);
								player.sendMessage("[RedemptionRoleplay] Police Gate #1 has been opened.");
							}else if(args[1].equalsIgnoreCase("2")){
								Block bottom = Bukkit.getWorld("world").getBlockAt(-267, 71, -105);
								Block top = Bukkit.getWorld("world").getBlockAt(-267, 72, -105);

								bottom.setType(Material.AIR);
								top.setType(Material.AIR);
								player.sendMessage("[RedemptionRoleplay] Police Gate #2 has been opened.");
							}
						}
						else if(args[0].equalsIgnoreCase("close")){
							if(args[1].equalsIgnoreCase("1")){
								Block bottomLeft = Bukkit.getWorld("world").getBlockAt(-263, 71, -118);
								Block bottomRight = Bukkit.getWorld("world").getBlockAt(-263, 71, -119);
								Block topLeft = Bukkit.getWorld("world").getBlockAt(-263, 72, -118);
								Block topRight = Bukkit.getWorld("world").getBlockAt(-263, 72, -119);

								bottomLeft.setType(Material.IRON_FENCE);
								bottomRight.setType(Material.IRON_FENCE);
								topLeft.setType(Material.IRON_FENCE);
								topRight.setType(Material.IRON_FENCE);
								player.sendMessage("[RedemptionRoleplay] Police Gate #1 has been closed.");
							}else if(args[1].equalsIgnoreCase("2")){
								Block bottom = Bukkit.getWorld("world").getBlockAt(-267, 71, -105);
								Block top = Bukkit.getWorld("world").getBlockAt(-267, 72, -105);

								bottom.setType(Material.IRON_FENCE);
								top.setType(Material.IRON_FENCE);
								player.sendMessage("[RedemptionRoleplay] Police Gate #2 has been closed.");
							}
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not an on duty police officer!");
					}
				}else{
					player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /pdgate <Open/Close> <GateID>");
				}
			}
		}
		return false;
	}

}
