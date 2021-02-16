package haveric.dieSheep;

import org.bukkit.Material;

public class DSTools {
    public static boolean isWool(Material material) {
        switch (material) {
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
                return true;
            default:
                return false;
        }
    }
}
