package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Enums.Direction;
import inf112.skeleton.app.Enums.PlayerState;
import inf112.skeleton.app.Enums.TurnDirection;
import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.Interfaces.IPlayer;

import java.util.ArrayList;

public class Board implements IBoard {
    // The map itself
    private TiledMap Map;
    // Map layers
    private TiledMapTileLayer Board;
    private TiledMapTileLayer ConveyorBelts;
    private TiledMapTileLayer TurnGears;
    private TiledMapTileLayer Holes;
    private TiledMapTileLayer Spawnpoints;
    public TiledMapTileLayer Flags;
    private TiledMapTileLayer PlayerLayer;
    private TiledMapTileLayer Laser;
    private TiledMapTileLayer Wall;
    private TiledMapTileLayer RepairTiles;
    //Other values
    private Integer flagCount;
    private ArrayList<IPlayer> players;
    private ArrayList<LaserWall> laserWallLists;
    private ArrayList<Spawnpoint> spawnpointList;
    //Sound effects
    private Sound fallSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/fall.wav"));
    private Sound flagSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/flag.wav"));
    private Sound victorySound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/victory.mp3"));
    private Sound gameoversound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gameover.wav"));


    public Board(TiledMap Map){
        // Takes in tiledMap from input
        this.Map = Map;
        // Gets the layers from the Map
        this.Board = (TiledMapTileLayer) Map.getLayers().get("Board");
        this.ConveyorBelts = (TiledMapTileLayer) Map.getLayers().get("ConveyorBelts");
        this.TurnGears = (TiledMapTileLayer) Map.getLayers().get("TurnGears");
        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Spawnpoints = (TiledMapTileLayer) Map.getLayers().get("Spawn");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.PlayerLayer = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
        this.Laser = (TiledMapTileLayer) Map.getLayers().get("Laser");
        this.Wall = (TiledMapTileLayer) Map.getLayers().get("Walls");
        this.RepairTiles = (TiledMapTileLayer) Map.getLayers().get("RepairTiles");
        this.flagCount = countFlags();
        this.laserWallLists = setUpLaserWalls();
        this.spawnpointList = findSpawnPoints();
    }
    private Integer countFlags(){
        Integer flagCount = 0;
        Integer mapHeight = Flags.getHeight();
        Integer mapWidth = Flags.getWidth();
        for(Integer i = 0; i < mapHeight; i++){
            for(Integer j = 0; j < mapWidth; j++){
                if(Flags.getCell(j,i) != null){
                    flagCount++;
                }
            }
        }
        return flagCount;
    }

