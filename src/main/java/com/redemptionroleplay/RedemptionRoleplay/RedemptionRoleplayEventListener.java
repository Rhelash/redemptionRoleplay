package com.redemptionroleplay.RedemptionRoleplay;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RedemptionRoleplayEventListener implements Listener{
	
	public RedemptionRoleplayEventListener(RedemptionRoleplay plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(RedemptionRoleplay.cuffList.contains(player)){
			event.setCancelled(true);
			player.teleport(player.getLocation());
			player.sendMessage("[RedemptionRoleplay] You cannot move whilst you are handcuffed!");
		}
		else if(RedemptionRoleplay.tazeList.contains(player)){
			event.setCancelled(true);
			player.teleport(player.getLocation());
			player.sendMessage("[RedemptionRoleplay] You cannot move for a while as you are tazed.");
		}
	}
	@EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
		if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Tazer") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Tazer")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Headgear") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Headgear")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Vest") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Vest")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Trousers") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Trousers")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Boots") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Boots")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Tazer Bullet")){
			event.getEntity().remove();
		}
		else if(event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("Police Baton") || event.getEntity().getItemStack().getItemMeta().getDisplayName().equals("CO Baton")){
			event.getEntity().remove();
		}
    }
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(event instanceof EntityDamageByEntityEvent){
			if(event.getEntity() instanceof Player){
				final Player entity = (Player) event.getEntity();
				if(event.getDamager() instanceof Arrow){
					Arrow a = (Arrow) event.getDamager();
					if(a.getShooter() instanceof Player){
						Player player = (Player) a.getShooter();
						if(player.getItemInHand().getItemMeta().getDisplayName().equals("Police Tazer") || player.getItemInHand().getItemMeta().getDisplayName().equals("CO Tazer")){
							if(RedemptionRoleplay.policeList.contains(player) || RedemptionRoleplay.coList.contains(player)){
								RedemptionRoleplay.tazeList.add(entity);
								player.sendMessage("[RedemptionRoleplay] Suspect has been tazed.");
								entity.sendMessage("[RedemptionRoleplay] You have been tazed by Officer " + player.getName());
								entity.chat("**Feels the electric shock through their body and falls to the ground**");
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
									public void run(){
										RedemptionRoleplay.tazeList.remove(entity);
										entity.sendMessage("[RedemptionRoleplay] You are no longer tazed.");
										entity.chat("**Slowly stumbles to their feet after the effects of the tazer**");
									}
								}, 7000);
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void onUse(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == Material.SUGAR){
			player.removePotionEffect(PotionEffectType.SPEED);
			player.removePotionEffect(PotionEffectType.CONFUSION);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 2));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2400, 2));
			ItemStack sugar = new ItemStack(Material.SUGAR,1);
			player.getInventory().removeItem(sugar);
		}
	}
}
