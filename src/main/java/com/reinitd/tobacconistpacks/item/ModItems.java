package com.reinitd.tobacconistpacks.item;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TobacconistPacks.MODID);

    // Registers as cigarette_pack_<cigarette>
    public static final List<String> CIGARETTES = List.of(
            "blank", "american_spirit_black", "american_spirit_light_blue",
            "camel_blue", "camel_crush", "camel_gold", "camel_royal", "chesterfield_filters",
            "ld_blue", "lm_blue", "lucky_strike_red", "marlboro_gold", "marlboro_red", "newport"
    );
    public static final Map<String, DeferredItem<CigarettePackItem>> CIGARETTE_PACKS = new LinkedHashMap<>();

    static {
        for (String cigarette : CIGARETTES) {
            CIGARETTE_PACKS.put(
                    cigarette,
                    ITEMS.registerItem("cigarette_pack_" + cigarette, CigarettePackItem::new, new Item.Properties().stacksTo(1))
            );
        }
    }

    // Registers as cigar_box_<wood>
    // cigar_box_<wood>_<empty|quarter|half|threequarter|full>.png
    public static final List<String> CIGAR_BOX_WOODS = List.of(
            "oak", "spruce", "birch", "jungle", "acacia", "darkoak", "mangrove", "cherry", "paleoak", "crimson", "warped", "chorus");
    public static final Map<String, DeferredItem<CigarBoxItem>> CIGAR_BOXES = new LinkedHashMap<>();

    static {
        for (String wood : CIGAR_BOX_WOODS) {
            Item.Properties properties = new Item.Properties().stacksTo(1);
            if (wood.equals("chorus")) {
                properties.rarity(Rarity.RARE);
            }
            CIGAR_BOXES.put(wood, ITEMS.registerItem("cigar_box_" + wood, CigarBoxItem::new, properties));
        }
    }
}
