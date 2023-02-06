package cn.elytra.mod.nee;

import codechicken.nei.api.ItemFilter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import gregtech.api.enums.Element;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Set;

public class ElementalItemFilter implements ItemFilter {

	/**
	 * A lowercase element chemical name to Element instance lookup table
	 * e.g.: he_3 => {@link Element#He_3}; o => {@link Element#O}.
	 */
	private static final Map<String, Element> ELEMENT_LOOKUP;

	static {
		ELEMENT_LOOKUP = Maps.newHashMap();
		for(Element element : Element.values()) {
			ELEMENT_LOOKUP.put(element.name().toLowerCase(), element);
		}
	}

	private final Set<Element> includedElements;
	private final Set<Element> excludedElements;

	public ElementalItemFilter(Set<Element> includedElements, Set<Element> excludedElements) {
		this.includedElements = includedElements;
		this.excludedElements = excludedElements;
		NotEnoughElementsMod.LOG.debug("Searching Elements: included {} excluded {}", includedElements, excludedElements);
	}

	public static ElementalItemFilter create(String[] matchingPatterns) {
		Set<Element> included = Sets.newHashSet();
		Set<Element> excluded = Sets.newHashSet();

		for(String pattern : matchingPatterns) {
			if(pattern.startsWith("!")) { // inverted(excluded)
				excluded.add(ELEMENT_LOOKUP.get(pattern.substring(1)));
			} else { // included
				included.add(ELEMENT_LOOKUP.get(pattern));
			}
		}

		return new ElementalItemFilter(included, excluded);
	}

	@Override
	public boolean matches(ItemStack itemStack) {
		if(includedElements.isEmpty()) return true;

		ItemData data = GT_OreDictUnificator.getItemData(itemStack);
		if(data == null) return false;

		Set<Element> c = GregTechUtil.getContainingElements(data);

		boolean includedMatched = c.containsAll(includedElements);
		boolean excludedMatched = c.stream().noneMatch(excludedElements::contains);

		return includedMatched && excludedMatched;
	}

}
