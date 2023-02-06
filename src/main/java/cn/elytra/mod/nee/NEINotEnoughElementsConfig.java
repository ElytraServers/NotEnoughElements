package cn.elytra.mod.nee;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEINotEnoughElementsConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		NotEnoughElementsMod.LOG.info("Not Enough Elements Search Provider initialized");
		API.addSearchProvider(new ElementalFilterProvider());
	}

	@Override
	public String getName() {
		return Tags.MODNAME;
	}

	@Override
	public String getVersion() {
		return Tags.VERSION;
	}

}
