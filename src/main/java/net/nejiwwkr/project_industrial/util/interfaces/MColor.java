package net.nejiwwkr.project_industrial.util.interfaces;

import net.nejiwwkr.project_industrial.item.ChemicalItemWithColor;

/**
 * <p>该接口应被Item及其子类实现</p>
 * <p>用于校验模组物品具有颜色的属性</p>
 * @see ChemicalItemWithColor
 * @since 1.0.0
 * @author Nejiwwkr
 */
public interface MColor extends ContainsTranslatableText {
    default int getColor() {
        return 0xff0000;
    }
}
