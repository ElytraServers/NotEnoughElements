package cn.elytra.mod.nee;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import gregtech.api.enums.Element;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class ItemElementTooltip {

	private static final LoadingCache<ItemStack, String[]> ITEM_TOOLTIP_CACHE = CacheBuilder.newBuilder()
		.maximumSize(500)
		.build(new ItemElementTooltipCacheLoader());

	public static String[] getItemElementTooltip(ItemStack stack) {
		return ITEM_TOOLTIP_CACHE.getUnchecked(stack);
	}

	private static class ItemElementTooltipCacheLoader extends CacheLoader<ItemStack, String[]> {
		@Override
		public String[] load(@NotNull ItemStack itemStack) {
			Set<Element> elements = GregTechUtil.getContainingElements(itemStack);

			if (elements.isEmpty()) {
				return new String[0];
			}

			StringBuilder sb = new StringBuilder();
			Iterator<Element> iter = elements.iterator();

			while (iter.hasNext()) {
				sb.append(iter.next().mName);
				if (iter.hasNext()) sb.append(", ");
				if (iter.hasNext() && sb.lastIndexOf("\n") + 25 < sb.length()) sb.append("\n");
			}

			return sb.toString().split("\n");
		}
	}

}
