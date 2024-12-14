package cn.elytra.mod.nee;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEINotEnoughElementsConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		NotEnoughElementsMod.LOG.info("Not Enough Elements Search Provider initialized");
		API.addSearchProvider(ElementalItemFilterV2.ELEMENTAL_SEARCH_PARSER_PROVIDER);
	}

	@Override
	public String getName() {
		return Tags.GRADLETOKEN_MODNAME;
	}

	@Override
	public String getVersion() {
		return Tags.GRADLETOKEN_VERSION;
	}

}
