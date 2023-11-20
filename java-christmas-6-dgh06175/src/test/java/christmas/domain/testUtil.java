package christmas.domain;

import christmas.domain.records.MenuQuantity;
import christmas.domain.enums.Menu;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class testUtil {
    public static LocalDate eventDate = LocalDate.of(2023, 12, 1);

    public static OrderedMenu createBigOrder() {
        List<MenuQuantity> orderDetails = new ArrayList<>();
        orderDetails.add(new MenuQuantity(Menu.T_BONE_STEAK, 1));
        orderDetails.add(new MenuQuantity(Menu.BARBECUE_RIBS, 2));
        orderDetails.add(new MenuQuantity(Menu.TAPAS, 1));
        orderDetails.add(new MenuQuantity(Menu.CHRISTMAS_PASTA, 1));
        orderDetails.add(new MenuQuantity(Menu.ICE_CREAM, 3));
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSimpleOrder() {
        List<MenuQuantity> orderDetails = new ArrayList<>();
        orderDetails.add(new MenuQuantity(Menu.TAPAS, 1));
        orderDetails.add(new MenuQuantity(Menu.ZERO_COLA, 1));
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSampleOrder1() {
        List<MenuQuantity> orderDetails = new ArrayList<>();
        orderDetails.add(new MenuQuantity(Menu.T_BONE_STEAK, 2));
        orderDetails.add(new MenuQuantity(Menu.BARBECUE_RIBS, 1));
        return new OrderedMenu(orderDetails);
    }

    public static OrderedMenu createSampleOrder2() {
        List<MenuQuantity> orderDetails = new ArrayList<>();
        orderDetails.add(new MenuQuantity(Menu.MUSHROOM_SOUP, 1));
        orderDetails.add(new MenuQuantity(Menu.ZERO_COLA, 2));
        return new OrderedMenu(orderDetails);
    }
}
