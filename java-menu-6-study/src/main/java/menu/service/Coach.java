package menu.service;

import menu.exception.InvalidInputException;

public class Coach {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 4;
    private static final int MIN_BANNED_MENU_SIZE = 0;
    private static final int MAX_BANNED_MENU_SIZE = 2;

    private final String name;
    private final Menus bannedMenus;

    public Coach (String name, Menus bannedMenus) {
        validateName(name);
        validateBannedMenus(bannedMenus);
        this.name = name;
        this.bannedMenus = bannedMenus;
    }

    public boolean isBannedMenu(String menu) {
        return bannedMenus.hasMenu(menu);
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new InvalidInputException("이름의 길이는 최소 2글자, 최대 4글자 입니다.");
        }
    }

    private void validateBannedMenus(Menus bannedMenus) {
        if (bannedMenus.size() < MIN_BANNED_MENU_SIZE || bannedMenus.size() > MAX_BANNED_MENU_SIZE) {
            throw new InvalidInputException("먹지 못하는 메뉴는 최소 0개, 최대 2개 입니다.");
        }
    }
}
