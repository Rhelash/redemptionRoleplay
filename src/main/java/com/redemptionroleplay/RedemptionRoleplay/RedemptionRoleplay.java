package com.redemptionroleplay.RedemptionRoleplay;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RedemptionRoleplay extends JavaPlugin{
	static ArrayList<Player> policeList = new ArrayList<Player>();
	static ArrayList<Player> policeSupervisorList = new ArrayList<Player>();
	static ArrayList<Player> coList = new ArrayList<Player>();
	static ArrayList<Player> cuffList = new ArrayList<Player>();
	static ArrayList<Player> tazeList = new ArrayList<Player>();
	
	static HashMap<String,String> wantedList = new HashMap<String,String>();
	static HashMap<String,String> arrestList = new HashMap<String,String>();
	static HashMap<Player,Integer> fineList = new HashMap<Player,Integer>();
	static HashMap<Player,Player> officerFineList = new HashMap<Player,Player>();
	static HashMap<Player,Integer> drugsAmountList = new HashMap<Player,Integer>();
	static HashMap<Player,Integer> drugsPriceList = new HashMap<Player,Integer>();
	static HashMap<Player,Player> drugsNameList = new HashMap<Player,Player>();
	
	@Override
	public void onEnable(){
		getLogger().info("[RedemptionRoleplay] Version: " + Bukkit.getVersion() + " has been enabled.");
		new RedemptionRoleplayEventListener(this);
		//Command Instances
		this.getCommand("police").setExecutor(new RedemptionRoleplayPoliceCommands(this));
		this.getCommand("pdgate").setExecutor(new RedemptionRoleplayPoliceCommands(this));
		this.getCommand("co").setExecutor(new RedemptionRoleplayPrisonCommands(this));
		this.getCommand("staff").setExecutor(new RedemptionRoleplayStaffCommands(this));
		this.getCommand("chelp").setExecutor(new RedemptionRoleplayCommandExecutor(this));
		this.getCommand("fine").setExecutor(new RedemptionRoleplayCommandExecutor(this));
		this.getCommand("drugs").setExecutor(new RedemptionRoleplayCommandExecutor(this));
	}
	@Override
	public void onDisable(){
		getLogger().info("[RedemptionRoleplay] Version: " + Bukkit.getVersion() + " has been disabled.");
	}
}
