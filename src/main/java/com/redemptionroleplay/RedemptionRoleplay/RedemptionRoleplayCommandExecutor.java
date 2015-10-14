package com.redemptionroleplay.RedemptionRoleplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RedemptionRoleplayCommandExecutor implements CommandExecutor{

	@SuppressWarnings("unused")
	private final RedemptionRoleplay plugin;

	public RedemptionRoleplayCommandExecutor(RedemptionRoleplay plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//CITIZEN COMMANDS

		if (cmd.getName().equalsIgnoreCase("chelp")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				
				player.sendMessage("[RedemptionRoleplay] List of citizen command categories:");
				player.sendMessage("[RedemptionRoleplay] /fine help -- Lists help for all fine related commands.");
				player.sendMessage("[RedemptionRoleplay] /drugs help -- Lists help for all drugs related commands.");
			}
		}

		//--FINE COMMANDS--//
		if (cmd.getName().equalsIgnoreCase("fine"))
		{
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args[0].equalsIgnoreCase("help")){
					player.sendMessage("[RedemptionRoleplay] List of fine commands:");
					player.sendMessage("[RedemptionRoleplay] /fine accept -- Accepts a fine handed to you by a Police Officer.");
					player.sendMessage("[RedemptionRoleplay] /fine deny -- Denys a fine handed to you by an officer.");
				}
				else if(args[0].equalsIgnoreCase("accept")){
					if(RedemptionRoleplay.fineList.containsKey(player)){
						int amount = RedemptionRoleplay.fineList.get(player);
						Player officer = RedemptionRoleplay.officerFineList.get(player);

						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco take " + player.getName() + " " + amount);
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + officer.getName() + " " + amount);
						RedemptionRoleplay.fineList.remove(player);
						RedemptionRoleplay.officerFineList.remove(player);
						player.sendMessage("[RedemptionRoleplay] You have paid the fine of $" + amount + ", given to you by Officer " + officer.getName());
						officer.sendMessage("[RedemptionRoleplay] " + player.getName() + " has paid the fine of $" + amount);
						Bukkit.broadcastMessage("§4" + player.getName() + " has paid a fine of $" + amount + ", given to them by Officer " + officer.getName());
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not currently being fined.");
					}
				}
				else if(args[0].equalsIgnoreCase("deny")){
					if(RedemptionRoleplay.fineList.containsKey(player)){
						int amount = RedemptionRoleplay.fineList.get(player);
						Player officer = RedemptionRoleplay.officerFineList.get(player);

						RedemptionRoleplay.fineList.remove(player);
						RedemptionRoleplay.officerFineList.remove(player);
						player.sendMessage("[RedemptionRoleplay] You have denied the fine for $" + amount + ", given to you by Officer " + officer.getName());
						officer.sendMessage("[RedemptionRoleplay] " + player.getName() + " has denied the fine for $" + amount);
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not currently being fined.");
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("drugs")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args[0].equalsIgnoreCase("help")){
					player.sendMessage("[RedemptionRoleplay] List of drugs commands:");
					player.sendMessage("[RedemptionRoleplay] /drugs sell <Player> <Amount> <Price> -- Sells drugs to a player.");
					player.sendMessage("[RedemptionRoleplay] /drugs accept -- Accepts a drug offer that was made to you.");
					player.sendMessage("[RedemptionRoleplay] /drugs deny -- Denies a drug offer that was made to you.");
				}
				else if(args[0].equalsIgnoreCase("sell")){
					if(args.length >= 4){
						Player target = Bukkit.getPlayer(args[1]);
						if(target.isOnline()){
							if(!(RedemptionRoleplay.drugsNameList.containsKey(target))){
								int amount = Integer.parseInt(args[2]);
								int price = Integer.parseInt(args[3]);
								
								PlayerInventory inventory = player.getInventory();
								inventory.containsAtLeast(new ItemStack(Material.SUGAR), amount);
								target.sendMessage("[RedemptionRoleplay] " + player.getName() + " is offering you some cocaine (SUGAR), Amount: " + amount + ", Price: $" + price);
								player.sendMessage("[RedemptionRoleplay] You are currently offering cocaine (SUGAR) to " + target.getName() + ", Amount: " + amount + ", Price: $" + price);
								RedemptionRoleplay.drugsAmountList.put(target, amount);
								RedemptionRoleplay.drugsNameList.put(target, player);
								RedemptionRoleplay.drugsPriceList.put(target, price);
							}else{
								player.sendMessage("[RedemptionRoleplay] That player is already being offered drugs.");
							}
						}else{
							player.sendMessage("[RedemptionRoleplay] That player isn't currently online!");
						}
					}else{
						player.sendMessage("[RedemptionRoleplay] Incorrect amount of arguments! Format: /drugs sell <Player> <Amount> <Price>");
					}
				}
				else if(args[0].equalsIgnoreCase("accept")){
					if(RedemptionRoleplay.drugsNameList.containsKey(player)){
						Player seller = RedemptionRoleplay.drugsNameList.get(player);
						int amount = RedemptionRoleplay.drugsAmountList.get(player);
						int price = RedemptionRoleplay.drugsPriceList.get(player);
						PlayerInventory sellerInventory = seller.getInventory();
						PlayerInventory playerInventory = player.getInventory();
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco take " + player.getName() + " " + price);
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + seller.getName() + " " + price);
						sellerInventory.removeItem(new ItemStack(Material.SUGAR, amount));
						playerInventory.addItem(new ItemStack(Material.SUGAR, amount));
						player.sendMessage("You have bought cocaine (SUGAR) from " + seller.getName() + ", Amount: " + amount + ", Price: $" + price);
						seller.sendMessage(player.getName() + " has bought cocaine (SUGAR) from you, Amount: " + amount + ", Price: $" + price);
						RedemptionRoleplay.drugsAmountList.remove(player);
						RedemptionRoleplay.drugsNameList.remove(player);
						RedemptionRoleplay.drugsPriceList.remove(player);
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not currently being offered drugs.");
					}
				}
				else if(args[0].equalsIgnoreCase("deny")){
					if(RedemptionRoleplay.drugsNameList.containsKey(player)){
						int amount = RedemptionRoleplay.drugsAmountList.get(player);
						int price = RedemptionRoleplay.drugsPriceList.get(player);
						Player seller = RedemptionRoleplay.drugsNameList.get(player);

						player.sendMessage("[RedemptionRoleplay] You have denied the cocaine (SUGAR) from " + seller.getName() + ", Amount: " + amount + ", Price: $" + price);
						seller.sendMessage(player.getName() + " has denied the cocaine (SUGAR) from you, Amount: " + amount + ", Price: $" + price);
						RedemptionRoleplay.drugsAmountList.remove(player);
						RedemptionRoleplay.drugsNameList.remove(player);
						RedemptionRoleplay.drugsPriceList.remove(player);
					}else{
						player.sendMessage("[RedemptionRoleplay] You are not currently being offered drugs.");
					}
				}
			}
		}
		
		return false;
	}

}
