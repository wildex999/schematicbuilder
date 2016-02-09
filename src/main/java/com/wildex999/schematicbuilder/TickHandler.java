package com.wildex999.schematicbuilder;

import com.wildex999.utils.ModLog;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class TickHandler {
	
	public int saveFrequency = 60; //Seconds between each Config save(If marked for saving)
	public long nextSave = 0;
	
	@SubscribeEvent
	public void onServerTick(ServerTickEvent event) {
		if(event.side == Side.CLIENT)
            return;

		if(event.phase == Phase.END)
		{
			//Networking queued messages
			//MessageBase.sendQueuedMessages();
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event) {
		long currentTime = System.currentTimeMillis();
		if(currentTime > nextSave)
		{
			nextSave = currentTime + saveFrequency*1000;
			ModSchematicBuilder mod = ModSchematicBuilder.instance;
			
			/*if(mod.debug)
				ModLog.logger.info("Saving changed configs...");*/
			
			if(mod.configGeneral.markedForSave)
			{
				if(mod.debug)
					ModLog.logger.info("Saving config: General");
				mod.configGeneral.saveConfig(true);
			}
			
			/*if(mod.debug)
				ModLog.logger.info("Done saving config.");*/
		}
	}
}
