package inf112.skeleton.app;

public interface ICard {
    void playCard(IPlayer owner);

    String getCommand();

    Integer getValue();

    void setValue(Integer newValue);

    void setOwner(IPlayer player);

    IPlayer getOwner();

    void clearOwner();
}
