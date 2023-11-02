package io.github.koufu193.core.game.data;

import java.util.Map;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import io.github.koufu193.core.game.data.block.BlockMeta;
import org.jglrxavpok.hephaistos.mca.BlockState;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockMetaConverter;

public class Material<meta extends BlockMeta>{

	private static final IBlockMetaConverter<BlockMeta> DEFAULT_CONVERTER = new DefaultBlockMetaConverter();
	private static final Map<String, Material<?>> ids = new HashMap<>();
	public static final Material<BlockMeta> GILDED_BLACKSTONE = new Material<>(new Identifier("gilded_blackstone"), 64,
			20126, 1174, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_BRICK_SLAB = new Material<>(new Identifier("nether_brick_slab"), 64,
			11134, 249, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_WARPED_FUNGUS = new Material<>(
			new Identifier("potted_warped_fungus"), 64, 19297, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PIGLIN_BRUTE_SPAWN_EGG = new Material<>(
			new Identifier("piglin_brute_spawn_egg"), 64, -1, 1003, false, true, null);
	public static final Material<BlockMeta> DRAGON_HEAD = new Material<>(new Identifier("dragon_head"), 64, 8923, 1058,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_DYE = new Material<>(new Identifier("green_dye"), 64, -1, 913, false,
			true, null);
	public static final Material<BlockMeta> DIAMOND_ORE = new Material<>(new Identifier("diamond_ore"), 64, 4270, 62,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_STAINED_GLASS_PANE = new Material<>(
			new Identifier("black_stained_glass_pane"), 64, 9739, 478, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_FENCE_GATE = new Material<>(new Identifier("mangrove_fence_gate"),
			64, 11364, 715, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SLIME_BALL = new Material<>(new Identifier("slime_ball"), 64, -1, 882,
			false, true, null);
	public static final Material<BlockMeta> RABBIT = new Material<>(new Identifier("rabbit"), 64, -1, 1068, false, true,
			null);
	public static final Material<BlockMeta> PARROT_SPAWN_EGG = new Material<>(new Identifier("parrot_spawn_egg"), 64,
			-1, 999, false, true, null);
	public static final Material<BlockMeta> WEATHERED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("weathered_cut_copper_slab"), 64, 21500, 91, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SKELETON_WALL_SKULL = new Material<>(new Identifier("skeleton_wall_skull"),
			64, 8839, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_HANGING_SIGN = new Material<>(new Identifier("acacia_hanging_sign"),
			16, 5055, 857, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_PRESSURE_PLATE = new Material<>(
			new Identifier("warped_pressure_plate"), 64, 18524, 683, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MULE_SPAWN_EGG = new Material<>(new Identifier("mule_spawn_egg"), 64, -1,
			996, false, true, null);
	public static final Material<BlockMeta> SUSPICIOUS_STEW = new Material<>(new Identifier("suspicious_stew"), 1, -1,
			1138, false, true, null);
	public static final Material<BlockMeta> MAGENTA_STAINED_GLASS_PANE = new Material<>(
			new Identifier("magenta_stained_glass_pane"), 64, 9323, 465, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_CONCRETE = new Material<>(new Identifier("light_blue_concrete"),
			64, 12575, 534, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LAPIS_ORE = new Material<>(new Identifier("lapis_ore"), 64, 516, 60, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HONEYCOMB = new Material<>(new Identifier("honeycomb"), 64, -1, 1164, false,
			true, null);
	public static final Material<BlockMeta> MANGROVE_SIGN = new Material<>(new Identifier("mangrove_sign"), 16, 4523,
			849, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WRITABLE_BOOK = new Material<>(new Identifier("writable_book"), 1, -1, 1042,
			false, true, null);
	public static final Material<BlockMeta> TRIPWIRE = new Material<>(new Identifier("tripwire"), 64, 7660, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE = new Material<>(new Identifier("redstone"), 64, -1, 632, false,
			true, null);
	public static final Material<BlockMeta> BAMBOO_WALL_HANGING_SIGN = new Material<>(
			new Identifier("bamboo_wall_hanging_sign"), 16, 5615, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TURTLE_SPAWN_EGG = new Material<>(new Identifier("turtle_spawn_egg"), 64,
			-1, 1025, false, true, null);
	public static final Material<BlockMeta> MILK_BUCKET = new Material<>(new Identifier("milk_bucket"), 1, -1, 870,
			false, true, null);
	public static final Material<BlockMeta> CYAN_TERRACOTTA = new Material<>(new Identifier("cyan_terracotta"), 64,
			9221, 412, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_WALL_SIGN = new Material<>(new Identifier("warped_wall_sign"), 16,
			19190, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_BOOTS = new Material<>(new Identifier("iron_boots"), 1, -1, 823, false,
			true, null);
	public static final Material<BlockMeta> BROWN_CONCRETE_POWDER = new Material<>(
			new Identifier("brown_concrete_powder"), 64, 12600, 559, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_STONE = new Material<>(new Identifier("end_stone"), 64, 7411, 353, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLISTERING_MELON_SLICE = new Material<>(
			new Identifier("glistering_melon_slice"), 64, -1, 962, false, true, null);
	public static final Material<BlockMeta> CHERRY_STAIRS = new Material<>(new Identifier("cherry_stairs"), 64, 9831,
			364, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_SPROUTS = new Material<>(new Identifier("nether_sprouts"), 64, 18436,
			216, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_CONCRETE = new Material<>(new Identifier("green_concrete"), 64, 12585,
			544, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_FENCE_GATE = new Material<>(new Identifier("bamboo_fence_gate"), 64,
			11396, 716, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_AXE = new Material<>(new Identifier("golden_axe"), 1, -1, 786, false,
			true, null);
	public static final Material<BlockMeta> WHITE_STAINED_GLASS_PANE = new Material<>(
			new Identifier("white_stained_glass_pane"), 64, 9259, 463, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("white_glazed_terracotta"), 64, 12508, 515, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TUBE_CORAL_WALL_FAN = new Material<>(new Identifier("tube_coral_wall_fan"),
			64, 12734, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_RABBIT = new Material<>(new Identifier("cooked_rabbit"), 64, -1,
			1069, false, true, null);
	public static final Material<BlockMeta> CRIMSON_SLAB = new Material<>(new Identifier("crimson_slab"), 64, 18512,
			238, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHARCOAL = new Material<>(new Identifier("charcoal"), 64, -1, 759, false,
			true, null);
	public static final Material<BlockMeta> NETHER_WART_BLOCK = new Material<>(new Identifier("nether_wart_block"), 64,
			12388, 493, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_STONE = new Material<>(new Identifier("infested_stone"), 64, 6540,
			309, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_CONCRETE_POWDER = new Material<>(
			new Identifier("light_gray_concrete_powder"), 64, 12596, 555, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_PORKCHOP = new Material<>(new Identifier("cooked_porkchop"), 64, -1,
			838, false, true, null);
	public static final Material<BlockMeta> BLACK_CANDLE = new Material<>(new Identifier("black_candle"), 64, 20825,
			1200, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE_ORE = new Material<>(new Identifier("redstone_ore"), 64, 5731, 56,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HORN_CORAL_FAN = new Material<>(new Identifier("horn_coral_fan"), 64, 12692,
			588, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_WARPED_HYPHAE = new Material<>(
			new Identifier("stripped_warped_hyphae"), 64, 18430, 141, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_CONCRETE = new Material<>(new Identifier("black_concrete"), 64, 12587,
			546, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_DEEPSLATE = new Material<>(new Identifier("chiseled_deepslate"),
			64, 23694, 326, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_CHISELED_STONE_BRICKS = new Material<>(
			new Identifier("infested_chiseled_stone_bricks"), 64, 6545, 314, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_BROWN_MUSHROOM = new Material<>(
			new Identifier("potted_brown_mushroom"), 64, 8588, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRUCTURE_BLOCK = new Material<>(new Identifier("structure_block"), 64,
			19198, 750, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STICKY_PISTON = new Material<>(new Identifier("sticky_piston"), 64, 1994,
			638, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_STAINED_GLASS = new Material<>(new Identifier("gray_stained_glass"),
			64, 5949, 454, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_SHULKER_BOX = new Material<>(
			new Identifier("light_gray_shulker_box"), 1, 12464, 507, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_BUTTON = new Material<>(new Identifier("dark_oak_button"), 64,
			8760, 664, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_AXE = new Material<>(new Identifier("netherite_axe"), 1, -1, 801,
			false, true, null);
	public static final Material<BlockMeta> CANDLE_CAKE = new Material<>(new Identifier("candle_cake"), 64, 20839, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SAND = new Material<>(new Identifier("sand"), 64, 112, 44, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_GRANITE_SLAB = new Material<>(
			new Identifier("polished_granite_slab"), 64, 13926, 614, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_DOOR = new Material<>(new Identifier("dark_oak_door"), 64, 12008,
			691, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_SPRUCE_SAPLING = new Material<>(
			new Identifier("potted_spruce_sapling"), 64, 8566, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEACON = new Material<>(new Identifier("beacon"), 64, 7914, 372, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EMERALD = new Material<>(new Identifier("emerald"), 64, -1, 761, false,
			true, null);
	public static final Material<BlockMeta> BLACKSTONE = new Material<>(new Identifier("blackstone"), 64, 19301, 1171,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_TUBE_CORAL_WALL_FAN = new Material<>(
			new Identifier("dead_tube_coral_wall_fan"), 64, 12694, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("pink_glazed_terracotta"), 64, 12532, 521, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LILAC = new Material<>(new Identifier("lilac"), 64, 10605, 442, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRACKED_DEEPSLATE_TILES = new Material<>(
			new Identifier("cracked_deepslate_tiles"), 64, 23696, 325, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SHEEP_SPAWN_EGG = new Material<>(new Identifier("sheep_spawn_egg"), 64, -1,
			1010, false, true, null);
	public static final Material<BlockMeta> SMALL_DRIPLEAF = new Material<>(new Identifier("small_dripleaf"), 64, 22030,
			226, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SOUL_TORCH = new Material<>(new Identifier("soul_torch"), 64, 5855, 307,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_BRICK_STAIRS = new Material<>(
			new Identifier("polished_blackstone_brick_stairs"), 64, 19733, 1181, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_FENCE = new Material<>(new Identifier("spruce_fence"), 64, 11452,
			288, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COAL_BLOCK = new Material<>(new Identifier("coal_block"), 64, 10600, 67,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_WHITE_TULIP = new Material<>(new Identifier("potted_white_tulip"),
			64, 8581, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOAT_HORN = new Material<>(new Identifier("goat_horn"), 1, -1, 1146, false,
			true, null);
	public static final Material<BlockMeta> BAMBOO_PRESSURE_PLATE = new Material<>(
			new Identifier("bamboo_pressure_plate"), 64, 5729, 681, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_CRIMSON_HYPHAE = new Material<>(
			new Identifier("stripped_crimson_hyphae"), 64, 18447, 140, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WOODEN_PICKAXE = new Material<>(new Identifier("wooden_pickaxe"), 1, -1,
			775, false, true, null);
	public static final Material<BlockMeta> DIAMOND_PICKAXE = new Material<>(new Identifier("diamond_pickaxe"), 1, -1,
			795, false, true, null);
	public static final Material<BlockMeta> FLOWER_POT = new Material<>(new Identifier("flower_pot"), 64, 8563, 1046,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_DYE = new Material<>(new Identifier("gray_dye"), 64, -1, 907, false,
			true, null);
	public static final Material<BlockMeta> BLACK_SHULKER_BOX = new Material<>(new Identifier("black_shulker_box"), 1,
			12506, 514, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OCELOT_SPAWN_EGG = new Material<>(new Identifier("ocelot_spawn_egg"), 64,
			-1, 997, false, true, null);
	public static final Material<BlockMeta> WAXED_EXPOSED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("waxed_exposed_cut_copper_stairs"), 64, 21694, 102, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_WALL = new Material<>(
			new Identifier("polished_blackstone_wall"), 64, 20242, 389, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FLOWERING_AZALEA_LEAVES = new Material<>(
			new Identifier("flowering_azalea_leaves"), 64, 512, 162, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_STAINED_GLASS = new Material<>(
			new Identifier("orange_stained_glass"), 64, 5943, 448, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_CONCRETE = new Material<>(new Identifier("magenta_concrete"), 64,
			12574, 533, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHAIN_COMMAND_BLOCK = new Material<>(new Identifier("chain_command_block"),
			64, 12377, 491, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WEEPING_VINES = new Material<>(new Identifier("weeping_vines"), 64, 18452,
			217, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_WALL_SIGN = new Material<>(new Identifier("jungle_wall_sign"), 16,
			4799, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SNOW_BLOCK = new Material<>(new Identifier("snow_block"), 64, 5777, 283,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_WALL_HANGING_SIGN = new Material<>(
			new Identifier("acacia_wall_hanging_sign"), 16, 5559, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_BRICKS = new Material<>(new Identifier("prismarine_bricks"), 64,
			10319, 480, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_SWORD = new Material<>(new Identifier("iron_sword"), 1, -1, 788, false,
			true, null);
	public static final Material<BlockMeta> LIME_WALL_BANNER = new Material<>(new Identifier("lime_wall_banner"), 16,
			10890, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_WOOD = new Material<>(new Identifier("spruce_wood"), 64, 189, 144,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBWEB = new Material<>(new Identifier("cobweb"), 64, 2000, 171, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLAZE_SPAWN_EGG = new Material<>(new Identifier("blaze_spawn_egg"), 64, -1,
			967, false, true, null);
	public static final Material<BlockMeta> CHERRY_LEAVES = new Material<>(new Identifier("cherry_leaves"), 64, 400,
			158, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ELYTRA = new Material<>(new Identifier("elytra"), 1, -1, 731, false, true,
			null);
	public static final Material<BlockMeta> ACACIA_FENCE_GATE = new Material<>(new Identifier("acacia_fence_gate"), 64,
			11268, 712, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("blue_glazed_terracotta"), 64, 12552, 526, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FLINT_AND_STEEL = new Material<>(new Identifier("flint_and_steel"), 1, -1,
			754, false, true, null);
	public static final Material<BlockMeta> TNT = new Material<>(new Identifier("tnt"), 64, 2091, 653, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_SHULKER_BOX = new Material<>(new Identifier("pink_shulker_box"), 1,
			12452, 505, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_DOOR = new Material<>(new Identifier("bamboo_door"), 64, 12136, 693,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_TERRACOTTA = new Material<>(new Identifier("blue_terracotta"), 64,
			9223, 414, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_PRISMARINE_SLAB = new Material<>(
			new Identifier("dark_prismarine_slab"), 64, 10576, 256, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CONDUIT = new Material<>(new Identifier("conduit"), 64, 12783, 595, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_INGOT = new Material<>(new Identifier("iron_ingot"), 64, -1, 766,
			false, true, null);
	public static final Material<BlockMeta> OAK_STAIRS = new Material<>(new Identifier("oak_stairs"), 64, 2881, 359,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PLAYER_HEAD = new Material<>(new Identifier("player_head"), 64, 8883, 1055,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEDROCK = new Material<>(new Identifier("bedrock"), 64, 79, 43, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_CANDLE_CAKE = new Material<>(new Identifier("brown_candle_cake"), 64,
			20865, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_CHERRY_SAPLING = new Material<>(
			new Identifier("potted_cherry_sapling"), 64, 8570, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_LAPIS_ORE = new Material<>(new Identifier("deepslate_lapis_ore"),
			64, 517, 61, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POISONOUS_POTATO = new Material<>(new Identifier("poisonous_potato"), 64,
			-1, 1050, false, true, null);
	public static final Material<BlockMeta> BLACK_DYE = new Material<>(new Identifier("black_dye"), 64, -1, 915, false,
			true, null);
	public static final Material<BlockMeta> MAGENTA_BANNER = new Material<>(new Identifier("magenta_banner"), 16, 10646,
			1085, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_WALL_SIGN = new Material<>(new Identifier("dark_oak_wall_sign"),
			16, 4807, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_CONCRETE_POWDER = new Material<>(
			new Identifier("green_concrete_powder"), 64, 12601, 560, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_PORTAL = new Material<>(new Identifier("end_portal"), 64, 7402, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_WALL_SIGN = new Material<>(new Identifier("birch_wall_sign"), 16,
			4775, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMITHING_TABLE = new Material<>(new Identifier("smithing_table"), 64, 18307,
			1154, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COMPARATOR = new Material<>(new Identifier("comparator"), 64, 9032, 636,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_SHULKER_BOX = new Material<>(new Identifier("gray_shulker_box"), 1,
			12458, 506, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_CONCRETE_POWDER = new Material<>(
			new Identifier("yellow_concrete_powder"), 64, 12592, 551, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACKSTONE_WALL = new Material<>(new Identifier("blackstone_wall"), 64,
			19385, 388, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COD = new Material<>(new Identifier("cod"), 64, -1, 891, false, true, null);
	public static final Material<BlockMeta> SMOOTH_STONE = new Material<>(new Identifier("smooth_stone"), 64, 11161,
			260, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_PRESSURE_PLATE = new Material<>(
			new Identifier("spruce_pressure_plate"), 64, 5715, 674, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_SAPLING = new Material<>(new Identifier("spruce_sapling"), 64, 27,
			36, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_FENCE = new Material<>(new Identifier("acacia_fence"), 64, 11548,
			291, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_WALL_SIGN = new Material<>(new Identifier("spruce_wall_sign"), 16,
			4767, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_ROOTS = new Material<>(new Identifier("warped_roots"), 64, 18435,
			215, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ARROW = new Material<>(new Identifier("arrow"), 64, -1, 757, false, true,
			null);
	public static final Material<BlockMeta> CRIMSON_HYPHAE = new Material<>(new Identifier("crimson_hyphae"), 64, 18444,
			151, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PEARLESCENT_FROGLIGHT = new Material<>(
			new Identifier("pearlescent_froglight"), 64, 23713, 1208, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_MUSHROOM = new Material<>(new Identifier("brown_mushroom"), 64, 2085,
			210, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUDDING_AMETHYST = new Material<>(new Identifier("budding_amethyst"), 64,
			20873, 72, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_RED_TULIP = new Material<>(new Identifier("potted_red_tulip"), 64,
			8579, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_NUGGET = new Material<>(new Identifier("iron_nugget"), 64, -1, 1114,
			false, true, null);
	public static final Material<BlockMeta> WALL_TORCH = new Material<>(new Identifier("wall_torch"), 64, 2352, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TERRACOTTA = new Material<>(new Identifier("terracotta"), 64, 10599, 438,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_WOOL = new Material<>(new Identifier("green_wool"), 64, 2056, 192,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_STAINED_GLASS_PANE = new Material<>(
			new Identifier("light_blue_stained_glass_pane"), 64, 9355, 466, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_LOG = new Material<>(new Identifier("oak_log"), 64, 127, 109, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SNOW = new Material<>(new Identifier("snow"), 64, 5768, 281, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_CARPET = new Material<>(new Identifier("magenta_carpet"), 64, 10585,
			424, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIG_DRIPLEAF = new Material<>(new Identifier("big_dripleaf"), 64, 21988,
			225, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRANITE_STAIRS = new Material<>(new Identifier("granite_stairs"), 64, 13534,
			605, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POWERED_RAIL = new Material<>(new Identifier("powered_rail"), 64, 1953, 719,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EMERALD_ORE = new Material<>(new Identifier("emerald_ore"), 64, 7507, 58,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_FENCE = new Material<>(new Identifier("mangrove_fence"), 64, 11644,
			294, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_SPRUCE_LOG = new Material<>(new Identifier("stripped_spruce_log"),
			64, 159, 123, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_RED_SANDSTONE = new Material<>(new Identifier("cut_red_sandstone"), 64,
			10936, 488, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHICKEN = new Material<>(new Identifier("chicken"), 64, -1, 945, false,
			true, null);
	public static final Material<BlockMeta> CRIMSON_STEM = new Material<>(new Identifier("crimson_stem"), 64, 18438,
			119, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_HORN_CORAL_BLOCK = new Material<>(
			new Identifier("dead_horn_coral_block"), 64, 12648, 568, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_BANNER = new Material<>(new Identifier("cyan_banner"), 16, 10758, 1092,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_DOOR = new Material<>(new Identifier("warped_door"), 64, 19064, 695,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_LILY_OF_THE_VALLEY = new Material<>(
			new Identifier("potted_lily_of_the_valley"), 64, 8585, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCULK_SENSOR = new Material<>(new Identifier("sculk_sensor"), 64, 20927,
			650, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BREWING_STAND = new Material<>(new Identifier("brewing_stand"), 64, 7393,
			959, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_BRICKS = new Material<>(new Identifier("stone_bricks"), 64, 6534, 316,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_OAK_WOOD = new Material<>(new Identifier("stripped_oak_wood"), 64,
			210, 132, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUBBLE_CORAL_FAN = new Material<>(new Identifier("bubble_coral_fan"), 64,
			12688, 586, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_BOAT = new Material<>(new Identifier("cherry_boat"), 1, -1, 742,
			false, true, null);
	public static final Material<BlockMeta> OAK_WALL_SIGN = new Material<>(new Identifier("oak_wall_sign"), 16, 4759,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FISHING_ROD = new Material<>(new Identifier("fishing_rod"), 1, -1, 887,
			false, true, null);
	public static final Material<BlockMeta> HORSE_SPAWN_EGG = new Material<>(new Identifier("horse_spawn_egg"), 64, -1,
			990, false, true, null);
	public static final Material<BlockMeta> HORN_CORAL_WALL_FAN = new Material<>(new Identifier("horn_coral_wall_fan"),
			64, 12766, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_PLANKS = new Material<>(new Identifier("cherry_planks"), 64, 20, 28,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAVAGER_SPAWN_EGG = new Material<>(new Identifier("ravager_spawn_egg"), 64,
			-1, 1008, false, true, null);
	public static final Material<BlockMeta> INFESTED_STONE_BRICKS = new Material<>(
			new Identifier("infested_stone_bricks"), 64, 6542, 311, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_CHEST_BOAT = new Material<>(new Identifier("spruce_chest_boat"), 1,
			-1, 735, false, true, null);
	public static final Material<BlockMeta> END_STONE_BRICK_SLAB = new Material<>(
			new Identifier("end_stone_brick_slab"), 64, 13956, 619, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEATHER_BOOTS = new Material<>(new Identifier("leather_boots"), 1, -1, 815,
			false, true, null);
	public static final Material<BlockMeta> LIGHT_BLUE_DYE = new Material<>(new Identifier("light_blue_dye"), 64, -1,
			903, false, true, null);
	public static final Material<BlockMeta> CHAINMAIL_HELMET = new Material<>(new Identifier("chainmail_helmet"), 1, -1,
			816, false, true, null);
	public static final Material<BlockMeta> OAK_SLAB = new Material<>(new Identifier("oak_slab"), 64, 11020, 228, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_DOOR = new Material<>(new Identifier("spruce_door"), 64, 11688, 686,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ZOMBIE_HEAD = new Material<>(new Identifier("zombie_head"), 64, 8863, 1056,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_TUBE_CORAL = new Material<>(new Identifier("dead_tube_coral"), 64,
			12654, 583, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HORN_CORAL = new Material<>(new Identifier("horn_coral"), 64, 12672, 578,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_CRYSTALS = new Material<>(new Identifier("prismarine_crystals"),
			64, -1, 1067, false, true, null);
	public static final Material<BlockMeta> WHITE_CONCRETE_POWDER = new Material<>(
			new Identifier("white_concrete_powder"), 64, 12588, 547, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_CHEST_BOAT = new Material<>(new Identifier("jungle_chest_boat"), 1,
			-1, 739, false, true, null);
	public static final Material<BlockMeta> CAVE_VINES_PLANT = new Material<>(new Identifier("cave_vines_plant"), 64,
			21965, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_WALL_BANNER = new Material<>(new Identifier("red_wall_banner"), 16,
			10926, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_SHULKER_BOX = new Material<>(new Identifier("yellow_shulker_box"), 1,
			12440, 503, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_WALL_BANNER = new Material<>(new Identifier("brown_wall_banner"), 16,
			10918, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GUNPOWDER = new Material<>(new Identifier("gunpowder"), 64, -1, 808, false,
			true, null);
	public static final Material<BlockMeta> PUFFERFISH_BUCKET = new Material<>(new Identifier("pufferfish_bucket"), 1,
			-1, 871, false, true, null);
	public static final Material<BlockMeta> GLOW_SQUID_SPAWN_EGG = new Material<>(
			new Identifier("glow_squid_spawn_egg"), 64, -1, 986, false, true, null);
	public static final Material<BlockMeta> BAMBOO = new Material<>(new Identifier("bamboo"), 64, 12786, 227, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_SAND = new Material<>(new Identifier("red_sand"), 64, 117, 46, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_SHULKER_BOX = new Material<>(new Identifier("purple_shulker_box"), 1,
			12476, 509, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CLAY = new Material<>(new Identifier("clay"), 64, 5794, 285, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_STONE_BRICKS = new Material<>(
			new Identifier("chiseled_stone_bricks"), 64, 6537, 319, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LECTERN = new Material<>(new Identifier("lectern"), 64, 18294, 645, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_HELMET = new Material<>(new Identifier("diamond_helmet"), 1, -1,
			824, false, true, null);
	public static final Material<BlockMeta> QUARTZ_BLOCK = new Material<>(new Identifier("quartz_block"), 64, 9091, 399,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_CHESTPLATE = new Material<>(new Identifier("diamond_chestplate"), 1,
			-1, 825, false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_BLOCKS = new Material<>(new Identifier("music_disc_blocks"), 1,
			-1, 1119, false, true, null);
	public static final Material<BlockMeta> HANGING_ROOTS = new Material<>(new Identifier("hanging_roots"), 64, 22044,
			224, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_STONE_BRICK_STAIRS = new Material<>(
			new Identifier("end_stone_brick_stairs"), 64, 13214, 601, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_BUTTON = new Material<>(new Identifier("cherry_button"), 64, 8736,
			663, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EXPOSED_COPPER = new Material<>(new Identifier("exposed_copper"), 64, 21163,
			78, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_TERRACOTTA = new Material<>(new Identifier("purple_terracotta"), 64,
			9222, 413, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MYCELIUM = new Material<>(new Identifier("mycelium"), 64, 7266, 340, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CALCITE = new Material<>(new Identifier("calcite"), 64, 20923, 11, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_BIRCH_LOG = new Material<>(new Identifier("stripped_birch_log"),
			64, 162, 124, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HAY_BLOCK = new Material<>(new Identifier("hay_block"), 64, 10581, 421,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_CONCRETE_POWDER = new Material<>(
			new Identifier("light_blue_concrete_powder"), 64, 12591, 550, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_SAPLING = new Material<>(new Identifier("bamboo_sapling"), 64, 12785,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ANDESITE_WALL = new Material<>(new Identifier("andesite_wall"), 64, 16596,
			383, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_CONCRETE = new Material<>(new Identifier("yellow_concrete"), 64,
			12576, 535, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_WEATHERED_CUT_COPPER = new Material<>(
			new Identifier("waxed_weathered_cut_copper"), 64, 21520, 99, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBBLESTONE_SLAB = new Material<>(new Identifier("cobblestone_slab"), 64,
			11110, 245, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRUSH = new Material<>(new Identifier("brush"), 1, -1, 1211, false, true,
			null);
	public static final Material<BlockMeta> GLASS = new Material<>(new Identifier("glass"), 64, 515, 165, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHEST = new Material<>(new Identifier("chest"), 64, 2951, 275, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_TRAPDOOR = new Material<>(new Identifier("warped_trapdoor"), 64,
			18668, 707, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCULK_SHRIEKER = new Material<>(new Identifier("sculk_shrieker"), 64, 21160,
			350, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FURNACE_MINECART = new Material<>(new Identifier("furnace_minecart"), 1, -1,
			726, false, true, null);
	public static final Material<BlockMeta> END_PORTAL_FRAME = new Material<>(new Identifier("end_portal_frame"), 64,
			7407, 352, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> KELP_PLANT = new Material<>(new Identifier("kelp_plant"), 64, 12630, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRINDSTONE = new Material<>(new Identifier("grindstone"), 64, 18283, 1153,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_COPPER_BLOCK = new Material<>(new Identifier("waxed_copper_block"),
			64, 21515, 93, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_LEAVES = new Material<>(new Identifier("dark_oak_leaves"), 64, 428,
			159, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_SAPLING = new Material<>(new Identifier("jungle_sapling"), 64, 31,
			38, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AMETHYST_BLOCK = new Material<>(new Identifier("amethyst_block"), 64, 20872,
			71, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SWEET_BERRY_BUSH = new Material<>(new Identifier("sweet_berry_bush"), 64,
			18416, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LAVA = new Material<>(new Identifier("lava"), 64, 96, -1, true, false,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_CARPET = new Material<>(new Identifier("black_carpet"), 64, 10598,
			437, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FIRE_CORAL = new Material<>(new Identifier("fire_coral"), 64, 12670, 577,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TRIDENT = new Material<>(new Identifier("trident"), 1, -1, 1133, false,
			true, null);
	public static final Material<BlockMeta> WET_SPONGE = new Material<>(new Identifier("wet_sponge"), 64, 514, 164,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_WOOL = new Material<>(new Identifier("yellow_wool"), 64, 2047, 183,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCULK = new Material<>(new Identifier("sculk"), 64, 21022, 347, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHICKEN_SPAWN_EGG = new Material<>(new Identifier("chicken_spawn_egg"), 64,
			-1, 971, false, true, null);
	public static final Material<BlockMeta> YELLOW_TERRACOTTA = new Material<>(new Identifier("yellow_terracotta"), 64,
			9216, 407, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGMA_BLOCK = new Material<>(new Identifier("magma_block"), 64, 12387, 492,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAULDRON = new Material<>(new Identifier("cauldron"), 64, 7394, 960, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_CONCRETE_POWDER = new Material<>(
			new Identifier("pink_concrete_powder"), 64, 12594, 553, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CROSSBOW = new Material<>(new Identifier("crossbow"), 1, -1, 1137, false,
			true, null);
	public static final Material<BlockMeta> SOUL_SAND = new Material<>(new Identifier("soul_sand"), 64, 5847, 302, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_SHOVEL = new Material<>(new Identifier("diamond_shovel"), 1, -1,
			794, false, true, null);
	public static final Material<BlockMeta> PRISMARINE_BRICK_STAIRS = new Material<>(
			new Identifier("prismarine_brick_stairs"), 64, 10412, 483, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EXPERIENCE_BOTTLE = new Material<>(new Identifier("experience_bottle"), 64,
			-1, 1040, false, true, null);
	public static final Material<BlockMeta> GOLDEN_HORSE_ARMOR = new Material<>(new Identifier("golden_horse_armor"), 1,
			-1, 1075, false, true, null);
	public static final Material<BlockMeta> ENDER_DRAGON_SPAWN_EGG = new Material<>(
			new Identifier("ender_dragon_spawn_egg"), 64, -1, 979, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE = new Material<>(new Identifier("deepslate"), 64, 22048, 8, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEE_NEST = new Material<>(new Identifier("bee_nest"), 64, 19238, 1165, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_RED_SANDSTONE_SLAB = new Material<>(
			new Identifier("smooth_red_sandstone_slab"), 64, 13932, 615, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MINECART = new Material<>(new Identifier("minecart"), 1, -1, 724, false,
			true, null);
	public static final Material<BlockMeta> DEAD_HORN_CORAL_FAN = new Material<>(new Identifier("dead_horn_coral_fan"),
			64, 12682, 593, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT = new Material<>(new Identifier("light"), 64, 10253, 420, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_CANDLE_CAKE = new Material<>(new Identifier("purple_candle_cake"),
			64, 20861, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_SANDSTONE_STAIRS = new Material<>(
			new Identifier("smooth_sandstone_stairs"), 64, 13374, 603, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_CANDLE_CAKE = new Material<>(new Identifier("yellow_candle_cake"),
			64, 20849, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_WOOL = new Material<>(new Identifier("blue_wool"), 64, 2054, 190, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POPPED_CHORUS_FRUIT = new Material<>(new Identifier("popped_chorus_fruit"),
			64, -1, 1101, false, true, null);
	public static final Material<BlockMeta> CREEPER_BANNER_PATTERN = new Material<>(
			new Identifier("creeper_banner_pattern"), 1, -1, 1141, false, true, null);
	public static final Material<BlockMeta> REDSTONE_WIRE = new Material<>(new Identifier("redstone_wire"), 64, 4134,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_EXPOSED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("waxed_exposed_cut_copper_slab"), 64, 21858, 106, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_DARK_OAK_LOG = new Material<>(
			new Identifier("stripped_dark_oak_log"), 64, 174, 128, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_WALL_HANGING_SIGN = new Material<>(
			new Identifier("cherry_wall_hanging_sign"), 16, 5567, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_BED = new Material<>(new Identifier("red_bed"), 1, 1911, 934, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BARREL = new Material<>(new Identifier("barrel"), 64, 18250, 1148, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHAIN = new Material<>(new Identifier("chain"), 64, 6773, 332, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BRAIN_CORAL_FAN = new Material<>(
			new Identifier("dead_brain_coral_fan"), 64, 12676, 590, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SLIME_BLOCK = new Material<>(new Identifier("slime_block"), 64, 10220, 639,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PUMPKIN_STEM = new Material<>(new Identifier("pumpkin_stem"), 64, 6817, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EMERALD_BLOCK = new Material<>(new Identifier("emerald_block"), 64, 7661,
			358, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_FENCE = new Material<>(new Identifier("oak_fence"), 64, 5844, 287, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TNT_MINECART = new Material<>(new Identifier("tnt_minecart"), 1, -1, 727,
			false, true, null);
	public static final Material<BlockMeta> ACACIA_PLANKS = new Material<>(new Identifier("acacia_planks"), 64, 19, 27,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTERY_SHARD_ARCHER = new Material<>(
			new Identifier("pottery_shard_archer"), 64, -1, 1224, false, true, null);
	public static final Material<BlockMeta> IRON_ORE = new Material<>(new Identifier("iron_ore"), 64, 121, 50, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLAZE_POWDER = new Material<>(new Identifier("blaze_powder"), 64, -1, 957,
			false, true, null);
	public static final Material<BlockMeta> DEAD_BUBBLE_CORAL_BLOCK = new Material<>(
			new Identifier("dead_bubble_coral_block"), 64, 12646, 566, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTERY_SHARD_ARMS_UP = new Material<>(
			new Identifier("pottery_shard_arms_up"), 64, -1, 1226, false, true, null);
	public static final Material<BlockMeta> SMOOTH_STONE_SLAB = new Material<>(new Identifier("smooth_stone_slab"), 64,
			11086, 241, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAW_IRON = new Material<>(new Identifier("raw_iron"), 64, -1, 765, false,
			true, null);
	public static final Material<BlockMeta> GOLDEN_SWORD = new Material<>(new Identifier("golden_sword"), 1, -1, 783,
			false, true, null);
	public static final Material<BlockMeta> NETHERITE_UPGRADE_SMITHING_TEMPLATE = new Material<>(
			new Identifier("netherite_upgrade_smithing_template"), 64, -1, 1212, false, true, null);
	public static final Material<BlockMeta> LIME_CONCRETE = new Material<>(new Identifier("lime_concrete"), 64, 12577,
			536, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_COBBLESTONE_STAIRS = new Material<>(
			new Identifier("mossy_cobblestone_stairs"), 64, 13134, 600, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_BLUE_ORCHID = new Material<>(new Identifier("potted_blue_orchid"),
			64, 8576, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_BLOCK = new Material<>(new Identifier("iron_block"), 64, 2088, 73,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_SIGN = new Material<>(new Identifier("birch_sign"), 16, 4363, 844,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_MANGROVE_LOG = new Material<>(
			new Identifier("stripped_mangrove_log"), 64, 180, 129, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_FENCE_GATE = new Material<>(new Identifier("warped_fence_gate"), 64,
			18756, 718, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUD_BRICKS = new Material<>(new Identifier("mud_bricks"), 64, 6539, 321,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_WARPED_STEM = new Material<>(
			new Identifier("stripped_warped_stem"), 64, 18424, 131, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_TERRACOTTA = new Material<>(new Identifier("brown_terracotta"), 64,
			9224, 415, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_FENCE = new Material<>(new Identifier("dark_oak_fence"), 64, 11612,
			293, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_WITHER_ROSE = new Material<>(new Identifier("potted_wither_rose"),
			64, 8586, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_TERRACOTTA = new Material<>(new Identifier("green_terracotta"), 64,
			9225, 416, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_HORSE_ARMOR = new Material<>(new Identifier("iron_horse_armor"), 1, -1,
			1074, false, true, null);
	public static final Material<BlockMeta> RED_CANDLE_CAKE = new Material<>(new Identifier("red_candle_cake"), 64,
			20869, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WATER = new Material<>(new Identifier("water"), 64, 80, -1, true, false,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_CONCRETE = new Material<>(new Identifier("white_concrete"), 64, 12572,
			531, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_WOOD = new Material<>(new Identifier("oak_wood"), 64, 186, 143, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLOW_LICHEN = new Material<>(new Identifier("glow_lichen"), 64, 6992, 336,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_SHULKER_BOX = new Material<>(new Identifier("red_shulker_box"), 1,
			12500, 513, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_TERRACOTTA = new Material<>(
			new Identifier("light_blue_terracotta"), 64, 9215, 406, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAT_SPAWN_EGG = new Material<>(new Identifier("cat_spawn_egg"), 64, -1, 968,
			false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_FAR = new Material<>(new Identifier("music_disc_far"), 1, -1,
			1121, false, true, null);
	public static final Material<BlockMeta> BROWN_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("brown_glazed_terracotta"), 64, 12556, 527, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NOTE_BLOCK = new Material<>(new Identifier("note_block"), 64, 535, 655,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRACKED_STONE_BRICKS = new Material<>(
			new Identifier("cracked_stone_bricks"), 64, 6536, 318, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPUR_STAIRS = new Material<>(new Identifier("purpur_stairs"), 64, 12280,
			273, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_COPPER = new Material<>(new Identifier("cut_copper"), 64, 21170, 81,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_SIGN = new Material<>(new Identifier("jungle_sign"), 16, 4459, 845,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("green_glazed_terracotta"), 64, 12560, 528, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCUTE = new Material<>(new Identifier("scute"), 64, -1, 753, false, true,
			null);
	public static final Material<BlockMeta> LIME_CANDLE_CAKE = new Material<>(new Identifier("lime_candle_cake"), 64,
			20851, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_CHESTPLATE = new Material<>(new Identifier("golden_chestplate"), 1,
			-1, 829, false, true, null);
	public static final Material<BlockMeta> GOLDEN_SHOVEL = new Material<>(new Identifier("golden_shovel"), 1, -1, 784,
			false, true, null);
	public static final Material<BlockMeta> SPRUCE_STAIRS = new Material<>(new Identifier("spruce_stairs"), 64, 7673,
			360, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_WOOL = new Material<>(new Identifier("gray_wool"), 64, 2050, 186, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_SAPLING = new Material<>(new Identifier("cherry_sapling"), 64, 35,
			40, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_STAINED_GLASS = new Material<>(new Identifier("white_stained_glass"),
			64, 5942, 447, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ANCIENT_DEBRIS = new Material<>(new Identifier("ancient_debris"), 64, 19289,
			66, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAVE_AIR = new Material<>(new Identifier("cave_air"), 64, 12800, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND = new Material<>(new Identifier("diamond"), 64, -1, 760, false,
			true, null);
	public static final Material<BlockMeta> MAGENTA_CANDLE = new Material<>(new Identifier("magenta_candle"), 64, 20617,
			1187, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPUR_SLAB = new Material<>(new Identifier("purpur_slab"), 64, 11158, 253,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIDER_SPAWN_EGG = new Material<>(new Identifier("strider_spawn_egg"), 64,
			-1, 1021, false, true, null);
	public static final Material<BlockMeta> DRAGON_BREATH = new Material<>(new Identifier("dragon_breath"), 64, -1,
			1106, false, true, null);
	public static final Material<BlockMeta> POLISHED_DIORITE = new Material<>(new Identifier("polished_diorite"), 64, 5,
			5, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEEF = new Material<>(new Identifier("beef"), 64, -1, 943, false, true,
			null);
	public static final Material<BlockMeta> BRICK_WALL = new Material<>(new Identifier("brick_wall"), 64, 14004, 375,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_FENCE_GATE = new Material<>(new Identifier("birch_fence_gate"), 64,
			11204, 710, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_CHIRP = new Material<>(new Identifier("music_disc_chirp"), 1, -1,
			1120, false, true, null);
	public static final Material<BlockMeta> DIRT_PATH = new Material<>(new Identifier("dirt_path"), 64, 12357, 440,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_PLANKS = new Material<>(new Identifier("dark_oak_planks"), 64, 21,
			29, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PHANTOM_MEMBRANE = new Material<>(new Identifier("phantom_membrane"), 64,
			-1, 1134, false, true, null);
	public static final Material<BlockMeta> WOODEN_SWORD = new Material<>(new Identifier("wooden_sword"), 1, -1, 773,
			false, true, null);
	public static final Material<BlockMeta> BAMBOO_HANGING_SIGN = new Material<>(new Identifier("bamboo_hanging_sign"),
			16, 5503, 861, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ALLIUM = new Material<>(new Identifier("allium"), 64, 2075, 198, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_PLANKS = new Material<>(new Identifier("mangrove_planks"), 64, 22,
			30, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_LEAVES = new Material<>(new Identifier("jungle_leaves"), 64, 344,
			156, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHORUS_PLANT = new Material<>(new Identifier("chorus_plant"), 64, 12258,
			269, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_DEEPSLATE = new Material<>(new Identifier("infested_deepslate"),
			64, 23698, 315, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUCKET = new Material<>(new Identifier("bucket"), 16, -1, 864, false, true,
			null);
	public static final Material<BlockMeta> WARPED_BUTTON = new Material<>(new Identifier("warped_button"), 64, 18974,
			668, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_HELMET = new Material<>(new Identifier("golden_helmet"), 1, -1, 828,
			false, true, null);
	public static final Material<BlockMeta> DARK_OAK_PRESSURE_PLATE = new Material<>(
			new Identifier("dark_oak_pressure_plate"), 64, 5725, 679, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WEATHERED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("weathered_cut_copper_stairs"), 64, 21262, 87, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_RED_SANDSTONE_SLAB = new Material<>(
			new Identifier("cut_red_sandstone_slab"), 64, 11152, 252, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CARTOGRAPHY_TABLE = new Material<>(new Identifier("cartography_table"), 64,
			18277, 1151, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SOUL_WALL_TORCH = new Material<>(new Identifier("soul_wall_torch"), 64,
			5856, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTERY_SHARD_PRIZE = new Material<>(new Identifier("pottery_shard_prize"),
			64, -1, 1225, false, true, null);
	public static final Material<BlockMeta> LIGHT_GRAY_DYE = new Material<>(new Identifier("light_gray_dye"), 64, -1,
			908, false, true, null);
	public static final Material<BlockMeta> POTTED_OXEYE_DAISY = new Material<>(new Identifier("potted_oxeye_daisy"),
			64, 8583, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DAYLIGHT_DETECTOR = new Material<>(new Identifier("daylight_detector"), 64,
			9063, 649, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SLIME_SPAWN_EGG = new Material<>(new Identifier("slime_spawn_egg"), 64, -1,
			1015, false, true, null);
	public static final Material<BlockMeta> GRASS = new Material<>(new Identifier("grass"), 64, 2001, 172, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_COAL_ORE = new Material<>(new Identifier("deepslate_coal_ore"),
			64, 124, 49, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_PICKAXE = new Material<>(new Identifier("stone_pickaxe"), 1, -1, 780,
			false, true, null);
	public static final Material<BlockMeta> IRON_HELMET = new Material<>(new Identifier("iron_helmet"), 1, -1, 820,
			false, true, null);
	public static final Material<BlockMeta> BLACK_WALL_BANNER = new Material<>(new Identifier("black_wall_banner"), 16,
			10930, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PISTON = new Material<>(new Identifier("piston"), 64, 2013, 637, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_FIRE_CORAL_BLOCK = new Material<>(
			new Identifier("dead_fire_coral_block"), 64, 12647, 567, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_CANDLE = new Material<>(new Identifier("cyan_candle"), 64, 20729, 1194,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_CUT_COPPER = new Material<>(new Identifier("waxed_cut_copper"), 64,
			21522, 97, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHTNING_ROD = new Material<>(new Identifier("lightning_rod"), 64, 21886,
			648, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_BED = new Material<>(new Identifier("brown_bed"), 1, 1879, 932, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_LOG = new Material<>(new Identifier("cherry_log"), 64, 142, 114,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAW_IRON_BLOCK = new Material<>(new Identifier("raw_iron_block"), 64, 23701,
			68, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JACK_O_LANTERN = new Material<>(new Identifier("jack_o_lantern"), 64, 5867,
			300, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_GRANITE = new Material<>(new Identifier("polished_granite"), 64, 3,
			3, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_RED_SANDSTONE = new Material<>(
			new Identifier("smooth_red_sandstone"), 64, 11164, 258, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_DEEPSLATE = new Material<>(new Identifier("polished_deepslate"),
			64, 22461, 10, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPORE_BLOSSOM = new Material<>(new Identifier("spore_blossom"), 64, 21966,
			209, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITHER_SPAWN_EGG = new Material<>(new Identifier("wither_spawn_egg"), 64,
			-1, 1032, false, true, null);
	public static final Material<BlockMeta> MANGROVE_ROOTS = new Material<>(new Identifier("mangrove_roots"), 64, 151,
			117, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DANDELION = new Material<>(new Identifier("dandelion"), 64, 2071, 195, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_BED = new Material<>(new Identifier("pink_bed"), 1, 1783, 926, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ZOMBIFIED_PIGLIN_SPAWN_EGG = new Material<>(
			new Identifier("zombified_piglin_spawn_egg"), 64, -1, 1039, false, true, null);
	public static final Material<BlockMeta> ROSE_BUSH = new Material<>(new Identifier("rose_bush"), 64, 10607, 443,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DROWNED_SPAWN_EGG = new Material<>(new Identifier("drowned_spawn_egg"), 64,
			-1, 977, false, true, null);
	public static final Material<BlockMeta> IRON_BARS = new Material<>(new Identifier("iron_bars"), 64, 6769, 331, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_RED_SANDSTONE_STAIRS = new Material<>(
			new Identifier("smooth_red_sandstone_stairs"), 64, 12894, 597, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> QUARTZ_BRICKS = new Material<>(new Identifier("quartz_bricks"), 64, 20565,
			400, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHEAT_SEEDS = new Material<>(new Identifier("wheat_seeds"), 64, -1, 809,
			false, true, null);
	public static final Material<BlockMeta> STONE_SHOVEL = new Material<>(new Identifier("stone_shovel"), 1, -1, 779,
			false, true, null);
	public static final Material<BlockMeta> BUBBLE_CORAL_BLOCK = new Material<>(new Identifier("bubble_coral_block"),
			64, 12651, 571, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ZOMBIE_HORSE_SPAWN_EGG = new Material<>(
			new Identifier("zombie_horse_spawn_egg"), 64, -1, 1037, false, true, null);
	public static final Material<BlockMeta> WAXED_OXIDIZED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("waxed_oxidized_cut_copper_slab"), 64, 21846, 108, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_BANNER = new Material<>(new Identifier("green_banner"), 16, 10822,
			1096, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_BANNER = new Material<>(new Identifier("light_gray_banner"), 16,
			10742, 1091, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_SWORD = new Material<>(new Identifier("diamond_sword"), 1, -1, 793,
			false, true, null);
	public static final Material<BlockMeta> NETHERITE_BLOCK = new Material<>(new Identifier("netherite_block"), 64,
			19288, 77, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAT_SPAWN_EGG = new Material<>(new Identifier("bat_spawn_egg"), 64, -1, 965,
			false, true, null);
	public static final Material<BlockMeta> GLOWSTONE_DUST = new Material<>(new Identifier("glowstone_dust"), 64, -1,
			890, false, true, null);
	public static final Material<BlockMeta> CRIMSON_FENCE_GATE = new Material<>(new Identifier("crimson_fence_gate"),
			64, 18724, 717, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DECORATED_POT = new Material<>(new Identifier("decorated_pot"), 1, 23718,
			264, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_CONCRETE = new Material<>(new Identifier("red_concrete"), 64, 12586,
			545, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("purple_glazed_terracotta"), 64, 12548, 525, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HUSK_SPAWN_EGG = new Material<>(new Identifier("husk_spawn_egg"), 64, -1,
			991, false, true, null);
	public static final Material<BlockMeta> ZOMBIE_VILLAGER_SPAWN_EGG = new Material<>(
			new Identifier("zombie_villager_spawn_egg"), 64, -1, 1038, false, true, null);
	public static final Material<BlockMeta> DISPENSER = new Material<>(new Identifier("dispenser"), 64, 520, 643, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_CRIMSON_STEM = new Material<>(
			new Identifier("stripped_crimson_stem"), 64, 18441, 130, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAW_GOLD_BLOCK = new Material<>(new Identifier("raw_gold_block"), 64, 23703,
			70, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_AXE = new Material<>(new Identifier("diamond_axe"), 1, -1, 796,
			false, true, null);
	public static final Material<BlockMeta> SOUL_LANTERN = new Material<>(new Identifier("soul_lantern"), 64, 18351,
			1158, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_WOOL = new Material<>(new Identifier("orange_wool"), 64, 2044, 180,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TURTLE_HELMET = new Material<>(new Identifier("turtle_helmet"), 1, -1, 752,
			false, true, null);
	public static final Material<BlockMeta> HEAVY_WEIGHTED_PRESSURE_PLATE = new Material<>(
			new Identifier("heavy_weighted_pressure_plate"), 64, 9015, 672, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_SANDSTONE_WALL = new Material<>(new Identifier("red_sandstone_wall"),
			64, 14652, 377, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_DOOR = new Material<>(new Identifier("crimson_door"), 64, 19000,
			694, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHIPPED_ANVIL = new Material<>(new Identifier("chipped_anvil"), 64, 8967,
			396, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_WALL_SIGN = new Material<>(new Identifier("acacia_wall_sign"), 16,
			4783, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_STAINED_GLASS_PANE = new Material<>(
			new Identifier("red_stained_glass_pane"), 64, 9707, 477, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_STAIRS = new Material<>(new Identifier("acacia_stairs"), 64, 9751,
			363, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DROPPER = new Material<>(new Identifier("dropper"), 64, 9201, 644, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_BRICK_SLAB = new Material<>(
			new Identifier("deepslate_brick_slab"), 64, 23367, 629, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_BANNER = new Material<>(new Identifier("light_blue_banner"), 16,
			10662, 1086, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_ALLIUM = new Material<>(new Identifier("potted_allium"), 64, 8577,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_WOOL = new Material<>(new Identifier("light_blue_wool"), 64,
			2046, 182, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_STAIRS = new Material<>(
			new Identifier("polished_blackstone_stairs"), 64, 20138, 1177, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_STAINED_GLASS = new Material<>(
			new Identifier("light_blue_stained_glass"), 64, 5945, 450, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITHER_SKELETON_WALL_SKULL = new Material<>(
			new Identifier("wither_skeleton_wall_skull"), 64, 8859, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAVE_VINES = new Material<>(new Identifier("cave_vines"), 64, 21913, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_SAPLING = new Material<>(new Identifier("acacia_sapling"), 64, 33,
			39, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_POLISHED_BLACKSTONE = new Material<>(
			new Identifier("chiseled_polished_blackstone"), 64, 19715, 1178, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("black_glazed_terracotta"), 64, 12568, 530, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKIE = new Material<>(new Identifier("cookie"), 64, -1, 936, false, true,
			null);
	public static final Material<BlockMeta> BOOKSHELF = new Material<>(new Identifier("bookshelf"), 64, 2092, 262, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_PRESSURE_PLATE = new Material<>(
			new Identifier("cherry_pressure_plate"), 64, 5723, 678, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_COPPER_SLAB = new Material<>(new Identifier("cut_copper_slab"), 64,
			21512, 89, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BELL = new Material<>(new Identifier("bell"), 64, 18313, 1156, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_MOSSY_STONE_BRICKS = new Material<>(
			new Identifier("infested_mossy_stone_bricks"), 64, 6543, 312, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POWDER_SNOW = new Material<>(new Identifier("powder_snow"), 1, 20925, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_FUNGUS = new Material<>(new Identifier("warped_fungus"), 64, 18433,
			213, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_BOAT = new Material<>(new Identifier("oak_boat"), 1, -1, 732, false,
			true, null);
	public static final Material<BlockMeta> LIGHT_GRAY_CANDLE_CAKE = new Material<>(
			new Identifier("light_gray_candle_cake"), 64, 20857, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NAUTILUS_SHELL = new Material<>(new Identifier("nautilus_shell"), 64, -1,
			1135, false, true, null);
	public static final Material<BlockMeta> POTTED_OAK_SAPLING = new Material<>(new Identifier("potted_oak_sapling"),
			64, 8565, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DETECTOR_RAIL = new Material<>(new Identifier("detector_rail"), 64, 1977,
			720, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CARROT_ON_A_STICK = new Material<>(new Identifier("carrot_on_a_stick"), 1,
			-1, 729, false, true, null);
	public static final Material<BlockMeta> SHIELD = new Material<>(new Identifier("shield"), 1, -1, 1111, false, true,
			null);
	public static final Material<BlockMeta> ANVIL = new Material<>(new Identifier("anvil"), 64, 8963, 395, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AZALEA_LEAVES = new Material<>(new Identifier("azalea_leaves"), 64, 484,
			161, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TOTEM_OF_UNDYING = new Material<>(new Identifier("totem_of_undying"), 1, -1,
			1112, false, true, null);
	public static final Material<BlockMeta> BAMBOO_MOSAIC_SLAB = new Material<>(new Identifier("bamboo_mosaic_slab"),
			64, 11074, 237, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_PICKAXE = new Material<>(new Identifier("netherite_pickaxe"), 1,
			-1, 800, false, true, null);
	public static final Material<BlockMeta> MOOSHROOM_SPAWN_EGG = new Material<>(new Identifier("mooshroom_spawn_egg"),
			64, -1, 995, false, true, null);
	public static final Material<BlockMeta> MAP = new Material<>(new Identifier("map"), 64, -1, 1051, false, true,
			null);
	public static final Material<BlockMeta> DARK_OAK_SIGN = new Material<>(new Identifier("dark_oak_sign"), 16, 4491,
			848, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_BOOTS = new Material<>(new Identifier("netherite_boots"), 1, -1,
			835, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_TILE_SLAB = new Material<>(new Identifier("deepslate_tile_slab"),
			64, 22956, 630, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_BUTTON = new Material<>(
			new Identifier("polished_blackstone_button"), 64, 20224, 657, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_REDSTONE_ORE = new Material<>(
			new Identifier("deepslate_redstone_ore"), 64, 5733, 57, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TORCH = new Material<>(new Identifier("torch"), 64, 2351, 267, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_TORCHFLOWER = new Material<>(new Identifier("potted_torchflower"),
			64, 8564, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_BUTTON = new Material<>(new Identifier("oak_button"), 64, 8616, 658,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRAY_SPAWN_EGG = new Material<>(new Identifier("stray_spawn_egg"), 64, -1,
			1020, false, true, null);
	public static final Material<BlockMeta> SPAWNER = new Material<>(new Identifier("spawner"), 64, 2869, 274, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_STONE_BRICKS = new Material<>(new Identifier("mossy_stone_bricks"),
			64, 6535, 317, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_DOOR = new Material<>(new Identifier("birch_door"), 64, 11752, 687,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_ACACIA_WOOD = new Material<>(
			new Identifier("stripped_acacia_wood"), 64, 222, 136, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COW_SPAWN_EGG = new Material<>(new Identifier("cow_spawn_egg"), 64, -1, 973,
			false, true, null);
	public static final Material<BlockMeta> SCULK_VEIN = new Material<>(new Identifier("sculk_vein"), 64, 21150, 348,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_13 = new Material<>(new Identifier("music_disc_13"), 1, -1, 1117,
			false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_11 = new Material<>(new Identifier("music_disc_11"), 1, -1, 1127,
			false, true, null);
	public static final Material<BlockMeta> BLUE_CONCRETE = new Material<>(new Identifier("blue_concrete"), 64, 12583,
			542, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WOODEN_AXE = new Material<>(new Identifier("wooden_axe"), 1, -1, 776, false,
			true, null);
	public static final Material<BlockMeta> BRAIN_CORAL = new Material<>(new Identifier("brain_coral"), 64, 12666, 575,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SHEARS = new Material<>(new Identifier("shears"), 1, -1, 938, false, true,
			null);
	public static final Material<BlockMeta> WARPED_FENCE = new Material<>(new Identifier("warped_fence"), 64, 18588,
			297, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("light_blue_glazed_terracotta"), 64, 12520, 518, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_STAINED_GLASS_PANE = new Material<>(
			new Identifier("cyan_stained_glass_pane"), 64, 9547, 472, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_QUARTZ_ORE = new Material<>(new Identifier("nether_quartz_ore"), 64,
			9080, 65, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENCHANTED_BOOK = new Material<>(new Identifier("enchanted_book"), 1, -1,
			1064, false, true, null);
	public static final Material<BlockMeta> POTTED_CRIMSON_ROOTS = new Material<>(
			new Identifier("potted_crimson_roots"), 64, 19298, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CARVED_PUMPKIN = new Material<>(new Identifier("carved_pumpkin"), 64, 5863,
			299, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WATER_CAULDRON = new Material<>(new Identifier("water_cauldron"), 64, 7395,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRANITE_WALL = new Material<>(new Identifier("granite_wall"), 64, 15300,
			379, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE_TORCH = new Material<>(new Identifier("redstone_torch"), 64, 5734,
			633, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SANDSTONE_WALL = new Material<>(new Identifier("sandstone_wall"), 64, 17244,
			385, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_GRANITE_STAIRS = new Material<>(
			new Identifier("polished_granite_stairs"), 64, 12814, 596, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLOW_BERRIES = new Material<>(new Identifier("glow_berries"), 64, -1, 1160,
			false, true, null);
	public static final Material<BlockMeta> DEAD_HORN_CORAL = new Material<>(new Identifier("dead_horn_coral"), 64,
			12662, 582, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_CONCRETE_POWDER = new Material<>(
			new Identifier("orange_concrete_powder"), 64, 12589, 548, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_HANGING_SIGN = new Material<>(
			new Identifier("dark_oak_hanging_sign"), 16, 5247, 859, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAMEL_SPAWN_EGG = new Material<>(new Identifier("camel_spawn_egg"), 64, -1,
			969, false, true, null);
	public static final Material<BlockMeta> DARK_OAK_FENCE_GATE = new Material<>(new Identifier("dark_oak_fence_gate"),
			64, 11332, 714, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COMPASS = new Material<>(new Identifier("compass"), 64, -1, 884, false,
			true, null);
	public static final Material<BlockMeta> WAXED_EXPOSED_CUT_COPPER = new Material<>(
			new Identifier("waxed_exposed_cut_copper"), 64, 21521, 98, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_QUARTZ_SLAB = new Material<>(new Identifier("smooth_quartz_slab"),
			64, 13968, 621, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_ACACIA_LOG = new Material<>(new Identifier("stripped_acacia_log"),
			64, 168, 126, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_ORANGE_TULIP = new Material<>(new Identifier("potted_orange_tulip"),
			64, 8580, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TADPOLE_SPAWN_EGG = new Material<>(new Identifier("tadpole_spawn_egg"), 64,
			-1, 1022, false, true, null);
	public static final Material<BlockMeta> MANGROVE_TRAPDOOR = new Material<>(new Identifier("mangrove_trapdoor"), 64,
			6421, 704, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE_BLOCK = new Material<>(new Identifier("redstone_block"), 64, 9079,
			634, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_COD = new Material<>(new Identifier("cooked_cod"), 64, -1, 895,
			false, true, null);
	public static final Material<BlockMeta> BONE_MEAL = new Material<>(new Identifier("bone_meal"), 64, -1, 916, false,
			true, null);
	public static final Material<BlockMeta> HONEYCOMB_BLOCK = new Material<>(new Identifier("honeycomb_block"), 64,
			19287, 1168, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_STAINED_GLASS = new Material<>(new Identifier("cyan_stained_glass"),
			64, 5951, 456, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_BAMBOO = new Material<>(new Identifier("potted_bamboo"), 64, 12798,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_CHESTPLATE = new Material<>(
			new Identifier("netherite_chestplate"), 1, -1, 833, false, true, null);
	public static final Material<BlockMeta> ORANGE_BED = new Material<>(new Identifier("orange_bed"), 1, 1703, 921,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PETRIFIED_OAK_SLAB = new Material<>(new Identifier("petrified_oak_slab"),
			64, 11104, 244, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_CANDLE_CAKE = new Material<>(new Identifier("cyan_candle_cake"), 64,
			20859, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUD_BRICK_WALL = new Material<>(new Identifier("mud_brick_wall"), 64, 15948,
			381, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PUMPKIN = new Material<>(new Identifier("pumpkin"), 64, 5845, 298, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TRAPPED_CHEST = new Material<>(new Identifier("trapped_chest"), 64, 8976,
			652, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_CONCRETE = new Material<>(new Identifier("orange_concrete"), 64,
			12573, 532, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PUMPKIN_SEEDS = new Material<>(new Identifier("pumpkin_seeds"), 64, -1, 941,
			false, true, null);
	public static final Material<BlockMeta> OCHRE_FROGLIGHT = new Material<>(new Identifier("ochre_froglight"), 64,
			23707, 1206, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_SAPLING = new Material<>(new Identifier("oak_sapling"), 64, 25, 35,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_STAINED_GLASS_PANE = new Material<>(
			new Identifier("brown_stained_glass_pane"), 64, 9643, 475, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_BED = new Material<>(new Identifier("white_bed"), 1, 1687, 920, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OBSERVER = new Material<>(new Identifier("observer"), 64, 12399, 641, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_PETALS = new Material<>(new Identifier("pink_petals"), 64, 21970, 222,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PODZOL = new Material<>(new Identifier("podzol"), 64, 13, 17, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_WOOL = new Material<>(new Identifier("cyan_wool"), 64, 2052, 188, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FIRE = new Material<>(new Identifier("fire"), 64, 2387, -1, true, false,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STICK = new Material<>(new Identifier("stick"), 64, -1, 803, false, true,
			null);
	public static final Material<BlockMeta> DIAMOND_BOOTS = new Material<>(new Identifier("diamond_boots"), 1, -1, 827,
			false, true, null);
	public static final Material<BlockMeta> FIRE_CORAL_BLOCK = new Material<>(new Identifier("fire_coral_block"), 64,
			12652, 572, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_CARPET = new Material<>(new Identifier("gray_carpet"), 64, 10590, 429,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COD_SPAWN_EGG = new Material<>(new Identifier("cod_spawn_egg"), 64, -1, 972,
			false, true, null);
	public static final Material<BlockMeta> GRAY_STAINED_GLASS_PANE = new Material<>(
			new Identifier("gray_stained_glass_pane"), 64, 9483, 470, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_CANDLE_CAKE = new Material<>(new Identifier("blue_candle_cake"), 64,
			20863, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_DYE = new Material<>(new Identifier("lime_dye"), 64, -1, 905, false,
			true, null);
	public static final Material<BlockMeta> ORANGE_WALL_BANNER = new Material<>(new Identifier("orange_wall_banner"),
			16, 10874, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_SIGN = new Material<>(new Identifier("acacia_sign"), 16, 4395, 846,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_BRICK_WALL = new Material<>(
			new Identifier("polished_blackstone_brick_wall"), 64, 19805, 390, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RABBIT_STEW = new Material<>(new Identifier("rabbit_stew"), 1, -1, 1070,
			false, true, null);
	public static final Material<BlockMeta> PEONY = new Material<>(new Identifier("peony"), 64, 10609, 444, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_AXE = new Material<>(new Identifier("stone_axe"), 1, -1, 781, false,
			true, null);
	public static final Material<BlockMeta> ROOTED_DIRT = new Material<>(new Identifier("rooted_dirt"), 64, 22045, 18,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POPPY = new Material<>(new Identifier("poppy"), 64, 2073, 196, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_SWORD = new Material<>(new Identifier("stone_sword"), 1, -1, 778,
			false, true, null);
	public static final Material<BlockMeta> CHEST_MINECART = new Material<>(new Identifier("chest_minecart"), 1, -1,
			725, false, true, null);
	public static final Material<BlockMeta> CRIMSON_HANGING_SIGN = new Material<>(
			new Identifier("crimson_hanging_sign"), 16, 5311, 862, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_TRAPDOOR = new Material<>(new Identifier("spruce_trapdoor"), 64,
			6037, 698, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CARROTS = new Material<>(new Identifier("carrots"), 64, 8591, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NAME_TAG = new Material<>(new Identifier("name_tag"), 64, -1, 1079, false,
			true, null);
	public static final Material<BlockMeta> MANGROVE_DOOR = new Material<>(new Identifier("mangrove_door"), 64, 12072,
			692, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RIB_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("rib_armor_trim_smithing_template"), 64, -1, 1222, false, true, null);
	public static final Material<BlockMeta> SPRUCE_LOG = new Material<>(new Identifier("spruce_log"), 64, 130, 110,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_TERRACOTTA = new Material<>(new Identifier("black_terracotta"), 64,
			9227, 418, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OXIDIZED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("oxidized_cut_copper_stairs"), 64, 21182, 88, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_TERRACOTTA = new Material<>(new Identifier("orange_terracotta"), 64,
			9213, 404, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ALLAY_SPAWN_EGG = new Material<>(new Identifier("allay_spawn_egg"), 64, -1,
			963, false, true, null);
	public static final Material<BlockMeta> SUSPICIOUS_SAND = new Material<>(new Identifier("suspicious_sand"), 64, 113,
			45, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SOUL_CAMPFIRE = new Material<>(new Identifier("soul_campfire"), 64, 18387,
			1162, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SALMON_BUCKET = new Material<>(new Identifier("salmon_bucket"), 1, -1, 872,
			false, true, null);
	public static final Material<BlockMeta> LIGHT_GRAY_TERRACOTTA = new Material<>(
			new Identifier("light_gray_terracotta"), 64, 9220, 411, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_SLAB = new Material<>(new Identifier("spruce_slab"), 64, 11026, 229,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_NETHER_BRICK_STAIRS = new Material<>(
			new Identifier("red_nether_brick_stairs"), 64, 13694, 607, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_CANDLE = new Material<>(new Identifier("yellow_candle"), 64, 20649,
			1189, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_TERRACOTTA = new Material<>(new Identifier("gray_terracotta"), 64,
			9219, 410, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ICE = new Material<>(new Identifier("ice"), 64, 5776, 282, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_SHULKER_BOX = new Material<>(new Identifier("blue_shulker_box"), 1,
			12482, 510, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_AXE = new Material<>(new Identifier("iron_axe"), 1, -1, 791, false,
			true, null);
	public static final Material<BlockMeta> LOOM = new Material<>(new Identifier("loom"), 64, 18245, 1139, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TORCHFLOWER = new Material<>(new Identifier("torchflower"), 64, 2072, 208,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE = new Material<>(new Identifier("polished_blackstone"),
			64, 19712, 1175, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_LEAVES = new Material<>(new Identifier("oak_leaves"), 64, 260, 153,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBBLED_DEEPSLATE_SLAB = new Material<>(
			new Identifier("cobbled_deepslate_slab"), 64, 22134, 627, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COMPOSTER = new Material<>(new Identifier("composter"), 64, 19213, 1147,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUTTON = new Material<>(new Identifier("mutton"), 64, -1, 1081, false, true,
			null);
	public static final Material<BlockMeta> COPPER_ORE = new Material<>(new Identifier("copper_ore"), 64, 21165, 52,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> KNOWLEDGE_BOOK = new Material<>(new Identifier("knowledge_book"), 1, -1,
			1115, false, true, null);
	public static final Material<BlockMeta> WHITE_WALL_BANNER = new Material<>(new Identifier("white_wall_banner"), 16,
			10870, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OBSIDIAN = new Material<>(new Identifier("obsidian"), 64, 2350, 266, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_CARPET = new Material<>(new Identifier("cyan_carpet"), 64, 10592, 431,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_HORN_CORAL_WALL_FAN = new Material<>(
			new Identifier("dead_horn_coral_wall_fan"), 64, 12726, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_MELLOHI = new Material<>(new Identifier("music_disc_mellohi"), 1,
			-1, 1123, false, true, null);
	public static final Material<BlockMeta> POTTED_CORNFLOWER = new Material<>(new Identifier("potted_cornflower"), 64,
			8584, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COARSE_DIRT = new Material<>(new Identifier("coarse_dirt"), 64, 11, 16,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_PRISMARINE_STAIRS = new Material<>(
			new Identifier("dark_prismarine_stairs"), 64, 10492, 484, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_BUTTON = new Material<>(new Identifier("jungle_button"), 64, 8688,
			661, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_TUBE_CORAL_BLOCK = new Material<>(
			new Identifier("dead_tube_coral_block"), 64, 12644, 564, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTATOES = new Material<>(new Identifier("potatoes"), 64, 8599, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_MUTTON = new Material<>(new Identifier("cooked_mutton"), 64, -1,
			1082, false, true, null);
	public static final Material<BlockMeta> JUNGLE_FENCE = new Material<>(new Identifier("jungle_fence"), 64, 11516,
			290, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUKEBOX = new Material<>(new Identifier("jukebox"), 64, 5812, 286, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_WALL_BANNER = new Material<>(new Identifier("green_wall_banner"), 16,
			10922, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_STAINED_GLASS_PANE = new Material<>(
			new Identifier("purple_stained_glass_pane"), 64, 9579, 473, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_TRAPDOOR = new Material<>(new Identifier("birch_trapdoor"), 64, 6101,
			699, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> APPLE = new Material<>(new Identifier("apple"), 64, -1, 755, false, true,
			null);
	public static final Material<BlockMeta> ELDER_GUARDIAN_SPAWN_EGG = new Material<>(
			new Identifier("elder_guardian_spawn_egg"), 64, -1, 978, false, true, null);
	public static final Material<BlockMeta> SPIDER_EYE = new Material<>(new Identifier("spider_eye"), 64, -1, 955,
			false, true, null);
	public static final Material<BlockMeta> ZOGLIN_SPAWN_EGG = new Material<>(new Identifier("zoglin_spawn_egg"), 64,
			-1, 1035, false, true, null);
	public static final Material<BlockMeta> PIGLIN_BANNER_PATTERN = new Material<>(
			new Identifier("piglin_banner_pattern"), 1, -1, 1145, false, true, null);
	public static final Material<BlockMeta> POTTED_DEAD_BUSH = new Material<>(new Identifier("potted_dead_bush"), 64,
			8589, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LILY_OF_THE_VALLEY = new Material<>(new Identifier("lily_of_the_valley"),
			64, 2084, 206, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PUMPKIN_PIE = new Material<>(new Identifier("pumpkin_pie"), 64, -1, 1061,
			false, true, null);
	public static final Material<BlockMeta> CHERRY_SLAB = new Material<>(new Identifier("cherry_slab"), 64, 11050, 233,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_SANDSTONE_STAIRS = new Material<>(
			new Identifier("red_sandstone_stairs"), 64, 10948, 489, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRAFTING_TABLE = new Material<>(new Identifier("crafting_table"), 64, 4273,
			276, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAVE_SPIDER_SPAWN_EGG = new Material<>(
			new Identifier("cave_spider_spawn_egg"), 64, -1, 970, false, true, null);
	public static final Material<BlockMeta> COBBLESTONE_STAIRS = new Material<>(new Identifier("cobblestone_stairs"),
			64, 4689, 280, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_MUSHROOM_BLOCK = new Material<>(
			new Identifier("brown_mushroom_block"), 64, 6546, 328, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_BLOCK = new Material<>(new Identifier("diamond_block"), 64, 4272,
			76, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_LOG = new Material<>(new Identifier("mangrove_log"), 64, 148, 116,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_STONE_BRICKS = new Material<>(new Identifier("end_stone_bricks"), 64,
			12349, 354, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_BRICK_WALL = new Material<>(new Identifier("nether_brick_wall"), 64,
			16272, 382, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_CACTUS = new Material<>(new Identifier("potted_cactus"), 64, 8590,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENCHANTED_GOLDEN_APPLE = new Material<>(
			new Identifier("enchanted_golden_apple"), 64, -1, 841, false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_CAT = new Material<>(new Identifier("music_disc_cat"), 1, -1,
			1118, false, true, null);
	public static final Material<BlockMeta> PURPLE_DYE = new Material<>(new Identifier("purple_dye"), 64, -1, 910,
			false, true, null);
	public static final Material<BlockMeta> FIRE_CHARGE = new Material<>(new Identifier("fire_charge"), 64, -1, 1041,
			false, true, null);
	public static final Material<BlockMeta> TUBE_CORAL_BLOCK = new Material<>(new Identifier("tube_coral_block"), 64,
			12649, 569, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SANDSTONE_STAIRS = new Material<>(new Identifier("sandstone_stairs"), 64,
			7438, 356, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POWDER_SNOW_BUCKET = new Material<>(new Identifier("powder_snow_bucket"), 1,
			-1, 867, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_BRICK_STAIRS = new Material<>(
			new Identifier("deepslate_brick_stairs"), 64, 23295, 612, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FERN = new Material<>(new Identifier("fern"), 64, 2002, 173, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SKELETON_SPAWN_EGG = new Material<>(new Identifier("skeleton_spawn_egg"),
			64, -1, 1013, false, true, null);
	public static final Material<BlockMeta> PUFFERFISH_SPAWN_EGG = new Material<>(
			new Identifier("pufferfish_spawn_egg"), 64, -1, 1006, false, true, null);
	public static final Material<BlockMeta> GOAT_SPAWN_EGG = new Material<>(new Identifier("goat_spawn_egg"), 64, -1,
			987, false, true, null);
	public static final Material<BlockMeta> LIGHT_BLUE_CARPET = new Material<>(new Identifier("light_blue_carpet"), 64,
			10586, 425, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIORITE_SLAB = new Material<>(new Identifier("diorite_slab"), 64, 13998,
			626, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SOUL_SOIL = new Material<>(new Identifier("soul_soil"), 64, 5848, 303, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("lime_glazed_terracotta"), 64, 12528, 520, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MEDIUM_AMETHYST_BUD = new Material<>(new Identifier("medium_amethyst_bud"),
			64, 20907, 1202, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_BRICK_STAIRS = new Material<>(new Identifier("nether_brick_stairs"),
			64, 7312, 346, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LAVA_CAULDRON = new Material<>(new Identifier("lava_cauldron"), 64, 7398,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_FIRE_CORAL_WALL_FAN = new Material<>(
			new Identifier("dead_fire_coral_wall_fan"), 64, 12718, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> VILLAGER_SPAWN_EGG = new Material<>(new Identifier("villager_spawn_egg"),
			64, -1, 1027, false, true, null);
	public static final Material<BlockMeta> CHERRY_FENCE_GATE = new Material<>(new Identifier("cherry_fence_gate"), 64,
			11300, 713, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SNOWBALL = new Material<>(new Identifier("snowball"), 16, -1, 868, false,
			true, null);
	public static final Material<BlockMeta> BUBBLE_CORAL_WALL_FAN = new Material<>(
			new Identifier("bubble_coral_wall_fan"), 64, 12750, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_QUARTZ_STAIRS = new Material<>(
			new Identifier("smooth_quartz_stairs"), 64, 13454, 604, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_BRICK_WALL = new Material<>(new Identifier("stone_brick_wall"), 64,
			15624, 380, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLASS_BOTTLE = new Material<>(new Identifier("glass_bottle"), 64, -1, 954,
			false, true, null);
	public static final Material<BlockMeta> RESPAWN_ANCHOR = new Material<>(new Identifier("respawn_anchor"), 64, 19291,
			1183, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_BANNER = new Material<>(new Identifier("red_banner"), 16, 10838, 1097,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEE_SPAWN_EGG = new Material<>(new Identifier("bee_spawn_egg"), 64, -1, 966,
			false, true, null);
	public static final Material<BlockMeta> VERDANT_FROGLIGHT = new Material<>(new Identifier("verdant_froglight"), 64,
			23710, 1207, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SNOW_GOLEM_SPAWN_EGG = new Material<>(
			new Identifier("snow_golem_spawn_egg"), 64, -1, 1017, false, true, null);
	public static final Material<BlockMeta> BAMBOO_SIGN = new Material<>(new Identifier("bamboo_sign"), 16, 4555, 850,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TIPPED_ARROW = new Material<>(new Identifier("tipped_arrow"), 64, -1, 1109,
			false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_OTHERSIDE = new Material<>(
			new Identifier("music_disc_otherside"), 1, -1, 1129, false, true, null);
	public static final Material<BlockMeta> ORANGE_CANDLE = new Material<>(new Identifier("orange_candle"), 64, 20601,
			1186, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_STAINED_GLASS = new Material<>(
			new Identifier("yellow_stained_glass"), 64, 5946, 451, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_WARPED_ROOTS = new Material<>(new Identifier("potted_warped_roots"),
			64, 19299, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENDER_PEARL = new Material<>(new Identifier("ender_pearl"), 16, -1, 948,
			false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_DIAMOND_ORE = new Material<>(
			new Identifier("deepslate_diamond_ore"), 64, 4271, 63, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_SLAB = new Material<>(new Identifier("prismarine_slab"), 64,
			10564, 254, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DRIED_KELP = new Material<>(new Identifier("dried_kelp"), 64, -1, 940,
			false, true, null);
	public static final Material<BlockMeta> BIG_DRIPLEAF_STEM = new Material<>(new Identifier("big_dripleaf_stem"), 64,
			22020, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_SHARD = new Material<>(new Identifier("prismarine_shard"), 64,
			-1, 1066, false, true, null);
	public static final Material<BlockMeta> LAPIS_BLOCK = new Material<>(new Identifier("lapis_block"), 64, 518, 167,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_WOOL = new Material<>(new Identifier("magenta_wool"), 64, 2045, 181,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_FENCE_GATE = new Material<>(new Identifier("oak_fence_gate"), 64, 7000,
			708, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_JUNGLE_WOOD = new Material<>(
			new Identifier("stripped_jungle_wood"), 64, 219, 135, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_DOOR = new Material<>(new Identifier("iron_door"), 64, 5659, 684, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_BOAT = new Material<>(new Identifier("spruce_boat"), 1, -1, 734,
			false, true, null);
	public static final Material<BlockMeta> CHERRY_WOOD = new Material<>(new Identifier("cherry_wood"), 64, 201, 148,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_QUARTZ = new Material<>(new Identifier("smooth_quartz"), 64, 11163,
			257, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("tide_armor_trim_smithing_template"), 64, -1, 1220, false, true, null);
	public static final Material<BlockMeta> GRAY_CANDLE = new Material<>(new Identifier("gray_candle"), 64, 20697, 1192,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_HANGING_SIGN = new Material<>(
			new Identifier("mangrove_hanging_sign"), 16, 5439, 860, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_WOOL = new Material<>(new Identifier("pink_wool"), 64, 2049, 185, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITHER_ROSE = new Material<>(new Identifier("wither_rose"), 64, 2083, 207,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("light_gray_glazed_terracotta"), 64, 12540, 523, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_JUNGLE_SAPLING = new Material<>(
			new Identifier("potted_jungle_sapling"), 64, 8568, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ITEM_FRAME = new Material<>(new Identifier("item_frame"), 64, -1, 1044,
			false, true, null);
	public static final Material<BlockMeta> RED_TULIP = new Material<>(new Identifier("red_tulip"), 64, 2077, 200, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_SAPLING = new Material<>(new Identifier("birch_sapling"), 64, 29, 37,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_ANDESITE = new Material<>(new Identifier("polished_andesite"), 64,
			7, 7, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUD = new Material<>(new Identifier("mud"), 64, 22046, 19, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SHROOMLIGHT = new Material<>(new Identifier("shroomlight"), 64, 18451, 1163,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPIDER_SPAWN_EGG = new Material<>(new Identifier("spider_spawn_egg"), 64,
			-1, 1018, false, true, null);
	public static final Material<BlockMeta> TRIPWIRE_HOOK = new Material<>(new Identifier("tripwire_hook"), 64, 7526,
			651, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("spire_armor_trim_smithing_template"), 64, -1, 1223, false, true, null);
	public static final Material<BlockMeta> POTTED_AZURE_BLUET = new Material<>(new Identifier("potted_azure_bluet"),
			64, 8578, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PAPER = new Material<>(new Identifier("paper"), 64, -1, 880, false, true,
			null);
	public static final Material<BlockMeta> LIGHT_BLUE_WALL_BANNER = new Material<>(
			new Identifier("light_blue_wall_banner"), 16, 10882, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_DEEPSLATE_WALL = new Material<>(
			new Identifier("polished_deepslate_wall"), 64, 22551, 392, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_LOG = new Material<>(new Identifier("birch_log"), 64, 133, 111, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_SIGN = new Material<>(new Identifier("warped_sign"), 16, 19150, 852,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PUFFERFISH = new Material<>(new Identifier("pufferfish"), 64, -1, 894,
			false, true, null);
	public static final Material<BlockMeta> OAK_PLANKS = new Material<>(new Identifier("oak_planks"), 64, 15, 23, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLD_ORE = new Material<>(new Identifier("gold_ore"), 64, 119, 54, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAW_GOLD = new Material<>(new Identifier("raw_gold"), 64, -1, 769, false,
			true, null);
	public static final Material<BlockMeta> FERMENTED_SPIDER_EYE = new Material<>(
			new Identifier("fermented_spider_eye"), 64, -1, 956, false, true, null);
	public static final Material<BlockMeta> MAGENTA_WALL_BANNER = new Material<>(new Identifier("magenta_wall_banner"),
			16, 10878, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_QUARTZ_BLOCK = new Material<>(
			new Identifier("chiseled_quartz_block"), 64, 9092, 398, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FLOWERING_AZALEA = new Material<>(new Identifier("flowering_azalea"), 64,
			21968, 175, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_DYE = new Material<>(new Identifier("cyan_dye"), 64, -1, 909, false,
			true, null);
	public static final Material<BlockMeta> QUARTZ_SLAB = new Material<>(new Identifier("quartz_slab"), 64, 11140, 250,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FIRE_CORAL_WALL_FAN = new Material<>(new Identifier("fire_coral_wall_fan"),
			64, 12758, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_MUSHROOM = new Material<>(new Identifier("red_mushroom"), 64, 2086, 211,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_COBBLESTONE = new Material<>(
			new Identifier("infested_cobblestone"), 64, 6541, 310, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CARROT = new Material<>(new Identifier("carrot"), 64, -1, 1047, false, true,
			null);
	public static final Material<BlockMeta> ANDESITE_SLAB = new Material<>(new Identifier("andesite_slab"), 64, 13980,
			623, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EGG = new Material<>(new Identifier("egg"), 16, -1, 883, false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_STAL = new Material<>(new Identifier("music_disc_stal"), 1, -1,
			1124, false, true, null);
	public static final Material<BlockMeta> BIRCH_STAIRS = new Material<>(new Identifier("birch_stairs"), 64, 7753, 361,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_SIGN = new Material<>(new Identifier("spruce_sign"), 16, 4331, 843,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HONEY_BLOCK = new Material<>(new Identifier("honey_block"), 64, 19286, 640,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEBUG_STICK = new Material<>(new Identifier("debug_stick"), 1, -1, 1116,
			false, true, null);
	public static final Material<BlockMeta> RECOVERY_COMPASS = new Material<>(new Identifier("recovery_compass"), 64,
			-1, 885, false, true, null);
	public static final Material<BlockMeta> SPRUCE_FENCE_GATE = new Material<>(new Identifier("spruce_fence_gate"), 64,
			11172, 709, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AZURE_BLUET = new Material<>(new Identifier("azure_bluet"), 64, 2076, 199,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AMETHYST_CLUSTER = new Material<>(new Identifier("amethyst_cluster"), 64,
			20883, 1204, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_BRICK_SLAB = new Material<>(
			new Identifier("prismarine_brick_slab"), 64, 10570, 255, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DRAGON_EGG = new Material<>(new Identifier("dragon_egg"), 64, 7412, 355,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_STAINED_GLASS_PANE = new Material<>(
			new Identifier("light_gray_stained_glass_pane"), 64, 9515, 471, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCAFFOLDING = new Material<>(new Identifier("scaffolding"), 64, 18244, 631,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_RED_MUSHROOM = new Material<>(new Identifier("potted_red_mushroom"),
			64, 8587, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_BUTTON = new Material<>(new Identifier("mangrove_button"), 64,
			8784, 665, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LARGE_FERN = new Material<>(new Identifier("large_fern"), 64, 10613, 446,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COCOA = new Material<>(new Identifier("cocoa"), 64, 7415, -1, true, false,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_BED = new Material<>(new Identifier("light_blue_bed"), 1, 1735,
			923, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_PRESSURE_PLATE = new Material<>(
			new Identifier("birch_pressure_plate"), 64, 5717, 675, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_MOSAIC = new Material<>(new Identifier("bamboo_mosaic"), 64, 24, 34,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLD_BLOCK = new Material<>(new Identifier("gold_block"), 64, 2087, 75,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DRIPSTONE_BLOCK = new Material<>(new Identifier("dripstone_block"), 64,
			21911, 13, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_LOG = new Material<>(new Identifier("acacia_log"), 64, 139, 113,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TROPICAL_FISH_SPAWN_EGG = new Material<>(
			new Identifier("tropical_fish_spawn_egg"), 64, -1, 1024, false, true, null);
	public static final Material<BlockMeta> ZOMBIE_SPAWN_EGG = new Material<>(new Identifier("zombie_spawn_egg"), 64,
			-1, 1036, false, true, null);
	public static final Material<BlockMeta> GLOW_ITEM_FRAME = new Material<>(new Identifier("glow_item_frame"), 64, -1,
			1045, false, true, null);
	public static final Material<BlockMeta> WHITE_DYE = new Material<>(new Identifier("white_dye"), 64, -1, 900, false,
			true, null);
	public static final Material<BlockMeta> POTTED_CRIMSON_FUNGUS = new Material<>(
			new Identifier("potted_crimson_fungus"), 64, 19296, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("sentry_armor_trim_smithing_template"), 64, -1, 1213, false, true, null);
	public static final Material<BlockMeta> BONE_BLOCK = new Material<>(new Identifier("bone_block"), 64, 12391, 496,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_TUBE_CORAL_FAN = new Material<>(new Identifier("dead_tube_coral_fan"),
			64, 12674, 589, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_FENCE = new Material<>(new Identifier("birch_fence"), 64, 11484, 289,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_STAIRS = new Material<>(new Identifier("prismarine_stairs"), 64,
			10332, 482, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ATTACHED_PUMPKIN_STEM = new Material<>(
			new Identifier("attached_pumpkin_stem"), 64, 6809, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_HANGING_SIGN = new Material<>(new Identifier("jungle_hanging_sign"),
			16, 5183, 856, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_DOOR = new Material<>(new Identifier("acacia_door"), 64, 11880, 689,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBBLESTONE_WALL = new Material<>(new Identifier("cobblestone_wall"), 64,
			7918, 373, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_DANDELION = new Material<>(new Identifier("potted_dandelion"), 64,
			8574, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_STONE_BRICK_WALL = new Material<>(
			new Identifier("end_stone_brick_wall"), 64, 17568, 386, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> VEX_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("vex_armor_trim_smithing_template"), 64, -1, 1219, false, true, null);
	public static final Material<BlockMeta> RED_MUSHROOM_BLOCK = new Material<>(new Identifier("red_mushroom_block"),
			64, 6610, 329, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AMETHYST_SHARD = new Material<>(new Identifier("amethyst_shard"), 64, -1,
			764, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_GOLD_ORE = new Material<>(new Identifier("deepslate_gold_ore"),
			64, 120, 55, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_OAK_LOG = new Material<>(new Identifier("stripped_oak_log"), 64,
			177, 122, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_HELMET = new Material<>(new Identifier("netherite_helmet"), 1, -1,
			832, false, true, null);
	public static final Material<BlockMeta> CYAN_CONCRETE_POWDER = new Material<>(
			new Identifier("cyan_concrete_powder"), 64, 12597, 556, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SADDLE = new Material<>(new Identifier("saddle"), 1, -1, 723, false, true,
			null);
	public static final Material<BlockMeta> OAK_SIGN = new Material<>(new Identifier("oak_sign"), 16, 4299, 842, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_GOLD_ORE = new Material<>(new Identifier("nether_gold_ore"), 64, 125,
			64, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_WALL_SIGN = new Material<>(new Identifier("bamboo_wall_sign"), 16,
			4823, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_BEEF = new Material<>(new Identifier("cooked_beef"), 64, -1, 944,
			false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_EMERALD_ORE = new Material<>(
			new Identifier("deepslate_emerald_ore"), 64, 7508, 59, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FARMLAND = new Material<>(new Identifier("farmland"), 64, 4282, 277, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_WOOL = new Material<>(new Identifier("red_wool"), 64, 2057, 193, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("waxed_cut_copper_slab"), 64, 21864, 105, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_WOOL = new Material<>(new Identifier("black_wool"), 64, 2058, 194,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLD_INGOT = new Material<>(new Identifier("gold_ingot"), 64, -1, 770,
			false, true, null);
	public static final Material<BlockMeta> CRACKED_DEEPSLATE_BRICKS = new Material<>(
			new Identifier("cracked_deepslate_bricks"), 64, 23695, 323, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_BUTTON = new Material<>(new Identifier("stone_button"), 64, 5753, 656,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MELON = new Material<>(new Identifier("melon"), 64, 6808, 334, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_STRAD = new Material<>(new Identifier("music_disc_strad"), 1, -1,
			1125, false, true, null);
	public static final Material<BlockMeta> MOJANG_BANNER_PATTERN = new Material<>(
			new Identifier("mojang_banner_pattern"), 1, -1, 1143, false, true, null);
	public static final Material<BlockMeta> BIRCH_WOOD = new Material<>(new Identifier("birch_wood"), 64, 192, 145,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSHROOM_STEW = new Material<>(new Identifier("mushroom_stew"), 1, -1, 805,
			false, true, null);
	public static final Material<BlockMeta> FLINT = new Material<>(new Identifier("flint"), 64, -1, 836, false, true,
			null);
	public static final Material<BlockMeta> SMOOTH_SANDSTONE_SLAB = new Material<>(
			new Identifier("smooth_sandstone_slab"), 64, 13962, 620, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_WALL_HANGING_SIGN = new Material<>(
			new Identifier("oak_wall_hanging_sign"), 16, 5535, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_PLANKS = new Material<>(new Identifier("warped_planks"), 64, 18508,
			33, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSHROOM_STEM = new Material<>(new Identifier("mushroom_stem"), 64, 6674,
			330, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HOGLIN_SPAWN_EGG = new Material<>(new Identifier("hoglin_spawn_egg"), 64,
			-1, 989, false, true, null);
	public static final Material<BlockMeta> DEAD_BRAIN_CORAL_BLOCK = new Material<>(
			new Identifier("dead_brain_coral_block"), 64, 12645, 565, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OXIDIZED_COPPER = new Material<>(new Identifier("oxidized_copper"), 64,
			21161, 80, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_BAMBOO_BLOCK = new Material<>(
			new Identifier("stripped_bamboo_block"), 64, 183, 142, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_CHEST_RAFT = new Material<>(new Identifier("bamboo_chest_raft"), 1,
			-1, 749, false, true, null);
	public static final Material<BlockMeta> SHULKER_SPAWN_EGG = new Material<>(new Identifier("shulker_spawn_egg"), 64,
			-1, 1011, false, true, null);
	public static final Material<BlockMeta> BEEHIVE = new Material<>(new Identifier("beehive"), 64, 19262, 1166, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BASALT = new Material<>(new Identifier("polished_basalt"), 64,
			5853, 305, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_WOOL = new Material<>(new Identifier("purple_wool"), 64, 2053, 189,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHORUS_FLOWER = new Material<>(new Identifier("chorus_flower"), 64, 12259,
			270, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_HANGING_SIGN = new Material<>(new Identifier("cherry_hanging_sign"),
			16, 5119, 858, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BRAIN_CORAL_WALL_FAN = new Material<>(
			new Identifier("dead_brain_coral_wall_fan"), 64, 12702, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_LEAVES = new Material<>(new Identifier("birch_leaves"), 64, 316, 155,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_BUTTON = new Material<>(new Identifier("acacia_button"), 64, 8712,
			662, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_DARK_OAK_WOOD = new Material<>(
			new Identifier("stripped_dark_oak_wood"), 64, 228, 138, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_TERRACOTTA = new Material<>(new Identifier("pink_terracotta"), 64,
			9218, 409, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_CANDLE = new Material<>(new Identifier("purple_candle"), 64, 20745,
			1195, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_TERRACOTTA = new Material<>(new Identifier("magenta_terracotta"),
			64, 9214, 405, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_COPPER_ORE = new Material<>(
			new Identifier("deepslate_copper_ore"), 64, 21166, 53, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRAIN_CORAL_FAN = new Material<>(new Identifier("brain_coral_fan"), 64,
			12686, 585, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_NETHER_BRICK_SLAB = new Material<>(
			new Identifier("red_nether_brick_slab"), 64, 13986, 624, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SUGAR_CANE = new Material<>(new Identifier("sugar_cane"), 64, 5795, 219,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TALL_GRASS = new Material<>(new Identifier("tall_grass"), 64, 10611, 445,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TALL_SEAGRASS = new Material<>(new Identifier("tall_seagrass"), 64, 2006,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_MANGROVE_PROPAGULE = new Material<>(
			new Identifier("potted_mangrove_propagule"), 64, 8572, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_CHESTPLATE = new Material<>(new Identifier("iron_chestplate"), 1, -1,
			821, false, true, null);
	public static final Material<BlockMeta> OXIDIZED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("oxidized_cut_copper_slab"), 64, 21494, 92, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_PLANKS = new Material<>(new Identifier("bamboo_planks"), 64, 23, 31,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLOWSTONE = new Material<>(new Identifier("glowstone"), 64, 5860, 308, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUBBLE_COLUMN = new Material<>(new Identifier("bubble_column"), 64, 12801,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_STAINED_GLASS = new Material<>(new Identifier("green_stained_glass"),
			64, 5955, 460, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_TULIP = new Material<>(new Identifier("white_tulip"), 64, 2079, 202,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COPPER_BLOCK = new Material<>(new Identifier("copper_block"), 64, 21164, 74,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_BED = new Material<>(new Identifier("magenta_bed"), 1, 1719, 922,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_NYLIUM = new Material<>(new Identifier("warped_nylium"), 64, 18432,
			21, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIORITE = new Material<>(new Identifier("diorite"), 64, 4, 4, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_SHULKER_BOX = new Material<>(new Identifier("cyan_shulker_box"), 1,
			12470, 508, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAVEL = new Material<>(new Identifier("gravel"), 64, 118, 47, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITCH_SPAWN_EGG = new Material<>(new Identifier("witch_spawn_egg"), 64, -1,
			1031, false, true, null);
	public static final Material<BlockMeta> NETHER_PORTAL = new Material<>(new Identifier("nether_portal"), 64, 5861,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JIGSAW = new Material<>(new Identifier("jigsaw"), 64, 19211, 751, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRAIN_CORAL_WALL_FAN = new Material<>(
			new Identifier("brain_coral_wall_fan"), 64, 12742, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_WOOD = new Material<>(new Identifier("mangrove_wood"), 64, 207,
			150, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_STONE_BRICK_SLAB = new Material<>(
			new Identifier("mossy_stone_brick_slab"), 64, 13938, 616, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_CARPET = new Material<>(new Identifier("yellow_carpet"), 64, 10587,
			426, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TINTED_GLASS = new Material<>(new Identifier("tinted_glass"), 64, 20924,
			166, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AIR = new Material<>(new Identifier("air"), 64, 0, 0, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_FENCE_GATE = new Material<>(new Identifier("jungle_fence_gate"), 64,
			11236, 711, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SANDSTONE = new Material<>(new Identifier("sandstone"), 64, 531, 168, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SOUL_FIRE = new Material<>(new Identifier("soul_fire"), 64, 2868, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TROPICAL_FISH = new Material<>(new Identifier("tropical_fish"), 64, -1, 893,
			false, true, null);
	public static final Material<BlockMeta> NETHER_STAR = new Material<>(new Identifier("nether_star"), 64, -1, 1060,
			false, true, null);
	public static final Material<BlockMeta> LIGHT_BLUE_CANDLE = new Material<>(new Identifier("light_blue_candle"), 64,
			20633, 1188, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTATO = new Material<>(new Identifier("potato"), 64, -1, 1048, false, true,
			null);
	public static final Material<BlockMeta> NETHER_BRICKS = new Material<>(new Identifier("nether_bricks"), 64, 7268,
			342, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_STAINED_GLASS = new Material<>(new Identifier("brown_stained_glass"),
			64, 5954, 459, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_NETHER_BRICKS = new Material<>(
			new Identifier("chiseled_nether_bricks"), 64, 20563, 344, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_SLAB = new Material<>(
			new Identifier("polished_blackstone_slab"), 64, 20210, 1176, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_ANDESITE_SLAB = new Material<>(
			new Identifier("polished_andesite_slab"), 64, 13992, 625, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_STAINED_GLASS = new Material<>(
			new Identifier("light_gray_stained_glass"), 64, 5950, 455, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TROPICAL_FISH_BUCKET = new Material<>(
			new Identifier("tropical_fish_bucket"), 1, -1, 874, false, true, null);
	public static final Material<BlockMeta> PURPUR_BLOCK = new Material<>(new Identifier("purpur_block"), 64, 12265,
			271, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_BANNER = new Material<>(new Identifier("blue_banner"), 16, 10790, 1094,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INFESTED_CRACKED_STONE_BRICKS = new Material<>(
			new Identifier("infested_cracked_stone_bricks"), 64, 6544, 313, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CLAY_BALL = new Material<>(new Identifier("clay_ball"), 64, -1, 878, false,
			true, null);
	public static final Material<BlockMeta> CRIMSON_BUTTON = new Material<>(new Identifier("crimson_button"), 64, 18950,
			667, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENDERMAN_SPAWN_EGG = new Material<>(new Identifier("enderman_spawn_egg"),
			64, -1, 980, false, true, null);
	public static final Material<BlockMeta> DONKEY_SPAWN_EGG = new Material<>(new Identifier("donkey_spawn_egg"), 64,
			-1, 976, false, true, null);
	public static final Material<BlockMeta> STONECUTTER = new Material<>(new Identifier("stonecutter"), 64, 18308, 1155,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHAINMAIL_BOOTS = new Material<>(new Identifier("chainmail_boots"), 1, -1,
			819, false, true, null);
	public static final Material<BlockMeta> LIME_STAINED_GLASS_PANE = new Material<>(
			new Identifier("lime_stained_glass_pane"), 64, 9419, 468, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRUCTURE_VOID = new Material<>(new Identifier("structure_void"), 64, 12393,
			497, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BRAIN_CORAL = new Material<>(new Identifier("dead_brain_coral"), 64,
			12656, 579, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_STAIRS = new Material<>(new Identifier("crimson_stairs"), 64, 18792,
			369, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FROG_SPAWN_EGG = new Material<>(new Identifier("frog_spawn_egg"), 64, -1,
			984, false, true, null);
	public static final Material<BlockMeta> CLOCK = new Material<>(new Identifier("clock"), 64, -1, 888, false, true,
			null);
	public static final Material<BlockMeta> LLAMA_SPAWN_EGG = new Material<>(new Identifier("llama_spawn_egg"), 64, -1,
			993, false, true, null);
	public static final Material<BlockMeta> WARPED_HANGING_SIGN = new Material<>(new Identifier("warped_hanging_sign"),
			16, 5375, 863, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTERY_SHARD_SKULL = new Material<>(new Identifier("pottery_shard_skull"),
			64, -1, 1227, false, true, null);
	public static final Material<BlockMeta> DEAD_FIRE_CORAL_FAN = new Material<>(new Identifier("dead_fire_coral_fan"),
			64, 12680, 592, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CREEPER_SPAWN_EGG = new Material<>(new Identifier("creeper_spawn_egg"), 64,
			-1, 974, false, true, null);
	public static final Material<BlockMeta> TWISTING_VINES_PLANT = new Material<>(
			new Identifier("twisting_vines_plant"), 64, 18505, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_PLANKS = new Material<>(new Identifier("jungle_planks"), 64, 18, 26,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEATHER_HELMET = new Material<>(new Identifier("leather_helmet"), 1, -1,
			812, false, true, null);
	public static final Material<BlockMeta> STRIPPED_CHERRY_WOOD = new Material<>(
			new Identifier("stripped_cherry_wood"), 64, 225, 137, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_FENCE = new Material<>(new Identifier("crimson_fence"), 64, 18556,
			296, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_CARPET = new Material<>(new Identifier("blue_carpet"), 64, 10594, 433,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_HOE = new Material<>(new Identifier("iron_hoe"), 1, -1, 792, false,
			true, null);
	public static final Material<BlockMeta> EYE_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("eye_armor_trim_smithing_template"), 64, -1, 1218, false, true, null);
	public static final Material<BlockMeta> LIME_CANDLE = new Material<>(new Identifier("lime_candle"), 64, 20665, 1190,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_PRESSURE_PLATE = new Material<>(new Identifier("oak_pressure_plate"),
			64, 5713, 673, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_CHEST_BOAT = new Material<>(new Identifier("mangrove_chest_boat"),
			1, -1, 747, false, true, null);
	public static final Material<BlockMeta> CYAN_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("cyan_glazed_terracotta"), 64, 12544, 524, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BASALT = new Material<>(new Identifier("basalt"), 64, 5850, 304, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_DOOR = new Material<>(new Identifier("jungle_door"), 64, 11816, 688,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_CARPET = new Material<>(new Identifier("brown_carpet"), 64, 10595,
			434, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_CONCRETE_POWDER = new Material<>(
			new Identifier("gray_concrete_powder"), 64, 12595, 554, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_CANDLE = new Material<>(new Identifier("red_candle"), 64, 20809, 1199,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> QUARTZ = new Material<>(new Identifier("quartz"), 64, -1, 763, false, true,
			null);
	public static final Material<BlockMeta> RAW_COPPER = new Material<>(new Identifier("raw_copper"), 64, -1, 767,
			false, true, null);
	public static final Material<BlockMeta> BEETROOT = new Material<>(new Identifier("beetroot"), 64, -1, 1103, false,
			true, null);
	public static final Material<BlockMeta> DEAD_FIRE_CORAL = new Material<>(new Identifier("dead_fire_coral"), 64,
			12660, 581, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_MALL = new Material<>(new Identifier("music_disc_mall"), 1, -1,
			1122, false, true, null);
	public static final Material<BlockMeta> LADDER = new Material<>(new Identifier("ladder"), 64, 4651, 279, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LODESTONE = new Material<>(new Identifier("lodestone"), 64, 19300, 1169,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_WALL_BANNER = new Material<>(new Identifier("pink_wall_banner"), 16,
			10894, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_HOE = new Material<>(new Identifier("netherite_hoe"), 1, -1, 802,
			false, true, null);
	public static final Material<BlockMeta> FROGSPAWN = new Material<>(new Identifier("frogspawn"), 64, 23715, 1209,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WATER_BUCKET = new Material<>(new Identifier("water_bucket"), 1, -1, 865,
			false, true, null);
	public static final Material<BlockMeta> WARPED_STAIRS = new Material<>(new Identifier("warped_stairs"), 64, 18872,
			370, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BUBBLE_CORAL = new Material<>(new Identifier("dead_bubble_coral"), 64,
			12658, 580, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_HANGING_SIGN = new Material<>(new Identifier("oak_hanging_sign"), 16,
			4863, 853, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHORUS_FRUIT = new Material<>(new Identifier("chorus_fruit"), 64, -1, 1100,
			false, true, null);
	public static final Material<BlockMeta> GRANITE_SLAB = new Material<>(new Identifier("granite_slab"), 64, 13974,
			622, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SANDSTONE_SLAB = new Material<>(new Identifier("sandstone_slab"), 64, 11092,
			242, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_AZALEA_BUSH = new Material<>(new Identifier("potted_azalea_bush"),
			64, 23704, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CAKE = new Material<>(new Identifier("cake"), 1, 5871, 919, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_WALL_BANNER = new Material<>(new Identifier("gray_wall_banner"), 16,
			10898, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_LEAVES = new Material<>(new Identifier("acacia_leaves"), 64, 372,
			157, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSS_CARPET = new Material<>(new Identifier("moss_carpet"), 64, 21969, 221,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_BANNER = new Material<>(new Identifier("brown_banner"), 16, 10806,
			1095, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BUBBLE_CORAL_WALL_FAN = new Material<>(
			new Identifier("dead_bubble_coral_wall_fan"), 64, 12710, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_BRICK = new Material<>(new Identifier("nether_brick"), 64, -1, 1065,
			false, true, null);
	public static final Material<BlockMeta> PINK_STAINED_GLASS_PANE = new Material<>(
			new Identifier("pink_stained_glass_pane"), 64, 9451, 469, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WILD_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("wild_armor_trim_smithing_template"), 64, -1, 1216, false, true, null);
	public static final Material<BlockMeta> DIAMOND_LEGGINGS = new Material<>(new Identifier("diamond_leggings"), 1, -1,
			826, false, true, null);
	public static final Material<BlockMeta> WARPED_SLAB = new Material<>(new Identifier("warped_slab"), 64, 18518, 239,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_STAIRS = new Material<>(new Identifier("mangrove_stairs"), 64,
			9991, 366, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_COBBLESTONE_SLAB = new Material<>(
			new Identifier("mossy_cobblestone_slab"), 64, 13950, 618, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WOODEN_HOE = new Material<>(new Identifier("wooden_hoe"), 1, -1, 777, false,
			true, null);
	public static final Material<BlockMeta> WHITE_WOOL = new Material<>(new Identifier("white_wool"), 64, 2043, 179,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> VOID_AIR = new Material<>(new Identifier("void_air"), 64, 12799, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TORCHFLOWER_CROP = new Material<>(new Identifier("torchflower_crop"), 64,
			12350, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHAINMAIL_CHESTPLATE = new Material<>(
			new Identifier("chainmail_chestplate"), 1, -1, 817, false, true, null);
	public static final Material<BlockMeta> IRON_LEGGINGS = new Material<>(new Identifier("iron_leggings"), 1, -1, 822,
			false, true, null);
	public static final Material<BlockMeta> ACACIA_CHEST_BOAT = new Material<>(new Identifier("acacia_chest_boat"), 1,
			-1, 741, false, true, null);
	public static final Material<BlockMeta> LIGHT_GRAY_WALL_BANNER = new Material<>(
			new Identifier("light_gray_wall_banner"), 16, 10902, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_WALL_HANGING_SIGN = new Material<>(
			new Identifier("dark_oak_wall_hanging_sign"), 16, 5583, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_STAINED_GLASS = new Material<>(
			new Identifier("purple_stained_glass"), 64, 5952, 457, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_BOOKSHELF = new Material<>(new Identifier("chiseled_bookshelf"),
			64, 2156, 263, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_BED = new Material<>(new Identifier("green_bed"), 1, 1895, 933, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_CONCRETE_POWDER = new Material<>(new Identifier("red_concrete_powder"),
			64, 12602, 561, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REPEATER = new Material<>(new Identifier("repeater"), 64, 5881, 635, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_SANDSTONE = new Material<>(new Identifier("chiseled_sandstone"),
			64, 532, 169, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LINGERING_POTION = new Material<>(new Identifier("lingering_potion"), 1, -1,
			1110, false, true, null);
	public static final Material<BlockMeta> CUT_COPPER_STAIRS = new Material<>(new Identifier("cut_copper_stairs"), 64,
			21422, 85, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_DYE = new Material<>(new Identifier("pink_dye"), 64, -1, 906, false,
			true, null);
	public static final Material<BlockMeta> ORANGE_CARPET = new Material<>(new Identifier("orange_carpet"), 64, 10584,
			423, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_CONCRETE_POWDER = new Material<>(
			new Identifier("magenta_concrete_powder"), 64, 12590, 549, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_FUNGUS_ON_A_STICK = new Material<>(
			new Identifier("warped_fungus_on_a_stick"), 1, -1, 730, false, true, null);
	public static final Material<BlockMeta> ARMOR_STAND = new Material<>(new Identifier("armor_stand"), 16, -1, 1073,
			false, true, null);
	public static final Material<BlockMeta> RED_NETHER_BRICKS = new Material<>(new Identifier("red_nether_bricks"), 64,
			12389, 495, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_CONCRETE = new Material<>(new Identifier("light_gray_concrete"),
			64, 12580, 539, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SEAGRASS = new Material<>(new Identifier("seagrass"), 64, 2004, 177, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_STAIRS = new Material<>(new Identifier("stone_stairs"), 64, 13294,
			602, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_TERRACOTTA = new Material<>(new Identifier("red_terracotta"), 64, 9226,
			417, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_GOLEM_SPAWN_EGG = new Material<>(
			new Identifier("iron_golem_spawn_egg"), 64, -1, 992, false, true, null);
	public static final Material<BlockMeta> PINK_CANDLE_CAKE = new Material<>(new Identifier("pink_candle_cake"), 64,
			20853, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_WALL_HANGING_SIGN = new Material<>(
			new Identifier("spruce_wall_hanging_sign"), 16, 5543, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEATHER_LEGGINGS = new Material<>(new Identifier("leather_leggings"), 1, -1,
			814, false, true, null);
	public static final Material<BlockMeta> LIME_SHULKER_BOX = new Material<>(new Identifier("lime_shulker_box"), 1,
			12446, 504, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CREEPER_HEAD = new Material<>(new Identifier("creeper_head"), 64, 8903,
			1057, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WEATHERED_COPPER = new Material<>(new Identifier("weathered_copper"), 64,
			21162, 79, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_BANNER = new Material<>(new Identifier("gray_banner"), 16, 10726, 1090,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUD_BRICK_STAIRS = new Material<>(new Identifier("mud_brick_stairs"), 64,
			7196, 339, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRING = new Material<>(new Identifier("string"), 64, -1, 806, false, true,
			null);
	public static final Material<BlockMeta> SPRUCE_HANGING_SIGN = new Material<>(new Identifier("spruce_hanging_sign"),
			16, 4927, 854, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_TERRACOTTA = new Material<>(new Identifier("white_terracotta"), 64,
			9212, 403, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BOOK = new Material<>(new Identifier("book"), 64, -1, 881, false, true,
			null);
	public static final Material<BlockMeta> WOODEN_SHOVEL = new Material<>(new Identifier("wooden_shovel"), 1, -1, 774,
			false, true, null);
	public static final Material<BlockMeta> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("snout_armor_trim_smithing_template"), 64, -1, 1221, false, true, null);
	public static final Material<BlockMeta> BLACKSTONE_SLAB = new Material<>(new Identifier("blackstone_slab"), 64,
			19709, 1172, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_RAFT = new Material<>(new Identifier("bamboo_raft"), 1, -1, 748,
			false, true, null);
	public static final Material<BlockMeta> JUNGLE_TRAPDOOR = new Material<>(new Identifier("jungle_trapdoor"), 64,
			6165, 700, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("magenta_glazed_terracotta"), 64, 12516, 517, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_BED = new Material<>(new Identifier("gray_bed"), 1, 1799, 927, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_CHERRY_LOG = new Material<>(new Identifier("stripped_cherry_log"),
			64, 171, 127, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_LEAVES = new Material<>(new Identifier("mangrove_leaves"), 64, 456,
			160, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DRIED_KELP_BLOCK = new Material<>(new Identifier("dried_kelp_block"), 64,
			12631, 879, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BONE = new Material<>(new Identifier("bone"), 64, -1, 917, false, true,
			null);
	public static final Material<BlockMeta> BAMBOO_TRAPDOOR = new Material<>(new Identifier("bamboo_trapdoor"), 64,
			6485, 705, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TARGET = new Material<>(new Identifier("target"), 64, 19222, 646, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_WEATHERED_COPPER = new Material<>(
			new Identifier("waxed_weathered_copper"), 64, 21516, 95, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DRAGON_WALL_HEAD = new Material<>(new Identifier("dragon_wall_head"), 64,
			8939, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITHER_SKELETON_SPAWN_EGG = new Material<>(
			new Identifier("wither_skeleton_spawn_egg"), 64, -1, 1033, false, true, null);
	public static final Material<BlockMeta> CHERRY_FENCE = new Material<>(new Identifier("cherry_fence"), 64, 11580,
			292, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HORN_CORAL_BLOCK = new Material<>(new Identifier("horn_coral_block"), 64,
			12653, 573, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PISTON_HEAD = new Material<>(new Identifier("piston_head"), 64, 2021, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_CANDLE = new Material<>(new Identifier("blue_candle"), 64, 20761, 1196,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_TULIP = new Material<>(new Identifier("orange_tulip"), 64, 2078, 201,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_SANDSTONE_SLAB = new Material<>(new Identifier("cut_sandstone_slab"),
			64, 11098, 243, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_BIRCH_SAPLING = new Material<>(
			new Identifier("potted_birch_sapling"), 64, 8567, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRASS_BLOCK = new Material<>(new Identifier("grass_block"), 64, 9, 14, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_PIGSTEP = new Material<>(new Identifier("music_disc_pigstep"), 1,
			-1, 1131, false, true, null);
	public static final Material<BlockMeta> END_GATEWAY = new Material<>(new Identifier("end_gateway"), 64, 12358, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_BED = new Material<>(new Identifier("black_bed"), 1, 1927, 935, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_OXIDIZED_COPPER = new Material<>(
			new Identifier("waxed_oxidized_copper"), 64, 21518, 96, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPECTRAL_ARROW = new Material<>(new Identifier("spectral_arrow"), 64, -1,
			1108, false, true, null);
	public static final Material<BlockMeta> JUNGLE_STAIRS = new Material<>(new Identifier("jungle_stairs"), 64, 7833,
			362, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_SHOVEL = new Material<>(new Identifier("netherite_shovel"), 1, -1,
			799, false, true, null);
	public static final Material<BlockMeta> PIGLIN_SPAWN_EGG = new Material<>(new Identifier("piglin_spawn_egg"), 64,
			-1, 1002, false, true, null);
	public static final Material<BlockMeta> OXEYE_DAISY = new Material<>(new Identifier("oxeye_daisy"), 64, 2081, 204,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_OXIDIZED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("waxed_oxidized_cut_copper_stairs"), 64, 21534, 104, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEATHER_CHESTPLATE = new Material<>(new Identifier("leather_chestplate"), 1,
			-1, 813, false, true, null);
	public static final Material<BlockMeta> AXOLOTL_BUCKET = new Material<>(new Identifier("axolotl_bucket"), 1, -1,
			875, false, true, null);
	public static final Material<BlockMeta> SMALL_AMETHYST_BUD = new Material<>(new Identifier("small_amethyst_bud"),
			64, 20919, 1201, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLAR_BEAR_SPAWN_EGG = new Material<>(
			new Identifier("polar_bear_spawn_egg"), 64, -1, 1005, false, true, null);
	public static final Material<BlockMeta> REINFORCED_DEEPSLATE = new Material<>(
			new Identifier("reinforced_deepslate"), 64, 23716, 327, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SNIFFER_SPAWN_EGG = new Material<>(new Identifier("sniffer_spawn_egg"), 64,
			-1, 1016, false, true, null);
	public static final Material<BlockMeta> ATTACHED_MELON_STEM = new Material<>(new Identifier("attached_melon_stem"),
			64, 6813, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LAVA_BUCKET = new Material<>(new Identifier("lava_bucket"), 1, -1, 866,
			false, true, null);
	public static final Material<BlockMeta> YELLOW_BANNER = new Material<>(new Identifier("yellow_banner"), 16, 10678,
			1087, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_DOOR = new Material<>(new Identifier("cherry_door"), 64, 11944, 690,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ROTTEN_FLESH = new Material<>(new Identifier("rotten_flesh"), 64, -1, 947,
			false, true, null);
	public static final Material<BlockMeta> PURPLE_BANNER = new Material<>(new Identifier("purple_banner"), 16, 10774,
			1093, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHAINMAIL_LEGGINGS = new Material<>(new Identifier("chainmail_leggings"), 1,
			-1, 818, false, true, null);
	public static final Material<BlockMeta> PINK_CANDLE = new Material<>(new Identifier("pink_candle"), 64, 20681, 1191,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> QUARTZ_PILLAR = new Material<>(new Identifier("quartz_pillar"), 64, 9094,
			401, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_STAIRS = new Material<>(new Identifier("bamboo_stairs"), 64, 10071,
			367, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_CONCRETE_POWDER = new Material<>(
			new Identifier("purple_concrete_powder"), 64, 12598, 557, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_DIORITE_SLAB = new Material<>(
			new Identifier("polished_diorite_slab"), 64, 13944, 617, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE = new Material<>(new Identifier("prismarine"), 64, 10318, 479,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_WEATHERED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("waxed_weathered_cut_copper_slab"), 64, 21852, 107, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_TRAPDOOR = new Material<>(new Identifier("crimson_trapdoor"), 64,
			18604, 706, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FILLED_MAP = new Material<>(new Identifier("filled_map"), 64, -1, 937,
			false, true, null);
	public static final Material<BlockMeta> WEEPING_VINES_PLANT = new Material<>(new Identifier("weeping_vines_plant"),
			64, 18478, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PORKCHOP = new Material<>(new Identifier("porkchop"), 64, -1, 837, false,
			true, null);
	public static final Material<BlockMeta> PINK_TULIP = new Material<>(new Identifier("pink_tulip"), 64, 2080, 203,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_ICE = new Material<>(new Identifier("blue_ice"), 64, 12782, 594, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WOLF_SPAWN_EGG = new Material<>(new Identifier("wolf_spawn_egg"), 64, -1,
			1034, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_TILE_STAIRS = new Material<>(
			new Identifier("deepslate_tile_stairs"), 64, 22884, 613, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRYING_OBSIDIAN = new Material<>(new Identifier("crying_obsidian"), 64,
			19290, 1170, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> VINE = new Material<>(new Identifier("vine"), 64, 6864, 335, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> QUARTZ_STAIRS = new Material<>(new Identifier("quartz_stairs"), 64, 9107,
			402, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RAIL = new Material<>(new Identifier("rail"), 64, 4659, 721, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_BANNER = new Material<>(new Identifier("white_banner"), 16, 10614,
			1083, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSS_BLOCK = new Material<>(new Identifier("moss_block"), 64, 21986, 223,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_STAINED_GLASS = new Material<>(new Identifier("blue_stained_glass"),
			64, 5953, 458, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_CARPET = new Material<>(new Identifier("red_carpet"), 64, 10597, 436,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_SLAB = new Material<>(new Identifier("mangrove_slab"), 64, 11062,
			235, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FLOWER_BANNER_PATTERN = new Material<>(
			new Identifier("flower_banner_pattern"), 1, -1, 1140, false, true, null);
	public static final Material<BlockMeta> LIME_CONCRETE_POWDER = new Material<>(
			new Identifier("lime_concrete_powder"), 64, 12593, 552, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUDDY_MANGROVE_ROOTS = new Material<>(
			new Identifier("muddy_mangrove_roots"), 64, 153, 118, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_DYE = new Material<>(new Identifier("blue_dye"), 64, -1, 911, false,
			true, null);
	public static final Material<BlockMeta> SUGAR = new Material<>(new Identifier("sugar"), 64, -1, 918, false, true,
			null);
	public static final Material<BlockMeta> COPPER_INGOT = new Material<>(new Identifier("copper_ingot"), 64, -1, 768,
			false, true, null);
	public static final Material<BlockMeta> COD_BUCKET = new Material<>(new Identifier("cod_bucket"), 1, -1, 873, false,
			true, null);
	public static final Material<BlockMeta> CRIMSON_PLANKS = new Material<>(new Identifier("crimson_planks"), 64, 18507,
			32, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_BLOCK = new Material<>(new Identifier("bamboo_block"), 64, 156, 121,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> INK_SAC = new Material<>(new Identifier("ink_sac"), 64, -1, 897, false,
			true, null);
	public static final Material<BlockMeta> BOWL = new Material<>(new Identifier("bowl"), 64, -1, 804, false, true,
			null);
	public static final Material<BlockMeta> SKELETON_SKULL = new Material<>(new Identifier("skeleton_skull"), 64, 8823,
			1053, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_DYE = new Material<>(new Identifier("orange_dye"), 64, -1, 901,
			false, true, null);
	public static final Material<BlockMeta> YELLOW_BED = new Material<>(new Identifier("yellow_bed"), 1, 1751, 924,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_LEGGINGS = new Material<>(new Identifier("netherite_leggings"), 1,
			-1, 834, false, true, null);
	public static final Material<BlockMeta> BIRCH_PLANKS = new Material<>(new Identifier("birch_planks"), 64, 17, 25,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SILVERFISH_SPAWN_EGG = new Material<>(
			new Identifier("silverfish_spawn_egg"), 64, -1, 1012, false, true, null);
	public static final Material<BlockMeta> GREEN_STAINED_GLASS_PANE = new Material<>(
			new Identifier("green_stained_glass_pane"), 64, 9675, 476, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_BASALT = new Material<>(new Identifier("smooth_basalt"), 64, 23700,
			306, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_CONCRETE_POWDER = new Material<>(
			new Identifier("black_concrete_powder"), 64, 12603, 562, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RABBIT_SPAWN_EGG = new Material<>(new Identifier("rabbit_spawn_egg"), 64,
			-1, 1007, false, true, null);
	public static final Material<BlockMeta> LIME_CARPET = new Material<>(new Identifier("lime_carpet"), 64, 10588, 427,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_CONCRETE_POWDER = new Material<>(
			new Identifier("blue_concrete_powder"), 64, 12599, 558, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE_WALL_TORCH = new Material<>(new Identifier("redstone_wall_torch"),
			64, 5736, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HOPPER = new Material<>(new Identifier("hopper"), 64, 9081, 642, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_ACACIA_SAPLING = new Material<>(
			new Identifier("potted_acacia_sapling"), 64, 8569, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_TERRACOTTA = new Material<>(new Identifier("lime_terracotta"), 64,
			9217, 408, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAKED_POTATO = new Material<>(new Identifier("baked_potato"), 64, -1, 1049,
			false, true, null);
	public static final Material<BlockMeta> MOSSY_COBBLESTONE = new Material<>(new Identifier("mossy_cobblestone"), 64,
			2349, 265, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRAIN_CORAL_BLOCK = new Material<>(new Identifier("brain_coral_block"), 64,
			12650, 570, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_SWORD = new Material<>(new Identifier("netherite_sword"), 1, -1,
			798, false, true, null);
	public static final Material<BlockMeta> COBBLED_DEEPSLATE = new Material<>(new Identifier("cobbled_deepslate"), 64,
			22050, 9, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_CANDLE = new Material<>(new Identifier("brown_candle"), 64, 20777,
			1197, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> YELLOW_STAINED_GLASS_PANE = new Material<>(
			new Identifier("yellow_stained_glass_pane"), 64, 9387, 467, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_TRAPDOOR = new Material<>(new Identifier("oak_trapdoor"), 64, 5973, 697,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACK_STAINED_GLASS = new Material<>(new Identifier("black_stained_glass"),
			64, 5957, 462, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_BED = new Material<>(new Identifier("lime_bed"), 1, 1767, 925, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLAST_FURNACE = new Material<>(new Identifier("blast_furnace"), 64, 18270,
			1150, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPONGE = new Material<>(new Identifier("sponge"), 64, 513, 163, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERITE_INGOT = new Material<>(new Identifier("netherite_ingot"), 64, -1,
			771, false, true, null);
	public static final Material<BlockMeta> BLACK_CANDLE_CAKE = new Material<>(new Identifier("black_candle_cake"), 64,
			20871, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEETROOT_SOUP = new Material<>(new Identifier("beetroot_soup"), 1, -1, 1105,
			false, true, null);
	public static final Material<BlockMeta> RAW_COPPER_BLOCK = new Material<>(new Identifier("raw_copper_block"), 64,
			23702, 69, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_CARPET = new Material<>(new Identifier("light_gray_carpet"), 64,
			10591, 430, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_WARD = new Material<>(new Identifier("music_disc_ward"), 1, -1,
			1126, false, true, null);
	public static final Material<BlockMeta> END_CRYSTAL = new Material<>(new Identifier("end_crystal"), 64, -1, 1099,
			false, true, null);
	public static final Material<BlockMeta> VINDICATOR_SPAWN_EGG = new Material<>(
			new Identifier("vindicator_spawn_egg"), 64, -1, 1028, false, true, null);
	public static final Material<BlockMeta> WHEAT = new Material<>(new Identifier("wheat"), 64, 4274, 810, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> END_ROD = new Material<>(new Identifier("end_rod"), 64, 12193, 268, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PHANTOM_SPAWN_EGG = new Material<>(new Identifier("phantom_spawn_egg"), 64,
			-1, 1000, false, true, null);
	public static final Material<BlockMeta> GUARDIAN_SPAWN_EGG = new Material<>(new Identifier("guardian_spawn_egg"),
			64, -1, 988, false, true, null);
	public static final Material<BlockMeta> PINK_STAINED_GLASS = new Material<>(new Identifier("pink_stained_glass"),
			64, 5948, 453, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MELON_SLICE = new Material<>(new Identifier("melon_slice"), 64, -1, 939,
			false, true, null);
	public static final Material<BlockMeta> ENDER_CHEST = new Material<>(new Identifier("ender_chest"), 64, 7510, 357,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> KELP = new Material<>(new Identifier("kelp"), 64, 12604, 220, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHERRY_CHEST_BOAT = new Material<>(new Identifier("cherry_chest_boat"), 1,
			-1, 743, false, true, null);
	public static final Material<BlockMeta> LIGHT_GRAY_WOOL = new Material<>(new Identifier("light_gray_wool"), 64,
			2051, 187, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOVING_PISTON = new Material<>(new Identifier("moving_piston"), 64, 2059,
			-1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SUNFLOWER = new Material<>(new Identifier("sunflower"), 64, 10603, 441,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> HEART_OF_THE_SEA = new Material<>(new Identifier("heart_of_the_sea"), 64,
			-1, 1136, false, true, null);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_BRICKS = new Material<>(
			new Identifier("polished_blackstone_bricks"), 64, 19713, 1179, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPYGLASS = new Material<>(new Identifier("spyglass"), 1, -1, 889, false,
			true, null);
	public static final Material<BlockMeta> POWDER_SNOW_CAULDRON = new Material<>(
			new Identifier("powder_snow_cauldron"), 64, 7399, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEAD_BUBBLE_CORAL_FAN = new Material<>(
			new Identifier("dead_bubble_coral_fan"), 64, 12678, 591, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FURNACE = new Material<>(new Identifier("furnace"), 64, 4291, 278, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_STAINED_GLASS = new Material<>(new Identifier("red_stained_glass"), 64,
			5956, 461, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_SLAB = new Material<>(new Identifier("stone_slab"), 64, 11080, 240,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_MOSAIC_STAIRS = new Material<>(
			new Identifier("bamboo_mosaic_stairs"), 64, 10151, 368, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLAZE_ROD = new Material<>(new Identifier("blaze_rod"), 64, -1, 949, false,
			true, null);
	public static final Material<BlockMeta> IRON_TRAPDOOR = new Material<>(new Identifier("iron_trapdoor"), 64, 10269,
			696, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_PRESSURE_PLATE = new Material<>(
			new Identifier("stone_pressure_plate"), 64, 5647, 669, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_CONCRETE = new Material<>(new Identifier("brown_concrete"), 64, 12584,
			543, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_CONCRETE = new Material<>(new Identifier("cyan_concrete"), 64, 12581,
			540, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_BIRCH_WOOD = new Material<>(new Identifier("stripped_birch_wood"),
			64, 216, 134, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARD_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("ward_armor_trim_smithing_template"), 64, -1, 1217, false, true, null);
	public static final Material<BlockMeta> MANGROVE_BOAT = new Material<>(new Identifier("mangrove_boat"), 1, -1, 746,
			false, true, null);
	public static final Material<BlockMeta> FROSTED_ICE = new Material<>(new Identifier("frosted_ice"), 64, 12383, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RABBIT_FOOT = new Material<>(new Identifier("rabbit_foot"), 64, -1, 1071,
			false, true, null);
	public static final Material<BlockMeta> YELLOW_WALL_BANNER = new Material<>(new Identifier("yellow_wall_banner"),
			16, 10886, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_WALL_BANNER = new Material<>(new Identifier("cyan_wall_banner"), 16,
			10906, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIAMOND_HORSE_ARMOR = new Material<>(new Identifier("diamond_horse_armor"),
			1, -1, 1076, false, true, null);
	public static final Material<BlockMeta> WHITE_CARPET = new Material<>(new Identifier("white_carpet"), 64, 10583,
			422, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FLETCHING_TABLE = new Material<>(new Identifier("fletching_table"), 64,
			18278, 1152, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COCOA_BEANS = new Material<>(new Identifier("cocoa_beans"), 64, -1, 899,
			false, true, null);
	public static final Material<BlockMeta> CRACKED_POLISHED_BLACKSTONE_BRICKS = new Material<>(
			new Identifier("cracked_polished_blackstone_bricks"), 64, 19714, 1182, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_SLAB = new Material<>(new Identifier("acacia_slab"), 64, 11044, 232,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WITHER_SKELETON_SKULL = new Material<>(
			new Identifier("wither_skeleton_skull"), 64, 8843, 1054, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FIRE_CORAL_FAN = new Material<>(new Identifier("fire_coral_fan"), 64, 12690,
			587, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUBBLE_CORAL = new Material<>(new Identifier("bubble_coral"), 64, 12668,
			576, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_WOOL = new Material<>(new Identifier("lime_wool"), 64, 2048, 184, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REDSTONE_LAMP = new Material<>(new Identifier("redstone_lamp"), 64, 7414,
			654, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_WOOD = new Material<>(new Identifier("jungle_wood"), 64, 195, 146,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTION = new Material<>(new Identifier("potion"), 1, -1, 953, false, true,
			null);
	public static final Material<BlockMeta> GOLD_NUGGET = new Material<>(new Identifier("gold_nugget"), 64, -1, 951,
			false, true, null);
	public static final Material<BlockMeta> WARPED_STEM = new Material<>(new Identifier("warped_stem"), 64, 18421, 120,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_TRAPDOOR = new Material<>(new Identifier("acacia_trapdoor"), 64,
			6229, 701, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_COBBLESTONE_WALL = new Material<>(
			new Identifier("mossy_cobblestone_wall"), 64, 8242, 374, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_PRESSURE_PLATE = new Material<>(
			new Identifier("polished_blackstone_pressure_plate"), 64, 20214, 670, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_IRON_ORE = new Material<>(new Identifier("deepslate_iron_ore"),
			64, 122, 51, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPUR_PILLAR = new Material<>(new Identifier("purpur_pillar"), 64, 12267,
			272, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FEATHER = new Material<>(new Identifier("feather"), 64, -1, 807, false,
			true, null);
	public static final Material<BlockMeta> TRADER_LLAMA_SPAWN_EGG = new Material<>(
			new Identifier("trader_llama_spawn_egg"), 64, -1, 1023, false, true, null);
	public static final Material<BlockMeta> HONEY_BOTTLE = new Material<>(new Identifier("honey_bottle"), 16, -1, 1167,
			false, true, null);
	public static final Material<BlockMeta> COBBLED_DEEPSLATE_STAIRS = new Material<>(
			new Identifier("cobbled_deepslate_stairs"), 64, 22062, 610, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_BED = new Material<>(new Identifier("purple_bed"), 1, 1847, 930,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> FIREWORK_ROCKET = new Material<>(new Identifier("firework_rocket"), 64, -1,
			1062, false, true, null);
	public static final Material<BlockMeta> WAXED_WEATHERED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("waxed_weathered_cut_copper_stairs"), 64, 21614, 103, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("orange_glazed_terracotta"), 64, 12512, 516, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLACKSTONE_STAIRS = new Material<>(new Identifier("blackstone_stairs"), 64,
			19313, 1173, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_CONCRETE = new Material<>(new Identifier("purple_concrete"), 64,
			12582, 541, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_STAINED_GLASS_PANE = new Material<>(
			new Identifier("orange_stained_glass_pane"), 64, 9291, 464, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SCULK_CATALYST = new Material<>(new Identifier("sculk_catalyst"), 64, 21152,
			349, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_CARPET = new Material<>(new Identifier("pink_carpet"), 64, 10589, 428,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_HYPHAE = new Material<>(new Identifier("warped_hyphae"), 64, 18427,
			152, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_DEEPSLATE_SLAB = new Material<>(
			new Identifier("polished_deepslate_slab"), 64, 22545, 628, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_STONE_BRICK_STAIRS = new Material<>(
			new Identifier("mossy_stone_brick_stairs"), 64, 12974, 598, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TADPOLE_BUCKET = new Material<>(new Identifier("tadpole_bucket"), 1, -1,
			876, false, true, null);
	public static final Material<BlockMeta> CHERRY_WALL_SIGN = new Material<>(new Identifier("cherry_wall_sign"), 16,
			4791, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_JUNGLE_LOG = new Material<>(new Identifier("stripped_jungle_log"),
			64, 165, 125, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUSIC_DISC_5 = new Material<>(new Identifier("music_disc_5"), 1, -1, 1130,
			false, true, null);
	public static final Material<BlockMeta> DEAD_BUSH = new Material<>(new Identifier("dead_bush"), 64, 2003, 176, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_WALL_HANGING_SIGN = new Material<>(
			new Identifier("warped_wall_hanging_sign"), 16, 5607, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DAMAGED_ANVIL = new Material<>(new Identifier("damaged_anvil"), 64, 8971,
			397, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_DYE = new Material<>(new Identifier("red_dye"), 64, -1, 914, false,
			true, null);
	public static final Material<BlockMeta> DARK_OAK_CHEST_BOAT = new Material<>(new Identifier("dark_oak_chest_boat"),
			1, -1, 745, false, true, null);
	public static final Material<BlockMeta> MAGENTA_STAINED_GLASS = new Material<>(
			new Identifier("magenta_stained_glass"), 64, 5944, 449, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_POPPY = new Material<>(new Identifier("potted_poppy"), 64, 8575, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LAPIS_LAZULI = new Material<>(new Identifier("lapis_lazuli"), 64, -1, 762,
			false, true, null);
	public static final Material<BlockMeta> MUSIC_DISC_WAIT = new Material<>(new Identifier("music_disc_wait"), 1, -1,
			1128, false, true, null);
	public static final Material<BlockMeta> CHERRY_SIGN = new Material<>(new Identifier("cherry_sign"), 16, 4427, 847,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BUNDLE = new Material<>(new Identifier("bundle"), 1, -1, 886, false, true,
			null);
	public static final Material<BlockMeta> BREAD = new Material<>(new Identifier("bread"), 64, -1, 811, false, true,
			null);
	public static final Material<BlockMeta> LANTERN = new Material<>(new Identifier("lantern"), 64, 18347, 1157, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_SHOVEL = new Material<>(new Identifier("iron_shovel"), 1, -1, 789,
			false, true, null);
	public static final Material<BlockMeta> BLACK_BANNER = new Material<>(new Identifier("black_banner"), 16, 10854,
			1098, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CANDLE = new Material<>(new Identifier("candle"), 64, 20569, 1184, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_WOOL = new Material<>(new Identifier("brown_wool"), 64, 2055, 191,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARPED_WART_BLOCK = new Material<>(new Identifier("warped_wart_block"), 64,
			18434, 494, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COOKED_SALMON = new Material<>(new Identifier("cooked_salmon"), 64, -1, 896,
			false, true, null);
	public static final Material<BlockMeta> GREEN_CARPET = new Material<>(new Identifier("green_carpet"), 64, 10596,
			435, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_CANDLE_CAKE = new Material<>(new Identifier("gray_candle_cake"), 64,
			20855, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_EXPOSED_COPPER = new Material<>(
			new Identifier("waxed_exposed_copper"), 64, 21517, 94, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIORITE_WALL = new Material<>(new Identifier("diorite_wall"), 64, 17892,
			387, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> VEX_SPAWN_EGG = new Material<>(new Identifier("vex_spawn_egg"), 64, -1,
			1026, false, true, null);
	public static final Material<BlockMeta> MANGROVE_WALL_SIGN = new Material<>(new Identifier("mangrove_wall_sign"),
			16, 4815, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_PLANKS = new Material<>(new Identifier("spruce_planks"), 64, 16, 24,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPLASH_POTION = new Material<>(new Identifier("splash_potion"), 1, -1, 1107,
			false, true, null);
	public static final Material<BlockMeta> WRITTEN_BOOK = new Material<>(new Identifier("written_book"), 16, -1, 1043,
			false, true, null);
	public static final Material<BlockMeta> GHAST_TEAR = new Material<>(new Identifier("ghast_tear"), 64, -1, 950,
			false, true, null);
	public static final Material<BlockMeta> GLASS_PANE = new Material<>(new Identifier("glass_pane"), 64, 6807, 333,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SEA_PICKLE = new Material<>(new Identifier("sea_pickle"), 64, 12774, 178,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_OXIDIZED_CUT_COPPER = new Material<>(
			new Identifier("waxed_oxidized_cut_copper"), 64, 21519, 100, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIRT = new Material<>(new Identifier("dirt"), 64, 10, 15, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_WOOD = new Material<>(new Identifier("dark_oak_wood"), 64, 204,
			149, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AZALEA = new Material<>(new Identifier("azalea"), 64, 21967, 174, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEVER = new Material<>(new Identifier("lever"), 64, 5631, 647, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_BED = new Material<>(new Identifier("blue_bed"), 1, 1863, 931, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_BOAT = new Material<>(new Identifier("acacia_boat"), 1, -1, 740,
			false, true, null);
	public static final Material<BlockMeta> ACACIA_PRESSURE_PLATE = new Material<>(
			new Identifier("acacia_pressure_plate"), 64, 5721, 677, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ANDESITE_STAIRS = new Material<>(new Identifier("andesite_stairs"), 64,
			13614, 606, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SKELETON_HORSE_SPAWN_EGG = new Material<>(
			new Identifier("skeleton_horse_spawn_egg"), 64, -1, 1014, false, true, null);
	public static final Material<BlockMeta> POLISHED_ANDESITE_STAIRS = new Material<>(
			new Identifier("polished_andesite_stairs"), 64, 13774, 608, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COAL = new Material<>(new Identifier("coal"), 64, -1, 758, false, true,
			null);
	public static final Material<BlockMeta> JUNGLE_PRESSURE_PLATE = new Material<>(
			new Identifier("jungle_pressure_plate"), 64, 5719, 676, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_SLAB = new Material<>(new Identifier("bamboo_slab"), 64, 11068, 236,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CYAN_BED = new Material<>(new Identifier("cyan_bed"), 1, 1831, 929, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_PICKAXE = new Material<>(new Identifier("golden_pickaxe"), 1, -1,
			785, false, true, null);
	public static final Material<BlockMeta> SWEET_BERRIES = new Material<>(new Identifier("sweet_berries"), 64, -1,
			1159, false, true, null);
	public static final Material<BlockMeta> BOW = new Material<>(new Identifier("bow"), 1, -1, 756, false, true, null);
	public static final Material<BlockMeta> CRIMSON_ROOTS = new Material<>(new Identifier("crimson_roots"), 64, 18506,
			214, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOOTH_SANDSTONE = new Material<>(new Identifier("smooth_sandstone"), 64,
			11162, 259, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_BRICKS = new Material<>(new Identifier("deepslate_bricks"), 64,
			23283, 322, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBBLESTONE = new Material<>(new Identifier("cobblestone"), 64, 14, 22,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PLAYER_WALL_HEAD = new Material<>(new Identifier("player_wall_head"), 64,
			8899, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_WALL_HANGING_SIGN = new Material<>(
			new Identifier("mangrove_wall_hanging_sign"), 16, 5591, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRICK = new Material<>(new Identifier("brick"), 64, -1, 877, false, true,
			null);
	public static final Material<BlockMeta> YELLOW_DYE = new Material<>(new Identifier("yellow_dye"), 64, -1, 904,
			false, true, null);
	public static final Material<BlockMeta> BRICKS = new Material<>(new Identifier("bricks"), 64, 2089, 261, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_HANGING_SIGN = new Material<>(new Identifier("birch_hanging_sign"),
			16, 4991, 855, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SALMON_SPAWN_EGG = new Material<>(new Identifier("salmon_spawn_egg"), 64,
			-1, 1009, false, true, null);
	public static final Material<BlockMeta> LEATHER_HORSE_ARMOR = new Material<>(new Identifier("leather_horse_armor"),
			1, -1, 1077, false, true, null);
	public static final Material<BlockMeta> ORANGE_BANNER = new Material<>(new Identifier("orange_banner"), 16, 10630,
			1084, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TUBE_CORAL = new Material<>(new Identifier("tube_coral"), 64, 12664, 574,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_PROPAGULE = new Material<>(new Identifier("mangrove_propagule"),
			64, 44, 42, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PIG_SPAWN_EGG = new Material<>(new Identifier("pig_spawn_egg"), 64, -1,
			1001, false, true, null);
	public static final Material<BlockMeta> STONE_BRICK_SLAB = new Material<>(new Identifier("stone_brick_slab"), 64,
			11122, 247, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_PRISMARINE = new Material<>(new Identifier("dark_prismarine"), 64,
			10320, 481, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PURPLE_WALL_BANNER = new Material<>(new Identifier("purple_wall_banner"),
			16, 10910, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PAINTING = new Material<>(new Identifier("painting"), 64, -1, 839, false,
			true, null);
	public static final Material<BlockMeta> BROWN_SHULKER_BOX = new Material<>(new Identifier("brown_shulker_box"), 1,
			12488, 511, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_PINK_TULIP = new Material<>(new Identifier("potted_pink_tulip"), 64,
			8582, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_CANDLE_CAKE = new Material<>(new Identifier("magenta_candle_cake"),
			64, 20845, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_BRICK_FENCE = new Material<>(new Identifier("nether_brick_fence"),
			64, 7300, 345, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_SLAB = new Material<>(new Identifier("birch_slab"), 64, 11032, 230,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LILY_PAD = new Material<>(new Identifier("lily_pad"), 64, 7267, 341, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PIGLIN_HEAD = new Material<>(new Identifier("piglin_head"), 64, 8943, 1059,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ANDESITE = new Material<>(new Identifier("andesite"), 64, 6, 6, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> REPEATING_COMMAND_BLOCK = new Material<>(
			new Identifier("repeating_command_block"), 64, 12365, 490, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PACKED_ICE = new Material<>(new Identifier("packed_ice"), 64, 10601, 439,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ECHO_SHARD = new Material<>(new Identifier("echo_shard"), 64, -1, 1210,
			false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_TILES = new Material<>(new Identifier("deepslate_tiles"), 64,
			22872, 324, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_WALL_HANGING_SIGN = new Material<>(
			new Identifier("crimson_wall_hanging_sign"), 16, 5599, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENCHANTING_TABLE = new Material<>(new Identifier("enchanting_table"), 64,
			7385, 351, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLOW_INK_SAC = new Material<>(new Identifier("glow_ink_sac"), 64, -1, 898,
			false, true, null);
	public static final Material<BlockMeta> EXPOSED_CUT_COPPER = new Material<>(new Identifier("exposed_cut_copper"),
			64, 21169, 82, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WAXED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("waxed_cut_copper_stairs"), 64, 21774, 101, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_LOG = new Material<>(new Identifier("dark_oak_log"), 64, 145, 115,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POINTED_DRIPSTONE = new Material<>(new Identifier("pointed_dripstone"), 64,
			21896, 1205, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COAST_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("coast_armor_trim_smithing_template"), 64, -1, 1215, false, true, null);
	public static final Material<BlockMeta> NETHERITE_SCRAP = new Material<>(new Identifier("netherite_scrap"), 64, -1,
			772, false, true, null);
	public static final Material<BlockMeta> RED_NETHER_BRICK_WALL = new Material<>(
			new Identifier("red_nether_brick_wall"), 64, 16920, 384, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENDER_EYE = new Material<>(new Identifier("ender_eye"), 64, -1, 961, false,
			true, null);
	public static final Material<BlockMeta> LIGHT_BLUE_CANDLE_CAKE = new Material<>(
			new Identifier("light_blue_candle_cake"), 64, 20847, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CUT_SANDSTONE = new Material<>(new Identifier("cut_sandstone"), 64, 533,
			170, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STONE_BRICK_STAIRS = new Material<>(new Identifier("stone_brick_stairs"),
			64, 7116, 338, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_SLAB = new Material<>(new Identifier("jungle_slab"), 64, 11038, 231,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CACTUS = new Material<>(new Identifier("cactus"), 64, 5778, 284, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("gray_glazed_terracotta"), 64, 12536, 522, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_BUTTON = new Material<>(new Identifier("birch_button"), 64, 8664, 660,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_SANDSTONE_SLAB = new Material<>(new Identifier("red_sandstone_slab"),
			64, 11146, 251, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BROWN_DYE = new Material<>(new Identifier("brown_dye"), 64, -1, 912, false,
			true, null);
	public static final Material<BlockMeta> OXIDIZED_CUT_COPPER = new Material<>(new Identifier("oxidized_cut_copper"),
			64, 21167, 84, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_BED = new Material<>(new Identifier("light_gray_bed"), 1, 1815,
			928, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRICK_SLAB = new Material<>(new Identifier("brick_slab"), 64, 11116, 246,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_LEAVES = new Material<>(new Identifier("spruce_leaves"), 64, 288,
			154, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COMMAND_BLOCK_MINECART = new Material<>(
			new Identifier("command_block_minecart"), 1, -1, 1080, false, true, null);
	public static final Material<BlockMeta> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = new Material<>(
			new Identifier("dune_armor_trim_smithing_template"), 64, -1, 1214, false, true, null);
	public static final Material<BlockMeta> SEA_LANTERN = new Material<>(new Identifier("sea_lantern"), 64, 10579, 485,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_SHULKER_BOX = new Material<>(new Identifier("green_shulker_box"), 1,
			12494, 512, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> OAK_DOOR = new Material<>(new Identifier("oak_door"), 64, 4597, 685, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_CHEST_BOAT = new Material<>(new Identifier("birch_chest_boat"), 1, -1,
			737, false, true, null);
	public static final Material<BlockMeta> DARK_OAK_STAIRS = new Material<>(new Identifier("dark_oak_stairs"), 64,
			9911, 365, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_MANGROVE_WOOD = new Material<>(
			new Identifier("stripped_mangrove_wood"), 64, 231, 139, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SMOKER = new Material<>(new Identifier("smoker"), 64, 18262, 1149, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CREEPER_WALL_HEAD = new Material<>(new Identifier("creeper_wall_head"), 64,
			8919, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PIGLIN_WALL_HEAD = new Material<>(new Identifier("piglin_wall_head"), 64,
			8959, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHER_WART = new Material<>(new Identifier("nether_wart"), 64, 7381, 952,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SKULL_BANNER_PATTERN = new Material<>(
			new Identifier("skull_banner_pattern"), 1, -1, 1142, false, true, null);
	public static final Material<BlockMeta> FIREWORK_STAR = new Material<>(new Identifier("firework_star"), 64, -1,
			1063, false, true, null);
	public static final Material<BlockMeta> PURPLE_CARPET = new Material<>(new Identifier("purple_carpet"), 64, 10593,
			432, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_HOE = new Material<>(new Identifier("golden_hoe"), 1, -1, 787, false,
			true, null);
	public static final Material<BlockMeta> COOKED_CHICKEN = new Material<>(new Identifier("cooked_chicken"), 64, -1,
			946, false, true, null);
	public static final Material<BlockMeta> BIRCH_WALL_HANGING_SIGN = new Material<>(
			new Identifier("birch_wall_hanging_sign"), 16, 5551, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DOLPHIN_SPAWN_EGG = new Material<>(new Identifier("dolphin_spawn_egg"), 64,
			-1, 975, false, true, null);
	public static final Material<BlockMeta> WHITE_CANDLE = new Material<>(new Identifier("white_candle"), 64, 20585,
			1185, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_BOAT = new Material<>(new Identifier("dark_oak_boat"), 1, -1, 744,
			false, true, null);
	public static final Material<BlockMeta> GOLDEN_BOOTS = new Material<>(new Identifier("golden_boots"), 1, -1, 831,
			false, true, null);
	public static final Material<BlockMeta> BLUE_ORCHID = new Material<>(new Identifier("blue_orchid"), 64, 2074, 197,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SQUID_SPAWN_EGG = new Material<>(new Identifier("squid_spawn_egg"), 64, -1,
			1019, false, true, null);
	public static final Material<BlockMeta> MELON_STEM = new Material<>(new Identifier("melon_stem"), 64, 6825, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_GRAY_CANDLE = new Material<>(new Identifier("light_gray_candle"), 64,
			20713, 1193, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_FERN = new Material<>(new Identifier("potted_fern"), 64, 8573, -1,
			true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WARDEN_SPAWN_EGG = new Material<>(new Identifier("warden_spawn_egg"), 64,
			-1, 1030, false, true, null);
	public static final Material<BlockMeta> GOLDEN_CARROT = new Material<>(new Identifier("golden_carrot"), 64, -1,
			1052, false, true, null);
	public static final Material<BlockMeta> STONE = new Material<>(new Identifier("stone"), 64, 1, 1, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_SIGN = new Material<>(new Identifier("crimson_sign"), 16, 19118,
			851, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DARK_OAK_TRAPDOOR = new Material<>(new Identifier("dark_oak_trapdoor"), 64,
			6357, 703, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PRISMARINE_WALL = new Material<>(new Identifier("prismarine_wall"), 64,
			14328, 376, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGMA_CREAM = new Material<>(new Identifier("magma_cream"), 64, -1, 958,
			false, true, null);
	public static final Material<BlockMeta> GHAST_SPAWN_EGG = new Material<>(new Identifier("ghast_spawn_egg"), 64, -1,
			985, false, true, null);
	public static final Material<BlockMeta> PINK_BANNER = new Material<>(new Identifier("pink_banner"), 16, 10710, 1089,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EXPOSED_CUT_COPPER_SLAB = new Material<>(
			new Identifier("exposed_cut_copper_slab"), 64, 21506, 90, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MELON_SEEDS = new Material<>(new Identifier("melon_seeds"), 64, -1, 942,
			false, true, null);
	public static final Material<BlockMeta> RED_SANDSTONE = new Material<>(new Identifier("red_sandstone"), 64, 10934,
			486, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COBBLED_DEEPSLATE_WALL = new Material<>(
			new Identifier("cobbled_deepslate_wall"), 64, 22140, 391, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CHISELED_RED_SANDSTONE = new Material<>(
			new Identifier("chiseled_red_sandstone"), 64, 10935, 487, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> AXOLOTL_SPAWN_EGG = new Material<>(new Identifier("axolotl_spawn_egg"), 64,
			-1, 964, false, true, null);
	public static final Material<BlockMeta> WHITE_SHULKER_BOX = new Material<>(new Identifier("white_shulker_box"), 1,
			12416, 499, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PACKED_MUD = new Material<>(new Identifier("packed_mud"), 64, 6538, 320,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_BANNER = new Material<>(new Identifier("lime_banner"), 16, 10694, 1088,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_LEGGINGS = new Material<>(new Identifier("golden_leggings"), 1, -1,
			830, false, true, null);
	public static final Material<BlockMeta> DARK_OAK_SAPLING = new Material<>(new Identifier("dark_oak_sapling"), 64,
			37, 41, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POLISHED_DIORITE_STAIRS = new Material<>(
			new Identifier("polished_diorite_stairs"), 64, 13054, 599, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ENDERMITE_SPAWN_EGG = new Material<>(new Identifier("endermite_spawn_egg"),
			64, -1, 981, false, true, null);
	public static final Material<BlockMeta> OAK_CHEST_BOAT = new Material<>(new Identifier("oak_chest_boat"), 1, -1,
			733, false, true, null);
	public static final Material<BlockMeta> TUBE_CORAL_FAN = new Material<>(new Identifier("tube_coral_fan"), 64, 12684,
			584, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_DYE = new Material<>(new Identifier("magenta_dye"), 64, -1, 902,
			false, true, null);
	public static final Material<BlockMeta> CRIMSON_FUNGUS = new Material<>(new Identifier("crimson_fungus"), 64, 18450,
			212, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LEATHER = new Material<>(new Identifier("leather"), 64, -1, 869, false,
			true, null);
	public static final Material<BlockMeta> CRACKED_NETHER_BRICKS = new Material<>(
			new Identifier("cracked_nether_bricks"), 64, 20564, 343, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_DARK_OAK_SAPLING = new Material<>(
			new Identifier("potted_dark_oak_sapling"), 64, 8571, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> POTTED_FLOWERING_AZALEA_BUSH = new Material<>(
			new Identifier("potted_flowering_azalea_bush"), 64, 23705, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MANGROVE_PRESSURE_PLATE = new Material<>(
			new Identifier("mangrove_pressure_plate"), 64, 5727, 680, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BIRCH_BOAT = new Material<>(new Identifier("birch_boat"), 1, -1, 736, false,
			true, null);
	public static final Material<BlockMeta> COMMAND_BLOCK = new Material<>(new Identifier("command_block"), 64, 7908,
			371, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WANDERING_TRADER_SPAWN_EGG = new Material<>(
			new Identifier("wandering_trader_spawn_egg"), 64, -1, 1029, false, true, null);
	public static final Material<BlockMeta> TUFF = new Material<>(new Identifier("tuff"), 64, 20922, 12, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRAY_CONCRETE = new Material<>(new Identifier("gray_concrete"), 64, 12579,
			538, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_BLUE_SHULKER_BOX = new Material<>(
			new Identifier("light_blue_shulker_box"), 1, 12434, 502, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_CANDLE = new Material<>(new Identifier("green_candle"), 64, 20793,
			1198, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_WALL_BANNER = new Material<>(new Identifier("blue_wall_banner"), 16,
			10914, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_NYLIUM = new Material<>(new Identifier("crimson_nylium"), 64, 18449,
			20, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WEATHERED_CUT_COPPER = new Material<>(
			new Identifier("weathered_cut_copper"), 64, 21168, 83, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACACIA_WOOD = new Material<>(new Identifier("acacia_wood"), 64, 198, 147,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LARGE_AMETHYST_BUD = new Material<>(new Identifier("large_amethyst_bud"),
			64, 20895, 1203, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BAMBOO_BUTTON = new Material<>(new Identifier("bamboo_button"), 64, 8808,
			666, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ORANGE_CANDLE_CAKE = new Material<>(new Identifier("orange_candle_cake"),
			64, 20843, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIME_STAINED_GLASS = new Material<>(new Identifier("lime_stained_glass"),
			64, 5947, 452, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGENTA_SHULKER_BOX = new Material<>(new Identifier("magenta_shulker_box"),
			1, 12428, 501, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MUD_BRICK_SLAB = new Material<>(new Identifier("mud_brick_slab"), 64, 11128,
			248, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GLOBE_BANNER_PATTERN = new Material<>(
			new Identifier("globe_banner_pattern"), 1, -1, 1144, false, true, null);
	public static final Material<BlockMeta> POLISHED_DEEPSLATE_STAIRS = new Material<>(
			new Identifier("polished_deepslate_stairs"), 64, 22473, 611, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GOLDEN_APPLE = new Material<>(new Identifier("golden_apple"), 64, -1, 840,
			false, true, null);
	public static final Material<BlockMeta> CHERRY_TRAPDOOR = new Material<>(new Identifier("cherry_trapdoor"), 64,
			6293, 702, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EVOKER_SPAWN_EGG = new Material<>(new Identifier("evoker_spawn_egg"), 64,
			-1, 982, false, true, null);
	public static final Material<BlockMeta> LEAD = new Material<>(new Identifier("lead"), 64, -1, 1078, false, true,
			null);
	public static final Material<BlockMeta> STONE_HOE = new Material<>(new Identifier("stone_hoe"), 1, -1, 782, false,
			true, null);
	public static final Material<BlockMeta> BEETROOT_SEEDS = new Material<>(new Identifier("beetroot_seeds"), 64, -1,
			1104, false, true, null);
	public static final Material<BlockMeta> PANDA_SPAWN_EGG = new Material<>(new Identifier("panda_spawn_egg"), 64, -1,
			998, false, true, null);
	public static final Material<BlockMeta> CORNFLOWER = new Material<>(new Identifier("cornflower"), 64, 2082, 205,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SHULKER_SHELL = new Material<>(new Identifier("shulker_shell"), 64, -1,
			1113, false, true, null);
	public static final Material<BlockMeta> DISC_FRAGMENT_5 = new Material<>(new Identifier("disc_fragment_5"), 64, -1,
			1132, false, true, null);
	public static final Material<BlockMeta> ORANGE_SHULKER_BOX = new Material<>(new Identifier("orange_shulker_box"), 1,
			12422, 500, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BARRIER = new Material<>(new Identifier("barrier"), 64, 10221, 419, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BEETROOTS = new Material<>(new Identifier("beetroots"), 64, 12353, -1, true,
			false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RABBIT_HIDE = new Material<>(new Identifier("rabbit_hide"), 64, -1, 1072,
			false, true, null);
	public static final Material<BlockMeta> PILLAGER_SPAWN_EGG = new Material<>(new Identifier("pillager_spawn_egg"),
			64, -1, 1004, false, true, null);
	public static final Material<BlockMeta> CAMPFIRE = new Material<>(new Identifier("campfire"), 64, 18355, 1161, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DEEPSLATE_BRICK_WALL = new Material<>(
			new Identifier("deepslate_brick_wall"), 64, 23373, 393, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_BOAT = new Material<>(new Identifier("jungle_boat"), 1, -1, 738,
			false, true, null);
	public static final Material<BlockMeta> BAMBOO_FENCE = new Material<>(new Identifier("bamboo_fence"), 64, 11676,
			295, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> EXPOSED_CUT_COPPER_STAIRS = new Material<>(
			new Identifier("exposed_cut_copper_stairs"), 64, 21342, 86, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SALMON = new Material<>(new Identifier("salmon"), 64, -1, 892, false, true,
			null);
	public static final Material<BlockMeta> FOX_SPAWN_EGG = new Material<>(new Identifier("fox_spawn_egg"), 64, -1, 983,
			false, true, null);
	public static final Material<BlockMeta> DIAMOND_HOE = new Material<>(new Identifier("diamond_hoe"), 1, -1, 797,
			false, true, null);
	public static final Material<BlockMeta> POLISHED_BLACKSTONE_BRICK_SLAB = new Material<>(
			new Identifier("polished_blackstone_brick_slab"), 64, 19719, 1180, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TWISTING_VINES = new Material<>(new Identifier("twisting_vines"), 64, 18479,
			218, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TURTLE_EGG = new Material<>(new Identifier("turtle_egg"), 64, 12632, 563,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> RED_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("red_glazed_terracotta"), 64, 12564, 529, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> COAL_ORE = new Material<>(new Identifier("coal_ore"), 64, 123, 48, true,
			true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BRICK_STAIRS = new Material<>(new Identifier("brick_stairs"), 64, 7036, 337,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> DIORITE_STAIRS = new Material<>(new Identifier("diorite_stairs"), 64, 13854,
			609, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> STRIPPED_SPRUCE_WOOD = new Material<>(
			new Identifier("stripped_spruce_wood"), 64, 213, 133, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_WALL_SIGN = new Material<>(new Identifier("crimson_wall_sign"), 16,
			19182, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> WHITE_CANDLE_CAKE = new Material<>(new Identifier("white_candle_cake"), 64,
			20841, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SPRUCE_BUTTON = new Material<>(new Identifier("spruce_button"), 64, 8640,
			659, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ZOMBIE_WALL_HEAD = new Material<>(new Identifier("zombie_wall_head"), 64,
			8879, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MAGMA_CUBE_SPAWN_EGG = new Material<>(
			new Identifier("magma_cube_spawn_egg"), 64, -1, 994, false, true, null);
	public static final Material<BlockMeta> DEEPSLATE_TILE_WALL = new Material<>(new Identifier("deepslate_tile_wall"),
			64, 22962, 394, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_LOG = new Material<>(new Identifier("jungle_log"), 64, 136, 112,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> IRON_PICKAXE = new Material<>(new Identifier("iron_pickaxe"), 1, -1, 790,
			false, true, null);
	public static final Material<BlockMeta> DARK_OAK_SLAB = new Material<>(new Identifier("dark_oak_slab"), 64, 11056,
			234, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> NETHERRACK = new Material<>(new Identifier("netherrack"), 64, 5846, 301,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> LIGHT_WEIGHTED_PRESSURE_PLATE = new Material<>(
			new Identifier("light_weighted_pressure_plate"), 64, 8999, 671, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> ACTIVATOR_RAIL = new Material<>(new Identifier("activator_rail"), 64, 9189,
			722, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> SHULKER_BOX = new Material<>(new Identifier("shulker_box"), 1, 12410, 498,
			true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> JUNGLE_WALL_HANGING_SIGN = new Material<>(
			new Identifier("jungle_wall_hanging_sign"), 16, 5575, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> CRIMSON_PRESSURE_PLATE = new Material<>(
			new Identifier("crimson_pressure_plate"), 64, 18522, 682, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> TORCHFLOWER_SEEDS = new Material<>(new Identifier("torchflower_seeds"), 64,
			-1, 1102, false, true, null);
	public static final Material<BlockMeta> HOPPER_MINECART = new Material<>(new Identifier("hopper_minecart"), 1, -1,
			728, false, true, null);
	public static final Material<BlockMeta> YELLOW_GLAZED_TERRACOTTA = new Material<>(
			new Identifier("yellow_glazed_terracotta"), 64, 12524, 519, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GREEN_CANDLE_CAKE = new Material<>(new Identifier("green_candle_cake"), 64,
			20867, -1, true, false, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> BLUE_STAINED_GLASS_PANE = new Material<>(
			new Identifier("blue_stained_glass_pane"), 64, 9611, 474, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> MOSSY_STONE_BRICK_WALL = new Material<>(
			new Identifier("mossy_stone_brick_wall"), 64, 14976, 378, true, true, DEFAULT_CONVERTER);
	public static final Material<BlockMeta> GRANITE = new Material<>(new Identifier("granite"), 64, 2, 2, true, true,
			DEFAULT_CONVERTER);
	public static final Material<BlockMeta> PINK_CONCRETE = new Material<>(new Identifier("pink_concrete"), 64, 12578,
			537, true, true, DEFAULT_CONVERTER);
	private final Identifier id;
	private final int maxStack;
	private final int blockId;
	private final boolean isItem;
	private final boolean isBlock;
	private final int itemId;
	private final IBlockMetaConverter<meta> converter;

	public Material(@NotNull Identifier id, int maxStack, int blockId, int itemId, boolean isBlock, boolean isItem,
			IBlockMetaConverter<meta> converter) {
		if (maxStack <= 0)
			throw new IllegalArgumentException(String.format("Max Stack(%d) cannot be negative or zero", maxStack));
		this.id = id;
		this.maxStack = maxStack;
		this.blockId = blockId;
		this.isBlock = isBlock;
		this.isItem = isItem;
		this.itemId = itemId;
		this.converter = converter;
		ids.put(id.toString(), this);
	}

	public Identifier id() {
		return this.id;
	}

	public int blockId() {
		return this.blockId;
	}

	public int maxStack() {
		return this.maxStack;
	}

	public boolean isItem() {
		return this.isItem;
	}

	public boolean isBlock() {
		return this.isBlock;
	}

	public int itemId() {
		return this.itemId;
	}

	public meta convert(@NotNull BlockState state) {
		if (!isBlock())
			throw new IllegalStateException("Not a block");
		return this.converter.convert(state);
	}

	public static Material<?> fromId(@NotNull Identifier id) {
		return fromId(id.toString());
	}

	public static Material<?> fromId(@NotNull String id) {
		if (!id.contains(":"))
			id = "minecraft:" + id;
		return ids.get(id);
	}
}