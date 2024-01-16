package pl.ttpsc.javaupdate.project.utility;

public interface MenuComponent {
    default void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    String getName();

    void getNameWithSeparator();

    default void execute() {
        throw new UnsupportedOperationException();
    }

}
