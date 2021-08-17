package com.bloodshardnotifier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "Blood Shard Notifier"
)
public class BloodShardNotifierPlugin extends Plugin
{
	@Inject
	private Client client;

	//@Inject
	//private BloodShardNotifierConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Blood Shard Notifier started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Blood Shard Notifier stopped!");
	}

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned)
	{
		TileItem item = itemSpawned.getItem();
		if(item.getId() == ItemID.BLOOD_SHARD){
			//Using a direct beep rather than the notify from the notifier plugin because this plugin
			//is made for people who want to only get sound notifications for blood shards, but not other things
			Toolkit.getDefaultToolkit().beep();
		}
	}

	@Provides
	BloodShardNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BloodShardNotifierConfig.class);
	}
}
