package cn.elytra.mod.nee;

import com.google.common.collect.Sets;
import gregtech.api.enums.Element;
import gregtech.api.enums.Materials;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GTOreDictUnificator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Set;

public class GregTechUtil {

	public static Set<Element> getContainingElements(ItemStack stack) {
		Set<Element> result = Sets.newHashSet();

		readItemDataElements(result, GTOreDictUnificator.getItemData(stack));
		readContainingFluidElements(result, stack);

		return result;
	}

	@Deprecated
	public static Set<Element> getContainingElements(ItemData data) {
		Set<Element> result = Sets.newHashSet();

		// check null
		if(data == null) return result;

		for(MaterialStack mStack : data.getAllMaterialStacks()) {
			readMaterialStackElements(result, mStack);
		}

		return result;
	}

	private static void readItemDataElements(Set<Element> result, ItemData data) {
		if(data == null) return;

		for(MaterialStack mStack : data.getAllMaterialStacks()) {
			readMaterialStackElements(result, mStack);
		}
	}

	private static void readMaterialsElements(Set<Element> result, Materials materials) {
		if(materials == null) return;

		if(materials.mElement != null) {
			result.add(materials.mElement);
		}
		if(materials.mMaterialList.size() > 0) {
			for(MaterialStack stack : materials.mMaterialList) {
				readMaterialStackElements(result, stack);
			}
		}
	}

	private static void readMaterialStackElements(Set<Element> result, MaterialStack materialStack) {
		if(materialStack == null) return;

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

	private static void readContainingFluidElements(Set<Element> result, ItemStack stack) {
		if(stack == null) return;

		FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(stack);
		if(fluidStack != null) {
			Fluid fluid = fluidStack.getFluid();
			if(fluid != null) {
				Materials materials = Materials.getGtMaterialFromFluid(fluid);
				if(materials != null) {
					readMaterialsElements(result, materials);
				}
			}
		}
	}

}
