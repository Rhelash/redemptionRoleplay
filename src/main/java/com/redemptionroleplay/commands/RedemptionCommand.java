package com.redemptionroleplay.commands;

import com.redemptionroleplay.playerRoles.PlayerRole;

public interface RedemptionCommand {	
	public boolean setRole(PlayerRole role);
	public void execute(String[] args);	
}
