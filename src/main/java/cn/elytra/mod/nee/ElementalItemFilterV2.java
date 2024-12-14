package cn.elytra.mod.nee;

import codechicken.nei.SearchField;
import codechicken.nei.SearchTokenParser;
import codechicken.nei.api.ItemFilter;
import gregtech.api.enums.Element;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.Set;

import java.util.regex.Pattern;

public class ElementalItemFilterV2 {

	public static final SearchField.SearchParserProvider ELEMENTAL_SEARCH_PARSER_PROVIDER =
		new SearchField.SearchParserProvider('*', "itemElement", EnumChatFormatting.GOLD, ElementItemFilter::new) {
			@Override
			public SearchTokenParser.SearchMode getSearchMode() {
				return SearchTokenParser.SearchMode.PREFIX;
			}
		};

	public static class ElementItemFilter implements ItemFilter {

		private final Pattern pattern;

		public ElementItemFilter(Pattern pattern) {
			this.pattern = pattern;
		}

		@Override
		public boolean matches(ItemStack item) {
			Set<Element> elements = GregTechUtil.getContainingElements(item);
			for (Element el : elements) {
				if (pattern.matcher(el.mName).find()) {
					return true;
				}
				if (pattern.matcher(el.name()).find()) {
					return true;
				}
			}
			return false;
		}

	}

}