    @Override
    public void checkForSpecialTiles(IPlayer player){
        // Coordinates of the player/AI used to check for other things in the map
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        // Checks through maplayers for overlap and gives appropriate response
        checkBelts(player , xLoc, yLoc);
        checkTurnGears(player, xLoc, yLoc);
        checkRepairTiles(player, xLoc, yLoc);
        checkFlags(player, xLoc, yLoc);
    }
    private void checkFlags(IPlayer player, Integer xLoc, Integer yLoc){
        if (Flags.getCell(xLoc, yLoc) != null) {
            Integer tileId = Flags.getCell(xLoc, yLoc).getTile().getId();
            Vector2 newSpawnPoint = new Vector2(xLoc, yLoc);
            if (tileId == 55 && player.getFlags() == 0){
                player.visitFlag();
                flagSound.play();
                player.setSpawnPoint(newSpawnPoint);
            }
            else if (tileId == 63 && player.getFlags() == 1){
                player.visitFlag();
                flagSound.play();
                player.setSpawnPoint(newSpawnPoint);
            }
            else if (tileId == 71 && player.getFlags() == 2){
                player.visitFlag();
                flagSound.play();
                player.setSpawnPoint(newSpawnPoint);
            }
            else if (tileId == 79 && player.getFlags() == 3){
                player.visitFlag();
                flagSound.play();
                player.setSpawnPoint(newSpawnPoint);
            }
            if (player.getFlags() == flagCount && player.getPlayerState() != PlayerState.WINNER) {
                player.setPlayerState(PlayerState.WINNER);
                if (player.isYou()) {
                    victorySound.play(0.5f);
                }
                else {
                    gameoversound.play();
                }
            }
        }
    }
    public void checkHoles(IPlayer player){
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        if (Holes.getCell(xLoc, yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
            if(player.isPlayer()) {
                fallSound.play();
            }
        }
    }
    private void checkTurnGears(IPlayer player, Integer xLoc, Integer yLoc){
        if (TurnGears.getCell(xLoc, yLoc) != null){
            Integer tileId = TurnGears.getCell(xLoc, yLoc).getTile().getId();
            // Turn right
            if(tileId == 54){
                player.Turn(TurnDirection.RIGHT);
            }
            // Turn left
            else if(tileId == 53){
                player.Turn(TurnDirection.LEFT);
            }
        }
    }
    private void checkRepairTiles(IPlayer player, Integer xLoc, Integer yLoc){
        if (RepairTiles.getCell(xLoc, yLoc) != null){
            Integer tileId = RepairTiles.getCell(xLoc, yLoc).getTile().getId();
            if (tileId == 15){
                player.takeDamage(-1);
            }
            else if(tileId == 7){
                player.takeDamage(-2);
            }
        }
    }
    private void checkBelts(IPlayer player, Integer xLoc, Integer yLoc){
        if (ConveyorBelts.getCell(xLoc, yLoc) != null){
            Integer tileId = ConveyorBelts.getCell(xLoc, yLoc).getTile().getId();
            checkFastBelts(player, tileId);
            Integer xLoc2 = player.getX();
            Integer yLoc2 = player.getY();
            tileId = ConveyorBelts.getCell(xLoc2, yLoc2).getTile().getId();
            // Yellow belts
            if (tileId == 42 || tileId == 43 || tileId == 49 || tileId == 57 || tileId == 65 || tileId == 69 ||
                    tileId == 13 || tileId == 26 || tileId == 27 || tileId == 73 || tileId == 77 || tileId == 84){
                player.Move(Direction.NORTH);
            }
            else if(tileId == 33 || tileId == 36 || tileId == 50 || tileId == 59 || tileId == 62 || tileId == 67 ||
                    tileId == 17 || tileId == 20 || tileId == 21 || tileId == 75 || tileId == 82 || tileId == 86){
                player.Move(Direction.SOUTH);
            }
            else if(tileId == 35 || tileId == 41 || tileId == 52 || tileId == 58 || tileId == 61 || tileId == 66 ||
                    tileId == 14 || tileId == 19 || tileId == 25 || tileId == 74 || tileId == 78 || tileId == 81){
                player.Move(Direction.EAST);
            }
            else if(tileId == 34 || tileId == 44 || tileId == 51 || tileId == 60 || tileId == 68 || tileId == 70 ||
                    tileId == 18 || tileId == 22 || tileId == 28 || tileId == 76 || tileId == 83 || tileId == 85){
                player.Move(Direction.WEST);
            }
        }
    }
    private void checkFastBelts(IPlayer player, Integer tileId){
        if (tileId == 13 || tileId == 26 || tileId == 27 || tileId == 73 || tileId == 77 || tileId == 84){
            player.Move(Direction.NORTH);
        }
        else if (tileId == 17 || tileId == 20 || tileId == 21 || tileId == 75 || tileId == 82 || tileId == 86){
            player.Move(Direction.SOUTH);
        }
        else if (tileId == 14 || tileId == 19 || tileId == 25 || tileId == 74 || tileId == 78 || tileId == 81){
            player.Move(Direction.EAST);
        }
        else if (tileId == 18 || tileId == 22 || tileId == 28 || tileId == 76 || tileId == 83 || tileId == 85){
            player.Move(Direction.WEST);
        }
    }
    public boolean checkForObstacles(Integer xLoc, Integer yLoc, Direction dir){
        if(outOfBoundsCheck(xLoc,yLoc) || getPlayerLayer().getCell(xLoc,yLoc) != null || !wallCheck(xLoc,yLoc,dir,false)){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean wallCheck(Integer xLoc, Integer yLoc, Direction dir, Boolean second){
        Direction dirInUse = dir;
        if (second){
            if (dir == Direction.NORTH) {
                yLoc++;
            }
            else if (dir == Direction.SOUTH){
                yLoc--;
            }
            else if (dir == Direction.WEST){
                xLoc--;
            }
            else if (dir == Direction.EAST){
                xLoc++;
            }
            DirCtrl dirCtrl = new DirCtrl();
            dirInUse = dirCtrl.invertDirection(dir);
        }
        if (dirInUse == Direction.NORTH) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 1 || tileId == 9 || tileId == 16 || tileId == 24 || tileId == 31 || tileId == 45 || tileId == 94) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.SOUTH) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 3 || tileId == 8 || tileId == 11 || tileId == 29 || tileId == 32 || tileId == 37 || tileId == 87) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.WEST) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 4 || tileId == 12 || tileId == 24 || tileId == 30 || tileId == 32 || tileId == 38 || tileId == 93) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.EAST) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 2 || tileId == 8 || tileId == 10 || tileId == 16 || tileId == 23 || tileId == 46 || tileId == 95) {
                    return false;
                }
            }
        }
        if (!second) {
            if (!wallCheck(xLoc, yLoc, dir, true)){
                return false;
            }
        }
        return true;
    }
    public boolean outOfBoundsCheck(Integer xLoc, Integer yLoc){
        if (xLoc < 0 || xLoc >= PlayerLayer.getWidth() || yLoc < 0 || yLoc >= PlayerLayer.getHeight()){
            return true;
        }
        return false;
    }

    public void acquirePlayers(ArrayList<IPlayer> playerList){
        players = playerList;
    }

    // Methods to get elements from Board
    public TiledMap getMap(){
        return Map;
    }
    public TiledMapTileLayer getBoard(){
        return Board;
    }
    public TiledMapTileLayer getPlayerLayer(){
        return PlayerLayer;
    }
    public TiledMapTileLayer getLaserLayer(){
        return Laser;
    }
    public ArrayList<IPlayer> getPlayers(){
        return players;
    }

    //Finding objects of spesific types on board
    public ArrayList<Vector2> findAllFlags(){
        ArrayList<Vector2> allFlags = new ArrayList<Vector2>();
        Integer mapHeight = Flags.getHeight();
        Integer mapWidth = Flags.getWidth();
        for(Integer i = 0; i < mapHeight; i++){
            for(Integer j = 0; j < mapWidth; j++){
                if(Flags.getCell(j,i) != null){
                    Vector2 flagLoc = new Vector2();
                    flagLoc.set(j, i).cpy();
                    allFlags.add(flagLoc);;
                }
            }
        }
        return allFlags;
    }

    public ArrayList<Spawnpoint> findSpawnPoints(){
        System.out.println("Spawnpoint search started");
        ArrayList<Spawnpoint> theList = new ArrayList<>();
        Integer mapHeight = Spawnpoints.getHeight();
        Integer mapWidth = Spawnpoints.getWidth();
        for(Integer i = 0; i < mapHeight; i++){
            for(Integer j = 0; j < mapWidth; j++){
                if(Spawnpoints.getCell(j,i) != null){
                    Integer tileId = Spawnpoints.getCell(j,i).getTile().getId();
                    if(tileId == 121 || tileId == 122 || tileId == 123 || tileId == 124 ||
                            tileId == 129 || tileId == 130 || tileId == 131 || tileId == 132){
                        System.out.println("Spawnpoint found");
                        Spawnpoint point = new Spawnpoint(new Vector2(j,i),tileId);
                        theList.add(point);
                    }
                }
            }
        }
        return theList;
    }
    public Spawnpoint getSpawnPoint(Integer pointId){
        Spawnpoint point = null;
        for(Integer i = 0; i < spawnpointList.size(); i++){
            if (pointId == spawnpointList.get(i).getSpawnId()){
                point = spawnpointList.get(i);
            }
        }
        return point;
    }

    public ArrayList<LaserWall> setUpLaserWalls(){
        ArrayList<LaserWall> theList = new ArrayList<>();
        Integer mapHeight = Laser.getHeight();
        Integer mapWidth = Laser.getWidth();
        for(Integer i = 0; i < mapHeight; i++){
            for(Integer j = 0; j < mapWidth; j++){
                if(Wall.getCell(j,i) != null){
                    Integer tileId = Wall.getCell(j,i).getTile().getId();
                    if(tileId == 37 || tileId == 38 || tileId == 45 || tileId == 46 ||
                            tileId == 87 || tileId == 93 || tileId == 94 || tileId == 95){
                        theList.add(new LaserWall(tileId, new Vector2(j,i),this));
                    }
                }
            }
        }
        return theList;
    }
    public void fireAllLasers(){
        if(!laserWallLists.isEmpty()) {
            for (Integer i = 0; i < laserWallLists.size(); i++){
                laserWallLists.get(i).fireLaser();
            }
        }
    }
    public void clearLasers(){
        Integer mapHeight = Laser.getHeight();
        Integer mapWidth = Laser.getWidth();
        for(Integer i = 0; i < mapHeight; i++){
            for(Integer j = 0; j < mapWidth; j++){
                Laser.setCell(j,i,null);
            }
        }
    }
}