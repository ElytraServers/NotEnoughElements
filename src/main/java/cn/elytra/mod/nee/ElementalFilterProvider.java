package cn.elytra.mod.nee;

import codechicken.nei.SearchField;
import codechicken.nei.api.ItemFilter;

public class ElementalFilterProvider implements SearchField.ISearchProvider {

	private static final String LONG_PREFIX = "element:";
	private static final String SHORT_PREFIX = "el:";

	@Override
	public boolean isPrimary() {
		return false;
	}

	@Override
	public ItemFilter getFilter(String s) {
		if(s.startsWith(LONG_PREFIX)) {
			return ElementalItemFilter.create(s.substring(LONG_PREFIX.length()).split("\\s+"));
		}
		if(s.startsWith(SHORT_PREFIX)) {
			return ElementalItemFilter.create(s.substring(SHORT_PREFIX.length()).split("\\s+"));
		}
		return null;
	}
}
