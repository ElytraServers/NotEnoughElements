package cn.elytra.mod.nee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME,
	acceptedMinecraftVersions = "[1.7.10]", dependencies = "")
public class NotEnoughElementsMod {

	public static final Logger LOG = LogManager.getLogger(Tags.MODID);

	public NotEnoughElementsMod() {
		LOG.info("Not Enough Elements!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new NotEnoughElementsCommand());
	}
}
