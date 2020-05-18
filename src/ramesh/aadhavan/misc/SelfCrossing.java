package ramesh.aadhavan.misc;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SelfCrossing {
    static class Position {
        private int x;
        private int y;

        public Position() {
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Position) {
                Position other = (Position) obj;
                return (x == other.x) && (y == other.y);
            }

            return false;
        }
    }

    private Position currentPosition;
    private Set<Position> positionsTravelled;

    public SelfCrossing(int x, int y) {
        currentPosition = new Position(x, y);
        positionsTravelled = new HashSet<>();
        positionsTravelled.add(currentPosition);
    }

    public boolean isSelfCrossing(int[] x) {
        for(int i=0; i<x.length; i++) {
            if(!moveAndCommit(x[i], i%4))
                return true;
        }

        return false;
    }

    private boolean moveAndCommit(int distance, int direction) {
        Position position = new Position(currentPosition.x, currentPosition.y);

        while(distance != 0) {
            switch (direction) {
                case 0: {
                    position.y = position.y + 1;
                    break;
                }
                case 1: {
                    position.x = position.x + 1;
                    break;
                }
                case 2: {
                    position.y = position.y - 1;
                    break;
                }
                case 3: {
                    position.x = position.x - 1;
                    break;
                }
                default: {
                    break;
                }
            }

            if(positionsTravelled.contains(position))
                return false;
            else
                positionsTravelled.add(position);

            position = new Position(position.x, position.y);
            distance--;
        }

        this.currentPosition = position;
        return true;
    }
}
