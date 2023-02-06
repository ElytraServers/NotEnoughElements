package cn.elytra.mod.nee;

import com.google.common.collect.Sets;
import gregtech.api.enums.Element;
import gregtech.api.enums.Materials;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;

import java.util.Set;

public class GregTechUtil {

	public static Set<Element> getContainingElements(ItemData data) {
		Set<Element> result = Sets.newHashSet();

		// check null
		if(data == null) return result;

		for(MaterialStack mStack : data.getAllMaterialStacks()) {
			readMaterialStackElements(result, mStack);
		}

		return result;
	}

	private static void readMaterialStackElements(Set<Element> result, MaterialStack materialStack) {
		Materials materials = materialStack.mMaterial;
		if(materials.mElement != null) {
			result.add(materials.mElement);
		}
		if(materials.mMaterialList.size() > 0) {
			for(MaterialStack stack : materials.mMaterialList) {
				readMaterialStackElements(result, stack);
			}
		}
	}

}
