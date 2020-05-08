package inf112.skeleton.app.Interfaces;

public interface ICard {
    void playCard(IPlayer owner);

    String getCommand();

    Integer getValue();

    void setValue(Integer newValue);

    void setOwner(IPlayer player);

    IPlayer getOwner();

    void clearOwner();
}
