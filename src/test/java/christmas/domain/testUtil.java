package christmas.domain;

import christmas.domain.enums.Menu;
import java.util.EnumMap;

public class testUtil {
    public static OrderedMenu createBigOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.T_BONE_STEAK, 1);
        orderDetails.put(Menu.BARBECUE_RIBS, 2);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.CHRISTMAS_PASTA, 1);
        orderDetails.put(Menu.ICE_CREAM, 3);
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSimpleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.ZERO_COLA, 1);
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSampleOrder1() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.T_BONE_STEAK, 2);
        orderDetails.put(Menu.BARBECUE_RIBS, 1);
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSampleOrder2() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.MUSHROOM_SOUP, 1);
        orderDetails.put(Menu.ZERO_COLA, 2);
        return new OrderedMenu(orderDetails);
    }
}
