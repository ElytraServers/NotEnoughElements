package cn.elytra.mod.nee;

import gregtech.api.enums.Element;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

import java.util.Set;
import java.util.stream.Collectors;

public class NotEnoughElementsCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "not-enough-elements";
	}

	@Override
	public String getCommandUsage(ICommandSender iCommandSender) {
		return "/not-enough-elements";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] strings) {
		if(sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			ItemStack stack = player.getHeldItem();
			Set<Element> elements = GregTechUtil.getContainingElements(stack);
			IChatComponent message;
			if(elements.size() > 0) {
				message = new ChatComponentTranslation(
						"NotEnoughElements.Message.ElementsContaining",
						String.join(", ", elements.stream().map(Enum::name).collect(Collectors.toSet()))
				);
			} else {
				message = new ChatComponentTranslation("NotEnoughElements.Message.NoElementContaining");
			}
			sender.addChatMessage(message);
		} else {
			sender.addChatMessage(new ChatComponentText("This command is player only!"));
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
}
