package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;


public class Spawnpoint {
    private Vector2 SpawnLoc;
    private Integer SpawnId;

    public Spawnpoint(Vector2 loc, Integer tileId){
        this.SpawnLoc = loc;
        System.out.println("Spawnpoint in the making");
        switch (tileId){
            case 121:
                this.SpawnId = 1;
                break;
            case 122:
                this.SpawnId = 2;
                break;
            case 123:
                this.SpawnId = 3;
                break;
            case 124:
                this.SpawnId= 4;
                break;
            case 129:
                this.SpawnId= 5;
                break;
            case 130:
                this.SpawnId= 6;
                break;
            case 131:
                this.SpawnId= 7;
                break;
            case 132:
                this.SpawnId= 8;
                break;
        }
    }

    public Integer getSpawnId() {
        return SpawnId;
    }
    public Vector2 getSpawnLoc() {
        return SpawnLoc;
    }
}
