package com.reinitd.tobacconistpacks.item;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TobacconistPacks.MODID);
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_BLANK =
            ITEMS.registerItem("cigarette_pack_blank", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_AMERICAN_SPIRIT_BLACK =
            ITEMS.registerItem("cigarette_pack_american_spirit_black", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_AMERICAN_SPIRIT_LIGHT_BLUE =
            ITEMS.registerItem("cigarette_pack_american_spirit_light_blue", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_CAMEL_BLUE =
            ITEMS.registerItem("cigarette_pack_camel_blue", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_CAMEL_GOLD =
            ITEMS.registerItem("cigarette_pack_camel_gold", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_CAMEL_ROYAL =
            ITEMS.registerItem("cigarette_pack_camel_royal", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_CHESTERFIELD_FILTERS =
            ITEMS.registerItem("cigarette_pack_chesterfield_filters", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_LM_BLUE =
            ITEMS.registerItem("cigarette_pack_lm_blue", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_MARLBORO_GOLD =
            ITEMS.registerItem("cigarette_pack_marlboro_gold", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_MARLBORO_RED =
            ITEMS.registerItem("cigarette_pack_marlboro_red", CigarettePackItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<CigarettePackItem> CIGARETTE_PACK_NEWPORT =
            ITEMS.registerItem("cigarette_pack_newport", CigarettePackItem::new, new Item.Properties().stacksTo(1));

    public static final DeferredItem<CigarBoxItem> CIGAR_BOX_SPRUCE =
            ITEMS.registerItem("cigar_box_spruce", CigarBoxItem::new, new Item.Properties().stacksTo(1));
}
