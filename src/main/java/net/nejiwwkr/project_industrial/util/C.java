package net.nejiwwkr.project_industrial.util;

import net.nejiwwkr.project_industrial.util.annotation.Essential;

/**
 * A class contains all the consts in the mod
 */
@Essential
public class C {
    public static final String MOD_ID = "project_industrial";

    public static final class color {
        public static final int Ca = 0xffec6941;
        public static final int Pb = 0xff989ccd;
        public static final int K = 0xffa781df;
        public static final int Mo = 0xffabcd98;
    }

    public static final class Prefix {
        public static final String MAIN_INGREDIENT = "project_industrial.alloy.main_ingredient.";
        public static final String SIDE_INGREDIENT = "project_industrial.alloy.side_ingredient.";
        public static final String ALLOY_USAGE = "project_industrial.alloy_usage";
    }

    public static final class Suffix {
        public static final String ON_TOOL = ".on_tool";
        public static final String ON_ARMOR = ".on_armor";
    }
}
