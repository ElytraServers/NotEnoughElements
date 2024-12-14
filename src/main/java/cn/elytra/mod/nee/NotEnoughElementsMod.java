package cn.elytra.mod.nee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.GRADLETOKEN_MODID, version = Tags.GRADLETOKEN_VERSION, name = Tags.GRADLETOKEN_MODNAME,
	acceptedMinecraftVersions = "[1.7.10]")
public class NotEnoughElementsMod {

	public static final Logger LOG = LogManager.getLogger(Tags.GRADLETOKEN_MODID);

	public NotEnoughElementsMod() {
		LOG.info("Not Enough Elements!");

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new NotEnoughElementsCommand());
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!event.showAdvancedItemTooltips) return;

		try {
			String[] tooltips = ItemElementTooltip.getItemElementTooltip(event.itemStack);
			if (tooltips != null) {
				for (String tooltip : tooltips) {
					event.toolTip.add(EnumChatFormatting.GOLD + tooltip);
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}
}
