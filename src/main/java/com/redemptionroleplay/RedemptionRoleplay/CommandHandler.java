package com.redemptionroleplay.RedemptionRoleplay;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.redemptionroleplay.commands.RedemptionCommand;
import com.redemptionroleplay.playerRoles.PlayerRole;

public class CommandHandler implements CommandExecutor {
		
	private String[] validCommandNames;	
	
	public CommandHandler(String[] validCommandsArg) {
		validCommandNames = validCommandsArg;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Work out what command is being requested
		RedemptionCommand calledCommand = this.determineCommand(cmd);				
		
		// Work out player role and assign to command
		PlayerRole senderRole = this.determineRole(sender);
		calledCommand.setRole(senderRole);
		
		// Execute command with attached role
		calledCommand.execute(args);		
		
		return false;				
	}
	
	
	// TODO: Add docs
	// TODO: This method is too big
	private PlayerRole determineRole(CommandSender sender) {
		
		// TODO: Throw exception if sender can't be cast to Player
		
		Player playerInstance = (Player) sender;
		
		
		// TODO: Find an alternative way to deal with all of these static references (config object?)
		boolean isCriminal = 
			RedemptionRoleplay.arrestList.containsKey(playerInstance.getName()) &&
			RedemptionRoleplay.cuffList.contains(playerInstance.getName());
		
		boolean isPolice = 
			playerInstance.hasPermission("roleplay.police") &&
			!isCriminal &&
			RedemptionRoleplay.policeList.contains(playerInstance.getName());
		
		boolean isPrisonGuard =
			playerInstance.hasPermission("roleplay.co") &&
			!isCriminal &&
			RedemptionRoleplay.coList.contains(playerInstance.getName());
		
		boolean isModerator =
			playerInstance.hasPermission("staff.moderator");
		
		boolean isAdmin =
			playerInstance.hasPermission("staff.administrator");
		
		boolean isOwner =
			playerInstance.hasPermission("staff.*");
		
				
		PlayerRole returnVal;		
		
		if(isPolice) {
			returnVal = new PolicePlayer(); 
		} else if (isPrisonGuard) {
			returnVal = new PrisonGuardPlayer();
		} else if (isModerator) {
			returnVal = new ModeratorPlayer();
		} else if (isAdmin) {
			returnVal = new AdminPlayer();
		} else if (isOwner) {
			returnVal = new OwnerPlayer();
		}
		
		return returnVal;
		
	}

	
	// TODO: Add docs
	private RedemptionCommand determineCommand(Command cmd) {
		
		RedemptionCommand returnVal;
		
		// TODO: This list will grow large, need to link class names to the "validCommandNames" variable.
		switch(cmd.getName().toLowerCase()) {
			case "police":
				returnVal = new PoliceCommand();
				break;
			case "pdgate":
				returnVal = new PDGateCommand();
				break;
			case "co":
				returnVal = new CoCommand();
				break;
			case "staff":
				returnVal = new StaffCommand();
				break;
			case "chelp":
				returnVal = new CHelpCommand();
				break;
			case "fine":
				returnVal = new FineCommand();
				break;
			case "drugs":
				returnVal = new DrugsCommand();
				break;			
				
			// TODO: Deal with this case using an exception
			default:
				returnVal = null;
		}
	}

}
