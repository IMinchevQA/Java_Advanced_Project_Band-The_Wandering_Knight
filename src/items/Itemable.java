package items;


public enum  Itemable {

    COIN("coin", "coin", 0),
    WOOD("cutDownTree", "Wood", 1),
    ROCK("rockItem", "rock", 2),
    MEAT("meat", "meat", 3),
    KEY("key", "key", 4);

    private String asset;
    private String name;
    private int id;

    Itemable(String asset, String name, int id){
        this.asset = asset;
        this.name = name;
        this.id = id;
    }

    public String getAsset() {
        return this.asset;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
