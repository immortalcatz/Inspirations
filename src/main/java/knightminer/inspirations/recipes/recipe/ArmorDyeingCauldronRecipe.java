package knightminer.inspirations.recipes.recipe;

import knightminer.inspirations.library.recipe.cauldron.ICauldronRecipe;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public enum ArmorDyeingCauldronRecipe implements ICauldronRecipe {
	INSTANCE;

	@Override
	public boolean matches(ItemStack stack, boolean boiling, int level, CauldronState state) {
		if(level == 0 || state.getType() != CauldronContents.DYE) {
			return false;
		}
		if(!(stack.getItem() instanceof ItemArmor)) {
			return false;
		}

		// only color leather, and ensure we are changing the color
		ItemArmor armor = (ItemArmor) stack.getItem();
		return armor.getArmorMaterial() == ArmorMaterial.LEATHER && armor.getColor(stack) != state.getColor();
	}

	@Override
	public ItemStack transformInput(ItemStack stack, boolean boiling, int level, CauldronState state) {
		((ItemArmor) stack.getItem()).setColor(stack, state.getColor());
		return stack;
	}

	@Override
	public int getLevel(int level) {
		return level - 1;
	}
}