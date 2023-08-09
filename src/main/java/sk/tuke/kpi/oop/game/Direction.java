package sk.tuke.kpi.oop.game;

public enum Direction {
    NONE(0, 0),
    NORTH(0, 1),
    NORTHWEST(-1, 1),
    NORTHEAST(1, 1),
    WEST(-1, 0),
    SOUTHWEST(-1, -1),
    SOUTHEAST(1, -1),
    EAST(1, 0),
    SOUTH(0, -1);

    private int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle() {
        switch (this){
            case NORTH:
                return 0F;
            case SOUTH:
                return 180F;
            case NORTHWEST:
                return 45F;
            case NORTHEAST:
                return 315F;
            case SOUTHEAST:
                return 225F;
            case SOUTHWEST:
                return 135F;
            case WEST:
                return 90F;
            case EAST:
                return 270F;
            default:
                return 0f;
        }
    }

    public Direction combine(Direction other) {
        if (this == other) return other;
        if (other != null) {
            Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                if (direction.getDx() == ((this.getDx() == other.getDx()) ? getDx() : getDx() + other.getDx())
                    && direction.getDy() == ((this.getDy() == other.getDy()) ? getDy() : getDy() + other.getDy())) {
                    return direction;
                }
            }
            return NONE;
        }
        return null;
    }

    public static Direction fromAngle(float angle) {
//        if (angle == 0) return NORTH;
//        else if (angle == 270)return EAST;
//        else if (angle == 180)return SOUTH;
//        else if (angle == 45)return NORTHWEST;
//        else if (angle == 90)return WEST;
//        else if (angle == 225) return SOUTHEAST;
//        else if (angle == 135) return SOUTHWEST;
//        else if (angle == 315) return NORTHEAST;

        switch ((int) angle){
            case 0:
                return NORTH;
            case 270:
                return EAST;
            case 180:
                return SOUTH;
            case 45:
                return NORTHWEST;
            case 90:
                return WEST;
            case 225:
                return SOUTHEAST;
            case 135:
                return SOUTHWEST;
            case 315:
                return NORTHEAST;
            default:
                return NONE;
        }
    }
}
